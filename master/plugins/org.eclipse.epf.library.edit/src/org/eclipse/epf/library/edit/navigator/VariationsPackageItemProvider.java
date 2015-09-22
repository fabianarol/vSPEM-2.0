package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.common.command.Command;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.process.IVariantsItemProvider;

import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;

/**
 * The item provider adapter for a process package in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class VariationsPackageItemProvider extends
		org.eclipse.epf.uma.provider.VariationsPackageItemProvider implements
		IVariantsItemProvider, IStatefulItemProvider {

	protected EClass variantType;

	private TailoredProcessComponentItemProvider parent;
	
	protected Collection children;
	
	private MethodPlugin plugin;

	private String processLineName;
	
	private ModelStructure modelStructure;

	private String tailoredProcessName;



	/**
	 * Creates a new instance.
	 */
	public VariationsPackageItemProvider(AdapterFactory adapterFactory) {
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
		if(object instanceof VariationsPackage){
			VariationsPackage variationsPkg = (VariationsPackage) object;
			children = variationsPkg.getVariations();
		}
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
		return LibraryEditPlugin.INSTANCE.getString("_UI_Varitions_type");
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/variations");
	}

	public void setProcessType(EClass variantType) {
		this.variantType = variantType;
	}

	protected Command createInitializeCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementInitializeCopyCommand(domain, owner, helper);
	}

	protected Command createCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementCreateCopyCommand(domain, owner, helper);
	}

	public void setParent(TailoredProcessComponentItemProvider parent) {
		this.parent = parent;
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

	public void setTailoredProcessOwner(String name) {
		this.tailoredProcessName = name;
		
	}
	
	public String getTailoredProcessOwner() {
		return tailoredProcessName;
		
	}

}

