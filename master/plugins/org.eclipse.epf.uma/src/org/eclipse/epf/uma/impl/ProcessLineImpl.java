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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.epf.uma.ProcessLine;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Line</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineImpl#getGenera <em>Genera</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineImpl#getCompuestapor <em>Compuestapor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessLineImpl extends ProcessPackageImpl implements ProcessLine {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getGenera() <em>Genera</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenera()
	 * @generated
	 * @ordered
	 */
	protected TailoredProcessComponent genera = null;

	/**
	 * The cached value of the '{@link #getCompuestapor() <em>Compuestapor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompuestapor()
	 * @generated
	 * @ordered
	 */
	protected VarElement compuestapor = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessLineImpl() {
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
		return UmaPackage.Literals.PROCESS_LINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredProcessComponent getGenera() {
		if (genera != null && ((EObject) genera).eIsProxy()) {
			InternalEObject oldGenera = (InternalEObject) genera;
			genera = (TailoredProcessComponent) eResolveProxy(oldGenera);
			if (genera != oldGenera) {
				InternalEObject newGenera = (InternalEObject) genera;
				NotificationChain msgs = oldGenera.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_LINE__GENERA, null, null);
				if (newGenera.eInternalContainer() == null) {
					msgs = newGenera.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
							- UmaPackage.PROCESS_LINE__GENERA, null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS_LINE__GENERA, oldGenera, genera));
			}
		}
		return genera;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredProcessComponent basicGetGenera() {
		return genera;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGenera(TailoredProcessComponent newGenera,
			NotificationChain msgs) {
		TailoredProcessComponent oldGenera = genera;
		genera = newGenera;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.PROCESS_LINE__GENERA,
					oldGenera, newGenera);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenera(TailoredProcessComponent newGenera) {
		if (newGenera != genera) {
			NotificationChain msgs = null;
			if (genera != null)
				msgs = ((InternalEObject) genera).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_LINE__GENERA, null, msgs);
			if (newGenera != null)
				msgs = ((InternalEObject) newGenera).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_LINE__GENERA, null, msgs);
			msgs = basicSetGenera(newGenera, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_LINE__GENERA, newGenera, newGenera));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarElement getCompuestapor() {
		if (compuestapor != null && ((EObject) compuestapor).eIsProxy()) {
			InternalEObject oldCompuestapor = (InternalEObject) compuestapor;
			compuestapor = (VarElement) eResolveProxy(oldCompuestapor);
			if (compuestapor != oldCompuestapor) {
				InternalEObject newCompuestapor = (InternalEObject) compuestapor;
				NotificationChain msgs = oldCompuestapor.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_LINE__COMPUESTAPOR, null,
						null);
				if (newCompuestapor.eInternalContainer() == null) {
					msgs = newCompuestapor.eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
									- UmaPackage.PROCESS_LINE__COMPUESTAPOR,
							null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS_LINE__COMPUESTAPOR,
							oldCompuestapor, compuestapor));
			}
		}
		return compuestapor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarElement basicGetCompuestapor() {
		return compuestapor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompuestapor(VarElement newCompuestapor,
			NotificationChain msgs) {
		VarElement oldCompuestapor = compuestapor;
		compuestapor = newCompuestapor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.PROCESS_LINE__COMPUESTAPOR,
					oldCompuestapor, newCompuestapor);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompuestapor(VarElement newCompuestapor) {
		if (newCompuestapor != compuestapor) {
			NotificationChain msgs = null;
			if (compuestapor != null)
				msgs = ((InternalEObject) compuestapor).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_LINE__COMPUESTAPOR, null,
						msgs);
			if (newCompuestapor != null)
				msgs = ((InternalEObject) newCompuestapor).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_LINE__COMPUESTAPOR, null,
						msgs);
			msgs = basicSetCompuestapor(newCompuestapor, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_LINE__COMPUESTAPOR, newCompuestapor,
					newCompuestapor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.PROCESS_LINE__GENERA:
			return basicSetGenera(null, msgs);
		case UmaPackage.PROCESS_LINE__COMPUESTAPOR:
			return basicSetCompuestapor(null, msgs);
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
		case UmaPackage.PROCESS_LINE__GENERA:
			if (resolve)
				return getGenera();
			return basicGetGenera();
		case UmaPackage.PROCESS_LINE__COMPUESTAPOR:
			if (resolve)
				return getCompuestapor();
			return basicGetCompuestapor();
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
		case UmaPackage.PROCESS_LINE__GENERA:
			setGenera((TailoredProcessComponent) newValue);
			return;
		case UmaPackage.PROCESS_LINE__COMPUESTAPOR:
			setCompuestapor((VarElement) newValue);
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
		case UmaPackage.PROCESS_LINE__GENERA:
			setGenera((TailoredProcessComponent) null);
			return;
		case UmaPackage.PROCESS_LINE__COMPUESTAPOR:
			setCompuestapor((VarElement) null);
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
		case UmaPackage.PROCESS_LINE__GENERA:
			return genera != null;
		case UmaPackage.PROCESS_LINE__COMPUESTAPOR:
			return compuestapor != null;
		}
		return super.eIsSet(featureID);
	}

} //ProcessLineImpl