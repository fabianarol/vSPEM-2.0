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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessLineInstance;
import org.eclipse.epf.uma.ProcessLineElement;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Lines Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLinesPackageImpl#getProcessLines <em>Process Lines</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLinesPackageImpl#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessLinesPackageImpl extends MethodPackageImpl implements
		ProcessLinesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getProcessLines() <em>Process Lines</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessLines()
	 * @generated
	 * @ordered
	 */
	protected EList processLines = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessLinesPackageImpl() {
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
		return UmaPackage.Literals.PROCESS_LINES_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getProcessLines() {
		if (processLines == null) {
			processLines = new EObjectContainmentEList.Resolving(
					ProcessLineElement.class, this,
					UmaPackage.PROCESS_LINES_PACKAGE__PROCESS_LINES);
		}
		return processLines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectContainmentEList.Resolving(Diagram.class,
					this, UmaPackage.PROCESS_LINES_PACKAGE__DIAGRAMS);
		}
		return diagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.PROCESS_LINES_PACKAGE__PROCESS_LINES:
			return ((InternalEList) getProcessLines()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.PROCESS_LINES_PACKAGE__DIAGRAMS:
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
		case UmaPackage.PROCESS_LINES_PACKAGE__PROCESS_LINES:
			return getProcessLines();
		case UmaPackage.PROCESS_LINES_PACKAGE__DIAGRAMS:
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
		case UmaPackage.PROCESS_LINES_PACKAGE__PROCESS_LINES:
			getProcessLines().clear();
			getProcessLines().addAll((Collection) newValue);
			return;
		case UmaPackage.PROCESS_LINES_PACKAGE__DIAGRAMS:
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
		case UmaPackage.PROCESS_LINES_PACKAGE__PROCESS_LINES:
			getProcessLines().clear();
			return;
		case UmaPackage.PROCESS_LINES_PACKAGE__DIAGRAMS:
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
		case UmaPackage.PROCESS_LINES_PACKAGE__PROCESS_LINES:
			return processLines != null && !processLines.isEmpty();
		case UmaPackage.PROCESS_LINES_PACKAGE__DIAGRAMS:
			return diagrams != null && !diagrams.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessLinesPackageImpl