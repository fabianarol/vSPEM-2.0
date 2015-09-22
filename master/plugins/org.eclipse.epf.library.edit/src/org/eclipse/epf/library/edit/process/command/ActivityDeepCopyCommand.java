//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage.Literals;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ConfigurationSetter;
import org.eclipse.epf.library.edit.util.ConstraintManager;
import org.eclipse.epf.library.edit.util.DepthLevelAdapterFactoryTreeIterator;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.ITextReferenceReplacer;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.osgi.util.NLS;

/**
 * Physically copies an activity with all of its direct or inherited structural features and references.
 *   
 * @author Phong Nguyen Le - Jun 21, 2006
 * @since  1.0
 */
public class ActivityDeepCopyCommand extends CopyCommand {
	private static final AdapterFactory[] adapterFactories = {
		TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(),
		TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory(),
		TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory()
	};
	
	private static final int[] diagramTypes = {
		IDiagramManager.ACTIVITY_DETAIL_DIAGRAM,
		IDiagramManager.ACTIVITY_DIAGRAM,
		IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM
	};
	
	public static AdapterFactory[] getAdapterFactories() {
		return adapterFactories;
	}
	
	private class EditingDomain extends AdapterFactoryEditingDomain {

		/**
		 * @param adapterFactory
		 * @param commandStack
		 */
		public EditingDomain() {
			super(TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(), new BasicCommandStack());
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain#createCommand(java.lang.Class, org.eclipse.emf.edit.command.CommandParameter)
		 */
		public Command createCommand(Class commandClass, CommandParameter commandParameter) {
			if(commandClass == CreateCopyCommand.class) {
				return new CreateDeepCopyCommand(this, commandParameter.getEOwner(), (Helper) commandParameter.getValue());
			}
			if(commandClass == InitializeCopyCommand.class) {
				return new InitializeDeepCopyCommand(this, commandParameter.getEOwner(), (Helper) commandParameter.getValue());
			}
			return super.createCommand(commandClass, commandParameter);
		}
	}
	
	private class CreateDeepCopyCommand extends CreateCopyCommand {

		/**
		 * @param domain
		 * @param owner
		 * @param copyHelper
		 */
		public CreateDeepCopyCommand(EditingDomain domain, EObject owner, Helper copyHelper) {
			super(domain, owner, copyHelper);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.edit.command.CreateCopyCommand#doGetChildrenToCopy()
		 */
		public Collection<?> doGetChildrenToCopy() {
			ConfigurationSetter configSetter = new ConfigurationSetter(getAdapterFactory());
			try {
				configSetter.set(config);
				IConfigurator configurator = configSetter.getConfigurator();
				
				// Create commands to create copies of the children.
				//
				List<Object> result = new ArrayList<Object>();
				for (Iterator<EObject> i = owner.eContents().iterator(); i.hasNext(); )
				{
					Object o = i.next();
					if(o instanceof Activity) {
						// make sure that Activity will be copied first
						// so references to its inherited elements can be resolved
						// properly
						//
						if(configurator == null || configurator.accept(o)) {
							result.add(0, o);
						}
					}
					else {
						result.add(o);
					}
				}
				return result;
			}
			finally {
				configSetter.restore();
			}
		}
		
	}
	
	private class InitializeDeepCopyCommand extends MethodElementInitializeCopyCommand {
		/**
		 * List of GUIDs of activity starting from the top process to 
		 */
		private List<String> guidList;
		
		/**
		 * @param domain
		 * @param owner
		 * @param copyHelper
		 */
		public InitializeDeepCopyCommand(EditingDomain domain, EObject owner, Helper copyHelper) {
			super(domain, owner, copyHelper);
			copy = (EObject) copyHelper.get(owner);
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand#copyReferences()
		 */
		protected void copyReferences() {
			Activity base = null;
			Activity baseCopy = null;
			Collection<Object> childBaseCopies = new HashSet<Object>();
			ConfigurationSetter configSetter = null;
			IConfigurator configurator = null;
			boolean baseAccepted = false;
			try {
				if(owner instanceof Activity) {
					Activity activity = (Activity) owner;
					if(ProcessUtil.isExtendingOrLocallyContributing(activity)) {
						configSetter = new ConfigurationSetter(getAdapterFactory());
						configSetter.set(config);
						configurator = configSetter.getConfigurator();
						// copy inherited elements by deep-copying base activity
						//
						// deep copy of base activity at this point is essential so references to predecessors
						// will be copied correctly
						//
						base = (Activity) activity.getVariabilityBasedOnElement();
						baseAccepted = configurator == null || configurator.accept(base);
						if(baseAccepted) {							
							CopyHelper helper = (CopyHelper)copyHelper;
							Activity oldBaseCopy = (Activity) helper.get(base);
							// this will redirect any reference to copy of base in diagram to the copy of the activity
							//
							helper.putVariabilityElement(base, activity);
							ArrayList childBases = new ArrayList();
							for (Iterator iter = activity.getBreakdownElements().iterator(); iter.hasNext();) {
								Object e = iter.next();
								if(e instanceof VariabilityElement) {
									VariabilityElement ve = (VariabilityElement) e;
									VariabilityElement baseElement = ve.getVariabilityBasedOnElement();
									if(baseElement != null &&
											(ve.getVariabilityType() == VariabilityType.LOCAL_CONTRIBUTION_LITERAL || ve.getVariabilityType() == VariabilityType.LOCAL_REPLACEMENT_LITERAL))
									{
										helper.putVariabilityElement(baseElement, ve);
										childBases.add(baseElement);
									}
								}
							}

							ActivityDeepCopyCommand cmd = new ActivityDeepCopyCommand(base, helper, config, targetProcess, monitor, false);
							cmd.guidList = new LinkedList<String>(ActivityDeepCopyCommand.this.guidList);
							cmd.guidList.addAll(ProcessUtil.getGuidList(ActivityDeepCopyCommand.this.activity, activity));
							try {
								cmd.execute();

								baseCopy = (Activity) helper.get(base);
								if(activity.getVariabilityType() == VariabilityType.EXTENDS_LITERAL) {
									// keep activity only as backup copy of base
									//
									helper.removeVariabilityElement(base);
									helper.putBackupCopy(base, activity);

									// put back oldBaseCopy
									if(oldBaseCopy != null) {
										helper.basicPut(base, oldBaseCopy);
									}
								}

								// remove the copies of all base since base copies are not added to the process
								//
								helper.remove(base);
								for (Iterator iter = childBases.iterator(); iter.hasNext();) {
									EObject childBaseCopy = (EObject) helper.remove(iter.next());
									if(childBaseCopy != null) {
										childBaseCopies.add(childBaseCopy);
										EcoreUtil.remove(childBaseCopy.eContainer());
									}
								}

								Activity activityCopy = (Activity) copy;

								// remove the copies of base diagram if the activity already have diagram of the same type
								//
								for (int i = 0; i < diagramTypes.length; i++) {
									int type = diagramTypes[i];
									if(diagramMgr.getDiagram(activity, type) != null) {
										Diagram diagram = diagramMgr.getDiagram(activityCopy, type);
										if(diagram != null) {
											EcoreUtil.remove(diagram);
										}
									}
								}

								// move content of base package over to the activity's package
								//
								moveContent(cmd.pkgCopy, activityCopy);	

								// record the copy of the wrappers
								//
								for (Iterator iterator = baseCopy.getBreakdownElements().iterator(); iterator
								.hasNext();) {
									BreakdownElement e = (BreakdownElement) iterator.next();
									if(!childBaseCopies.contains(e)) {
										BreakdownElement original = (BreakdownElement) helper.getOriginal(e);
										List<String> list = new ArrayList<String>(cmd.guidList);
										list.add(original.getGuid());
										helper.putWrapperCopy(ProcessUtil.toGuidPath(list), e);								
									}
								}
							}
							finally {
								cmd.dispose();
							}
						}
					}
				}

				super.copyReferences();

				if(base != null) {
					Activity activity = (Activity) owner;
					Activity activityCopy = ((Activity) copy);					
					
					if(baseAccepted) {
						// copy inherited breakdown elements from the base activity in the right order
						//
						MethodConfiguration currentConfig = configSetter.getOldConfiguration();

						// Add breakdown elements from base copy to the activity copy. 
						// Don't add copy of base element of the existing children
						//
						List<BreakdownElement> breakdownElements = new ArrayList<BreakdownElement>();
						for (Iterator<BreakdownElement> iter = baseCopy.getBreakdownElements().iterator(); iter.hasNext();) {
							BreakdownElement e = iter.next();
							if(!childBaseCopies.contains(e) && (configurator == null || configurator.accept(e))) {
								breakdownElements.add(e);
							}						
						}
						activityCopy.getBreakdownElements().addAll(0, breakdownElements);
						baseCopy.setSuperActivities(null);
						EcoreUtil.remove(baseCopy);


						// copy other string attributes as well after realize them
						//
						MethodConfiguration copyConfig = config == null ? (currentConfig != null ? currentConfig
								: targetProcess.getDefaultContext())
								: config;
						for (EAttribute attribute : getAttributesToCopy()) {
							if (attribute.isChangeable() && !attribute.isDerived()) {
								Object value = Providers.getConfigurationApplicator().getAttribute(activity, attribute, copyConfig);
								activityCopy.eSet(attribute, value);
							}
						}
						ContentDescription content = activity.getPresentation();
						for (EAttribute attribute : content.eClass().getEAllAttributes()) {
							if (attribute.isChangeable() && !attribute.isDerived()
									&& attribute.getEAttributeType().getInstanceClass() == Literals.STRING.getInstanceClass())
							{
								Object value = Providers.getConfigurationApplicator().getAttribute(content, activity, attribute, copyConfig);
								activityCopy.getPresentation().eSet(attribute, value);
							}
						}
					}

					// copy presentation name
					//
					activityCopy.setPresentationName(ProcessUtil.getPresentationName(activity));					

					// clear the variability data
					//
					activityCopy.setVariabilityBasedOnElement(null);
					activityCopy.setVariabilityType(null);
				}
			}
			finally {
				if(configSetter != null) {
					configSetter.restore();
				}
			}

			if(copy instanceof Activity) {
				Activity activityCopy = ((Activity) copy);					

				// clear suppression data
				//
				Constraint rule = ConstraintManager.getConstraint(activityCopy, ConstraintManager.PROCESS_SUPPRESSION, false);
				if(rule != null) {
					EcoreUtil.remove(rule);
				}
			}
		}		
	}

	protected ProcessPackage pkgCopy;
	protected MethodConfiguration config;
	protected IDiagramManager diagramMgr;

	protected Process targetProcess;

	protected IProgressMonitor monitor;

	protected Activity activity;

	private LinkedList<String> guidList;

	private BreakdownElementWrapperItemProvider wrapper;
	
	private ActivityDeepCopyCommand(Activity activity, CopyHelper copyHelper,
			MethodConfiguration config, Process targetProcess,
			IProgressMonitor monitor, boolean initGuidList) {
		super(null, activity.eContainer(), copyHelper);
		Assert.isTrue(activity.eContainer() instanceof ProcessPackage, "Activity's container must be a ProcessPackage"); //$NON-NLS-1$
		this.activity = activity;
		domain = new EditingDomain();
		this.config = config;
		this.targetProcess = targetProcess;
		diagramMgr = ExtensionManager.getDiagramManager();
		Assert.isNotNull(diagramMgr, "Could not load diagram manager"); //$NON-NLS-1$
		this.monitor = monitor;
		if(initGuidList) {
			guidList = ProcessUtil.getGuidList(null, this.activity);
		}
	}
	
	public ActivityDeepCopyCommand(Activity activity, CopyHelper copyHelper,
			MethodConfiguration config, Process targetProcess,
			IProgressMonitor monitor) {
		this(activity, copyHelper, config, targetProcess, monitor, true);
	}
	
	public ActivityDeepCopyCommand(Object activityOrWrapper, CopyHelper copyHelper, MethodConfiguration config, Process targetProcess, IProgressMonitor monitor) {
		this((Activity)TngUtil.unwrap(activityOrWrapper), copyHelper, config, targetProcess, monitor, 
				activityOrWrapper instanceof Activity);
		if(activityOrWrapper instanceof BreakdownElementWrapperItemProvider) {
			wrapper = (BreakdownElementWrapperItemProvider) activityOrWrapper;
			guidList = ProcessUtil.getGuidList(wrapper);
		}
	}	
	
	public AdapterFactory getAdapterFactory() {
		return ((AdapterFactoryEditingDomain)domain).getAdapterFactory();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.CopyCommand#execute()
	 */
	public void execute() {
		monitor.subTask(NLS.bind(LibraryEditResources.copyingActivities_msg, ProcessUtil.getPresentationName(activity)));
		super.execute();
		Collection<?> result = super.getResult();
		if(!result.isEmpty()) {
			pkgCopy = (ProcessPackage) result.iterator().next();
			fixProcessComponent();
			replaceTextReferences();
		}
	}
	
	/**
	 * fix the process component to make it a process package.
	 * This method might be overriden by subclass to achive difference result.
	 * For example, 
	 */
	protected void fixProcessComponent() {
		if(pkgCopy instanceof ProcessComponent) {
			// replace the ProcessComponent with a equivalent ProcessPackage
			//
			ProcessPackage pkg = UmaFactory.eINSTANCE.createProcessPackage();
			Collection features = new ArrayList(pkgCopy.eClass().getEAllStructuralFeatures());
			features.retainAll(pkg.eClass().getEAllStructuralFeatures());
			for (Iterator iter = features.iterator(); iter.hasNext();) {
				EStructuralFeature feature = (EStructuralFeature) iter.next();
				Object value = pkgCopy.eGet(feature);
				pkg.eSet(feature, value);
			}
			Process proc = ((ProcessComponent)pkgCopy).getProcess();
			if(proc != null) {
				pkg.getProcessElements().add(0, proc);
			}
			pkgCopy = pkg;
		}		
	}
	
	/**
	 * If textual descriptions in the copied elements contain references (URLs) to other elements 
	 * within the same copied process then replace these references with references that point to 
	 * the new elements in the copied structures.
	 */
	protected void replaceTextReferences() {
		ITextReferenceReplacer txtRefReplacer = ExtensionManager.getTextReferenceReplacer();
		if(txtRefReplacer == null) return;

		Map oldToNewObjectMap = ((CopyHelper) copyHelper).getObjectToCopyMap();
		for (Iterator iter = pkgCopy.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			for (Iterator attributes = element.eClass().getEAllAttributes().iterator(); attributes.hasNext(); )
			{
				EAttribute attribute = (EAttribute) attributes.next();
				if (attribute.isChangeable() && !attribute.isDerived() && (attribute.isMany() || element.eIsSet(attribute))
						&& attribute.getEAttributeType().getInstanceClass() == Literals.STRING.getInstanceClass())
				{
					String text = (String) element.eGet(attribute);
					if(text != null) {
						text = txtRefReplacer.replace(text, targetProcess, oldToNewObjectMap);
						element.eSet(attribute, text);
					}
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.CompoundCommand#getResult()
	 */
	public Collection<?> getResult() {
		if(pkgCopy != null) {
			Activity act = ProcessUtil.findActivity(pkgCopy);
			if(act != null) {
				return Collections.singletonList(act);
			}
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * Moves content of source object <code>src</code> over to the target objec <code>target</code>
	 * @param src
	 * @param target
	 */
	private static void moveContent(ProcessPackage src, Activity act) {
		ProcessPackage target = (ProcessPackage) act.eContainer();
		// move content of base package over to the activity's package					
		for (Iterator iter = new ArrayList(src.eContents()).iterator(); iter.hasNext();) {
			EObject e = (EObject) iter.next();
			EStructuralFeature f = e.eContainingFeature();						
			if(f.isMany()) {
				((List)target.eGet(f)).add(e);
			}
		}
		if(src instanceof ProcessComponent) {
			Process baseCopy = ((ProcessComponent)src).getProcess();
			target.getProcessElements().add(baseCopy);
		}

	}
	
	private static BreakdownElement getTopElement(BreakdownElement e) {
		if(e == null) {
			return null;
		}
		BreakdownElement top = e;
		for(BreakdownElement o = e.getSuperActivities(); o != null; o = o.getSuperActivities()) {
			top = o;
		}
		return top;
	}
	
	public void copySuppressionStates() {
		Collection<?> result = getResult();
		if(result.isEmpty()) {
			return;
		}
		String wrapperPath = wrapper != null ? ProcessUtil.toGuidPath(guidList) : null;
		for (int i = 0; i < adapterFactories.length; i++) {
			AdapterFactory adapterFactory = adapterFactories[i];
			boolean isWBS = adapterFactory == TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
			IFilter filter = ProcessUtil.getFilter(adapterFactory);
			IConfigurator configurator = null;
			MethodConfiguration currentConfig = null;
			if(filter instanceof IConfigurator) {
				configurator = (IConfigurator) filter;
				currentConfig = configurator.getMethodConfiguration();
				configurator.setMethodConfiguration(config);
			}
			try {
				Activity act = ProcessUtil.findActivity((ProcessPackage) owner);
				ProcessUtil.initializeItemProviderPath(act, adapterFactory);
				Process proc = TngUtil.getOwningProcess(act);
				Suppression suppression = Suppression.getSuppression(proc);
				DepthLevelAdapterFactoryTreeIterator<?> iter = new DepthLevelAdapterFactoryTreeIterator<Object>(adapterFactory, proc);
				int depthLevel = -1;
				find_deep_copied_activity:
					while(iter.hasNext()) {
						Object child = iter.next();

						// set depth level if the current child is the deep-copied activity
						//
						if(wrapper == null) {
							if(child == activity) {
								depthLevel = iter.getDepthLevel();
								break find_deep_copied_activity;
							}
						}
						else {
							if(child instanceof BreakdownElementWrapperItemProvider) {
								String path = Suppression.getPathWithoutViewType((BreakdownElementWrapperItemProvider) child).toString();
								if(wrapperPath.equals(path)) {
									depthLevel = iter.getDepthLevel();
									break find_deep_copied_activity;
								}
							}
						}					
					}
				
				// copy suppression state of all suppressed elements under deep-copied activity
				//
				CopyHelper helper = (CopyHelper)copyHelper;
				Map<?, ?> originalToCopyMap = helper.getObjectToCopyMap();
				Map<Object, Object> elementOrWrapperToCopyMap = null;
				if(isWBS) {
					elementOrWrapperToCopyMap = new HashMap<Object, Object>();
					Activity deepCopy = (Activity) result.iterator().next();
					elementOrWrapperToCopyMap.put(wrapper != null ? wrapper : activity, deepCopy);
				}
				while(iter.hasNext()) {
					Object child = iter.next();
					if(iter.getDepthLevel() > depthLevel) {
						Object e = TngUtil.unwrap(child);
						if(e instanceof BreakdownElement) {
							if(!isWBS && e instanceof Activity) {
								// skip copying suppression state and ordering
								// breakdown elements for activity in other view
								// than WBS
								//
								continue;
							}
//							BreakdownElement original = (BreakdownElement) e;
//							BreakdownElement copy = (BreakdownElement) originalToCopyMap.get(original);
//							
//							if(TngUtil.DEBUG) {
//								String copyName = (copy != null ? copy.getName() : null);
//								BreakdownElement top = getTopElement(copy);
//								if(copy == null || !original.getName().equals(copyName) || top != deepCopy) {
//									System.err.println("original: '" + original.getName() + "', copy: '" + copyName + "', copy's top activity: " + top);
//								}
//							}
							BreakdownElement copy = null;
							boolean isSuppressed = suppression.isSuppressed(child);
							
							// find copy to suppress or, if element is an activity, to reorder its breakdown elements
							//
							if(isSuppressed || isWBS) {
								if(child == e) {	
									copy = (BreakdownElement) originalToCopyMap.get(e);
								}
								else if(child instanceof BreakdownElementWrapperItemProvider) {
									BreakdownElementWrapperItemProvider childWrapper = (BreakdownElementWrapperItemProvider) child;
									if(childWrapper.isInherited()) {
										String guidPath = Suppression.getPathWithoutViewType(childWrapper).toString();
										copy = helper.getWrapperCopy(guidPath);
									}
								}
								if(copy != null) {
									if(isSuppressed) {
										copy.setSuppressed(Boolean.TRUE);
									}
									if(isWBS) {
										elementOrWrapperToCopyMap.put(child, copy);
									}
								}
							}
						}
					}
					else {
						break;
					}
				}
				// reorder breakdown elements of activities
				//
				if(isWBS) {
					for (Map.Entry<Object, Object> entry : elementOrWrapperToCopyMap.entrySet()) {
						Object o = entry.getKey();
						Object copy = entry.getValue();
						if(copy instanceof Activity) {
							ITreeItemContentProvider ip = (ITreeItemContentProvider) adapterFactory.adapt(o, ITreeItemContentProvider.class);
							Collection<?> children = ip.getChildren(o);
							EList<?> list =  (EList<?>) ((Activity) copy).getBreakdownElements();
							List<?> childrenList = children instanceof List<?> ? (List<?>) children : new ArrayList<Object>(children);
							int size = childrenList.size();
							for (int j = 0; j < size; j++) {
								Object child = childrenList.get(j);
								Object childCopy = elementOrWrapperToCopyMap.get(child);
								if(childCopy != null) {
									if(j < size - 1) {
										Object nextChild = childrenList.get(j + 1);
										Object nextChildCopy = elementOrWrapperToCopyMap.get(nextChild);	
										if(nextChildCopy != null) {
											int succId = list.indexOf(nextChildCopy);
											if(succId != -1) {
												int id = list.indexOf(childCopy);
												if(id != succId - 1) {
													if(id < succId) {
														list.move(succId - 1, id);
													}
													else {
														list.move(id, succId);
													}
												}
											}										
										}
										else if(TngUtil.DEBUG) {
											System.err.println("Element does not have copy: " + nextChild); //$NON-NLS-1$
										}
									}
								}
								else if(TngUtil.DEBUG) {
									System.err.println("Element does not have copy: " + child); //$NON-NLS-1$
								}
							}
						}
					}
				}
			}
			finally {
				if(configurator != null) {
					configurator.setMethodConfiguration(currentConfig);
				}
			}
		}
	}
	
	public CopyHelper getCopyHelper() {
		return (CopyHelper) copyHelper;
	}
}