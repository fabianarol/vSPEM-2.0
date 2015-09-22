package org.eclipse.epf.authoring.ui.actions;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;



import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.authoring.ui.vepfdialogs.ReportDialog;
import org.eclipse.epf.authoring.ui.vepfdialogs.SucessfullDialog;


import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;


public class LibraryViewGenerateReportAction extends BaseSelectionListenerAction {

	/**
	 * The ID for this action.
	 */
	public static final String ACTION_ID = LibraryViewAdaptAction.class.getName();
	
	private Collection selected;


	/**
	 * Creates a new instance.
	 */
	public LibraryViewGenerateReportAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		for (Iterator objects = selected.iterator(); objects.hasNext();) {
			
			TailoredProcessComponent tailoredComponent = (TailoredProcessComponent) objects.next();
			
			/**
			 * Se va a lanzar la ventana de SeeVariationDialog con toda la info.
			 */

			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			ReportDialog reportDialog = new ReportDialog(shell, SWT.NULL, tailoredComponent);
			reportDialog.open();
			
			if(reportDialog.getExecute()){
			    //Lanzamos ventana de exito
				Display displayNew = Display.getDefault();
				Shell shellNew = new Shell(displayNew);
			    String message = "Report generated successfully";
			    SucessfullDialog sucessfullDialog = new SucessfullDialog(shellNew, SWT.NULL, message);
			    sucessfullDialog.open();
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
		Boolean enable = false;
		
		for (Iterator objects = selection.iterator(); objects.hasNext();) {
			EObject objectTarget = (EObject) objects.next();
			
			if(objectTarget instanceof TailoredProcessComponent){
				TailoredProcessComponent occupation = (TailoredProcessComponent) objectTarget;
				enable = true;
				selected.add(objectTarget);
			}
		}
		return !selected.isEmpty();
	}

}


