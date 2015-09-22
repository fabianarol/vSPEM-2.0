package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.TailoredProcessesPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

public class TailoredProcessItemProvider extends AbstractTailoredProcessItemProvider{

	private MethodPlugin plugin;
	
	private TailoredProcessesPackage tailoredProcessesPackage;
	
	public TailoredProcessItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.plugin = plugin;
	}
	
	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();

		}
		else {
		}

		return children;
	}
	
	public Object getParent(Object object) {
		return plugin;
	}
	
	public TailoredProcessesPackage getTailoredProcessPackage(){
		return tailoredProcessesPackage;
	}
	
}
