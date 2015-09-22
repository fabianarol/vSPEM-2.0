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
package org.eclipse.epf.library.edit.process.consolidated;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.process.TeamProfileItemProvider;
import org.eclipse.epf.library.edit.process.VarTeamProfileItemProvider;
import org.eclipse.epf.library.edit.process.WBSItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.process.vpTeamProfileItemProvider;
import org.eclipse.epf.library.edit.process.publishing.WPBSActivityItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;


/**
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ItemProviderAdapterFactory extends WBSItemProviderAdapterFactory {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.ItemProviderAdapterFactory#createTaskDescriptorAdapter()
	 */
	public Adapter createTaskDescriptorAdapter() {
		return new TaskDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createTaskDescriptorAdapter());
	}
	
	public Adapter createVarTaskDescriptorAdapter() {
//		return createTaskDescriptorAdapter();
		return new VarTaskDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarTaskDescriptorAdapter());
	}
	
	public Adapter createvpTaskDescriptorAdapter() {
//		return createTaskDescriptorAdapter();
		return new vpTaskDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpTaskDescriptorAdapter());
	}

	public Adapter createRoleDescriptorAdapter() {
		return new RoleDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createRoleDescriptorAdapter());
	}
	
	public Adapter createVarRoleDescriptorAdapter() {
//		return createRoleDescriptorAdapter();
		return new VarRoleDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarRoleDescriptorAdapter());
	}
	
	public Adapter createvpRoleDescriptorAdapter() {
//		return createRoleDescriptorAdapter();
		return new vpRoleDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpRoleDescriptorAdapter());
	}

	public Adapter createWorkProductDescriptorAdapter() {
		return new WorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createWorkProductDescriptorAdapter());
	}
	
	public Adapter createVarWorkProductDescriptorAdapter() {
		return new VarWorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarWorkProductDescriptorAdapter());
	}
	
	public Adapter createvpWorkProductDescriptorAdapter() {
		return new vpWorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpWorkProductDescriptorAdapter());
	}

	public Adapter createActivityAdapter() {
		return new ActivityItemProvider(this);
	}
	
	public Adapter createvpActivityAdapter() {
		return createActivityAdapter();
	}
	
	public Adapter createVarActivityAdapter() {
		return createActivityAdapter();
	}

	public Adapter createCapabilityPatternAdapter() {
		return new ProcessItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createCapabilityPatternAdapter());
	}

	public Adapter createDeliveryProcessAdapter() {
		return new ProcessItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createDeliveryProcessAdapter());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createTeamProfileAdapter()
	 */
	public Adapter createTeamProfileAdapter() {
		return new TeamProfileItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory.createTeamProfileAdapter());
	}
	
	public Adapter createVarTeamProfileAdapter() {
		return new VarTeamProfileItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory.createVarTeamProfileAdapter());
	}
	
	public Adapter createvpTeamProfileAdapter() {
		return new vpTeamProfileItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory.createvpTeamProfileAdapter());
	}
}
