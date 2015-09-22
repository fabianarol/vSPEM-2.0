package org.eclipse.epf.uma.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.variant2variant;
import org.eclipse.epf.uma.varp2variant;

public class VariationsLogic {
	
	public VariationsLogic(){
		
	}
	
	
	/**
	 * Metodo que examina si se produce algun tipo de conflicto entre las variantes que hay ocupando un Var Point y una variant
	 * @param occupationCollectionTailored
	 * @param variantTarget
	 * @param occupationCollection
	 * @param provokerVariant
	 * @return
	 */
	public static Boolean checkVar2VarConflict(Collection occupationCollectionTailored, Variant variantTarget, Collection occupationCollection, Variant provokerVariant){
		
		
		
		Boolean found = false;
		//Exclusion variant2variant
		for(Iterator iterator = occupationCollection.iterator(); iterator.hasNext() && !found;){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				Variant variantOfOccupation = occupationTarget.getOcupadopor();
				
				//Comprobamos las dependencias del elemento seleccionado respecto al elemento actual de la lista de Ocupaciones
				//
				
				//De la parte de cliente
				if(variantTarget.getClient().size() > 0 && !found){
					for(Iterator iter = variantTarget.getClient().iterator(); iter.hasNext() && !found;){
						EObject dependenceObj = (EObject) iter.next();
						if(dependenceObj instanceof Dependences){
							Dependences dependenceTarget = (Dependences) dependenceObj;
							//Si hay dependencia variant2variant
							if(dependenceTarget instanceof variant2variant){
								variant2variant dep = (variant2variant) dependenceTarget;
								//Si es de exclusividad
								if(!dep.isIsInclusive()){
									if(dep.getSupplier().equals(variantOfOccupation)){
										System.out.print("Dep exclusiva variant2variant entre: "+
												variantTarget.getVpName()+" ----> "+variantOfOccupation.getVpName());
										found = true;
										provokerVariant = variantOfOccupation;
										//Se inserta en el arbol la dependencia
										//
										//treeConstructor(variantTarget, variantOfOccupation);
										
									}
								}
							}
						}
					}
				}
				
				//De la parte de supplier si en cliente no encontro
				if(variantTarget.getSupplier().size() > 0 && !found){
					for(Iterator iter = variantTarget.getSupplier().iterator(); iter.hasNext() && !found;){
						EObject dependenceObj = (EObject) iter.next();
						if(dependenceObj instanceof Dependences){
							Dependences dependenceTarget = (Dependences) dependenceObj;
							//Si hay dependencia variant2variant
							if(dependenceTarget instanceof variant2variant){
								variant2variant dep = (variant2variant) dependenceTarget;
								//Si es de exclusividad
								if(!dep.isIsInclusive()){
									if(dep.getClient().equals(variantOfOccupation)){
										System.out.print("Dep exclusiva variant2variant entre: "+
												variantOfOccupation.getVpName()+" ----> "+variantTarget.getVpName());
										found = true;
										provokerVariant = variantOfOccupation;
										//Se inserta en el arbol la dependencia
										//
										//treeConstructor(variantOfOccupation, variantTarget);
									}
								}
							}
						}
					}
				}

			}
		}
		//Podemos borrar en el arbol si no encontro nada (Limpiarlo)
		
		return found;
	}
	
	/**
	 * Metodo que obtiene las ocupaciones de un punto de variacion
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
	 * Metodo que comprueba si una variante pertenece a un grupo de variaciones
	 * @param occupationCollection
	 * @param variant
	 * @return
	 */
	public static Boolean isInOccupationCollection(Collection occupationCollection, Variant variant){
		Boolean found = false;
		for(Iterator iterator = occupationCollection.iterator(); iterator.hasNext() && !found;){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				if(occupationTarget.getOcupadopor().equals(variant)){
					found = true;
				}
			}
		}
		
		return found;
	}
	
	/**
	 * Metodo que me devuelve las variantes compatibles con un punto de variacion
	 * @param auxVariants
	 * @param isOpen
	 * @param varPointSelected
	 * @param occupationCollection
	 * @return
	 */
	public static Collection getVariantsList(Collection auxVariants, boolean isOpen, VarPoint varPointSelected, Collection occupationCollection) {
		Collection variantList = new ArrayList();
		
		//Si es abierto devuelvo todas las VARIANTES del mismo tipo
		if(isOpen){
			for(Iterator iterator = auxVariants.iterator(); iterator.hasNext();){
				EObject element = (EObject) iterator.next();
				if(element instanceof Variant){
					Variant variantTarget = (Variant) element;
					//Si son del mismo tipo
					if(variantTarget.getClass().getSuperclass().equals(
							varPointSelected.getClass().getSuperclass())){
						
						if(!VariationsLogic.isInOccupationCollection(occupationCollection, variantTarget)){
							variantList.add(variantTarget);
						}
						
					}
				}
			}
		}
		//Si no es abierto devuelvo las VARIANTES que tengan varP -> variant (Dependencia)
		else{
			Collection varpoint2variant = varPointSelected.getClient();
			for(Iterator iterator = varpoint2variant.iterator(); iterator.hasNext();){
				Dependences depTarget =  (Dependences) iterator.next();
				if(depTarget instanceof varp2variant){//Si la dependencia es del tipo varp2variant entonces añado la Variante a la lista
					varp2variant depVarp2Variant = (varp2variant) depTarget;

					if(!VariationsLogic.isInOccupationCollection(occupationCollection, depVarp2Variant.getSupplier())){
						variantList.add(depVarp2Variant.getSupplier());
					}
				}
			}
			
		}
		return variantList;
	}
	
	/**
	 * Metodo que comprueba el tipo de punto de variacion segun su cardinalidad
	 * @param varPointSelected
	 * @return
	 */
	public static String checkType(VarPoint varPointSelected){
		
		String type = "";
		//Obligatorio
		if(varPointSelected.getMin() == 1  &&
				varPointSelected.getMax() == 1 ){
				type = "Obligatory";			
		}
		//Optativo
		else if(varPointSelected.getMin() == 0){
				type = "Optative";
		}
		//Alternativo
		else if(varPointSelected.getMax() == -1){
				type = "Alternative";
		}
		return type;
	}
}
