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
 * A representation of the model object '<em><b>Work Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Work Definition is an abstract Method Element that generalizes all descriptions of work within the Unified Method Architecture.  This package introduces two concrete types of Work Definitions: Task and Step.  Work Definitions can contain sets of pre- and post-conditions defining constraints that need to be valid before the described work can begin or before it can be declared as finished.  Note that general ownedRules can be used to define additional constraints and rules for Work Definitions.
 * Work Definitions represent behavioral descriptions for doing work.  These behavioral descriptions are not bound to one specific classifier, but represent an arbitrary definition of work.  For example, a Work Definition could represent work that is being performed by a specific Role (e.g. a Role performing a specific Task or Steps of a Task), by many Roles working in close collaboration (many Roles all working together on the same interdisciplinary Task), or complex work that is performed throughout the lifecycle (e.g. a process defining a breakdown structure for organizing larger composite units of work performed by many Roles working in collaboration).
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.WorkDefinition#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.WorkDefinition#getPostcondition <em>Postcondition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getWorkDefinition()
 * @model abstract="true"
 * @generated
 */
public interface WorkDefinition extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' containment reference.
	 * @see #setPrecondition(Constraint)
	 * @see org.eclipse.epf.uma.UmaPackage#getWorkDefinition_Precondition()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	Constraint getPrecondition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.WorkDefinition#getPrecondition <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' containment reference.
	 * @see #getPrecondition()
	 * @generated
	 */
	void setPrecondition(Constraint value);

	/**
	 * Returns the value of the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcondition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcondition</em>' containment reference.
	 * @see #setPostcondition(Constraint)
	 * @see org.eclipse.epf.uma.UmaPackage#getWorkDefinition_Postcondition()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	Constraint getPostcondition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.WorkDefinition#getPostcondition <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcondition</em>' containment reference.
	 * @see #getPostcondition()
	 * @generated
	 */
	void setPostcondition(Constraint value);

} // WorkDefinition
