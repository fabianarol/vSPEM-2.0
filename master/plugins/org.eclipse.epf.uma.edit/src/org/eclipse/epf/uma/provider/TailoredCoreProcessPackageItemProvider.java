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

import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.epf.uma.TailoredCoreProcessPackage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TailoredCoreProcessPackageItemProvider extends
		MethodPackageItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredCoreProcessPackageItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures
					.add(UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS);
			childrenFeatures.add(UmaPackage.Literals.PROCESS_PACKAGE__DIAGRAMS);
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
	 * This returns TailoredCoreProcessPackage.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage(
				"full/obj16/TailoredCoreProcessPackage")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((TailoredCoreProcessPackage) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_TailoredCoreProcessPackage_type") : //$NON-NLS-1$
				getString("_UI_TailoredCoreProcessPackage_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(TailoredCoreProcessPackage.class)) {
		case UmaPackage.TAILORED_CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
		case UmaPackage.TAILORED_CORE_PROCESS_PACKAGE__DIAGRAMS:
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
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createMilestone()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createActivity()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createIteration()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createPhase()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createTeamProfile()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createWorkOrder()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createPlanningData()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createWorkProductDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createTaskDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createCompositeRole()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createDeliveryProcess()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createCapabilityPattern()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createProcessPlanningTemplate()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createProcessComponentInterface()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createProcessComponentDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createLineProcess()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createProcessLineComponentInterface()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createTailoredProcess()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpActivity()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpPhase()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpIteration()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpTaskDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpMilestone()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpTeamProfile()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createvpWorkProductDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarActivity()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarPhase()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarIteration()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarRoleDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarTaskDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarMilestone()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarTeamProfile()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS,
				UmaFactory.eINSTANCE.createVarWorkProductDescriptor()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.PROCESS_PACKAGE__DIAGRAMS,
				UmaFactory.eINSTANCE.createDiagram()));
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
