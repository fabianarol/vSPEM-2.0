package org.eclipse.epf.library.edit.process;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.util.ProcessUtil;


public class VarRoleDescriptorItemProvider extends DescriptorItemProvider {

	/**
	 * @param adapterFactory
	 */
	public VarRoleDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	public Collection getEClasses() {
		return ProcessUtil.getOBSEclasses();
	}

}