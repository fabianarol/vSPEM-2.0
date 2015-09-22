package org.eclipse.epf.authoring.ui.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;


public class LibraryViewAdaptAction extends BaseSelectionListenerAction {

	/**
	 * The ID for this action.
	 */
	public static final String ACTION_ID = LibraryViewAdaptAction.class.getName();
	
	private Collection selected;

	/**
	 * Creates a new instance.
	 */
	public LibraryViewAdaptAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		for (Iterator objects = selected.iterator(); objects.hasNext();) {
			Object element = objects.next();
			
			//Recomprobamos que lo que hay seleccionado es un tailored Process -> Abrimos el editor de Tailored 
			if(element instanceof TailoredProcessComponent){
				EditorChooser.getInstance().openAdaptProcessEditor(element);
			}
			else{//Si no abrimos el editor adecuado para el objeto correspondiente
				EditorChooser.getInstance().openEditor(element);
			}
		}
	}

	/**
	 * Actualiza cuando hacemos click sobre un elemento
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		if (selection.isEmpty()) {
			return false;
		}

		selected = new HashSet();
		Boolean enable = true;
		
		for (Iterator objects = selection.iterator(); objects.hasNext();) {
			Object element = objects.next();
			Object realObj = TngUtil.unwrap(element);
			if (realObj instanceof MethodElement
					&& realObj instanceof TailoredProcessComponent
					&& !TngUtil.isPredefined((MethodElement) realObj)
					&& !(realObj instanceof ProcessPackage && !(realObj instanceof ProcessComponent))) {
//				selected.add(realObj);
				
				//FIXED CONTROL REPLICAS GENERACIÓN DE PROCESO (DESACTIVAMOS SI HAY PROCESOS CREADOS) -> BORRADO MANUAL USUARIO 19/03/12
				//Se activa cuando no hay procesos creados, para que se active se deben borrar los procesos existentes o no podra adaptarse de nuevo
				TailoredProcessComponent tailoredObj = (TailoredProcessComponent) realObj;
				
				for(Iterator iterator = tailoredObj.getChildPackages().iterator(); iterator.hasNext() && enable;){
					EObject ePkg = (EObject) iterator.next();
					if(ePkg instanceof TailoredCoreProcessPackage){
						TailoredCoreProcessPackage processPackage = (TailoredCoreProcessPackage) ePkg;
						if(processPackage.getChildPackages().size() != 0){
							enable = false;
						}
					}
				}
				if(enable){
					selected.add(realObj);
				}
			}
		}
		return !selected.isEmpty();
	}

}
