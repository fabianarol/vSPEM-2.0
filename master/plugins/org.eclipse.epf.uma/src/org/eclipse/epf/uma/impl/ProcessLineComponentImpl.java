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
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.CoreProcess;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLineComponentInterface;
import org.eclipse.epf.uma.ProcessLineElement;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Line Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getChangeDate <em>Change Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getCopyrightStatement <em>Copyright Statement</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getUserChangeable <em>User Changeable</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getMethodPackages <em>Method Packages</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getBases <em>Bases</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getProcessLine <em>Process Line</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessLineComponentImpl#getCoreProcess <em>Core Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessLineComponentImpl extends ProcessLinesPackageImpl implements
		ProcessLineComponent {
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
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList interfaces = null;

	/**
	 * The cached value of the '{@link #getProcessLine() <em>Process Line</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessLine()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.epf.uma.Process processLine = null;

	/**
	 * The cached value of the '{@link #getCoreProcess() <em>Core Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreProcess()
	 * @generated
	 * @ordered
	 */
	protected CoreProcessPackage coreProcess = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessLineComponentImpl() {
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
		return UmaPackage.Literals.PROCESS_LINE_COMPONENT;
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
					UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS, oldAuthors,
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
					UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE,
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
					UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION,
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
					UmaPackage.PROCESS_LINE_COMPONENT__VERSION, oldVersion,
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
							UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT,
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
					UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT,
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
					UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE,
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
					UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES);
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
					UmaPackage.PROCESS_LINE_COMPONENT__BASES);
		}
		return bases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getInterfaces() {
		if (interfaces == null) {
			interfaces = new EObjectResolvingEList(
					ProcessLineComponentInterface.class, this,
					UmaPackage.PROCESS_LINE_COMPONENT__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Process getProcessLine() {
		if (processLine != null && ((EObject) processLine).eIsProxy()) {
			InternalEObject oldProcessLine = (InternalEObject) processLine;
			processLine = (org.eclipse.epf.uma.Process) eResolveProxy(oldProcessLine);
			if (processLine != oldProcessLine) {
				InternalEObject newProcessLine = (InternalEObject) processLine;
				NotificationChain msgs = oldProcessLine
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
								null, null);
				if (newProcessLine.eInternalContainer() == null) {
					msgs = newProcessLine
							.eInverseAdd(
									this,
									EOPPOSITE_FEATURE_BASE
											- UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
									null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
							oldProcessLine, processLine));
			}
		}
		return processLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Process basicGetProcessLine() {
		return processLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessLine(
			org.eclipse.epf.uma.Process newProcessLine, NotificationChain msgs) {
		org.eclipse.epf.uma.Process oldProcessLine = processLine;
		processLine = newProcessLine;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
					oldProcessLine, newProcessLine);
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
	public void setProcessLine(org.eclipse.epf.uma.Process newProcessLine) {
		if (newProcessLine != processLine) {
			NotificationChain msgs = null;
			if (processLine != null)
				msgs = ((InternalEObject) processLine)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
								null, msgs);
			if (newProcessLine != null)
				msgs = ((InternalEObject) newProcessLine)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
								null, msgs);
			msgs = basicSetProcessLine(newProcessLine, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE,
					newProcessLine, newProcessLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreProcessPackage getCoreProcess() {
		if (coreProcess != null && ((EObject) coreProcess).eIsProxy()) {
			InternalEObject oldCoreProcess = (InternalEObject) coreProcess;
			coreProcess = (CoreProcessPackage) eResolveProxy(oldCoreProcess);
			if (coreProcess != oldCoreProcess) {
				InternalEObject newCoreProcess = (InternalEObject) coreProcess;
				NotificationChain msgs = oldCoreProcess
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
								null, null);
				if (newCoreProcess.eInternalContainer() == null) {
					msgs = newCoreProcess
							.eInverseAdd(
									this,
									EOPPOSITE_FEATURE_BASE
											- UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
									null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
							oldCoreProcess, coreProcess));
			}
		}
		return coreProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreProcessPackage basicGetCoreProcess() {
		return coreProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCoreProcess(
			CoreProcessPackage newCoreProcess, NotificationChain msgs) {
		CoreProcessPackage oldCoreProcess = coreProcess;
		coreProcess = newCoreProcess;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
					oldCoreProcess, newCoreProcess);
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
	public void setCoreProcess(CoreProcessPackage newCoreProcess) {
		if (newCoreProcess != coreProcess) {
			NotificationChain msgs = null;
			if (coreProcess != null)
				msgs = ((InternalEObject) coreProcess)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
								null, msgs);
			if (newCoreProcess != null)
				msgs = ((InternalEObject) newCoreProcess)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
								null, msgs);
			msgs = basicSetCoreProcess(newCoreProcess, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS,
					newCoreProcess, newCoreProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES:
			return ((InternalEList) getMethodPackages()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE:
			return basicSetProcessLine(null, msgs);
		case UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS:
			return basicSetCoreProcess(null, msgs);
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
		case UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS:
			return getAuthors();
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE:
			return getChangeDate();
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION:
			return getChangeDescription();
		case UmaPackage.PROCESS_LINE_COMPONENT__VERSION:
			return getVersion();
		case UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT:
			if (resolve)
				return getCopyrightStatement();
			return basicGetCopyrightStatement();
		case UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE:
			return getUserChangeable();
		case UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES:
			return getMethodPackages();
		case UmaPackage.PROCESS_LINE_COMPONENT__BASES:
			return getBases();
		case UmaPackage.PROCESS_LINE_COMPONENT__INTERFACES:
			return getInterfaces();
		case UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE:
			if (resolve)
				return getProcessLine();
			return basicGetProcessLine();
		case UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS:
			if (resolve)
				return getCoreProcess();
			return basicGetCoreProcess();
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
		case UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS:
			setAuthors((String) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE:
			setChangeDate((Date) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION:
			setChangeDescription((String) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__VERSION:
			setVersion((String) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT:
			setCopyrightStatement((SupportingMaterial) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE:
			setUserChangeable((Boolean) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES:
			getMethodPackages().clear();
			getMethodPackages().addAll((Collection) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__BASES:
			getBases().clear();
			getBases().addAll((Collection) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__INTERFACES:
			getInterfaces().clear();
			getInterfaces().addAll((Collection) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE:
			setProcessLine((org.eclipse.epf.uma.Process) newValue);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS:
			setCoreProcess((CoreProcessPackage) newValue);
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
		case UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS:
			setAuthors(AUTHORS_EDEFAULT);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE:
			setChangeDate(CHANGE_DATE_EDEFAULT);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION:
			setChangeDescription(CHANGE_DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT:
			setCopyrightStatement((SupportingMaterial) null);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE:
			setUserChangeable(USER_CHANGEABLE_EDEFAULT);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES:
			getMethodPackages().clear();
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__BASES:
			getBases().clear();
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__INTERFACES:
			getInterfaces().clear();
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE:
			setProcessLine((org.eclipse.epf.uma.Process) null);
			return;
		case UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS:
			setCoreProcess((CoreProcessPackage) null);
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
		case UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS:
			return AUTHORS_EDEFAULT == null ? authors != null
					: !AUTHORS_EDEFAULT.equals(authors);
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE:
			return CHANGE_DATE_EDEFAULT == null ? changeDate != null
					: !CHANGE_DATE_EDEFAULT.equals(changeDate);
		case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION:
			return CHANGE_DESCRIPTION_EDEFAULT == null ? changeDescription != null
					: !CHANGE_DESCRIPTION_EDEFAULT.equals(changeDescription);
		case UmaPackage.PROCESS_LINE_COMPONENT__VERSION:
			return VERSION_EDEFAULT == null ? version != null
					: !VERSION_EDEFAULT.equals(version);
		case UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT:
			return copyrightStatement != null;
		case UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE:
			return USER_CHANGEABLE_EDEFAULT == null ? userChangeable != null
					: !USER_CHANGEABLE_EDEFAULT.equals(userChangeable);
		case UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES:
			return methodPackages != null && !methodPackages.isEmpty();
		case UmaPackage.PROCESS_LINE_COMPONENT__BASES:
			return bases != null && !bases.isEmpty();
		case UmaPackage.PROCESS_LINE_COMPONENT__INTERFACES:
			return interfaces != null && !interfaces.isEmpty();
		case UmaPackage.PROCESS_LINE_COMPONENT__PROCESS_LINE:
			return processLine != null;
		case UmaPackage.PROCESS_LINE_COMPONENT__CORE_PROCESS:
			return coreProcess != null;
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
			case UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS:
				return UmaPackage.METHOD_UNIT__AUTHORS;
			case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE:
				return UmaPackage.METHOD_UNIT__CHANGE_DATE;
			case UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION:
				return UmaPackage.METHOD_UNIT__CHANGE_DESCRIPTION;
			case UmaPackage.PROCESS_LINE_COMPONENT__VERSION:
				return UmaPackage.METHOD_UNIT__VERSION;
			case UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT:
				return UmaPackage.METHOD_UNIT__COPYRIGHT_STATEMENT;
			default:
				return -1;
			}
		}
		if (baseClass == MethodPlugin.class) {
			switch (derivedFeatureID) {
			case UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE:
				return UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE;
			case UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES:
				return UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES;
			case UmaPackage.PROCESS_LINE_COMPONENT__BASES:
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
				return UmaPackage.PROCESS_LINE_COMPONENT__AUTHORS;
			case UmaPackage.METHOD_UNIT__CHANGE_DATE:
				return UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DATE;
			case UmaPackage.METHOD_UNIT__CHANGE_DESCRIPTION:
				return UmaPackage.PROCESS_LINE_COMPONENT__CHANGE_DESCRIPTION;
			case UmaPackage.METHOD_UNIT__VERSION:
				return UmaPackage.PROCESS_LINE_COMPONENT__VERSION;
			case UmaPackage.METHOD_UNIT__COPYRIGHT_STATEMENT:
				return UmaPackage.PROCESS_LINE_COMPONENT__COPYRIGHT_STATEMENT;
			default:
				return -1;
			}
		}
		if (baseClass == MethodPlugin.class) {
			switch (baseFeatureID) {
			case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
				return UmaPackage.PROCESS_LINE_COMPONENT__USER_CHANGEABLE;
			case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
				return UmaPackage.PROCESS_LINE_COMPONENT__METHOD_PACKAGES;
			case UmaPackage.METHOD_PLUGIN__BASES:
				return UmaPackage.PROCESS_LINE_COMPONENT__BASES;
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

	@Override
	public MethodPackage getParentPackage() {
		// TODO Auto-generated method stub
		return null;
	}

} //ProcessLineComponentImpl