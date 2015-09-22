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
import java.util.Date;
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.ProcessComponentInterface;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Variation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tailored Process Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getChangeDate <em>Change Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getCopyrightStatement <em>Copyright Statement</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getUserChangeable <em>User Changeable</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getMethodPackages <em>Method Packages</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getBases <em>Bases</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getAtravesde <em>Atravesde</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getOccupations <em>Occupations</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TailoredProcessComponentImpl#getTailoredProcess <em>Tailored Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TailoredProcessComponentImpl extends TailoredProcessesPackageImpl
		implements TailoredProcessComponent {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The default value of the '{@link #getAuthors() <em>Authors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthors()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHORS_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getAuthors() <em>Authors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthors()
	 * @generated
	 * @ordered
	 */
	protected String authors = AUTHORS_EDEFAULT;

	/**
	 * The default value of the '{@link #getChangeDate() <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date CHANGE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChangeDate() <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDate()
	 * @generated
	 * @ordered
	 */
	protected Date changeDate = CHANGE_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getChangeDescription() <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANGE_DESCRIPTION_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getChangeDescription() <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDescription()
	 * @generated
	 * @ordered
	 */
	protected String changeDescription = CHANGE_DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCopyrightStatement() <em>Copyright Statement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCopyrightStatement()
	 * @generated
	 * @ordered
	 */
	protected SupportingMaterial copyrightStatement = null;

	/**
	 * The default value of the '{@link #getUserChangeable() <em>User Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserChangeable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean USER_CHANGEABLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getUserChangeable() <em>User Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserChangeable()
	 * @generated
	 * @ordered
	 */
	protected Boolean userChangeable = USER_CHANGEABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMethodPackages() <em>Method Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodPackages()
	 * @generated
	 * @ordered
	 */
	protected EList methodPackages = null;

	/**
	 * The cached value of the '{@link #getBases() <em>Bases</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBases()
	 * @generated
	 * @ordered
	 */
	protected EList bases = null;

	/**
	 * The cached value of the '{@link #getAtravesde() <em>Atravesde</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtravesde()
	 * @generated
	 * @ordered
	 */
	protected Variation atravesde = null;

	/**
	 * The cached value of the '{@link #getOccupations() <em>Occupations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccupations()
	 * @generated
	 * @ordered
	 */
	protected EList occupations = null;

	/**
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList interfaces = null;

	/**
	 * The cached value of the '{@link #getTailoredProcess() <em>Tailored Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTailoredProcess()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.epf.uma.Process tailoredProcess = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TailoredProcessComponentImpl() {
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
		return UmaPackage.Literals.TAILORED_PROCESS_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthors(String newAuthors) {
		String oldAuthors = authors;
		authors = newAuthors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS, oldAuthors,
					authors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getChangeDate() {
		return changeDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeDate(Date newChangeDate) {
		Date oldChangeDate = changeDate;
		changeDate = newChangeDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE,
					oldChangeDate, changeDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChangeDescription() {
		return changeDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeDescription(String newChangeDescription) {
		String oldChangeDescription = changeDescription;
		changeDescription = newChangeDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION,
					oldChangeDescription, changeDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION, oldVersion,
					version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportingMaterial getCopyrightStatement() {
		if (copyrightStatement != null
				&& ((EObject) copyrightStatement).eIsProxy()) {
			InternalEObject oldCopyrightStatement = (InternalEObject) copyrightStatement;
			copyrightStatement = (SupportingMaterial) eResolveProxy(oldCopyrightStatement);
			if (copyrightStatement != oldCopyrightStatement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT,
							oldCopyrightStatement, copyrightStatement));
			}
		}
		return copyrightStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportingMaterial basicGetCopyrightStatement() {
		return copyrightStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCopyrightStatement(SupportingMaterial newCopyrightStatement) {
		SupportingMaterial oldCopyrightStatement = copyrightStatement;
		copyrightStatement = newCopyrightStatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT,
					oldCopyrightStatement, copyrightStatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getUserChangeable() {
		return userChangeable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserChangeable(Boolean newUserChangeable) {
		Boolean oldUserChangeable = userChangeable;
		userChangeable = newUserChangeable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE,
					oldUserChangeable, userChangeable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getMethodPackages() {
		if (methodPackages == null) {
			methodPackages = new EObjectContainmentEList.Resolving(
					MethodPackage.class, this,
					UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES);
		}
		return methodPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getBases() {
		if (bases == null) {
			bases = new EObjectResolvingEList(MethodPlugin.class, this,
					UmaPackage.TAILORED_PROCESS_COMPONENT__BASES);
		}
		return bases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variation getAtravesde() {
		if (atravesde != null && ((EObject) atravesde).eIsProxy()) {
			InternalEObject oldAtravesde = (InternalEObject) atravesde;
			atravesde = (Variation) eResolveProxy(oldAtravesde);
			if (atravesde != oldAtravesde) {
				InternalEObject newAtravesde = (InternalEObject) atravesde;
				NotificationChain msgs = oldAtravesde
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
								null, null);
				if (newAtravesde.eInternalContainer() == null) {
					msgs = newAtravesde
							.eInverseAdd(
									this,
									EOPPOSITE_FEATURE_BASE
											- UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
									null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
							oldAtravesde, atravesde));
			}
		}
		return atravesde;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variation basicGetAtravesde() {
		return atravesde;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAtravesde(Variation newAtravesde,
			NotificationChain msgs) {
		Variation oldAtravesde = atravesde;
		atravesde = newAtravesde;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
					oldAtravesde, newAtravesde);
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
	public void setAtravesde(Variation newAtravesde) {
		if (newAtravesde != atravesde) {
			NotificationChain msgs = null;
			if (atravesde != null)
				msgs = ((InternalEObject) atravesde)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
								null, msgs);
			if (newAtravesde != null)
				msgs = ((InternalEObject) newAtravesde)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
								null, msgs);
			msgs = basicSetAtravesde(newAtravesde, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE,
					newAtravesde, newAtravesde));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getOccupations() {
		if (occupations == null) {
			occupations = new EObjectContainmentEList.Resolving(
					Occupation.class, this,
					UmaPackage.TAILORED_PROCESS_COMPONENT__OCCUPATIONS);
		}
		return occupations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getInterfaces() {
		if (interfaces == null) {
			interfaces = new EObjectResolvingEList(
					ProcessComponentInterface.class, this,
					UmaPackage.TAILORED_PROCESS_COMPONENT__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Process getTailoredProcess() {
		if (tailoredProcess != null && ((EObject) tailoredProcess).eIsProxy()) {
			InternalEObject oldTailoredProcess = (InternalEObject) tailoredProcess;
			tailoredProcess = (org.eclipse.epf.uma.Process) eResolveProxy(oldTailoredProcess);
			if (tailoredProcess != oldTailoredProcess) {
				InternalEObject newTailoredProcess = (InternalEObject) tailoredProcess;
				NotificationChain msgs = oldTailoredProcess
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
								null, null);
				if (newTailoredProcess.eInternalContainer() == null) {
					msgs = newTailoredProcess
							.eInverseAdd(
									this,
									EOPPOSITE_FEATURE_BASE
											- UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
									null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
							oldTailoredProcess, tailoredProcess));
			}
		}
		return tailoredProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Process basicGetTailoredProcess() {
		return tailoredProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTailoredProcess(
			org.eclipse.epf.uma.Process newTailoredProcess,
			NotificationChain msgs) {
		org.eclipse.epf.uma.Process oldTailoredProcess = tailoredProcess;
		tailoredProcess = newTailoredProcess;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
					oldTailoredProcess, newTailoredProcess);
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
	public void setTailoredProcess(
			org.eclipse.epf.uma.Process newTailoredProcess) {
		if (newTailoredProcess != tailoredProcess) {
			NotificationChain msgs = null;
			if (tailoredProcess != null)
				msgs = ((InternalEObject) tailoredProcess)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
								null, msgs);
			if (newTailoredProcess != null)
				msgs = ((InternalEObject) newTailoredProcess)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
								null, msgs);
			msgs = basicSetTailoredProcess(newTailoredProcess, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS,
					newTailoredProcess, newTailoredProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES:
			return ((InternalEList) getMethodPackages()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE:
			return basicSetAtravesde(null, msgs);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__OCCUPATIONS:
			return ((InternalEList) getOccupations()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS:
			return basicSetTailoredProcess(null, msgs);
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
		case UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS:
			return getAuthors();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE:
			return getChangeDate();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			return getChangeDescription();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION:
			return getVersion();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			if (resolve)
				return getCopyrightStatement();
			return basicGetCopyrightStatement();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE:
			return getUserChangeable();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES:
			return getMethodPackages();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__BASES:
			return getBases();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE:
			if (resolve)
				return getAtravesde();
			return basicGetAtravesde();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__OCCUPATIONS:
			return getOccupations();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__INTERFACES:
			return getInterfaces();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS:
			if (resolve)
				return getTailoredProcess();
			return basicGetTailoredProcess();
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
		case UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS:
			setAuthors((String) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE:
			setChangeDate((Date) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			setChangeDescription((String) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION:
			setVersion((String) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			setCopyrightStatement((SupportingMaterial) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE:
			setUserChangeable((Boolean) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES:
			getMethodPackages().clear();
			getMethodPackages().addAll((Collection) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__BASES:
			getBases().clear();
			getBases().addAll((Collection) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE:
			setAtravesde((Variation) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__OCCUPATIONS:
			getOccupations().clear();
			getOccupations().addAll((Collection) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__INTERFACES:
			getInterfaces().clear();
			getInterfaces().addAll((Collection) newValue);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS:
			setTailoredProcess((org.eclipse.epf.uma.Process) newValue);
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
		case UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS:
			setAuthors(AUTHORS_EDEFAULT);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE:
			setChangeDate(CHANGE_DATE_EDEFAULT);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			setChangeDescription(CHANGE_DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			setCopyrightStatement((SupportingMaterial) null);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE:
			setUserChangeable(USER_CHANGEABLE_EDEFAULT);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES:
			getMethodPackages().clear();
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__BASES:
			getBases().clear();
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE:
			setAtravesde((Variation) null);
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__OCCUPATIONS:
			getOccupations().clear();
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__INTERFACES:
			getInterfaces().clear();
			return;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS:
			setTailoredProcess((org.eclipse.epf.uma.Process) null);
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
		case UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS:
			return AUTHORS_EDEFAULT == null ? authors != null
					: !AUTHORS_EDEFAULT.equals(authors);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE:
			return CHANGE_DATE_EDEFAULT == null ? changeDate != null
					: !CHANGE_DATE_EDEFAULT.equals(changeDate);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			return CHANGE_DESCRIPTION_EDEFAULT == null ? changeDescription != null
					: !CHANGE_DESCRIPTION_EDEFAULT.equals(changeDescription);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION:
			return VERSION_EDEFAULT == null ? version != null
					: !VERSION_EDEFAULT.equals(version);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			return copyrightStatement != null;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE:
			return USER_CHANGEABLE_EDEFAULT == null ? userChangeable != null
					: !USER_CHANGEABLE_EDEFAULT.equals(userChangeable);
		case UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES:
			return methodPackages != null && !methodPackages.isEmpty();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__BASES:
			return bases != null && !bases.isEmpty();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__ATRAVESDE:
			return atravesde != null;
		case UmaPackage.TAILORED_PROCESS_COMPONENT__OCCUPATIONS:
			return occupations != null && !occupations.isEmpty();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__INTERFACES:
			return interfaces != null && !interfaces.isEmpty();
		case UmaPackage.TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS:
			return tailoredProcess != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == MethodUnit.class) {
			switch (derivedFeatureID) {
			case UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS:
				return UmaPackage.METHOD_UNIT__AUTHORS;
			case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE:
				return UmaPackage.METHOD_UNIT__CHANGE_DATE;
			case UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION:
				return UmaPackage.METHOD_UNIT__CHANGE_DESCRIPTION;
			case UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION:
				return UmaPackage.METHOD_UNIT__VERSION;
			case UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
				return UmaPackage.METHOD_UNIT__COPYRIGHT_STATEMENT;
			default:
				return -1;
			}
		}
		if (baseClass == MethodPlugin.class) {
			switch (derivedFeatureID) {
			case UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE:
				return UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE;
			case UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES:
				return UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES;
			case UmaPackage.TAILORED_PROCESS_COMPONENT__BASES:
				return UmaPackage.METHOD_PLUGIN__BASES;
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
		if (baseClass == MethodUnit.class) {
			switch (baseFeatureID) {
			case UmaPackage.METHOD_UNIT__AUTHORS:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__AUTHORS;
			case UmaPackage.METHOD_UNIT__CHANGE_DATE:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DATE;
			case UmaPackage.METHOD_UNIT__CHANGE_DESCRIPTION:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__CHANGE_DESCRIPTION;
			case UmaPackage.METHOD_UNIT__VERSION:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__VERSION;
			case UmaPackage.METHOD_UNIT__COPYRIGHT_STATEMENT:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__COPYRIGHT_STATEMENT;
			default:
				return -1;
			}
		}
		if (baseClass == MethodPlugin.class) {
			switch (baseFeatureID) {
			case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__USER_CHANGEABLE;
			case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__METHOD_PACKAGES;
			case UmaPackage.METHOD_PLUGIN__BASES:
				return UmaPackage.TAILORED_PROCESS_COMPONENT__BASES;
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
		result.append(" (authors: "); //$NON-NLS-1$
		result.append(authors);
		result.append(", changeDate: "); //$NON-NLS-1$
		result.append(changeDate);
		result.append(", changeDescription: "); //$NON-NLS-1$
		result.append(changeDescription);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", userChangeable: "); //$NON-NLS-1$
		result.append(userChangeable);
		result.append(')');
		return result.toString();
	}

} //TailoredProcessComponentImpl