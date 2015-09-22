package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.command.MethodElementCreateChildCommand;
import org.eclipse.epf.library.edit.process.command.CreateProcessLineComponentCommand;
import org.eclipse.epf.library.edit.process.command.CreateTailoredProcessComponentCommand;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.TailoredProcessesPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

public class TailoredProcessesPackageItemProvider extends 
		org.eclipse.epf.uma.provider.TailoredProcessesPackageItemProvider implements IStatefulItemProvider{
	
	protected EClass processType;

	private String label;

	private Object parent;
	
	private MethodPlugin plugin; 
	
	private ModelStructure modelStruct;

	private String processLineName;

	public TailoredProcessesPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	public EClass getProcessType() {
		return processType;
	}

	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		
		/*Que solo deje crear tailoredProcessComponent*/
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
				.createTailoredProcessComponent()));
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
		Collection children = super.getChildren(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (!(child instanceof TailoredProcessComponent) 
					&& (child instanceof TailoredProcessesPackage)) {
				TailoredProcessesPackageItemProvider itemProvider = (TailoredProcessesPackageItemProvider) getRootAdapterFactory()
						.adapt(child, ITreeItemContentProvider.class);
				if (processType != null)
					itemProvider.setProcessType(processType);
				
			}
			
			
		}
		return children;
	}
	
	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Tailored_Process_Package_group");
	}
	
	//Objeto de la carpeta padre
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/TailoredProcessPackage");
	}
	
	//Texto de la carpeta hija (Proceso Adaptado)
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection selection) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Tailored_Process_Component");

	}
	
	//Imagen de la carpeta hija (Linea de proceso)
	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/TailoredProcess");
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setProcessType(EClass processType) {
		this.processType = processType;
	}
	
	//Metodo para crear un Tailored Process
	protected Command createCreateChildCommand(EditingDomain domain,
			EObject owner, EStructuralFeature feature, Object value, int index,
			Collection collection) {
		
		
		if (value instanceof TailoredProcessComponent) {
			return new CreateTailoredProcessComponentCommand(domain, owner, feature,
					value, index, collection, this, plugin, modelStruct);
		}

		return new MethodElementCreateChildCommand(domain, owner, feature,
				value, index, collection, this);
	}
	
	//Comando para la creacion de Tailored Process -> Se comprueba si hay almenos variantes
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof TailoredProcessesPackage) {
				VariantsPackage aux = (VariantsPackage) UmaUtil.findVariantsPackage((MethodPlugin) owner.eContainer(), modelStruct.variantsPath, getProcessLineOwner());
				//Si tenemos variantes entonces añadimos la opcion de crear procesos adaptados
				if (aux.getVariant() != null ){
					if(aux.getVariant().size() > 0){
						selection.add(element);
					}
				}
			}
		}
		if (selection.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}

		// return super.createAddCommand(domain, owner, feature, collection,
		// index);
		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, selection, index)) {
			
			protected void featureChanged(EObject object,
					EStructuralFeature feature, Object newValue) {
				
				if (object instanceof TailoredProcessComponent
						&& feature == UmaPackage.eINSTANCE
								.getNamedElement_Name()) {
					Process proc = ((TailoredProcessComponent) object).getTailoredProcess();//Devuelve un proceso
					if (proc != null) {
						proc.setName((String) newValue);
					}
				}
				
			}
		};
	}
	
	
	protected Command createInitializeCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementInitializeCopyCommand(domain, owner, helper);
	}

	protected Command createCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementCreateCopyCommand(domain, owner, helper);
	}
	
	public void setParent(Object parent) {
		this.parent = parent;
	}
	
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		return super.getParent(object);
	}
	
	public MethodPlugin getMethodPlugin(){
		return this.plugin;
	}
	
	public void setPlugin(MethodPlugin newPlugin){
		this.plugin = newPlugin;
	}
	public void setModelStructure(ModelStructure newModelStruct){
		this.modelStruct = newModelStruct;
	}
	
	public void setProcessLineOwner(String name){
		this.processLineName = name;
	}
	
	public String getProcessLineOwner(){
		return this.processLineName;
	}
	
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);

		super.notifyChanged(notification);
	}
}
