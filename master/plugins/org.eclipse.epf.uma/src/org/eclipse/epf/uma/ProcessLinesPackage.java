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
 * A representation of the model object '<em><b>Process Lines Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessLinesPackage#getProcessLines <em>Process Lines</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessLinesPackage#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessLinesPackage()
 * @model
 * @generated
 */
public interface ProcessLinesPackage extends MethodPackage {
	/**
	 * Returns the value of the '<em><b>Process Lines</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcessLineElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Lines</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Lines</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLinesPackage_ProcessLines()
	 * @model type="org.eclipse.epf.uma.ProcessLineElement" containment="true" resolveProxies="true"
	 * @generated
	 */
	List getProcessLines();

	/**
	 * Returns the value of the '<em><b>Diagrams</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Diagram}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagrams</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagrams</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLinesPackage_Diagrams()
	 * @model type="org.eclipse.epf.uma.Diagram" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List getDiagrams();

} // ProcessLinesPackage