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
import org.eclipse.epf.library.edit.process.IProcessLineItemProvider;
import org.eclipse.epf.library.edit.process.command.CreateProcessComponentCommand;
import org.eclipse.epf.library.edit.process.command.CreateProcessLineComponentCommand;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.provider.UmaEditPlugin;


public class ProcessLinesPackageItemProvider extends
		org.eclipse.epf.uma.provider.ProcessLinesPackageItemProvider implements
		IProcessLineItemProvider, IStatefulItemProvider {

	protected EClass processType;

	private String label;

	private Object parent;
	
	private MethodPlugin plugin; 
	
	private ModelStructure modelStruct;

	/**
	 * Creates a new instance.
	 */
	public ProcessLinesPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EClass getProcessType() {
		return processType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		
//		ProcessLineComponent aux = UmaFactory.eINSTANCE.createProcessLineComponent();
//	
		/*Que solo deje crear ProcessLineComponent . Esto se mostrará en la vista*/
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
				.createProcessLineComponent()));
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
			
//			//Added features
//			childrenFeatures.add(UmaPackage.eINSTANCE.getCoreProcessPackage_ProcessElements());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (!(child instanceof ProcessLineComponent) 
					&& (child instanceof ProcessLinesPackage)) {
				ProcessLinesPackageItemProvider itemProvider = (ProcessLinesPackageItemProvider) getRootAdapterFactory()
						.adapt(child, ITreeItemContentProvider.class);
				if (processType != null)
					itemProvider.setProcessType(processType);
				
			}
			
			
		}

//		Collections.sort((List) children, PresentationContext.INSTANCE
//				.getProcessPackageComparator());

		return children;
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Process_Lines_Package_group");
	}


	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/ProcessLinesPackage");
	}
	
	//Texto de la carpeta hija (Linea de proceso)
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection selection) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Process_Line_Component");

	}

	//Imagen de la carpeta hija (Linea de proceso)
	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/ProcessLine");
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setProcessType(EClass processType) {
		this.processType = processType;
	}
	//Comando para la creacion de líneas de procesos
	protected Command createCreateChildCommand(EditingDomain domain,
			EObject owner, EStructuralFeature feature, Object value, int index,
			Collection collection) {
		
		//Adaptamos a la creación a las lineas de procesos
		if (value instanceof ProcessLineComponent) {
//			return new CreateProcessComponentCommand(domain, owner, feature,
//					value, index, collection, this);
			return new CreateProcessLineComponentCommand(domain, owner, feature,
			value, index, collection, this, plugin, modelStruct);


		}
		// return super.createCreateChildCommand(domain, owner, feature, value,
		// index, collection);
		return new MethodElementCreateChildCommand(domain, owner, feature,
				value, index, collection, this);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createAddCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection, int)
	 */
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof ProcessLinesPackage) {
				// check to exclude any pasted-in package that contains
				// process of different type than that of this package
				//
//				if (!ProcessUtil.hasProcessNotOfType((ProcessLinesPackage) element,
//						processType)) {
					selection.add(element);
//				}
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
				
				if (object instanceof ProcessLineComponent
						&& feature == UmaPackage.eINSTANCE
								.getNamedElement_Name()) {
					Process proc = ((ProcessLineComponent) object).getProcessLine();//Devuelve un proceso
					if (proc != null) {
						proc.setName((String) newValue);
						//*Setemos valores nuevos*//
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
	
	public MethodPlugin getMethodPlugin(){
		return this.plugin;
	}
	public ModelStructure getModelStructure(){
		return this.modelStruct;
	}
	public void setMethodPlugin(MethodPlugin newPlugin){
		this.plugin = newPlugin;
	}
	public void setModelStructure(ModelStructure newModelStruct){
		this.modelStruct = newModelStruct;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ProcessPackageItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);

		super.notifyChanged(notification);
	}
}
