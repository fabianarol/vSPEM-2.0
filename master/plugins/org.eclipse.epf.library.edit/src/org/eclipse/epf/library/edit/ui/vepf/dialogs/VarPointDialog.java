package org.eclipse.epf.library.edit.ui.vepf.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.varP2varP;
import org.eclipse.epf.uma.varp2variant;
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

import org.eclipse.epf.uma.util.UmaUtil;

public class VarPointDialog extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Group descriptionGrp;
	private Button okBtn;
	private Label depWithVarPointsLbl;
	private Label label1;
	private Label typeLbl;
	private Button eReciVarPoint2VarPointBtn;
	private Button eAddVarPoint2VarPoint;
	private Button eDeleteVarPoint2VarPointBtn;
	private List exclusiveVarPoint2VarPointList;
	private Button iReciVarPoint2VarPoint;
	private Button iAddVarPoint2VarPoint;
	private Button iDeleteVarPoint2VarPointBtn;
	private List inclusiveVarPoint2VarPointList;
	private Button iDefVarVarPoint2VariantBtn;
	private Button iReciVarPoint2VariantBtn;
	private Button iAddVarPoint2VariantBtn;
	private Button iDeleteVarPoint2VariantBtn;
	private List inclusiveVarPoint2VariantList;
	private Label label2;
	private Label varPoint2Variant;
	private Label varPoint2VarPoint;
	private Text typeTxt;
	private Combo cardinalityMaxCombo;
	private Label cardinalityMaxLbl;
	private Combo isImplicitCombo;
	private Combo isOpenCombo;
	private Combo cardinalityMinCombo;
	private Label isImplicitLbl;
	private Label isOpenLbl;
	private Label cardinalityMinLbl;
	private Button cancelBtn;
	private Group behaviorGrp;
	private Group propertiesGrp;
	private Text elementTxt;
	private Text nameTxt;
	private Label iconLbl;
	private Text descriptionTxt;
	private Label descriptionLbl;
	private Label elementLbl;
	private Label nameLbl;
	private Label imageLbl;
	
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
	public String cardMinString;
	public String cardMaxString;
	public int cardMin;
	public int cardMax;
	public Boolean isOpen;
	public Boolean isImplicit;
	public String tipo;
	
	private Boolean editMode;
	private Boolean seeMode;
	
	//Dependencias
	public Collection variantsList;
	public Collection varPointsList;
	private VarPoint varPointToAdd;
	
	public Collection inclusiveVarPoint2VariantPersistence;
	public Collection inclusiveVarPoint2VarPointPersistence;
	public Collection exclusiveVarPoint2VarPointPersistence;
	
	public Collection varElementsDeleted;
	
	//Comportamiento
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			VarPointDialog inst = new VarPointDialog(shell, SWT.NULL, "", "", null, null, null, null, false, true);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VarPointDialog(Shell parent, int style, String nombreN, String elementoN, Image imgIconN, VarPoint varPoint, Collection auxVariants, Collection auxVarPoints, Boolean editMode, Boolean seeMode) {
		super(parent, style);
		
		//Seteo de variables externas
		this.nombreN = nombreN;
		this.elementoN = elementoN;
		this.imgIconN = imgIconN;
		
		this.varPointToAdd = varPoint;
		
		this.variantsList = auxVariants;
		
		this.varPointsList = auxVarPoints;
		
		if(varPointsList != null){
			varPointsList.remove(varPoint);//Eliminamos la autoreferencia
		}

		
		this.editMode = editMode;
		this.seeMode = seeMode;
	}

	public void open() {
		try {
			/*Inicializacion de listas*/
			inclusiveVarPoint2VariantPersistence = new ArrayList();
			inclusiveVarPoint2VarPointPersistence = new ArrayList();
			exclusiveVarPoint2VarPointPersistence = new ArrayList();
			
			varElementsDeleted = new ArrayList();
			
			
			final Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

			dialogShell.setLayout(new FormLayout());
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(600, 688);
			dialogShell.setText("Var Point");
			dialogShell.setToolTipText("Window for generate Var Points");
					{
						okBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
						FormData okBtnLData = new FormData();
						okBtnLData.left =  new FormAttachment(0, 1000, 439);
						okBtnLData.top =  new FormAttachment(0, 1000, 625);
						okBtnLData.width = 64;
						okBtnLData.height = 25;
						okBtn.setLayoutData(okBtnLData);
						okBtn.setText("Ok");
						
						
					}
					{
						cancelBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
						FormData cancelBtnLData = new FormData();
						cancelBtnLData.left =  new FormAttachment(0, 1000, 509);
						cancelBtnLData.top =  new FormAttachment(0, 1000, 625);
						cancelBtnLData.width = 63;
						cancelBtnLData.height = 25;
						cancelBtn.setLayoutData(cancelBtnLData);
						cancelBtn.setText("Cancel");

						
					}
					{
						behaviorGrp = new Group(dialogShell, SWT.NONE);
						behaviorGrp.setLayout(null);
						FormData behaviorGrpLData = new FormData();
						behaviorGrpLData.left =  new FormAttachment(0, 1000, 12);
						behaviorGrpLData.top =  new FormAttachment(0, 1000, 342);
						behaviorGrpLData.width = 554;
						behaviorGrpLData.height = 259;
						behaviorGrp.setLayoutData(behaviorGrpLData);
						behaviorGrp.setText("Behavior");
						{
							label1 = new Label(behaviorGrp, SWT.NONE);
							label1.setText("Dependencies with variants");
							label1.setBounds(12, 24, 218, 20);
						}
						{
							depWithVarPointsLbl = new Label(behaviorGrp, SWT.NONE);
							depWithVarPointsLbl.setText("Dependencies with var points");
							depWithVarPointsLbl.setBounds(384, 24, 164, 17);
						}
						{
							varPoint2VarPoint = new Label(behaviorGrp, SWT.NONE);
							varPoint2VarPoint.setText("--Exclusives (VarPoint2VarPoint)");
							varPoint2VarPoint.setBounds(372, 161, 171, 17);
						}
						{
							varPoint2Variant = new Label(behaviorGrp, SWT.NONE);
							varPoint2Variant.setText("--Inclusive (VarPoint2Variant)");
							varPoint2Variant.setBounds(12, 44, 158, 16);
						}
						{
							label2 = new Label(behaviorGrp, SWT.NONE);
							label2.setText("--Inclusive (VarPoint2VarPoint)");
							label2.setBounds(379, 44, 164, 16);
						}
						{
							inclusiveVarPoint2VariantList = new List(behaviorGrp, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
							inclusiveVarPoint2VariantList.setBounds(12, 60, 218, 59);
							inclusiveVarPoint2VariantList.setSize(244, 59);
						}
						{
							iDeleteVarPoint2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iDeleteVarPoint2VariantBtn.setText("Delete");
							iDeleteVarPoint2VariantBtn.setBounds(12, 125, 47, 30);
							iDeleteVarPoint2VariantBtn.setSize(50, 30);
						}
						{
							iAddVarPoint2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iAddVarPoint2VariantBtn.setText("Add");
							iAddVarPoint2VariantBtn.setBounds(71, 125, 53, 30);
						}
						{
							iReciVarPoint2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iReciVarPoint2VariantBtn.setText("Reciprocal");
							iReciVarPoint2VariantBtn.setBounds(130, 125, 67, 30);
						}
						{
							iDefVarVarPoint2VariantBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iDefVarVarPoint2VariantBtn.setText("Def Var");
							iDefVarVarPoint2VariantBtn.setBounds(209, 125, 50, 30);
						}
						{
							inclusiveVarPoint2VarPointList = new List(behaviorGrp, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
							inclusiveVarPoint2VarPointList.setBounds(348, 60, 200, 59);
						}
						{
							iDeleteVarPoint2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iDeleteVarPoint2VarPointBtn.setText("Delete");
							iDeleteVarPoint2VarPointBtn.setBounds(348, 125, 50, 30);
						}
						{
							iAddVarPoint2VarPoint = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iAddVarPoint2VarPoint.setText("Add");
							iAddVarPoint2VarPoint.setBounds(416, 125, 53, 30);
						}
						{
							iReciVarPoint2VarPoint = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							iReciVarPoint2VarPoint.setText("Reciprocal");
							iReciVarPoint2VarPoint.setBounds(481, 125, 67, 30);
						}
						{
							exclusiveVarPoint2VarPointList = new List(behaviorGrp, SWT.BORDER |SWT.MULTI | SWT.V_SCROLL);
							exclusiveVarPoint2VarPointList.setBounds(348, 178, 200, 59);
						}
						{
							eDeleteVarPoint2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eDeleteVarPoint2VarPointBtn.setText("Delete");
							eDeleteVarPoint2VarPointBtn.setBounds(348, 243, 50, 30);
						}
						{
							eAddVarPoint2VarPoint = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eAddVarPoint2VarPoint.setText("Add");
							eAddVarPoint2VarPoint.setBounds(416, 243, 53, 30);
						}
						{
							eReciVarPoint2VarPointBtn = new Button(behaviorGrp, SWT.PUSH | SWT.CENTER);
							eReciVarPoint2VarPointBtn.setText("Reciprocal");
							eReciVarPoint2VarPointBtn.setBounds(481, 243, 67, 30);
						}
					}
					{
						propertiesGrp = new Group(dialogShell, SWT.NONE);
						propertiesGrp.setLayout(null);
						FormData propertiesGrpLData = new FormData();
						propertiesGrpLData.left =  new FormAttachment(0, 1000, 12);
						propertiesGrpLData.top =  new FormAttachment(0, 1000, 200);
						propertiesGrpLData.width = 554;
						propertiesGrpLData.height = 118;
						propertiesGrp.setLayoutData(propertiesGrpLData);
						propertiesGrp.setText("Properties");
						{
							cardinalityMinLbl = new Label(propertiesGrp, SWT.NONE);
							cardinalityMinLbl.setText("Minimum cardinality");
							cardinalityMinLbl.setBounds(12, 32, 115, 30);
						}
						{
							isOpenLbl = new Label(propertiesGrp, SWT.NONE);
							isOpenLbl.setText("Is Open");
							isOpenLbl.setSize(60, 30);
							isOpenLbl.setBounds(12, 68, 60, 30);
						}
						{
							isImplicitLbl = new Label(propertiesGrp, SWT.NONE);
							isImplicitLbl.setText("Is Implicit");
							isImplicitLbl.setSize(60, 30);
							isImplicitLbl.setBounds(12, 104, 60, 30);
						}
						{
							cardinalityMinCombo = new Combo(propertiesGrp, SWT.READ_ONLY);
							cardinalityMinCombo.setSize(33, 37);
							cardinalityMinCombo.setBounds(159, 32, 60, 23);
						}
						{
							isOpenCombo = new Combo(propertiesGrp, SWT.READ_ONLY);
							isOpenCombo.setSize(33, 37);
							isOpenCombo.setBounds(159, 68, 60, 23);
						}
						{
							isImplicitCombo = new Combo(propertiesGrp, SWT.READ_ONLY);
							isImplicitCombo.setBounds(159, 104, 60, 23);
							isImplicitCombo.setEnabled(false);
						}
						{
							cardinalityMaxLbl = new Label(propertiesGrp, SWT.READ_ONLY);
							cardinalityMaxLbl.setText("Maximum cardinality");
							cardinalityMaxLbl.setBounds(325, 32, 117, 30);
						}
						{
							typeLbl = new Label(propertiesGrp, SWT.NONE);
							typeLbl.setText("Type");
							typeLbl.setSize(60, 30);
							typeLbl.setBounds(325, 68, 60, 30);
						}
						{
							cardinalityMaxCombo = new Combo(propertiesGrp, SWT.READ_ONLY);
							cardinalityMaxCombo.setSize(33, 37);
							cardinalityMaxCombo.setBounds(488, 32, 60, 23);
						}
						{
							typeTxt = new Text(propertiesGrp, SWT.NONE);
							typeTxt.setBounds(409, 68, 139, 23);
							typeTxt.setEnabled(false);
						}
					}
			{
				descriptionGrp = new Group(dialogShell, SWT.NONE);
				descriptionGrp.setLayout(null);
				FormData descriptionGrpLData = new FormData();
				descriptionGrpLData.left =  new FormAttachment(0, 1000, 12);
				descriptionGrpLData.top =  new FormAttachment(0, 1000, 12);
				descriptionGrpLData.width = 554;
				descriptionGrpLData.height = 164;
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
					descriptionTxt.setBounds(11, 108, 536, 66);
				}
				{
					iconLbl = new Label(descriptionGrp, SWT.NONE);
					iconLbl.setText("Icon");
					iconLbl.setBounds(367, 38, 30, 30);
				}
				{
					nameTxt = new Text(descriptionGrp, SWT.BORDER);
					nameTxt.setBounds(78, 30, 172, 22);
					nameTxt.setText("Identificador del nombre");
					
				}
				{
					elementTxt = new Text(descriptionGrp, SWT.NONE);
					elementTxt.setBounds(78, 58, 172, 22);
					elementTxt.setEnabled(false);
				}
				{
					imageLbl = new Label(descriptionGrp, SWT.NONE | SWT.BORDER);
//					imageLbl.setText("IMAGE");
					imageLbl.setBounds(409, 18, 70, 70);
					
					//Si distinto de Null entonces insertamos imagen
					if(imgIconN != null){
						imageLbl.setImage(imgIconN);
					}
					
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
				//Variables por defecto
				//
				{
				this.cardinalityMinCombo.add("0");
				this.cardinalityMinCombo.add("1");
				this.cardinalityMinCombo.select(0);
				
				
				this.cardinalityMaxCombo.add("1");
				this.cardinalityMaxCombo.add("N");
				this.cardinalityMaxCombo.select(1);
				
				this.isOpenCombo.add("YES");
				this.isOpenCombo.add("NO");
				
				this.isOpenCombo.select(0);
				
				this.isImplicitCombo.add("NO");
				this.isImplicitCombo.select(0);
				
				this.typeTxt.setText("Optative");
				
				}
				//Si estamos editando añadimos además los datos que el punto de variación ya tiene (En caso contrario no hacemos nada)
				if(this.editMode){
					if(varPointToAdd != null){
						//Set Nombre y descripcion
						this.nameTxt.setText(varPointToAdd.getVpName());
						this.descriptionTxt.setText(varPointToAdd.getDescription());
						//Icono seteado desde la llamada
						//Elemento seteado desde la llamada
						
						//Set cardinalidades
						int cardMin = varPointToAdd.getMin();
						int cardMax = varPointToAdd.getMax();
						String cardMinString = "";
						String cardMaxString = "";	
						cardMinString =  String.valueOf(cardMin);
						//Set Open
						Boolean isOpen = varPointToAdd.isIsOpen();
						String isOpenString = "";
						if(isOpen){
							isOpenString = "YES";
						}
						else{
							isOpenString = "NO";
						}
						//Set Implicit
						Boolean isImplicit = varPointToAdd.isIsImplicit();
						String isImplicitString = "";
						if(isImplicit){
							isImplicitString = "YES";
						}
						else{
							isImplicitString = "NO";
						}
						
						if(cardMax == -1){
							cardMaxString = "N";
						}
						else{
							cardMaxString = String.valueOf(cardMax);
						}				
						Boolean found = false;
						for(int i = 0; i<cardinalityMinCombo.getItems().length && !found; i++){
							if(cardinalityMinCombo.getItems()[i].toString().equals(cardMinString)){
								cardinalityMinCombo.select(i);
								found = true;
							}
						}
						found = false;
						for(int i = 0; i<cardinalityMaxCombo.getItems().length && !found; i++){
							if(cardinalityMaxCombo.getItems()[i].toString().equals(cardMaxString)){
								cardinalityMaxCombo.select(i);
								found = true;
							}
						}
						
						//Set Open
						found = false;
						for(int i=0; i<isOpenCombo.getItems().length && !found; i++){
							if(isOpenCombo.getItems()[i].toString().equals(isOpenString)){
								isOpenCombo.select(i);
								found = true;
							}
						}
						
						//Set Implicit
						found = false;
						for(int i=0; i<isImplicitCombo.getItems().length && !found; i++){
							if(isImplicitCombo.getItems()[i].toString().equals(isImplicitString)){
								isImplicitCombo.select(i);
								found = true;
							}
						}
						
						//Type
						modificaTipo();
						
						//Dependencias (Cliente exclusivamente)
						if(varPointToAdd.getClient().size() > 0){
							for(Iterator iterator = varPointToAdd.getClient().iterator(); iterator.hasNext();){
								EObject dependenceObj = (EObject) iterator.next();
								
								if(dependenceObj instanceof Dependences){
									Dependences dependence = (Dependences) dependenceObj;
									
									//Dependencia varpoint2variant
									if(dependence instanceof varp2variant){
										varp2variant varp2variantObj = (varp2variant) dependence;
										inclusiveVarPoint2VariantList.add(varp2variantObj.getSupplier().getVpName());
										inclusiveVarPoint2VariantPersistence.add(varp2variantObj.getSupplier());
									}
									//Dependencia varpoint2varpoint
									else if(dependence instanceof varP2varP){
										varP2varP varP2varPObj = (varP2varP) dependence;
										
										//Inclusiva
										if(dependence.isIsInclusive()){
											inclusiveVarPoint2VarPointList.add(varP2varPObj.getSupplier().getVpName());
											inclusiveVarPoint2VarPointPersistence.add(varP2varPObj.getSupplier());
										}
										//Exclusiva
										else{
											exclusiveVarPoint2VarPointList.add(varP2varPObj.getSupplier().getVpName());
											exclusiveVarPoint2VarPointPersistence.add(varP2varPObj.getSupplier());
										}
									}
								}
							}
						}
					}
				}
				
				//Si estamos en modo solo vista deshabilitamos todos los campos
				if(this.seeMode){
					this.cardinalityMaxCombo.setEnabled(false);
					this.cardinalityMinCombo.setEnabled(false);
					this.isOpenCombo.setEnabled(false);
					for(int i =0; i< this.dialogShell.getChildren().length; i++){
						recursiveSetDisabled(this.dialogShell.getChildren()[i]);
						
					}
				}
				
			}
			
			//***Eventos***//
			/***Eventos Inclusividad VarPoint2Variant***/
			
			//Evento que añade un elemento a la lista de inclusividad de VarPoint2Variant
			this.iAddVarPoint2VariantBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent e) {
					
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
				    				if(!inclusiveVarPoint2VariantPersistence.contains(auxVarElement)){
				    					inclusiveVarPoint2VariantPersistence.add(auxVarElement);
				    					inclusiveVarPoint2VariantList.add(auxVarElement.getVpName());
				    					
				    					if(varElementsDeleted.contains(auxVarElement)){
				    						varElementsDeleted.remove(auxVarElement);
				    					}
				    				}	
								}
				    		}
				    	}
			    	}
					
				}
				
			});
			
			//Evento que borra de la lista
			this.iDeleteVarPoint2VariantBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}


				public void widgetSelected(SelectionEvent e) {
					
					for(int i=0; i<inclusiveVarPoint2VariantList.getSelectionIndices().length; i++){
						
						Boolean  exit = false;
		    			String nameInInterface = inclusiveVarPoint2VariantList.getItem(inclusiveVarPoint2VariantList.getSelectionIndices()[i]);
		    			
		    			for(Iterator iter = inclusiveVarPoint2VariantPersistence.iterator(); iter.hasNext() && exit == false;){
		    				EObject element = (EObject) iter.next();
		    				if(element instanceof VarElement){
		    					VarElement auxVarElement = (VarElement) element;
		    					if (auxVarElement.getVpName().equals(nameInInterface)){
		    						
		    						iter.remove();//Borramos de lista persistente
		    						inclusiveVarPoint2VariantList.remove(inclusiveVarPoint2VariantList.getSelectionIndices()[i]);//Borramos de la lista de la interfaz
		    						exit = true;
		    						
		    						if(!varElementsDeleted.contains(auxVarElement)){
		    							varElementsDeleted.add(auxVarElement);
		    						}
		    					}	
		    				}
		    			}
		    		}
				}
				
			});
			
			/***Eventos Inclusividad VarPoint2VarPoint***/
			//Evento boton Añadir -> Mostramos Puntos de Variación
			this.iAddVarPoint2VarPoint.addSelectionListener(new SelectionListener() {


				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}


				public void widgetSelected(SelectionEvent arg0) {
					
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
				    				if(!inclusiveVarPoint2VarPointPersistence.contains(auxVarElement) &&
				    						!exclusiveVarPoint2VarPointPersistence.contains(auxVarElement)){
				    					inclusiveVarPoint2VarPointPersistence.add(auxVarElement);
				    					inclusiveVarPoint2VarPointList.add(auxVarElement.getVpName());
				    					
			    						if(varElementsDeleted.contains(auxVarElement)){
			    							varElementsDeleted.remove(auxVarElement);
			    						}
				    				}
								}
				    		}
				    	}
			    	}
					
				}
			});
			this.iDeleteVarPoint2VarPointBtn.addSelectionListener(new SelectionListener () {


				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}


				public void widgetSelected(SelectionEvent e) {
					for(int i=0; i<inclusiveVarPoint2VarPointList.getSelectionIndices().length; i++){
						
						Boolean  exit = false;
		    			String nameInInterface = inclusiveVarPoint2VarPointList.getItem(inclusiveVarPoint2VarPointList.getSelectionIndices()[i]);
		    			
		    			for(Iterator iter = inclusiveVarPoint2VarPointPersistence.iterator(); iter.hasNext() && exit == false;){
		    				EObject element = (EObject) iter.next();
		    				if(element instanceof VarElement){
		    					VarElement auxVarElement = (VarElement) element;
		    					if (auxVarElement.getVpName().equals(nameInInterface)){
		    						
		    						iter.remove();//Borramos de lista persistente
		    						inclusiveVarPoint2VarPointList.remove(inclusiveVarPoint2VarPointList.getSelectionIndices()[i]);//Borramos de la lista de la interfaz
		    						exit = true;
		    						
		    						if(!varElementsDeleted.contains(auxVarElement)){
		    							varElementsDeleted.add(auxVarElement);
		    						}
		    					}	
		    				}
		    			}
		    		}
				}
				
			});
			/**Eventos de exclusividad VarPoint2VarPoint**/
			
			//Evento para añadir elementos exclusivos
			this.eAddVarPoint2VarPoint.addSelectionListener(new SelectionListener() {


				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}


				public void widgetSelected(SelectionEvent e) {
					
					Display display = Display.getDefault();
					Shell shell = new Shell(display);			    	  
			    	ListWindow listDialog = new ListWindow(shell, SWT.NULL, varPointsList);
			    	listDialog.open();
			    	
			    	//Si se han seleccionado elementos en la ventana hija entones añadimos a la lista
			    	if(listDialog.execute){

				    	
				    	if (listDialog.elementsSelected.length > 0){
				    		
			
				    		java.util.List auxList = (java.util.List) varPointsList;//Una referencia para poder obtener los objetos por posicion
				    		
				    		for(int i=0; i<listDialog.elementsSelected.length; i++){
				    			EObject element = (EObject) auxList.get(listDialog.elementsSelected[i]);
				    			if (element instanceof VarElement){//Si lo que viene es instancia de varElement cojemos el nombre (Forma genérica)
									
				    				VarElement auxVarElement = (VarElement) element;
				    				//Si el elemento no esta en mi lista de dependencias lo añado (Tanto Exclusiva como inclusiva ya que habria inconsistencia)
				    				if(!inclusiveVarPoint2VarPointPersistence.contains(auxVarElement) &&
				    						!exclusiveVarPoint2VarPointPersistence.contains(auxVarElement)){
				    					exclusiveVarPoint2VarPointPersistence.add(auxVarElement);
				    					exclusiveVarPoint2VarPointList.add(auxVarElement.getVpName());
				    					
			    						if(varElementsDeleted.contains(auxVarElement)){
			    							varElementsDeleted.remove(auxVarElement);
			    						}
				    				}

									
									
								}
				    		}
				    	}
			    	}
					
				}
				
			});
			
			//Evento para eliminar elementos exclusividad
			this.eDeleteVarPoint2VarPointBtn.addSelectionListener(new SelectionListener (){

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent e) {
					
					for(int i=0; i<exclusiveVarPoint2VarPointList.getSelectionIndices().length; i++){
						
						Boolean  exit = false;
		    			String nameInInterface = exclusiveVarPoint2VarPointList.getItem(exclusiveVarPoint2VarPointList.getSelectionIndices()[i]);
		    			
		    			for(Iterator iter = exclusiveVarPoint2VarPointPersistence.iterator(); iter.hasNext() && exit == false;){
		    				EObject element = (EObject) iter.next();
		    				if(element instanceof VarElement){
		    					VarElement auxVarElement = (VarElement) element;
		    					if (auxVarElement.getVpName().equals(nameInInterface)){
		    						
		    						iter.remove();//Borramos de lista persistente
		    						exclusiveVarPoint2VarPointList.remove(exclusiveVarPoint2VarPointList.getSelectionIndices()[i]);//Borramos de la lista de la interfaz
		    						exit = true;
		    						
		    						if(!varElementsDeleted.contains(auxVarElement)){
		    							varElementsDeleted.add(auxVarElement);
		    						}
		    						
		    					}	
		    				}
		    			}
		    		}
					
				}
				
			});
			
			//Eventos que comprueba si estamos seleccionando la cardinalidad correcta
			this.cardinalityMaxCombo.addSelectionListener(new SelectionListener() {
				
			      public void widgetSelected(SelectionEvent event) {
			        System.out.print("Max Combo");
			        compruebaCombos();
			        modificaTipo();
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			    	 System.out.print("Max Combo");
			    	 compruebaCombos();
			    	 modificaTipo();
			      }
			});
			
			this.cardinalityMinCombo.addSelectionListener(new SelectionListener() {
				
			      public void widgetSelected(SelectionEvent event) {
			        System.out.print("Min Combo");
			        compruebaCombos();
			        modificaTipo();
			      }

			      public void widgetDefaultSelected(SelectionEvent event) {
			    	 System.out.print("Min Combo");
			    	 compruebaCombos();
			    	 modificaTipo();
			      }
			});
			
			
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
			        vpId = java.util.UUID.randomUUID().toString(); //Identificador Java
			        elemento = elementTxt.getText();//Elemento
			        icono = iconoN;
			        descripcion = descriptionTxt.getText();
			        //Si el nombre es cadena vacia metemos el mismo identificador que el del VP
			        if(nameTxt.getText() == ""){
			        	nombre = vpId;
			        }
			        else{
			        	nombre = nameTxt.getText();
			        }
			        
			        /////Propiedades
			        
			        //Cardinalidad
			        cardMinString = cardinalityMinCombo.getText();
			        cardMaxString = cardinalityMaxCombo.getText();
			        
			        cardMin = Integer.parseInt(cardMinString);
			        if(cardMaxString.equals("N")){
			        	cardMax = -1;
			        }
			        else{
			        	cardMax = Integer.parseInt(cardMaxString);
			        }
			        
			        //Tipo
			        tipo = typeTxt.getText();
			        if(isOpenCombo.getText().toString().equals("YES")){
			        	isOpen = true;
					}
			        else{
			        	isOpen = false;
			        }
			        if(isImplicitCombo.getText().toString().equals("YES")){
			        	isImplicit = true;
			        }
			        else{
			        	isImplicit = false;
			        }
			        //Control de consistencia de dependencias
			        for(Iterator iterator = varElementsDeleted.iterator(); iterator.hasNext();){
			        	VarElement varElement = (VarElement) iterator.next();
			        	UmaUtil.deleteSupplierDependence(varElement, varPointToAdd);
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
			//Evitamos que el 
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
	
	
	/**
	 * Este metodo comprobara que la cardinalidad Max > Min y lo cambia segun sea necesario  ---> FIXME! 
	 */
	public void compruebaCombos(){
		//Si Max == N -> todo bien (Porque la minima nunca tendra N)
		if(this.cardinalityMaxCombo.getText().equals("N")){
			
		}
		else{
			//Recogemos los valores
			int auxMin = Integer.parseInt(this.cardinalityMinCombo.getText());
			int auxMax = Integer.parseInt(this.cardinalityMaxCombo.getText());
			
			if(auxMin > auxMax){//Si auxMin > auxMax, lo seteamos por defecto: cardMin = minimoValor ; cardMax = maxValor
				this.cardinalityMinCombo.select(0);//El minimo valor
				this.cardinalityMaxCombo.select(this.cardinalityMaxCombo.getItemCount());//El maximo valor
			}
		}
		
	}
	/**
	 * Metodo que modifica el TIPO según la cardinalidad actual (o cuando esta cambie)
	 */
	public void modificaTipo(){
		//Obligatorio
		if(this.cardinalityMinCombo.getText().equals("1")  &&
			this.cardinalityMaxCombo.getText().equals("1")){
				this.typeTxt.setText("Obligatory");
				
		}
		//Optativo
		else if(this.cardinalityMinCombo.getText().equals("0")){
				this.typeTxt.setText("Optative");
		}
		//Alternativo
		else if(this.cardinalityMaxCombo.getText().equals("N")){
				this.typeTxt.setText("Alternative");
		}
	}
}
