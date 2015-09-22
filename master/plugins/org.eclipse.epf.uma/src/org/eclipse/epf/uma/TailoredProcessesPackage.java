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
 * A representation of the model object '<em><b>Tailored Processes Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.TailoredProcessesPackage#getTailoredProcessElements <em>Tailored Process Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TailoredProcessesPackage#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessesPackage()
 * @model
 * @generated
 */
public interface TailoredProcessesPackage extends MethodPackage {
	/**
	 * Returns the value of the '<em><b>Tailored Process Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcessElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tailored Process Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tailored Process Elements</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessesPackage_TailoredProcessElements()
	 * @model type="org.eclipse.epf.uma.ProcessElement"
	 * @generated
	 */
	List getTailoredProcessElements();

	/**
	 * Returns the value of the '<em><b>Diagrams</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Diagram}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagrams</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagrams</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTailoredProcessesPackage_Diagrams()
	 * @model type="org.eclipse.epf.uma.Diagram"
	 * @generated
	 */
	List getDiagrams();

} // TailoredProcessesPackage