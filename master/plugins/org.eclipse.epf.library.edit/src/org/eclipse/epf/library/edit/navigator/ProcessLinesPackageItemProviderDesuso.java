package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

public class ProcessLinesPackageItemProviderDesuso extends AbstractProcessLinesItemProvider {


	private MethodPlugin plugin;

	
	private ProcessLinesPackage processLinesPackage;

	public ProcessLinesPackageItemProviderDesuso(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.plugin = plugin;
	}
	
	
	//*Método que llena ProcessL*//
	public Collection getChildren(Object object) {

		//Inicialmente esta vacio//
		
		//*Parte de los procesos*//		
		if (children == null) {
			children = new ArrayList();

			org.eclipse.epf.uma.ProcessLinesPackage pkgLines = (org.eclipse.epf.uma.ProcessLinesPackage) 
					UmaUtil.findMethodPackage(plugin, modelStruct.processLineElementPath);
			
			
			processLinesPackage = pkgLines;
			
			if (pkgLines != null) {
//				ProcessLinesPackageItemEditorProvider 
//				ProcessPackageItemProvider adapter = (ProcessPackageItemProvider) TngUtil
//				.getBestAdapterFactory(adapterFactory).adapt(pkg,
//						ITreeItemContentProvider.class);
//		adapter.setProcessType(UmaPackage.eINSTANCE
//				.getDeliveryProcess());
//		adapter.setLabel(LibraryEditPlugin.INSTANCE
//				.getString("_UI_DeliveryProcesses_text")); //$NON-NLS-1$
//		adapter.setParent(this);
//		children.add(pkg);
				
				children.add(pkgLines);
			}

		}

		return children;
		
		//*Parte de las lineas de procesos*//
//		return super.getChildren(object);
	}

//	private ProcessPackage getProcessContributionsPackage() {
//		if (processContributionsPackage == null) {
//			processContributionsPackage = (org.eclipse.epf.uma.ProcessPackage) UmaUtil
//					.findMethodPackage(plugin,
//							modelStruct.processContributionPath);
//			if (processContributionsPackage == null) {
//				processContributionsPackage = ModelStructure
//						.createProcessContributionPackage(plugin);
//			}
//			ProcessPackageItemProvider adapter = (ProcessPackageItemProvider) getRootAdapterFactory()
//					.adapt(processContributionsPackage,
//							ITreeItemContentProvider.class);
//			adapter.setLabel(PROCESS_CONTRIBUTIONS_LABEL);
//			adapter.setParent(this);
//		}
//		return processContributionsPackage;
//	}

	public Object getParent(Object object) {
		return plugin;
	}

//	public ProcessPackage getCapabilityPatternPackage() {
//		return capabilityPatternPackage;
//	}
//	
//	public ProcessPackage getDeliveryProcessPackage() {
//		return deliveryProcessPackage;
//	}
}
