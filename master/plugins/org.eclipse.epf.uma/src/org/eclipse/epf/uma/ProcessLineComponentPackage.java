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
 * A representation of the model object '<em><b>Process Line Component Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessLineComponentPackage#getProcessLineComponent <em>Process Line Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessLineComponentPackage()
 * @model
 * @generated
 */
public interface ProcessLineComponentPackage extends MethodPackage {
	/**
	 * Returns the value of the '<em><b>Process Line Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Line Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Line Component</em>' reference.
	 * @see #setProcessLineComponent(ProcessLineComponent)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLineComponentPackage_ProcessLineComponent()
	 * @model required="true"
	 * @generated
	 */
	ProcessLineComponent getProcessLineComponent();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessLineComponentPackage#getProcessLineComponent <em>Process Line Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Line Component</em>' reference.
	 * @see #getProcessLineComponent()
	 * @generated
	 */
	void setProcessLineComponent(ProcessLineComponent value);

} // ProcessLineComponentPackage