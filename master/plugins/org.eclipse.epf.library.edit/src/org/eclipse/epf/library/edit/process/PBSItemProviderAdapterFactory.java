//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.process;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class PBSItemProviderAdapterFactory extends ItemProviderAdapterFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createActivityAdapter()
	 */
	public Adapter createActivityAdapter() {
		return new PBSActivityItemProvider(this);
	}

	public Adapter createvpActivityAdapter() {
		return createActivityAdapter();
	}
	
	public Adapter createVarActivityAdapter() {
		return createActivityAdapter();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createPhaseAdapter()
	 */
	public Adapter createMilestoneAdapter() {
		return new PBSMilestoneItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createMilestoneAdapter());
		

	}
	
	public Adapter createVarMilestoneAdapter() {
		return new PBSMilestoneItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createVarMilestoneAdapter());
		
//		return createMilestoneAdapter();
	}
	
	public Adapter createvpMilestoneAdapter() {
		return new PBSMilestoneItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createvpMilestoneAdapter());
		
//		return createMilestoneAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createIterationAdapter()
	 */
	public Adapter createIterationAdapter() {
		return createActivityAdapter();
	}
	
	public Adapter createvpIterationAdapter() {
		return createActivityAdapter();
	}
	
	public Adapter createVarIterationAdapter() {
		return createActivityAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createPhaseAdapter()
	 */
	public Adapter createPhaseAdapter() {
		return createActivityAdapter();
	}
	
	public Adapter createvpPhaseAdapter() {
		return createActivityAdapter();
	}
	
	public Adapter createVarPhaseAdapter() {
		return createActivityAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.ItemProviderAdapterFactory#createProcessAdapter()
	 */
	public Adapter createProcessAdapter() {
		return createActivityAdapter();
	}

	public Adapter createCapabilityPatternAdapter() {
		return new PBSProcessItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createCapabilityPatternAdapter());
	}

	public Adapter createDeliveryProcessAdapter() {
		return new PBSProcessItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createDeliveryProcessAdapter());
	}

}
