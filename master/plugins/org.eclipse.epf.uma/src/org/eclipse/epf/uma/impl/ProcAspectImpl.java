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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ProcAdvice;
import org.eclipse.epf.uma.ProcAspect;
import org.eclipse.epf.uma.ProcPointcut;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.Variation;
import org.eclipse.epf.uma.VariationType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proc Aspect</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcAspectImpl#getTipoVariacion <em>Tipo Variacion</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcAspectImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcAspectImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcAspectImpl#getPointcut <em>Pointcut</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcAspectImpl#getContain <em>Contain</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcAspectImpl#getUtiliza <em>Utiliza</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcAspectImpl extends VarElementImpl implements ProcAspect {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getTipoVariacion() <em>Tipo Variacion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTipoVariacion()
	 * @generated
	 * @ordered
	 */
	protected VariationType tipoVariacion = null;

	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAdvice() <em>Advice</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdvice()
	 * @generated
	 * @ordered
	 */
	protected EList advice = null;

	/**
	 * The cached value of the '{@link #getPointcut() <em>Pointcut</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointcut()
	 * @generated
	 * @ordered
	 */
	protected EList pointcut = null;

	/**
	 * The cached value of the '{@link #getContain() <em>Contain</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContain()
	 * @generated
	 * @ordered
	 */
	protected Variant contain = null;

	/**
	 * The cached value of the '{@link #getUtiliza() <em>Utiliza</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtiliza()
	 * @generated
	 * @ordered
	 */
	protected VarPoint utiliza = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcAspectImpl() {
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
		return UmaPackage.Literals.PROC_ASPECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationType getTipoVariacion() {
		if (tipoVariacion != null && ((EObject) tipoVariacion).eIsProxy()) {
			InternalEObject oldTipoVariacion = (InternalEObject) tipoVariacion;
			tipoVariacion = (VariationType) eResolveProxy(oldTipoVariacion);
			if (tipoVariacion != oldTipoVariacion) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROC_ASPECT__TIPO_VARIACION,
							oldTipoVariacion, tipoVariacion));
			}
		}
		return tipoVariacion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationType basicGetTipoVariacion() {
		return tipoVariacion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTipoVariacion(VariationType newTipoVariacion) {
		VariationType oldTipoVariacion = tipoVariacion;
		tipoVariacion = newTipoVariacion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROC_ASPECT__TIPO_VARIACION, oldTipoVariacion,
					tipoVariacion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROC_ASPECT__ACTIVE, oldActive, active));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getAdvice() {
		if (advice == null) {
			advice = new EObjectContainmentEList.Resolving(ProcAdvice.class,
					this, UmaPackage.PROC_ASPECT__ADVICE);
		}
		return advice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPointcut() {
		if (pointcut == null) {
			pointcut = new EObjectContainmentEList.Resolving(
					ProcPointcut.class, this, UmaPackage.PROC_ASPECT__POINTCUT);
		}
		return pointcut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variant getContain() {
		if (contain != null && ((EObject) contain).eIsProxy()) {
			InternalEObject oldContain = (InternalEObject) contain;
			contain = (Variant) eResolveProxy(oldContain);
			if (contain != oldContain) {
				InternalEObject newContain = (InternalEObject) contain;
				NotificationChain msgs = oldContain.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROC_ASPECT__CONTAIN, null, null);
				if (newContain.eInternalContainer() == null) {
					msgs = newContain.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
							- UmaPackage.PROC_ASPECT__CONTAIN, null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROC_ASPECT__CONTAIN, oldContain,
							contain));
			}
		}
		return contain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variant basicGetContain() {
		return contain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContain(Variant newContain,
			NotificationChain msgs) {
		Variant oldContain = contain;
		contain = newContain;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.PROC_ASPECT__CONTAIN,
					oldContain, newContain);
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
	public void setContain(Variant newContain) {
		if (newContain != contain) {
			NotificationChain msgs = null;
			if (contain != null)
				msgs = ((InternalEObject) contain).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROC_ASPECT__CONTAIN, null, msgs);
			if (newContain != null)
				msgs = ((InternalEObject) newContain).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROC_ASPECT__CONTAIN, null, msgs);
			msgs = basicSetContain(newContain, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROC_ASPECT__CONTAIN, newContain, newContain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPoint getUtiliza() {
		if (utiliza != null && ((EObject) utiliza).eIsProxy()) {
			InternalEObject oldUtiliza = (InternalEObject) utiliza;
			utiliza = (VarPoint) eResolveProxy(oldUtiliza);
			if (utiliza != oldUtiliza) {
				InternalEObject newUtiliza = (InternalEObject) utiliza;
				NotificationChain msgs = oldUtiliza.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROC_ASPECT__UTILIZA, null, null);
				if (newUtiliza.eInternalContainer() == null) {
					msgs = newUtiliza.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
							- UmaPackage.PROC_ASPECT__UTILIZA, null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROC_ASPECT__UTILIZA, oldUtiliza,
							utiliza));
			}
		}
		return utiliza;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPoint basicGetUtiliza() {
		return utiliza;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUtiliza(VarPoint newUtiliza,
			NotificationChain msgs) {
		VarPoint oldUtiliza = utiliza;
		utiliza = newUtiliza;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.PROC_ASPECT__UTILIZA,
					oldUtiliza, newUtiliza);
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
	public void setUtiliza(VarPoint newUtiliza) {
		if (newUtiliza != utiliza) {
			NotificationChain msgs = null;
			if (utiliza != null)
				msgs = ((InternalEObject) utiliza).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROC_ASPECT__UTILIZA, null, msgs);
			if (newUtiliza != null)
				msgs = ((InternalEObject) newUtiliza).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROC_ASPECT__UTILIZA, null, msgs);
			msgs = basicSetUtiliza(newUtiliza, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROC_ASPECT__UTILIZA, newUtiliza, newUtiliza));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Artifact interpretation() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.PROC_ASPECT__ADVICE:
			return ((InternalEList) getAdvice()).basicRemove(otherEnd, msgs);
		case UmaPackage.PROC_ASPECT__POINTCUT:
			return ((InternalEList) getPointcut()).basicRemove(otherEnd, msgs);
		case UmaPackage.PROC_ASPECT__CONTAIN:
			return basicSetContain(null, msgs);
		case UmaPackage.PROC_ASPECT__UTILIZA:
			return basicSetUtiliza(null, msgs);
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
		case UmaPackage.PROC_ASPECT__TIPO_VARIACION:
			if (resolve)
				return getTipoVariacion();
			return basicGetTipoVariacion();
		case UmaPackage.PROC_ASPECT__ACTIVE:
			return isActive() ? Boolean.TRUE : Boolean.FALSE;
		case UmaPackage.PROC_ASPECT__ADVICE:
			return getAdvice();
		case UmaPackage.PROC_ASPECT__POINTCUT:
			return getPointcut();
		case UmaPackage.PROC_ASPECT__CONTAIN:
			if (resolve)
				return getContain();
			return basicGetContain();
		case UmaPackage.PROC_ASPECT__UTILIZA:
			if (resolve)
				return getUtiliza();
			return basicGetUtiliza();
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
		case UmaPackage.PROC_ASPECT__TIPO_VARIACION:
			setTipoVariacion((VariationType) newValue);
			return;
		case UmaPackage.PROC_ASPECT__ACTIVE:
			setActive(((Boolean) newValue).booleanValue());
			return;
		case UmaPackage.PROC_ASPECT__ADVICE:
			getAdvice().clear();
			getAdvice().addAll((Collection) newValue);
			return;
		case UmaPackage.PROC_ASPECT__POINTCUT:
			getPointcut().clear();
			getPointcut().addAll((Collection) newValue);
			return;
		case UmaPackage.PROC_ASPECT__CONTAIN:
			setContain((Variant) newValue);
			return;
		case UmaPackage.PROC_ASPECT__UTILIZA:
			setUtiliza((VarPoint) newValue);
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
		case UmaPackage.PROC_ASPECT__TIPO_VARIACION:
			setTipoVariacion((VariationType) null);
			return;
		case UmaPackage.PROC_ASPECT__ACTIVE:
			setActive(ACTIVE_EDEFAULT);
			return;
		case UmaPackage.PROC_ASPECT__ADVICE:
			getAdvice().clear();
			return;
		case UmaPackage.PROC_ASPECT__POINTCUT:
			getPointcut().clear();
			return;
		case UmaPackage.PROC_ASPECT__CONTAIN:
			setContain((Variant) null);
			return;
		case UmaPackage.PROC_ASPECT__UTILIZA:
			setUtiliza((VarPoint) null);
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
		case UmaPackage.PROC_ASPECT__TIPO_VARIACION:
			return tipoVariacion != null;
		case UmaPackage.PROC_ASPECT__ACTIVE:
			return active != ACTIVE_EDEFAULT;
		case UmaPackage.PROC_ASPECT__ADVICE:
			return advice != null && !advice.isEmpty();
		case UmaPackage.PROC_ASPECT__POINTCUT:
			return pointcut != null && !pointcut.isEmpty();
		case UmaPackage.PROC_ASPECT__CONTAIN:
			return contain != null;
		case UmaPackage.PROC_ASPECT__UTILIZA:
			return utiliza != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == Variation.class) {
			switch (derivedFeatureID) {
			case UmaPackage.PROC_ASPECT__TIPO_VARIACION:
				return UmaPackage.VARIATION__TIPO_VARIACION;
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
		if (baseClass == Variation.class) {
			switch (baseFeatureID) {
			case UmaPackage.VARIATION__TIPO_VARIACION:
				return UmaPackage.PROC_ASPECT__TIPO_VARIACION;
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
		result.append(" (active: "); //$NON-NLS-1$
		result.append(active);
		result.append(')');
		return result.toString();
	}

} //ProcAspectImpl