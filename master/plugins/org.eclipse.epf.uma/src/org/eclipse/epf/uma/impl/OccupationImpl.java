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
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Occupation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.OccupationImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.OccupationImpl#isIsObjective <em>Is Objective</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.OccupationImpl#getOcupadopor <em>Ocupadopor</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.OccupationImpl#getEsocupado <em>Esocupado</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OccupationImpl extends VariationImpl implements Occupation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsObjective() <em>Is Objective</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsObjective()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_OBJECTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsObjective() <em>Is Objective</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsObjective()
	 * @generated
	 * @ordered
	 */
	protected boolean isObjective = IS_OBJECTIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOcupadopor() <em>Ocupadopor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOcupadopor()
	 * @generated
	 * @ordered
	 */
	protected Variant ocupadopor = null;

	/**
	 * The cached value of the '{@link #getEsocupado() <em>Esocupado</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEsocupado()
	 * @generated
	 * @ordered
	 */
	protected VarPoint esocupado = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OccupationImpl() {
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
		return UmaPackage.Literals.OCCUPATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.OCCUPATION__DESCRIPTION, oldDescription,
					description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsObjective() {
		return isObjective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsObjective(boolean newIsObjective) {
		boolean oldIsObjective = isObjective;
		isObjective = newIsObjective;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.OCCUPATION__IS_OBJECTIVE, oldIsObjective,
					isObjective));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variant getOcupadopor() {
		if (ocupadopor != null && ((EObject) ocupadopor).eIsProxy()) {
			InternalEObject oldOcupadopor = (InternalEObject) ocupadopor;
			ocupadopor = (Variant) eResolveProxy(oldOcupadopor);
			if (ocupadopor != oldOcupadopor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.OCCUPATION__OCUPADOPOR, oldOcupadopor,
							ocupadopor));
			}
		}
		return ocupadopor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variant basicGetOcupadopor() {
		return ocupadopor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOcupadopor(Variant newOcupadopor) {
		Variant oldOcupadopor = ocupadopor;
		ocupadopor = newOcupadopor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.OCCUPATION__OCUPADOPOR, oldOcupadopor,
					ocupadopor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPoint getEsocupado() {
		if (esocupado != null && ((EObject) esocupado).eIsProxy()) {
			InternalEObject oldEsocupado = (InternalEObject) esocupado;
			esocupado = (VarPoint) eResolveProxy(oldEsocupado);
			if (esocupado != oldEsocupado) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.OCCUPATION__ESOCUPADO, oldEsocupado,
							esocupado));
			}
		}
		return esocupado;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPoint basicGetEsocupado() {
		return esocupado;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEsocupado(VarPoint newEsocupado) {
		VarPoint oldEsocupado = esocupado;
		esocupado = newEsocupado;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.OCCUPATION__ESOCUPADO, oldEsocupado, esocupado));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.OCCUPATION__DESCRIPTION:
			return getDescription();
		case UmaPackage.OCCUPATION__IS_OBJECTIVE:
			return isIsObjective() ? Boolean.TRUE : Boolean.FALSE;
		case UmaPackage.OCCUPATION__OCUPADOPOR:
			if (resolve)
				return getOcupadopor();
			return basicGetOcupadopor();
		case UmaPackage.OCCUPATION__ESOCUPADO:
			if (resolve)
				return getEsocupado();
			return basicGetEsocupado();
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
		case UmaPackage.OCCUPATION__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UmaPackage.OCCUPATION__IS_OBJECTIVE:
			setIsObjective(((Boolean) newValue).booleanValue());
			return;
		case UmaPackage.OCCUPATION__OCUPADOPOR:
			setOcupadopor((Variant) newValue);
			return;
		case UmaPackage.OCCUPATION__ESOCUPADO:
			setEsocupado((VarPoint) newValue);
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
		case UmaPackage.OCCUPATION__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.OCCUPATION__IS_OBJECTIVE:
			setIsObjective(IS_OBJECTIVE_EDEFAULT);
			return;
		case UmaPackage.OCCUPATION__OCUPADOPOR:
			setOcupadopor((Variant) null);
			return;
		case UmaPackage.OCCUPATION__ESOCUPADO:
			setEsocupado((VarPoint) null);
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
		case UmaPackage.OCCUPATION__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case UmaPackage.OCCUPATION__IS_OBJECTIVE:
			return isObjective != IS_OBJECTIVE_EDEFAULT;
		case UmaPackage.OCCUPATION__OCUPADOPOR:
			return ocupadopor != null;
		case UmaPackage.OCCUPATION__ESOCUPADO:
			return esocupado != null;
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
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", isObjective: "); //$NON-NLS-1$
		result.append(isObjective);
		result.append(')');
		return result.toString();
	}

} //OccupationImpl