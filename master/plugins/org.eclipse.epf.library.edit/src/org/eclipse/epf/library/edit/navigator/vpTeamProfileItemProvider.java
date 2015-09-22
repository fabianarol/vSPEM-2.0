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
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.process.IVarPointsItemProvider;
import org.eclipse.epf.library.edit.process.IVariantsItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.vpTeamProfile;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.util.UmaUtil;

public class vpTeamProfileItemProvider extends 
			org.eclipse.epf.uma.provider.VarPointItemProvider implements
			IVarPointsItemProvider, IStatefulItemProvider{
	
	protected EClass varPointType;

	private String label;

	private CoreProcessesItemProvider parent;
	
	protected Collection children;
	
	private MethodPlugin plugin;

	private String processLineName;

	private ModelStructure modelStructure;
	
	
	public vpTeamProfileItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EClass getProcessType() {
		return varPointType;
	}

	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		
	}
	
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodPackage_ChildPackages());
		}
		return childrenFeatures;
	}
	

	public Collection getChildren(Object object) {

		children = new ArrayList();
		return children;
	}
	
	/**
	 * Añade el plugin
	 */
	public void setPlugin(MethodPlugin plugin){
		this.plugin = plugin;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		vpTeamProfile auxActivity = (vpTeamProfile) object;
		return auxActivity.getName();
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/vpteamprofile");
	}
//	
//	public void setLabel(String label) {
//		this.label = label;
//	}
//
//	public void setProcessType(EClass varPointType) {
//		this.varPointType = varPointType;
//	}
	
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
}


