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
 * A representation of the model object '<em><b>Occupation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Occupation#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Occupation#isIsObjective <em>Is Objective</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Occupation#getOcupadopor <em>Ocupadopor</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Occupation#getEsocupado <em>Esocupado</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getOccupation()
 * @model
 * @generated
 */
public interface Occupation extends Variation {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getOccupation_Description()
	 * @model dataType="org.eclipse.epf.uma.String"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Occupation#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Is Objective</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Objective</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Objective</em>' attribute.
	 * @see #setIsObjective(boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getOccupation_IsObjective()
	 * @model
	 * @generated
	 */
	boolean isIsObjective();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Occupation#isIsObjective <em>Is Objective</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Objective</em>' attribute.
	 * @see #isIsObjective()
	 * @generated
	 */
	void setIsObjective(boolean value);

	/**
	 * Returns the value of the '<em><b>Ocupadopor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocupadopor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocupadopor</em>' reference.
	 * @see #setOcupadopor(Variant)
	 * @see org.eclipse.epf.uma.UmaPackage#getOccupation_Ocupadopor()
	 * @model
	 * @generated
	 */
	Variant getOcupadopor();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Occupation#getOcupadopor <em>Ocupadopor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocupadopor</em>' reference.
	 * @see #getOcupadopor()
	 * @generated
	 */
	void setOcupadopor(Variant value);

	/**
	 * Returns the value of the '<em><b>Esocupado</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Esocupado</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Esocupado</em>' reference.
	 * @see #setEsocupado(VarPoint)
	 * @see org.eclipse.epf.uma.UmaPackage#getOccupation_Esocupado()
	 * @model
	 * @generated
	 */
	VarPoint getEsocupado();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Occupation#getEsocupado <em>Esocupado</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Esocupado</em>' reference.
	 * @see #getEsocupado()
	 * @generated
	 */
	void setEsocupado(VarPoint value);

} // Occupation