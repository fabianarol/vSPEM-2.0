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

import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.ProcPointcut;
import org.eclipse.epf.uma.UmaPackage;

import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proc Pointcut</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcPointcutImpl#getSelfcomposition <em>Selfcomposition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcPointcutImpl#getGenera <em>Genera</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcPointcutImpl extends MultiResourceEObject implements
		ProcPointcut {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getSelfcomposition() <em>Selfcomposition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelfcomposition()
	 * @generated
	 * @ordered
	 */
	protected ProcPointcut selfcomposition = null;

	/**
	 * The cached value of the '{@link #getGenera() <em>Genera</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenera()
	 * @generated
	 * @ordered
	 */
	protected Occupation genera = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcPointcutImpl() {
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
		return UmaPackage.Literals.PROC_POINTCUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcPointcut getSelfcomposition() {
		if (selfcomposition != null && ((EObject) selfcomposition).eIsProxy()) {
			InternalEObject oldSelfcomposition = (InternalEObject) selfcomposition;
			selfcomposition = (ProcPointcut) eResolveProxy(oldSelfcomposition);
			if (selfcomposition != oldSelfcomposition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROC_POINTCUT__SELFCOMPOSITION,
							oldSelfcomposition, selfcomposition));
			}
		}
		return selfcomposition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcPointcut basicGetSelfcomposition() {
		return selfcomposition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelfcomposition(ProcPointcut newSelfcomposition) {
		ProcPointcut oldSelfcomposition = selfcomposition;
		selfcomposition = newSelfcomposition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROC_POINTCUT__SELFCOMPOSITION,
					oldSelfcomposition, selfcomposition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Occupation getGenera() {
		if (genera != null && ((EObject) genera).eIsProxy()) {
			InternalEObject oldGenera = (InternalEObject) genera;
			genera = (Occupation) eResolveProxy(oldGenera);
			if (genera != oldGenera) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROC_POINTCUT__GENERA, oldGenera, genera));
			}
		}
		return genera;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Occupation basicGetGenera() {
		return genera;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenera(Occupation newGenera) {
		Occupation oldGenera = genera;
		genera = newGenera;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROC_POINTCUT__GENERA, oldGenera, genera));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.PROC_POINTCUT__SELFCOMPOSITION:
			if (resolve)
				return getSelfcomposition();
			return basicGetSelfcomposition();
		case UmaPackage.PROC_POINTCUT__GENERA:
			if (resolve)
				return getGenera();
			return basicGetGenera();
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
		case UmaPackage.PROC_POINTCUT__SELFCOMPOSITION:
			setSelfcomposition((ProcPointcut) newValue);
			return;
		case UmaPackage.PROC_POINTCUT__GENERA:
			setGenera((Occupation) newValue);
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
		case UmaPackage.PROC_POINTCUT__SELFCOMPOSITION:
			setSelfcomposition((ProcPointcut) null);
			return;
		case UmaPackage.PROC_POINTCUT__GENERA:
			setGenera((Occupation) null);
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
		case UmaPackage.PROC_POINTCUT__SELFCOMPOSITION:
			return selfcomposition != null;
		case UmaPackage.PROC_POINTCUT__GENERA:
			return genera != null;
		}
		return super.eIsSet(featureID);
	}

} //ProcPointcutImpl