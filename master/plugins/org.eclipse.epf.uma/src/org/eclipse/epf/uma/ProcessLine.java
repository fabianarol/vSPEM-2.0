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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Line</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessLine#getGenera <em>Genera</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessLine#getCompuestapor <em>Compuestapor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessLine()
 * @model
 * @generated
 */
public interface ProcessLine extends ProcessPackage {
	/**
	 * Returns the value of the '<em><b>Genera</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Genera</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Genera</em>' containment reference.
	 * @see #setGenera(TailoredProcessComponent)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLine_Genera()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	TailoredProcessComponent getGenera();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessLine#getGenera <em>Genera</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Genera</em>' containment reference.
	 * @see #getGenera()
	 * @generated
	 */
	void setGenera(TailoredProcessComponent value);

	/**
	 * Returns the value of the '<em><b>Compuestapor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compuestapor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compuestapor</em>' containment reference.
	 * @see #setCompuestapor(VarElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLine_Compuestapor()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	VarElement getCompuestapor();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessLine#getCompuestapor <em>Compuestapor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compuestapor</em>' containment reference.
	 * @see #getCompuestapor()
	 * @generated
	 */
	void setCompuestapor(VarElement value);

} // ProcessLine