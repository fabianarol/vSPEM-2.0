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

import org.eclipse.epf.uma.ecore.IModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Proc Pointcut</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcPointcut#getSelfcomposition <em>Selfcomposition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcPointcut#getGenera <em>Genera</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcPointcut()
 * @model
 * @extends IModelObject
 * @generated
 */
public interface ProcPointcut extends IModelObject {
	/**
	 * Returns the value of the '<em><b>Selfcomposition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selfcomposition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selfcomposition</em>' reference.
	 * @see #setSelfcomposition(ProcPointcut)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcPointcut_Selfcomposition()
	 * @model
	 * @generated
	 */
	ProcPointcut getSelfcomposition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcPointcut#getSelfcomposition <em>Selfcomposition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selfcomposition</em>' reference.
	 * @see #getSelfcomposition()
	 * @generated
	 */
	void setSelfcomposition(ProcPointcut value);

	/**
	 * Returns the value of the '<em><b>Genera</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Genera</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Genera</em>' reference.
	 * @see #setGenera(Occupation)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcPointcut_Genera()
	 * @model
	 * @generated
	 */
	Occupation getGenera();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcPointcut#getGenera <em>Genera</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Genera</em>' reference.
	 * @see #getGenera()
	 * @generated
	 */
	void setGenera(Occupation value);

} // ProcPointcut