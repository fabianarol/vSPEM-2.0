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
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.Variation;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.vpActivity;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.swt.graphics.Image;

public class VariationItemProvider extends 
			org.eclipse.epf.uma.provider.VariationItemProvider implements
			IStatefulItemProvider{
	
	protected EClass varPointType;

	private String label;

	private CoreProcessesItemProvider parent;
	
	protected Collection children;
	
	private MethodPlugin plugin;

	private String processLineName;

	private ModelStructure modelStructure;
	
	
	public VariationItemProvider(AdapterFactory adapterFactory) {
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
		if(object instanceof Occupation){
			Occupation occupation = (Occupation) object;
			VarPoint varPoint = occupation.getEsocupado();//Punto de variacion
			Variant variant = occupation.getOcupadopor();//Variante
			
			children.add(varPoint);
			children.add(variant);
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
	 * Obtiene el nombre de la variante que ocupa la Ocupación
	 */
	public String getText(Object object) {
		
		String name = "";
		if(object instanceof Occupation){
			Occupation occupation = (Occupation) object;
			name = occupation.getOcupadopor().getVpName();
		}
		else{
			name = "Unknown";
		}
		return name;
	}
	/**
	 * Obtiene la imagen del tipo de variante (Elemento - SPEM) que contiene la ocupación
	 */
	public Object getImage(Object object) {
		

		
		if (object instanceof Occupation){
			Occupation occupation = (Occupation) object;
			VarPoint varPointTarget = occupation.getEsocupado();
			
			if(varPointTarget instanceof Phase){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/Phase");
			}
			else if(varPointTarget instanceof Iteration){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/Iteration");
			}
			else if(varPointTarget instanceof Activity){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/Activity");
			}
			else if(varPointTarget instanceof Milestone){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/Milestone");
			}
			else if(varPointTarget instanceof TaskDescriptor){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/TaskDescriptor");
			}
			else if(varPointTarget instanceof RoleDescriptor){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/RoleDescriptor");
			}
			else if(varPointTarget instanceof TeamProfile){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/TeamProfile");
			}
			else if(varPointTarget instanceof WorkProductDescriptor){
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/WorkProductDescriptor");
			}
			else{
				return LibraryEditPlugin.INSTANCE.getImage("full/spem16/Unknown");
			}
		}
		else{
			return LibraryEditPlugin.INSTANCE.getImage("full/spem16/Unknown");
		}
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
		
	 @Override
	public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null) return false;
	        if (getClass() != obj.getClass()) return false;
	        final Variation other = (Variation)obj;
	        
//	        if (other.get != other.age) return false;
//	        if (name == null) {
//	            if (other.name != null) return false;
//	        } else if (!name.equals(other.name)) return false;
//	        if (surname == null) {
//	            if (other.surname != null) return false;
//	        } else if (!surname.equals(other.surname)) return false;
	        return true;
	}

}

