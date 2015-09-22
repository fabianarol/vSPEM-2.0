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
package org.eclipse.epf.library.edit.util;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;


/**
 * Defines internal structure of a method plugin with predefined packages
 *  
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ModelStructure {

	public static final ModelStructure DEFAULT = new ModelStructure();

	public static final String HIDDEN_PACKAGE_NAME = "Hidden"; //$NON-NLS-1$
	
	public static final String CONTENT_PACKAGE_NAME = "Content"; //$NON-NLS-1$
	
	public static final String CORE_CONTENT_PACAKGE_NAME = "CoreContent"; //$NON-NLS-1$
	
	private static final String CATEGORIES = "Categories"; //$NON-NLS-1$
	
	public static final String ROLE_SET_PACKAGE_NAME = "RoleSets"; //$NON-NLS-1$
	
	public static final String DISCIPLINE_PACKAGE_NAME = "Disciplines"; //$NON-NLS-1$
	
	public static final String CUSTOM_CATEGORY_PACKAGE_NAME = "CustomCategories"; //$NON-NLS-1$
	
	 //$NON-NLS-1$
	
	

	private static final String[] DEFAULT_CORE_CONTENT_PATH = {
		CONTENT_PACKAGE_NAME, CORE_CONTENT_PACAKGE_NAME };

	private static final String[] DEFAULT_ROLE_SET_PACKAGE_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES,  ROLE_SET_PACKAGE_NAME};

	public static final String[] DEFAULT_DISCIPLINE_DEF_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, DISCIPLINE_PACKAGE_NAME };

	private static final String[] DEFAULT_TOOL_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, "Tools" }; //$NON-NLS-1$

	private static final String[] DEFAULT_STANDARD_CATEGORY_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, "StandardCategories" }; //$NON-NLS-1$

	private static final String[] DEFAULT_DELIVERY_PROCESS_PATH = { "DeliveryProcesses" }; //$NON-NLS-1$

	private static final String[] DEFAULT_CAPABILITY_PATTERN_PATH = {
		CONTENT_PACKAGE_NAME, "CapabilityPatterns" }; //$NON-NLS-1$



	
	public static final String[] DEFAULT_DOMAIN_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, "Domains" }; //$NON-NLS-1$
	
	public static final String[] DEFAULT_PRUEBASLINEAS_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, "PruebasLineas" }; //$NON-NLS-1$

	public static final String[] DEFAULT_WORK_PRODUCT_TYPE_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, "WP Types" }; //$NON-NLS-1$

	public static final String[] DEFAULT_CUSTOM_CATEGORY_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES,  CUSTOM_CATEGORY_PACKAGE_NAME};
	
	public static final String[] DEFAULT_ESTIMATING_MODELS_PATH = {
		CONTENT_PACKAGE_NAME, CATEGORIES, "EstimatingModels" }; //$NON-NLS-1$
	
	//*Lineas de Procesos*//
//	public static final String[] DEFAULT_PROCESS_LINES_PACKAGE_PATH = { PROCESS_LINES_PACKAGE_NAME }; //$NON-NLS-1$
	private static final String[] DEFAULT_VARIABILITY_CAPABILITY_PATTERN_PATH = { "vCapabilityPatterns" }; 
	
	private static final String[] DEFAULT_VARIABILITY_DELIVERY_PROCESS_PATH = { "vDeliveryProcesses" };
	
	private static final String[] DEFAULT_TAILORED_CAPABILITY_PATTERN_PATH = { "tCapabilityPatterns" }; 
	
	private static final String[] DEFAULT_TAILORED_DELIVERY_PROCESS_PATH = { "tDeliveryProcesses" };
	
	public static final String PROCESS_LINES_PACKAGE_NAME = "ProcessLineElement";
	
	public static final String VARIANTS_PACKAGE_NAME = "VariantsPackage";
	
	public static final String VARIATIONS_PACKAGE_NAME = "VariationsPackage";
	
	public static final String VAR_POINTS_PACKAGE_NAME = "VarPointsPackage";
	
	public static final String CORE_PROCESS_NAME = "CoreProcess";
	
	public static final String TAILORED_PACKAGE_NAME = "TailoredPackage";
	
	public static final String[] DEFAULT_PROCESS_LINE_ELEMENT_PATH = { PROCESS_LINES_PACKAGE_NAME }; //ProcessLineElement
	
	public static final String[] DEFAULT_CORE_PROCESS_PATH = { PROCESS_LINES_PACKAGE_NAME, CORE_PROCESS_NAME }; //ProcessLineElement + CoreProcess
	
	public static final String[] DEFAULT_VARIANTS_PACKAGE_PATH = {VARIANTS_PACKAGE_NAME}; //Variants
	
	public static final String[] DEFAULT_VARIATIONS_PACKAGE_PATH = {VARIATIONS_PACKAGE_NAME}; //Variations
	
	public static final String[] DEFAULT_VAR_POINTS_PACKAGE_PATH = {VAR_POINTS_PACKAGE_NAME};//VarPoints
	
	public static final String[] DEFAULT_TAILORED_PACKAGE_PATH = {TAILORED_PACKAGE_NAME}; //TailoredPackage
	//**//

	private static final String[] DEFAULT_PROCESS_CONTRIBUTION_PATH = { "ProcessContributions" }; //$NON-NLS-1$

	private static final String[] DEFAULT_PROCESS_PLANNING_TEMPLATE_PATH = { "ProcessPlanningTemplates" }; //$NON-NLS-1$

	public static final Collection ALL_PREDEFINED_PATHS = new HashSet();

	public String[] coreContentPath;

	public String[] roleSetPath;

	public String[] disciplineDefinitionPath;

	public String[] domainPath;
	
	public String[] pruebasLineasPath;

	public String[] toolPath;

	public String[] capabilityPatternPath;

	public String[] deliveryProcessPath;

	public String[] workProductTypePath;

	public String[] processContributionPath;

	public String[] customCategoryPath;

	public String[] estimatingModelPath;
	
	public String[] standardCategoryPath;

	public String[] processPlanningTemplatePath;
	
	//**Lineas de procesos**//
//	public String[] processLinesPackagePath;
	
	public String[] processLineElementPath;
	
	public String[] coreProcessPath;
	
	public String[] variabilityCapabilityPatternPath;
	
	public String[] variabilityDeliveryProcessPath;
	
	public String[] tailoredCapabilityPatternPath;
	
	public String[] tailoredDeliveryProcessPath;
	
	public String[] variationsPath;
	
	public String[] variantsPath;
	
	public String[] varPointsPath;
	
	public String[] tailoredPackagePath;
	//**//
	
	/**
	 * this is the method to return all the system defined package path Please
	 * add this this array when ever you have new system packages defined
	 * 
	 * @return
	 */
	public String[][] getAllSystemPaths() {
		return new String[][] { coreContentPath, roleSetPath,
				disciplineDefinitionPath, domainPath, toolPath,
				capabilityPatternPath, deliveryProcessPath,
				workProductTypePath, processContributionPath,
				customCategoryPath, standardCategoryPath,
				processPlanningTemplatePath, 
				pruebasLineasPath, processLineElementPath, 
				coreProcessPath, variabilityCapabilityPatternPath, 
				variabilityDeliveryProcessPath, variantsPath, 
				tailoredPackagePath, varPointsPath,
				tailoredCapabilityPatternPath, tailoredDeliveryProcessPath,
				variationsPath};
	}

	public String[][] getCategoryPaths() {
		return new String[][] { roleSetPath, disciplineDefinitionPath,
				domainPath, toolPath, workProductTypePath,
				standardCategoryPath, customCategoryPath, pruebasLineasPath};
	}

	public static String toLines(String[] path) {
		StringBuffer lines = new StringBuffer();
		int max = path.length - 1;
		for (int i = 0; i < max; i++) {
			lines.append(path[i]).append('\n');
		}
		lines.append(path[max]);
		return lines.toString();
	}

	public static String toFilePath(String[] path) {
		StringBuffer lines = new StringBuffer();
		int max = path.length;
		for (int i = 0; i < max; i++) {
			lines.append(path[i]).append('/');
		}
		return lines.toString();
	}

	static {
		DEFAULT.coreContentPath = DEFAULT_CORE_CONTENT_PATH;
		DEFAULT.disciplineDefinitionPath = DEFAULT_DISCIPLINE_DEF_PATH;
		DEFAULT.roleSetPath = DEFAULT_ROLE_SET_PACKAGE_PATH;
		DEFAULT.domainPath = DEFAULT_DOMAIN_PATH;
		DEFAULT.pruebasLineasPath = DEFAULT_PRUEBASLINEAS_PATH;
		DEFAULT.toolPath = DEFAULT_TOOL_PATH;
		DEFAULT.capabilityPatternPath = DEFAULT_CAPABILITY_PATTERN_PATH;
		DEFAULT.deliveryProcessPath = DEFAULT_DELIVERY_PROCESS_PATH;
		DEFAULT.workProductTypePath = DEFAULT_WORK_PRODUCT_TYPE_PATH;
		DEFAULT.processContributionPath = DEFAULT_PROCESS_CONTRIBUTION_PATH;
		DEFAULT.standardCategoryPath = DEFAULT_STANDARD_CATEGORY_PATH;
		DEFAULT.customCategoryPath = DEFAULT_CUSTOM_CATEGORY_PATH;
		DEFAULT.estimatingModelPath = DEFAULT_ESTIMATING_MODELS_PATH;
		DEFAULT.processPlanningTemplatePath = DEFAULT_PROCESS_PLANNING_TEMPLATE_PATH;
		//**Lineas de procesos**//
//		DEFAULT.processLinesPackagePath = DEFAULT_PROCESS_LINES_PACKAGE_PATH;
		DEFAULT.processLineElementPath = DEFAULT_PROCESS_LINE_ELEMENT_PATH;
		DEFAULT.coreProcessPath = DEFAULT_CORE_PROCESS_PATH;
		DEFAULT.variabilityCapabilityPatternPath = DEFAULT_VARIABILITY_CAPABILITY_PATTERN_PATH;
		DEFAULT.variabilityDeliveryProcessPath = DEFAULT_VARIABILITY_DELIVERY_PROCESS_PATH;
		DEFAULT.tailoredCapabilityPatternPath = DEFAULT_TAILORED_CAPABILITY_PATTERN_PATH;
		DEFAULT.tailoredDeliveryProcessPath = DEFAULT_TAILORED_DELIVERY_PROCESS_PATH;
		DEFAULT.variantsPath = DEFAULT_VARIANTS_PACKAGE_PATH;
		DEFAULT.variationsPath = DEFAULT_VARIATIONS_PACKAGE_PATH;
		DEFAULT.tailoredPackagePath = DEFAULT_TAILORED_PACKAGE_PATH;
		DEFAULT.varPointsPath = DEFAULT_VAR_POINTS_PACKAGE_PATH;
		//**//
		
		
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.coreContentPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.disciplineDefinitionPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.roleSetPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.domainPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.pruebasLineasPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.toolPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.capabilityPatternPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.deliveryProcessPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.workProductTypePath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.processContributionPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.standardCategoryPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.customCategoryPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.estimatingModelPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.processPlanningTemplatePath));
		//**Lineas de procesos**//
//		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.processLinesPackagePath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.processLineElementPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.variabilityCapabilityPatternPath));
		ALL_PREDEFINED_PATHS.add(toFilePath(DEFAULT.variabilityDeliveryProcessPath));
		//**//
		
	}

	public static ProcessPackage createProcessContributionPackage(
			MethodPlugin plugin) {
		// boolean old = plugin.eDeliver();
		try {
			// create ProcessContributions process package
			//
			ProcessPackage pkg = UmaFactory.eINSTANCE.createProcessPackage();
			pkg.setName(ModelStructure.DEFAULT.processContributionPath[0]);

			// disable notification
			//
			plugin.eSetDeliver(false);

			plugin.getMethodPackages().add(pkg);
			return pkg;
		} finally {
			// restore original notifaction setting
			//
			plugin.eSetDeliver(true);
		}
	}
	
	public static CoreProcessPackage createCoreProcessContributionPackage(
			MethodPlugin plugin) {
		// boolean old = plugin.eDeliver();
		try {
			// create ProcessContributions process package
			//
			CoreProcessPackage pkg = UmaFactory.eINSTANCE.createCoreProcessPackage();
			pkg.setName(ModelStructure.DEFAULT.processContributionPath[0]);

			// disable notification
			//
			plugin.eSetDeliver(false);

			plugin.getMethodPackages().add(pkg);
			return pkg;
		} finally {
			// restore original notifaction setting
			//
			plugin.eSetDeliver(true);
		}
	}
	
	public static ProcessPackage createProcessPlanningTemplatePackage(
			MethodPlugin plugin) {
		// boolean old = plugin.eDeliver();
		try {
			// create ProcessPlanningTemplates process package
			//
			ProcessPackage pkg = UmaFactory.eINSTANCE.createProcessPackage();
			pkg.setName(ModelStructure.DEFAULT.processPlanningTemplatePath[0]);

			// disable notification
			//
			plugin.eSetDeliver(false);

			plugin.getMethodPackages().add(pkg);
			return pkg;
		} finally {
			// restore original notifaction setting
			//
			plugin.eSetDeliver(true);
		}
	}

}
