package org.eclipse.epf.authoring.ui.vepfdialogs;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ListWindow extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	protected List elementsList;
	private Button cancelBtn;
	private Button addbtn;
	protected boolean execute;
	
	public int[] elementsSelected;
	public Collection inputList;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			ListWindow inst = new ListWindow(shell, SWT.NULL, null);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListWindow(Shell parent, int style, Collection inputList) {
		super(parent, style);
		this.inputList = inputList;
	}

	public void open() {
		try {
			
			final Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

			dialogShell.setLayout(new FormLayout());
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(363, 283);
			{
				cancelBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData cancelBtnLData = new FormData();
				cancelBtnLData.left =  new FormAttachment(0, 1000, 288);
				cancelBtnLData.top =  new FormAttachment(0, 1000, 220);
				cancelBtnLData.width = 54;
				cancelBtnLData.height = 25;
				cancelBtn.setLayoutData(cancelBtnLData);
				cancelBtn.setText("Cancel");
			}
			{
				addbtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData addbtnLData = new FormData();
				addbtnLData.left =  new FormAttachment(0, 1000, 228);
				addbtnLData.top =  new FormAttachment(0, 1000, 220);
				addbtnLData.width = 54;
				addbtnLData.height = 25;
				addbtn.setLayoutData(addbtnLData);
				addbtn.setText("Add");
			}
			{
				FormData elementsListLData = new FormData();
				elementsListLData.left =  new FormAttachment(0, 1000, 30);
				elementsListLData.top =  new FormAttachment(0, 1000, 12);
				elementsListLData.width = 307;
				elementsListLData.height = 202;
				elementsList = new List(dialogShell, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
				elementsList.setLayoutData(elementsListLData);
				
				if (inputList.size() > 0){//Si tiene elementos llenamos la lista
					for(Iterator iterator = inputList.iterator(); iterator.hasNext();){
						EObject element = (EObject) iterator.next();
						
						if (element instanceof VarElement){//Si lo que viene es instancia de varElement cojemos el nombre (Forma genérica)
							
							//Añado nombre o vpName pero hay que localizar donde se produce la modificación y añadir el elemento FIXME
							if(element instanceof Activity){
								Activity auxActivityElement = (Activity) element;
								elementsList.add(auxActivityElement.getName());
							}
							else{
								VarElement auxVarElement = (VarElement) element;
								elementsList.add(auxVarElement.getVpName());
							}

						}
					}
				}
			}
			
			//Evento boton Añadir
			this.addbtn.addSelectionListener(new SelectionListener() {
				
			      public void widgetSelected(SelectionEvent event) {
			        System.out.print("Add Btn");
			        if (elementsList.getSelectionIndices().length > 0){
			        	 execute=true;
					     elementsSelected = new int[elementsList.getSelectionIndices().length];
					     for (int i = 0; i< elementsList.getSelectionIndices().length; i++){
					    	 elementsSelected[i] = elementsList.getSelectionIndices()[i];
					     }
			        	 
					     parent.dispose();
			        }			       
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
				        System.out.print("Add Btn");
				        if (elementsList.getSelectionIndices() != null){
				        	 execute=true;
						     parent.dispose();
				        }
				       
				        
			      }
			});
			
			//Evento boton Cancelar
			this.cancelBtn.addSelectionListener(new SelectionListener() {
				
			      public void widgetSelected(SelectionEvent event) {
			    	  System.out.print("Cancel Btn");
			    	  parent.dispose();
				      execute=false;
			       
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			    	  System.out.print("Cancel Btn");
			    	  parent.dispose();
				      execute=false;
			      }
			});	
			
			//Evitamos que el que se pueda cerrar
			dialogShell.addShellListener(new ShellAdapter() {
				public void shellClosed(ShellEvent e) {
//				e.doit = false;
				execute = false;	
				}
			});
					
			Monitor primary = Display.getDefault().getPrimaryMonitor();
			Rectangle bounds = primary.getBounds();
			Rectangle rect = dialogShell.getBounds();
			    
			int x = bounds.x + (bounds.width - rect.width) / 2;
			int y = bounds.y + (bounds.height - rect.height) / 2;
			    
			dialogShell.setLocation(x, y);
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
