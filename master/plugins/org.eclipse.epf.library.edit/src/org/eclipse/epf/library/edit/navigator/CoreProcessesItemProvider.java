package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarPointsPackage;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.util.UmaUtil;

public class CoreProcessesItemProvider extends AbstractCoreProcessesItemProvider{

	private static final String PROCESS_CONTRIBUTIONS_LABEL = LibraryEditPlugin.INSTANCE
			.getString("_UI_ProcessContributions_text"); //$NON-NLS-1$

	private MethodPlugin plugin;
	private String processLineName;
	
	private CoreProcessPackage processContributionsPackage;
	private CoreProcessPackage capabilityPatternPackage;
	private CoreProcessPackage deliveryProcessPackage;
	private VarPointsPackage varPointsPackage;
	
	private Object parent;
	
	public CoreProcessesItemProvider(AdapterFactory adapterFactory, MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.plugin = plugin;
	}	
	
	//Recupera el label para el Core Process
	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_CoreProcess_type");
	}

	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
			/*De momento ira vacio aqui habra que añadir que se creen Variation Points*/

	}
	
	public Collection getChildren(Object object) {
		
		if (children == null) {
			
			children = new ArrayList();
			/**CAPABILITY PATTERNS**/
			//Nos generamos el objeto para los capability Patterns
			org.eclipse.epf.uma.CoreProcessPackage pkg = (org.eclipse.epf.uma.CoreProcessPackage) UmaUtil.createCoreProcessPackage(plugin, modelStruct.variabilityCapabilityPatternPath, getProcessLineOwner());
			capabilityPatternPackage = pkg;
			
			if (pkg != null) {
				CoreProcessPackageItemProvider adapter = (CoreProcessPackageItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(pkg,
								ITreeItemContentProvider.class);
				adapter.setProcessType(UmaPackage.eINSTANCE
						.getCapabilityPattern());
				adapter.setLabel(LibraryEditPlugin.INSTANCE
						.getString("_UI_CapabilityPatterns_text"));
				adapter.setParent(this);
				adapter.setProcessLineOwner(getProcessLineOwner());
				children.add(pkg);
			}
			/**DELIVERY PROCESS**/
			//Nos generamos el objeto para los delivery process
			pkg = (org.eclipse.epf.uma.CoreProcessPackage) UmaUtil.createCoreProcessPackage(plugin, modelStruct.variabilityDeliveryProcessPath, getProcessLineOwner());
			deliveryProcessPackage = pkg;
			

			if (pkg != null) {
				CoreProcessPackageItemProvider adapter = (CoreProcessPackageItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(pkg,
								ITreeItemContentProvider.class);
				adapter.setProcessType(UmaPackage.eINSTANCE
						.getDeliveryProcess());
				adapter.setLabel(LibraryEditPlugin.INSTANCE
						.getString("_UI_DeliveryProcesses_text")); //$NON-NLS-1$
				adapter.setParent(this);
				adapter.setProcessLineOwner(getProcessLineOwner());
				children.add(pkg);
			}
			
			/**VARIATION POINTS**/
			VarPointsPackage pkgVarPoints = (VarPointsPackage) UmaUtil.createVarPointsPackage(plugin, modelStruct.varPointsPath, getProcessLineOwner());
			varPointsPackage = pkgVarPoints;
			
			if(varPointsPackage!=null){
				VarPointsPackageItemProvider adapter = (VarPointsPackageItemProvider) TngUtil
				.getBestAdapterFactory(adapterFactory).adapt(pkgVarPoints,
						ITreeItemContentProvider.class);
				adapter.setParent(this);
				adapter.setPlugin(plugin);
				adapter.setVarPointsPackage(pkgVarPoints);
				adapter.setModelStructure(modelStruct);
				adapter.setProcessLineOwner(getProcessLineOwner());
				children.add(pkgVarPoints);
			}
			
			if (processContributionEnabled) {
				children.add(getCoreProcessContributionsPackage());
			}

		} else {
			if (processContributionEnabled) {
				CoreProcessPackage pkg = getCoreProcessContributionsPackage();
				if (!children.contains(pkg)) {
					children.add(pkg);
				}
			} else {
				CoreProcessPackage pkg = getCoreProcessContributionsPackage();
				children.remove(pkg);
			}
		}

		return children;
	}

	private CoreProcessPackage getCoreProcessContributionsPackage() {
		if (processContributionsPackage == null) {
			processContributionsPackage = UmaFactory.eINSTANCE.createCoreProcessPackage();
			
			//Necesita del Plugin... Para desactivar las notificaciones...
			if (processContributionsPackage == null) {
//				processContributionsPackage = ModelStructure
//						.createCoreProcessContributionPackage(plugin);
//				processContributionsPackage = UmaFactory.eINSTANCE.createCore;
			}
			CoreProcessPackageItemProvider adapter = (CoreProcessPackageItemProvider) getRootAdapterFactory()
					.adapt(processContributionsPackage,
							ITreeItemContentProvider.class);
			adapter.setLabel(PROCESS_CONTRIBUTIONS_LABEL);
			adapter.setParent(this);
		}
		return processContributionsPackage;
	}

	public Object getParent(Object object) {
		if (parent != null){
			return this.parent;
		}
		else{
			return object;
		}
	}
	
	public void setParent(Object object){
		this.parent = object;
	}
	
	public void setProcessLineOwner(String name){
		this.processLineName = name;
	}
	
	public String getProcessLineOwner(){
		return this.processLineName;
	}

	public CoreProcessPackage getCapabilityPatternPackage() {
		return capabilityPatternPackage;
	}
	
	public CoreProcessPackage getDeliveryProcessPackage() {
		return deliveryProcessPackage;
	}
}
