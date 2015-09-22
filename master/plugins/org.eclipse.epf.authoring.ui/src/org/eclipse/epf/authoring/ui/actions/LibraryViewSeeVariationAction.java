package org.eclipse.epf.authoring.ui.actions;



import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;





import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.authoring.ui.vepfdialogs.SeeVariationDialog;

import org.eclipse.epf.uma.Occupation;

import org.eclipse.epf.uma.Variation;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;




public class LibraryViewSeeVariationAction extends BaseSelectionListenerAction {

	/**
	 * The ID for this action.
	 */
	public static final String ACTION_ID = LibraryViewAdaptAction.class.getName();
	
	private Collection selected;


	/**
	 * Creates a new instance.
	 */
	public LibraryViewSeeVariationAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		for (Iterator objects = selected.iterator(); objects.hasNext();) {
			Occupation occupationTarget = (Occupation) objects.next();
			
			/**
			 * Se va a lanzar la ventana de SeeVariationDialog con toda la info.
			 */
			
			
			
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			SeeVariationDialog seeVariationDialog = new SeeVariationDialog(shell, SWT.NULL, occupationTarget.getEsocupado(), occupationTarget.getOcupadopor(), occupationTarget);
			seeVariationDialog.open();

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
		Boolean enable = false;
		
		for (Iterator objects = selection.iterator(); objects.hasNext();) {
			EObject objectTarget = (EObject) objects.next();
			
			if(objectTarget instanceof Variation){
				Occupation occupation = (Occupation) objectTarget;
				enable = true;
				selected.add(objectTarget);
			}
		}
		return !selected.isEmpty();
	}

}

