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


public class ProcessLinesItemProvider extends AbstractProcessLinesItemProvider {

	private MethodPlugin plugin;
	
	private ProcessLinesPackage processLinesPackage;

	/**
	 * Creates a new instance.
	 */
	public ProcessLinesItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.plugin = plugin;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			//Buscamos en el arbol lógico el ProcessLinesPackage
			org.eclipse.epf.uma.ProcessLinesPackage pkg = (org.eclipse.epf.uma.ProcessLinesPackage) UmaUtil.findMethodPackage(plugin,modelStruct.processLineElementPath);

			
			//Si lo encontramos entonces buscamos el adapter para el
			if (pkg != null) {
				//Genera un error de Class not Found - Hay algun adaptador que no se genera
				ProcessLinesPackageItemProvider adapter = (ProcessLinesPackageItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(pkg,
								ITreeItemContentProvider.class);
			
				/*Seteamos valores para nuestro Proceso hibrido*/
				adapter.setProcessType(UmaPackage.eINSTANCE.getLineProcess());//Instancia LineProcess
				adapter.setLabel("Lineas de proceso"); //$NON-NLS-1$
				adapter.setParent(this);
				adapter.setMethodPlugin(plugin);
				adapter.setModelStructure(modelStruct);
				children.add(pkg);
			}
		} 
		else {
		}

		return children;
	}

	public Object getParent(Object object) {
		return plugin;
	}
	
	public ProcessLinesPackage getProcessLinesPackage() {
		return processLinesPackage;
	}
}
