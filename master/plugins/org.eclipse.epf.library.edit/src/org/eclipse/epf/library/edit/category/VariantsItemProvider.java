package org.eclipse.epf.library.edit.category;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.navigator.AbstractVariantsItemProvider;
import org.eclipse.epf.library.edit.navigator.ProcessPackageItemProvider;
import org.eclipse.epf.library.edit.navigator.VariantsPackageItemProvider;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.util.UmaUtil;

public class VariantsItemProvider extends AbstractVariantsItemProvider{

	private MethodPlugin plugin;
	
	private Object parent;
	
	protected Collection children;
	
	public VariantsItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.plugin = plugin;
		
	}
	
	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			org.eclipse.epf.uma.VariantsPackage pkg = (org.eclipse.epf.uma.VariantsPackage) UmaUtil
					.findMethodPackage(plugin,
							modelStruct.variantsPath);
			if (pkg != null) {
				VariantsPackageItemProvider adapter = (VariantsPackageItemProvider) TngUtil
						.getBestAdapterFactory(adapterFactory).adapt(pkg,
								ITreeItemContentProvider.class);
//				adapter.setParent(this);
				children.add(pkg);
			}
		}

		return children;
	}
	
	public Object getParent(Object object) {
		return plugin;
	}
	
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
//		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
//				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
//				.createVariant()));
		
		
		/*Actualmente no puede realizar ningún tipo de acción*/
		
	}
	
//	public Collection getChildrenFeatures(Object object) {
//		if (childrenFeatures == null) {
//			childrenFeatures = new ArrayList();
//			childrenFeatures.add(UmaPackage.eINSTANCE
//					.getContentPackage_ContentElements());
//		}
//		return childrenFeatures;
//	}
	
//	protected boolean acceptAsChild(Object obj) {
//		if (!super.acceptAsChild(obj))
//			return false;
//		return obj instanceof Variant;
//	}
	
	
	public void addChildren(Object obj){
		children.add(obj);
	}
	
	public void setDefaultName(Object obj) {
		if (obj instanceof Variant) {
			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
					.getContentElements(), Variant.class), (MethodElement) obj,
					LibraryEditConstants.NEW_VARIANT);
		}
	}	

}
