/**
 * 
 */
package org.eclipse.epf.library.edit.breakdownelement;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;


public class VarMilestoneItemProvider extends
		org.eclipse.epf.uma.provider.VarMilestoneItemProvider {

	/**
	 * @param adapterFactory
	 */
	public VarMilestoneItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean hasChildren(Object object) {
		return false;
	}
	@Override
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_VarMilestone_type")); //$NON-NLS-1$
	}
	@Override
	public Collection getChildren(Object object) {
		return Collections.EMPTY_LIST;
	}
	
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		return;
	}
}
