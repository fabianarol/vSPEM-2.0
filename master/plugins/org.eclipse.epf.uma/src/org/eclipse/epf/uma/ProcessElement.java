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
 * A representation of the model object '<em><b>Process Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Process Element is a Describable Element that represents an abstract generalization for all elements defined in the Process package.
 * Process Elements represents Process specific elements that are supposed to be managed in Process Packages.  The separation of Process Element from Content Element allows to clearly distinguish between pure method content from content that is represented in processes. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessElement#getProcessLineName <em>Process Line Name</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessElement#getTailoredProcessName <em>Tailored Process Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessElement()
 * @model abstract="true"
 * @generated
 */
public interface ProcessElement extends DescribableElement {

	/**
	 * Returns the value of the '<em><b>Process Line Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Line Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Line Name</em>' attribute.
	 * @see #setProcessLineName(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessElement_ProcessLineName()
	 * @model
	 * @generated
	 */
	String getProcessLineName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessElement#getProcessLineName <em>Process Line Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Line Name</em>' attribute.
	 * @see #getProcessLineName()
	 * @generated
	 */
	void setProcessLineName(String value);

	/**
	 * Returns the value of the '<em><b>Tailored Process Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tailored Process Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tailored Process Name</em>' attribute.
	 * @see #setTailoredProcessName(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessElement_TailoredProcessName()
	 * @model
	 * @generated
	 */
	String getTailoredProcessName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessElement#getTailoredProcessName <em>Tailored Process Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tailored Process Name</em>' attribute.
	 * @see #getTailoredProcessName()
	 * @generated
	 */
	void setTailoredProcessName(String value);
} // ProcessElement
