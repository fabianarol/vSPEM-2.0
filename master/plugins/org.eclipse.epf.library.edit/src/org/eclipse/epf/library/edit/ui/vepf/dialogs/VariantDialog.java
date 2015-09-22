package org.eclipse.epf.library.edit.ui.vepf.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.variant2varP;
import org.eclipse.epf.uma.variant2variant;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;

public class VariantDialog extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Group descriptionGrp;
	private Button okBtn;
	private Label depWithVarPointsLbl;
	private Label label1;
	
	private Button eReciVariant2VariantBtn;
	private Button eAddVariant2VariantBtn;
	private Button eDeleteVariant2VariantBtn;
	private List exclusiveVariant2VariantList;
	
	private Button iReciVariant2VariantBtn;
	private Button iAddVariant2VariantBtn;//
	private Button iDeleteVariant2VariantBtn;//
	private List inclusiveVariant2VariantList;
	
	private Group elementPropertiesGrp;
	private Combo elementCombo;
	
	private Button idefVarVariant2VarPointBtn;
	private Button ireciVariant2VarPointBtn;
	private Button iaddVariant2VarPointBtn;
	private Button iDeleteVariant2VarPointBtn;
	private List inclusiveVariant2VarPointList;
	
	
	private Label inclusiveVariant2Variant;
	private Label inclusiveVariant2VarPoint;
	private Label exclusiveVariant2Variant;
	private Button cancelBtn;
	private Group behaviorGrp;
	private Text nameTxt;
	private Label iconLbl;
	private Text descriptionTxt;
	private Text elementTxt;
	private Label descriptionLbl;
	private Label elementLbl;
	private Label nameLbl;
	private Label imageLbl;
	
	private Button idefVar2Var;
	private Button eDefVar2Var;
	
	//Valores externos
	public String nombreN;
	public String elementoN;
	public String iconoN;
	public Image imgIconN;
	
	
	//Control
	public Boolean execute; //Variable para continuar o no la persistencia del objeto
	
	//Documentacion
	public String nombre;
	public String elemento;
	public String icono;
	public String descripcion;
	
	//Propiedades
	public String vpId;
	public String cardMin;
	public String cardMax;
	public Boolean isOpen;
	public Boolean isImplicit;
	public String tipo;
	
	private Boolean editMode;
	private Boolean seeMode;
	
	//Dependencias
	public Collection variantsList;
	public Collection varPointsList;
	
	private Variant variantToAdd;
	
	
	public Collection inclusiveVariant2VariantPersistence;
	public Collection inclusiveVariant2VarPointPersistence;
	public Collection exclusiveVariant2VariantPersistence;
	
	public Collection varElementsDeleted;
	
	//Comportamiento
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			VariantDialog inst = new VariantDialog(shell, SWT.NULL, "", "",null, null, null, null, false, true);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VariantDialog(Shell parent, int style, String nombreN, String elementoN, Image imgIconN, Variant variant, Collection auxVariants, Collection auxVarPoints, Boolean editMode, Boolean seeMode) {
		super(parent, style);
		
		//Seteo de variables externas
		this.nombreN = nombreN;
		this.elementoN = elementoN;
		this.imgIconN = imgIconN;
		
		this.variantToAdd = variant;
		
		this.variantsList = auxVariants;
		if(variantsList != null){
			variantsList.remove(variant);
		}

		this.varPointsList = auxVarPoints;
		
		this.editMode = editMode;
		this.seeMode = seeMode;
		
	}

	public void open() {
		try {
			/*Inicializacion de listas*/
			inclusiveVariant2VariantPersistence = new ArrayList();
			inclusiveVariant2VarPointPersistence  = new ArrayList();
			exclusiveVariant2VariantPersistence  = new ArrayList();
			
			varElementsDeleted = new ArrayList();
			
			final Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

			dialogShell.setLayout(new FormLayout());
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(600, 620);
			dialogShell.setText("Variant");
			dialogShell.setToolTipText("Window for generate Var Points");
					{
						elementPropertiesGrp = new Group(dialogShell, SWT.NONE);
						GridLayout elementPropertiesGrpLayout = new GridLayout();
						elementPropertiesGrpLayout.makeColumnsEqualWidth = true;
						elementPropertiesGrp.setLayout(elementPropertiesGrpLayout);
						FormData elementPropertiesGrpLData = new FormData();
						elementPropertiesGrpLData.left =  new FormAttachment(0, 1000, 12);
						elementPropertiesGrpLData.top =  new FormAttachment(0, 1000, 533);
						elementPropertiesGrpLData.width = 554;
						elementPropertiesGrpLData.height = 1;
						elementPropertiesGrp.setLayoutData(elementPropertiesGrpLData);
						elementPropertiesGrp.setText("Element Properties");
					}
					{
						okBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
						FormData okBtnLData = new FormData();
						okBtnLData.left =  new FormAttachment(0, 1000, 470);
						okBtnLData.top =  new FormAttachment(0, 1000, 558);
						okBtnLData.width = 48;
						okBtnLData.height = 25;
						okBtn.setLayoutData(okBtnLData);
						okBtn.setText("Ok");
						
						
					}
					{
						cancelBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
						FormData cancelBtnLData = new FormData();
						cancelBtnLData.left =  new FormAttachment(0, 1000, 524);
						cancelBtnLData.top =  new FormAttachment(0, 1000, 558);
						cancelBtnLData.width = 48;
						cancelBtnLData.height = 25;
						cancelBtn.setLayoutData(cancelBtnLData);
						cancelBtn.setText("Cancel");

						
					}
					{
						behaviorGrp = new Group(dialogShell, SWT.NONE);
						behaviorGrp.setLayout(null);
						FormData behaviorGrpLData = new FormData();
						behaviorGrpLData.left =  new FormAttachment(0, 1000, 12);
						behaviorGrpLData.top =  new FormAttachment(0, 1000, 217);
						behaviorGrpLData.width = 558;
						behaviorGrpLData.height = 292;
						behaviorGrp.setLayoutData(behaviorGrpLData);
						behaviorGrp.setText("Behavior");
						{
							label1 = new Label(behaviorGrp, SWT.NONE);
							label1.setText("Dependencies with var points");
							label1.setBounds(12, 24, 218, 30);
						}
						{
							depWithVarPointsLbl = new Label(behaviorGrp, SWT.NONE);
							depWithVarPointsLbl.setText("Dependencies with variants");
							depWithVarPointsLbl.setBounds(384, 24, 164, 30);
						}
						{
							exclusiveVariant2Variant = new Label(behaviorGrp, SWT.NONE);
							exclusiveVariant2Variant.setText("--Exclusives (Variant2Variant)");
							exclusiveVariant2Variant.setBounds(389, 188, 171, 18);
						}
						{
							inclusiveVariant2VarPoint = new Label(behaviorGrp, SWT.NONE);
							inclusiveVariant2VarPoint.setText("--Inclusive (Variant2VarPoint)");
							inclusiveVariant2VarPoint.setBounds(12, 54, 158, 21);
						}
						{
							inclusiveVariant2Variant = new Label(behaviorGrp, SWT.NONE);
							inclusiveVariant2Variant.setText("--Inclusive (Variant2Variant)");
							inclusiveVariant2Variant.setBounds(384, 54, 164, 15);
						}
						{
							inclusiveVariant2VarPointList = new List(behaviorGrp, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
//							inclusiveVariant2VarPointList.setBounds(12, 81, 218, 59);
							inclusiveVariant2VarPointList.setBounds(12, 81, 181, 59);
//							inclusiveVariant2VarPointList.setSize(247, 59);
						}
						{
							iDeleteVariant2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iDeleteVariant2VarPointBtn.setText("Delete");
							iDeleteVariant2VarPointBtn.setBounds(7, 146, 50, 30);
						}
						{
							iaddVariant2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iaddVariant2VarPointBtn.setText("Add");
							iaddVariant2VarPointBtn.setBounds(67, 146, 53, 30);
						}
						{
							ireciVariant2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							ireciVariant2VarPointBtn.setText("Reciprocal");
							ireciVariant2VarPointBtn.setBounds(126, 146, 67, 30);
						}
//						{
//							idefVarVariant2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
//							idefVarVariant2VarPointBtn.setText("Def Var");
//							idefVarVariant2VarPointBtn.setBounds(199, 146, 50, 30);
//						}
						{
							inclusiveVariant2VariantList = new List(behaviorGrp, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
//							inclusiveVariant2VariantList.setBounds(352, 81, 200, 59);
							inclusiveVariant2VariantList.setBounds(296, 81, 256, 59);
						}
						{
							iDeleteVariant2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iDeleteVariant2VariantBtn.setText("Delete");
//							iDeleteVariant2VariantBtn.setBounds(352, 146, 50, 30);
							iDeleteVariant2VariantBtn.setBounds(296, 146, 50, 30);
						}
						{
							iAddVariant2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iAddVariant2VariantBtn.setText("Add");
//							iAddVariant2VariantBtn.setBounds(420, 146, 53, 30);
//							iAddVariant2VariantBtn.setOrientation(SWT.HORIZONTAL);
							iAddVariant2VariantBtn.setBounds(358, 146, 53, 30);
						}
						{
							iReciVariant2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iReciVariant2VariantBtn.setText("Reciprocal");
//							iReciVariant2VariantBtn.setBounds(485, 146, 67, 30);
							iReciVariant2VariantBtn.setBounds(423, 146, 67, 30);
						}
						{
							exclusiveVariant2VariantList = new List(behaviorGrp, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
//							exclusiveVariant2VariantList.setBounds(352, 212, 200, 59);
							exclusiveVariant2VariantList.setBounds(296, 212, 256, 59);
						}
						{
							eDeleteVariant2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eDeleteVariant2VariantBtn.setText("Delete");
//							eDeleteVariant2VariantBtn.setBounds(352, 277, 46, 30);
//							eDeleteVariant2VariantBtn.setSize(50, 30);
							eDeleteVariant2VariantBtn.setBounds(300, 277, 46, 30);
						}
						{
							eAddVariant2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eAddVariant2VariantBtn.setText("Add");
//							eAddVariant2VariantBtn.setBounds(419, 277, 53, 30);
							eAddVariant2VariantBtn.setBounds(358, 277, 53, 30);
						}
						{
							eReciVariant2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eReciVariant2VariantBtn.setText("Reciprocal");
//							eReciVariant2VariantBtn.setBounds(484, 277, 64, 30);
							eReciVariant2VariantBtn.setSize(67, 30);
							eReciVariant2VariantBtn.setBounds(423, 277, 64, 30);
						}
						{
							idefVar2Var = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							idefVar2Var.setText("Def Var");
							idefVar2Var.setBounds(496, 146, 56, 30);
						}
						{
							eDefVar2Var = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eDefVar2Var.setText("Def Var");
							eDefVar2Var.setBounds(493, 277, 60, 30);
							eDefVar2Var.setSize(56, 30);
						}
					}
			{
				descriptionGrp = new Group(dialogShell, SWT.NONE);
				descriptionGrp.setLayout(null);
				FormData descriptionGrpLData = new FormData();
				descriptionGrpLData.left =  new FormAttachment(0, 1000, 12);
				descriptionGrpLData.top =  new FormAttachment(0, 1000, 12);
				descriptionGrpLData.width = 554;
				descriptionGrpLData.height = 181;
				descriptionGrp.setLayoutData(descriptionGrpLData);
				descriptionGrp.setText("Description");
				{
					nameLbl = new Label(descriptionGrp, SWT.NONE);
					nameLbl.setText("Name");
					nameLbl.setBounds(12, 30, 60, 28);
				}
				{
					elementLbl = new Label(descriptionGrp, SWT.NONE);
					elementLbl.setText("Element");
					elementLbl.setBounds(12, 58, 60, 30);
				}
				{
					descriptionLbl = new Label(descriptionGrp, SWT.NONE);
					descriptionLbl.setText("Description");
					descriptionLbl.setBounds(12, 88, 60, 14);
				}
				{
					descriptionTxt = new Text(descriptionGrp, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
					descriptionTxt.setBounds(12, 108, 536, 86);
				}
				{
					iconLbl = new Label(descriptionGrp, SWT.NONE);
					iconLbl.setText("Icon");
					iconLbl.setBounds(367, 38, 25, 14);
				}
				{
					nameTxt = new Text(descriptionGrp, SWT.BORDER);
					nameTxt.setBounds(78, 30, 172, 22);
					nameTxt.setText("Identificador del nombre");
					
				}
				{
					imageLbl = new Label(descriptionGrp, SWT.NONE | SWT.BORDER);
//					imageLbl.setText("IMAGE");
					imageLbl.setBounds(412, 18, 70, 70);
					
					//Si distinto de Null entonces insertamos imagen
					if(imgIconN != null){
						imageLbl.setImage(imgIconN);
					}
					
				}
				{
					elementTxt = new Text(descriptionGrp, SWT.NONE);
					elementTxt.setBounds(78, 58, 172, 22);
					elementTxt.setEnabled(false);
				}
				//Variables externas
				{
					if(nombreN != null){
						this.nameTxt.setText(nombreN);
					}
					if(elementoN != null){
						this.elementTxt.setText(elementoN);
					}
				}
				
				//Si estamos editando añadimos además los datos que la variante ya tiene (En caso contrario no hacemos nada)
				if(this.editMode){
					if(variantToAdd != null){
						this.nameTxt.setText(variantToAdd.getVpName());
						this.descriptionTxt.setText(variantToAdd.getDescription());
						//Icono seteado desde la llamada
						//Elemento seteado desde la llamada
						
						//Dependencias (Cliente exclusivamente)
						if(variantToAdd.getClient().size() > 0){
							for(Iterator iterator = variantToAdd.getClient().iterator(); iterator.hasNext();){
								EObject dependenceObj = (EObject) iterator.next();
								
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
					}

				}
				//Si estamos en modo solo vista deshabilitamos todos los campos
				if(this.seeMode){
					
					for(int i =0; i< this.dialogShell.getChildren().length; i++){
						recursiveSetDisabled(this.dialogShell.getChildren()[i]);
						
					}
				}
			}
			
			//***Eventos***//
			/***Eventos Inclusividad Var2VarPoint***/
			
			//Evento que añade un elemento a la lista de inclusividad de Variantes2VarPoints
			this.iaddVariant2VarPointBtn.addSelectionListener(new SelectionListener() {
			      public void widgetSelected(SelectionEvent event) {

			    	  	Display display = Display.getDefault();
						Shell shell = new Shell(display);			    	  
				    	ListWindow listDialog = new ListWindow(shell, SWT.NULL, varPointsList);
				    	listDialog.open();
				    	
				    	if(listDialog.execute){
				    		//Si se han seleccionado elementos en la ventana hija entones añadimos a la lista
					    	if (listDialog.elementsSelected.length > 0){
					    		
					    		
					    		java.util.List auxList = (java.util.List) varPointsList;//Una referencia para poder obtener los objetos por posicion
					    		
					    		for(int i=0; i<listDialog.elementsSelected.length; i++){
					    			EObject element = (EObject) auxList.get(listDialog.elementsSelected[i]);
					    			if (element instanceof VarElement){//Si lo que viene es instancia de varElement cojemos el nombre (Forma genérica)
										
					    				VarElement auxVarElement = (VarElement) element;
					    				//Si el elemento no esta en mi lista de dependencias lo añado (Tanto Exclusiva como inclusiva ya que habria inconsistencia)
					    				if(!inclusiveVariant2VarPointPersistence.contains(auxVarElement)){
					    					inclusiveVariant2VarPointPersistence.add(auxVarElement);
					    					inclusiveVariant2VarPointList.add(auxVarElement.getVpName());
					    					
					    					if(varElementsDeleted.contains(auxVarElement)){
					    						varElementsDeleted.remove(auxVarElement);
					    					}
					    				}	
									}
					    		}
					    	}
				    	}
				    	
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			      }
			});
			
			//Evento que borra de la lista
			this.iDeleteVariant2VarPointBtn.addSelectionListener(new SelectionListener() {
			      public void widgetSelected(SelectionEvent event) {
			    	  
						for(int i=0; i<inclusiveVariant2VarPointList.getSelectionIndices().length; i++){
							
							Boolean  exit = false;
			    			String nameInInterface = inclusiveVariant2VarPointList.getItem(inclusiveVariant2VarPointList.getSelectionIndices()[i]);
			    			
			    			for(Iterator iter = inclusiveVariant2VarPointPersistence.iterator(); iter.hasNext() && exit == false;){
			    				EObject element = (EObject) iter.next();
			    				if(element instanceof VarElement){
			    					VarElement auxVarElement = (VarElement) element;
			    					if (auxVarElement.getVpName().equals(nameInInterface)){
			    						
			    						iter.remove();//Borramos de lista persistente
			    						inclusiveVariant2VarPointList.remove(inclusiveVariant2VarPointList.getSelectionIndices()[i]);//Borramos de la lista de la interfaz
			    						exit = true;
			    						
				    					if(!varElementsDeleted.contains(auxVarElement)){
				    						varElementsDeleted.add(auxVarElement);
				    					}
			    						
			    					}	
			    				}
			    			}
			    		}
						
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			      }
			});
			
			
			/****/
			
			
			/***Eventos Inclusividad Var2Var***/
			//Evento boton Añadir -> Mostramos Variantes
			this.iAddVariant2VariantBtn.addSelectionListener(new SelectionListener() {
			      public void widgetSelected(SelectionEvent event) {
						
			    	  	Display display = Display.getDefault();
						Shell shell = new Shell(display);			    	  
				    	ListWindow listDialog = new ListWindow(shell, SWT.NULL, variantsList);
				    	listDialog.open();
				    	
				    	if(listDialog.execute){
				    		//Si se han seleccionado elementos en la ventana hija entones añadimos a la lista
					    	if (listDialog.elementsSelected.length > 0){
					    		
					    		
					    		java.util.List auxList = (java.util.List) variantsList;//Una referencia para poder obtener los objetos por posicion
					    		
					    		for(int i=0; i<listDialog.elementsSelected.length; i++){
					    			EObject element = (EObject) auxList.get(listDialog.elementsSelected[i]);
					    			if (element instanceof VarElement){//Si lo que viene es instancia de varElement cojemos el nombre (Forma genérica)
										
					    				VarElement auxVarElement = (VarElement) element;
					    				//Si el elemento no esta en mi lista de dependencias lo añado (Tanto Exclusiva como inclusiva ya que habria inconsistencia)
					    				if(!inclusiveVariant2VariantPersistence.contains(auxVarElement) &&
					    						!exclusiveVariant2VariantPersistence.contains(auxVarElement)){
					    					inclusiveVariant2VariantPersistence.add(auxVarElement);
					    					inclusiveVariant2VariantList.add(auxVarElement.getVpName());
					    					
					    					if(varElementsDeleted.contains(auxVarElement)){
					    						varElementsDeleted.remove(auxVarElement);
					    					}
					    				}
									}
					    		}
					    	}
				    	}
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {     
			      }
			});
			//Evento boton Borrar -> Borramos variantes iDeleteVariant2VariantBtn
			this.iDeleteVariant2VariantBtn.addSelectionListener(new SelectionListener() {
			      public void widgetSelected(SelectionEvent event) {
							for(int i=0; i<inclusiveVariant2VariantList.getSelectionIndices().length; i++){
								
								Boolean  exit = false;
				    			String nameInInterface = inclusiveVariant2VariantList.getItem(inclusiveVariant2VariantList.getSelectionIndices()[i]);
				    			
				    			for(Iterator iter = inclusiveVariant2VariantPersistence.iterator(); iter.hasNext() && exit == false;){
				    				EObject element = (EObject) iter.next();
				    				if(element instanceof VarElement){
				    					VarElement auxVarElement = (VarElement) element;
				    					if (auxVarElement.getVpName().equals(nameInInterface)){
				    						
				    						iter.remove();//Borramos de lista persistente
				    						inclusiveVariant2VariantList.remove(inclusiveVariant2VariantList.getSelectionIndices()[i]);//Borramos de la lista de la interfaz
				    						exit = true;
				    						
					    					if(!varElementsDeleted.contains(auxVarElement)){
					    						varElementsDeleted.add(auxVarElement);
					    					}
				    					}	
				    				}
				    			}
				    		}	
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {     
			      }
			});
			/***/
			/**Eventos de exclusividad Var2Var**/
			
			//Evento para añadir elementos exclusivos
			this.eAddVariant2VariantBtn.addSelectionListener(new SelectionListener(){
			      public void widgetSelected(SelectionEvent event) {
			    	  	
			    	    Display display = Display.getDefault();
						Shell shell = new Shell(display);			    	  
				    	ListWindow listDialog = new ListWindow(shell, SWT.NULL, variantsList);
				    	listDialog.open();
				    	
				    	//Si se han seleccionado elementos en la ventana hija entones añadimos a la lista
				    	if(listDialog.execute){

					    	
					    	if (listDialog.elementsSelected.length > 0){
					    		
				
					    		java.util.List auxList = (java.util.List) variantsList;//Una referencia para poder obtener los objetos por posicion
					    		
					    		for(int i=0; i<listDialog.elementsSelected.length; i++){
					    			EObject element = (EObject) auxList.get(listDialog.elementsSelected[i]);
					    			if (element instanceof VarElement){//Si lo que viene es instancia de varElement cojemos el nombre (Forma genérica)
										
					    				VarElement auxVarElement = (VarElement) element;
					    				//Si el elemento no esta en mi lista de dependencias lo añado (Tanto Exclusiva como inclusiva ya que habria inconsistencia)
					    				if(!inclusiveVariant2VariantPersistence.contains(auxVarElement) &&
					    						!exclusiveVariant2VariantPersistence.contains(auxVarElement)){
					    					exclusiveVariant2VariantPersistence.add(auxVarElement);
					    					exclusiveVariant2VariantList.add(auxVarElement.getVpName());
					    					
					    					if(varElementsDeleted.contains(auxVarElement)){
					    						varElementsDeleted.remove(auxVarElement);
					    					}
					    				}

										
										
									}
					    		}
					    	}
				    	}
				    	
				    	
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			      }
			});
			
			//Evento para eliminar elementos exclusividad
			this.eDeleteVariant2VariantBtn.addSelectionListener(new SelectionListener(){
				  public void widgetSelected(SelectionEvent event) {
					  
							for(int i=0; i<exclusiveVariant2VariantList.getSelectionIndices().length; i++){
								
								Boolean  exit = false;
				    			String nameInInterface = exclusiveVariant2VariantList.getItem(exclusiveVariant2VariantList.getSelectionIndices()[i]);
				    			
				    			for(Iterator iter = exclusiveVariant2VariantPersistence.iterator(); iter.hasNext() && exit == false;){
				    				EObject element = (EObject) iter.next();
				    				if(element instanceof VarElement){
				    					VarElement auxVarElement = (VarElement) element;
				    					if (auxVarElement.getVpName().equals(nameInInterface)){
				    						
				    						iter.remove();//Borramos de lista persistente
				    						exclusiveVariant2VariantList.remove(exclusiveVariant2VariantList.getSelectionIndices()[i]);//Borramos de la lista de la interfaz
				    						exit = true;
				    						
					    					if(!varElementsDeleted.contains(auxVarElement)){
					    						varElementsDeleted.add(auxVarElement);
					    					}
				    					}	
				    				}
				    			}
				    		}		  
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {

			      }
			});
			
			/***/
			//Eventos que comprueba si estamos seleccionando la cardinalidad correcta

			//Evento Boton cancelar -> Paramos toda la actividad -> No devolvemos ningún valor
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
			
			//Evento Boton aceptar -> Recogemos los datos -> No se comprueban valores
			this.okBtn.addSelectionListener(new SelectionListener() {
					
			      public void widgetSelected(SelectionEvent event) {
			        System.out.print("Ok Btn");

			       			        
			        //Documentacion
			        //
			        
			        //Identificador
			        if(variantToAdd.getVId() == null){
			        	vpId = java.util.UUID.randomUUID().toString(); //Identificador Java
			        }
			        else{
			        	vpId = variantToAdd.getVId();
			        }
			        
			        icono = iconoN;
			        descripcion = descriptionTxt.getText();
			        //Si el nombre es cadena vacia metemos el mismo identificador que el del VP
			        if(nameTxt.getText() == ""){
			        	nombre = vpId;
			        }
			        else{
			        	nombre = nameTxt.getText();
			        }
			        
			        //Propiedades
			        
			        //Comportamiento
			        
			        //Control de consistencia de dependencias
			        for(Iterator iterator = varElementsDeleted.iterator(); iterator.hasNext();){
			        	VarElement varElement = (VarElement) iterator.next();
			        	UmaUtil.deleteSupplierDependence(varElement, variantToAdd);
			        }
			        
			        //Cerramos la ventana
			        execute=true;
			        parent.dispose();
			       
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			    	  System.out.print("Ok Btn");
			    	  
			    	  //Cerramos la ventana
			    	  execute=true;
			    	  parent.dispose();
				        
			      }
			});
			//Evitamos que el que se pueda cerrar
			dialogShell.addShellListener(new ShellAdapter() {
				public void shellClosed(ShellEvent e) {
//				e.doit = false;
				execute = false;	
				}
			});
			//**Eventos**//
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
	/**
	 * Metodo que desactiva los controles de manera recursiva
	 */
	private void recursiveSetDisabled(Control control) {
		if(control instanceof Composite){
			Composite comp = (Composite) control;
			for(int j=0; j< comp.getChildren().length; j++){
				recursiveSetDisabled(comp.getChildren()[j]);
			}
		}else{
			if( !(control instanceof Label )){
				control.setEnabled(false);
			}
		}	
	}
}
