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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.TailoredProcessesPackage;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tailored Processes Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessesPackageImpl#getTailoredProcessElements <em>Tailored Process Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessesPackageImpl#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TailoredProcessesPackageImpl extends MethodPackageImpl implements
		TailoredProcessesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getTailoredProcessElements() <em>Tailored Process Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTailoredProcessElements()
	 * @generated
	 * @ordered
	 */
	protected EList tailoredProcessElements = null;

	/**
	 * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList diagrams = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TailoredProcessesPackageImpl() {
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
		return UmaPackage.Literals.TAILORED_PROCESSES_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getTailoredProcessElements() {
		if (tailoredProcessElements == null) {
			tailoredProcessElements = new EObjectResolvingEList(
					ProcessElement.class,
					this,
					UmaPackage.TAILORED_PROCESSES_PACKAGE__TAILORED_PROCESS_ELEMENTS);
		}
		return tailoredProcessElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectResolvingEList(Diagram.class, this,
					UmaPackage.TAILORED_PROCESSES_PACKAGE__DIAGRAMS);
		}
		return diagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__TAILORED_PROCESS_ELEMENTS:
			return getTailoredProcessElements();
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__DIAGRAMS:
			return getDiagrams();
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
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__TAILORED_PROCESS_ELEMENTS:
			getTailoredProcessElements().clear();
			getTailoredProcessElements().addAll((Collection) newValue);
			return;
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__DIAGRAMS:
			getDiagrams().clear();
			getDiagrams().addAll((Collection) newValue);
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
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__TAILORED_PROCESS_ELEMENTS:
			getTailoredProcessElements().clear();
			return;
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__DIAGRAMS:
			getDiagrams().clear();
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
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__TAILORED_PROCESS_ELEMENTS:
			return tailoredProcessElements != null
					&& !tailoredProcessElements.isEmpty();
		case UmaPackage.TAILORED_PROCESSES_PACKAGE__DIAGRAMS:
			return diagrams != null && !diagrams.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TailoredProcessesPackageImpl