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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.UmaPackage;

import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;

import org.eclipse.epf.uma.vpActivity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>vp Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getVpName <em>Vp Name</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getVId <em>VId</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getPathIcon <em>Path Icon</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getClient <em>Client</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getSupplier <em>Supplier</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#isIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#isIsOpen <em>Is Open</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.vpActivityImpl#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class vpActivityImpl extends ActivityImpl implements vpActivity {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The default value of the '{@link #getVpName() <em>Vp Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVpName()
	 * @generated
	 * @ordered
	 */
	protected static final String VP_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getVpName() <em>Vp Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVpName()
	 * @generated
	 * @ordered
	 */
	protected String vpName = VP_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getVId() <em>VId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVId()
	 * @generated
	 * @ordered
	 */
	protected static final String VID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getVId() <em>VId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVId()
	 * @generated
	 * @ordered
	 */
	protected String vId = VID_EDEFAULT;
	/**
	 * The default value of the '{@link #getPathIcon() <em>Path Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_ICON_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getPathIcon() <em>Path Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathIcon()
	 * @generated
	 * @ordered
	 */
	protected String pathIcon = PATH_ICON_EDEFAULT;
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
	 * The cached value of the '{@link #getClient() <em>Client</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClient()
	 * @generated
	 * @ordered
	 */
	protected EList client = null;

	/**
	 * The cached value of the '{@link #getSupplier() <em>Supplier</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplier()
	 * @generated
	 * @ordered
	 */
	protected EList supplier = null;

	/**
	 * The default value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMPLICIT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected boolean isImplicit = IS_IMPLICIT_EDEFAULT;
	/**
	 * The default value of the '{@link #isIsOpen() <em>Is Open</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOpen()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_OPEN_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsOpen() <em>Is Open</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOpen()
	 * @generated
	 * @ordered
	 */
	protected boolean isOpen = IS_OPEN_EDEFAULT;
	/**
	 * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected int min = MIN_EDEFAULT;
	/**
	 * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected int max = MAX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected vpActivityImpl() {
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
		return UmaPackage.Literals.VP_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVpName() {
		return vpName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVpName(String newVpName) {
		String oldVpName = vpName;
		vpName = newVpName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__VP_NAME, oldVpName, vpName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVId() {
		return vId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVId(String newVId) {
		String oldVId = vId;
		vId = newVId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__VID, oldVId, vId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPathIcon() {
		return pathIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathIcon(String newPathIcon) {
		String oldPathIcon = pathIcon;
		pathIcon = newPathIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__PATH_ICON, oldPathIcon, pathIcon));
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
					UmaPackage.VP_ACTIVITY__DESCRIPTION, oldDescription,
					description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getClient() {
		if (client == null) {
			client = new EObjectContainmentEList.Resolving(Dependences.class,
					this, UmaPackage.VP_ACTIVITY__CLIENT);
		}
		return client;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getSupplier() {
		if (supplier == null) {
			supplier = new EObjectContainmentEList.Resolving(Dependences.class,
					this, UmaPackage.VP_ACTIVITY__SUPPLIER);
		}
		return supplier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsImplicit() {
		return isImplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsImplicit(boolean newIsImplicit) {
		boolean oldIsImplicit = isImplicit;
		isImplicit = newIsImplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__IS_IMPLICIT, oldIsImplicit,
					isImplicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsOpen() {
		return isOpen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOpen(boolean newIsOpen) {
		boolean oldIsOpen = isOpen;
		isOpen = newIsOpen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__IS_OPEN, oldIsOpen, isOpen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin() {
		return min;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin(int newMin) {
		int oldMin = min;
		min = newMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__MIN, oldMin, min));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax() {
		return max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax(int newMax) {
		int oldMax = max;
		max = newMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.VP_ACTIVITY__MAX, oldMax, max));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.VP_ACTIVITY__CLIENT:
			return ((InternalEList) getClient()).basicRemove(otherEnd, msgs);
		case UmaPackage.VP_ACTIVITY__SUPPLIER:
			return ((InternalEList) getSupplier()).basicRemove(otherEnd, msgs);
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
		case UmaPackage.VP_ACTIVITY__VP_NAME:
			return getVpName();
		case UmaPackage.VP_ACTIVITY__VID:
			return getVId();
		case UmaPackage.VP_ACTIVITY__PATH_ICON:
			return getPathIcon();
		case UmaPackage.VP_ACTIVITY__DESCRIPTION:
			return getDescription();
		case UmaPackage.VP_ACTIVITY__CLIENT:
			return getClient();
		case UmaPackage.VP_ACTIVITY__SUPPLIER:
			return getSupplier();
		case UmaPackage.VP_ACTIVITY__IS_IMPLICIT:
			return isIsImplicit() ? Boolean.TRUE : Boolean.FALSE;
		case UmaPackage.VP_ACTIVITY__IS_OPEN:
			return isIsOpen() ? Boolean.TRUE : Boolean.FALSE;
		case UmaPackage.VP_ACTIVITY__MIN:
			return new Integer(getMin());
		case UmaPackage.VP_ACTIVITY__MAX:
			return new Integer(getMax());
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
		case UmaPackage.VP_ACTIVITY__VP_NAME:
			setVpName((String) newValue);
			return;
		case UmaPackage.VP_ACTIVITY__VID:
			setVId((String) newValue);
			return;
		case UmaPackage.VP_ACTIVITY__PATH_ICON:
			setPathIcon((String) newValue);
			return;
		case UmaPackage.VP_ACTIVITY__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UmaPackage.VP_ACTIVITY__CLIENT:
			getClient().clear();
			getClient().addAll((Collection) newValue);
			return;
		case UmaPackage.VP_ACTIVITY__SUPPLIER:
			getSupplier().clear();
			getSupplier().addAll((Collection) newValue);
			return;
		case UmaPackage.VP_ACTIVITY__IS_IMPLICIT:
			setIsImplicit(((Boolean) newValue).booleanValue());
			return;
		case UmaPackage.VP_ACTIVITY__IS_OPEN:
			setIsOpen(((Boolean) newValue).booleanValue());
			return;
		case UmaPackage.VP_ACTIVITY__MIN:
			setMin(((Integer) newValue).intValue());
			return;
		case UmaPackage.VP_ACTIVITY__MAX:
			setMax(((Integer) newValue).intValue());
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
		case UmaPackage.VP_ACTIVITY__VP_NAME:
			setVpName(VP_NAME_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__VID:
			setVId(VID_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__PATH_ICON:
			setPathIcon(PATH_ICON_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__CLIENT:
			getClient().clear();
			return;
		case UmaPackage.VP_ACTIVITY__SUPPLIER:
			getSupplier().clear();
			return;
		case UmaPackage.VP_ACTIVITY__IS_IMPLICIT:
			setIsImplicit(IS_IMPLICIT_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__IS_OPEN:
			setIsOpen(IS_OPEN_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__MIN:
			setMin(MIN_EDEFAULT);
			return;
		case UmaPackage.VP_ACTIVITY__MAX:
			setMax(MAX_EDEFAULT);
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
		case UmaPackage.VP_ACTIVITY__VP_NAME:
			return VP_NAME_EDEFAULT == null ? vpName != null
					: !VP_NAME_EDEFAULT.equals(vpName);
		case UmaPackage.VP_ACTIVITY__VID:
			return VID_EDEFAULT == null ? vId != null : !VID_EDEFAULT
					.equals(vId);
		case UmaPackage.VP_ACTIVITY__PATH_ICON:
			return PATH_ICON_EDEFAULT == null ? pathIcon != null
					: !PATH_ICON_EDEFAULT.equals(pathIcon);
		case UmaPackage.VP_ACTIVITY__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case UmaPackage.VP_ACTIVITY__CLIENT:
			return client != null && !client.isEmpty();
		case UmaPackage.VP_ACTIVITY__SUPPLIER:
			return supplier != null && !supplier.isEmpty();
		case UmaPackage.VP_ACTIVITY__IS_IMPLICIT:
			return isImplicit != IS_IMPLICIT_EDEFAULT;
		case UmaPackage.VP_ACTIVITY__IS_OPEN:
			return isOpen != IS_OPEN_EDEFAULT;
		case UmaPackage.VP_ACTIVITY__MIN:
			return min != MIN_EDEFAULT;
		case UmaPackage.VP_ACTIVITY__MAX:
			return max != MAX_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == VarElement.class) {
			switch (derivedFeatureID) {
			case UmaPackage.VP_ACTIVITY__VP_NAME:
				return UmaPackage.VAR_ELEMENT__VP_NAME;
			case UmaPackage.VP_ACTIVITY__VID:
				return UmaPackage.VAR_ELEMENT__VID;
			case UmaPackage.VP_ACTIVITY__PATH_ICON:
				return UmaPackage.VAR_ELEMENT__PATH_ICON;
			case UmaPackage.VP_ACTIVITY__DESCRIPTION:
				return UmaPackage.VAR_ELEMENT__DESCRIPTION;
			case UmaPackage.VP_ACTIVITY__CLIENT:
				return UmaPackage.VAR_ELEMENT__CLIENT;
			case UmaPackage.VP_ACTIVITY__SUPPLIER:
				return UmaPackage.VAR_ELEMENT__SUPPLIER;
			default:
				return -1;
			}
		}
		if (baseClass == VarPoint.class) {
			switch (derivedFeatureID) {
			case UmaPackage.VP_ACTIVITY__IS_IMPLICIT:
				return UmaPackage.VAR_POINT__IS_IMPLICIT;
			case UmaPackage.VP_ACTIVITY__IS_OPEN:
				return UmaPackage.VAR_POINT__IS_OPEN;
			case UmaPackage.VP_ACTIVITY__MIN:
				return UmaPackage.VAR_POINT__MIN;
			case UmaPackage.VP_ACTIVITY__MAX:
				return UmaPackage.VAR_POINT__MAX;
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
		if (baseClass == VarElement.class) {
			switch (baseFeatureID) {
			case UmaPackage.VAR_ELEMENT__VP_NAME:
				return UmaPackage.VP_ACTIVITY__VP_NAME;
			case UmaPackage.VAR_ELEMENT__VID:
				return UmaPackage.VP_ACTIVITY__VID;
			case UmaPackage.VAR_ELEMENT__PATH_ICON:
				return UmaPackage.VP_ACTIVITY__PATH_ICON;
			case UmaPackage.VAR_ELEMENT__DESCRIPTION:
				return UmaPackage.VP_ACTIVITY__DESCRIPTION;
			case UmaPackage.VAR_ELEMENT__CLIENT:
				return UmaPackage.VP_ACTIVITY__CLIENT;
			case UmaPackage.VAR_ELEMENT__SUPPLIER:
				return UmaPackage.VP_ACTIVITY__SUPPLIER;
			default:
				return -1;
			}
		}
		if (baseClass == VarPoint.class) {
			switch (baseFeatureID) {
			case UmaPackage.VAR_POINT__IS_IMPLICIT:
				return UmaPackage.VP_ACTIVITY__IS_IMPLICIT;
			case UmaPackage.VAR_POINT__IS_OPEN:
				return UmaPackage.VP_ACTIVITY__IS_OPEN;
			case UmaPackage.VAR_POINT__MIN:
				return UmaPackage.VP_ACTIVITY__MIN;
			case UmaPackage.VAR_POINT__MAX:
				return UmaPackage.VP_ACTIVITY__MAX;
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
		result.append(" (vpName: "); //$NON-NLS-1$
		result.append(vpName);
		result.append(", vId: "); //$NON-NLS-1$
		result.append(vId);
		result.append(", pathIcon: "); //$NON-NLS-1$
		result.append(pathIcon);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", isImplicit: "); //$NON-NLS-1$
		result.append(isImplicit);
		result.append(", isOpen: "); //$NON-NLS-1$
		result.append(isOpen);
		result.append(", min: "); //$NON-NLS-1$
		result.append(min);
		result.append(", max: "); //$NON-NLS-1$
		result.append(max);
		result.append(')');
		return result.toString();
	}

} //vpActivityImpl