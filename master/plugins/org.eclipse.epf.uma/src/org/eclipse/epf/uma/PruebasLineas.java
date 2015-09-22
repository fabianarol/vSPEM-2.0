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
 * A representation of the model object '<em><b>Pruebas Lineas</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.PruebasLineas#getSubPruebasLineas <em>Sub Pruebas Lineas</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getPruebasLineas()
 * @model
 * @generated
 */
public interface PruebasLineas extends ContentCategory {
	/**
	 * Returns the value of the '<em><b>Sub Pruebas Lineas</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.PruebasLineas}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Pruebas Lineas</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Pruebas Lineas</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getPruebasLineas_SubPruebasLineas()
	 * @model type="org.eclipse.epf.uma.PruebasLineas" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List getSubPruebasLineas();

} // PruebasLineas