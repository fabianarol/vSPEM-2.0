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
package org.eclipse.epf.uma.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLineComponentPackage;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Line Component Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentPackageImpl#getProcessLineComponent <em>Process Line Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessLineComponentPackageImpl extends MethodPackageImpl
		implements ProcessLineComponentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getProcessLineComponent() <em>Process Line Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessLineComponent()
	 * @generated
	 * @ordered
	 */
	protected ProcessLineComponent processLineComponent = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessLineComponentPackageImpl() {
		super();

		//UMA-->
		reassignDefaultValues();
		//UMA<--
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UmaPackage.Literals.PROCESS_LINE_COMPONENT_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineComponent getProcessLineComponent() {
		if (processLineComponent != null
				&& ((EObject) processLineComponent).eIsProxy()) {
			InternalEObject oldProcessLineComponent = (InternalEObject) processLineComponent;
			processLineComponent = (ProcessLineComponent) eResolveProxy(oldProcessLineComponent);
			if (processLineComponent != oldProcessLineComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT,
							oldProcessLineComponent, processLineComponent));
			}
		}
		return processLineComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineComponent basicGetProcessLineComponent() {
		return processLineComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessLineComponent(
			ProcessLineComponent newProcessLineComponent) {
		ProcessLineComponent oldProcessLineComponent = processLineComponent;
		processLineComponent = newProcessLineComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT,
					oldProcessLineComponent, processLineComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT:
			if (resolve)
				return getProcessLineComponent();
			return basicGetProcessLineComponent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT:
			setProcessLineComponent((ProcessLineComponent) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
		case UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT:
			setProcessLineComponent((ProcessLineComponent) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if (feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
		//UMA<--		
		switch (featureID) {
		case UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT:
			return processLineComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //ProcessLineComponentPackageImpl