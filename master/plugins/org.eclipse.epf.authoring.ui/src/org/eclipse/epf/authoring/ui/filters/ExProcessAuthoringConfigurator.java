/**
 * 
 */
package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.Viewer;

/**
 * Configuration filter that still shows certain elements during process authoring that
 * ProcessConfigurator does not show. Filter elements based on search pattern and type.
 * @author skannoor
 *
 */
public class ExProcessAuthoringConfigurator extends
		ProcessAuthoringConfigurator {

	String filterStr;

	MethodConfiguration methodConfiguration;

	protected FilterHelper helper;
	/**
	 * @param methodConfig
	 * @param viewer
	 */
	public ExProcessAuthoringConfigurator(MethodConfiguration methodConfig,
			Viewer viewer) {
		super(methodConfig, viewer);
		//this.filterStr = filterStr;
		this.methodConfiguration = methodConfig;
		// TODO Auto-generated constructor stub
	}

	public boolean accept(Object obj) {

		if(obj == null) return false;
		
		if (obj instanceof MethodConfiguration) {
			return methodConfiguration.equals(obj);
		}

//		if (!super.accept(obj))
//			return false;
		if (methodConfig == null)
			return true;

		obj = LibraryUtil.unwrap(obj);

//		if (obj instanceof MethodPackage) {
//			return methodConfig.getMethodPackageSelection().contains(obj);
//		} else if (obj instanceof ItemProviderAdapter) {
//			return true;
//		} else {
//			if(Log.DEBUG) {
//				System.out.println("Object filtered: " + (obj == null ? null : obj.toString())); //$NON-NLS-1$
//			}
//		}
		if (helper.isShowPresentationName()) {
			if (!helper.matchPatternOnPresentationName(obj))
				return false;
		} else {
			if (!helper.matchPattern(obj))
				return false;
		}
		if (!helper.isObjectInSelectedItems(obj))
			return false;
		if (childAccept(obj))
			return true;
		return false;

	}

	public boolean childAccept(Object obj) {
		return false;
	}

	public void setHelper(FilterHelper helper) {
		this.helper = helper;
	}
	
	public void setMethodConfiguration(MethodConfiguration config)
	{
		super.setMethodConfiguration(config);
		this.methodConfiguration = config;
	}
}
