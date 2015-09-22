package org.eclipse.epf.library.edit.process;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.WorkProductDescriptor;


public class vpWorkProductDescriptorWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {
	
	protected vpWorkProductDescriptorWrapperItemProvider(Object value, Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public vpWorkProductDescriptorWrapperItemProvider(WorkProductDescriptor value,
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
	public vpWorkProductDescriptorWrapperItemProvider(
			WorkProductDescriptor value, Object owner,
			EStructuralFeature feature, int index, AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}

}


