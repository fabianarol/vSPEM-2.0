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
package org.eclipse.epf.uma;

import org.eclipse.emf.common.util.EList;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tailored Process Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.TailoredProcessComponent#getAtravesde <em>Atravesde</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TailoredProcessComponent#getOccupations <em>Occupations</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TailoredProcessComponent#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TailoredProcessComponent#getTailoredProcess <em>Tailored Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessComponent()
 * @model
 * @generated
 */
public interface TailoredProcessComponent extends TailoredProcessesPackage,
		MethodUnit, MethodPlugin {
	/**
	 * Returns the value of the '<em><b>Atravesde</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Atravesde</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Atravesde</em>' containment reference.
	 * @see #setAtravesde(Variation)
	 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessComponent_Atravesde()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	Variation getAtravesde();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.TailoredProcessComponent#getAtravesde <em>Atravesde</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Atravesde</em>' containment reference.
	 * @see #getAtravesde()
	 * @generated
	 */
	void setAtravesde(Variation value);

	/**
	 * Returns the value of the '<em><b>Occupations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Occupation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occupations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occupations</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessComponent_Occupations()
	 * @model type="org.eclipse.epf.uma.Occupation" containment="true" resolveProxies="true"
	 * @generated
	 */
	List getOccupations();

	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcessComponentInterface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessComponent_Interfaces()
	 * @model type="org.eclipse.epf.uma.ProcessComponentInterface" required="true"
	 * @generated
	 */
	List getInterfaces();

	/**
	 * Returns the value of the '<em><b>Tailored Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tailored Process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tailored Process</em>' containment reference.
	 * @see #setTailoredProcess(org.eclipse.epf.uma.Process)
	 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessComponent_TailoredProcess()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	org.eclipse.epf.uma.Process getTailoredProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.TailoredProcessComponent#getTailoredProcess <em>Tailored Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tailored Process</em>' containment reference.
	 * @see #getTailoredProcess()
	 * @generated
	 */
	void setTailoredProcess(org.eclipse.epf.uma.Process value);

} // TailoredProcessComponent