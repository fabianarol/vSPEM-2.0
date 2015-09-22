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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessElementImpl#getProcessLineName <em>Process Line Name</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessElementImpl#getTailoredProcessName <em>Tailored Process Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ProcessElementImpl extends DescribableElementImpl
		implements ProcessElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The default value of the '{@link #getProcessLineName() <em>Process Line Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessLineName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROCESS_LINE_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getProcessLineName() <em>Process Line Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessLineName()
	 * @generated
	 * @ordered
	 */
	protected String processLineName = PROCESS_LINE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTailoredProcessName() <em>Tailored Process Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTailoredProcessName()
	 * @generated
	 * @ordered
	 */
	protected static final String TAILORED_PROCESS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTailoredProcessName() <em>Tailored Process Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTailoredProcessName()
	 * @generated
	 * @ordered
	 */
	protected String tailoredProcessName = TAILORED_PROCESS_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessElementImpl() {
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
		return UmaPackage.Literals.PROCESS_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProcessLineName() {
		return processLineName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessLineName(String newProcessLineName) {
		String oldProcessLineName = processLineName;
		processLineName = newProcessLineName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_ELEMENT__PROCESS_LINE_NAME,
					oldProcessLineName, processLineName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTailoredProcessName() {
		return tailoredProcessName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTailoredProcessName(String newTailoredProcessName) {
		String oldTailoredProcessName = tailoredProcessName;
		tailoredProcessName = newTailoredProcessName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_ELEMENT__TAILORED_PROCESS_NAME,
					oldTailoredProcessName, tailoredProcessName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.PROCESS_ELEMENT__PROCESS_LINE_NAME:
			return getProcessLineName();
		case UmaPackage.PROCESS_ELEMENT__TAILORED_PROCESS_NAME:
			return getTailoredProcessName();
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
		case UmaPackage.PROCESS_ELEMENT__PROCESS_LINE_NAME:
			setProcessLineName((String) newValue);
			return;
		case UmaPackage.PROCESS_ELEMENT__TAILORED_PROCESS_NAME:
			setTailoredProcessName((String) newValue);
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
		case UmaPackage.PROCESS_ELEMENT__PROCESS_LINE_NAME:
			setProcessLineName(PROCESS_LINE_NAME_EDEFAULT);
			return;
		case UmaPackage.PROCESS_ELEMENT__TAILORED_PROCESS_NAME:
			setTailoredProcessName(TAILORED_PROCESS_NAME_EDEFAULT);
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
		case UmaPackage.PROCESS_ELEMENT__PROCESS_LINE_NAME:
			return PROCESS_LINE_NAME_EDEFAULT == null ? processLineName != null
					: !PROCESS_LINE_NAME_EDEFAULT.equals(processLineName);
		case UmaPackage.PROCESS_ELEMENT__TAILORED_PROCESS_NAME:
			return TAILORED_PROCESS_NAME_EDEFAULT == null ? tailoredProcessName != null
					: !TAILORED_PROCESS_NAME_EDEFAULT
							.equals(tailoredProcessName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (processLineName: "); //$NON-NLS-1$
		result.append(processLineName);
		result.append(", tailoredProcessName: "); //$NON-NLS-1$
		result.append(tailoredProcessName);
		result.append(')');
		return result.toString();
	}

} //ProcessElementImpl
