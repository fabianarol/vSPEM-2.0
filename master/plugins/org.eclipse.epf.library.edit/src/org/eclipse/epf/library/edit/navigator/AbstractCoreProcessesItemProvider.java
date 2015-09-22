package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

public abstract class AbstractCoreProcessesItemProvider extends ItemProviderAdapter
					implements IEditingDomainItemProvider, IStructuredItemContentProvider,
					ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
					IElementItemProvider, IGroupContainer {

	protected Map groupItemProviderMap;

	protected ModelStructure modelStruct;

	protected ArrayList children;

	protected static final boolean processContributionEnabled = false;

	private IPropertyChangeListener prefStoreListener;
	
	public AbstractCoreProcessesItemProvider(AdapterFactory adapterFactory,
			ModelStructure modelStruct) {
		super(adapterFactory);
		this.modelStruct = modelStruct;

	}
	
	public void dispose() {
		IPreferenceStore prefStore = Providers.getPreferenceStore();
		if (prefStore != null && prefStoreListener != null) {
			prefStore.removePropertyChangeListener(prefStoreListener);
		}
		super.dispose();
	}
	
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Processes"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"); //$NON-NLS-1$
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}
	
}
