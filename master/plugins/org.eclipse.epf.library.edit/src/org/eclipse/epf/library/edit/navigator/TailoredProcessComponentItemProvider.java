package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStructuredMethodPluginItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;

import org.eclipse.epf.library.edit.IStatefulItemProvider;

import org.eclipse.epf.library.edit.StructuredMethodPluginItemProvider;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.category.VariantsItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CoreProcess;
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.LineProcess;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.impl.ProcessLinesPackageImpl;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
* Hay que implementar herencia multiple para heredar de ProcessLineComponentItemprovider y StructuredMethodPlugin
*/
public class TailoredProcessComponentItemProvider extends
		org.eclipse.epf.uma.provider.TailoredProcessComponentItemProvider {

//public class TailoredProcessComponentItemProvider extends StructuredMethodPluginItemProvider 
//				implements ITailoredProcessComponentItemProvider{

	private MethodPlugin plugin;
	private ModelStructure modelStruct;
	private CoreProcessPackage coreProcess;
	private String processLineName;
	
	private TailoredCoreProcessPackage processContributionsPackage;
	private TailoredCoreProcessPackage capabilityPatternPackage;
	private TailoredCoreProcessPackage deliveryProcessPackage;
	private VariationsPackage variationsPackage;
	
	private Object parent;
	
	private String TailoredProcessName;
	
	protected Collection children;
	
	protected Map groupItemProviderMap;
	
	protected Collection lineProcesses  = new ArrayList();//
	
	protected Collection<Collection> childrens = new ArrayList();
	
	
	public TailoredProcessComponentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		
	}
	
	public Collection getChildren(Object object) {
		
		children = new ArrayList();//Nueva lista
		if(children.isEmpty()){
			
			
			//Obtengo datos
			ModelStructure aux = new ModelStructure();
			modelStruct = aux.DEFAULT;//extraigo el modelStructure por defecto definido
			parent = this.getParent(object);
			plugin = (MethodPlugin) UmaUtil.getMethodPluginFromProcessLine((EObject) object);
			
			
			if(UmaUtil.getProcessLine((EObject) object).getName() != null){
				setProcessLineOwner(UmaUtil.getProcessLine((EObject) object).getName());
			}
			
			
			/**CAPABILITY PATTERNS**/
			//Nos generamos el objeto para los capability Patterns
			org.eclipse.epf.uma.TailoredCoreProcessPackage pkg = (org.eclipse.epf.uma.TailoredCoreProcessPackage) UmaUtil.createTailoredCoreProcessPackage((TailoredProcessComponent) object, modelStruct.tailoredCapabilityPatternPath);
			capabilityPatternPackage = pkg;
			
			if(pkg != null){
				TailoredCoreProcessPackageItemProvider adapter = (TailoredCoreProcessPackageItemProvider) TngUtil
				.getBestAdapterFactory(adapterFactory).adapt(pkg,
						ITreeItemContentProvider.class);
				adapter.setProcessType(UmaPackage.eINSTANCE
						.getCapabilityPattern());
				adapter.setLabel(LibraryEditPlugin.INSTANCE
						.getString("_UI_CapabilityPatterns_text"));
				adapter.setParent(this);
				adapter.setProcessLineOwner(getProcessLineOwner());
				if(((TailoredProcessComponent) object).getName() != null){
					adapter.setTailoredProcessOwner(((TailoredProcessComponent) object).getName());
				}
				children.add(pkg);
			}
			/**DELIVERY PROCESS**/
			//Nos generamos el objeto para los delivery process
			pkg = (org.eclipse.epf.uma.TailoredCoreProcessPackage) UmaUtil.createTailoredCoreProcessPackage((TailoredProcessComponent) object, modelStruct.tailoredDeliveryProcessPath);
			deliveryProcessPackage = pkg;
			
			if(pkg != null){
				TailoredCoreProcessPackageItemProvider adapter = (TailoredCoreProcessPackageItemProvider) TngUtil
				.getBestAdapterFactory(adapterFactory).adapt(pkg,
						ITreeItemContentProvider.class);
				adapter.setProcessType(UmaPackage.eINSTANCE
						.getDeliveryProcess());
				adapter.setLabel(LibraryEditPlugin.INSTANCE
						.getString("_UI_DeliveryProcesses_text"));
				adapter.setParent(this);
				adapter.setProcessLineOwner(getProcessLineOwner());
				if(((TailoredProcessComponent) object).getName() != null){
					adapter.setTailoredProcessOwner(((TailoredProcessComponent) object).getName());
				}
				children.add(pkg);
			}
			/**VARIATIONS**/
			//Nos generamos el objeto para los Variations
			org.eclipse.epf.uma.VariationsPackage pkgVariations = (org.eclipse.epf.uma.VariationsPackage) UmaUtil.createVariationsPackage((TailoredProcessComponent) object, modelStruct.variationsPath);
			variationsPackage = pkgVariations;
			
			if(pkgVariations != null){
				VariationsPackageItemProvider adapter = (VariationsPackageItemProvider) TngUtil
				.getBestAdapterFactory(adapterFactory).adapt(pkgVariations,
						ITreeItemContentProvider.class);
				adapter.setParent(this);
				adapter.setProcessLineOwner(getProcessLineOwner());
				if(((TailoredProcessComponent) object).getName() != null){
					adapter.setTailoredProcessOwner(((TailoredProcessComponent) object).getName());
				}
				children.add(pkgVariations);
			}
			
		}
		
		
		
		
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		/*No hay acciones de momento*/
	}

	public Collection getChildrenFeatures(Object object) {

		return Collections.EMPTY_LIST;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/TailoredProcess");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_TailoredProcessComponent_type")); //$NON-NLS-1$
	}

	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TailoredProcessComponent.class)) {
		case UmaPackage.TAILORED_PROCESS_COMPONENT__NAME:
			TailoredProcessComponent pc = (TailoredProcessComponent) notification.getNotifier();
			if (pc.getTailoredProcess() != null) {
				boolean old = pc.getTailoredProcess().eDeliver();
				try {
					pc.getTailoredProcess().eSetDeliver(false);
					pc.getTailoredProcess().setName(pc.getName());
				} finally {
					pc.getTailoredProcess().eSetDeliver(old);
				}
			}
			break;
		}

		TngUtil.refreshParentIfNameChanged(notification, this);

		super.notifyChanged(notification);
	}

	protected Command createInitializeCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementInitializeCopyCommand(domain, owner, helper);
	}

	protected Command createCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementCreateCopyCommand(domain, owner, helper);
	}
	
	public void setPlugin(MethodPlugin newPlugin){
		this.plugin = newPlugin;
	}

	public void setModelStructure(ModelStructure newModelStruct){
		this.modelStruct = newModelStruct;
	}
	
	public ModelStructure getModelStructure(){
		return this.modelStruct;
	}
	
	public String getTailoredProcessOwner(){
		return this.TailoredProcessName;
	}
	
	public Object getParent(Object object) {
//		return auxParent;//De aquí podemos obtener el MethodPlugin y el padre
		if (parent != null)
			return parent;
		return super.getParent(object);
	}
	

	private String getProcessLineOwner() {
		return this.processLineName;
	}

	private void setProcessLineOwner(String newProcessLineName){
		this.processLineName = newProcessLineName;
	}
	
	public void setParent(Object parent){
		this.parent = parent;
	}
	
}
