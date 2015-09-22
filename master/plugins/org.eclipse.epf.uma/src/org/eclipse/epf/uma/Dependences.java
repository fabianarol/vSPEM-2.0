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
 * A representation of the model object '<em><b>Dependences</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Dependences#isIsInclusive <em>Is Inclusive</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getDependences()
 * @model
 * @extends IModelObject
 * @generated
 */
public interface Dependences extends IModelObject {
	/**
	 * Returns the value of the '<em><b>Is Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Inclusive</em>' attribute.
	 * @see #setIsInclusive(boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getDependences_IsInclusive()
	 * @model
	 * @generated
	 */
	boolean isIsInclusive();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Dependences#isIsInclusive <em>Is Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Inclusive</em>' attribute.
	 * @see #isIsInclusive()
	 * @generated
	 */
	void setIsInclusive(boolean value);

} // Dependences