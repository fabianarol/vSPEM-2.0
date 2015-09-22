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
 * A representation of the model object '<em><b>Process Line Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessLineComponent#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessLineComponent#getProcessLine <em>Process Line</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessLineComponent#getCoreProcess <em>Core Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessLineComponent()
 * @model
 * @generated
 */
public interface ProcessLineComponent extends ProcessLinesPackage, MethodUnit,
		MethodPlugin {
	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcessLineComponentInterface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLineComponent_Interfaces()
	 * @model type="org.eclipse.epf.uma.ProcessLineComponentInterface" required="true" ordered="false"
	 * @generated
	 */
	List getInterfaces();

	/**
	 * Returns the value of the '<em><b>Process Line</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Line</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Line</em>' containment reference.
	 * @see #setProcessLine(org.eclipse.epf.uma.Process)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLineComponent_ProcessLine()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	org.eclipse.epf.uma.Process getProcessLine();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessLineComponent#getProcessLine <em>Process Line</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Line</em>' containment reference.
	 * @see #getProcessLine()
	 * @generated
	 */
	void setProcessLine(org.eclipse.epf.uma.Process value);

	/**
	 * Returns the value of the '<em><b>Core Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Process</em>' containment reference.
	 * @see #setCoreProcess(CoreProcessPackage)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessLineComponent_CoreProcess()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	CoreProcessPackage getCoreProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessLineComponent#getCoreProcess <em>Core Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core Process</em>' containment reference.
	 * @see #getCoreProcess()
	 * @generated
	 */
	void setCoreProcess(CoreProcessPackage value);

} // ProcessLineComponent