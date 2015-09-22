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
package org.eclipse.epf.uma.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.epf.uma.ProcessLine;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.epf.uma.ProcessLine} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessLineItemProvider extends ProcessPackageItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(UmaPackage.Literals.PROCESS_LINE__GENERA);
			childrenFeatures
					.add(UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ProcessLine.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage(
				"full/obj16/ProcessLine")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ProcessLine) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ProcessLine_type") : //$NON-NLS-1$
				getString("_UI_ProcessLine_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ProcessLine.class)) {
		case UmaPackage.PROCESS_LINE__GENERA:
		case UmaPackage.PROCESS_LINE__COMPUESTAPOR:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__GENERA, UmaFactory.eINSTANCE
						.createTailoredProcessComponent()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarPoint()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpActivity()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpPhase()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpIteration()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpTaskDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpMilestone()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpTeamProfile()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createvpWorkProductDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVariant()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarActivity()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarPhase()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarIteration()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarTaskDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarMilestone()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarTeamProfile()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createVarWorkProductDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR,
				UmaFactory.eINSTANCE.createProcAspect()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == UmaPackage.Literals.METHOD_PACKAGE__CHILD_PACKAGES
				|| childFeature == UmaPackage.Literals.PROCESS_LINE__GENERA
				|| childFeature == UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS
				|| childFeature == UmaPackage.Literals.PROCESS_LINE__COMPUESTAPOR;

		if (qualify) {
			return getString("_UI_CreateChild_text2", //$NON-NLS-1$
					new Object[] { getTypeText(childObject),
							getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return UmaEditPlugin.INSTANCE;
	}

}
