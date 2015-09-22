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
 * A representation of the model object '<em><b>Var Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.VarElement#getVpName <em>Vp Name</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.VarElement#getVId <em>VId</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.VarElement#getPathIcon <em>Path Icon</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.VarElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.VarElement#getClient <em>Client</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.VarElement#getSupplier <em>Supplier</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getVarElement()
 * @model abstract="true"
 * @generated
 */
public interface VarElement extends Variability {
	/**
	 * Returns the value of the '<em><b>Vp Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vp Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vp Name</em>' attribute.
	 * @see #setVpName(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getVarElement_VpName()
	 * @model
	 * @generated
	 */
	String getVpName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.VarElement#getVpName <em>Vp Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vp Name</em>' attribute.
	 * @see #getVpName()
	 * @generated
	 */
	void setVpName(String value);

	/**
	 * Returns the value of the '<em><b>VId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>VId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>VId</em>' attribute.
	 * @see #setVId(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getVarElement_VId()
	 * @model
	 * @generated
	 */
	String getVId();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.VarElement#getVId <em>VId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VId</em>' attribute.
	 * @see #getVId()
	 * @generated
	 */
	void setVId(String value);

	/**
	 * Returns the value of the '<em><b>Path Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Icon</em>' attribute.
	 * @see #setPathIcon(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getVarElement_PathIcon()
	 * @model
	 * @generated
	 */
	String getPathIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.VarElement#getPathIcon <em>Path Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Icon</em>' attribute.
	 * @see #getPathIcon()
	 * @generated
	 */
	void setPathIcon(String value);

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
	 * @see org.eclipse.epf.uma.UmaPackage#getVarElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.VarElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Client</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Dependences}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getVarElement_Client()
	 * @model type="org.eclipse.epf.uma.Dependences" containment="true" resolveProxies="true"
	 * @generated
	 */
	List getClient();

	/**
	 * Returns the value of the '<em><b>Supplier</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Dependences}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supplier</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supplier</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getVarElement_Supplier()
	 * @model type="org.eclipse.epf.uma.Dependences" containment="true" resolveProxies="true"
	 * @generated
	 */
	List getSupplier();

} // VarElement