package org.eclipse.epf.library.edit.process;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.TaskDescriptor;


public class VarTaskDescriptorWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {	

	protected VarTaskDescriptorWrapperItemProvider(Object value, Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param index
	 * @param feature
	 * @param adapterFactory
	 */
	public VarTaskDescriptorWrapperItemProvider(TaskDescriptor value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}

	@Override
	public String getAttribute(Object object, String property) {
		if(getValue() instanceof ComposedBreakdownElementWrapperItemProvider) {
			return ((ComposedBreakdownElementWrapperItemProvider)getValue()).getAttribute(object, property);
		}
		return super.getAttribute(object, property);
	}
	
	@Override
	public boolean isInherited() {
		if(getValue() instanceof ComposedBreakdownElementWrapperItemProvider) {
			return ((ComposedBreakdownElementWrapperItemProvider)getValue()).isInherited();
		}
		return super.isInherited();
	}
}

