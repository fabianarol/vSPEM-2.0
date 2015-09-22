package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.process.IVarPointsItemProvider;
import org.eclipse.epf.library.edit.process.IVariantsItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.VarPointsPackage;
import org.eclipse.epf.uma.VarRoleDescriptor;
import org.eclipse.epf.uma.VarTaskDescriptor;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.vpActivity;
import org.eclipse.epf.uma.vpIteration;
import org.eclipse.epf.uma.vpMilestone;
import org.eclipse.epf.uma.vpPhase;
import org.eclipse.epf.uma.vpRoleDescriptor;
import org.eclipse.epf.uma.vpTaskDescriptor;
import org.eclipse.epf.uma.vpTeamProfile;
import org.eclipse.epf.uma.vpWorkProductDescriptor;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.util.UmaUtil;

public class VarPointsPackageItemProvider extends 
			org.eclipse.epf.uma.provider.VarPointsPackageItemProvider implements
			IVarPointsItemProvider, IStatefulItemProvider{
	
	protected EClass varPointType;

	private String label;

	private CoreProcessesItemProvider parent;
	
	protected Collection children;
	
	private MethodPlugin plugin;

	private String processLineName;

	private ModelStructure modelStructure;

	private VarPointsPackage pkgVarPoints;
	
	
	public VarPointsPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EClass getProcessType() {
		return varPointType;
	}

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
	
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodPackage_ChildPackages());
		}
		return childrenFeatures;
	}
	
	//!FIXME
	public Collection getChildren(Object object) {
		//Limpiamos los hijos (Refrescamos la lista entera)
		children = new ArrayList();
		getVarPointsPackage().getVarPoints().clear();
		
		
		if(object instanceof MethodPackage){
			MethodPackage pkg = (MethodPackage) object;	
			
			//Var Points del paquete de Capability
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
									
									if(auxPackage.getProcessElements().get(0) instanceof VarPoint){						
										org.eclipse.epf.uma.VarPoint varPoint = (VarPoint) auxPackage.getProcessElements().get(0);
										if(varPoint != null){
											
											//Actividad
											if (varPoint instanceof vpActivity){
												vpActivityItemProvider adapter = (vpActivityItemProvider) TngUtil
												.getBestAdapterFactory(adapterFactory).adapt(varPoint,
														ITreeItemContentProvider.class);
												adapter.setPlugin(plugin);
												adapter.setProcessLineOwner(getProcessLineOwner());
												children.add(varPoint);
											}
											//Phase
											if (varPoint instanceof vpPhase){
												vpPhaseItemProvider adapter = (vpPhaseItemProvider) TngUtil
												.getBestAdapterFactory(adapterFactory).adapt(varPoint,
														ITreeItemContentProvider.class);
												adapter.setPlugin(plugin);
												adapter.setProcessLineOwner(getProcessLineOwner());
												children.add(varPoint);
											}
											//Iteration
											if (varPoint instanceof vpIteration){
												vpIterationItemProvider adapter = (vpIterationItemProvider) TngUtil
												.getBestAdapterFactory(adapterFactory).adapt(varPoint,
														ITreeItemContentProvider.class);
												adapter.setPlugin(plugin);
												adapter.setProcessLineOwner(getProcessLineOwner());
												children.add(varPoint);
											}
	
											
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
								if ((elementChild instanceof ProcessElement) && (elementChild instanceof VarPoint )){
									VarPoint varPoint = (VarPoint) elementChild;
									//Role Descriptor
									if (varPoint instanceof vpRoleDescriptor){
										vpRoleDescriptorItemProvider adapter = (vpRoleDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//Task Descriptor
									if (varPoint instanceof vpTaskDescriptor){
										vpTaskDescriptorItemProvider adapter = (vpTaskDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//WorkProduct Descriptor
									if (varPoint instanceof vpWorkProductDescriptor){
										vpWorkProductDescriptorItemProvider adapter = (vpWorkProductDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//TeamProfile
									if (varPoint instanceof vpTeamProfile){
										vpTeamProfileItemProvider adapter = (vpTeamProfileItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//Milestone
									if (varPoint instanceof vpMilestone){
										vpMilestoneItemProvider adapter = (vpMilestoneItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
								}
							}
						}
					}
				
			}
		
			//Var Points del paquete de Delivery
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
									
									if(auxPackage.getProcessElements().get(0) instanceof VarPoint){
										
										org.eclipse.epf.uma.VarPoint varPoint = (VarPoint) auxPackage.getProcessElements().get(0);
										if(varPoint != null){
											
											//Actividad
											if (varPoint instanceof vpActivity){
												vpActivityItemProvider adapter = (vpActivityItemProvider) TngUtil
												.getBestAdapterFactory(adapterFactory).adapt(varPoint,
														ITreeItemContentProvider.class);
												adapter.setPlugin(plugin);
												adapter.setProcessLineOwner(getProcessLineOwner());
												children.add(auxPackage.getProcessElements().get(0));
											}
											//Phase
											if (varPoint instanceof vpPhase){
												vpPhaseItemProvider adapter = (vpPhaseItemProvider) TngUtil
												.getBestAdapterFactory(adapterFactory).adapt(varPoint,
														ITreeItemContentProvider.class);
												adapter.setPlugin(plugin);
												adapter.setProcessLineOwner(getProcessLineOwner());
												children.add(varPoint);
											}
											//Iteration
											if (varPoint instanceof vpIteration){
												vpIterationItemProvider adapter = (vpIterationItemProvider) TngUtil
												.getBestAdapterFactory(adapterFactory).adapt(varPoint,
														ITreeItemContentProvider.class);
												adapter.setPlugin(plugin);
												adapter.setProcessLineOwner(getProcessLineOwner());
												children.add(varPoint);
											}
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
								if ((elementChild instanceof ProcessElement) && (elementChild instanceof VarPoint )){
									VarPoint varPoint = (VarPoint) elementChild;
									//Role Descriptor
									if (varPoint instanceof vpRoleDescriptor){
										vpRoleDescriptorItemProvider adapter = (vpRoleDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//Task Descriptor
									if (varPoint instanceof vpTaskDescriptor){
										vpTaskDescriptorItemProvider adapter = (vpTaskDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//WorkProduct Descriptor
									if (varPoint instanceof vpWorkProductDescriptor){
										vpWorkProductDescriptorItemProvider adapter = (vpWorkProductDescriptorItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//TeamProfile
									if (varPoint instanceof vpTeamProfile){
										vpTeamProfileItemProvider adapter = (vpTeamProfileItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
									//Milestone
									if (varPoint instanceof vpMilestone){
										vpMilestoneItemProvider adapter = (vpMilestoneItemProvider) TngUtil
										.getBestAdapterFactory(adapterFactory).adapt(varPoint,
												ITreeItemContentProvider.class);
										adapter.setPlugin(plugin);
										adapter.setProcessLineOwner(getProcessLineOwner());
										children.add(varPoint);
									}
								}
							}
						}
					}	
			}
		}
		
		getVarPointsPackage().getVarPoints().addAll(children);//Sincronizo con el paquete
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
		return LibraryEditPlugin.INSTANCE.getString("_UI_Var_Points_type");
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/VarPointsPackage");
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setProcessType(EClass varPointType) {
		this.varPointType = varPointType;
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

	public void setParent(CoreProcessesItemProvider coreProcessesItemProvider) {
		this.parent = coreProcessesItemProvider;
	}

	public void setProcessLineOwner(String name){
		this.processLineName = name;
	}
	
	public String getProcessLineOwner(){
		return this.processLineName;
	}
	
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

	public void setVarPointsPackage(VarPointsPackage pkgVarPoints) {
		this.pkgVarPoints = pkgVarPoints;
		
	}
	
	public VarPointsPackage getVarPointsPackage() {
		return this.pkgVarPoints;
		
	}
}
