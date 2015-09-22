package org.eclipse.epf.library.edit.process;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.util.ProcessUtil;


public class vpRoleDescriptorItemProvider extends DescriptorItemProvider {

	/**
	 * @param adapterFactory
	 */
	public vpRoleDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	public Collection getEClasses() {
		return ProcessUtil.getOBSEclasses();
	}

}