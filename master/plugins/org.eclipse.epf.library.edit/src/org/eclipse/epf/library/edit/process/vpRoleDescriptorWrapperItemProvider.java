package org.eclipse.epf.library.edit.process;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.vpRoleDescriptor;


public class vpRoleDescriptorWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {
	
	protected vpRoleDescriptorWrapperItemProvider(Object value, Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public vpRoleDescriptorWrapperItemProvider(vpRoleDescriptor value,
			Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param index
	 * @param feature
	 * @param adapterFactory
	 */
	public vpRoleDescriptorWrapperItemProvider(vpRoleDescriptor value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}

	@Override
	public boolean isInherited() {
		if(getValue() instanceof ComposedBreakdownElementWrapperItemProvider) {
			return ((ComposedBreakdownElementWrapperItemProvider)getValue()).isInherited();
		}
		return super.isInherited();
	}
}

