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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.vpActivity;
import org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ItemProviderAdapterFactory extends UmaItemProviderAdapterFactory
		implements IColumnAware {

	private Map columnIndexToNameMap;

	/**
	 * References to stateful adapters created by this adapter factory
	 */
	private Set disposableAdapters = new HashSet();

	/**
	 * 
	 */
	public ItemProviderAdapterFactory() {
		super();
		supportedTypes.add(ITableItemLabelProvider.class);
		// createdAdapters = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createActivityAdapter()
	 */
	public Adapter createActivityAdapter() {
		return new ActivityItemProvider(this);
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
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createBreakdownElementAdapter()
	 */
	public Adapter createBreakdownElementAdapter() {
		return new BreakdownElementItemProvider(this,
				(ItemProviderAdapter) super.createBreakdownElementAdapter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.DescriptorsAdapterFactory#createDescriptorAdapter()
	 */
	public Adapter createDescriptorAdapter() {
		return new DescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createDescriptorAdapter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.DescriptorsAdapterFactory#createRoleDescriptorAdapter()
	 */
	public Adapter createRoleDescriptorAdapter() {
		return new RoleDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createRoleDescriptorAdapter());
	}
	
	public Adapter createvpRoleDescriptorAdapter(){
		return new vpRoleDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createvpRoleDescriptorAdapter());
	}
	
	public Adapter createVarRoleDescriptorAdapter(){
		return new VarRoleDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createVarRoleDescriptorAdapter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.DescriptorsAdapterFactory#createTaskDescriptorAdapter()
	 */
	public Adapter createTaskDescriptorAdapter() {
		return new TaskDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createTaskDescriptorAdapter());
	}
	
	public Adapter createvpTaskDescriptorAdapter() {
		return new vpTaskDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createvpTaskDescriptorAdapter());
	}
	
	public Adapter createVarTaskDescriptorAdapter() {
		return new VarTaskDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createVarTaskDescriptorAdapter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.DescriptorsAdapterFactory#createWorkProductDescriptorAdapter()
	 */
	public Adapter createWorkProductDescriptorAdapter() {
		return new WorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createWorkProductDescriptorAdapter());
	}
	
	public Adapter createvpWorkProductDescriptorAdapter() {
		return new vpWorkProductDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createvpWorkProductDescriptorAdapter());
	}
	
	public Adapter createVarWorkProductDescriptorAdapter() {
		return new VarWorkProductDescriptorItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createVarWorkProductDescriptorAdapter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IColumnAware#setColumnIndexToNameMap(java.util.Map)
	 */
	public void setColumnIndexToNameMap(Map map) {
		if (map != null) {
			columnIndexToNameMap = map;
		}
	}

	public Object adapt(Object object, Object type) {
		Object adapter = super.adapt(object, type);
		if (adapter instanceof IColumnAware) {
			((IColumnAware) adapter)
					.setColumnIndexToNameMap(columnIndexToNameMap);
		}
		return adapter;
	}

	public Adapter adapt(Notifier notifier, Object type) {
		Adapter adapter = super.adapt(notifier, type);
		if (adapter instanceof IColumnAware) {
			((IColumnAware) adapter)
					.setColumnIndexToNameMap(columnIndexToNameMap);
		}
		if (adapter instanceof IStatefulItemProvider
				&& notifier instanceof EObject
				&& ((EObject) notifier).eContainer() != null) {
			disposableAdapters.add(adapter);
		}
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.ProcesstypesAdapterFactory#createProcessAdapter()
	 */
	// public Adapter createProcessAdapter() {
	// return new ProcessItemProvider(this);
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ProcesstypesItemProviderAdapterFactory#createDeliveryProcessAdapter()
	 */
	// public Adapter createDeliveryProcessAdapter() {
	// return createProcessAdapter();
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ProcesstypesItemProviderAdapterFactory#createCapabilityPatternAdapter()
	 */
	// public Adapter createCapabilityPatternAdapter() {
	// return createProcessAdapter();
	// }
	public Adapter createProcessComponentAdapter() {
		if (processComponentItemProvider == null) {
			processComponentItemProvider = new ProcessComponentItemProvider(
					this);
		}

		return processComponentItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createProcessPackageAdapter()
	 */
	public Adapter createProcessPackageAdapter() {
		if (processPackageItemProvider == null) {
			processPackageItemProvider = new ProcessPackageItemProvider(this);
		}

		return processPackageItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createCompositeRoleAdapter()
	 */
	public Adapter createCompositeRoleAdapter() {
		return new CompositeRoleItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createCompositeRoleAdapter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createRoleAdapter()
	 */
	public Adapter createRoleAdapter() {
		if (roleItemProvider == null) {
			roleItemProvider = new RoleItemProvider(this);
		}
		return roleItemProvider;
	}

	public Adapter createWorkOrderAdapter() {
		if (workOrderItemProvider == null) {
			workOrderItemProvider = new WorkOrderItemProvider(this);
		}
		return workOrderItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IColumnAware#getColumnIndexToNameMap()
	 */
	public Map getColumnIndexToNameMap() {
		return columnIndexToNameMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#dispose()
	 */
	public void dispose() {
		if (!disposableAdapters.isEmpty()) {
			for (Iterator iter = disposableAdapters.iterator(); iter.hasNext();) {
				IDisposable adapter = (IDisposable) iter.next();
				adapter.dispose();
			}
			disposableAdapters.clear();
		}

		super.dispose();
	}

}
