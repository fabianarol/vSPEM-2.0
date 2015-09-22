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
package org.eclipse.epf.library.edit.process.publishing;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.process.consolidated.ItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * @author Phong Nguyen Le - Mar 22, 2006
 * @since  1.0
 */
public class CBSItemProviderAdapterFactory extends ItemProviderAdapterFactory {

	public CBSItemProviderAdapterFactory() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.consolidated.ItemProviderAdapterFactory#createActivityAdapter()
	 */
	public Adapter createActivityAdapter() {
		return new CBSActivityItemProvider(this);
	}
	
	public Adapter createvpActivityAdapter() {
		//return createActivityAdapter();
		return new CBSProcessItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpActivityAdapter());
	}
	
	public Adapter createVarActivityAdapter() {
		//return createActivityAdapter();
		return new CBSProcessItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarActivityAdapter());
	}
	
	
	
	public Adapter createCapabilityPatternAdapter() {
		return new CBSProcessItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createCapabilityPatternAdapter());
	}

	public Adapter createDeliveryProcessAdapter() {
		return new CBSProcessItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createDeliveryProcessAdapter());
	}

	public Adapter createTaskDescriptorAdapter() {
		return new CBSTaskDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createTaskDescriptorAdapter());
	}
	//VarTask
	public Adapter createVarTaskDescriptorAdapter() {
		return new CBSTaskDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarTaskDescriptorAdapter());
	}
	//VpTask
	public Adapter createvpTaskDescriptorAdapter() {
		return new CBSTaskDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpTaskDescriptorAdapter());
	}

	public Adapter createRoleDescriptorAdapter() {
		return new CBSRoleDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createRoleDescriptorAdapter());
	}
	//VarRole
	public Adapter createVarRoleDescriptorAdapter() {
		return new CBSRoleDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarRoleDescriptorAdapter());
	}
	//VpRole
	public Adapter createvpRoleDescriptorAdapter() {
		return new CBSRoleDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpRoleDescriptorAdapter());
	}

	public Adapter createWorkProductDescriptorAdapter() {
		return new CBSWorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createWorkProductDescriptorAdapter());
	}
	
	public Adapter createVarWorkProductDescriptorAdapter() {
		return new CBSWorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createVarWorkProductDescriptorAdapter());
	}
	
	public Adapter createvpWorkProductDescriptorAdapter() {
		return new CBSWorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createvpWorkProductDescriptorAdapter());
	}
}
