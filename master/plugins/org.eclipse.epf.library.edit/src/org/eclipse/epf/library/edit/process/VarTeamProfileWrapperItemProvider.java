package org.eclipse.epf.library.edit.process;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VarTeamProfile;


public class VarTeamProfileWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {

	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public VarTeamProfileWrapperItemProvider(TeamProfile value, Object owner,
			AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param index
	 * @param feature
	 * @param adapterFactory
	 */
	public VarTeamProfileWrapperItemProvider(TeamProfile value, Object owner,
			EStructuralFeature feature, int index, AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementWrapperItemProvider#getAttribute(java.lang.Object,
	 *      java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		if (property == IBSItemProvider.COL_TEAMS) {
			return TngUtil.getPresentationName(((TeamProfile) TngUtil
					.unwrap(object)).getSuperTeam());
		}
		return super.getAttribute(object, property);
	}

}

