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
package org.eclipse.epf.uma.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CoreProcessPackage;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.ProcessLinesPackage;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.TailoredProcessesPackage;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.VarPointsPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.varP2varP;
import org.eclipse.epf.uma.variant2varP;
import org.eclipse.epf.uma.variant2variant;
import org.eclipse.epf.uma.varp2variant;


/**
 * Utility class for accessing and updating the UMA model objects.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class UmaUtil {

	/**
	 * Replaces the feature values of an old method element with the
	 * corresponding feature values of a new method element.
	 * <p>
	 * Note: All features are updated except for the GUID feature.
	 * 
	 * @param oldElement
	 *            the old method element
	 * @param newElement
	 *            the new method element
	 */
	public static void replace(MethodElement oldElement,
			MethodElement newElement) {
		List features = oldElement.eClass().getEAllStructuralFeatures();
		if (features != null) {
			int size = features.size();
			for (int i = 0; i < size; i++) {
				EStructuralFeature feature = (EStructuralFeature) features
						.get(i);
				if (feature != UmaPackage.eINSTANCE.getMethodElement_Guid()) {
					// don't replace GUID
					Object newValue = newElement.eGet(feature);
					oldElement.eSet(feature, newValue);
				}
			}
		}

	}

	/**
	 * Checks whether a model object has a direct resource.
	 * 
	 * @param e
	 *            a model object
	 * @return <code>true</code> if the specified model object is contained by
	 *         a resource
	 */
	public static boolean hasDirectResource(EObject e) {
		Resource resource = e.eResource();
		return (resource != null && resource.getContents().contains(e));
	}
	
	/**
	 * Gets all resources owned by the specified object.
	 * 
	 * @param e
	 * @return
	 */
	public static Collection<Resource> getResources(EObject e) {
		HashSet<Resource> resources = new HashSet<Resource>();
		getResources(e, resources);
		return resources;
	}
	
	public static void getResources(EObject e, Collection<Resource> resources) {
		if (UmaUtil.hasDirectResource(e)) {
			resources.add(e.eResource());
		} else {
			for (Iterator iter = e.eContents().iterator(); iter.hasNext();) {
				getResources((EObject) iter.next(), resources);
			}
		}
	}

	/**
	 * Gets a specific type of adapter associated with a notifier.
	 * 
	 * @param eObj
	 *            a notifier
	 * @param cls
	 *            the adapter class
	 * @return an <code>Adapter<code> object or <code>null</code>
	 */
	public static Object getAdapter(EObject eObj, Class cls) {
		for (Iterator adapters = eObj.eAdapters().iterator(); adapters
				.hasNext();) {
			Adapter adapter = (Adapter) adapters.next();
			if (cls.isInstance(adapter)) {
				return adapter;
			}
		}
		return null;
	}
	
	/**
	 * Removes all adapters of the specified object and all of its children.
	 * 
	 * @param eObject
	 */
	public static void removeAllAdapters(EObject eObject) {
		for (TreeIterator<EObject> iter = eObject.eAllContents(); iter.hasNext();) {
			EObject o = iter.next();
			o.eAdapters().clear();
		}
	}

	/**
	 * Gets the method package with a specific name.
	 * 
	 * @param methodPackages
	 *            a list of method packages
	 * @param name
	 *            a method package name
	 * @return a method package with the matching name or <code>null</code>
	 */
	public static MethodPackage findMethodPackage(List methodPackages,
			String name) {
		for (int i = methodPackages.size() - 1; i > -1; i--) {
			Object obj = methodPackages.get(i);
			if (obj instanceof MethodPackage) {
				MethodPackage pkg = (MethodPackage) obj;
				if (name.equals(pkg.getName())) {
					return pkg;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the method package with a specific path.
	 * 
	 * @param methodPlugin
	 *            a method plug-in
	 * @param path
	 *            an array of method element path fragments
	 * @return a method package with the matching path or <code>null</code>
	 */
	public static MethodPackage findMethodPackage(MethodPlugin methodPlugin,
			String[] path) {
		MethodPackage pkg = null;
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkg = findMethodPackage(list, path[i]);
			if (pkg == null) {
				return null;
			}
			list = pkg.getChildPackages();
		}
		return pkg;
	}


	
	
	
	/********vEPF*********/
	
	/**
	 * Metodo que busca un coreProcess a partir de una ruta (Delivery o Capability generalmente)
	 */
	public static CoreProcessPackage getVPackageProcess(MethodPackage pkg, String[] path){
		Boolean found= false;
		for (EObject obj = pkg; obj != null; obj = obj.eContainer()) {
			if ( (obj instanceof MethodPlugin) && (obj instanceof ProcessLineComponent) ) {//Encontramos el processLine
				ProcessLineComponent processLine = (ProcessLineComponent) obj;
				for(Iterator iterator = processLine.getChildPackages().iterator(); iterator.hasNext() && !found;){
					EObject pkgCoreEObj = (EObject) iterator.next();
					if(pkgCoreEObj instanceof CoreProcessPackage){
						CoreProcessPackage pkgCore = (CoreProcessPackage) pkgCoreEObj;
						if(pkgCore.getName().equals(path[0])){
							found=true;
							return pkgCore;
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * Obtiene el methodplugin para un tailoredProcess
	 */
	public static MethodPlugin getMethodPluginForTailoredProcess(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if ((obj instanceof MethodPlugin) && (obj instanceof ProcessLineComponent)) {
				return (MethodPlugin) obj;
			}
		}
		return null;
	}
	
	
	
	/**
	 * Obtiene el methodplugin para una linea de proceso
	 */
	public static MethodPlugin getMethodPluginForProcessLine(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if ((obj instanceof MethodPlugin) && !(obj instanceof ProcessLineComponent)) {
				return (MethodPlugin) obj;
			}
		}
		return null;
	}
	/**
	 * Metodo que comprueba si un nombre de proceso existe y le añade un número (Modo copia sin reemplazo)
	 */
	public static String checkName(String name , TailoredCoreProcessPackage pkg, int contador){
		String newName=name;
		Boolean found=false;
		
		for(Iterator iterator = pkg.getChildPackages().iterator(); iterator.hasNext() && !found;){
			EObject processObj = (EObject) iterator.next();
			if(processObj instanceof ProcessComponent){
				ProcessComponent processComponent = (ProcessComponent) processObj;
				if(processComponent.getName().equals(name+Integer.toString(contador))){
					found = true;
					newName=checkName(name, pkg, contador+1);
				}
			}
		}
		if(found == false){
			newName=name+Integer.toString(contador);
		}
		
		return newName;
	}
	/**
	 * Metodo que comprueba si un proceso existe y devuelva true o false
	 * @param name
	 * @param pkg
	 * @return
	 */
	public static Boolean checkProcess(String name , TailoredCoreProcessPackage pkg){
		Boolean found=false;
		
		for(Iterator iterator = pkg.getChildPackages().iterator(); iterator.hasNext() && !found;){
			EObject processObj = (EObject) iterator.next();
			if(processObj instanceof ProcessComponent){
				ProcessComponent processComponent = (ProcessComponent) processObj;
				if(processComponent.getName().equals(name)){
					found = true;
				}
			}
		}
		
		return found;
	}
	/**
	 * Metodo que obtiene un proceso adaptado ya creado a partir de su nombre
	 * @param name
	 * @param pkg
	 * @return
	 */
	public static ProcessComponent getProcess(String name , TailoredCoreProcessPackage pkg){
		Boolean found=false;
		ProcessComponent processComponentFound = null;
		
		for(Iterator iterator = pkg.getChildPackages().iterator(); iterator.hasNext() && !found;){
			EObject processObj = (EObject) iterator.next();
			if(processObj instanceof ProcessComponent){
				ProcessComponent processComponent = (ProcessComponent) processObj;
				if(processComponent.getName().equals(name)){
					found = true;
					processComponentFound = processComponent;
				}
			}
		}
		return processComponentFound;
		
	}
	
	/**
	 * Metodo que limpia los procesos de variantes y puntos de variacion y deja unicamente los elementos de SPEM
	 */
	public static void cleanCopyProcess(Collection collectionProcesses){
		
		for(Iterator iterator = collectionProcesses.iterator(); iterator.hasNext();){
			ProcessComponent process = (ProcessComponent) iterator.next();
			
			for(Iterator iter = process.getChildPackages().iterator(); iter.hasNext();){
				EObject element = (EObject) iter.next();
				
				if(element instanceof ProcessPackage){//Encapsulacion
					ProcessPackage elementPkg = (ProcessPackage) element;
					if( (elementPkg.getProcessElements().get(0) instanceof VarPoint) || (elementPkg.getProcessElements().get(0) instanceof Variant)){
						iter.remove();
					}
				}
			}
		}
	}
	
	/***
	 * Metodo que limpia las dependencias cuando un elemento es borrado (tanto clientes como suppliers, todas las referencias)
	 */
	public static void cleanDependencesElement(VarElement varElementDelete){
		//Parte Cliente
		if(varElementDelete.getClient().size() > 0){
			for(Iterator iterator = varElementDelete.getClient().iterator(); iterator.hasNext();){
				Dependences depObj = (Dependences) iterator.next();
				if( (depObj instanceof variant2varP)){
					variant2varP dep = (variant2varP) depObj;
					deleteSupplierDependence(dep.getSupplier(), varElementDelete);
				}
				else if( (depObj instanceof variant2variant)){
					variant2variant dep = (variant2variant) depObj;
					deleteSupplierDependence(dep.getSupplier(), varElementDelete);
				}
				else if( (depObj instanceof varp2variant)){
					varp2variant dep = (varp2variant) depObj;
					deleteSupplierDependence(dep.getSupplier(), varElementDelete);
				}
				else if( (depObj instanceof varP2varP)){
					varP2varP dep = (varP2varP) depObj;
					deleteSupplierDependence(dep.getSupplier(), varElementDelete);
				}
			}
		}
		//Parte Supplier
		if(varElementDelete.getSupplier().size() > 0){
			for(Iterator iterator = varElementDelete.getSupplier().iterator(); iterator.hasNext();){
				Dependences depObj = (Dependences) iterator.next();
				if( (depObj instanceof variant2varP)){
					variant2varP dep = (variant2varP) depObj;
					deleteClientDependence(dep.getClient(), varElementDelete);
				}
				else if( (depObj instanceof variant2variant)){
					variant2variant dep = (variant2variant) depObj;
					deleteClientDependence(dep.getClient(), varElementDelete);

				}
				else if( (depObj instanceof varp2variant)){
					varp2variant dep = (varp2variant) depObj;
					deleteClientDependence(dep.getClient(), varElementDelete);
					

				}
				else if( (depObj instanceof varP2varP)){
					varP2varP dep = (varP2varP) depObj;
					deleteClientDependence(dep.getClient(), varElementDelete);
				}
			}
		}
	}
	
	/**
	 * Método que borra de un VarElement A la dependencia client que pueda tener con B (Mantiene la consistencia entre las dependencias)
	 */
	public static Boolean deleteClientDependence(VarElement elementToModify, VarElement elementTarget){
		Boolean delete = false;
		Collection clientDepElementToModify = elementToModify.getClient();
		
		if(clientDepElementToModify.size() > 0){
			for(Iterator iterator = clientDepElementToModify.iterator(); iterator.hasNext() && !delete;){
				EObject clientDepObj = (EObject) iterator.next();
				if(clientDepObj instanceof Dependences){
					Dependences clientDep = (Dependences) clientDepObj;	
					if( (clientDep instanceof variant2varP)){
						variant2varP dep = (variant2varP) clientDep;
						if(dep.getSupplier().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
					else if( (clientDep instanceof variant2variant)){
						variant2variant dep = (variant2variant) clientDep;
						if(dep.getSupplier().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
					else if( (clientDep instanceof varp2variant)){
						varp2variant dep = (varp2variant) clientDep;
						if(dep.getSupplier().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
					else if( (clientDep instanceof varP2varP)){
						varP2varP dep = (varP2varP) clientDep;
						if(dep.getSupplier().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
				}
			}
		}
		
		return delete;
	}
	
	/**
	 * Método que borra de un VarElement A la dependencia supplier que pueda tener con B (Mantiene la consistencia entre las dependencias)
	 */
	public static Boolean deleteSupplierDependence(VarElement elementToModify, VarElement elementTarget){
		Boolean delete = false;
		Collection supplierDepElementToModify = elementToModify.getSupplier();
		
		if(supplierDepElementToModify.size() > 0){
			for(Iterator iterator = supplierDepElementToModify.iterator(); iterator.hasNext() && !delete;){
				EObject supplierDepObj = (EObject) iterator.next();
				if(supplierDepObj instanceof Dependences){
					Dependences supplierDep = (Dependences) supplierDepObj;	
					if( (supplierDep instanceof variant2varP)){
						variant2varP dep = (variant2varP) supplierDep;
						if(dep.getClient().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
					else if( (supplierDep instanceof variant2variant)){
						variant2variant dep = (variant2variant) supplierDep;
						if(dep.getClient().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
					else if( (supplierDep instanceof varp2variant)){
						varp2variant dep = (varp2variant) supplierDep;
						if(dep.getClient().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
					else if( (supplierDep instanceof varP2varP)){
						varP2varP dep = (varP2varP) supplierDep;
						if(dep.getClient().equals(elementTarget)){
							iterator.remove();
							delete = true;
						}
					}
				}
			}
		}
		
		return delete;
	}
	
	/**
	 * Método devuelve si una dependencia esta contenida dentro de una lista de dependencias (Evita generar un metodo equals en UMA)
	 */
	public static Boolean collectionContainsDependence(Collection dependences, Dependences dependenceTarget){
		Boolean found = false;
		for(Iterator iterator = dependences.iterator(); iterator.hasNext() && !found;){
			EObject dependenceObj = (EObject) iterator.next();
			if(dependenceObj instanceof Dependences){
				Dependences dependenceOrig = (Dependences) dependenceObj;
				if( (dependenceTarget instanceof variant2varP) && (dependenceOrig instanceof variant2varP)){
					variant2varP depOrig = (variant2varP) dependenceOrig;
					variant2varP depTarget =  (variant2varP) dependenceTarget;
					if(depOrig.isIsInclusive() == depTarget.isIsInclusive()){
						if(depOrig.getClient().equals(depTarget.getClient()) &&
								depOrig.getSupplier().equals(depTarget.getSupplier())){
							found = true;
						}
					}
				}
				else if( (dependenceTarget instanceof variant2variant) && (dependenceOrig instanceof variant2variant)){
					variant2variant depOrig = (variant2variant) dependenceOrig;
					variant2variant depTarget =  (variant2variant) dependenceTarget;
					if(depOrig.isIsInclusive() == depTarget.isIsInclusive()){
						if(depOrig.getClient().equals(depTarget.getClient()) &&
								depOrig.getSupplier().equals(depTarget.getSupplier())){
							found = true;
						}
					}
				}
				else if( (dependenceTarget instanceof varp2variant) && (dependenceOrig instanceof varp2variant)){
					varp2variant depOrig = (varp2variant) dependenceOrig;
					varp2variant depTarget =  (varp2variant) dependenceTarget;
					if(depOrig.isIsInclusive() == depTarget.isIsInclusive()){
						if(depOrig.getClient().equals(depTarget.getClient()) &&
								depOrig.getSupplier().equals(depTarget.getSupplier())){
							found = true;
						}
					}
				}
				else if( (dependenceTarget instanceof varP2varP) && (dependenceOrig instanceof varP2varP)){
					varP2varP depOrig = (varP2varP) dependenceOrig;
					varP2varP depTarget =  (varP2varP) dependenceTarget;
					if(depOrig.isIsInclusive() == depTarget.isIsInclusive()){
						if(depOrig.getClient().equals(depTarget.getClient()) &&
								depOrig.getSupplier().equals(depTarget.getSupplier())){
							found = true;
						}
					}
				}
			}
		}
		
		return found;
	}
	
	/**
	 * Metodo que busca el paquete de Variaciones dentro de un tailored Process
	 * @param tailoredProcess
	 * @return
	 */
	public static VariationsPackage findVariationsPackage(TailoredProcessComponent tailoredProcess){
		
		VariationsPackage pkgVariations = null;
		if(tailoredProcess.getChildPackages().size() > 0){
			Boolean found = false;
			for(Iterator iterator = tailoredProcess.getChildPackages().iterator(); iterator.hasNext() && !found;){
				EObject iterObj = (EObject) iterator.next();
				if(iterObj instanceof VariationsPackage){
					found = true;
					pkgVariations = (VariationsPackage) iterObj;
				}
			}
		}
		return pkgVariations;
	}
	/**
	 * Metodo que obtiene el tipo de un punto de variación
	 * @param varPoint
	 * @return
	 */
	public static String findVarPointType(VarPoint varPoint){
		String typeName="";
		
		if(varPoint instanceof Phase){
			typeName = "Phase";
		}
		else if(varPoint instanceof Iteration){
			typeName = "Iteration";
		}
		else if(varPoint instanceof Activity){
			typeName = "Activity";
		}
		else if(varPoint instanceof Milestone){
			typeName = "Milestone";
		}
		else if(varPoint instanceof TeamProfile){
			typeName = "TeamProfile";
		}
		else if(varPoint instanceof TaskDescriptor){
			typeName = "TaskDescriptor";
		}
		else if(varPoint instanceof WorkProductDescriptor){
			typeName = "WorkProductDescriptor";
		}
		else if(varPoint instanceof RoleDescriptor){
			typeName = "RoleDescriptor";
		}
		else{
			typeName = "Unknown";
		}
		return typeName;
	}
	
	/**
	 * Metodo que encuentra una linea de proceso y la devuelve
	 */
	public static MethodPackage findProcessLinePackage(List methodPackages,
			String name) {
		MethodPackage pkgLine = null;
		
		for (int i = methodPackages.size() - 1; i > -1; i--) {
			Object obj = methodPackages.get(i);
			if (obj instanceof ProcessLinesPackage && obj instanceof MethodPackage) {
				MethodPackage pkg = (MethodPackage) obj;
				Collection processLineComponentList = new ArrayList();
				processLineComponentList = pkg.getChildPackages();//Recojo las lineas de procesos
				for(Iterator iterator = processLineComponentList.iterator(); iterator.hasNext();){
					EObject processLineElementObj = (EObject) iterator.next();
					if (processLineElementObj instanceof ProcessLineComponent){
						ProcessLineComponent processLineElement = (ProcessLineComponent) processLineElementObj;
						if (processLineElement.getName().equals(name)){
							pkgLine = processLineElement;
						}
					}
				}
			}
		}
		return pkgLine;
	}
	
	
	
	/**
	 * Método que encuentra/crea un paquete CoreProcess.
	 * 
	 * @param methodPlugin
	 * @param path
	 * @param lineProcessName
	 * @return
	 */
	public static MethodPackage createCoreProcessPackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {
		
		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkgLine = findProcessLinePackage(list, lineProcessName);//Buscamos la linea de proceso
			//Si no esta creamos el paquete nuevo
			if (pkgLine != null) {

				Boolean exists = false;
				for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
					EObject packetTargetObj = (EObject) iterator.next();
					if (packetTargetObj !=null){
						if(packetTargetObj instanceof MethodPackage){
							MethodPackage packetTarget = (MethodPackage) packetTargetObj;
							if(packetTarget.getName().equals(path[i])){
								exists = true;
								newPackage = packetTarget;
							}
						}
					}
				}
				//Si no existe lo creamos
				if(exists == false){
					CoreProcessPackage pkgCoreProcessPackage = UmaFactory.eINSTANCE.createCoreProcessPackage();
					pkgCoreProcessPackage.setName(path[i]); 
					pkgLine.getChildPackages().add(pkgCoreProcessPackage);
					newPackage = pkgCoreProcessPackage;
				}
				
			}
			list = pkgLine.getChildPackages();
		}
		return newPackage;
	}	
	
	/**
	 * Metodo que encuentra/crea un paquete TailoredCoreProcess
	 * @param tailoredProcess
	 * @param path
	 * @return
	 */
	
	public static MethodPackage createTailoredCoreProcessPackage(TailoredProcessComponent tailoredProcess, String path[]) {
		
		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		//Buscamos si existe -> No lo creamos, si no existe -> Lo creamos
		for (int i = 0; i < path.length; i++) {
			
			Boolean found = false;
			
			for(Iterator iterator = tailoredProcess.getChildPackages().iterator(); iterator.hasNext() && !found;){
				EObject tailoredCoreProcessPackageObj = (EObject) iterator.next();
				
				if(tailoredCoreProcessPackageObj instanceof TailoredCoreProcessPackage){
					TailoredCoreProcessPackage tailoredCoreProcessPackage = (TailoredCoreProcessPackage) tailoredCoreProcessPackageObj;
					if(tailoredCoreProcessPackage.getName().equals(path[i])){
						found = true;
						newPackage = tailoredCoreProcessPackage;
					}
				}
			}
			if(!found){
				TailoredCoreProcessPackage pkgTailoredCoreProcessPackage = UmaFactory.eINSTANCE.createTailoredCoreProcessPackage();
				pkgTailoredCoreProcessPackage.setName(path[i]);
				tailoredProcess.getChildPackages().add(pkgTailoredCoreProcessPackage);
				newPackage = pkgTailoredCoreProcessPackage;
			}
		}
		return newPackage;
	}
	
	/**
	 * Metodo que encuentra/crea un paquete VariationsPackage
	 * @param tailoredProcess
	 * @param path
	 * @return
	 */
	
	public static MethodPackage createVariationsPackage(TailoredProcessComponent tailoredProcess, String path[]) {
		
		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		//Buscamos si existe -> No lo creamos, si no existe -> Lo creamos
		for (int i = 0; i < path.length; i++) {
			
			Boolean found = false;
			
			for(Iterator iterator = tailoredProcess.getChildPackages().iterator(); iterator.hasNext() && !found;){
				EObject variationsPackageObj = (EObject) iterator.next();
				
				if(variationsPackageObj instanceof VariationsPackage){
					VariationsPackage variationsProcessPackage = (VariationsPackage) variationsPackageObj;
					if(variationsProcessPackage.getName().equals(path[i])){
						found = true;
						newPackage = variationsProcessPackage;
					}
				}
			}
			if(!found){
				VariationsPackage pkgVariationsPackage = UmaFactory.eINSTANCE.createVariationsPackage();
				pkgVariationsPackage.setName(path[i]);
				tailoredProcess.getChildPackages().add(pkgVariationsPackage);
				newPackage = pkgVariationsPackage;
			}
		}
		return newPackage;
	}
	
	
	/***
	 * Metodo para encontrar un core process !FIXME Codigo replicado
	 * @param methodPlugin
	 * @param path
	 * @param lineProcessName
	 * @return
	 */
	public static MethodPackage findCorePackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {

		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkgLine = findProcessLinePackage(list, lineProcessName);//Buscamos la linea de proceso
			//Si no esta creamos el paquete nuevo
			if (pkgLine != null) {
				for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
					EObject coreProcessPackageObj = (EObject) iterator.next();
					if(coreProcessPackageObj instanceof CoreProcessPackage){
						CoreProcessPackage coreProcessPackage = (CoreProcessPackage) coreProcessPackageObj;
						if(coreProcessPackage.getName().equals(path[i])){
							newPackage = coreProcessPackage;
						}
					}
				}
				list = pkgLine.getChildPackages();
			}
			
			
		}
		return newPackage;
	}
	/***
	 * Método encargado de crear paquetes de variantes !FIXME Codigo replicado
	 * @param methodPlugin
	 * @param path
	 * @param lineProcessName
	 * @return
	 */
	
	public static MethodPackage createVariantsPackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {
		
		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkgLine = findProcessLinePackage(list, lineProcessName);//Buscamos la linea de proceso
			//Si no esta creamos el paquete nuevo
			if (pkgLine != null) {

				
				Boolean exists = false;
				for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
					EObject packetTargetObj = (EObject) iterator.next();
					if (packetTargetObj !=null){
						if(packetTargetObj instanceof MethodPackage){
							MethodPackage packetTarget = (MethodPackage) packetTargetObj;
							if(packetTarget.getName().equals(path[i])){
								exists = true;
								newPackage = packetTarget;
							}
						}
					}
				}
				//Si no existe lo creamos
				if(exists == false){
					VariantsPackage pkgVariants = UmaFactory.eINSTANCE.createVariantsPackage();
					pkgVariants.setName(path[i]); 
					pkgLine.getChildPackages().add(pkgVariants);
					newPackage = pkgVariants;
				}
			}
			list = pkgLine.getChildPackages();
		}
		return newPackage;
	}
	/**
	 * Recoje el MethodPlugin de una linea de proceso
	 * @param element
	 * @return
	 */
	
	
	public static MethodPlugin getMethodPluginFromProcessLine(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof MethodPlugin && !(obj instanceof ProcessLineComponent)) {
				return (MethodPlugin) obj;
			}
		}
		return null;
	}
	
	/**
	 * Devuelve el ProcessLine de un elemento dado
	 * @return
	 */
	public static ProcessLineComponent getProcessLine(EObject element){
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if ((obj instanceof ProcessLineComponent)) {
				return (ProcessLineComponent) obj;
			}
		}
		return null;
		
	}
	
	/**
	 * Metodo para encontrar un paquete de variantes !FIXME Codigo replicado
	 * @param methodPlugin
	 * @param path
	 * @param lineProcessName
	 * @return
	 */
	public static MethodPackage findVariantsPackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {		
		
			ProcessLineComponent pkgLine = null;
			MethodPackage newPackage = null;
		

			if(methodPlugin instanceof ProcessLineComponent){
				pkgLine = (ProcessLineComponent)methodPlugin;

				if (pkgLine != null) {
					for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
						EObject variantsPackageObj = (EObject) iterator.next();
						if(variantsPackageObj instanceof VariantsPackage){
							VariantsPackage variantsPackage = (VariantsPackage) variantsPackageObj;
							if(variantsPackage.getName().equals(path[0])){
								newPackage = variantsPackage;
							}
						}
					}
				}
			}
			
		
		return newPackage;
	}
	
	/**
	 * Metodo para encontrar un paquete de puntos de variacion
	 * @param methodPlugin
	 * @param path
	 * @param lineProcessName
	 * @return
	 */
	public static MethodPackage findVarPointsPackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {
		
		ProcessLineComponent pkgLine = null;
		MethodPackage newPackage = null;

		if(methodPlugin instanceof ProcessLineComponent){
			pkgLine = (ProcessLineComponent)methodPlugin;
			if (pkgLine != null) {
				for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
					EObject varPointsPackageObj = (EObject) iterator.next();
					if(varPointsPackageObj instanceof VarPointsPackage){
						VarPointsPackage varPointsPackage = (VarPointsPackage) varPointsPackageObj;
						if(varPointsPackage.getName().equals(path[0])){
							newPackage = varPointsPackage;
						}
					}
				}
			}
		}
		return newPackage;
	}
	
	
	
	/***
	 * Método encargado de crear un paquete procesos adaptados
	 * @param methodPlugin
	 * @param path
	 * @param lineProcessName
	 * @return
	 */
	
	public static MethodPackage createTailoredProcessPackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {
		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkgLine = findProcessLinePackage(list, lineProcessName);//Buscamos la linea de proceso
			//Si no esta creamos el paquete nuevo
			if (pkgLine != null) {

				Boolean exists = false;
				for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
					EObject packetTargetObj = (EObject) iterator.next();
					if (packetTargetObj !=null){
						if(packetTargetObj instanceof MethodPackage){
							MethodPackage packetTarget = (MethodPackage) packetTargetObj;
							if(packetTarget.getName().equals(path[i])){
								exists = true;
								newPackage = packetTarget;
							}
						}
					}
				}
				//Si no existe lo creamos
				if(exists == false){
					TailoredProcessesPackage pkgTailored = UmaFactory.eINSTANCE.createTailoredProcessesPackage();
					pkgTailored.setName(path[i]); 
					pkgLine.getChildPackages().add(pkgTailored);
					newPackage = pkgTailored;
				}
				
			}
			list = pkgLine.getChildPackages();
		}
		return newPackage;
	}
	
	
	public static MethodPackage createVarPointsPackage(MethodPlugin methodPlugin,
			String[] path, String lineProcessName) {
		
		MethodPackage pkgLine = null;
		MethodPackage newPackage = null;
		
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkgLine = findProcessLinePackage(list, lineProcessName);//Buscamos la linea de proceso
			//Si no esta creamos el paquete nuevo
			if (pkgLine != null) {

				
				Boolean exists = false;
				for(Iterator iterator = pkgLine.getChildPackages().iterator(); iterator.hasNext();){
					EObject packetTargetObj = (EObject) iterator.next();
					if (packetTargetObj !=null){
						if(packetTargetObj instanceof MethodPackage){
							MethodPackage packetTarget = (MethodPackage) packetTargetObj;
							if(packetTarget.getName().equals(path[i])){
								exists = true;
								newPackage = packetTarget;
							}
						}
					}
				}
				//Si no existe lo creamos
				if(exists == false){
					VarPointsPackage pkgVarPoints = UmaFactory.eINSTANCE.createVarPointsPackage();
					pkgVarPoints.setName(path[i]); 
					pkgLine.getChildPackages().add(pkgVarPoints);
					newPackage = pkgVarPoints;
				}
			}
			list = pkgLine.getChildPackages();
		}
		return newPackage;
	}
	
	
	
	/*******************************************/
	
	
	
	/**
	 * Gets the parent activity of a breakdown element.
	 * 
	 * @param e
	 *            a breakdown element
	 * @return the parent activity or <code>null</code>
	 */
	public static Activity getParentActivity(BreakdownElement e) {
		return e.getSuperActivities();
	}

	/**
	 * Gets the parent activity of a work order.
	 * 
	 * @param workOrder
	 *            a work order
	 * @return the parent activity or <code>null</code>
	 */
	public static Activity getOwningActivity(WorkOrder workOrder) {
		ProcessPackage pkg = (ProcessPackage) workOrder.eContainer();
		for (Iterator iter = pkg.getProcessElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof Activity) {
				return (Activity) element;
			}
		}
		return null;
	}

	/**
	 * Gets the content package with a specific name.
	 * 
	 * @param methodPackages
	 *            a list of method packages
	 * @param name
	 *            a content package name
	 * @return a content package with the mathcing name or <code>null</code>
	 */
	public static ContentPackage findContentPackage(List methodPackages,
			String name) {
		for (int i = methodPackages.size() - 1; i > -1; i--) {
			Object obj = methodPackages.get(i);
			if (obj instanceof ContentPackage) {
				ContentPackage pkg = (ContentPackage) obj;
				if (name.equals(pkg.getName())) {
					return pkg;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the content package with a specific path.
	 * 
	 * @param methodPlugin
	 *            a method plug-in
	 * @param path
	 *            an array of method element path fragments
	 * @return a content package with the matching path or <code>null</code>
	 */
	public static ContentPackage findContentPackage(MethodPlugin methodPlugin,
			String[] path) {
		ContentPackage pkg = null;
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkg = findContentPackage(list, path[i]);
			if (pkg == null) {
				return null;
			}
			list = pkg.getChildPackages();
		}
		return pkg;
	}

	/**
	 * Gets the parent method plug-in of a method element.
	 * 
	 * @param element
	 *            a Method element
	 * @return the parent method plug-in or <code>null</code>
	 */
	public static MethodPlugin getMethodPlugin(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof MethodPlugin) {
				return (MethodPlugin) obj;
			}
		}
		return null;
	}

	/**
	 * Gets the parent method library of a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return the parent method library or <code>null</code>
	 */
	public static MethodLibrary getMethodLibrary(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof MethodLibrary) {
				return (MethodLibrary) obj;
			}
		}
		return null;
	}
	
	public static EObject getTopContainer(EObject element) {
		EObject container = null;
		for (EObject obj = element.eContainer(); obj != null; obj = obj.eContainer()) {
			container = obj;
		}
		return container;		
	}

	/**
	 * Gets the parent content package of a content element.
	 * 
	 * @param element
	 *            a content element
	 * @return the parent content package or <code>null</code>
	 */
	public static ContentPackage getContentPackage(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof ContentPackage) {
				return (ContentPackage) obj;
			}
		}
		return null;
	}

	/**
	 * Gets the parent process package of a process element.
	 * 
	 * @param element
	 *            a Process element
	 * @return the parent process package or <code>null</code>
	 */
	public static ProcessPackage getProcessPackage(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof ProcessPackage) {
				return (ProcessPackage) obj;
			}
		}
		return null;
	}

	/**
	 * Gets the parent diagram of a diagram element.
	 * 
	 * @param element
	 *            a diagram element
	 * @return the parent diagram or <code>null</code>
	 */
	public static Diagram getDiagram(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof Diagram) {
				return (Diagram) obj;
			}
		}
		return null;
	}

	/**
	 * Checks whether a method element is contained by a specific content
	 * package.
	 * 
	 * @param element
	 *            a method element
	 * @param contentPackage
	 *            a content package
	 * @return <code>true</code> if the method element is contained by the
	 *         content package
	 */
	public static boolean isContainedByContentPackage(EObject element,
			ContentPackage contentPackage) {
		return isContainedBy(element, contentPackage);
	}

	/**
	 * Checks whether a model object is contained by a specific container.
	 * 
	 * @param eObj
	 *            a model object
	 * @param container
	 *            a container
	 * @return <code>true</code> if the model object is contained by the
	 *         container
	 */
	public static boolean isContainedBy(EObject eObj, Object container) {
		if (eObj == null) {
			return false;
		}
		for (EObject obj = eObj.eContainer(); obj != null; obj = obj
				.eContainer()) {
			if (obj == container) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Generates and returns a unique ID.
	 * 
	 * @return a unique ID
	 */
	public static String generateGUID() {
		return EcoreUtil.generateUUID();
	}

	/**
	 * Creates and returns the content description name of a given method
	 * element.
	 * 
	 * @param element
	 *            a method element
	 * @return a suitable content description name
	 */
	public static String createContentDescriptionName(MethodElement e) {
		return e.getName() + ',' + e.getGuid();
	}

	public static void getAllSupers(List supers, VariabilityElement e,
			VariabilityType type) {
		VariabilityElement base = e.getVariabilityBasedOnElement();
		if (base != null && e.getVariabilityType() == type
				&& !supers.contains(base)) {
			supers.add(base);
			getAllSupers(supers, base, type);
		}
	}

	public static void getAllSupersBoth(List supers, VariabilityElement e,
			VariabilityType type1, VariabilityType type2) {
		VariabilityElement base = e.getVariabilityBasedOnElement();
		if (base != null
				&& (e.getVariabilityType() == type1 || e.getVariabilityType() == type2)
				&& !supers.contains(base)) {
			supers.add(base);
			getAllSupersBoth(supers, base, type1, type2);
		}
	}

	/**
	 * Gets the class of a content element.
	 * 
	 * @param contentElement
	 *            a content element
	 * @return the content element class
	 */
	public static Class getClassOfContentElement(ContentElement contentElement) {
		if (contentElement instanceof Role)
			return Role.class;
		if (contentElement instanceof Task)
			return Task.class;
		if (contentElement instanceof WorkProduct)
			return WorkProduct.class;
		if (contentElement instanceof Guidance)
			return Guidance.class;
		if (contentElement instanceof Domain)
			return Domain.class;
		if (contentElement instanceof Discipline)
			return Discipline.class;
		if (contentElement instanceof DisciplineGrouping)
			return DisciplineGrouping.class;
		if (contentElement instanceof RoleSet)
			return RoleSet.class;
		if (contentElement instanceof RoleSetGrouping)
			return RoleSetGrouping.class;
		if (contentElement instanceof WorkProductType)
			return WorkProductType.class;
		return Object.class;
	}

	/**
	 * Creates a default work order for two work breakdown elements.
	 * 
	 * @param e
	 *            a work breakdown element
	 * @param predecessor
	 *            the predecessor work breakdown element
	 * @return the newly created work order
	 */
	public static WorkOrder createDefaultWorkOrder(WorkBreakdownElement succ,
			WorkBreakdownElement pred) {
		return createDefaultWorkOrder(succ, pred, true);
	}
	
	public static WorkOrder createDefaultWorkOrder(WorkBreakdownElement succ,
			WorkBreakdownElement pred, boolean link) {
		WorkOrder wo = UmaFactory.eINSTANCE.createWorkOrder();
		wo.setPred(pred);
		if(link) {
			succ.getLinkToPredecessor().add(wo);
		}
		return wo;
	}	

	/**
	 * Locates the work order associated with two work breakdown elements.
	 * 
	 * @param e
	 *            a work breakdown element
	 * @param predecessor
	 *            the predecessor work breakdown element
	 * @return a work order or <code>null</code>
	 */
	public static WorkOrder findWorkOrder(WorkBreakdownElement e,
			Object predecessor) {
		for (Iterator iter = e.getLinkToPredecessor().iterator(); iter
				.hasNext();) {
			WorkOrder workOrder = (WorkOrder) iter.next();
			if (workOrder.getPred() == predecessor)
				return workOrder;
		}

		return null;
	}
	
	/**
	 * Locates the work order associated with two work breakdown elements. If
	 * includeBase is true, will collect WorkOrder for base element of the
	 * predecessor as well.
	 * 
	 * @param e
	 * @param predecessor
	 * @param includeBase
	 * @return
	 */
	public static Collection<WorkOrder> findWorkOrder(WorkBreakdownElement e, WorkBreakdownElement predecessor, boolean includeBase) {
		if(!includeBase) {
			WorkOrder wo = findWorkOrder(e, predecessor);
			return (Collection<WorkOrder>) (wo == null ? Collections.emptyList() : Collections.singletonList(wo));
		}
		ArrayList<WorkOrder> workOrders = new ArrayList<WorkOrder>();
		collectWorkOrders(workOrders, e, predecessor);
		return workOrders;
	}
	
	/**
	 * Collects the WorkOrder objects associated between the specified work
	 * breakdown element <code>e</code> and the predecessor or any of its base elements.
	 * 
	 * @param workOrders
	 * @param e
	 * @param predecessor
	 */
	public static void collectWorkOrders(Collection<WorkOrder> workOrders,
			WorkBreakdownElement e, Object predecessor) {
		for (Iterator iter = e.getLinkToPredecessor().iterator(); iter
				.hasNext();) {
			WorkOrder workOrder = (WorkOrder) iter.next();
			if (workOrder.getPred() == predecessor) {
				workOrders.add(workOrder);
			}
		}
		if(predecessor instanceof VariabilityElement) {
			VariabilityElement ve = ((VariabilityElement)predecessor).getVariabilityBasedOnElement();
			if(ve != null) {
				collectWorkOrders(workOrders, e, ve);
			}
		}
	}

	/**
	 * Removes the work order associated with two work breakdown elements.
	 * 
	 * @param e
	 *            a work breakdown element
	 * @param predecessor
	 *            the predecessor work breakdown element
	 * @return the removed work order or <code>null</code>
	 */
	public static WorkOrder removeWorkOrder(WorkBreakdownElement e,
			Object predecessor) {
		for (Iterator iterator = e.getLinkToPredecessor().iterator(); iterator
				.hasNext();) {
			WorkOrder order = (WorkOrder) iterator.next();
			if (order.getPred() == predecessor) {
				iterator.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * Gets the parent process component of a method element.
	 * 
	 * @param e
	 *            a method element, typically a process element
	 * @return the parent process component or <code>null</code>
	 */
	public static ProcessComponent getProcessComponent(MethodElement e) {
		EObject container;
		for (container = e; container != null
				&& !(container instanceof ProcessComponent); container = container
				.eContainer())
			;
		if (container != null) {
			return ((ProcessComponent) container);
		}
		return null;
	}

	/**
	 * Gets the parent method unit of a method element.
	 * 
	 * @param e
	 *            a method element
	 * @return the parent method unit or <code>null</code>
	 */
	public static MethodUnit getMethodUnit(MethodElement e) {
		EObject container;
		for (container = e; container != null
				&& !(container instanceof MethodUnit); container = container
				.eContainer())
			;
		if (container != null) {
			return ((MethodUnit) container);
		}
		return null;

	}

	/**
	 * Checks whether a method plug-in has method elements that reference
	 * elements in a base plug-in.
	 * <p>
	 * Note: This is a expensive call to make for a large method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @param base
	 *            a base method plug-in
	 * @return <code>true</code> if the specified method plug-in contain
	 *         method elements that reference elements in a base plug-in.
	 */
	public static boolean hasReference(MethodPlugin plugin, MethodPlugin base) {
		for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) plugin
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject ref = (EObject) featureIterator.next();
			EStructuralFeature f = featureIterator.feature();

			if (f != UmaPackage.eINSTANCE.getMethodPlugin_Bases()
					&& UmaUtil.getMethodPlugin(ref) == base) {
				return true;
			}
		}

		for (Iterator iter = plugin.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();

			// ignore ProcessElement b/c it can references anything
			//
			if (element instanceof ProcessElement) {
				continue;
			}

			for (Iterator iterator = element.eCrossReferences().iterator(); iterator
					.hasNext();) {
				EObject ref = (EObject) iterator.next();
				if (getMethodPlugin(ref) == base) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes all element references in a method plug-in that point to elements
	 * in a base plug-in.
	 * <p>
	 * Note: This is a expensive call to make for a large method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @param base
	 *            the base method plug-in
	 * @return <code>true</code> if the operation is successful
	 */
	public static boolean removeReferences(MethodPlugin plugin,
			MethodPlugin base) {
		for (Iterator iter = plugin.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();

			// ignore ProcessElement b/c it can references anything
			//
			if (element instanceof ProcessElement) {
				continue;
			}

			for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) element
					.eCrossReferences().iterator(); featureIterator.hasNext();) {
				EObject ref = (EObject) featureIterator.next();

				if (getMethodPlugin(ref) == base) {
					EStructuralFeature f = featureIterator.feature();
					if (f.isMany()) {
						((Collection) element.eGet(f)).remove(ref);
					} else {
						element.eSet(f, null);
					}
				}
			}
		}

		return false;
	}

	public static String getMessage(IStatus status) {
		String msg = status.getMessage();
		if (status.isMultiStatus()) {
			StringBuffer strBuf = new StringBuffer(msg);
			IStatus statuses[] = status.getChildren();
			for (int i = 0; i < statuses.length; i++) {
				strBuf.append('\n').append(statuses[i].getMessage());
			}
			msg = strBuf.toString();
		}
		if (msg != null && msg.trim().length() == 0) {
			msg = null;
		}
		return msg;
	}

	/**
	 * Generates a GUID using a base GUID.
	 * 
	 * @param baseGUID
	 *            a base GUID
	 * @return a unique ID
	 */
	public static final String generateGUID(String baseGUID) {
		return GUID.generate(baseGUID);
	}

	private static class GUID {
		private static MessageDigest md5 = null;

		private static MessageDigest getMD5() {
			if (md5 == null) {
				synchronized (GUID.class) {
					if (md5 == null) {
						try {
							md5 = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return md5;
		}

		public static final String generate(String baseGUID) {
			MessageDigest md5 = getMD5();
			md5.update(baseGUID.getBytes());
			byte[] hash = md5.digest();
			char[] buffer = new char[23];
			buffer[0] = '-';

			// Do a base 64 conversion by turning every 3 bytes into 4 base 64
			// characters
			//
			for (int i = 0; i < 5; ++i) {
				buffer[4 * i + 1] = BASE64_DIGITS[(hash[i * 3] >> 2) & 0x3F];
				buffer[4 * i + 2] = BASE64_DIGITS[((hash[i * 3] << 4) & 0x30)
						| ((hash[i * 3 + 1] >> 4) & 0xF)];
				buffer[4 * i + 3] = BASE64_DIGITS[((hash[i * 3 + 1] << 2) & 0x3C)
						| ((hash[i * 3 + 2] >> 6) & 0x3)];
				buffer[4 * i + 4] = BASE64_DIGITS[hash[i * 3 + 2] & 0x3F];
			}

			// Handle the last byte at the end.
			//
			buffer[21] = BASE64_DIGITS[(hash[15] >> 2) & 0x3F];
			buffer[22] = BASE64_DIGITS[(hash[15] << 4) & 0x30];

			return new String(buffer);
		}

		private static final char[] BASE64_DIGITS = { 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
				'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };
	}
	
	public static void main(String[] args) {
		System.out.println(generateGUID());
	}

	

}