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
 * A representation of the model object '<em><b>Breakdown Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Breakdown Element is an abstract generalization for any type of Method Element that is part of a breakdown structure.  It defines a set of properties available to all of its specializations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getIsPlanned <em>Is Planned</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getHasMultipleOccurrences <em>Has Multiple Occurrences</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getIsOptional <em>Is Optional</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getPresentedAfter <em>Presented After</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getPresentedBefore <em>Presented Before</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getPlanningData <em>Planning Data</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElement#getSuperActivities <em>Super Activities</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement()
 * @model abstract="true"
 * @generated
 */
public interface BreakdownElement extends ProcessElement {
	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Prefix represents an additional label that will be presented as a prefix to any Breakdown Element to indicate a user-defined sub-type for the element.  For example, if the process engineer would like to distinguish his Activities by 'Module' (as done in the IBM Rational Summit Ascendant Method), he can define a different prefix for every model to be used in addition to naming Activities, e.g. "SRA.Establish Requirements" with SRA indicating that this Activity belongs to the "Software Requirements Analysis" module.  Another common application for prefix is to qualify roles in Role Descriptors.  For example, "Customer.Architect" would define a "Customer" prefix for the Role Descriptor "Architect" expressing that this is an architect on the customer side and not the development team side.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_Prefix()
	 * @model default="" dataType="org.eclipse.epf.uma.String"
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Is Planned</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A key application for Development Processes expressed with Breakdown structures is to generate a project plan from it.  A process as defined in UMA (cf. with Sections XX and 5.2) is a multi-dimensional structure defining what work is being performed at what time by which roles using which input and producing what outputs.  A project plan as it is represented in project planning tools such as IBM Rational Portfolio Manager or Microsoft Project normally does not need all this information and is normally limited to just representing a subset.  For example, a typical MS Project plan only represents the work breakdown consisting of Tasks and Activities (sometimes referred to as summary tasks).  It does not show the input and output Work Products for a Task, but it can show which roles shall be staffed for performing the Task.  However, such role allocation need to be replaced with concrete resources when instantiating the plan for a concrete project.  Sometimes project plans can then again be organized differently by organizing work by deliverables in which Work Products are mapped to the plan's summary tasks and Task that have these work products as output mapped below such as summary task.  Therefore, a process can make recommendations about which elements to include and which to exclude when generating a plan.  When the isPlanned attribute is set to False for an instance of a Breakdown Element, then this element shall not be not included when a concrete project plan is being generated from the breakdown structure that contains this element.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Planned</em>' attribute.
	 * @see #setIsPlanned(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_IsPlanned()
	 * @model default="true"
	 * @generated
	 */
	Boolean getIsPlanned();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getIsPlanned <em>Is Planned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Planned</em>' attribute.
	 * @see #getIsPlanned()
	 * @generated
	 */
	void setIsPlanned(Boolean value);

	/**
	 * Returns the value of the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Just as the isPlanned attribute the hasMultipleOccurrences attribute has an impact on generating plans from a Process.  When this attribute is set to True for a Breakdown Element then it will typically occur multiple times within the same Activity.  For example, a Task such as "Detail Use Case" would be performed for every use case identified for a particular Iteration or Activity.  Generating a plan would list one Task instance per use case.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has Multiple Occurrences</em>' attribute.
	 * @see #setHasMultipleOccurrences(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_HasMultipleOccurrences()
	 * @model default="false"
	 * @generated
	 */
	Boolean getHasMultipleOccurrences();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getHasMultipleOccurrences <em>Has Multiple Occurrences</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Multiple Occurrences</em>' attribute.
	 * @see #getHasMultipleOccurrences()
	 * @generated
	 */
	void setHasMultipleOccurrences(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Optional</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The isOptional attribute indicates that the Breakdown Element describes work, a work result, or even work resources, which inclusion is not mandatory when performing a project that is planned based on a process containing this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Optional</em>' attribute.
	 * @see #setIsOptional(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_IsOptional()
	 * @model default="false"
	 * @generated
	 */
	Boolean getIsOptional();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getIsOptional <em>Is Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Optional</em>' attribute.
	 * @see #getIsOptional()
	 * @generated
	 */
	void setIsOptional(Boolean value);

	/**
	 * Returns the value of the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presented After</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presented After</em>' reference.
	 * @see #setPresentedAfter(BreakdownElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_PresentedAfter()
	 * @model
	 * @generated
	 */
	BreakdownElement getPresentedAfter();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getPresentedAfter <em>Presented After</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presented After</em>' reference.
	 * @see #getPresentedAfter()
	 * @generated
	 */
	void setPresentedAfter(BreakdownElement value);

	/**
	 * Returns the value of the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presented Before</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presented Before</em>' reference.
	 * @see #setPresentedBefore(BreakdownElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_PresentedBefore()
	 * @model
	 * @generated
	 */
	BreakdownElement getPresentedBefore();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getPresentedBefore <em>Presented Before</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presented Before</em>' reference.
	 * @see #getPresentedBefore()
	 * @generated
	 */
	void setPresentedBefore(BreakdownElement value);

	/**
	 * Returns the value of the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Planning Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Planning Data</em>' containment reference.
	 * @see #setPlanningData(PlanningData)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_PlanningData()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	PlanningData getPlanningData();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getPlanningData <em>Planning Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Planning Data</em>' containment reference.
	 * @see #getPlanningData()
	 * @generated
	 */
	void setPlanningData(PlanningData value);

	/**
	 * Returns the value of the '<em><b>Super Activities</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.Activity#getBreakdownElements <em>Breakdown Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Activities</em>' reference.
	 * @see #setSuperActivities(Activity)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElement_SuperActivities()
	 * @see org.eclipse.epf.uma.Activity#getBreakdownElements
	 * @model opposite="breakdownElements" required="true"
	 * @generated
	 */
	Activity getSuperActivities();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElement#getSuperActivities <em>Super Activities</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Activities</em>' reference.
	 * @see #getSuperActivities()
	 * @generated
	 */
	void setSuperActivities(Activity value);

} // BreakdownElement
