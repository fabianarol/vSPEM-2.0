package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.command.MethodElementCreateChildCommand;
import org.eclipse.epf.library.edit.process.IProcessItemProvider;
import org.eclipse.epf.library.edit.process.IVariantsItemProvider;
import org.eclipse.epf.library.edit.process.command.CreateProcessComponentCommand;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarActivity;
import org.eclipse.epf.uma.VarIteration;
import org.eclipse.epf.uma.VarMilestone;
import org.eclipse.epf.uma.VarPhase;
import org.eclipse.epf.uma.VarRoleDescriptor;
import org.eclipse.epf.uma.VarTaskDescriptor;
import org.eclipse.epf.uma.VarTeamProfile;
import org.eclipse.epf.uma.VarWorkProductDescriptor;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a process package in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class VariantsPackageItemProvider extends
		org.eclipse.epf.uma.provider.VariantsPackageItemProvider implements
		IVariantsItemProvider, IStatefulItemProvider {

	protected EClass variantType;

	private String label;

	private ProcessLineComponentItemProvider parent;
	
	protected Collection children;
	
	private MethodPlugin plugin;

	private String processLineName;

	private ModelStructure modelStructure;

	private VariantsPackage variantsPkg;

	/**
	 * Creates a new instance.
	 */
	public VariantsPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EClass getProcessType() {
		return variantType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
//		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
//				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
//				.createProcessPackage()));
//
//		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
//				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
//				.createProcessComponent()));
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodPackage_ChildPackages());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * !FIXME Se puede hacer mas eficiente (V 2.0)
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		//Limpiamos los hijos (Refrescamos la lista entera)
		children = new ArrayList();
		getVariantPackage().getVariant().clear();
		
		if(object instanceof MethodPackage){
			MethodPackage pkg = (MethodPackage) object;	
		
			//Variantes del paquete de Capability
	//		org.eclipse.epf.uma.CoreProcessPackage pkgCapability = (org.eclipse.epf.uma.CoreProcessPackage) UmaUtil.findCorePackage(plugin, modelStructure.variabilityCapabilityPatternPath, getProcessLineOwner());
			org.eclipse.epf.uma.CoreProcessPackage pkgCapability = UmaUtil.getVPackageProcess(pkg, modelStructure.variabilityCapabilityPatternPath);
			if (pkgCapability != null){
				Collection auxProcesses = pkgCapability.getChildPackages();
				//Recorremos procesos
				for(Iterator iterator = auxProcesses.iterator(); iterator.hasNext();){
					EObject elementProcess = (EObject) iterator.next();
					
					if (elementProcess instanceof ProcessComponent){
						ProcessComponent elementProcessTarget = (ProcessComponent) elementProcess;
							Collection auxElements = elementProcessTarget.getChildPackages();
							/**
							 * Recorrido de childs
							 */
							for(Iterator iterElement = auxElements.iterator(); iterElement.hasNext();){
								EObject elementChild = (EObject) iterElement.next();
								//Si es un ProcessPackage (Hay que ver el objeto de nuevo (desencapsularlo))
								if (elementChild instanceof ProcessPackage){
									ProcessPackage auxPackage = (ProcessPackage) elementChild;
									
									if(auxPackage.getProcessElements().get(0) instanceof Variant){
	//									children.add(auxPackage.getProcessElements().get(0));
										
										Variant variant = (Variant) auxPackage.getProcessElements().get(0);
										
										//Actividad
										if(variant instanceof VarActivity){
											VarActivityItemProvider adapter = (VarActivityItemProvider) TngUtil
											.getBestAdapterFactory(adapterFactory).adapt(variant,
													ITreeItemContentProvider.class);
											children.add(variant);
											
										}
										//Phase
										if(variant instanceof VarPhase){
											VarPhaseItemProvider adapter = (VarPhaseItemProvider) TngUtil
											.getBestAdapterFactory(adapterFactory).adapt(variant,
													ITreeItemContentProvider.class);
											children.add(variant);
											
										}
										//Iteration
										if(variant instanceof VarIteration){
											VarIterationItemProvider adapter = (VarIterationItemProvider) TngUtil
											.getBestAdapterFactory(adapterFactory).adapt(variant,
													ITreeItemContentProvider.class);
											children.add(variant);
											
										}
	
									}
										
								}
							}
							auxElements = elementProcessTarget.getProcessElements();
							/**
							 * Recorrido de ProcessElements
							 */
							for(Iterator iterElement = auxElements.iterator(); iterElement.hasNext();){
								EObject elementChild = (EObject) iterElement.next();
								if ((elementChild instanceof ProcessElement) && (elementChild instanceof Variant )){
									Variant variant = (Variant) elementChild;
									//Role Descriptor
									if(variant instanceof VarRoleDescriptor){
										VarRoleDescriptorItemProvider adapter = (VarRoleDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//Task Descriptor
									if(variant instanceof VarTaskDescriptor){
										VarTaskDescriptorItemProvider adapter = (VarTaskDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//WorkProduct Descriptor
									if(variant instanceof VarWorkProductDescriptor){
										VarWorkProductDescriptorItemProvider adapter = (VarWorkProductDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//TeamProfile
									if(variant instanceof VarTeamProfile){
										VarTeamProfileItemProvider adapter = (VarTeamProfileItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//Milestone
									if(variant instanceof VarMilestone){
										VarMilestoneItemProvider adapter = (VarMilestoneItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
								}
							}
							
						}
					}
				
			}
		
			//Variantes del paquete de Delivery
	//		org.eclipse.epf.uma.CoreProcessPackage pkgDelivery = (org.eclipse.epf.uma.CoreProcessPackage) UmaUtil.findCorePackage(plugin, modelStructure.variabilityDeliveryProcessPath, getProcessLineOwner());
			org.eclipse.epf.uma.CoreProcessPackage pkgDelivery= UmaUtil.getVPackageProcess(pkg, modelStructure.variabilityDeliveryProcessPath);
			if (pkgDelivery != null){
				Collection auxElements = pkgDelivery.getChildPackages();
				
				for(Iterator iterator = auxElements.iterator(); iterator.hasNext();){
					EObject elementProcess = (EObject) iterator.next();
					
					if (elementProcess instanceof ProcessComponent){
						ProcessComponent elementProcessTarget = (ProcessComponent) elementProcess;
							Collection auxElementsVa = elementProcessTarget.getChildPackages();
							
							/**
							 * Recorrido de childs
							 */
							for(Iterator iterElement = auxElementsVa.iterator(); iterElement.hasNext();){
								EObject elementChild = (EObject) iterElement.next();
								//Si es un ProcessPackage (Hay que ver el objeto de nuevo (desencapsularlo))
								if (elementChild instanceof ProcessPackage){
									ProcessPackage auxPackage = (ProcessPackage) elementChild;
									
									if(auxPackage.getProcessElements().get(0) instanceof Variant){
	//									children.add(auxPackage.getProcessElements().get(0));
										
										Variant variant = (Variant) auxPackage.getProcessElements().get(0);
										
										//Actividad
										if(variant instanceof VarActivity){
											VarActivityItemProvider adapter = (VarActivityItemProvider) TngUtil
											.getBestAdapterFactory(adapterFactory).adapt(variant,
													ITreeItemContentProvider.class);
											children.add(variant);
											
										}
										//Phase
										if(variant instanceof VarPhase){
											VarPhaseItemProvider adapter = (VarPhaseItemProvider) TngUtil
											.getBestAdapterFactory(adapterFactory).adapt(variant,
													ITreeItemContentProvider.class);
											children.add(variant);
											
										}
										//Iteration
										if(variant instanceof VarIteration){
											VarIterationItemProvider adapter = (VarIterationItemProvider) TngUtil
											.getBestAdapterFactory(adapterFactory).adapt(variant,
													ITreeItemContentProvider.class);
											children.add(variant);
											
										}
									}
										
								}
							}
							auxElements = elementProcessTarget.getProcessElements();
							/**
							 * Recorrido de Process Elements
							 */
							for(Iterator iterElement = auxElements.iterator(); iterElement.hasNext();){
								EObject elementChild = (EObject) iterElement.next();
								if ((elementChild instanceof ProcessElement) && (elementChild instanceof Variant )){
									Variant variant = (Variant) elementChild;
									//Role Descriptor
									if(variant instanceof VarRoleDescriptor){
										VarRoleDescriptorItemProvider adapter = (VarRoleDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//Task Descriptor
									if(variant instanceof VarTaskDescriptor){
										VarTaskDescriptorItemProvider adapter = (VarTaskDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//WorkProduct Descriptor
									if(variant instanceof VarWorkProductDescriptor){
										VarWorkProductDescriptorItemProvider adapter = (VarWorkProductDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//TeamProfile
									if(variant instanceof VarTeamProfile){
										VarTeamProfileItemProvider adapter = (VarTeamProfileItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
									
									//Milestone
									if(variant instanceof VarMilestone){
										VarMilestoneItemProvider adapter = (VarMilestoneItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(variant,
												ITreeItemContentProvider.class);
										children.add(variant);
										
									}
								}
							}
						}
					}	
			}
		}
		getVariantPackage().getVariant().addAll(children);//Sincronizo con el paquete		
		return children;
	}
	/**
	 * Añade el plugin
	 */
	public void setPlugin(MethodPlugin plugin){
		this.plugin = plugin;
	}
	
	/**
	 * Añade un nuevo hijo Variante
	 * @param object
	 */
	public void addChildren (Object object){
		if (object instanceof Variant){
			children.add(object);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Variants_type");
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/variants");
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setProcessType(EClass variantType) {
		this.variantType = variantType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createInitializeCopyCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.edit.command.CopyCommand.Helper)
	 */
	protected Command createInitializeCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementInitializeCopyCommand(domain, owner, helper);
	}

	protected Command createCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementCreateCopyCommand(domain, owner, helper);
	}

	public void setParent(ProcessLineComponentItemProvider parent) {
		this.parent = parent;
	}

	public void setProcessLineOwner(String name){
		this.processLineName = name;
	}
	
	public String getProcessLineOwner(){
		return this.processLineName;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		return super.getParent(object);
	}
	
	public void setModelStructure(ModelStructure modelStructure){
		this.modelStructure = modelStructure;
	}
	
	public ModelStructure getModelStructure(){
		return this.modelStructure;
	}

	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);
	
		super.notifyChanged(notification);
	}

	public void setVariantPackage(VariantsPackage pkg) {
		this.variantsPkg = pkg;
		
	}
	
	public VariantsPackage getVariantPackage(){
		return this.variantsPkg;
	}
}

