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
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;
import org.eclipse.epf.uma.impl.ProcessLinesPackageImpl;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
* Hay que implementar herencia multiple para heredar de ProcessLineComponentItemprovider y StructuredMethodPlugin
*/
public class ProcessLineComponentItemProvider extends
		org.eclipse.epf.uma.provider.ProcessLineComponentItemProvider {

//public class ProcessLineComponentItemProvider extends StructuredMethodPluginItemProvider 
//				implements IProcessLineComponentItemProvider{

	private MethodPlugin plugin;
	private ModelStructure modelStruct;
	private CoreProcessPackage coreProcess;
	
	private Object parent;
	
	private String processLineName;
	
	protected Collection children;
	
	protected Map groupItemProviderMap;
	
	protected Collection lineProcesses  = new ArrayList();//
	
	protected Collection<Collection> childrens = new ArrayList();
	
	public ProcessLineComponentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		
	}
	
	public Collection getChildren(Object object) {
		
		if(!lineProcesses.contains(object)){
			children = new ArrayList();//Nueva lista
			if(children.isEmpty()){		
					groupItemProviderMap = new HashMap();
					
					/****Seteamos valores necesarios****/
						/*Linea de Proceso*/
						ProcessLineComponent auxLine = (ProcessLineComponent) object;
						this.processLineName = auxLine.getName();
	
						/*ModelStructure*/
						ModelStructure aux = new ModelStructure();
						modelStruct = aux.DEFAULT;//extraigo el modelStructure por defecto definido
						
						/*Parent*/
						parent = this.getParent(object);

						
						/*Plugin*/
//						ProcessLinesPackageImpl auxPackage = (ProcessLinesPackageImpl) parent;
						plugin = (MethodPlugin) UmaUtil.getMethodPluginFromProcessLine((EObject) object);
						/****______****/
						
						
					/****Construimos el esqueleto de la linea de proceso****/
						//CoreProcess - Method Package
						//
						String name = LibraryEditPlugin.INSTANCE.getString("_UI_CoreProcess_type");
						CoreProcessesItemProvider childCore = new CoreProcessesItemProvider(adapterFactory, plugin, getModelStructure());
						childCore.setParent(this);
						childCore.setProcessLineOwner(processLineName);
						groupItemProviderMap.put(name, childCore);
						children.add(childCore);
						
						//Variants - Method Package -> ModelStorage
						//
						org.eclipse.epf.uma.VariantsPackage pkg = (org.eclipse.epf.uma.VariantsPackage) UmaUtil.createVariantsPackage(plugin, modelStruct.variantsPath, getProcessLineOwner());
						if (pkg != null) {
							VariantsPackageItemProvider adapter = (VariantsPackageItemProvider) TngUtil
									.getBestAdapterFactory(adapterFactory).adapt(pkg,
											ITreeItemContentProvider.class);
							adapter.setParent(this);
							adapter.setVariantPackage(pkg);
							adapter.setPlugin(plugin);
							adapter.setModelStructure(modelStruct);
							adapter.setProcessLineOwner(getProcessLineOwner());
							children.add(pkg);
						}		
						
						//Tailored P - Method Package  -> ModelStorage
						//
						org.eclipse.epf.uma.TailoredProcessesPackage pkgTailored = (org.eclipse.epf.uma.TailoredProcessesPackage) UmaUtil.createTailoredProcessPackage(plugin, modelStruct.tailoredPackagePath, getProcessLineOwner());
						if(pkgTailored != null){
							TailoredProcessesPackageItemProvider adapter = (TailoredProcessesPackageItemProvider) TngUtil
							.getBestAdapterFactory(adapterFactory).adapt(pkgTailored,
									ITreeItemContentProvider.class);
							adapter.setParent(this);
							adapter.setProcessType(UmaPackage.eINSTANCE
						.getTailoredProcess());
							adapter.setPlugin(plugin);
							adapter.setModelStructure(modelStruct);
							adapter.setProcessLineOwner(getProcessLineOwner());
							children.add(pkgTailored);
						}
						/****______****/
			}
			lineProcesses.add(object);
			childrens.add(children);
		}
		//Si el objeto llama ya genero su estructura entonces que devuelva sus hijos de la lista de lineas de procesos
		else{
			Boolean foundLine = false;
			int i=0;
			for(Iterator iter = lineProcesses.iterator(); iter.hasNext() && !foundLine;){
				Object lineProcess = iter.next();
				i++;
				if(lineProcess.equals(object)){
					foundLine = true;
				}
			}
			//Si el resultado fue encontrado, devolvemos los hijos de la posicion adecuada de la lista de hijos
			if (foundLine){
				Boolean foundChildren = false;
				int j = 0;
				for(Iterator iter = childrens.iterator(); iter.hasNext() && !foundChildren;){
					Object childs = iter.next();
					j++;
					if(j == i){
						foundChildren = true;
						return (Collection) childs;
					}
				}
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
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/ProcessLine");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_ProcessLineComponent_type")); //$NON-NLS-1$
	}

	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ProcessLineComponent.class)) {
		case UmaPackage.PROCESS_LINE_COMPONENT__NAME:
			ProcessLineComponent pc = (ProcessLineComponent) notification.getNotifier();
			if (pc.getProcessLine() != null) {
				boolean old = pc.getProcessLine().eDeliver();
				try {
					pc.getProcessLine().eSetDeliver(false);
					pc.getProcessLine().setName(pc.getName());
				} finally {
					pc.getProcessLine().eSetDeliver(old);
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
	
	public String getProcessLineOwner(){
		return this.processLineName;
	}
	
	public Object getParent(Object object) {
//		return auxParent;//De aquí podemos obtener el MethodPlugin y el padre
		if (parent != null)
			return parent;
		return super.getParent(object);
	}
	
	public void setParent(Object parent){
		this.parent = parent;
	}
	
}
