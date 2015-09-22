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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.VarElementImpl#getVpName <em>Vp Name</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.VarElementImpl#getVId <em>VId</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.VarElementImpl#getPathIcon <em>Path Icon</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.VarElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.VarElementImpl#getClient <em>Client</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.VarElementImpl#getSupplier <em>Supplier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class VarElementImpl extends VariabilityImpl implements
		VarElement {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VarElementImpl() {
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
		return UmaPackage.Literals.VAR_ELEMENT;
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
					UmaPackage.VAR_ELEMENT__VP_NAME, oldVpName, vpName));
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
					UmaPackage.VAR_ELEMENT__VID, oldVId, vId));
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
					UmaPackage.VAR_ELEMENT__PATH_ICON, oldPathIcon, pathIcon));
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
					UmaPackage.VAR_ELEMENT__DESCRIPTION, oldDescription,
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
					this, UmaPackage.VAR_ELEMENT__CLIENT);
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
					this, UmaPackage.VAR_ELEMENT__SUPPLIER);
		}
		return supplier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.VAR_ELEMENT__CLIENT:
			return ((InternalEList) getClient()).basicRemove(otherEnd, msgs);
		case UmaPackage.VAR_ELEMENT__SUPPLIER:
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
		case UmaPackage.VAR_ELEMENT__VP_NAME:
			return getVpName();
		case UmaPackage.VAR_ELEMENT__VID:
			return getVId();
		case UmaPackage.VAR_ELEMENT__PATH_ICON:
			return getPathIcon();
		case UmaPackage.VAR_ELEMENT__DESCRIPTION:
			return getDescription();
		case UmaPackage.VAR_ELEMENT__CLIENT:
			return getClient();
		case UmaPackage.VAR_ELEMENT__SUPPLIER:
			return getSupplier();
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
		case UmaPackage.VAR_ELEMENT__VP_NAME:
			setVpName((String) newValue);
			return;
		case UmaPackage.VAR_ELEMENT__VID:
			setVId((String) newValue);
			return;
		case UmaPackage.VAR_ELEMENT__PATH_ICON:
			setPathIcon((String) newValue);
			return;
		case UmaPackage.VAR_ELEMENT__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UmaPackage.VAR_ELEMENT__CLIENT:
			getClient().clear();
			getClient().addAll((Collection) newValue);
			return;
		case UmaPackage.VAR_ELEMENT__SUPPLIER:
			getSupplier().clear();
			getSupplier().addAll((Collection) newValue);
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
		case UmaPackage.VAR_ELEMENT__VP_NAME:
			setVpName(VP_NAME_EDEFAULT);
			return;
		case UmaPackage.VAR_ELEMENT__VID:
			setVId(VID_EDEFAULT);
			return;
		case UmaPackage.VAR_ELEMENT__PATH_ICON:
			setPathIcon(PATH_ICON_EDEFAULT);
			return;
		case UmaPackage.VAR_ELEMENT__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.VAR_ELEMENT__CLIENT:
			getClient().clear();
			return;
		case UmaPackage.VAR_ELEMENT__SUPPLIER:
			getSupplier().clear();
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
		case UmaPackage.VAR_ELEMENT__VP_NAME:
			return VP_NAME_EDEFAULT == null ? vpName != null
					: !VP_NAME_EDEFAULT.equals(vpName);
		case UmaPackage.VAR_ELEMENT__VID:
			return VID_EDEFAULT == null ? vId != null : !VID_EDEFAULT
					.equals(vId);
		case UmaPackage.VAR_ELEMENT__PATH_ICON:
			return PATH_ICON_EDEFAULT == null ? pathIcon != null
					: !PATH_ICON_EDEFAULT.equals(pathIcon);
		case UmaPackage.VAR_ELEMENT__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case UmaPackage.VAR_ELEMENT__CLIENT:
			return client != null && !client.isEmpty();
		case UmaPackage.VAR_ELEMENT__SUPPLIER:
			return supplier != null && !supplier.isEmpty();
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
		result.append(" (vpName: "); //$NON-NLS-1$
		result.append(vpName);
		result.append(", vId: "); //$NON-NLS-1$
		result.append(vId);
		result.append(", pathIcon: "); //$NON-NLS-1$
		result.append(pathIcon);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //VarElementImpl