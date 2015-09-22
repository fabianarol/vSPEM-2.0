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
 * A representation of the model object '<em><b>Proc Aspect</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcAspect#isActive <em>Active</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcAspect#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcAspect#getPointcut <em>Pointcut</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcAspect#getContain <em>Contain</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcAspect#getUtiliza <em>Utiliza</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcAspect()
 * @model
 * @generated
 */
public interface ProcAspect extends VarElement, Variation {
	/**
	 * Returns the value of the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active</em>' attribute.
	 * @see #setActive(boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcAspect_Active()
	 * @model
	 * @generated
	 */
	boolean isActive();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcAspect#isActive <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active</em>' attribute.
	 * @see #isActive()
	 * @generated
	 */
	void setActive(boolean value);

	/**
	 * Returns the value of the '<em><b>Advice</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcAdvice}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Advice</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Advice</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcAspect_Advice()
	 * @model type="org.eclipse.epf.uma.ProcAdvice" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	List getAdvice();

	/**
	 * Returns the value of the '<em><b>Pointcut</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcPointcut}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pointcut</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pointcut</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcAspect_Pointcut()
	 * @model type="org.eclipse.epf.uma.ProcPointcut" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	List getPointcut();

	/**
	 * Returns the value of the '<em><b>Contain</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contain</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contain</em>' containment reference.
	 * @see #setContain(Variant)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcAspect_Contain()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	Variant getContain();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcAspect#getContain <em>Contain</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contain</em>' containment reference.
	 * @see #getContain()
	 * @generated
	 */
	void setContain(Variant value);

	/**
	 * Returns the value of the '<em><b>Utiliza</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Utiliza</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Utiliza</em>' containment reference.
	 * @see #setUtiliza(VarPoint)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcAspect_Utiliza()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	VarPoint getUtiliza();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcAspect#getUtiliza <em>Utiliza</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Utiliza</em>' containment reference.
	 * @see #getUtiliza()
	 * @generated
	 */
	void setUtiliza(VarPoint value);

} // ProcAspect