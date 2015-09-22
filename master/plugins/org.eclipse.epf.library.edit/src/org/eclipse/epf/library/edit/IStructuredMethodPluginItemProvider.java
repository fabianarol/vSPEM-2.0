package org.eclipse.epf.library.edit;

import org.eclipse.epf.library.edit.util.ModelStructure;

public interface IStructuredMethodPluginItemProvider {
	
	public ModelStructure getModelStructure();

	public Object getGroupItemProvider(String name);

}
