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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Core Process Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.CoreProcessPackageImpl#getProcessElements <em>Process Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.CoreProcessPackageImpl#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.CoreProcessPackageImpl#getLineName <em>Line Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CoreProcessPackageImpl extends MethodPackageImpl implements
		CoreProcessPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getProcessElements() <em>Process Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessElements()
	 * @generated
	 * @ordered
	 */
	protected EList processElements = null;

	/**
	 * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList diagrams = null;

	/**
	 * The default value of the '{@link #getLineName() <em>Line Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineName()
	 * @generated
	 * @ordered
	 */
	protected static final String LINE_NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getLineName() <em>Line Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineName()
	 * @generated
	 * @ordered
	 */
	protected String lineName = LINE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoreProcessPackageImpl() {
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
		return UmaPackage.Literals.CORE_PROCESS_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getProcessElements() {
		if (processElements == null) {
			processElements = new EObjectContainmentEList.Resolving(
					ProcessElement.class, this,
					UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS);
		}
		return processElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectContainmentEList.Resolving(Diagram.class,
					this, UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS);
		}
		return diagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLineName() {
		return lineName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineName(String newLineName) {
		String oldLineName = lineName;
		lineName = newLineName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CORE_PROCESS_PACKAGE__LINE_NAME, oldLineName,
					lineName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
			return ((InternalEList) getProcessElements()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS:
			return ((InternalEList) getDiagrams()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
			return getProcessElements();
		case UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS:
			return getDiagrams();
		case UmaPackage.CORE_PROCESS_PACKAGE__LINE_NAME:
			return getLineName();
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
		case UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
			getProcessElements().clear();
			getProcessElements().addAll((Collection) newValue);
			return;
		case UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS:
			getDiagrams().clear();
			getDiagrams().addAll((Collection) newValue);
			return;
		case UmaPackage.CORE_PROCESS_PACKAGE__LINE_NAME:
			setLineName((String) newValue);
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
		case UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
			getProcessElements().clear();
			return;
		case UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS:
			getDiagrams().clear();
			return;
		case UmaPackage.CORE_PROCESS_PACKAGE__LINE_NAME:
			setLineName(LINE_NAME_EDEFAULT);
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
		case UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
			return processElements != null && !processElements.isEmpty();
		case UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS:
			return diagrams != null && !diagrams.isEmpty();
		case UmaPackage.CORE_PROCESS_PACKAGE__LINE_NAME:
			return LINE_NAME_EDEFAULT == null ? lineName != null
					: !LINE_NAME_EDEFAULT.equals(lineName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == ProcessPackage.class) {
			switch (derivedFeatureID) {
			case UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS:
				return UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS;
			case UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS:
				return UmaPackage.PROCESS_PACKAGE__DIAGRAMS;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == ProcessPackage.class) {
			switch (baseFeatureID) {
			case UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS:
				return UmaPackage.CORE_PROCESS_PACKAGE__PROCESS_ELEMENTS;
			case UmaPackage.PROCESS_PACKAGE__DIAGRAMS:
				return UmaPackage.CORE_PROCESS_PACKAGE__DIAGRAMS;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (lineName: "); //$NON-NLS-1$
		result.append(lineName);
		result.append(')');
		return result.toString();
	}

} //CoreProcessPackageImpl