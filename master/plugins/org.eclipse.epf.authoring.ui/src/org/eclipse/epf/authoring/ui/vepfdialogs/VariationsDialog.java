package org.eclipse.epf.authoring.ui.vepfdialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.ui.vepfdialogs.VarPointDialog;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VarActivity;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarIteration;
import org.eclipse.epf.uma.VarMilestone;
import org.eclipse.epf.uma.VarPhase;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.VarRoleDescriptor;
import org.eclipse.epf.uma.VarTaskDescriptor;
import org.eclipse.epf.uma.VarTeamProfile;
import org.eclipse.epf.uma.VarWorkProductDescriptor;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.variant2variant;
import org.eclipse.epf.uma.varp2variant;
import org.eclipse.epf.uma.vpActivity;
import org.eclipse.epf.uma.vpIteration;
import org.eclipse.epf.uma.vpMilestone;
import org.eclipse.epf.uma.vpPhase;
import org.eclipse.epf.uma.vpRoleDescriptor;
import org.eclipse.epf.uma.vpTaskDescriptor;
import org.eclipse.epf.uma.vpTeamProfile;
import org.eclipse.epf.uma.vpWorkProductDescriptor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.SWT;

import org.eclipse.epf.uma.util.VariationsLogic;

public class VariationsDialog extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Group documentationGrp;
	private Button cancelBtn;
	private Group conflictsGrp;
	private List variantsList;
	private Text descriptionSelectedVariantTxt;
	private Label label1;
	private Button defVariantBtn;
	private Button previewVariantBtn;
	private Group variantGrp;
	private Label typeLbl;
	private Button okBtn;
	private Button seeBtn;
	private Button deleteVariation;
	private Tree conflictsTree;
	private Text typeText;
	private Text maxCardText;
	private Text minCardText;
	private Group variationPointGrp;
	private Label maxCardinalityLbl;
	private Label cardMinLbl;
	private Button previewVarPointBtn;
	private Text descriptionText;
	private Text elementText;
	private Text nameText;
	private Label descriptionLbl;
	private Label elementLbl;
	private Label nameLbl;
	private Text descriptionVariantTxt;

	//Datos de entrada
	private VarPoint varPointSelected;
	private Collection auxVariants;
	private Variant variantSelected;
	private Collection occupationListTailored;
	
	//Variables de control
	public Boolean execute;
	private Collection occupationCollection;
	
	private Variant provokerVariant;//Es la variante que provoca el conflicto de nuestras variones actuales
	
	//Variables de salida
	private Collection auxVariantsToList;
	public Occupation occupationToAdd;
	private Collection occupationList;
	public String description;
	
	//Constantes
	private static final String collisionDependence = "Dependence collision: ";
	private static final String variant2variantExclusive = "<var2var Exclusive> ";
	private static final String supplierElement = "Supplier Element ";
	private static final String clientElement = "Client Element ";
	

	
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			VariationsDialog inst = new VariationsDialog(shell, SWT.NULL, null, null, null);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VariationsDialog(Shell parent, int style, VarElement varElementSelected, Collection auxVariants, Collection occupationListTailored) {
		super(parent, style);
		this.varPointSelected = (VarPoint) varElementSelected;
		this.auxVariants = auxVariants;
		this.occupationListTailored = occupationListTailored;
		this.occupationCollection = VariationsLogic.getOccupationList(occupationListTailored, varPointSelected);
	}

	public void open() {
		try {
			//Inicialización de variables
			execute = false;
			occupationToAdd = UmaFactory.eINSTANCE.createOccupation();
			
			final Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

			dialogShell.setLayout(new FormLayout());
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(600, 612);
			{
				okBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData okBtnLData = new FormData();
				okBtnLData.left =  new FormAttachment(0, 1000, 449);
				okBtnLData.top =  new FormAttachment(0, 1000, 549);
				okBtnLData.width = 63;
				okBtnLData.height = 25;
				okBtn.setLayoutData(okBtnLData);
				okBtn.setText("Ok");
			}
			{
				cancelBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData cancelbtnLData = new FormData();
				cancelbtnLData.left =  new FormAttachment(0, 1000, 518);
				cancelbtnLData.top =  new FormAttachment(0, 1000, 549);
				cancelbtnLData.width = 48;
				cancelbtnLData.height = 25;
				cancelBtn.setLayoutData(cancelbtnLData);
				cancelBtn.setText("Cancel");
			}
			{
				conflictsGrp = new Group(dialogShell, SWT.NONE);
				conflictsGrp.setLayout(null);
				FormData conflictsGrpLData = new FormData();
				conflictsGrpLData.left =  new FormAttachment(0, 1000, 6);
				conflictsGrpLData.top =  new FormAttachment(0, 1000, 448);
				conflictsGrpLData.width = 560;
				conflictsGrpLData.height = 77;
				conflictsGrp.setLayoutData(conflictsGrpLData);
				conflictsGrp.setText("Conflicts");
				{
					conflictsTree = new Tree(conflictsGrp, SWT.NONE | SWT.BORDER | SWT.V_SCROLL);
					conflictsTree.setBounds(6, 21, 458, 62);
				}
				{
					deleteVariation = new Button(conflictsGrp, SWT.PUSH | SWT.CENTER);
					deleteVariation.setText("Delete Variation");
					deleteVariation.setBounds(470, 53, 89, 30);
				}
				{
					seeBtn = new Button(conflictsGrp, SWT.PUSH | SWT.CENTER);
					seeBtn.setText("See");
					seeBtn.setBounds(470, 21, 60, 26);
				}
			}
			{
				variantGrp = new Group(dialogShell, SWT.NONE);
				variantGrp.setLayout(null);
				FormData variantGrpLData = new FormData();
				variantGrpLData.left =  new FormAttachment(0, 1000, 6);
				variantGrpLData.top =  new FormAttachment(0, 1000, 333);
				variantGrpLData.width = 560;
				variantGrpLData.height = 91;
				variantGrp.setLayoutData(variantGrpLData);
				variantGrp.setText("Variant");
				{
					variantsList = new List(variantGrp, SWT.NONE | SWT.BORDER | SWT.V_SCROLL);
					variantsList.setBounds(4, 19, 167, 83);
					auxVariantsToList = new ArrayList();
					//Debemos de añadir solo aquellas variantes compatibles con el punto de variación (Y que no esten además actualmente añadidasa ese punto de variacion)
					auxVariantsToList = VariationsLogic.getVariantsList(auxVariants, varPointSelected.isIsOpen(), varPointSelected, occupationCollection);
					for(Iterator iterator = auxVariantsToList.iterator(); iterator.hasNext();){
						Variant element = (Variant) iterator.next();
						variantsList.add(element.getVpName());
					}
				}
				{
					previewVariantBtn = new Button(variantGrp, SWT.PUSH | SWT.CENTER);
					previewVariantBtn.setText("Preview Variant");
					previewVariantBtn.setBounds(177, 19, 107, 30);
				}
				{
					defVariantBtn = new Button(variantGrp, SWT.PUSH | SWT.CENTER);
					defVariantBtn.setText("Def Variant");
					defVariantBtn.setBounds(177, 55, 70, 30);
					defVariantBtn.setEnabled(false);
				}
				{
					label1 = new Label(variantGrp, SWT.NONE);
					label1.setText("Description");
					label1.setBounds(315, 12, 60, 13);
				}
				{
					descriptionSelectedVariantTxt = new Text(variantGrp, SWT.NONE | SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
					descriptionSelectedVariantTxt.setBounds(315, 31, 239, 71);
				}
			}
			{
				variationPointGrp = new Group(dialogShell, SWT.NONE);
				variationPointGrp.setLayout(null);
				FormData variationPointGrpLData = new FormData();
				variationPointGrpLData.left =  new FormAttachment(0, 1000, 6);
				variationPointGrpLData.top =  new FormAttachment(0, 1000, 116);
				variationPointGrpLData.width = 560;
				variationPointGrpLData.height = 193;
				variationPointGrp.setLayoutData(variationPointGrpLData);
				variationPointGrp.setText("Variation Point");
				{
					nameLbl = new Label(variationPointGrp, SWT.NONE);
					nameLbl.setText("Name");
					nameLbl.setBounds(12, 22, 60, 15);
				}
				{
					elementLbl = new Label(variationPointGrp, SWT.NONE);
					elementLbl.setText("Element");
					elementLbl.setBounds(12, 49, 60, 14);
				}
				{
					descriptionLbl = new Label(variationPointGrp, SWT.NONE);
					descriptionLbl.setText("Description");
					descriptionLbl.setBounds(12, 69, 60, 15);
				}
				{
					nameText = new Text(variationPointGrp, SWT.NONE | SWT.BORDER | SWT.READ_ONLY);
					nameText.setBounds(110, 22, 231, 21);
					nameText.setText(varPointSelected.getVpName());//Nombre
				}
				{
					elementText = new Text(variationPointGrp, SWT.NONE | SWT.BORDER | SWT.READ_ONLY);
					elementText.setBounds(110, 49, 231, 23);
					
					if(varPointSelected instanceof Activity){
						elementText.setText("Activity");
					}
					else if(varPointSelected instanceof Phase){
						elementText.setText("Phase");
					}
					else if(varPointSelected instanceof Role){
						elementText.setText("Role");
					}
					else if(varPointSelected instanceof Task){
						elementText.setText("Task");
					}
					else if(varPointSelected instanceof Iteration){
						elementText.setText("Iteration");
					}
					else if(varPointSelected instanceof Milestone){
						elementText.setText("Milestone");
					}
					else if(varPointSelected instanceof TeamProfile){
						elementText.setText("TeamProfile");
					}
					else if(varPointSelected instanceof WorkProduct){
						elementText.setText("WorkProduct");
					}
				}
				{
					descriptionText = new Text(variationPointGrp, SWT.NONE | SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
					descriptionText.setBounds(12, 90, 542, 71);
					descriptionText.setText(varPointSelected.getDescription());//Descripcion
				}
				{
					previewVarPointBtn = new Button(variationPointGrp, SWT.PUSH | SWT.CENTER);
					previewVarPointBtn.setText("Preview Variation Point");
					previewVarPointBtn.setBounds(353, 49, 137, 23);
				}
				{
					cardMinLbl = new Label(variationPointGrp, SWT.NONE);
					cardMinLbl.setText("Min cardinality");
					cardMinLbl.setBounds(12, 179, 84, 14);
				}
				{
					maxCardinalityLbl = new Label(variationPointGrp, SWT.NONE);
					maxCardinalityLbl.setText("Max");
					maxCardinalityLbl.setBounds(140, 179, 29, 14);
				}
				{
					minCardText = new Text(variationPointGrp, SWT.NONE | SWT.BORDER | SWT.READ_ONLY);
					minCardText.setBounds(96, 179, 32, 20);
					//Transformacion para que no aparezca -1
					if(varPointSelected.getMin() == -1){
						minCardText.setText("N");
					}
					else{
						minCardText.setText(String.valueOf(varPointSelected.getMin()));
					}
					
				}
				{
					maxCardText = new Text(variationPointGrp, SWT.NONE | SWT.BORDER | SWT.READ_ONLY);
					maxCardText.setBounds(169, 179, 31, 20);
					maxCardText.setSize(26, 20);
					maxCardText.setText(String.valueOf(varPointSelected.getMax()));
					if(varPointSelected.getMax() == -1){
						maxCardText.setText("N");
					}
					else{
						maxCardText.setText(String.valueOf(varPointSelected.getMax()));
					}
				}
				{
					typeLbl = new Label(variationPointGrp, SWT.NONE);
					typeLbl.setText("Type");
					typeLbl.setBounds(309, 179, 32, 14);
				}
				{
					typeText = new Text(variationPointGrp, SWT.NONE | SWT.BORDER | SWT.READ_ONLY);
					typeText.setText(VariationsLogic.checkType(varPointSelected));
					typeText.setBounds(347, 179, 202, 20);
				}
			}
			{
				documentationGrp = new Group(dialogShell, SWT.NONE);
				GridLayout documentationGrpLayout = new GridLayout();
				documentationGrpLayout.makeColumnsEqualWidth = true;
				documentationGrp.setLayout(documentationGrpLayout);
				FormData documentationGrpLData = new FormData();
				documentationGrpLData.left =  new FormAttachment(0, 1000, 6);
				documentationGrpLData.top =  new FormAttachment(0, 1000, 12);
				documentationGrpLData.width = 560;
				documentationGrpLData.height = 80;
				documentationGrp.setLayoutData(documentationGrpLData);
				documentationGrp.setText("Documentation");
				{
					descriptionVariantTxt = new Text(documentationGrp, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
					GridData descriptionTxtLData = new GridData();
					descriptionTxtLData.widthHint = 520;
					descriptionTxtLData.heightHint = 68;
					descriptionVariantTxt.setLayoutData(descriptionTxtLData);
				}
			}
			//Eventos
			
			//Evento que hace la previsualización de un punto de variación
			this.previewVarPointBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
				}

				public void widgetSelected(SelectionEvent e) {
					//Solo necesitará los datos básicos de información del punto de variación
					////VPActividad
					String auxName="";
					String auxElement="";
					Image imgIcon = null;
					if(varPointSelected instanceof vpActivity){//Instancia de VarActivity
						auxName="vpActivity";
						auxElement="Activity";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpactivity");
					}
					////VPPhase
					if(varPointSelected instanceof vpPhase){//Instancia de VarActivity
						auxName="vpPhase";
						auxElement="Phase";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpphase");
					}
					////VPIteration
					if(varPointSelected instanceof vpIteration){//Instancia de VarActivity
						auxName="vpIteration";
						auxElement="Iteration";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpiteration");
					}
					////VPRoleDescriptor
					if(varPointSelected instanceof vpRoleDescriptor){//Instancia de VarActivity
						auxName="vpRoleDescriptor";
						auxElement="RoleDescriptor";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vproledescriptor");
					}
					////VPTaskDescriptor
					if(varPointSelected instanceof vpTaskDescriptor){//Instancia de VarActivity
						auxName="vpTaskDescriptor";
						auxElement="TaskDescriptor";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vptaskdescriptor");
					}
					////VPWorkProductDescriptor
					if(varPointSelected instanceof vpWorkProductDescriptor){
						auxName="vpWorkProduct Descriptor";
						auxElement="WorkProduct Descriptor";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpworkproductdescriptor");
					}
					////VPTeamProfile
					if(varPointSelected instanceof vpTeamProfile){
						auxName="vpTeamProfile";
						auxElement="TeamProfile";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpteamprofile");
					}
					////VPMilestone
					if(varPointSelected instanceof vpMilestone){
						auxName="vpMilestone";
						auxElement="Milestone";
						imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpmilestone");
					}
					
					
					Display display = Display.getDefault();
					Shell shell = new Shell(display);
					VarPointDialog varPointDialog = new VarPointDialog(shell, SWT.NULL, auxName, auxElement, imgIcon, varPointSelected, null, null, true, true);
					varPointDialog.open();
					
				}
				
			});
			
			//Evento que hace la previsualización de una variante
			this.previewVariantBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}

				public void widgetSelected(SelectionEvent e) {
					//Si hay alguna variante seleccionada
					if(variantsList.getSelection() != null && variantSelected != null){
						String auxName="";
						String auxElement="";
						Image imgIcon = null;
						
						////VarActivity
						if(variantSelected instanceof VarActivity){//Instancia de VarActivity
							auxName="VarActivity";
							auxElement="Activity";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vactivity");//Icon
						}
						////Phase
						if(variantSelected instanceof VarPhase){//Instancia de VarPhase
							auxName="VarPhase";
							auxElement="Phase";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/varphase");//Icon
						}
						////Iteration
						if(variantSelected instanceof VarIteration){//Instancia de VarIteration
							auxName="VarIteration";
							auxElement="Iteration";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/variteration");//Icon
						}
						////RoleDescriptor
						if(variantSelected instanceof VarRoleDescriptor){//Instancia de VarRoleDescriptor
							auxName="VarRoleDescriptor";
							auxElement="Role Descriptor";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/varroledescriptor");//Icon
						}
						////TaskDescriptor
						if(variantSelected instanceof VarTaskDescriptor){//Instancia de VarTaskDescriptor
							auxName="VarTaskDescriptor";
							auxElement="Task Descriptor";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vartaskdescriptor");//Icon
						}
						
						////VarWorkProductDescriptor
						if(variantSelected instanceof VarWorkProductDescriptor){
							auxName="VarWorkProductDescriptor";
							auxElement="WorkProduct Descriptor";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vworkproductdescriptor");//Icon
						}
						////VarTeamProfile
						if(variantSelected instanceof VarTeamProfile){
							auxName="VarTeamProfile";
							auxElement="Team Profile";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vteamprofile");//Icon
						}
						
						////VarMilestone
						if(variantSelected instanceof VarMilestone){
							auxName="VarMilestone";
							auxElement="Milestone";
							imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vmilestone");//Icon
						}
						
						okBtn.setEnabled(false);//Inicialmente esta desactivado (Solo se activa cuando seleccionamos una Variante)
						
						Display display = Display.getDefault();
						Shell shell = new Shell(display);
						VariantDialog variantDialog = new VariantDialog(shell, SWT.APPLICATION_MODAL, auxName, auxElement, imgIcon, variantSelected, null, null, true, true);
						variantDialog.open();
					}

				}
				
			});
			
			
			//Evento que se produce cuando pulsamos el boton de eliminarDependencia -> Elimina la Variación para que se elimina dicha colisión
			//En definitiva soluciona el conflicto que se esta produciendo
			this.deleteVariation.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
				
				}

				public void widgetSelected(SelectionEvent e) {
					//Si el arbol de conflictos tiene elementos entonces podemos eliminar dicha variación
					if(conflictsTree.getItemCount() > 0 && provokerVariant != null){
						Boolean found = false;
						for(Iterator iterator = occupationListTailored.iterator(); iterator.hasNext() && !found;){
							EObject occupationObj = (EObject) iterator.next();
							if(occupationObj instanceof Occupation){
								Occupation occupationTarget = (Occupation) occupationObj;
								if(occupationTarget.getEsocupado().equals(varPointSelected) && 
										occupationTarget.getOcupadopor().equals(provokerVariant)){
									iterator.remove();//Elimina la variacion
									found = true;
									conflictsTree.removeAll();//Limpiar el arbol de conflictos
									occupationCollection = VariationsLogic.getOccupationList(occupationListTailored, varPointSelected);//refrescamos las ocupaciones
									okBtn.setEnabled(true);
									deleteVariation.setEnabled(false);
									cancelBtn.setEnabled(false);
								}
							}
						}
					}
				}
				
			});
			//Evento Ok Boton -> guardamos la ocupacion generada (Debe estar desactivado si hay algún conflicto!)
			this.okBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					if(variantSelected != null){
						description =descriptionVariantTxt.getText();
						
						occupationToAdd.setOcupadopor(variantSelected);
						occupationToAdd.setEsocupado(varPointSelected);
						occupationToAdd.setDescription(description);
						
						occupationListTailored.add(occupationToAdd);
						
				        //Cerramos la ventana
						execute = true;
				        parent.dispose();
					}
				}

				public void widgetSelected(SelectionEvent e) {
					if(variantSelected != null){
						description =descriptionVariantTxt.getText();
						
						occupationToAdd.setOcupadopor(variantSelected);
						occupationToAdd.setEsocupado(varPointSelected);
						occupationToAdd.setDescription(description);
						
						occupationListTailored.add(occupationToAdd);
						
				        //Cerramos la ventana
						execute = true;
				        parent.dispose();
					}
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
			
			//Evento que muestra la informacion dependiendo de la variante seleccionada (tambien se muestran posibles conflictos)
			this.variantsList.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
					Boolean found = false;
					for(Iterator iterator = auxVariantsToList.iterator(); iterator.hasNext() && !found;){
						Variant auxVariant = (Variant) iterator.next();
						if(auxVariant.getVpName().equals(variantsList.getSelection()[0].toString())){
							found=true;
							descriptionSelectedVariantTxt.setText(auxVariant.getDescription());
							variantSelected = auxVariant;
							
							conflictsTree.removeAll();//Limpiamos el arbol antes de comprobar si hay dependencias
							
							//Comprobamos conflictos (Si hay conflictos entre las ocupaciones del Var Point y la variante seleccionada
							//deshabilitamos el botón OK) Exclusive varPoint2Variant
							//Habilitamos el botón de eliminar cuando hay colisión
							if(checkVar2VarConflict(occupationListTailored, variantSelected)){
								okBtn.setEnabled(false);
								deleteVariation.setEnabled(true);
							}
							else{
								okBtn.setEnabled(true);
								deleteVariation.setEnabled(false);
							}
						}
					}
					
				}

				public void widgetSelected(SelectionEvent e) {
					Boolean found = false;
					for(Iterator iterator = auxVariantsToList.iterator(); iterator.hasNext() && !found;){
						Variant auxVariant = (Variant) iterator.next();
						if(auxVariant.getVpName().equals(variantsList.getSelection()[0].toString())){
							found=true;
							descriptionSelectedVariantTxt.setText(auxVariant.getDescription());
							variantSelected = auxVariant;
							
							conflictsTree.removeAll();//Limpiamos el arbol antes de comprobar si hay dependencias
							provokerVariant = null;//Limpiamos la variable provocadora del conflicto de nuestras variacion
							
							//Comprobamos conflictos (Si hay conflictos entre las ocupaciones del Var Point y la variante seleccionada
							//deshabilitamos el botón OK) Exclusive varPoint2Variant
							//Habilitamos el botón de eliminar cuando hay colisión
							if(checkVar2VarConflict(occupationListTailored, variantSelected)){
								okBtn.setEnabled(false);
								deleteVariation.setEnabled(true);
							}
							else{
								okBtn.setEnabled(true);
								deleteVariation.setEnabled(false);
							}
						}
					}
				}
				
			});
			
			if(variantSelected == null){
				okBtn.setEnabled(false);
			}
			
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
	 * Metodo que examina si se produce algun tipo de conflicto entre las variantes que hay ocupando un Var Point y una variant
	 * FIXME Hay que moverlo a VariationsLogic
	 * @param occupationCollectionTailored
	 * @param variantTarget
	 * @return
	 */
	private Boolean checkVar2VarConflict(Collection occupationCollectionTailored, Variant variantTarget){
		
		
		
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
										treeConstructor(variantTarget, variantOfOccupation);
										
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
										treeConstructor(variantOfOccupation, variantTarget);
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
	 * Metodo que construye el arbol (dada variantes: Client -> Supplier)
	 */
	public void treeConstructor(Variant client, Variant supplier){
		
		//RootText
		TreeItem itemVariantRootText = new TreeItem(conflictsTree, SWT.LEFT);
		itemVariantRootText.setText(collisionDependence+variant2variantExclusive);
		itemVariantRootText.setData(collisionDependence+variant2variantExclusive);
	    
			//Middle Child Texto
		    TreeItem subItemClientText = new TreeItem(itemVariantRootText, SWT.NONE);
		    subItemClientText.setText(clientElement);
		    subItemClientText.setData(clientElement);
		    
		    	//Client Variant
		    	TreeItem subItemClientVariant = new TreeItem(subItemClientText, SWT.NONE);
		    	subItemClientVariant.setText(client.getVpName());
		    	subItemClientVariant.setData(client);
		    	
		    //Middle Child Texto
		    TreeItem subItemSupplierText = new TreeItem(itemVariantRootText, SWT.NONE);
		    subItemSupplierText.setText(supplierElement);
		    subItemSupplierText.setData(supplierElement);
		    
		    	//Supplier Variant
		    	TreeItem subItemSupplierVariant = new TreeItem(subItemSupplierText, SWT.NONE);
		    	subItemSupplierVariant.setText(supplier.getVpName());
		    	subItemSupplierVariant.setData(supplier);
		    	
		
	}
	
}
