package org.eclipse.epf.uma.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkDefinition;

/**
 * Clase encargada de la lógica de (Logica insuficiente para implementar métodos)
 * @author flucas
 *
 */
public class TailoredProcessLogic {

		public TailoredProcessLogic(){
			
		}
		
		
		/**
		 * Metodo que borra de la lista de ocupaciones, todas las apariciones del varElement
		 * @param list
		 * @param varElement
		 */
		public static void removeVarElementFromList(Collection list, VarElement varElement){
			for(Iterator iterator = list.iterator(); iterator.hasNext();){
				EObject occupationObj = (EObject) iterator.next();
				if(occupationObj instanceof Occupation){
					Occupation occupationTarget = (Occupation) occupationObj;
					
					if(varElement instanceof Variant){
						
					}
					if(varElement instanceof VarPoint){
						if(occupationTarget.getEsocupado().equals(varElement)){
							iterator.remove();
						}
					}
				}
			}
		}
		
		
		/**
		 * Metodo que obtiene las Ocupaciones de un punto de variacion
		 * @param occupationCollection
		 * @param varPointTarget
		 * @return
		 */
		public static Collection getOccupationList(Collection occupationCollection, VarPoint varPointTarget){
			Collection occupationListFromVP = new ArrayList();
			for(Iterator iterator = occupationCollection.iterator(); iterator.hasNext();){
				EObject occupationObj = (EObject) iterator.next();
				if(occupationObj instanceof Occupation){
					Occupation occupationTarget = (Occupation) occupationObj;
					if(occupationTarget.getEsocupado().equals(varPointTarget)){
						occupationListFromVP.add(occupationObj);
					}
				}
			}
			return occupationListFromVP;
		}
		
		/**
		 * Metodo que dado un punto de variación y una variante devuelve una ocupación si existe
		 * @param varPointOrig
		 * @param varDest
		 * @return
		 */
		public static Occupation searchOccupation(VarPoint varPointOrig, Variant varDest,  Collection occupationList){
			
			Occupation occuToReturn=null;
			Boolean found=false;
			for(Iterator iterator = occupationList.iterator(); iterator.hasNext() && !found;){
				Occupation occupationTarget = (Occupation) iterator.next();
				Variant varTarget = occupationTarget.getOcupadopor();
				VarPoint vpTarget = occupationTarget.getEsocupado();
				
				if(varPointOrig.equals(vpTarget) &&
						varDest.equals(varTarget)){
					occuToReturn = occupationTarget;
					found = true;
				}
						
			}
			
			
			return occuToReturn;
			
		}
		
		
		/**
		 * Metodo que devuelve si un elemento es hijo de algun elemento de una coleccion
		 * @param elements
		 * @param e
		 * @return
		 */
		public static boolean isChildOf(Collection elements, Object e) {
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				if (isChildOf(iter.next(), e)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Metodo que devuelve si un elemento es hijo de algun elemento de una coleccion
		 * @param elements
		 * @param e
		 * @return
		 */
		public static boolean isChildOf(Object parent, Object e) {
			if (parent instanceof Activity) {
				Activity act = (Activity) parent;
				for (Iterator iter = act.getBreakdownElements().iterator(); iter
						.hasNext();) {
					Object child = iter.next();
					if (e == child) {
						return true;
					}
					if (isChildOf(child, e)) {
						return true;
					}
				}
			}
			// else if(parent instanceof WorkProductDescriptor) {
			// WorkProduct wp = ((WorkProductDescriptor)parent).getWorkProduct();
			// if(wp instanceof Artifact) {
			// if(e instanceof WorkProductDescriptor) {
			// if(UmaUtil.isContainedBy(((WorkProductDescriptor)e).getWorkProduct(),
			// wp)) {
			// return true;
			// }
			// }
			// }
			// }
			return false;
		}
		
		/**
		 * Metodo que realiza la copia de un array de elementos a otro Collection (COPIAS ENTERAS, NO REFERENCIAS)
		 * @param elementsToCopy
		 * @return
		 */
		public static Collection prepareCopyList(Collection elementsToCopy) {
			ArrayList copyList = new ArrayList();
			ArrayList originals = new ArrayList();
			Map originalToCopyMap = new HashMap();
			
			for (Iterator iter = elementsToCopy.iterator(); iter
					.hasNext();) {
				Object element = iter.next();
				Object original = element;
				if (original != null) {
					originals.add(original);
					originalToCopyMap.put(original, element);
				}
			}
			
			ArrayList removeList = new ArrayList();
			elementsToCopy.clear();
			// ArrayList addList = new ArrayList();
			for (Iterator iter = originals.iterator(); iter.hasNext();) {
				Object element = iter.next();
				copyList.add(originalToCopyMap.get(element));
			}
			
			return copyList;
		}
		
		/**
		 * Metodo que prepara una lista de elementos a copiar
		 * @param elementsToCopy
		 */
		public static void prepareAddList(Collection elementsToCopy) {
			// cleanup element list before adding it to the activity to avoid adding
			// elements multiple times
			//		

				ArrayList originals = new ArrayList();
				Map originalToCopyMap = new HashMap();
				
				for (Iterator iter = elementsToCopy.iterator(); iter
						.hasNext();) {
					Object element = iter.next();
					Object original = element;
					if (original != null) {
						originals.add(original);
						originalToCopyMap.put(original, element);
					}
				}

				ArrayList removeList = new ArrayList();
				// ArrayList addList = new ArrayList();
				for (Iterator iter = originals.iterator(); iter.hasNext();) {
					Object element = iter.next();
					if (TailoredProcessLogic.isChildOf(originals, element)) {
						removeList.add(originalToCopyMap.get(element));
					}
				}
				elementsToCopy.removeAll(removeList);
			
		}
		
		/**
		 * Hace la copia de elementos de un encapsulamiento a otro objeto encapsulado
		 * @param copy
		 * @return
		 */
		public static Activity getActivityCopy(ProcessPackage copy) {
			if (copy instanceof ProcessComponent) {
				Activity actCopy = ((ProcessComponent) copy)
						.getProcess();
				// copia datos del proceso Package anterior al nuevo
				ProcessPackage pkgCopy = UmaFactory.eINSTANCE
						.createProcessPackage();
				pkgCopy.setName(actCopy.getName());
				pkgCopy.getProcessElements().add(actCopy);
				pkgCopy.getProcessElements().addAll(
						copy.getProcessElements());
				pkgCopy.getDiagrams()
						.addAll(copy.getDiagrams());
				pkgCopy.getChildPackages().addAll(
						copy.getChildPackages());

				return actCopy;
			} else {
				for (Iterator iterator = copy.getProcessElements().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();
					if (element instanceof Activity) {
						return (Activity) element;
					}
				}
			}
			return null;
		}
		
		/**
		 * Copia todos los elementos de tipo proceso (Los desencapsula del paquete)
		 * @param copy
		 * @return
		 */
		public static ProcessElement getProcessElementCopy(ProcessElement copy){

					if (copy instanceof ProcessElement) {
						return (ProcessElement) copy;
					}
				
			return null;
		}
		
		/**
		 * Metodo que setea los atributos básicos de un descriptor
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillDescriptorFields(Descriptor newElement, Descriptor copyElement){
			
			if( (copyElement instanceof Descriptor ) && (newElement instanceof Descriptor)){
				newElement.setIsSynchronizedWithSource(copyElement.getIsSynchronizedWithSource());
			}
		}
		/**
		 * Método que setea los atributos básicos de un WorkBreakDownElement
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillWorkBreakDownElement(WorkBreakdownElement newElement, WorkBreakdownElement copyElement){
			
			if( (copyElement instanceof WorkBreakdownElement ) && (newElement instanceof WorkBreakdownElement)){
				newElement.setIsRepeatable(copyElement.getIsRepeatable());
				newElement.setIsOngoing(copyElement.getIsOngoing());
				newElement.setIsEventDriven(copyElement.getIsEventDriven());
				newElement.getLinkToPredecessor().addAll(copyElement.getLinkToPredecessor());
			}
		}
		
		/**
		 * Metodo que setea los atributos básicos para un BreakDownElement
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillBreakDownElement(BreakdownElement newElement, BreakdownElement copyElement){
			
			if( (copyElement instanceof BreakdownElement ) && (newElement instanceof BreakdownElement)){
				newElement.setPrefix(copyElement.getPrefix());
				newElement.setIsPlanned(copyElement.getIsPlanned());
				newElement.setHasMultipleOccurrences(copyElement.getHasMultipleOccurrences());
				newElement.setIsOptional(copyElement.getIsOptional());
				newElement.setPresentedAfter(copyElement.getPresentedAfter());
				newElement.setPresentedBefore(copyElement.getPresentedBefore());
				newElement.setPlanningData(copyElement.getPlanningData());
				//newElement.setSuperActivities(copyElement.getSuperActivities);
			}
			
		}
		/**
		 * Metodo que setea los atributos básicos para los ProcessElement
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillProcessElement(ProcessElement newElement, ProcessElement copyElement){
			
			if( (copyElement instanceof ProcessElement) && (newElement instanceof ProcessElement)){
				newElement.setProcessLineName(copyElement.getProcessLineName());
				newElement.setTailoredProcessName(copyElement.getTailoredProcessName());
			}
		}
		/**
		 * Metodo que setea los atributos básicos para los DescribableElement
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillDescribableElement(DescribableElement newElement, DescribableElement copyElement){
			
			if( (copyElement instanceof DescribableElement) && (newElement instanceof DescribableElement) ){
				if(copyElement instanceof Variant){
					Variant copyElementVariant = (Variant) copyElement;
					newElement.setPresentationName(copyElementVariant.getVpName());
				}
				else{
					newElement.setPresentationName(copyElement.getPresentationName());
				}
				newElement.setPresentation(copyElement.getPresentation());
				newElement.setShapeicon(copyElement.getShapeicon());
				newElement.setNodeicon(copyElement.getNodeicon());
			}
		}
		/**
		 * Método que setea los atributos básicos para los MethodElement
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillMethodElement(MethodElement newElement, MethodElement copyElement){
			
			if((copyElement instanceof MethodElement) && (newElement instanceof MethodElement)){
				if(copyElement instanceof Variant){
					Variant copyElementVariant = (Variant) copyElement;
					newElement.setBriefDescription(copyElementVariant.getDescription());
					newElement.setName(copyElementVariant.getVpName());
				}
				else{
					newElement.setBriefDescription(copyElement.getBriefDescription());
					newElement.setName(copyElement.getName());
				}
				//Guid?
				newElement.getOwnedRules().addAll(copyElement.getOwnedRules());
				newElement.getMethodElementProperty().addAll(copyElement.getMethodElementProperty());
				newElement.setSuppressed(copyElement.getSuppressed());
				newElement.setOrderingGuide(copyElement.getOrderingGuide());
			}
		}
		/**
		 * Método que setea los atributos básicos para los WorkDefinition
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillWorkDefinition(WorkDefinition newElement, WorkDefinition copyElement){
			if( (copyElement instanceof WorkDefinition) && (newElement instanceof WorkDefinition)){
				newElement.setPrecondition(copyElement.getPrecondition());
				newElement.setPostcondition(copyElement.getPostcondition());
			}
		}
		/**
		 * Método que setea los atributos básicos para Activity
		 * @param newElement
		 * @param copyElement
		 */
		public static void fillActivity(Activity newElement, Activity copyElement){
			if( (copyElement instanceof Activity) && ( newElement instanceof Activity)){
				newElement.getBreakdownElements().addAll(copyElement.getBreakdownElements());
				newElement.getRoadmaps().addAll(copyElement.getRoadmaps());
				newElement.getSupportingMaterials().addAll(copyElement.getSupportingMaterials());
				newElement.getChecklists().addAll(copyElement.getChecklists());
				newElement.getConcepts().addAll(copyElement.getConcepts());
				newElement.getExamples().addAll(copyElement.getExamples());
				newElement.getGuidelines().addAll(copyElement.getGuidelines());
				newElement.getReusableAssets().addAll(copyElement.getReusableAssets());
				newElement.setIsEnactable(copyElement.getIsEnactable());
			}
		}
		
}
