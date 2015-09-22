package org.eclipse.epf.uma.util;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.variant2varP;
import org.eclipse.epf.uma.variant2variant;



/**
 * Clase encargada de la lógica de las variantes (Logica insuficiente para implementar métodos)
 * @author flucas
 *
 */
public class VariantLogic {
	
	
	public VariantLogic(){
		
	}
	
	/**
	 * Metodo que añade dependencias de un objeto a las distintas listas
	 * @param dependenceObj
	 * @param inclusiveVariant2VarPointList
	 * @param inclusiveVariant2VarPointPersistence
	 * @param inclusiveVariant2VariantList
	 * @param inclusiveVariant2VariantPersistence
	 * @param exclusiveVariant2VariantList
	 * @param exclusiveVariant2VariantPersistence
	 */
	public static void addDependence(EObject dependenceObj, ArrayList<String> inclusiveVariant2VarPointList, ArrayList<VarPoint> inclusiveVariant2VarPointPersistence, 
			ArrayList<String> inclusiveVariant2VariantList, ArrayList<Variant> inclusiveVariant2VariantPersistence, ArrayList<String> exclusiveVariant2VariantList, ArrayList<Variant> exclusiveVariant2VariantPersistence){
		
			if(dependenceObj instanceof Dependences){
			Dependences dependence = (Dependences) dependenceObj;
			
			//Dependencia variant2VarPoint
			if(dependence instanceof variant2varP){
				variant2varP variant2varPObj = (variant2varP) dependence;
				inclusiveVariant2VarPointList.add(variant2varPObj.getSupplier().getVpName());
				inclusiveVariant2VarPointPersistence.add(variant2varPObj.getSupplier());
			}
			//Dependencia variant2variant
			else if(dependence instanceof variant2variant){
				variant2variant variant2variantObj = (variant2variant) dependence;
				
				//Inclusiva
				if(dependence.isIsInclusive()){
					inclusiveVariant2VariantList.add(variant2variantObj.getSupplier().getVpName());
					inclusiveVariant2VariantPersistence.add(variant2variantObj.getSupplier());
				}
				//Exclusiva
				else{
					exclusiveVariant2VariantList.add(variant2variantObj.getSupplier().getVpName());
					exclusiveVariant2VariantPersistence.add(variant2variantObj.getSupplier());
				}
			}
		}
	}

}
