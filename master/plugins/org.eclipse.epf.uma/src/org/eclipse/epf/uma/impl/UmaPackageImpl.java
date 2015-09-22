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
package org.eclipse.epf.uma.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ActivityDescription;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ArtifactDescription;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Classifier;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.CoreProcess;
import org.eclipse.epf.uma.CoreSemanticModelBridge;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DeliverableDescription;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DeliveryProcessDescription;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.DescriptorDescription;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.DiagramLink;
import org.eclipse.epf.uma.Dimension;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.Ellipse;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.GraphElement;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.GraphicPrimitive;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.GuidanceDescription;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Image;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.LeafElement;
import org.eclipse.epf.uma.LineProcess;
import org.eclipse.epf.uma.Mandatory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Namespace;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Optative;
import org.eclipse.epf.uma.Optional;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.PackageableElement;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.PlanningData;
import org.eclipse.epf.uma.Point;
import org.eclipse.epf.uma.Polyline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.PracticeDescription;
import org.eclipse.epf.uma.ProcAdvice;
import org.eclipse.epf.uma.ProcAspect;
import org.eclipse.epf.uma.ProcPointcut;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessComponentDescriptor;
import org.eclipse.epf.uma.ProcessComponentInterface;
import org.eclipse.epf.uma.ProcessDescription;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessFamily;
import org.eclipse.epf.uma.ProcessLine;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLineComponentInterface;
import org.eclipse.epf.uma.ProcessLineComponentPackage;
import org.eclipse.epf.uma.ProcessLineInstance;
import org.eclipse.epf.uma.ProcessLineElement;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.ProcessPlanningTemplate;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.PruebasLineas;
import org.eclipse.epf.uma.PseudoState;
import org.eclipse.epf.uma.PseudoStateKind;
import org.eclipse.epf.uma.Reference;
import org.eclipse.epf.uma.Region;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescription;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.SimpleSemanticModelElement;
import org.eclipse.epf.uma.State;
import org.eclipse.epf.uma.StateMachine;
import org.eclipse.epf.uma.Step;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.TailoredProcess;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.TailoredProcessesPackage;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescription;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.TextElement;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.Transition;
import org.eclipse.epf.uma.Type;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarActivity;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarIteration;
import org.eclipse.epf.uma.VarMilestone;
import org.eclipse.epf.uma.VarPhase;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.VarPointsPackage;
import org.eclipse.epf.uma.VarRoleDescriptor;
import org.eclipse.epf.uma.VarTaskDescriptor;
import org.eclipse.epf.uma.VarTeamProfile;
import org.eclipse.epf.uma.VarWorkProductDescriptor;
import org.eclipse.epf.uma.Variability;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariantsListPackage;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.Variation;
import org.eclipse.epf.uma.VariationType;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.Vertex;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkDefinition;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescription;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.WorkProductType;

import org.eclipse.epf.uma.vActivity;
import org.eclipse.epf.uma.vIteration;
import org.eclipse.epf.uma.vPhase;
import org.eclipse.epf.uma.varP2varP;
import org.eclipse.epf.uma.variant2varP;
import org.eclipse.epf.uma.variant2variant;
import org.eclipse.epf.uma.varp2variant;
import org.eclipse.epf.uma.vpActivity;
import org.eclipse.epf.uma.vpIteration;
import org.eclipse.epf.uma.vpMilestone;
import org.eclipse.epf.uma.vpPhase;
import org.eclipse.epf.uma.vpRoleDescriptor;
import org.eclipse.epf.uma.vpTaskDescriptor;
import org.eclipse.epf.uma.vpTeamProfile;
import org.eclipse.epf.uma.vpWorkProductDescriptor;
import org.eclipse.epf.uma.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UmaPackageImpl extends EPackageImpl implements UmaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namespaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodElementPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass describableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guidanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliverableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outcomeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliverableDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guidanceDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass practiceDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semanticModelBridgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleSemanticModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass umaSemanticModelBridgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coreSemanticModelBridgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leafElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicPrimitiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polylineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ellipseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workBreakdownElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakdownElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass milestoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass phaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass teamProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workOrderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass planningDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakdownElementDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliveryProcessDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptorDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conceptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checklistEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exampleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guidelineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass estimationConsiderationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass supportingMaterialEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolMentorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass whitepaperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass termDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass practiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reusableAssetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass regionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateMachineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pseudoStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disciplineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pruebasLineasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disciplineGroupingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleSetGroupingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliveryProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass capabilityPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processPlanningTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roadmapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processComponentInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processComponentDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodPluginEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processLinesPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processLineElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processLineComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processLineComponentPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processLineComponentInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coreProcessPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tailoredProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tailoredProcessesPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tailoredProcessComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tailoredCoreProcessPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variationsPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variantsPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varPointsPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lineProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variabilityElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processFamilyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodLibraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variabilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varPhaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varIterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varRoleDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varTaskDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varMilestoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varTeamProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varWorkProductDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass procAspectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variantsListPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass occupationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass procAdviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass procPointcutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependencesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variant2varPEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variant2variantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varp2variantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varP2varPEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processLineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass optativeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mandatoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass optionalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpIterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpRoleDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpTaskDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpMilestoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpTeamProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpWorkProductDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vIterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vPhaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpPhaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vpActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum workOrderTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pseudoStateKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum variabilityTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dateEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType unlimitedNaturalEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType setEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sequenceEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType floatEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.epf.uma.UmaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UmaPackageImpl() {
		super(eNS_URI, UmaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UmaPackage init() {
		if (isInited)
			return (UmaPackage) EPackage.Registry.INSTANCE
					.getEPackage(UmaPackage.eNS_URI);

		// Obtain or create and register package
		UmaPackageImpl theUmaPackage = (UmaPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(eNS_URI) instanceof UmaPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(eNS_URI)
				: new UmaPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theUmaPackage.createPackageContents();

		// Initialize created meta-data
		theUmaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUmaPackage.freeze();

		return theUmaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassifier() {
		return classifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute) namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageableElement() {
		return packageableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamespace() {
		return namespaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodElement() {
		return methodElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_Guid() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_BriefDescription() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_OwnedRules() {
		return (EReference) methodElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_MethodElementProperty() {
		return (EReference) methodElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_Suppressed() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_OrderingGuide() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_Body() {
		return (EAttribute) constraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodElementProperty() {
		return methodElementPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElementProperty_Value() {
		return (EAttribute) methodElementPropertyEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentElement() {
		return contentElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_SupportingMaterials() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_ConceptsAndPapers() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Checklists() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Guidelines() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Examples() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Assets() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_TermDefinition() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescribableElement() {
		return describableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_PresentationName() {
		return (EAttribute) describableElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescribableElement_Presentation() {
		return (EReference) describableElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Shapeicon() {
		return (EAttribute) describableElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Nodeicon() {
		return (EAttribute) describableElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentDescription() {
		return contentDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_MainDescription() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentDescription_Sections() {
		return (EReference) contentDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_ExternalId() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_KeyConsiderations() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSection() {
		return sectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_SectionName() {
		return (EAttribute) sectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_SectionDescription() {
		return (EAttribute) sectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSection_SubSections() {
		return (EReference) sectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSection_Predecessor() {
		return (EReference) sectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole() {
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_Modifies() {
		return (EReference) roleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_ResponsibleFor() {
		return (EReference) roleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProduct() {
		return workProductEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_Reports() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_Templates() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_ToolMentors() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_EstimationConsiderations() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_PerformedBy() {
		return (EReference) taskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_MandatoryInput() {
		return (EReference) taskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Output() {
		return (EReference) taskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_AdditionallyPerformedBy() {
		return (EReference) taskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_OptionalInput() {
		return (EReference) taskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Steps() {
		return (EReference) taskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_ToolMentors() {
		return (EReference) taskEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_EstimationConsiderations() {
		return (EReference) taskEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkDefinition() {
		return workDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkDefinition_Precondition() {
		return (EReference) workDefinitionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkDefinition_Postcondition() {
		return (EReference) workDefinitionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStep() {
		return stepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGuidance() {
		return guidanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_ContainerArtifact() {
		return (EReference) artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_ContainedArtifacts() {
		return (EReference) artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliverable() {
		return deliverableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeliverable_DeliveredWorkProducts() {
		return (EReference) deliverableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutcome() {
		return outcomeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodPackage() {
		return methodPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPackage_Global() {
		return (EAttribute) methodPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPackage_ReusedPackages() {
		return (EReference) methodPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPackage_ChildPackages() {
		return (EReference) methodPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentPackage() {
		return contentPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentPackage_ContentElements() {
		return (EReference) contentPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifactDescription() {
		return artifactDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_BriefOutline() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_RepresentationOptions() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_Representation() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_Notation() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProductDescription() {
		return workProductDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_Purpose() {
		return (EAttribute) workProductDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_ImpactOfNotHaving() {
		return (EAttribute) workProductDescriptionEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_ReasonsForNotNeeding() {
		return (EAttribute) workProductDescriptionEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliverableDescription() {
		return deliverableDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliverableDescription_ExternalDescription() {
		return (EAttribute) deliverableDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliverableDescription_PackagingGuidance() {
		return (EAttribute) deliverableDescriptionEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleDescription() {
		return roleDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_Skills() {
		return (EAttribute) roleDescriptionEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_AssignmentApproaches() {
		return (EAttribute) roleDescriptionEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_Synonyms() {
		return (EAttribute) roleDescriptionEClass.getEStructuralFeatures().get(
				2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskDescription() {
		return taskDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescription_Purpose() {
		return (EAttribute) taskDescriptionEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescription_Alternatives() {
		return (EAttribute) taskDescriptionEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGuidanceDescription() {
		return guidanceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGuidanceDescription_Attachments() {
		return (EAttribute) guidanceDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPracticeDescription() {
		return practiceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_AdditionalInfo() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Problem() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Background() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Goals() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Application() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_LevelsOfAdoption() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoint() {
		return pointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_X() {
		return (EAttribute) pointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_Y() {
		return (EAttribute) pointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphElement() {
		return graphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Position() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Contained() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Link() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Anchorage() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_SemanticModel() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramElement() {
		return diagramElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramElement_IsVisible() {
		return (EAttribute) diagramElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramElement_Container() {
		return (EReference) diagramElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramElement_Reference() {
		return (EReference) diagramElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramElement_Property() {
		return (EReference) diagramElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramLink() {
		return diagramLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramLink_Zoom() {
		return (EAttribute) diagramLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Viewport() {
		return (EReference) diagramLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_GraphElement() {
		return (EReference) diagramLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Diagram() {
		return (EReference) diagramLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphConnector() {
		return graphConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphConnector_GraphElement() {
		return (EReference) graphConnectorEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphConnector_GraphEdge() {
		return (EReference) graphConnectorEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemanticModelBridge() {
		return semanticModelBridgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSemanticModelBridge_Presentation() {
		return (EAttribute) semanticModelBridgeEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSemanticModelBridge_GraphElement() {
		return (EReference) semanticModelBridgeEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSemanticModelBridge_Diagram() {
		return (EReference) semanticModelBridgeEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDimension() {
		return dimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDimension_Width() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDimension_Height() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReference_IsIndividualRepresentation() {
		return (EAttribute) referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReference_Referenced() {
		return (EReference) referenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Key() {
		return (EAttribute) propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute) propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphEdge() {
		return graphEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphEdge_Anchor() {
		return (EReference) graphEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphEdge_Waypoints() {
		return (EReference) graphEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagram() {
		return diagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_Zoom() {
		return (EAttribute) diagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Viewpoint() {
		return (EReference) diagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_DiagramLink() {
		return (EReference) diagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Namespace() {
		return (EReference) diagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphNode() {
		return graphNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphNode_Size() {
		return (EReference) graphNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleSemanticModelElement() {
		return simpleSemanticModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSimpleSemanticModelElement_TypeInfo() {
		return (EAttribute) simpleSemanticModelElementEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUMASemanticModelBridge() {
		return umaSemanticModelBridgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUMASemanticModelBridge_Element() {
		return (EReference) umaSemanticModelBridgeEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoreSemanticModelBridge() {
		return coreSemanticModelBridgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoreSemanticModelBridge_Element() {
		return (EReference) coreSemanticModelBridgeEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeafElement() {
		return leafElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextElement() {
		return textElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextElement_Text() {
		return (EAttribute) textElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Uri() {
		return (EAttribute) imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_MimeType() {
		return (EAttribute) imageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicPrimitive() {
		return graphicPrimitiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolyline() {
		return polylineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPolyline_Closed() {
		return (EAttribute) polylineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPolyline_Waypoints() {
		return (EReference) polylineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEllipse() {
		return ellipseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_RadiusX() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_RadiusY() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_Rotation() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_StartAngle() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_EndAngle() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEllipse_Center() {
		return (EReference) ellipseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivity() {
		return activityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_BreakdownElements() {
		return (EReference) activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_Roadmaps() {
		return (EReference) activityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_SupportingMaterials() {
		return (EReference) activityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_Checklists() {
		return (EReference) activityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_Concepts() {
		return (EReference) activityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_Examples() {
		return (EReference) activityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_Guidelines() {
		return (EReference) activityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_ReusableAssets() {
		return (EReference) activityEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_IsEnactable() {
		return (EAttribute) activityEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkBreakdownElement() {
		return workBreakdownElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsRepeatable() {
		return (EAttribute) workBreakdownElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsOngoing() {
		return (EAttribute) workBreakdownElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsEventDriven() {
		return (EAttribute) workBreakdownElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkBreakdownElement_LinkToPredecessor() {
		return (EReference) workBreakdownElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakdownElement() {
		return breakdownElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Prefix() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_IsPlanned() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_HasMultipleOccurrences() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_IsOptional() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_PresentedAfter() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_PresentedBefore() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_PlanningData() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_SuperActivities() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMilestone() {
		return milestoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIteration() {
		return iterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPhase() {
		return phaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTeamProfile() {
		return teamProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTeamProfile_TeamRoles() {
		return (EReference) teamProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTeamProfile_SuperTeam() {
		return (EReference) teamProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTeamProfile_SubTeam() {
		return (EReference) teamProfileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleDescriptor() {
		return roleDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_Role() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_Modifies() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_ResponsibleFor() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkOrder() {
		return workOrderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkOrder_LinkType() {
		return (EAttribute) workOrderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkOrder_Pred() {
		return (EReference) workOrderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessElement() {
		return processElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessElement_ProcessLineName() {
		return (EAttribute) processElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessElement_TailoredProcessName() {
		return (EAttribute) processElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlanningData() {
		return planningDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_StartDate() {
		return (EAttribute) planningDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_FinishDate() {
		return (EAttribute) planningDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_Rank() {
		return (EAttribute) planningDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptor() {
		return descriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescriptor_IsSynchronizedWithSource() {
		return (EAttribute) descriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskDescriptor() {
		return taskDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_Task() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_AdditionallyPerformedBy() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_AssistedBy() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_ExternalInput() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_MandatoryInput() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_OptionalInput() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_Output() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_PerformedPrimarilyBy() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_SelectedSteps() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProductDescriptor() {
		return workProductDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ActivityEntryState() {
		return (EAttribute) workProductDescriptorEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ActivityExitState() {
		return (EAttribute) workProductDescriptorEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_WorkProduct() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_ImpactedBy() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_Impacts() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_DeliverableParts() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeRole() {
		return compositeRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeRole_AggregatedRoles() {
		return (EReference) compositeRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakdownElementDescription() {
		return breakdownElementDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElementDescription_UsageGuidance() {
		return (EAttribute) breakdownElementDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityDescription() {
		return activityDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_Purpose() {
		return (EAttribute) activityDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_Alternatives() {
		return (EAttribute) activityDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_HowtoStaff() {
		return (EAttribute) activityDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliveryProcessDescription() {
		return deliveryProcessDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_Scale() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_ProjectCharacteristics() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_RiskLevel() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_EstimatingTechnique() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_ProjectMemberExpertise() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_TypeOfContract() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessDescription() {
		return processDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDescription_Scope() {
		return (EAttribute) processDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDescription_UsageNotes() {
		return (EAttribute) processDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptorDescription() {
		return descriptorDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescriptorDescription_RefinedDescription() {
		return (EAttribute) descriptorDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcept() {
		return conceptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChecklist() {
		return checklistEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExample() {
		return exampleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGuideline() {
		return guidelineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEstimationConsiderations() {
		return estimationConsiderationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReport() {
		return reportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemplate() {
		return templateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSupportingMaterial() {
		return supportingMaterialEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getToolMentor() {
		return toolMentorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWhitepaper() {
		return whitepaperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTermDefinition() {
		return termDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPractice() {
		return practiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_SubPractices() {
		return (EReference) practiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_ContentReferences() {
		return (EReference) practiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_ActivityReferences() {
		return (EReference) practiceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReusableAsset() {
		return reusableAssetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_WorkProduct() {
		return (EReference) stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Region() {
		return (EReference) stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Submachine() {
		return (EReference) stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertex() {
		return vertexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_Container() {
		return (EReference) vertexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_Outgoing() {
		return (EReference) vertexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_Incoming() {
		return (EReference) vertexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRegion() {
		return regionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_Vertex() {
		return (EReference) regionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_Transition() {
		return (EReference) regionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_State() {
		return (EReference) regionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_StateMachine() {
		return (EReference) regionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateMachine() {
		return stateMachineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateMachine_Region() {
		return (EReference) stateMachineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_WorkDefinition() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Container() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Source() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Target() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPseudoState() {
		return pseudoStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiscipline() {
		return disciplineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_Tasks() {
		return (EReference) disciplineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_Subdiscipline() {
		return (EReference) disciplineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_ReferenceWorkflows() {
		return (EReference) disciplineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentCategory() {
		return contentCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleSet() {
		return roleSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleSet_Roles() {
		return (EReference) roleSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomain() {
		return domainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomain_WorkProducts() {
		return (EReference) domainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomain_Subdomains() {
		return (EReference) domainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPruebasLineas() {
		return pruebasLineasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPruebasLineas_SubPruebasLineas() {
		return (EReference) pruebasLineasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProductType() {
		return workProductTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductType_WorkProducts() {
		return (EReference) workProductTypeEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisciplineGrouping() {
		return disciplineGroupingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDisciplineGrouping_Disciplines() {
		return (EReference) disciplineGroupingEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTool() {
		return toolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTool_ToolMentors() {
		return (EReference) toolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleSetGrouping() {
		return roleSetGroupingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleSetGrouping_RoleSets() {
		return (EReference) roleSetGroupingEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomCategory() {
		return customCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomCategory_CategorizedElements() {
		return (EReference) customCategoryEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomCategory_SubCategories() {
		return (EReference) customCategoryEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliveryProcess() {
		return deliveryProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeliveryProcess_EducationMaterials() {
		return (EReference) deliveryProcessEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeliveryProcess_CommunicationsMaterials() {
		return (EReference) deliveryProcessEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcess() {
		return processEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_IncludesPatterns() {
		return (EReference) processEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_DefaultContext() {
		return (EReference) processEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_ValidContext() {
		return (EReference) processEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapabilityPattern() {
		return capabilityPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessPlanningTemplate() {
		return processPlanningTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPlanningTemplate_BasedOnProcesses() {
		return (EReference) processPlanningTemplateEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoadmap() {
		return roadmapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessComponent() {
		return processComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponent_Interfaces() {
		return (EReference) processComponentEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponent_Process() {
		return (EReference) processComponentEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessPackage() {
		return processPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPackage_ProcessElements() {
		return (EReference) processPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPackage_Diagrams() {
		return (EReference) processPackageEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessComponentInterface() {
		return processComponentInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentInterface_InterfaceSpecifications() {
		return (EReference) processComponentInterfaceEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentInterface_InterfaceIO() {
		return (EReference) processComponentInterfaceEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessComponentDescriptor() {
		return processComponentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentDescriptor_ProcessComponent() {
		return (EReference) processComponentDescriptorEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodPlugin() {
		return methodPluginEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPlugin_UserChangeable() {
		return (EAttribute) methodPluginEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPlugin_MethodPackages() {
		return (EReference) methodPluginEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPlugin_Bases() {
		return (EReference) methodPluginEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessLinesPackage() {
		return processLinesPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLinesPackage_ProcessLines() {
		return (EReference) processLinesPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLinesPackage_Diagrams() {
		return (EReference) processLinesPackageEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessLineElement() {
		return processLineElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessLineComponent() {
		return processLineComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLineComponent_Interfaces() {
		return (EReference) processLineComponentEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLineComponent_ProcessLine() {
		return (EReference) processLineComponentEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLineComponent_CoreProcess() {
		return (EReference) processLineComponentEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessLineComponentPackage() {
		return processLineComponentPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLineComponentPackage_ProcessLineComponent() {
		return (EReference) processLineComponentPackageEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessLineComponentInterface() {
		return processLineComponentInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLineComponentInterface_InterfaceSpecifications() {
		return (EReference) processLineComponentInterfaceEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLineComponentInterface_InterfaceIO() {
		return (EReference) processLineComponentInterfaceEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoreProcessPackage() {
		return coreProcessPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreProcessPackage_LineName() {
		return (EAttribute) coreProcessPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTailoredProcess() {
		return tailoredProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTailoredProcessesPackage() {
		return tailoredProcessesPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTailoredProcessesPackage_TailoredProcessElements() {
		return (EReference) tailoredProcessesPackageEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTailoredProcessesPackage_Diagrams() {
		return (EReference) tailoredProcessesPackageEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTailoredProcessComponent() {
		return tailoredProcessComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTailoredProcessComponent_Atravesde() {
		return (EReference) tailoredProcessComponentEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTailoredProcessComponent_Occupations() {
		return (EReference) tailoredProcessComponentEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTailoredProcessComponent_Interfaces() {
		return (EReference) tailoredProcessComponentEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTailoredProcessComponent_TailoredProcess() {
		return (EReference) tailoredProcessComponentEClass
				.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTailoredCoreProcessPackage() {
		return tailoredCoreProcessPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariationsPackage() {
		return variationsPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariationsPackage_Variations() {
		return (EReference) variationsPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariantsPackage() {
		return variantsPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariantsPackage_Variant() {
		return (EReference) variantsPackageEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarPointsPackage() {
		return varPointsPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVarPointsPackage_VarPoints() {
		return (EReference) varPointsPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLineProcess() {
		return lineProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariabilityElement() {
		return variabilityElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariabilityElement_VariabilityType() {
		return (EAttribute) variabilityElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariabilityElement_VariabilityBasedOnElement() {
		return (EReference) variabilityElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodUnit() {
		return methodUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_Authors() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_ChangeDate() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_ChangeDescription() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_Version() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodUnit_CopyrightStatement() {
		return (EReference) methodUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodConfiguration() {
		return methodConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_MethodPluginSelection() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_MethodPackageSelection() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_ProcessViews() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_DefaultView() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_BaseConfigurations() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_SubtractedCategory() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_AddedCategory() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessFamily() {
		return processFamilyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessFamily_DeliveryProcesses() {
		return (EReference) processFamilyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodLibrary() {
		return methodLibraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLibrary_MethodPlugins() {
		return (EReference) methodLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLibrary_PredefinedConfigurations() {
		return (EReference) methodLibraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariability() {
		return variabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarElement() {
		return varElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarElement_VpName() {
		return (EAttribute) varElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarElement_VId() {
		return (EAttribute) varElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarElement_PathIcon() {
		return (EAttribute) varElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarElement_Description() {
		return (EAttribute) varElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVarElement_Client() {
		return (EReference) varElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVarElement_Supplier() {
		return (EReference) varElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarPoint() {
		return varPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarPoint_IsImplicit() {
		return (EAttribute) varPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarPoint_IsOpen() {
		return (EAttribute) varPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarPoint_Min() {
		return (EAttribute) varPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarPoint_Max() {
		return (EAttribute) varPointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariant() {
		return variantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarActivity() {
		return varActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarPhase() {
		return varPhaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarIteration() {
		return varIterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarRoleDescriptor() {
		return varRoleDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarTaskDescriptor() {
		return varTaskDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarMilestone() {
		return varMilestoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarTeamProfile() {
		return varTeamProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarWorkProductDescriptor() {
		return varWorkProductDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcAspect() {
		return procAspectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcAspect_Active() {
		return (EAttribute) procAspectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcAspect_Advice() {
		return (EReference) procAspectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcAspect_Pointcut() {
		return (EReference) procAspectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcAspect_Contain() {
		return (EReference) procAspectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcAspect_Utiliza() {
		return (EReference) procAspectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariantsListPackage() {
		return variantsListPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariantsListPackage_Variants() {
		return (EReference) variantsListPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariation() {
		return variationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariation_TipoVariacion() {
		return (EReference) variationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOccupation() {
		return occupationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOccupation_Description() {
		return (EAttribute) occupationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOccupation_IsObjective() {
		return (EAttribute) occupationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOccupation_Ocupadopor() {
		return (EReference) occupationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOccupation_Esocupado() {
		return (EReference) occupationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcAdvice() {
		return procAdviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcAdvice_RunsBy() {
		return (EReference) procAdviceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcPointcut() {
		return procPointcutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcPointcut_Selfcomposition() {
		return (EReference) procPointcutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcPointcut_Genera() {
		return (EReference) procPointcutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependences() {
		return dependencesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDependences_IsInclusive() {
		return (EAttribute) dependencesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvariant2varP() {
		return variant2varPEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvariant2varP_Client() {
		return (EReference) variant2varPEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvariant2varP_Supplier() {
		return (EReference) variant2varPEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvariant2variant() {
		return variant2variantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvariant2variant_Client() {
		return (EReference) variant2variantEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvariant2variant_Supplier() {
		return (EReference) variant2variantEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvarp2variant() {
		return varp2variantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvarp2variant_Client() {
		return (EReference) varp2variantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvarp2variant_Supplier() {
		return (EReference) varp2variantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvarP2varP() {
		return varP2varPEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvarP2varP_Supplier() {
		return (EReference) varP2varPEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getvarP2varP_Client() {
		return (EReference) varP2varPEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessLine() {
		return processLineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLine_Genera() {
		return (EReference) processLineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessLine_Compuestapor() {
		return (EReference) processLineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariationType() {
		return variationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOptative() {
		return optativeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMandatory() {
		return mandatoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOptional() {
		return optionalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpIteration() {
		return vpIterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpRoleDescriptor() {
		return vpRoleDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpTaskDescriptor() {
		return vpTaskDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpMilestone() {
		return vpMilestoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpTeamProfile() {
		return vpTeamProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpWorkProductDescriptor() {
		return vpWorkProductDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvIteration() {
		return vIterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvPhase() {
		return vPhaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpPhase() {
		return vpPhaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvActivity() {
		return vActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getvpActivity() {
		return vpActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWorkOrderType() {
		return workOrderTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPseudoStateKind() {
		return pseudoStateKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVariabilityType() {
		return variabilityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDate() {
		return dateEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUri() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUnlimitedNatural() {
		return unlimitedNaturalEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getString() {
		return stringEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSet() {
		return setEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSequence() {
		return sequenceEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFloat() {
		return floatEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaFactory getUmaFactory() {
		return (UmaFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		classifierEClass = createEClass(CLASSIFIER);

		typeEClass = createEClass(TYPE);

		elementEClass = createEClass(ELEMENT);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		packageableElementEClass = createEClass(PACKAGEABLE_ELEMENT);

		packageEClass = createEClass(PACKAGE);

		namespaceEClass = createEClass(NAMESPACE);

		methodElementEClass = createEClass(METHOD_ELEMENT);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__GUID);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__BRIEF_DESCRIPTION);
		createEReference(methodElementEClass, METHOD_ELEMENT__OWNED_RULES);
		createEReference(methodElementEClass,
				METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__SUPPRESSED);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__ORDERING_GUIDE);

		constraintEClass = createEClass(CONSTRAINT);
		createEAttribute(constraintEClass, CONSTRAINT__BODY);

		methodElementPropertyEClass = createEClass(METHOD_ELEMENT_PROPERTY);
		createEAttribute(methodElementPropertyEClass,
				METHOD_ELEMENT_PROPERTY__VALUE);

		contentElementEClass = createEClass(CONTENT_ELEMENT);
		createEReference(contentElementEClass,
				CONTENT_ELEMENT__SUPPORTING_MATERIALS);
		createEReference(contentElementEClass,
				CONTENT_ELEMENT__CONCEPTS_AND_PAPERS);
		createEReference(contentElementEClass, CONTENT_ELEMENT__CHECKLISTS);
		createEReference(contentElementEClass, CONTENT_ELEMENT__GUIDELINES);
		createEReference(contentElementEClass, CONTENT_ELEMENT__EXAMPLES);
		createEReference(contentElementEClass, CONTENT_ELEMENT__ASSETS);
		createEReference(contentElementEClass, CONTENT_ELEMENT__TERM_DEFINITION);

		describableElementEClass = createEClass(DESCRIBABLE_ELEMENT);
		createEAttribute(describableElementEClass,
				DESCRIBABLE_ELEMENT__PRESENTATION_NAME);
		createEReference(describableElementEClass,
				DESCRIBABLE_ELEMENT__PRESENTATION);
		createEAttribute(describableElementEClass,
				DESCRIBABLE_ELEMENT__SHAPEICON);
		createEAttribute(describableElementEClass,
				DESCRIBABLE_ELEMENT__NODEICON);

		contentDescriptionEClass = createEClass(CONTENT_DESCRIPTION);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__MAIN_DESCRIPTION);
		createEReference(contentDescriptionEClass,
				CONTENT_DESCRIPTION__SECTIONS);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__EXTERNAL_ID);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__KEY_CONSIDERATIONS);

		sectionEClass = createEClass(SECTION);
		createEAttribute(sectionEClass, SECTION__SECTION_NAME);
		createEAttribute(sectionEClass, SECTION__SECTION_DESCRIPTION);
		createEReference(sectionEClass, SECTION__SUB_SECTIONS);
		createEReference(sectionEClass, SECTION__PREDECESSOR);

		roleEClass = createEClass(ROLE);
		createEReference(roleEClass, ROLE__MODIFIES);
		createEReference(roleEClass, ROLE__RESPONSIBLE_FOR);

		workProductEClass = createEClass(WORK_PRODUCT);
		createEReference(workProductEClass, WORK_PRODUCT__REPORTS);
		createEReference(workProductEClass, WORK_PRODUCT__TEMPLATES);
		createEReference(workProductEClass, WORK_PRODUCT__TOOL_MENTORS);
		createEReference(workProductEClass,
				WORK_PRODUCT__ESTIMATION_CONSIDERATIONS);

		taskEClass = createEClass(TASK);
		createEReference(taskEClass, TASK__PERFORMED_BY);
		createEReference(taskEClass, TASK__MANDATORY_INPUT);
		createEReference(taskEClass, TASK__OUTPUT);
		createEReference(taskEClass, TASK__ADDITIONALLY_PERFORMED_BY);
		createEReference(taskEClass, TASK__OPTIONAL_INPUT);
		createEReference(taskEClass, TASK__STEPS);
		createEReference(taskEClass, TASK__TOOL_MENTORS);
		createEReference(taskEClass, TASK__ESTIMATION_CONSIDERATIONS);

		workDefinitionEClass = createEClass(WORK_DEFINITION);
		createEReference(workDefinitionEClass, WORK_DEFINITION__PRECONDITION);
		createEReference(workDefinitionEClass, WORK_DEFINITION__POSTCONDITION);

		stepEClass = createEClass(STEP);

		guidanceEClass = createEClass(GUIDANCE);

		artifactEClass = createEClass(ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__CONTAINER_ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__CONTAINED_ARTIFACTS);

		deliverableEClass = createEClass(DELIVERABLE);
		createEReference(deliverableEClass,
				DELIVERABLE__DELIVERED_WORK_PRODUCTS);

		outcomeEClass = createEClass(OUTCOME);

		methodPackageEClass = createEClass(METHOD_PACKAGE);
		createEAttribute(methodPackageEClass, METHOD_PACKAGE__GLOBAL);
		createEReference(methodPackageEClass, METHOD_PACKAGE__REUSED_PACKAGES);
		createEReference(methodPackageEClass, METHOD_PACKAGE__CHILD_PACKAGES);

		contentPackageEClass = createEClass(CONTENT_PACKAGE);
		createEReference(contentPackageEClass,
				CONTENT_PACKAGE__CONTENT_ELEMENTS);

		artifactDescriptionEClass = createEClass(ARTIFACT_DESCRIPTION);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__BRIEF_OUTLINE);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__REPRESENTATION);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__NOTATION);

		workProductDescriptionEClass = createEClass(WORK_PRODUCT_DESCRIPTION);
		createEAttribute(workProductDescriptionEClass,
				WORK_PRODUCT_DESCRIPTION__PURPOSE);
		createEAttribute(workProductDescriptionEClass,
				WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING);
		createEAttribute(workProductDescriptionEClass,
				WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING);

		deliverableDescriptionEClass = createEClass(DELIVERABLE_DESCRIPTION);
		createEAttribute(deliverableDescriptionEClass,
				DELIVERABLE_DESCRIPTION__EXTERNAL_DESCRIPTION);
		createEAttribute(deliverableDescriptionEClass,
				DELIVERABLE_DESCRIPTION__PACKAGING_GUIDANCE);

		roleDescriptionEClass = createEClass(ROLE_DESCRIPTION);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__SKILLS);
		createEAttribute(roleDescriptionEClass,
				ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__SYNONYMS);

		taskDescriptionEClass = createEClass(TASK_DESCRIPTION);
		createEAttribute(taskDescriptionEClass, TASK_DESCRIPTION__PURPOSE);
		createEAttribute(taskDescriptionEClass, TASK_DESCRIPTION__ALTERNATIVES);

		guidanceDescriptionEClass = createEClass(GUIDANCE_DESCRIPTION);
		createEAttribute(guidanceDescriptionEClass,
				GUIDANCE_DESCRIPTION__ATTACHMENTS);

		practiceDescriptionEClass = createEClass(PRACTICE_DESCRIPTION);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__ADDITIONAL_INFO);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__PROBLEM);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__BACKGROUND);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__GOALS);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__APPLICATION);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION);

		pointEClass = createEClass(POINT);
		createEAttribute(pointEClass, POINT__X);
		createEAttribute(pointEClass, POINT__Y);

		graphElementEClass = createEClass(GRAPH_ELEMENT);
		createEReference(graphElementEClass, GRAPH_ELEMENT__POSITION);
		createEReference(graphElementEClass, GRAPH_ELEMENT__CONTAINED);
		createEReference(graphElementEClass, GRAPH_ELEMENT__LINK);
		createEReference(graphElementEClass, GRAPH_ELEMENT__ANCHORAGE);
		createEReference(graphElementEClass, GRAPH_ELEMENT__SEMANTIC_MODEL);

		diagramElementEClass = createEClass(DIAGRAM_ELEMENT);
		createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__IS_VISIBLE);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__CONTAINER);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__REFERENCE);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__PROPERTY);

		diagramLinkEClass = createEClass(DIAGRAM_LINK);
		createEAttribute(diagramLinkEClass, DIAGRAM_LINK__ZOOM);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__VIEWPORT);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__GRAPH_ELEMENT);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__DIAGRAM);

		graphConnectorEClass = createEClass(GRAPH_CONNECTOR);
		createEReference(graphConnectorEClass, GRAPH_CONNECTOR__GRAPH_ELEMENT);
		createEReference(graphConnectorEClass, GRAPH_CONNECTOR__GRAPH_EDGE);

		semanticModelBridgeEClass = createEClass(SEMANTIC_MODEL_BRIDGE);
		createEAttribute(semanticModelBridgeEClass,
				SEMANTIC_MODEL_BRIDGE__PRESENTATION);
		createEReference(semanticModelBridgeEClass,
				SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT);
		createEReference(semanticModelBridgeEClass,
				SEMANTIC_MODEL_BRIDGE__DIAGRAM);

		dimensionEClass = createEClass(DIMENSION);
		createEAttribute(dimensionEClass, DIMENSION__WIDTH);
		createEAttribute(dimensionEClass, DIMENSION__HEIGHT);

		referenceEClass = createEClass(REFERENCE);
		createEAttribute(referenceEClass,
				REFERENCE__IS_INDIVIDUAL_REPRESENTATION);
		createEReference(referenceEClass, REFERENCE__REFERENCED);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		graphEdgeEClass = createEClass(GRAPH_EDGE);
		createEReference(graphEdgeEClass, GRAPH_EDGE__ANCHOR);
		createEReference(graphEdgeEClass, GRAPH_EDGE__WAYPOINTS);

		diagramEClass = createEClass(DIAGRAM);
		createEAttribute(diagramEClass, DIAGRAM__ZOOM);
		createEReference(diagramEClass, DIAGRAM__VIEWPOINT);
		createEReference(diagramEClass, DIAGRAM__DIAGRAM_LINK);
		createEReference(diagramEClass, DIAGRAM__NAMESPACE);

		graphNodeEClass = createEClass(GRAPH_NODE);
		createEReference(graphNodeEClass, GRAPH_NODE__SIZE);

		simpleSemanticModelElementEClass = createEClass(SIMPLE_SEMANTIC_MODEL_ELEMENT);
		createEAttribute(simpleSemanticModelElementEClass,
				SIMPLE_SEMANTIC_MODEL_ELEMENT__TYPE_INFO);

		umaSemanticModelBridgeEClass = createEClass(UMA_SEMANTIC_MODEL_BRIDGE);
		createEReference(umaSemanticModelBridgeEClass,
				UMA_SEMANTIC_MODEL_BRIDGE__ELEMENT);

		coreSemanticModelBridgeEClass = createEClass(CORE_SEMANTIC_MODEL_BRIDGE);
		createEReference(coreSemanticModelBridgeEClass,
				CORE_SEMANTIC_MODEL_BRIDGE__ELEMENT);

		leafElementEClass = createEClass(LEAF_ELEMENT);

		textElementEClass = createEClass(TEXT_ELEMENT);
		createEAttribute(textElementEClass, TEXT_ELEMENT__TEXT);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__URI);
		createEAttribute(imageEClass, IMAGE__MIME_TYPE);

		graphicPrimitiveEClass = createEClass(GRAPHIC_PRIMITIVE);

		polylineEClass = createEClass(POLYLINE);
		createEAttribute(polylineEClass, POLYLINE__CLOSED);
		createEReference(polylineEClass, POLYLINE__WAYPOINTS);

		ellipseEClass = createEClass(ELLIPSE);
		createEAttribute(ellipseEClass, ELLIPSE__RADIUS_X);
		createEAttribute(ellipseEClass, ELLIPSE__RADIUS_Y);
		createEAttribute(ellipseEClass, ELLIPSE__ROTATION);
		createEAttribute(ellipseEClass, ELLIPSE__START_ANGLE);
		createEAttribute(ellipseEClass, ELLIPSE__END_ANGLE);
		createEReference(ellipseEClass, ELLIPSE__CENTER);

		workBreakdownElementEClass = createEClass(WORK_BREAKDOWN_ELEMENT);
		createEAttribute(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE);
		createEAttribute(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__IS_ONGOING);
		createEAttribute(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN);
		createEReference(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR);

		breakdownElementEClass = createEClass(BREAKDOWN_ELEMENT);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__PREFIX);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__IS_PLANNED);
		createEAttribute(breakdownElementEClass,
				BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__IS_OPTIONAL);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__PRESENTED_AFTER);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__PRESENTED_BEFORE);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__PLANNING_DATA);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__SUPER_ACTIVITIES);

		milestoneEClass = createEClass(MILESTONE);

		iterationEClass = createEClass(ITERATION);

		phaseEClass = createEClass(PHASE);

		teamProfileEClass = createEClass(TEAM_PROFILE);
		createEReference(teamProfileEClass, TEAM_PROFILE__TEAM_ROLES);
		createEReference(teamProfileEClass, TEAM_PROFILE__SUPER_TEAM);
		createEReference(teamProfileEClass, TEAM_PROFILE__SUB_TEAM);

		roleDescriptorEClass = createEClass(ROLE_DESCRIPTOR);
		createEReference(roleDescriptorEClass, ROLE_DESCRIPTOR__ROLE);
		createEReference(roleDescriptorEClass, ROLE_DESCRIPTOR__MODIFIES);
		createEReference(roleDescriptorEClass, ROLE_DESCRIPTOR__RESPONSIBLE_FOR);

		workOrderEClass = createEClass(WORK_ORDER);
		createEAttribute(workOrderEClass, WORK_ORDER__LINK_TYPE);
		createEReference(workOrderEClass, WORK_ORDER__PRED);

		planningDataEClass = createEClass(PLANNING_DATA);
		createEAttribute(planningDataEClass, PLANNING_DATA__START_DATE);
		createEAttribute(planningDataEClass, PLANNING_DATA__FINISH_DATE);
		createEAttribute(planningDataEClass, PLANNING_DATA__RANK);

		descriptorEClass = createEClass(DESCRIPTOR);
		createEAttribute(descriptorEClass,
				DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE);

		workProductDescriptorEClass = createEClass(WORK_PRODUCT_DESCRIPTOR);
		createEAttribute(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE);
		createEAttribute(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__IMPACTS);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS);

		taskDescriptorEClass = createEClass(TASK_DESCRIPTOR);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__TASK);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__ASSISTED_BY);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__EXTERNAL_INPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__MANDATORY_INPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__OPTIONAL_INPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__OUTPUT);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__SELECTED_STEPS);

		compositeRoleEClass = createEClass(COMPOSITE_ROLE);
		createEReference(compositeRoleEClass, COMPOSITE_ROLE__AGGREGATED_ROLES);

		breakdownElementDescriptionEClass = createEClass(BREAKDOWN_ELEMENT_DESCRIPTION);
		createEAttribute(breakdownElementDescriptionEClass,
				BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE);

		activityDescriptionEClass = createEClass(ACTIVITY_DESCRIPTION);
		createEAttribute(activityDescriptionEClass,
				ACTIVITY_DESCRIPTION__PURPOSE);
		createEAttribute(activityDescriptionEClass,
				ACTIVITY_DESCRIPTION__ALTERNATIVES);
		createEAttribute(activityDescriptionEClass,
				ACTIVITY_DESCRIPTION__HOWTO_STAFF);

		deliveryProcessDescriptionEClass = createEClass(DELIVERY_PROCESS_DESCRIPTION);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__SCALE);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT);

		processDescriptionEClass = createEClass(PROCESS_DESCRIPTION);
		createEAttribute(processDescriptionEClass, PROCESS_DESCRIPTION__SCOPE);
		createEAttribute(processDescriptionEClass,
				PROCESS_DESCRIPTION__USAGE_NOTES);

		descriptorDescriptionEClass = createEClass(DESCRIPTOR_DESCRIPTION);
		createEAttribute(descriptorDescriptionEClass,
				DESCRIPTOR_DESCRIPTION__REFINED_DESCRIPTION);

		conceptEClass = createEClass(CONCEPT);

		checklistEClass = createEClass(CHECKLIST);

		exampleEClass = createEClass(EXAMPLE);

		guidelineEClass = createEClass(GUIDELINE);

		reportEClass = createEClass(REPORT);

		templateEClass = createEClass(TEMPLATE);

		supportingMaterialEClass = createEClass(SUPPORTING_MATERIAL);

		toolMentorEClass = createEClass(TOOL_MENTOR);

		whitepaperEClass = createEClass(WHITEPAPER);

		termDefinitionEClass = createEClass(TERM_DEFINITION);

		practiceEClass = createEClass(PRACTICE);
		createEReference(practiceEClass, PRACTICE__SUB_PRACTICES);
		createEReference(practiceEClass, PRACTICE__CONTENT_REFERENCES);
		createEReference(practiceEClass, PRACTICE__ACTIVITY_REFERENCES);

		estimationConsiderationsEClass = createEClass(ESTIMATION_CONSIDERATIONS);

		reusableAssetEClass = createEClass(REUSABLE_ASSET);

		stateEClass = createEClass(STATE);
		createEReference(stateEClass, STATE__WORK_PRODUCT);
		createEReference(stateEClass, STATE__REGION);
		createEReference(stateEClass, STATE__SUBMACHINE);

		vertexEClass = createEClass(VERTEX);
		createEReference(vertexEClass, VERTEX__CONTAINER);
		createEReference(vertexEClass, VERTEX__OUTGOING);
		createEReference(vertexEClass, VERTEX__INCOMING);

		regionEClass = createEClass(REGION);
		createEReference(regionEClass, REGION__VERTEX);
		createEReference(regionEClass, REGION__TRANSITION);
		createEReference(regionEClass, REGION__STATE);
		createEReference(regionEClass, REGION__STATE_MACHINE);

		stateMachineEClass = createEClass(STATE_MACHINE);
		createEReference(stateMachineEClass, STATE_MACHINE__REGION);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__WORK_DEFINITION);
		createEReference(transitionEClass, TRANSITION__CONTAINER);
		createEReference(transitionEClass, TRANSITION__SOURCE);
		createEReference(transitionEClass, TRANSITION__TARGET);

		pseudoStateEClass = createEClass(PSEUDO_STATE);

		disciplineEClass = createEClass(DISCIPLINE);
		createEReference(disciplineEClass, DISCIPLINE__TASKS);
		createEReference(disciplineEClass, DISCIPLINE__SUBDISCIPLINE);
		createEReference(disciplineEClass, DISCIPLINE__REFERENCE_WORKFLOWS);

		contentCategoryEClass = createEClass(CONTENT_CATEGORY);

		roleSetEClass = createEClass(ROLE_SET);
		createEReference(roleSetEClass, ROLE_SET__ROLES);

		domainEClass = createEClass(DOMAIN);
		createEReference(domainEClass, DOMAIN__WORK_PRODUCTS);
		createEReference(domainEClass, DOMAIN__SUBDOMAINS);

		pruebasLineasEClass = createEClass(PRUEBAS_LINEAS);
		createEReference(pruebasLineasEClass,
				PRUEBAS_LINEAS__SUB_PRUEBAS_LINEAS);

		workProductTypeEClass = createEClass(WORK_PRODUCT_TYPE);
		createEReference(workProductTypeEClass,
				WORK_PRODUCT_TYPE__WORK_PRODUCTS);

		disciplineGroupingEClass = createEClass(DISCIPLINE_GROUPING);
		createEReference(disciplineGroupingEClass,
				DISCIPLINE_GROUPING__DISCIPLINES);

		toolEClass = createEClass(TOOL);
		createEReference(toolEClass, TOOL__TOOL_MENTORS);

		roleSetGroupingEClass = createEClass(ROLE_SET_GROUPING);
		createEReference(roleSetGroupingEClass, ROLE_SET_GROUPING__ROLE_SETS);

		customCategoryEClass = createEClass(CUSTOM_CATEGORY);
		createEReference(customCategoryEClass,
				CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS);
		createEReference(customCategoryEClass, CUSTOM_CATEGORY__SUB_CATEGORIES);

		deliveryProcessEClass = createEClass(DELIVERY_PROCESS);
		createEReference(deliveryProcessEClass,
				DELIVERY_PROCESS__EDUCATION_MATERIALS);
		createEReference(deliveryProcessEClass,
				DELIVERY_PROCESS__COMMUNICATIONS_MATERIALS);

		processEClass = createEClass(PROCESS);
		createEReference(processEClass, PROCESS__INCLUDES_PATTERNS);
		createEReference(processEClass, PROCESS__DEFAULT_CONTEXT);
		createEReference(processEClass, PROCESS__VALID_CONTEXT);

		capabilityPatternEClass = createEClass(CAPABILITY_PATTERN);

		processPlanningTemplateEClass = createEClass(PROCESS_PLANNING_TEMPLATE);
		createEReference(processPlanningTemplateEClass,
				PROCESS_PLANNING_TEMPLATE__BASED_ON_PROCESSES);

		roadmapEClass = createEClass(ROADMAP);

		processComponentEClass = createEClass(PROCESS_COMPONENT);
		createEReference(processComponentEClass, PROCESS_COMPONENT__INTERFACES);
		createEReference(processComponentEClass, PROCESS_COMPONENT__PROCESS);

		processPackageEClass = createEClass(PROCESS_PACKAGE);
		createEReference(processPackageEClass,
				PROCESS_PACKAGE__PROCESS_ELEMENTS);
		createEReference(processPackageEClass, PROCESS_PACKAGE__DIAGRAMS);

		processElementEClass = createEClass(PROCESS_ELEMENT);
		createEAttribute(processElementEClass,
				PROCESS_ELEMENT__PROCESS_LINE_NAME);
		createEAttribute(processElementEClass,
				PROCESS_ELEMENT__TAILORED_PROCESS_NAME);

		processComponentInterfaceEClass = createEClass(PROCESS_COMPONENT_INTERFACE);
		createEReference(processComponentInterfaceEClass,
				PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS);
		createEReference(processComponentInterfaceEClass,
				PROCESS_COMPONENT_INTERFACE__INTERFACE_IO);

		processComponentDescriptorEClass = createEClass(PROCESS_COMPONENT_DESCRIPTOR);
		createEReference(processComponentDescriptorEClass,
				PROCESS_COMPONENT_DESCRIPTOR__PROCESS_COMPONENT);

		methodPluginEClass = createEClass(METHOD_PLUGIN);
		createEAttribute(methodPluginEClass, METHOD_PLUGIN__USER_CHANGEABLE);
		createEReference(methodPluginEClass, METHOD_PLUGIN__METHOD_PACKAGES);
		createEReference(methodPluginEClass, METHOD_PLUGIN__BASES);

		lineProcessEClass = createEClass(LINE_PROCESS);

		processLinesPackageEClass = createEClass(PROCESS_LINES_PACKAGE);
		createEReference(processLinesPackageEClass,
				PROCESS_LINES_PACKAGE__PROCESS_LINES);
		createEReference(processLinesPackageEClass,
				PROCESS_LINES_PACKAGE__DIAGRAMS);

		processLineElementEClass = createEClass(PROCESS_LINE_ELEMENT);

		processLineComponentEClass = createEClass(PROCESS_LINE_COMPONENT);
		createEReference(processLineComponentEClass,
				PROCESS_LINE_COMPONENT__INTERFACES);
		createEReference(processLineComponentEClass,
				PROCESS_LINE_COMPONENT__PROCESS_LINE);
		createEReference(processLineComponentEClass,
				PROCESS_LINE_COMPONENT__CORE_PROCESS);

		processLineComponentPackageEClass = createEClass(PROCESS_LINE_COMPONENT_PACKAGE);
		createEReference(processLineComponentPackageEClass,
				PROCESS_LINE_COMPONENT_PACKAGE__PROCESS_LINE_COMPONENT);

		processLineComponentInterfaceEClass = createEClass(PROCESS_LINE_COMPONENT_INTERFACE);
		createEReference(processLineComponentInterfaceEClass,
				PROCESS_LINE_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS);
		createEReference(processLineComponentInterfaceEClass,
				PROCESS_LINE_COMPONENT_INTERFACE__INTERFACE_IO);

		coreProcessPackageEClass = createEClass(CORE_PROCESS_PACKAGE);
		createEAttribute(coreProcessPackageEClass,
				CORE_PROCESS_PACKAGE__LINE_NAME);

		tailoredProcessEClass = createEClass(TAILORED_PROCESS);

		tailoredProcessesPackageEClass = createEClass(TAILORED_PROCESSES_PACKAGE);
		createEReference(tailoredProcessesPackageEClass,
				TAILORED_PROCESSES_PACKAGE__TAILORED_PROCESS_ELEMENTS);
		createEReference(tailoredProcessesPackageEClass,
				TAILORED_PROCESSES_PACKAGE__DIAGRAMS);

		tailoredProcessComponentEClass = createEClass(TAILORED_PROCESS_COMPONENT);
		createEReference(tailoredProcessComponentEClass,
				TAILORED_PROCESS_COMPONENT__ATRAVESDE);
		createEReference(tailoredProcessComponentEClass,
				TAILORED_PROCESS_COMPONENT__OCCUPATIONS);
		createEReference(tailoredProcessComponentEClass,
				TAILORED_PROCESS_COMPONENT__INTERFACES);
		createEReference(tailoredProcessComponentEClass,
				TAILORED_PROCESS_COMPONENT__TAILORED_PROCESS);

		tailoredCoreProcessPackageEClass = createEClass(TAILORED_CORE_PROCESS_PACKAGE);

		variabilityElementEClass = createEClass(VARIABILITY_ELEMENT);
		createEAttribute(variabilityElementEClass,
				VARIABILITY_ELEMENT__VARIABILITY_TYPE);
		createEReference(variabilityElementEClass,
				VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT);

		methodUnitEClass = createEClass(METHOD_UNIT);
		createEAttribute(methodUnitEClass, METHOD_UNIT__AUTHORS);
		createEAttribute(methodUnitEClass, METHOD_UNIT__CHANGE_DATE);
		createEAttribute(methodUnitEClass, METHOD_UNIT__CHANGE_DESCRIPTION);
		createEAttribute(methodUnitEClass, METHOD_UNIT__VERSION);
		createEReference(methodUnitEClass, METHOD_UNIT__COPYRIGHT_STATEMENT);

		methodConfigurationEClass = createEClass(METHOD_CONFIGURATION);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__PROCESS_VIEWS);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__DEFAULT_VIEW);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__BASE_CONFIGURATIONS);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__SUBTRACTED_CATEGORY);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__ADDED_CATEGORY);

		processFamilyEClass = createEClass(PROCESS_FAMILY);
		createEReference(processFamilyEClass,
				PROCESS_FAMILY__DELIVERY_PROCESSES);

		methodLibraryEClass = createEClass(METHOD_LIBRARY);
		createEReference(methodLibraryEClass, METHOD_LIBRARY__METHOD_PLUGINS);
		createEReference(methodLibraryEClass,
				METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS);

		variabilityEClass = createEClass(VARIABILITY);

		varElementEClass = createEClass(VAR_ELEMENT);
		createEAttribute(varElementEClass, VAR_ELEMENT__VP_NAME);
		createEAttribute(varElementEClass, VAR_ELEMENT__VID);
		createEAttribute(varElementEClass, VAR_ELEMENT__PATH_ICON);
		createEAttribute(varElementEClass, VAR_ELEMENT__DESCRIPTION);
		createEReference(varElementEClass, VAR_ELEMENT__CLIENT);
		createEReference(varElementEClass, VAR_ELEMENT__SUPPLIER);

		varPointEClass = createEClass(VAR_POINT);
		createEAttribute(varPointEClass, VAR_POINT__IS_IMPLICIT);
		createEAttribute(varPointEClass, VAR_POINT__IS_OPEN);
		createEAttribute(varPointEClass, VAR_POINT__MIN);
		createEAttribute(varPointEClass, VAR_POINT__MAX);

		vpActivityEClass = createEClass(VP_ACTIVITY);

		vpPhaseEClass = createEClass(VP_PHASE);

		vpIterationEClass = createEClass(VP_ITERATION);

		vpRoleDescriptorEClass = createEClass(VP_ROLE_DESCRIPTOR);

		vpTaskDescriptorEClass = createEClass(VP_TASK_DESCRIPTOR);

		vpMilestoneEClass = createEClass(VP_MILESTONE);

		vpTeamProfileEClass = createEClass(VP_TEAM_PROFILE);

		vpWorkProductDescriptorEClass = createEClass(VP_WORK_PRODUCT_DESCRIPTOR);

		variantEClass = createEClass(VARIANT);

		varActivityEClass = createEClass(VAR_ACTIVITY);

		varPhaseEClass = createEClass(VAR_PHASE);

		varIterationEClass = createEClass(VAR_ITERATION);

		varRoleDescriptorEClass = createEClass(VAR_ROLE_DESCRIPTOR);

		varTaskDescriptorEClass = createEClass(VAR_TASK_DESCRIPTOR);

		varMilestoneEClass = createEClass(VAR_MILESTONE);

		varTeamProfileEClass = createEClass(VAR_TEAM_PROFILE);

		varWorkProductDescriptorEClass = createEClass(VAR_WORK_PRODUCT_DESCRIPTOR);

		activityEClass = createEClass(ACTIVITY);
		createEReference(activityEClass, ACTIVITY__BREAKDOWN_ELEMENTS);
		createEReference(activityEClass, ACTIVITY__ROADMAPS);
		createEReference(activityEClass, ACTIVITY__SUPPORTING_MATERIALS);
		createEReference(activityEClass, ACTIVITY__CHECKLISTS);
		createEReference(activityEClass, ACTIVITY__CONCEPTS);
		createEReference(activityEClass, ACTIVITY__EXAMPLES);
		createEReference(activityEClass, ACTIVITY__GUIDELINES);
		createEReference(activityEClass, ACTIVITY__REUSABLE_ASSETS);
		createEAttribute(activityEClass, ACTIVITY__IS_ENACTABLE);

		procAspectEClass = createEClass(PROC_ASPECT);
		createEAttribute(procAspectEClass, PROC_ASPECT__ACTIVE);
		createEReference(procAspectEClass, PROC_ASPECT__ADVICE);
		createEReference(procAspectEClass, PROC_ASPECT__POINTCUT);
		createEReference(procAspectEClass, PROC_ASPECT__CONTAIN);
		createEReference(procAspectEClass, PROC_ASPECT__UTILIZA);

		variantsListPackageEClass = createEClass(VARIANTS_LIST_PACKAGE);
		createEReference(variantsListPackageEClass,
				VARIANTS_LIST_PACKAGE__VARIANTS);

		variantsPackageEClass = createEClass(VARIANTS_PACKAGE);
		createEReference(variantsPackageEClass, VARIANTS_PACKAGE__VARIANT);

		varPointsPackageEClass = createEClass(VAR_POINTS_PACKAGE);
		createEReference(varPointsPackageEClass, VAR_POINTS_PACKAGE__VAR_POINTS);

		variationsPackageEClass = createEClass(VARIATIONS_PACKAGE);
		createEReference(variationsPackageEClass,
				VARIATIONS_PACKAGE__VARIATIONS);

		variationEClass = createEClass(VARIATION);
		createEReference(variationEClass, VARIATION__TIPO_VARIACION);

		variationTypeEClass = createEClass(VARIATION_TYPE);

		occupationEClass = createEClass(OCCUPATION);
		createEAttribute(occupationEClass, OCCUPATION__DESCRIPTION);
		createEAttribute(occupationEClass, OCCUPATION__IS_OBJECTIVE);
		createEReference(occupationEClass, OCCUPATION__OCUPADOPOR);
		createEReference(occupationEClass, OCCUPATION__ESOCUPADO);

		procAdviceEClass = createEClass(PROC_ADVICE);
		createEReference(procAdviceEClass, PROC_ADVICE__RUNS_BY);

		procPointcutEClass = createEClass(PROC_POINTCUT);
		createEReference(procPointcutEClass, PROC_POINTCUT__SELFCOMPOSITION);
		createEReference(procPointcutEClass, PROC_POINTCUT__GENERA);

		dependencesEClass = createEClass(DEPENDENCES);
		createEAttribute(dependencesEClass, DEPENDENCES__IS_INCLUSIVE);

		variant2varPEClass = createEClass(VARIANT2VAR_P);
		createEReference(variant2varPEClass, VARIANT2VAR_P__CLIENT);
		createEReference(variant2varPEClass, VARIANT2VAR_P__SUPPLIER);

		variant2variantEClass = createEClass(VARIANT2VARIANT);
		createEReference(variant2variantEClass, VARIANT2VARIANT__CLIENT);
		createEReference(variant2variantEClass, VARIANT2VARIANT__SUPPLIER);

		varp2variantEClass = createEClass(VARP2VARIANT);
		createEReference(varp2variantEClass, VARP2VARIANT__CLIENT);
		createEReference(varp2variantEClass, VARP2VARIANT__SUPPLIER);

		varP2varPEClass = createEClass(VAR_P2VAR_P);
		createEReference(varP2varPEClass, VAR_P2VAR_P__SUPPLIER);
		createEReference(varP2varPEClass, VAR_P2VAR_P__CLIENT);

		processLineEClass = createEClass(PROCESS_LINE);
		createEReference(processLineEClass, PROCESS_LINE__GENERA);
		createEReference(processLineEClass, PROCESS_LINE__COMPUESTAPOR);

		optativeEClass = createEClass(OPTATIVE);

		mandatoryEClass = createEClass(MANDATORY);

		optionalEClass = createEClass(OPTIONAL);

		vIterationEClass = createEClass(VITERATION);

		vPhaseEClass = createEClass(VPHASE);

		vActivityEClass = createEClass(VACTIVITY);

		// Create enums
		workOrderTypeEEnum = createEEnum(WORK_ORDER_TYPE);
		pseudoStateKindEEnum = createEEnum(PSEUDO_STATE_KIND);
		variabilityTypeEEnum = createEEnum(VARIABILITY_TYPE);

		// Create data types
		dateEDataType = createEDataType(DATE);
		uriEDataType = createEDataType(URI);
		unlimitedNaturalEDataType = createEDataType(UNLIMITED_NATURAL);
		stringEDataType = createEDataType(STRING);
		setEDataType = createEDataType(SET);
		sequenceEDataType = createEDataType(SEQUENCE);
		integerEDataType = createEDataType(INTEGER);
		floatEDataType = createEDataType(FLOAT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		classifierEClass.getESuperTypes().add(this.getType());
		typeEClass.getESuperTypes().add(this.getPackageableElement());
		namedElementEClass.getESuperTypes().add(this.getElement());
		packageableElementEClass.getESuperTypes().add(this.getNamedElement());
		packageEClass.getESuperTypes().add(this.getNamespace());
		packageEClass.getESuperTypes().add(this.getPackageableElement());
		namespaceEClass.getESuperTypes().add(this.getNamedElement());
		methodElementEClass.getESuperTypes().add(this.getPackageableElement());
		constraintEClass.getESuperTypes().add(this.getMethodElement());
		methodElementPropertyEClass.getESuperTypes().add(
				this.getPackageableElement());
		contentElementEClass.getESuperTypes().add(this.getDescribableElement());
		contentElementEClass.getESuperTypes().add(this.getVariabilityElement());
		describableElementEClass.getESuperTypes().add(this.getMethodElement());
		describableElementEClass.getESuperTypes().add(this.getClassifier());
		contentDescriptionEClass.getESuperTypes().add(this.getMethodUnit());
		sectionEClass.getESuperTypes().add(this.getVariabilityElement());
		roleEClass.getESuperTypes().add(this.getContentElement());
		workProductEClass.getESuperTypes().add(this.getContentElement());
		taskEClass.getESuperTypes().add(this.getContentElement());
		taskEClass.getESuperTypes().add(this.getWorkDefinition());
		workDefinitionEClass.getESuperTypes().add(this.getMethodElement());
		stepEClass.getESuperTypes().add(this.getSection());
		stepEClass.getESuperTypes().add(this.getWorkDefinition());
		guidanceEClass.getESuperTypes().add(this.getContentElement());
		artifactEClass.getESuperTypes().add(this.getWorkProduct());
		deliverableEClass.getESuperTypes().add(this.getWorkProduct());
		outcomeEClass.getESuperTypes().add(this.getWorkProduct());
		methodPackageEClass.getESuperTypes().add(this.getMethodElement());
		methodPackageEClass.getESuperTypes().add(this.getPackage());
		contentPackageEClass.getESuperTypes().add(this.getMethodPackage());
		artifactDescriptionEClass.getESuperTypes().add(
				this.getWorkProductDescription());
		workProductDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		deliverableDescriptionEClass.getESuperTypes().add(
				this.getWorkProductDescription());
		roleDescriptionEClass.getESuperTypes()
				.add(this.getContentDescription());
		taskDescriptionEClass.getESuperTypes()
				.add(this.getContentDescription());
		guidanceDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		practiceDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		graphElementEClass.getESuperTypes().add(this.getDiagramElement());
		diagramElementEClass.getESuperTypes().add(this.getMethodElement());
		diagramLinkEClass.getESuperTypes().add(this.getDiagramElement());
		graphConnectorEClass.getESuperTypes().add(this.getGraphElement());
		semanticModelBridgeEClass.getESuperTypes()
				.add(this.getDiagramElement());
		referenceEClass.getESuperTypes().add(this.getDiagramElement());
		propertyEClass.getESuperTypes().add(this.getDiagramElement());
		graphEdgeEClass.getESuperTypes().add(this.getGraphElement());
		diagramEClass.getESuperTypes().add(this.getGraphNode());
		graphNodeEClass.getESuperTypes().add(this.getGraphElement());
		simpleSemanticModelElementEClass.getESuperTypes().add(
				this.getSemanticModelBridge());
		umaSemanticModelBridgeEClass.getESuperTypes().add(
				this.getSemanticModelBridge());
		coreSemanticModelBridgeEClass.getESuperTypes().add(
				this.getSemanticModelBridge());
		leafElementEClass.getESuperTypes().add(this.getDiagramElement());
		textElementEClass.getESuperTypes().add(this.getLeafElement());
		imageEClass.getESuperTypes().add(this.getLeafElement());
		graphicPrimitiveEClass.getESuperTypes().add(this.getLeafElement());
		polylineEClass.getESuperTypes().add(this.getGraphicPrimitive());
		ellipseEClass.getESuperTypes().add(this.getGraphicPrimitive());
		workBreakdownElementEClass.getESuperTypes().add(
				this.getBreakdownElement());
		breakdownElementEClass.getESuperTypes().add(this.getProcessElement());
		milestoneEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		iterationEClass.getESuperTypes().add(this.getActivity());
		phaseEClass.getESuperTypes().add(this.getActivity());
		teamProfileEClass.getESuperTypes().add(this.getBreakdownElement());
		roleDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		workOrderEClass.getESuperTypes().add(this.getProcessElement());
		planningDataEClass.getESuperTypes().add(this.getProcessElement());
		descriptorEClass.getESuperTypes().add(this.getBreakdownElement());
		workProductDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		taskDescriptorEClass.getESuperTypes().add(
				this.getWorkBreakdownElement());
		taskDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		compositeRoleEClass.getESuperTypes().add(this.getRoleDescriptor());
		breakdownElementDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		activityDescriptionEClass.getESuperTypes().add(
				this.getBreakdownElementDescription());
		deliveryProcessDescriptionEClass.getESuperTypes().add(
				this.getProcessDescription());
		processDescriptionEClass.getESuperTypes().add(
				this.getActivityDescription());
		descriptorDescriptionEClass.getESuperTypes().add(
				this.getBreakdownElementDescription());
		conceptEClass.getESuperTypes().add(this.getGuidance());
		checklistEClass.getESuperTypes().add(this.getGuidance());
		exampleEClass.getESuperTypes().add(this.getGuidance());
		guidelineEClass.getESuperTypes().add(this.getGuidance());
		reportEClass.getESuperTypes().add(this.getGuidance());
		templateEClass.getESuperTypes().add(this.getGuidance());
		supportingMaterialEClass.getESuperTypes().add(this.getGuidance());
		toolMentorEClass.getESuperTypes().add(this.getGuidance());
		whitepaperEClass.getESuperTypes().add(this.getConcept());
		termDefinitionEClass.getESuperTypes().add(this.getGuidance());
		practiceEClass.getESuperTypes().add(this.getGuidance());
		estimationConsiderationsEClass.getESuperTypes().add(this.getGuidance());
		reusableAssetEClass.getESuperTypes().add(this.getGuidance());
		stateEClass.getESuperTypes().add(this.getVertex());
		stateMachineEClass.getESuperTypes().add(this.getWorkDefinition());
		pseudoStateEClass.getESuperTypes().add(this.getVertex());
		disciplineEClass.getESuperTypes().add(this.getContentCategory());
		contentCategoryEClass.getESuperTypes().add(this.getContentElement());
		roleSetEClass.getESuperTypes().add(this.getContentCategory());
		domainEClass.getESuperTypes().add(this.getContentCategory());
		pruebasLineasEClass.getESuperTypes().add(this.getContentCategory());
		workProductTypeEClass.getESuperTypes().add(this.getContentCategory());
		disciplineGroupingEClass.getESuperTypes()
				.add(this.getContentCategory());
		toolEClass.getESuperTypes().add(this.getContentCategory());
		roleSetGroupingEClass.getESuperTypes().add(this.getContentCategory());
		customCategoryEClass.getESuperTypes().add(this.getContentCategory());
		deliveryProcessEClass.getESuperTypes().add(this.getProcess());
		processEClass.getESuperTypes().add(this.getActivity());
		capabilityPatternEClass.getESuperTypes().add(this.getProcess());
		processPlanningTemplateEClass.getESuperTypes().add(this.getProcess());
		roadmapEClass.getESuperTypes().add(this.getGuidance());
		processComponentEClass.getESuperTypes().add(this.getProcessPackage());
		processComponentEClass.getESuperTypes().add(this.getMethodUnit());
		processPackageEClass.getESuperTypes().add(this.getMethodPackage());
		processElementEClass.getESuperTypes().add(this.getDescribableElement());
		processComponentInterfaceEClass.getESuperTypes().add(
				this.getBreakdownElement());
		processComponentDescriptorEClass.getESuperTypes().add(
				this.getDescriptor());
		methodPluginEClass.getESuperTypes().add(this.getMethodUnit());
		methodPluginEClass.getESuperTypes().add(this.getPackage());
		lineProcessEClass.getESuperTypes().add(this.getProcess());
		processLinesPackageEClass.getESuperTypes().add(this.getMethodPackage());
		processLineElementEClass.getESuperTypes().add(
				this.getDescribableElement());
		processLineComponentEClass.getESuperTypes().add(
				this.getProcessLinesPackage());
		processLineComponentEClass.getESuperTypes().add(this.getMethodUnit());
		processLineComponentEClass.getESuperTypes().add(this.getMethodPlugin());
		processLineComponentPackageEClass.getESuperTypes().add(
				this.getMethodPackage());
		processLineComponentInterfaceEClass.getESuperTypes().add(
				this.getBreakdownElement());
		coreProcessPackageEClass.getESuperTypes().add(this.getMethodPackage());
		coreProcessPackageEClass.getESuperTypes().add(this.getProcessPackage());
		tailoredProcessEClass.getESuperTypes().add(this.getProcess());
		tailoredProcessesPackageEClass.getESuperTypes().add(
				this.getMethodPackage());
		tailoredProcessComponentEClass.getESuperTypes().add(
				this.getTailoredProcessesPackage());
		tailoredProcessComponentEClass.getESuperTypes().add(
				this.getMethodUnit());
		tailoredProcessComponentEClass.getESuperTypes().add(
				this.getMethodPlugin());
		tailoredCoreProcessPackageEClass.getESuperTypes().add(
				this.getMethodPackage());
		tailoredCoreProcessPackageEClass.getESuperTypes().add(
				this.getProcessPackage());
		variabilityElementEClass.getESuperTypes().add(this.getMethodElement());
		variabilityElementEClass.getESuperTypes().add(this.getVariability());
		methodUnitEClass.getESuperTypes().add(this.getMethodElement());
		methodConfigurationEClass.getESuperTypes().add(this.getMethodUnit());
		processFamilyEClass.getESuperTypes().add(this.getMethodConfiguration());
		methodLibraryEClass.getESuperTypes().add(this.getMethodUnit());
		methodLibraryEClass.getESuperTypes().add(this.getPackage());
		varElementEClass.getESuperTypes().add(this.getVariability());
		varPointEClass.getESuperTypes().add(this.getVarElement());
		vpActivityEClass.getESuperTypes().add(this.getActivity());
		vpActivityEClass.getESuperTypes().add(this.getVarPoint());
		vpPhaseEClass.getESuperTypes().add(this.getPhase());
		vpPhaseEClass.getESuperTypes().add(this.getVarPoint());
		vpIterationEClass.getESuperTypes().add(this.getIteration());
		vpIterationEClass.getESuperTypes().add(this.getVarPoint());
		vpRoleDescriptorEClass.getESuperTypes().add(this.getRoleDescriptor());
		vpRoleDescriptorEClass.getESuperTypes().add(this.getVarPoint());
		vpTaskDescriptorEClass.getESuperTypes().add(this.getTaskDescriptor());
		vpTaskDescriptorEClass.getESuperTypes().add(this.getVarPoint());
		vpMilestoneEClass.getESuperTypes().add(this.getMilestone());
		vpMilestoneEClass.getESuperTypes().add(this.getVarPoint());
		vpTeamProfileEClass.getESuperTypes().add(this.getTeamProfile());
		vpTeamProfileEClass.getESuperTypes().add(this.getVarPoint());
		vpWorkProductDescriptorEClass.getESuperTypes().add(
				this.getWorkProductDescriptor());
		vpWorkProductDescriptorEClass.getESuperTypes().add(this.getVarPoint());
		variantEClass.getESuperTypes().add(this.getVarElement());
		varActivityEClass.getESuperTypes().add(this.getActivity());
		varActivityEClass.getESuperTypes().add(this.getVariant());
		varPhaseEClass.getESuperTypes().add(this.getPhase());
		varPhaseEClass.getESuperTypes().add(this.getVariant());
		varIterationEClass.getESuperTypes().add(this.getIteration());
		varIterationEClass.getESuperTypes().add(this.getVariant());
		varRoleDescriptorEClass.getESuperTypes().add(this.getRoleDescriptor());
		varRoleDescriptorEClass.getESuperTypes().add(this.getVariant());
		varTaskDescriptorEClass.getESuperTypes().add(this.getTaskDescriptor());
		varTaskDescriptorEClass.getESuperTypes().add(this.getVariant());
		varMilestoneEClass.getESuperTypes().add(this.getMilestone());
		varMilestoneEClass.getESuperTypes().add(this.getVariant());
		varTeamProfileEClass.getESuperTypes().add(this.getTeamProfile());
		varTeamProfileEClass.getESuperTypes().add(this.getVariant());
		varWorkProductDescriptorEClass.getESuperTypes().add(
				this.getWorkProductDescriptor());
		varWorkProductDescriptorEClass.getESuperTypes().add(this.getVariant());
		activityEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		activityEClass.getESuperTypes().add(this.getVariabilityElement());
		activityEClass.getESuperTypes().add(this.getWorkDefinition());
		procAspectEClass.getESuperTypes().add(this.getVarElement());
		procAspectEClass.getESuperTypes().add(this.getVariation());
		variantsListPackageEClass.getESuperTypes().add(
				this.getContentCategory());
		variantsPackageEClass.getESuperTypes().add(this.getMethodPackage());
		varPointsPackageEClass.getESuperTypes().add(this.getMethodPackage());
		variationsPackageEClass.getESuperTypes().add(this.getMethodPackage());
		occupationEClass.getESuperTypes().add(this.getVariation());
		variant2varPEClass.getESuperTypes().add(this.getDependences());
		variant2variantEClass.getESuperTypes().add(this.getDependences());
		varp2variantEClass.getESuperTypes().add(this.getDependences());
		varP2varPEClass.getESuperTypes().add(this.getDependences());
		processLineEClass.getESuperTypes().add(this.getProcessPackage());
		optativeEClass.getESuperTypes().add(this.getVariationType());
		mandatoryEClass.getESuperTypes().add(this.getVariationType());
		optionalEClass.getESuperTypes().add(this.getVariationType());

		// Initialize classes and features; add operations and parameters
		initEClass(
				classifierEClass,
				Classifier.class,
				"Classifier", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(typeEClass, Type.class,
				"Type", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				elementEClass,
				Element.class,
				"Element", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				namedElementEClass,
				NamedElement.class,
				"NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getNamedElement_Name(),
				this.getString(),
				"name", "", 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				packageableElementEClass,
				PackageableElement.class,
				"PackageableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				packageEClass,
				org.eclipse.epf.uma.Package.class,
				"Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				namespaceEClass,
				Namespace.class,
				"Namespace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				methodElementEClass,
				MethodElement.class,
				"MethodElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodElement_Guid(),
				this.getString(),
				"guid", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodElement_BriefDescription(),
				this.getString(),
				"briefDescription", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodElement_OwnedRules(),
				this.getConstraint(),
				null,
				"ownedRules", null, 0, -1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodElement_MethodElementProperty(),
				this.getMethodElementProperty(),
				null,
				"methodElementProperty", null, 0, -1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getMethodElement_Suppressed(),
				ecorePackage.getEBooleanObject(),
				"suppressed", "false", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodElement_OrderingGuide(),
				this.getString(),
				"orderingGuide", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				constraintEClass,
				Constraint.class,
				"Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getConstraint_Body(),
				this.getString(),
				"body", "", 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				methodElementPropertyEClass,
				MethodElementProperty.class,
				"MethodElementProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodElementProperty_Value(),
				this.getString(),
				"value", "", 0, 1, MethodElementProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				contentElementEClass,
				ContentElement.class,
				"ContentElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getContentElement_SupportingMaterials(),
				this.getSupportingMaterial(),
				null,
				"supportingMaterials", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_ConceptsAndPapers(),
				this.getConcept(),
				null,
				"conceptsAndPapers", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Checklists(),
				this.getChecklist(),
				null,
				"checklists", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Guidelines(),
				this.getGuideline(),
				null,
				"guidelines", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Examples(),
				this.getExample(),
				null,
				"examples", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Assets(),
				this.getReusableAsset(),
				null,
				"assets", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_TermDefinition(),
				this.getTermDefinition(),
				null,
				"termDefinition", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				describableElementEClass,
				DescribableElement.class,
				"DescribableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDescribableElement_PresentationName(),
				this.getString(),
				"presentationName", "", 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getDescribableElement_Presentation(),
				this.getContentDescription(),
				null,
				"presentation", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDescribableElement_Shapeicon(),
				this.getUri(),
				"shapeicon", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDescribableElement_Nodeicon(),
				this.getUri(),
				"nodeicon", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentDescriptionEClass,
				ContentDescription.class,
				"ContentDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getContentDescription_MainDescription(),
				this.getString(),
				"mainDescription", "", 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getContentDescription_Sections(),
				this.getSection(),
				null,
				"sections", null, 0, -1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getContentDescription_ExternalId(),
				this.getString(),
				"externalId", "", 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getContentDescription_KeyConsiderations(),
				this.getString(),
				"keyConsiderations", "", 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				sectionEClass,
				Section.class,
				"Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getSection_SectionName(),
				this.getString(),
				"sectionName", "", 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getSection_SectionDescription(),
				this.getString(),
				"sectionDescription", "", 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getSection_SubSections(),
				this.getSection(),
				null,
				"subSections", null, 0, -1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getSection_Predecessor(),
				this.getSection(),
				null,
				"predecessor", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roleEClass,
				Role.class,
				"Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRole_Modifies(),
				this.getWorkProduct(),
				null,
				"modifies", null, 0, -1, Role.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRole_ResponsibleFor(),
				this.getWorkProduct(),
				null,
				"responsibleFor", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workProductEClass,
				WorkProduct.class,
				"WorkProduct", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getWorkProduct_Reports(),
				this.getReport(),
				null,
				"reports", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProduct_Templates(),
				this.getTemplate(),
				null,
				"templates", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProduct_ToolMentors(),
				this.getToolMentor(),
				null,
				"toolMentors", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProduct_EstimationConsiderations(),
				this.getEstimationConsiderations(),
				null,
				"estimationConsiderations", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				taskEClass,
				Task.class,
				"Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTask_PerformedBy(),
				this.getRole(),
				null,
				"performedBy", null, 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_MandatoryInput(),
				this.getWorkProduct(),
				null,
				"mandatoryInput", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_Output(),
				this.getWorkProduct(),
				null,
				"output", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_AdditionallyPerformedBy(),
				this.getRole(),
				null,
				"additionallyPerformedBy", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_OptionalInput(),
				this.getWorkProduct(),
				null,
				"optionalInput", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_Steps(),
				this.getStep(),
				null,
				"steps", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_ToolMentors(),
				this.getToolMentor(),
				null,
				"toolMentors", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_EstimationConsiderations(),
				this.getEstimationConsiderations(),
				null,
				"estimationConsiderations", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workDefinitionEClass,
				WorkDefinition.class,
				"WorkDefinition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getWorkDefinition_Precondition(),
				this.getConstraint(),
				null,
				"precondition", null, 0, 1, WorkDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkDefinition_Postcondition(),
				this.getConstraint(),
				null,
				"postcondition", null, 0, 1, WorkDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				stepEClass,
				Step.class,
				"Step", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				guidanceEClass,
				Guidance.class,
				"Guidance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				artifactEClass,
				Artifact.class,
				"Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getArtifact_ContainerArtifact(),
				this.getArtifact(),
				this.getArtifact_ContainedArtifacts(),
				"containerArtifact", null, 0, 1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getArtifact_ContainedArtifacts(),
				this.getArtifact(),
				this.getArtifact_ContainerArtifact(),
				"containedArtifacts", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				deliverableEClass,
				Deliverable.class,
				"Deliverable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDeliverable_DeliveredWorkProducts(),
				this.getWorkProduct(),
				null,
				"deliveredWorkProducts", null, 0, -1, Deliverable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				outcomeEClass,
				Outcome.class,
				"Outcome", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				methodPackageEClass,
				MethodPackage.class,
				"MethodPackage", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodPackage_Global(),
				ecorePackage.getEBooleanObject(),
				"global", "false", 0, 1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodPackage_ReusedPackages(),
				this.getMethodPackage(),
				null,
				"reusedPackages", null, 0, -1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodPackage_ChildPackages(),
				this.getMethodPackage(),
				null,
				"childPackages", null, 0, -1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentPackageEClass,
				ContentPackage.class,
				"ContentPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getContentPackage_ContentElements(),
				this.getContentElement(),
				null,
				"contentElements", null, 0, -1, ContentPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				artifactDescriptionEClass,
				ArtifactDescription.class,
				"ArtifactDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getArtifactDescription_BriefOutline(),
				this.getString(),
				"briefOutline", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getArtifactDescription_RepresentationOptions(),
				this.getString(),
				"representationOptions", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getArtifactDescription_Representation(),
				this.getString(),
				"representation", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getArtifactDescription_Notation(),
				this.getString(),
				"notation", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				workProductDescriptionEClass,
				WorkProductDescription.class,
				"WorkProductDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkProductDescription_Purpose(),
				this.getString(),
				"purpose", "", 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkProductDescription_ImpactOfNotHaving(),
				this.getString(),
				"impactOfNotHaving", "", 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkProductDescription_ReasonsForNotNeeding(),
				this.getString(),
				"reasonsForNotNeeding", "", 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				deliverableDescriptionEClass,
				DeliverableDescription.class,
				"DeliverableDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDeliverableDescription_ExternalDescription(),
				this.getString(),
				"externalDescription", "", 0, 1, DeliverableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliverableDescription_PackagingGuidance(),
				this.getString(),
				"packagingGuidance", "", 0, 1, DeliverableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				roleDescriptionEClass,
				RoleDescription.class,
				"RoleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getRoleDescription_Skills(),
				this.getString(),
				"skills", "", 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getRoleDescription_AssignmentApproaches(),
				this.getString(),
				"assignmentApproaches", "", 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getRoleDescription_Synonyms(),
				this.getString(),
				"synonyms", "", 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				taskDescriptionEClass,
				TaskDescription.class,
				"TaskDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getTaskDescription_Purpose(),
				this.getString(),
				"purpose", "", 0, 1, TaskDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getTaskDescription_Alternatives(),
				this.getString(),
				"alternatives", "", 0, 1, TaskDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				guidanceDescriptionEClass,
				GuidanceDescription.class,
				"GuidanceDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getGuidanceDescription_Attachments(),
				this.getString(),
				"attachments", "", 0, 1, GuidanceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				practiceDescriptionEClass,
				PracticeDescription.class,
				"PracticeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPracticeDescription_AdditionalInfo(),
				this.getString(),
				"additionalInfo", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Problem(),
				this.getString(),
				"problem", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Background(),
				this.getString(),
				"background", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Goals(),
				this.getString(),
				"goals", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Application(),
				this.getString(),
				"application", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_LevelsOfAdoption(),
				this.getString(),
				"levelsOfAdoption", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				pointEClass,
				Point.class,
				"Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPoint_X(),
				ecorePackage.getEDoubleObject(),
				"x", null, 0, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getPoint_Y(),
				ecorePackage.getEDoubleObject(),
				"y", null, 0, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphElementEClass,
				GraphElement.class,
				"GraphElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphElement_Position(),
				this.getPoint(),
				null,
				"position", null, 0, 1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_Contained(),
				this.getDiagramElement(),
				this.getDiagramElement_Container(),
				"contained", null, 0, -1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_Link(),
				this.getDiagramLink(),
				this.getDiagramLink_GraphElement(),
				"link", null, 0, -1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_Anchorage(),
				this.getGraphConnector(),
				this.getGraphConnector_GraphElement(),
				"anchorage", null, 0, -1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_SemanticModel(),
				this.getSemanticModelBridge(),
				this.getSemanticModelBridge_GraphElement(),
				"semanticModel", null, 1, 1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				diagramElementEClass,
				DiagramElement.class,
				"DiagramElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDiagramElement_IsVisible(),
				ecorePackage.getEBooleanObject(),
				"isVisible", "true", 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getDiagramElement_Container(),
				this.getGraphElement(),
				this.getGraphElement_Contained(),
				"container", null, 0, 1, DiagramElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramElement_Reference(),
				this.getReference(),
				this.getReference_Referenced(),
				"reference", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramElement_Property(),
				this.getProperty(),
				null,
				"property", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				diagramLinkEClass,
				DiagramLink.class,
				"DiagramLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDiagramLink_Zoom(),
				ecorePackage.getEDoubleObject(),
				"zoom", null, 0, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramLink_Viewport(),
				this.getPoint(),
				null,
				"viewport", null, 0, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramLink_GraphElement(),
				this.getGraphElement(),
				this.getGraphElement_Link(),
				"graphElement", null, 1, 1, DiagramLink.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramLink_Diagram(),
				this.getDiagram(),
				this.getDiagram_DiagramLink(),
				"diagram", null, 1, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphConnectorEClass,
				GraphConnector.class,
				"GraphConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphConnector_GraphElement(),
				this.getGraphElement(),
				this.getGraphElement_Anchorage(),
				"graphElement", null, 1, 1, GraphConnector.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphConnector_GraphEdge(),
				this.getGraphEdge(),
				this.getGraphEdge_Anchor(),
				"graphEdge", null, 0, -1, GraphConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				semanticModelBridgeEClass,
				SemanticModelBridge.class,
				"SemanticModelBridge", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getSemanticModelBridge_Presentation(),
				this.getString(),
				"presentation", "", 0, 1, SemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getSemanticModelBridge_GraphElement(),
				this.getGraphElement(),
				this.getGraphElement_SemanticModel(),
				"graphElement", null, 0, 1, SemanticModelBridge.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getSemanticModelBridge_Diagram(),
				this.getDiagram(),
				this.getDiagram_Namespace(),
				"diagram", null, 0, 1, SemanticModelBridge.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				dimensionEClass,
				Dimension.class,
				"Dimension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDimension_Width(),
				ecorePackage.getEDoubleObject(),
				"width", null, 0, 1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDimension_Height(),
				ecorePackage.getEDoubleObject(),
				"height", null, 0, 1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				referenceEClass,
				Reference.class,
				"Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getReference_IsIndividualRepresentation(),
				ecorePackage.getEBooleanObject(),
				"isIndividualRepresentation", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getReference_Referenced(),
				this.getDiagramElement(),
				this.getDiagramElement_Reference(),
				"referenced", null, 1, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				propertyEClass,
				Property.class,
				"Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getProperty_Key(),
				this.getString(),
				"key", "", 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getProperty_Value(),
				this.getString(),
				"value", "", 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				graphEdgeEClass,
				GraphEdge.class,
				"GraphEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphEdge_Anchor(),
				this.getGraphConnector(),
				this.getGraphConnector_GraphEdge(),
				"anchor", null, 2, 2, GraphEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphEdge_Waypoints(),
				this.getPoint(),
				null,
				"waypoints", null, 2, -1, GraphEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				diagramEClass,
				Diagram.class,
				"Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDiagram_Zoom(),
				ecorePackage.getEDoubleObject(),
				"zoom", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagram_Viewpoint(),
				this.getPoint(),
				null,
				"viewpoint", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagram_DiagramLink(),
				this.getDiagramLink(),
				this.getDiagramLink_Diagram(),
				"diagramLink", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagram_Namespace(),
				this.getSemanticModelBridge(),
				this.getSemanticModelBridge_Diagram(),
				"namespace", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphNodeEClass,
				GraphNode.class,
				"GraphNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphNode_Size(),
				this.getDimension(),
				null,
				"size", null, 0, 1, GraphNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				simpleSemanticModelElementEClass,
				SimpleSemanticModelElement.class,
				"SimpleSemanticModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getSimpleSemanticModelElement_TypeInfo(),
				this.getString(),
				"typeInfo", "", 0, 1, SimpleSemanticModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				umaSemanticModelBridgeEClass,
				UMASemanticModelBridge.class,
				"UMASemanticModelBridge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getUMASemanticModelBridge_Element(),
				this.getMethodElement(),
				null,
				"element", null, 1, 1, UMASemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				coreSemanticModelBridgeEClass,
				CoreSemanticModelBridge.class,
				"CoreSemanticModelBridge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCoreSemanticModelBridge_Element(),
				this.getElement(),
				null,
				"element", null, 1, 1, CoreSemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				leafElementEClass,
				LeafElement.class,
				"LeafElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				textElementEClass,
				TextElement.class,
				"TextElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getTextElement_Text(),
				this.getString(),
				"text", "", 0, 1, TextElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				imageEClass,
				Image.class,
				"Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getImage_Uri(),
				this.getUri(),
				"uri", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getImage_MimeType(),
				this.getString(),
				"mimeType", "", 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				graphicPrimitiveEClass,
				GraphicPrimitive.class,
				"GraphicPrimitive", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				polylineEClass,
				Polyline.class,
				"Polyline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPolyline_Closed(),
				ecorePackage.getEBooleanObject(),
				"closed", "true", 0, 1, Polyline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getPolyline_Waypoints(),
				this.getPoint(),
				null,
				"waypoints", null, 2, -1, Polyline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				ellipseEClass,
				Ellipse.class,
				"Ellipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getEllipse_RadiusX(),
				ecorePackage.getEDoubleObject(),
				"radiusX", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_RadiusY(),
				ecorePackage.getEDoubleObject(),
				"radiusY", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_Rotation(),
				ecorePackage.getEDoubleObject(),
				"rotation", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_StartAngle(),
				ecorePackage.getEDoubleObject(),
				"startAngle", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_EndAngle(),
				ecorePackage.getEDoubleObject(),
				"endAngle", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getEllipse_Center(),
				this.getPoint(),
				null,
				"center", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workBreakdownElementEClass,
				WorkBreakdownElement.class,
				"WorkBreakdownElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkBreakdownElement_IsRepeatable(),
				ecorePackage.getEBooleanObject(),
				"isRepeatable", "false", 0, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkBreakdownElement_IsOngoing(),
				ecorePackage.getEBooleanObject(),
				"isOngoing", "false", 0, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkBreakdownElement_IsEventDriven(),
				ecorePackage.getEBooleanObject(),
				"isEventDriven", "false", 0, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getWorkBreakdownElement_LinkToPredecessor(),
				this.getWorkOrder(),
				null,
				"linkToPredecessor", null, 0, -1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				breakdownElementEClass,
				BreakdownElement.class,
				"BreakdownElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getBreakdownElement_Prefix(),
				this.getString(),
				"prefix", "", 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getBreakdownElement_IsPlanned(),
				ecorePackage.getEBooleanObject(),
				"isPlanned", "true", 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getBreakdownElement_HasMultipleOccurrences(),
				ecorePackage.getEBooleanObject(),
				"hasMultipleOccurrences", "false", 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getBreakdownElement_IsOptional(),
				ecorePackage.getEBooleanObject(),
				"isOptional", "false", 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getBreakdownElement_PresentedAfter(),
				this.getBreakdownElement(),
				null,
				"presentedAfter", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_PresentedBefore(),
				this.getBreakdownElement(),
				null,
				"presentedBefore", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_PlanningData(),
				this.getPlanningData(),
				null,
				"planningData", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_SuperActivities(),
				this.getActivity(),
				this.getActivity_BreakdownElements(),
				"superActivities", null, 1, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				milestoneEClass,
				Milestone.class,
				"Milestone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				iterationEClass,
				Iteration.class,
				"Iteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				phaseEClass,
				Phase.class,
				"Phase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				teamProfileEClass,
				TeamProfile.class,
				"TeamProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTeamProfile_TeamRoles(),
				this.getRoleDescriptor(),
				null,
				"teamRoles", null, 0, -1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTeamProfile_SuperTeam(),
				this.getTeamProfile(),
				this.getTeamProfile_SubTeam(),
				"superTeam", null, 1, 1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTeamProfile_SubTeam(),
				this.getTeamProfile(),
				this.getTeamProfile_SuperTeam(),
				"subTeam", null, 0, -1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roleDescriptorEClass,
				RoleDescriptor.class,
				"RoleDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_Role(),
				this.getRole(),
				null,
				"Role", null, 0, 1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_Modifies(),
				this.getWorkProductDescriptor(),
				null,
				"modifies", null, 0, -1, RoleDescriptor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_ResponsibleFor(),
				this.getWorkProductDescriptor(),
				null,
				"responsibleFor", null, 0, -1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workOrderEClass,
				WorkOrder.class,
				"WorkOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkOrder_LinkType(),
				this.getWorkOrderType(),
				"linkType", "finishToStart", 0, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getWorkOrder_Pred(),
				this.getWorkBreakdownElement(),
				null,
				"pred", null, 1, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				planningDataEClass,
				PlanningData.class,
				"PlanningData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPlanningData_StartDate(),
				this.getDate(),
				"startDate", null, 0, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getPlanningData_FinishDate(),
				this.getDate(),
				"finishDate", null, 0, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getPlanningData_Rank(),
				this.getInteger(),
				"rank", null, 0, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				descriptorEClass,
				org.eclipse.epf.uma.Descriptor.class,
				"Descriptor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDescriptor_IsSynchronizedWithSource(),
				ecorePackage.getEBooleanObject(),
				"isSynchronizedWithSource", "true", 0, 1, org.eclipse.epf.uma.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				workProductDescriptorEClass,
				WorkProductDescriptor.class,
				"WorkProductDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkProductDescriptor_ActivityEntryState(),
				this.getString(),
				"activityEntryState", "", 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkProductDescriptor_ActivityExitState(),
				this.getString(),
				"activityExitState", "", 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getWorkProductDescriptor_WorkProduct(),
				this.getWorkProduct(),
				null,
				"WorkProduct", null, 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_ImpactedBy(),
				this.getWorkProductDescriptor(),
				this.getWorkProductDescriptor_Impacts(),
				"impactedBy", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_Impacts(),
				this.getWorkProductDescriptor(),
				this.getWorkProductDescriptor_ImpactedBy(),
				"impacts", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_DeliverableParts(),
				this.getWorkProductDescriptor(),
				null,
				"deliverableParts", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				taskDescriptorEClass,
				TaskDescriptor.class,
				"TaskDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_Task(),
				this.getTask(),
				null,
				"Task", null, 0, 1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_AdditionallyPerformedBy(),
				this.getRoleDescriptor(),
				null,
				"additionallyPerformedBy", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_AssistedBy(),
				this.getRoleDescriptor(),
				null,
				"assistedBy", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_ExternalInput(),
				this.getWorkProductDescriptor(),
				null,
				"externalInput", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_MandatoryInput(),
				this.getWorkProductDescriptor(),
				null,
				"mandatoryInput", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_OptionalInput(),
				this.getWorkProductDescriptor(),
				null,
				"optionalInput", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_Output(),
				this.getWorkProductDescriptor(),
				null,
				"output", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_PerformedPrimarilyBy(),
				this.getRoleDescriptor(),
				null,
				"performedPrimarilyBy", null, 0, 1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_SelectedSteps(),
				this.getSection(),
				null,
				"selectedSteps", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				compositeRoleEClass,
				CompositeRole.class,
				"CompositeRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCompositeRole_AggregatedRoles(),
				this.getRole(),
				null,
				"aggregatedRoles", null, 0, -1, CompositeRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				breakdownElementDescriptionEClass,
				BreakdownElementDescription.class,
				"BreakdownElementDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getBreakdownElementDescription_UsageGuidance(),
				this.getString(),
				"usageGuidance", "", 0, 1, BreakdownElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				activityDescriptionEClass,
				ActivityDescription.class,
				"ActivityDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getActivityDescription_Purpose(),
				this.getString(),
				"purpose", "", 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getActivityDescription_Alternatives(),
				this.getString(),
				"alternatives", "", 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getActivityDescription_HowtoStaff(),
				this.getString(),
				"howtoStaff", "", 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				deliveryProcessDescriptionEClass,
				DeliveryProcessDescription.class,
				"DeliveryProcessDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDeliveryProcessDescription_Scale(),
				this.getString(),
				"scale", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_ProjectCharacteristics(),
				this.getString(),
				"projectCharacteristics", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_RiskLevel(),
				this.getString(),
				"riskLevel", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_EstimatingTechnique(),
				this.getString(),
				"estimatingTechnique", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_ProjectMemberExpertise(),
				this.getString(),
				"projectMemberExpertise", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_TypeOfContract(),
				this.getString(),
				"typeOfContract", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				processDescriptionEClass,
				ProcessDescription.class,
				"ProcessDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getProcessDescription_Scope(),
				this.getString(),
				"scope", "", 0, 1, ProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getProcessDescription_UsageNotes(),
				this.getString(),
				"usageNotes", "", 0, 1, ProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				descriptorDescriptionEClass,
				DescriptorDescription.class,
				"DescriptorDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDescriptorDescription_RefinedDescription(),
				this.getString(),
				"refinedDescription", "", 0, 1, DescriptorDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				conceptEClass,
				Concept.class,
				"Concept", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				checklistEClass,
				Checklist.class,
				"Checklist", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				exampleEClass,
				Example.class,
				"Example", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				guidelineEClass,
				Guideline.class,
				"Guideline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				reportEClass,
				Report.class,
				"Report", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				templateEClass,
				Template.class,
				"Template", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				supportingMaterialEClass,
				SupportingMaterial.class,
				"SupportingMaterial", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				toolMentorEClass,
				ToolMentor.class,
				"ToolMentor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				whitepaperEClass,
				Whitepaper.class,
				"Whitepaper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				termDefinitionEClass,
				TermDefinition.class,
				"TermDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				practiceEClass,
				Practice.class,
				"Practice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getPractice_SubPractices(),
				this.getPractice(),
				null,
				"subPractices", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getPractice_ContentReferences(),
				this.getContentElement(),
				null,
				"contentReferences", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getPractice_ActivityReferences(),
				this.getActivity(),
				null,
				"activityReferences", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				estimationConsiderationsEClass,
				EstimationConsiderations.class,
				"EstimationConsiderations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				reusableAssetEClass,
				ReusableAsset.class,
				"ReusableAsset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				stateEClass,
				State.class,
				"State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getState_WorkProduct(),
				this.getWorkProduct(),
				null,
				"WorkProduct", null, 1, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getState_Region(),
				this.getRegion(),
				this.getRegion_State(),
				"Region", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getState_Submachine(),
				this.getStateMachine(),
				null,
				"submachine", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				vertexEClass,
				Vertex.class,
				"Vertex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVertex_Container(),
				this.getRegion(),
				this.getRegion_Vertex(),
				"container", null, 0, 1, Vertex.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getVertex_Outgoing(),
				this.getTransition(),
				this.getTransition_Source(),
				"outgoing", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getVertex_Incoming(),
				this.getTransition(),
				this.getTransition_Target(),
				"incoming", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				regionEClass,
				Region.class,
				"Region", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRegion_Vertex(),
				this.getVertex(),
				this.getVertex_Container(),
				"Vertex", null, 0, -1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRegion_Transition(),
				this.getTransition(),
				this.getTransition_Container(),
				"Transition", null, 0, -1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRegion_State(),
				this.getState(),
				this.getState_Region(),
				"State", null, 0, 1, Region.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRegion_StateMachine(),
				this.getStateMachine(),
				this.getStateMachine_Region(),
				"StateMachine", null, 0, 1, Region.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				stateMachineEClass,
				StateMachine.class,
				"StateMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getStateMachine_Region(),
				this.getRegion(),
				this.getRegion_StateMachine(),
				"Region", null, 1, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				transitionEClass,
				Transition.class,
				"Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTransition_WorkDefinition(),
				this.getWorkDefinition(),
				null,
				"WorkDefinition", null, 1, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTransition_Container(),
				this.getRegion(),
				this.getRegion_Transition(),
				"container", null, 1, 1, Transition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTransition_Source(),
				this.getVertex(),
				this.getVertex_Outgoing(),
				"source", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTransition_Target(),
				this.getVertex(),
				this.getVertex_Incoming(),
				"target", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				pseudoStateEClass,
				PseudoState.class,
				"PseudoState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				disciplineEClass,
				Discipline.class,
				"Discipline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDiscipline_Tasks(),
				this.getTask(),
				null,
				"tasks", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiscipline_Subdiscipline(),
				this.getDiscipline(),
				null,
				"subdiscipline", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiscipline_ReferenceWorkflows(),
				this.getActivity(),
				null,
				"referenceWorkflows", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentCategoryEClass,
				ContentCategory.class,
				"ContentCategory", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				roleSetEClass,
				RoleSet.class,
				"RoleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRoleSet_Roles(),
				this.getRole(),
				null,
				"roles", null, 0, -1, RoleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				domainEClass,
				Domain.class,
				"Domain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDomain_WorkProducts(),
				this.getWorkProduct(),
				null,
				"workProducts", null, 0, -1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDomain_Subdomains(),
				this.getDomain(),
				null,
				"subdomains", null, 0, -1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				pruebasLineasEClass,
				PruebasLineas.class,
				"PruebasLineas", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getPruebasLineas_SubPruebasLineas(),
				this.getPruebasLineas(),
				null,
				"subPruebasLineas", null, 0, -1, PruebasLineas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workProductTypeEClass,
				WorkProductType.class,
				"WorkProductType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getWorkProductType_WorkProducts(),
				this.getWorkProduct(),
				null,
				"workProducts", null, 0, -1, WorkProductType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				disciplineGroupingEClass,
				DisciplineGrouping.class,
				"DisciplineGrouping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDisciplineGrouping_Disciplines(),
				this.getDiscipline(),
				null,
				"disciplines", null, 0, -1, DisciplineGrouping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				toolEClass,
				Tool.class,
				"Tool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTool_ToolMentors(),
				this.getToolMentor(),
				null,
				"toolMentors", null, 0, -1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roleSetGroupingEClass,
				RoleSetGrouping.class,
				"RoleSetGrouping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRoleSetGrouping_RoleSets(),
				this.getRoleSet(),
				null,
				"roleSets", null, 0, -1, RoleSetGrouping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				customCategoryEClass,
				CustomCategory.class,
				"CustomCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCustomCategory_CategorizedElements(),
				this.getDescribableElement(),
				null,
				"categorizedElements", null, 0, -1, CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getCustomCategory_SubCategories(),
				this.getContentCategory(),
				null,
				"subCategories", null, 0, -1, CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				deliveryProcessEClass,
				DeliveryProcess.class,
				"DeliveryProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDeliveryProcess_EducationMaterials(),
				this.getSupportingMaterial(),
				null,
				"educationMaterials", null, 0, -1, DeliveryProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDeliveryProcess_CommunicationsMaterials(),
				this.getSupportingMaterial(),
				null,
				"communicationsMaterials", null, 0, -1, DeliveryProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processEClass,
				org.eclipse.epf.uma.Process.class,
				"Process", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcess_IncludesPatterns(),
				this.getCapabilityPattern(),
				null,
				"includesPatterns", null, 0, -1, org.eclipse.epf.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcess_DefaultContext(),
				this.getMethodConfiguration(),
				null,
				"defaultContext", null, 1, 1, org.eclipse.epf.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcess_ValidContext(),
				this.getMethodConfiguration(),
				null,
				"validContext", null, 0, -1, org.eclipse.epf.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				capabilityPatternEClass,
				CapabilityPattern.class,
				"CapabilityPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				processPlanningTemplateEClass,
				ProcessPlanningTemplate.class,
				"ProcessPlanningTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessPlanningTemplate_BasedOnProcesses(),
				this.getProcess(),
				null,
				"basedOnProcesses", null, 0, -1, ProcessPlanningTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roadmapEClass,
				Roadmap.class,
				"Roadmap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				processComponentEClass,
				ProcessComponent.class,
				"ProcessComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessComponent_Interfaces(),
				this.getProcessComponentInterface(),
				null,
				"interfaces", null, 1, -1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessComponent_Process(),
				this.getProcess(),
				null,
				"process", null, 1, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processPackageEClass,
				ProcessPackage.class,
				"ProcessPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessPackage_ProcessElements(),
				this.getProcessElement(),
				null,
				"processElements", null, 0, -1, ProcessPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessPackage_Diagrams(),
				this.getDiagram(),
				null,
				"diagrams", null, 0, -1, ProcessPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processElementEClass,
				ProcessElement.class,
				"ProcessElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getProcessElement_ProcessLineName(),
				ecorePackage.getEString(),
				"processLineName", null, 0, 1, ProcessElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getProcessElement_TailoredProcessName(),
				ecorePackage.getEString(),
				"tailoredProcessName", null, 0, 1, ProcessElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processComponentInterfaceEClass,
				ProcessComponentInterface.class,
				"ProcessComponentInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessComponentInterface_InterfaceSpecifications(),
				this.getTaskDescriptor(),
				null,
				"interfaceSpecifications", null, 0, -1, ProcessComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessComponentInterface_InterfaceIO(),
				this.getWorkProductDescriptor(),
				null,
				"interfaceIO", null, 0, -1, ProcessComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processComponentDescriptorEClass,
				ProcessComponentDescriptor.class,
				"ProcessComponentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessComponentDescriptor_ProcessComponent(),
				this.getProcessComponent(),
				null,
				"ProcessComponent", null, 1, 1, ProcessComponentDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodPluginEClass,
				MethodPlugin.class,
				"MethodPlugin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodPlugin_UserChangeable(),
				ecorePackage.getEBooleanObject(),
				"userChangeable", "true", 0, 1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodPlugin_MethodPackages(),
				this.getMethodPackage(),
				null,
				"methodPackages", null, 1, -1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodPlugin_Bases(),
				this.getMethodPlugin(),
				null,
				"bases", null, 0, -1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				lineProcessEClass,
				LineProcess.class,
				"LineProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				processLinesPackageEClass,
				ProcessLinesPackage.class,
				"ProcessLinesPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessLinesPackage_ProcessLines(),
				this.getProcessLineElement(),
				null,
				"processLines", null, 0, -1, ProcessLinesPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessLinesPackage_Diagrams(),
				this.getDiagram(),
				null,
				"diagrams", null, 0, -1, ProcessLinesPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processLineElementEClass,
				ProcessLineElement.class,
				"ProcessLineElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				processLineComponentEClass,
				ProcessLineComponent.class,
				"ProcessLineComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessLineComponent_Interfaces(),
				this.getProcessLineComponentInterface(),
				null,
				"interfaces", null, 1, -1, ProcessLineComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessLineComponent_ProcessLine(),
				this.getProcess(),
				null,
				"processLine", null, 1, 1, ProcessLineComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessLineComponent_CoreProcess(),
				this.getCoreProcessPackage(),
				null,
				"coreProcess", null, 1, 1, ProcessLineComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processLineComponentPackageEClass,
				ProcessLineComponentPackage.class,
				"ProcessLineComponentPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessLineComponentPackage_ProcessLineComponent(),
				this.getProcessLineComponent(),
				null,
				"processLineComponent", null, 1, 1, ProcessLineComponentPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processLineComponentInterfaceEClass,
				ProcessLineComponentInterface.class,
				"ProcessLineComponentInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessLineComponentInterface_InterfaceSpecifications(),
				this.getTaskDescriptor(),
				null,
				"interfaceSpecifications", null, 0, -1, ProcessLineComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessLineComponentInterface_InterfaceIO(),
				this.getWorkProductDescriptor(),
				null,
				"interfaceIO", null, 0, -1, ProcessLineComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				coreProcessPackageEClass,
				CoreProcessPackage.class,
				"CoreProcessPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getCoreProcessPackage_LineName(),
				ecorePackage.getEString(),
				"lineName", "", 0, 1, CoreProcessPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				tailoredProcessEClass,
				TailoredProcess.class,
				"TailoredProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				tailoredProcessesPackageEClass,
				TailoredProcessesPackage.class,
				"TailoredProcessesPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTailoredProcessesPackage_TailoredProcessElements(),
				this.getProcessElement(),
				null,
				"tailoredProcessElements", null, 0, -1, TailoredProcessesPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTailoredProcessesPackage_Diagrams(),
				this.getDiagram(),
				null,
				"diagrams", null, 0, -1, TailoredProcessesPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				tailoredProcessComponentEClass,
				TailoredProcessComponent.class,
				"TailoredProcessComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTailoredProcessComponent_Atravesde(),
				this.getVariation(),
				null,
				"atravesde", null, 0, 1, TailoredProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTailoredProcessComponent_Occupations(),
				this.getOccupation(),
				null,
				"occupations", null, 0, -1, TailoredProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTailoredProcessComponent_Interfaces(),
				this.getProcessComponentInterface(),
				null,
				"interfaces", null, 1, -1, TailoredProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTailoredProcessComponent_TailoredProcess(),
				this.getProcess(),
				null,
				"tailoredProcess", null, 1, 1, TailoredProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				tailoredCoreProcessPackageEClass,
				TailoredCoreProcessPackage.class,
				"TailoredCoreProcessPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				variabilityElementEClass,
				VariabilityElement.class,
				"VariabilityElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getVariabilityElement_VariabilityType(),
				this.getVariabilityType(),
				"variabilityType", "na", 0, 1, VariabilityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getVariabilityElement_VariabilityBasedOnElement(),
				this.getVariabilityElement(),
				null,
				"variabilityBasedOnElement", null, 1, 1, VariabilityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodUnitEClass,
				MethodUnit.class,
				"MethodUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodUnit_Authors(),
				this.getString(),
				"authors", "", 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodUnit_ChangeDate(),
				this.getDate(),
				"changeDate", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getMethodUnit_ChangeDescription(),
				this.getString(),
				"changeDescription", "", 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodUnit_Version(),
				this.getString(),
				"version", "", 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodUnit_CopyrightStatement(),
				this.getSupportingMaterial(),
				null,
				"copyrightStatement", null, 1, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodConfigurationEClass,
				MethodConfiguration.class,
				"MethodConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_MethodPluginSelection(),
				this.getMethodPlugin(),
				null,
				"methodPluginSelection", null, 1, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_MethodPackageSelection(),
				this.getMethodPackage(),
				null,
				"methodPackageSelection", null, 1, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_ProcessViews(),
				this.getContentCategory(),
				null,
				"processViews", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_DefaultView(),
				this.getContentCategory(),
				null,
				"defaultView", null, 1, 1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_BaseConfigurations(),
				this.getMethodConfiguration(),
				null,
				"baseConfigurations", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_SubtractedCategory(),
				this.getContentCategory(),
				null,
				"subtractedCategory", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_AddedCategory(),
				this.getContentCategory(),
				null,
				"addedCategory", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processFamilyEClass,
				ProcessFamily.class,
				"ProcessFamily", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessFamily_DeliveryProcesses(),
				this.getDeliveryProcess(),
				null,
				"deliveryProcesses", null, 0, -1, ProcessFamily.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodLibraryEClass,
				MethodLibrary.class,
				"MethodLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getMethodLibrary_MethodPlugins(),
				this.getMethodPlugin(),
				null,
				"methodPlugins", null, 0, -1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodLibrary_PredefinedConfigurations(),
				this.getMethodConfiguration(),
				null,
				"predefinedConfigurations", null, 0, -1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variabilityEClass,
				Variability.class,
				"Variability", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varElementEClass,
				VarElement.class,
				"VarElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getVarElement_VpName(),
				ecorePackage.getEString(),
				"vpName", null, 0, 1, VarElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getVarElement_VId(),
				ecorePackage.getEString(),
				"vId", null, 0, 1, VarElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getVarElement_PathIcon(),
				ecorePackage.getEString(),
				"pathIcon", null, 0, 1, VarElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getVarElement_Description(),
				ecorePackage.getEString(),
				"description", null, 0, 1, VarElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getVarElement_Client(),
				this.getDependences(),
				null,
				"client", null, 0, -1, VarElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getVarElement_Supplier(),
				this.getDependences(),
				null,
				"supplier", null, 0, -1, VarElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				varPointEClass,
				VarPoint.class,
				"VarPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getVarPoint_IsImplicit(),
				ecorePackage.getEBoolean(),
				"isImplicit", null, 0, 1, VarPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getVarPoint_IsOpen(),
				ecorePackage.getEBoolean(),
				"isOpen", null, 0, 1, VarPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getVarPoint_Min(),
				ecorePackage.getEInt(),
				"min", null, 0, 1, VarPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getVarPoint_Max(),
				ecorePackage.getEInt(),
				"max", null, 0, 1, VarPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				vpActivityEClass,
				vpActivity.class,
				"vpActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpPhaseEClass,
				vpPhase.class,
				"vpPhase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpIterationEClass,
				vpIteration.class,
				"vpIteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpRoleDescriptorEClass,
				vpRoleDescriptor.class,
				"vpRoleDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpTaskDescriptorEClass,
				vpTaskDescriptor.class,
				"vpTaskDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpMilestoneEClass,
				vpMilestone.class,
				"vpMilestone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpTeamProfileEClass,
				vpTeamProfile.class,
				"vpTeamProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vpWorkProductDescriptorEClass,
				vpWorkProductDescriptor.class,
				"vpWorkProductDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				variantEClass,
				Variant.class,
				"Variant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varActivityEClass,
				VarActivity.class,
				"VarActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varPhaseEClass,
				VarPhase.class,
				"VarPhase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varIterationEClass,
				VarIteration.class,
				"VarIteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varRoleDescriptorEClass,
				VarRoleDescriptor.class,
				"VarRoleDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varTaskDescriptorEClass,
				VarTaskDescriptor.class,
				"VarTaskDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varMilestoneEClass,
				VarMilestone.class,
				"VarMilestone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varTeamProfileEClass,
				VarTeamProfile.class,
				"VarTeamProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				varWorkProductDescriptorEClass,
				VarWorkProductDescriptor.class,
				"VarWorkProductDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				activityEClass,
				Activity.class,
				"Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getActivity_BreakdownElements(),
				this.getBreakdownElement(),
				this.getBreakdownElement_SuperActivities(),
				"breakdownElements", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_Roadmaps(),
				this.getRoadmap(),
				null,
				"roadmaps", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_SupportingMaterials(),
				this.getSupportingMaterial(),
				null,
				"supportingMaterials", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_Checklists(),
				this.getChecklist(),
				null,
				"checklists", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_Concepts(),
				this.getConcept(),
				null,
				"concepts", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_Examples(),
				this.getExample(),
				null,
				"examples", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_Guidelines(),
				this.getGuideline(),
				null,
				"guidelines", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_ReusableAssets(),
				this.getReusableAsset(),
				null,
				"reusableAssets", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getActivity_IsEnactable(),
				ecorePackage.getEBooleanObject(),
				"isEnactable", "false", 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				procAspectEClass,
				ProcAspect.class,
				"ProcAspect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getProcAspect_Active(),
				ecorePackage.getEBoolean(),
				"active", null, 0, 1, ProcAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcAspect_Advice(),
				this.getProcAdvice(),
				null,
				"advice", null, 1, -1, ProcAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcAspect_Pointcut(),
				this.getProcPointcut(),
				null,
				"pointcut", null, 1, -1, ProcAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcAspect_Contain(),
				this.getVariant(),
				null,
				"contain", null, 0, 1, ProcAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcAspect_Utiliza(),
				this.getVarPoint(),
				null,
				"utiliza", null, 0, 1, ProcAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variantsListPackageEClass,
				VariantsListPackage.class,
				"VariantsListPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVariantsListPackage_Variants(),
				this.getVariant(),
				null,
				"variants", null, 0, -1, VariantsListPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variantsPackageEClass,
				VariantsPackage.class,
				"VariantsPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVariantsPackage_Variant(),
				this.getVariant(),
				null,
				"variant", null, 0, -1, VariantsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				varPointsPackageEClass,
				VarPointsPackage.class,
				"VarPointsPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVarPointsPackage_VarPoints(),
				this.getVarPoint(),
				null,
				"varPoints", null, 0, -1, VarPointsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variationsPackageEClass,
				VariationsPackage.class,
				"VariationsPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVariationsPackage_Variations(),
				this.getVariation(),
				null,
				"variations", null, 0, -1, VariationsPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variationEClass,
				Variation.class,
				"Variation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVariation_TipoVariacion(),
				this.getVariationType(),
				null,
				"tipoVariacion", null, 1, 1, Variation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(variationEClass, this.getArtifact(),
				"interpretation", 0, 1); //$NON-NLS-1$

		initEClass(
				variationTypeEClass,
				VariationType.class,
				"VariationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				occupationEClass,
				Occupation.class,
				"Occupation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getOccupation_Description(),
				this.getString(),
				"description", null, 0, 1, Occupation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getOccupation_IsObjective(),
				ecorePackage.getEBoolean(),
				"isObjective", null, 0, 1, Occupation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOccupation_Ocupadopor(),
				this.getVariant(),
				null,
				"ocupadopor", null, 0, 1, Occupation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOccupation_Esocupado(),
				this.getVarPoint(),
				null,
				"esocupado", null, 0, 1, Occupation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				procAdviceEClass,
				ProcAdvice.class,
				"ProcAdvice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcAdvice_RunsBy(),
				this.getProcPointcut(),
				null,
				"runsBy", null, 0, -1, ProcAdvice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				procPointcutEClass,
				ProcPointcut.class,
				"ProcPointcut", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcPointcut_Selfcomposition(),
				this.getProcPointcut(),
				null,
				"selfcomposition", null, 0, 1, ProcPointcut.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcPointcut_Genera(),
				this.getOccupation(),
				null,
				"genera", null, 0, 1, ProcPointcut.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				dependencesEClass,
				Dependences.class,
				"Dependences", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDependences_IsInclusive(),
				ecorePackage.getEBoolean(),
				"isInclusive", null, 0, 1, Dependences.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variant2varPEClass,
				variant2varP.class,
				"variant2varP", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getvariant2varP_Client(),
				this.getVariant(),
				null,
				"client", null, 0, 1, variant2varP.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getvariant2varP_Supplier(),
				this.getVarPoint(),
				null,
				"supplier", null, 0, 1, variant2varP.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variant2variantEClass,
				variant2variant.class,
				"variant2variant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getvariant2variant_Client(),
				this.getVariant(),
				null,
				"client", null, 0, 1, variant2variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getvariant2variant_Supplier(),
				this.getVariant(),
				null,
				"supplier", null, 0, 1, variant2variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				varp2variantEClass,
				varp2variant.class,
				"varp2variant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getvarp2variant_Client(),
				this.getVarPoint(),
				null,
				"client", null, 0, 1, varp2variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getvarp2variant_Supplier(),
				this.getVariant(),
				null,
				"supplier", null, 0, 1, varp2variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				varP2varPEClass,
				varP2varP.class,
				"varP2varP", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getvarP2varP_Supplier(),
				this.getVarPoint(),
				null,
				"supplier", null, 0, 1, varP2varP.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getvarP2varP_Client(),
				this.getVarPoint(),
				null,
				"client", null, 0, 1, varP2varP.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processLineEClass,
				ProcessLine.class,
				"ProcessLine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessLine_Genera(),
				this.getTailoredProcessComponent(),
				null,
				"genera", null, 0, 1, ProcessLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessLine_Compuestapor(),
				this.getVarElement(),
				null,
				"compuestapor", null, 0, 1, ProcessLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				optativeEClass,
				Optative.class,
				"Optative", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				mandatoryEClass,
				Mandatory.class,
				"Mandatory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				optionalEClass,
				Optional.class,
				"Optional", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vIterationEClass,
				vIteration.class,
				"vIteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vPhaseEClass,
				vPhase.class,
				"vPhase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				vActivityEClass,
				vActivity.class,
				"vActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(workOrderTypeEEnum, WorkOrderType.class, "WorkOrderType"); //$NON-NLS-1$
		addEEnumLiteral(workOrderTypeEEnum,
				WorkOrderType.FINISH_TO_START_LITERAL);
		addEEnumLiteral(workOrderTypeEEnum,
				WorkOrderType.FINISH_TO_FINISH_LITERAL);
		addEEnumLiteral(workOrderTypeEEnum,
				WorkOrderType.START_TO_START_LITERAL);
		addEEnumLiteral(workOrderTypeEEnum,
				WorkOrderType.START_TO_FINISH_LITERAL);

		initEEnum(pseudoStateKindEEnum, PseudoStateKind.class,
				"PseudoStateKind"); //$NON-NLS-1$
		addEEnumLiteral(pseudoStateKindEEnum, PseudoStateKind.INITIAL_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum, PseudoStateKind.JOIN_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum, PseudoStateKind.FORK_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum, PseudoStateKind.JUNCTION_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum, PseudoStateKind.CHOICE_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum,
				PseudoStateKind.ENTRY_POINT_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum,
				PseudoStateKind.EXIT_POINT_LITERAL);
		addEEnumLiteral(pseudoStateKindEEnum, PseudoStateKind.TERMINATE_LITERAL);

		initEEnum(variabilityTypeEEnum, VariabilityType.class,
				"VariabilityType"); //$NON-NLS-1$
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.NA_LITERAL);
		addEEnumLiteral(variabilityTypeEEnum,
				VariabilityType.CONTRIBUTES_LITERAL);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.EXTENDS_LITERAL);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.REPLACES_LITERAL);
		addEEnumLiteral(variabilityTypeEEnum,
				VariabilityType.LOCAL_CONTRIBUTION_LITERAL);
		addEEnumLiteral(variabilityTypeEEnum,
				VariabilityType.LOCAL_REPLACEMENT_LITERAL);
		addEEnumLiteral(variabilityTypeEEnum,
				VariabilityType.EXTENDS_REPLACES_LITERAL);

		// Initialize data types
		initEDataType(dateEDataType, Date.class,
				"Date", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(uriEDataType, java.net.URI.class,
				"Uri", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(
				unlimitedNaturalEDataType,
				int.class,
				"UnlimitedNatural", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(stringEDataType, String.class,
				"String", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(setEDataType, Set.class,
				"Set", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(sequenceEDataType, List.class,
				"Sequence", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(integerEDataType, int.class,
				"Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(floatEDataType, Float.class,
				"Float", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //UmaPackageImpl