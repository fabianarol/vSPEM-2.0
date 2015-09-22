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

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ActivityDescription;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ArtifactDescription;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentDescription;
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
import org.eclipse.epf.uma.DescriptorDescription;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.DiagramLink;
import org.eclipse.epf.uma.Dimension;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Ellipse;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.GuidanceDescription;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Image;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.LineProcess;
import org.eclipse.epf.uma.Mandatory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Optative;
import org.eclipse.epf.uma.Optional;
import org.eclipse.epf.uma.Outcome;
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
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarActivity;
import org.eclipse.epf.uma.VarIteration;
import org.eclipse.epf.uma.VarMilestone;
import org.eclipse.epf.uma.VarPhase;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.VarPointsPackage;
import org.eclipse.epf.uma.VarRoleDescriptor;
import org.eclipse.epf.uma.VarTaskDescriptor;
import org.eclipse.epf.uma.VarTeamProfile;
import org.eclipse.epf.uma.VarWorkProductDescriptor;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariantsListPackage;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.Variation;
import org.eclipse.epf.uma.VariationType;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.Vertex;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UmaFactoryImpl extends EFactoryImpl implements UmaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UmaFactory init() {
		try {
			UmaFactory theUmaFactory = (UmaFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.eclipse.org/epf/uma/1.0.4/uma.ecore"); //$NON-NLS-1$ 
			if (theUmaFactory != null) {
				return theUmaFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UmaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case UmaPackage.PACKAGE:
			return (EObject) createPackage();
		case UmaPackage.CONSTRAINT:
			return (EObject) createConstraint();
		case UmaPackage.METHOD_ELEMENT_PROPERTY:
			return (EObject) createMethodElementProperty();
		case UmaPackage.CONTENT_DESCRIPTION:
			return (EObject) createContentDescription();
		case UmaPackage.SECTION:
			return (EObject) createSection();
		case UmaPackage.ROLE:
			return (EObject) createRole();
		case UmaPackage.TASK:
			return (EObject) createTask();
		case UmaPackage.STEP:
			return (EObject) createStep();
		case UmaPackage.ARTIFACT:
			return (EObject) createArtifact();
		case UmaPackage.DELIVERABLE:
			return (EObject) createDeliverable();
		case UmaPackage.OUTCOME:
			return (EObject) createOutcome();
		case UmaPackage.CONTENT_PACKAGE:
			return (EObject) createContentPackage();
		case UmaPackage.ARTIFACT_DESCRIPTION:
			return (EObject) createArtifactDescription();
		case UmaPackage.WORK_PRODUCT_DESCRIPTION:
			return (EObject) createWorkProductDescription();
		case UmaPackage.DELIVERABLE_DESCRIPTION:
			return (EObject) createDeliverableDescription();
		case UmaPackage.ROLE_DESCRIPTION:
			return (EObject) createRoleDescription();
		case UmaPackage.TASK_DESCRIPTION:
			return (EObject) createTaskDescription();
		case UmaPackage.GUIDANCE_DESCRIPTION:
			return (EObject) createGuidanceDescription();
		case UmaPackage.PRACTICE_DESCRIPTION:
			return (EObject) createPracticeDescription();
		case UmaPackage.POINT:
			return (EObject) createPoint();
		case UmaPackage.DIAGRAM_LINK:
			return (EObject) createDiagramLink();
		case UmaPackage.GRAPH_CONNECTOR:
			return (EObject) createGraphConnector();
		case UmaPackage.DIMENSION:
			return (EObject) createDimension();
		case UmaPackage.REFERENCE:
			return (EObject) createReference();
		case UmaPackage.PROPERTY:
			return (EObject) createProperty();
		case UmaPackage.GRAPH_EDGE:
			return (EObject) createGraphEdge();
		case UmaPackage.DIAGRAM:
			return (EObject) createDiagram();
		case UmaPackage.GRAPH_NODE:
			return (EObject) createGraphNode();
		case UmaPackage.SIMPLE_SEMANTIC_MODEL_ELEMENT:
			return (EObject) createSimpleSemanticModelElement();
		case UmaPackage.UMA_SEMANTIC_MODEL_BRIDGE:
			return (EObject) createUMASemanticModelBridge();
		case UmaPackage.CORE_SEMANTIC_MODEL_BRIDGE:
			return (EObject) createCoreSemanticModelBridge();
		case UmaPackage.TEXT_ELEMENT:
			return (EObject) createTextElement();
		case UmaPackage.IMAGE:
			return (EObject) createImage();
		case UmaPackage.POLYLINE:
			return (EObject) createPolyline();
		case UmaPackage.ELLIPSE:
			return (EObject) createEllipse();
		case UmaPackage.MILESTONE:
			return (EObject) createMilestone();
		case UmaPackage.ITERATION:
			return (EObject) createIteration();
		case UmaPackage.PHASE:
			return (EObject) createPhase();
		case UmaPackage.TEAM_PROFILE:
			return (EObject) createTeamProfile();
		case UmaPackage.ROLE_DESCRIPTOR:
			return (EObject) createRoleDescriptor();
		case UmaPackage.WORK_ORDER:
			return (EObject) createWorkOrder();
		case UmaPackage.PLANNING_DATA:
			return (EObject) createPlanningData();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR:
			return (EObject) createWorkProductDescriptor();
		case UmaPackage.TASK_DESCRIPTOR:
			return (EObject) createTaskDescriptor();
		case UmaPackage.COMPOSITE_ROLE:
			return (EObject) createCompositeRole();
		case UmaPackage.BREAKDOWN_ELEMENT_DESCRIPTION:
			return (EObject) createBreakdownElementDescription();
		case UmaPackage.ACTIVITY_DESCRIPTION:
			return (EObject) createActivityDescription();
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION:
			return (EObject) createDeliveryProcessDescription();
		case UmaPackage.PROCESS_DESCRIPTION:
			return (EObject) createProcessDescription();
		case UmaPackage.DESCRIPTOR_DESCRIPTION:
			return (EObject) createDescriptorDescription();
		case UmaPackage.CONCEPT:
			return (EObject) createConcept();
		case UmaPackage.CHECKLIST:
			return (EObject) createChecklist();
		case UmaPackage.EXAMPLE:
			return (EObject) createExample();
		case UmaPackage.GUIDELINE:
			return (EObject) createGuideline();
		case UmaPackage.REPORT:
			return (EObject) createReport();
		case UmaPackage.TEMPLATE:
			return (EObject) createTemplate();
		case UmaPackage.SUPPORTING_MATERIAL:
			return (EObject) createSupportingMaterial();
		case UmaPackage.TOOL_MENTOR:
			return (EObject) createToolMentor();
		case UmaPackage.WHITEPAPER:
			return (EObject) createWhitepaper();
		case UmaPackage.TERM_DEFINITION:
			return (EObject) createTermDefinition();
		case UmaPackage.PRACTICE:
			return (EObject) createPractice();
		case UmaPackage.ESTIMATION_CONSIDERATIONS:
			return (EObject) createEstimationConsiderations();
		case UmaPackage.REUSABLE_ASSET:
			return (EObject) createReusableAsset();
		case UmaPackage.STATE:
			return (EObject) createState();
		case UmaPackage.VERTEX:
			return (EObject) createVertex();
		case UmaPackage.REGION:
			return (EObject) createRegion();
		case UmaPackage.STATE_MACHINE:
			return (EObject) createStateMachine();
		case UmaPackage.TRANSITION:
			return (EObject) createTransition();
		case UmaPackage.PSEUDO_STATE:
			return (EObject) createPseudoState();
		case UmaPackage.DISCIPLINE:
			return (EObject) createDiscipline();
		case UmaPackage.ROLE_SET:
			return (EObject) createRoleSet();
		case UmaPackage.DOMAIN:
			return (EObject) createDomain();
		case UmaPackage.PRUEBAS_LINEAS:
			return (EObject) createPruebasLineas();
		case UmaPackage.WORK_PRODUCT_TYPE:
			return (EObject) createWorkProductType();
		case UmaPackage.DISCIPLINE_GROUPING:
			return (EObject) createDisciplineGrouping();
		case UmaPackage.TOOL:
			return (EObject) createTool();
		case UmaPackage.ROLE_SET_GROUPING:
			return (EObject) createRoleSetGrouping();
		case UmaPackage.CUSTOM_CATEGORY:
			return (EObject) createCustomCategory();
		case UmaPackage.DELIVERY_PROCESS:
			return (EObject) createDeliveryProcess();
		case UmaPackage.CAPABILITY_PATTERN:
			return (EObject) createCapabilityPattern();
		case UmaPackage.PROCESS_PLANNING_TEMPLATE:
			return (EObject) createProcessPlanningTemplate();
		case UmaPackage.ROADMAP:
			return (EObject) createRoadmap();
		case UmaPackage.PROCESS_COMPONENT:
			return (EObject) createProcessComponent();
		case UmaPackage.PROCESS_PACKAGE:
			return (EObject) createProcessPackage();
		case UmaPackage.PROCESS_COMPONENT_INTERFACE:
			return (EObject) createProcessComponentInterface();
		case UmaPackage.PROCESS_COMPONENT_DESCRIPTOR:
			return (EObject) createProcessComponentDescriptor();
		case UmaPackage.METHOD_PLUGIN:
			return (EObject) createMethodPlugin();
		case UmaPackage.LINE_PROCESS:
			return (EObject) createLineProcess();
		case UmaPackage.PROCESS_LINES_PACKAGE:
			return (EObject) createProcessLinesPackage();
		case UmaPackage.PROCESS_LINE_ELEMENT:
			return (EObject) createProcessLineElement();
		case UmaPackage.PROCESS_LINE_COMPONENT:
			return (EObject) createProcessLineComponent();
		case UmaPackage.PROCESS_LINE_COMPONENT_PACKAGE:
			return (EObject) createProcessLineComponentPackage();
		case UmaPackage.PROCESS_LINE_COMPONENT_INTERFACE:
			return (EObject) createProcessLineComponentInterface();
		case UmaPackage.CORE_PROCESS_PACKAGE:
			return (EObject) createCoreProcessPackage();
		case UmaPackage.TAILORED_PROCESS:
			return (EObject) createTailoredProcess();
		case UmaPackage.TAILORED_PROCESSES_PACKAGE:
			return (EObject) createTailoredProcessesPackage();
		case UmaPackage.TAILORED_PROCESS_COMPONENT:
			return (EObject) createTailoredProcessComponent();
		case UmaPackage.TAILORED_CORE_PROCESS_PACKAGE:
			return (EObject) createTailoredCoreProcessPackage();
		case UmaPackage.METHOD_CONFIGURATION:
			return (EObject) createMethodConfiguration();
		case UmaPackage.PROCESS_FAMILY:
			return (EObject) createProcessFamily();
		case UmaPackage.METHOD_LIBRARY:
			return (EObject) createMethodLibrary();
		case UmaPackage.VAR_POINT:
			return (EObject) createVarPoint();
		case UmaPackage.VP_ACTIVITY:
			return (EObject) createvpActivity();
		case UmaPackage.VP_PHASE:
			return (EObject) createvpPhase();
		case UmaPackage.VP_ITERATION:
			return (EObject) createvpIteration();
		case UmaPackage.VP_ROLE_DESCRIPTOR:
			return (EObject) createvpRoleDescriptor();
		case UmaPackage.VP_TASK_DESCRIPTOR:
			return (EObject) createvpTaskDescriptor();
		case UmaPackage.VP_MILESTONE:
			return (EObject) createvpMilestone();
		case UmaPackage.VP_TEAM_PROFILE:
			return (EObject) createvpTeamProfile();
		case UmaPackage.VP_WORK_PRODUCT_DESCRIPTOR:
			return (EObject) createvpWorkProductDescriptor();
		case UmaPackage.VARIANT:
			return (EObject) createVariant();
		case UmaPackage.VAR_ACTIVITY:
			return (EObject) createVarActivity();
		case UmaPackage.VAR_PHASE:
			return (EObject) createVarPhase();
		case UmaPackage.VAR_ITERATION:
			return (EObject) createVarIteration();
		case UmaPackage.VAR_ROLE_DESCRIPTOR:
			return (EObject) createVarRoleDescriptor();
		case UmaPackage.VAR_TASK_DESCRIPTOR:
			return (EObject) createVarTaskDescriptor();
		case UmaPackage.VAR_MILESTONE:
			return (EObject) createVarMilestone();
		case UmaPackage.VAR_TEAM_PROFILE:
			return (EObject) createVarTeamProfile();
		case UmaPackage.VAR_WORK_PRODUCT_DESCRIPTOR:
			return (EObject) createVarWorkProductDescriptor();
		case UmaPackage.ACTIVITY:
			return (EObject) createActivity();
		case UmaPackage.PROC_ASPECT:
			return (EObject) createProcAspect();
		case UmaPackage.VARIANTS_LIST_PACKAGE:
			return (EObject) createVariantsListPackage();
		case UmaPackage.VARIANTS_PACKAGE:
			return (EObject) createVariantsPackage();
		case UmaPackage.VAR_POINTS_PACKAGE:
			return (EObject) createVarPointsPackage();
		case UmaPackage.VARIATIONS_PACKAGE:
			return (EObject) createVariationsPackage();
		case UmaPackage.VARIATION:
			return (EObject) createVariation();
		case UmaPackage.VARIATION_TYPE:
			return (EObject) createVariationType();
		case UmaPackage.OCCUPATION:
			return (EObject) createOccupation();
		case UmaPackage.PROC_ADVICE:
			return (EObject) createProcAdvice();
		case UmaPackage.PROC_POINTCUT:
			return (EObject) createProcPointcut();
		case UmaPackage.DEPENDENCES:
			return (EObject) createDependences();
		case UmaPackage.VARIANT2VAR_P:
			return (EObject) createvariant2varP();
		case UmaPackage.VARIANT2VARIANT:
			return (EObject) createvariant2variant();
		case UmaPackage.VARP2VARIANT:
			return (EObject) createvarp2variant();
		case UmaPackage.VAR_P2VAR_P:
			return (EObject) createvarP2varP();
		case UmaPackage.PROCESS_LINE:
			return (EObject) createProcessLine();
		case UmaPackage.OPTATIVE:
			return (EObject) createOptative();
		case UmaPackage.MANDATORY:
			return (EObject) createMandatory();
		case UmaPackage.OPTIONAL:
			return (EObject) createOptional();
		case UmaPackage.VITERATION:
			return (EObject) createvIteration();
		case UmaPackage.VPHASE:
			return (EObject) createvPhase();
		case UmaPackage.VACTIVITY:
			return (EObject) createvActivity();
		default:
			throw new IllegalArgumentException(
					"The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case UmaPackage.WORK_ORDER_TYPE:
			return createWorkOrderTypeFromString(eDataType, initialValue);
		case UmaPackage.PSEUDO_STATE_KIND:
			return createPseudoStateKindFromString(eDataType, initialValue);
		case UmaPackage.VARIABILITY_TYPE:
			return createVariabilityTypeFromString(eDataType, initialValue);
		case UmaPackage.DATE:
			return createDateFromString(eDataType, initialValue);
		case UmaPackage.URI:
			return createUriFromString(eDataType, initialValue);
		case UmaPackage.UNLIMITED_NATURAL:
			return createUnlimitedNaturalFromString(eDataType, initialValue);
		case UmaPackage.STRING:
			return createStringFromString(eDataType, initialValue);
		case UmaPackage.SET:
			return createSetFromString(eDataType, initialValue);
		case UmaPackage.SEQUENCE:
			return createSequenceFromString(eDataType, initialValue);
		case UmaPackage.INTEGER:
			return createIntegerFromString(eDataType, initialValue);
		case UmaPackage.FLOAT:
			return createFloatFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException(
					"The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case UmaPackage.WORK_ORDER_TYPE:
			return convertWorkOrderTypeToString(eDataType, instanceValue);
		case UmaPackage.PSEUDO_STATE_KIND:
			return convertPseudoStateKindToString(eDataType, instanceValue);
		case UmaPackage.VARIABILITY_TYPE:
			return convertVariabilityTypeToString(eDataType, instanceValue);
		case UmaPackage.DATE:
			return convertDateToString(eDataType, instanceValue);
		case UmaPackage.URI:
			return convertUriToString(eDataType, instanceValue);
		case UmaPackage.UNLIMITED_NATURAL:
			return convertUnlimitedNaturalToString(eDataType, instanceValue);
		case UmaPackage.STRING:
			return convertStringToString(eDataType, instanceValue);
		case UmaPackage.SET:
			return convertSetToString(eDataType, instanceValue);
		case UmaPackage.SEQUENCE:
			return convertSequenceToString(eDataType, instanceValue);
		case UmaPackage.INTEGER:
			return convertIntegerToString(eDataType, instanceValue);
		case UmaPackage.FLOAT:
			return convertFloatToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException(
					"The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint createConstraint() {
		ConstraintImpl constraint = new ConstraintImpl();
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodElementProperty createMethodElementProperty() {
		MethodElementPropertyImpl methodElementProperty = new MethodElementPropertyImpl();
		return methodElementProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentDescription createContentDescription() {
		ContentDescriptionImpl contentDescription = new ContentDescriptionImpl();
		return contentDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section createSection() {
		SectionImpl section = new SectionImpl();
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Task createTask() {
		TaskImpl task = new TaskImpl();
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step createStep() {
		StepImpl step = new StepImpl();
		return step;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Artifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Deliverable createDeliverable() {
		DeliverableImpl deliverable = new DeliverableImpl();
		return deliverable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Outcome createOutcome() {
		OutcomeImpl outcome = new OutcomeImpl();
		return outcome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentPackage createContentPackage() {
		ContentPackageImpl contentPackage = new ContentPackageImpl();
		return contentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactDescription createArtifactDescription() {
		ArtifactDescriptionImpl artifactDescription = new ArtifactDescriptionImpl();
		return artifactDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductDescription createWorkProductDescription() {
		WorkProductDescriptionImpl workProductDescription = new WorkProductDescriptionImpl();
		return workProductDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliverableDescription createDeliverableDescription() {
		DeliverableDescriptionImpl deliverableDescription = new DeliverableDescriptionImpl();
		return deliverableDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleDescription createRoleDescription() {
		RoleDescriptionImpl roleDescription = new RoleDescriptionImpl();
		return roleDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskDescription createTaskDescription() {
		TaskDescriptionImpl taskDescription = new TaskDescriptionImpl();
		return taskDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GuidanceDescription createGuidanceDescription() {
		GuidanceDescriptionImpl guidanceDescription = new GuidanceDescriptionImpl();
		return guidanceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PracticeDescription createPracticeDescription() {
		PracticeDescriptionImpl practiceDescription = new PracticeDescriptionImpl();
		return practiceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point createPoint() {
		PointImpl point = new PointImpl();
		return point;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramLink createDiagramLink() {
		DiagramLinkImpl diagramLink = new DiagramLinkImpl();
		return diagramLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphConnector createGraphConnector() {
		GraphConnectorImpl graphConnector = new GraphConnectorImpl();
		return graphConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension createDimension() {
		DimensionImpl dimension = new DimensionImpl();
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphEdge createGraphEdge() {
		GraphEdgeImpl graphEdge = new GraphEdgeImpl();
		return graphEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram createDiagram() {
		DiagramImpl diagram = new DiagramImpl();
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphNode createGraphNode() {
		GraphNodeImpl graphNode = new GraphNodeImpl();
		return graphNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleSemanticModelElement createSimpleSemanticModelElement() {
		SimpleSemanticModelElementImpl simpleSemanticModelElement = new SimpleSemanticModelElementImpl();
		return simpleSemanticModelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UMASemanticModelBridge createUMASemanticModelBridge() {
		UMASemanticModelBridgeImpl umaSemanticModelBridge = new UMASemanticModelBridgeImpl();
		return umaSemanticModelBridge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreSemanticModelBridge createCoreSemanticModelBridge() {
		CoreSemanticModelBridgeImpl coreSemanticModelBridge = new CoreSemanticModelBridgeImpl();
		return coreSemanticModelBridge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextElement createTextElement() {
		TextElementImpl textElement = new TextElementImpl();
		return textElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image createImage() {
		ImageImpl image = new ImageImpl();
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Polyline createPolyline() {
		PolylineImpl polyline = new PolylineImpl();
		return polyline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ellipse createEllipse() {
		EllipseImpl ellipse = new EllipseImpl();
		return ellipse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity createActivity() {
		ActivityImpl activity = new ActivityImpl();
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Milestone createMilestone() {
		MilestoneImpl milestone = new MilestoneImpl();
		return milestone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iteration createIteration() {
		IterationImpl iteration = new IterationImpl();
		return iteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phase createPhase() {
		PhaseImpl phase = new PhaseImpl();
		return phase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TeamProfile createTeamProfile() {
		TeamProfileImpl teamProfile = new TeamProfileImpl();
		return teamProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleDescriptor createRoleDescriptor() {
		RoleDescriptorImpl roleDescriptor = new RoleDescriptorImpl();
		return roleDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkOrder createWorkOrder() {
		WorkOrderImpl workOrder = new WorkOrderImpl();
		return workOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanningData createPlanningData() {
		PlanningDataImpl planningData = new PlanningDataImpl();
		return planningData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskDescriptor createTaskDescriptor() {
		TaskDescriptorImpl taskDescriptor = new TaskDescriptorImpl();
		return taskDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductDescriptor createWorkProductDescriptor() {
		WorkProductDescriptorImpl workProductDescriptor = new WorkProductDescriptorImpl();
		return workProductDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeRole createCompositeRole() {
		CompositeRoleImpl compositeRole = new CompositeRoleImpl();
		return compositeRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakdownElementDescription createBreakdownElementDescription() {
		BreakdownElementDescriptionImpl breakdownElementDescription = new BreakdownElementDescriptionImpl();
		return breakdownElementDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityDescription createActivityDescription() {
		ActivityDescriptionImpl activityDescription = new ActivityDescriptionImpl();
		return activityDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryProcessDescription createDeliveryProcessDescription() {
		DeliveryProcessDescriptionImpl deliveryProcessDescription = new DeliveryProcessDescriptionImpl();
		return deliveryProcessDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDescription createProcessDescription() {
		ProcessDescriptionImpl processDescription = new ProcessDescriptionImpl();
		return processDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptorDescription createDescriptorDescription() {
		DescriptorDescriptionImpl descriptorDescription = new DescriptorDescriptionImpl();
		return descriptorDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concept createConcept() {
		ConceptImpl concept = new ConceptImpl();
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Checklist createChecklist() {
		ChecklistImpl checklist = new ChecklistImpl();
		return checklist;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Example createExample() {
		ExampleImpl example = new ExampleImpl();
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Guideline createGuideline() {
		GuidelineImpl guideline = new GuidelineImpl();
		return guideline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EstimationConsiderations createEstimationConsiderations() {
		EstimationConsiderationsImpl estimationConsiderations = new EstimationConsiderationsImpl();
		return estimationConsiderations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Report createReport() {
		ReportImpl report = new ReportImpl();
		return report;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportingMaterial createSupportingMaterial() {
		SupportingMaterialImpl supportingMaterial = new SupportingMaterialImpl();
		return supportingMaterial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolMentor createToolMentor() {
		ToolMentorImpl toolMentor = new ToolMentorImpl();
		return toolMentor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Whitepaper createWhitepaper() {
		WhitepaperImpl whitepaper = new WhitepaperImpl();
		return whitepaper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TermDefinition createTermDefinition() {
		TermDefinitionImpl termDefinition = new TermDefinitionImpl();
		return termDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Practice createPractice() {
		PracticeImpl practice = new PracticeImpl();
		return practice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReusableAsset createReusableAsset() {
		ReusableAssetImpl reusableAsset = new ReusableAssetImpl();
		return reusableAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex createVertex() {
		VertexImpl vertex = new VertexImpl();
		return vertex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region createRegion() {
		RegionImpl region = new RegionImpl();
		return region;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachine createStateMachine() {
		StateMachineImpl stateMachine = new StateMachineImpl();
		return stateMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PseudoState createPseudoState() {
		PseudoStateImpl pseudoState = new PseudoStateImpl();
		return pseudoState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Discipline createDiscipline() {
		DisciplineImpl discipline = new DisciplineImpl();
		return discipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleSet createRoleSet() {
		RoleSetImpl roleSet = new RoleSetImpl();
		return roleSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Domain createDomain() {
		DomainImpl domain = new DomainImpl();
		return domain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PruebasLineas createPruebasLineas() {
		PruebasLineasImpl pruebasLineas = new PruebasLineasImpl();
		return pruebasLineas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductType createWorkProductType() {
		WorkProductTypeImpl workProductType = new WorkProductTypeImpl();
		return workProductType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisciplineGrouping createDisciplineGrouping() {
		DisciplineGroupingImpl disciplineGrouping = new DisciplineGroupingImpl();
		return disciplineGrouping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tool createTool() {
		ToolImpl tool = new ToolImpl();
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleSetGrouping createRoleSetGrouping() {
		RoleSetGroupingImpl roleSetGrouping = new RoleSetGroupingImpl();
		return roleSetGrouping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomCategory createCustomCategory() {
		CustomCategoryImpl customCategory = new CustomCategoryImpl();
		return customCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryProcess createDeliveryProcess() {
		DeliveryProcessImpl deliveryProcess = new DeliveryProcessImpl();
		return deliveryProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityPattern createCapabilityPattern() {
		CapabilityPatternImpl capabilityPattern = new CapabilityPatternImpl();
		return capabilityPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessPlanningTemplate createProcessPlanningTemplate() {
		ProcessPlanningTemplateImpl processPlanningTemplate = new ProcessPlanningTemplateImpl();
		return processPlanningTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Roadmap createRoadmap() {
		RoadmapImpl roadmap = new RoadmapImpl();
		return roadmap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessComponent createProcessComponent() {
		ProcessComponentImpl processComponent = new ProcessComponentImpl();
		return processComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessPackage createProcessPackage() {
		ProcessPackageImpl processPackage = new ProcessPackageImpl();
		return processPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessComponentInterface createProcessComponentInterface() {
		ProcessComponentInterfaceImpl processComponentInterface = new ProcessComponentInterfaceImpl();
		return processComponentInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessComponentDescriptor createProcessComponentDescriptor() {
		ProcessComponentDescriptorImpl processComponentDescriptor = new ProcessComponentDescriptorImpl();
		return processComponentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodPlugin createMethodPlugin() {
		MethodPluginImpl methodPlugin = new MethodPluginImpl();
		return methodPlugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLinesPackage createProcessLinesPackage() {
		ProcessLinesPackageImpl processLinesPackage = new ProcessLinesPackageImpl();
		return processLinesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineElement createProcessLineElement() {
		ProcessLineElementImpl processLineElement = new ProcessLineElementImpl();
		return processLineElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineComponent createProcessLineComponent() {
		ProcessLineComponentImpl processLineComponent = new ProcessLineComponentImpl();
		return processLineComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineComponentPackage createProcessLineComponentPackage() {
		ProcessLineComponentPackageImpl processLineComponentPackage = new ProcessLineComponentPackageImpl();
		return processLineComponentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLineComponentInterface createProcessLineComponentInterface() {
		ProcessLineComponentInterfaceImpl processLineComponentInterface = new ProcessLineComponentInterfaceImpl();
		return processLineComponentInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreProcessPackage createCoreProcessPackage() {
		CoreProcessPackageImpl coreProcessPackage = new CoreProcessPackageImpl();
		return coreProcessPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredProcess createTailoredProcess() {
		TailoredProcessImpl tailoredProcess = new TailoredProcessImpl();
		return tailoredProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredProcessesPackage createTailoredProcessesPackage() {
		TailoredProcessesPackageImpl tailoredProcessesPackage = new TailoredProcessesPackageImpl();
		return tailoredProcessesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredProcessComponent createTailoredProcessComponent() {
		TailoredProcessComponentImpl tailoredProcessComponent = new TailoredProcessComponentImpl();
		return tailoredProcessComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TailoredCoreProcessPackage createTailoredCoreProcessPackage() {
		TailoredCoreProcessPackageImpl tailoredCoreProcessPackage = new TailoredCoreProcessPackageImpl();
		return tailoredCoreProcessPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationsPackage createVariationsPackage() {
		VariationsPackageImpl variationsPackage = new VariationsPackageImpl();
		return variationsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariantsPackage createVariantsPackage() {
		VariantsPackageImpl variantsPackage = new VariantsPackageImpl();
		return variantsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPointsPackage createVarPointsPackage() {
		VarPointsPackageImpl varPointsPackage = new VarPointsPackageImpl();
		return varPointsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineProcess createLineProcess() {
		LineProcessImpl lineProcess = new LineProcessImpl();
		return lineProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodConfiguration createMethodConfiguration() {
		MethodConfigurationImpl methodConfiguration = new MethodConfigurationImpl();
		return methodConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessFamily createProcessFamily() {
		ProcessFamilyImpl processFamily = new ProcessFamilyImpl();
		return processFamily;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodLibrary createMethodLibrary() {
		MethodLibraryImpl methodLibrary = new MethodLibraryImpl();
		return methodLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPoint createVarPoint() {
		VarPointImpl varPoint = new VarPointImpl();
		return varPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variant createVariant() {
		VariantImpl variant = new VariantImpl();
		return variant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarActivity createVarActivity() {
		VarActivityImpl varActivity = new VarActivityImpl();
		return varActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarPhase createVarPhase() {
		VarPhaseImpl varPhase = new VarPhaseImpl();
		return varPhase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarIteration createVarIteration() {
		VarIterationImpl varIteration = new VarIterationImpl();
		return varIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarRoleDescriptor createVarRoleDescriptor() {
		VarRoleDescriptorImpl varRoleDescriptor = new VarRoleDescriptorImpl();
		return varRoleDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarTaskDescriptor createVarTaskDescriptor() {
		VarTaskDescriptorImpl varTaskDescriptor = new VarTaskDescriptorImpl();
		return varTaskDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarMilestone createVarMilestone() {
		VarMilestoneImpl varMilestone = new VarMilestoneImpl();
		return varMilestone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarTeamProfile createVarTeamProfile() {
		VarTeamProfileImpl varTeamProfile = new VarTeamProfileImpl();
		return varTeamProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarWorkProductDescriptor createVarWorkProductDescriptor() {
		VarWorkProductDescriptorImpl varWorkProductDescriptor = new VarWorkProductDescriptorImpl();
		return varWorkProductDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcAspect createProcAspect() {
		ProcAspectImpl procAspect = new ProcAspectImpl();
		return procAspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariantsListPackage createVariantsListPackage() {
		VariantsListPackageImpl variantsListPackage = new VariantsListPackageImpl();
		return variantsListPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variation createVariation() {
		VariationImpl variation = new VariationImpl();
		return variation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Occupation createOccupation() {
		OccupationImpl occupation = new OccupationImpl();
		return occupation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcAdvice createProcAdvice() {
		ProcAdviceImpl procAdvice = new ProcAdviceImpl();
		return procAdvice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcPointcut createProcPointcut() {
		ProcPointcutImpl procPointcut = new ProcPointcutImpl();
		return procPointcut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dependences createDependences() {
		DependencesImpl dependences = new DependencesImpl();
		return dependences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public variant2varP createvariant2varP() {
		variant2varPImpl variant2varP = new variant2varPImpl();
		return variant2varP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public variant2variant createvariant2variant() {
		variant2variantImpl variant2variant = new variant2variantImpl();
		return variant2variant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public varp2variant createvarp2variant() {
		varp2variantImpl varp2variant = new varp2variantImpl();
		return varp2variant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public varP2varP createvarP2varP() {
		varP2varPImpl varP2varP = new varP2varPImpl();
		return varP2varP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessLine createProcessLine() {
		ProcessLineImpl processLine = new ProcessLineImpl();
		return processLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationType createVariationType() {
		VariationTypeImpl variationType = new VariationTypeImpl();
		return variationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Optative createOptative() {
		OptativeImpl optative = new OptativeImpl();
		return optative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mandatory createMandatory() {
		MandatoryImpl mandatory = new MandatoryImpl();
		return mandatory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Optional createOptional() {
		OptionalImpl optional = new OptionalImpl();
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpIteration createvpIteration() {
		vpIterationImpl vpIteration = new vpIterationImpl();
		return vpIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpRoleDescriptor createvpRoleDescriptor() {
		vpRoleDescriptorImpl vpRoleDescriptor = new vpRoleDescriptorImpl();
		return vpRoleDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpTaskDescriptor createvpTaskDescriptor() {
		vpTaskDescriptorImpl vpTaskDescriptor = new vpTaskDescriptorImpl();
		return vpTaskDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpMilestone createvpMilestone() {
		vpMilestoneImpl vpMilestone = new vpMilestoneImpl();
		return vpMilestone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpTeamProfile createvpTeamProfile() {
		vpTeamProfileImpl vpTeamProfile = new vpTeamProfileImpl();
		return vpTeamProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpWorkProductDescriptor createvpWorkProductDescriptor() {
		vpWorkProductDescriptorImpl vpWorkProductDescriptor = new vpWorkProductDescriptorImpl();
		return vpWorkProductDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vIteration createvIteration() {
		vIterationImpl vIteration = new vIterationImpl();
		return vIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vPhase createvPhase() {
		vPhaseImpl vPhase = new vPhaseImpl();
		return vPhase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpPhase createvpPhase() {
		vpPhaseImpl vpPhase = new vpPhaseImpl();
		return vpPhase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vActivity createvActivity() {
		vActivityImpl vActivity = new vActivityImpl();
		return vActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpActivity createvpActivity() {
		vpActivityImpl vpActivity = new vpActivityImpl();
		return vpActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkOrderType createWorkOrderTypeFromString(EDataType eDataType,
			String initialValue) {
		WorkOrderType result = WorkOrderType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkOrderTypeToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PseudoStateKind createPseudoStateKindFromString(EDataType eDataType,
			String initialValue) {
		PseudoStateKind result = PseudoStateKind.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPseudoStateKindToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityType createVariabilityTypeFromString(EDataType eDataType,
			String initialValue) {
		VariabilityType result = VariabilityType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariabilityTypeToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date createDateFromString(EDataType eDataType, String initialValue) {
		return (Date) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDateToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI createUriFromString(EDataType eDataType, String initialValue) {
		return (URI) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUriToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createUnlimitedNaturalFromString(EDataType eDataType,
			String initialValue) {
		return (Integer) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnlimitedNaturalToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createStringFromString(EDataType eDataType,
			String initialValue) {
		return (String) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStringToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Set createSetFromString(EDataType eDataType, String initialValue) {
		return (Set) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSetToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List createSequenceFromString(EDataType eDataType,
			String initialValue) {
		return (List) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSequenceToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createIntegerFromString(EDataType eDataType,
			String initialValue) {
		return (Integer) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntegerToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Float createFloatFromString(EDataType eDataType, String initialValue) {
		return (Float) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFloatToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaPackage getUmaPackage() {
		return (UmaPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static UmaPackage getPackage() {
		return UmaPackage.eINSTANCE;
	}

} //UmaFactoryImpl
