package org.eclipse.epf.authoring.ui.vepfdialogs;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.epf.uma.impl.TailoredProcessComponentImpl;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.TailoredProcessLogic;


import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CreateChildCommand.Helper;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.LibraryViewAdaptAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewCopyAction;
import org.eclipse.epf.authoring.ui.actions.TailoredCopyProcessAction;
import org.eclipse.epf.authoring.ui.vepfpersistence.PersistenceLogic;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.common.serviceability.MsgBox;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.MethodElementCreateChildCommand;
import org.eclipse.epf.library.edit.navigator.TailoredProcessComponentItemProvider;
import org.eclipse.epf.library.edit.navigator.TailoredProcessesPackageItemProvider;
import org.eclipse.epf.library.edit.navigator.VariationItemProvider;
import org.eclipse.epf.library.edit.navigator.vpActivityItemProvider;
import org.eclipse.epf.library.edit.process.command.CreateTailoredCoreProcessComponentCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Dependences;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VarActivity;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.Variation;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkDefinition;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.varP2varP;
import org.eclipse.epf.uma.variant2varP;
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
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;

public class TailoredProcessDialog extends org.eclipse.swt.widgets.Dialog implements IStatefulItemProvider {

	private Shell dialogShell;
	private Group descriptionGrp;
	private Group VariationsGrp;
	private Label variationsCreatedLbl;
	private Button okBtn;
	private Button previewbtn;
	private Button cancelBtn;
	private Button saveBtn;
	private Button deleteConflictsBtn;
	private Button seeConflictsBtn;
	private Button seeVarPointBtn;
	private Button addVariationVarPointsBtn;
	private Button seeVariationBtn;
	private Button deleteVariationBtn;
	private Button addVariationVariationsCompletedBtn;
	private Button helpBtn;
	private Tree conflictsTree;
	private Tree cardinalityConflictsTree;
	private List varPointsPendingList;
	private Tree variationsCompletedTree;
	private Label conflictsLbl;
	private Label variationsPointsPendingLbl;
	private Label cardinalityLbl;
	private Text nameTxt;
	private Text descriptionTxt;
	private Label descriptionLbl;
	private Label nameLbl;
	
	//Datos de entrada
	private TailoredProcessComponent tailoredProcess;
	private Collection auxVariants;
	private Collection auxVarPoints;

	//Variables de control
	Boolean execute = false;
	private ModelStructure modelStruct;
	
	//Variables temporales (Ocupacion)
	private Occupation newOccupation;
	private Collection occupationList;
	private TreeItem[] varElementsCompletedTreeSelected;
	private TreeItem[] conflictsTreeSelected;

	
	//Constantes para la composición del arbol de variaciones
	private static final String variationPoint = "Punto de variación";
	private static final String variation = "Variación";
	private static final String variant = "Variante";
	private static final String cardinalityCollisionMin = "Colisión Card Min:";
	private static final String cardinalityCollisionMax = "Colisión Card Max:";
	
	private static final String varP2varPECollision = "Collision dependence VarP2VarP Exclusiveness:";
	private static final String varP2varPICollision = "Collision dependence VarP2VarP Inclusiveness:";
	private static final String variant2variantICollision = "Collision dependence Variant2Variant Inclusiveness:";
	private static final String varP2variantICollision = "Collision dependence VarP2Variant Inclusiveness:";
	private static final String clientDependence = "Client Element";
	private static final String supplierDependence = "Supplier Element";
	private static final String adaptedProcess = "Adapted";
	
	//Comandos
	protected TailoredCopyProcessAction tailoredCopyProcessAction;
	
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			TailoredProcessDialog inst = new TailoredProcessDialog(shell, SWT.NULL, null, null, null);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  

	public TailoredProcessDialog(Shell parent, int style, TailoredProcessComponent tailoredProcess, Collection auxVariants, Collection auxVarPoints) {
		super(parent, style);
		
		//Seteo de variables
		this.tailoredProcess = tailoredProcess;
		this.auxVariants = auxVariants;
		this.auxVarPoints = auxVarPoints;
		

	}

	public void open() {
		try {
			final Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			
			occupationList = new ArrayList();
			if(tailoredProcess.getOccupations().size() > 0){
				occupationList = tailoredProcess.getOccupations();
			}
			ModelStructure aux = new ModelStructure();
			modelStruct = aux.DEFAULT;//extraigo el modelStructure por defecto definido
			
			dialogShell.setLayout(new FormLayout());
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(660, 641);
			dialogShell.setText("Tailored Process");
			dialogShell.setToolTipText("Window for adapt the tailored process");
			{
				previewbtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData previewbtnLData = new FormData();
				previewbtnLData.left =  new FormAttachment(0, 1000, 394);
				previewbtnLData.top =  new FormAttachment(0, 1000, 573);
				previewbtnLData.width = 60;
				previewbtnLData.height = 30;
				previewbtn.setLayoutData(previewbtnLData);
				previewbtn.setText("Preview");
			}
			{
				cancelBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData cancelBtnLData = new FormData(60, 30);
				cancelBtnLData.left =  new FormAttachment(0, 1000, 584);
				cancelBtnLData.top =  new FormAttachment(0, 1000, 573);
				cancelBtnLData.width = 60;
				cancelBtnLData.height = 30;
				cancelBtn.setLayoutData(cancelBtnLData);
				cancelBtn.setText("Cancel");
				cancelBtn.setSize(60, 30);
			}
			{
				okBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData okBtnLData = new FormData();
				okBtnLData.left =  new FormAttachment(0, 1000, 538);
				okBtnLData.top =  new FormAttachment(0, 1000, 573);
				okBtnLData.width = 40;
				okBtnLData.height = 30;
				okBtn.setLayoutData(okBtnLData);
				okBtn.setText("Ok");
			}
			{
				saveBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				FormData saveBtnLData = new FormData();
				saveBtnLData.left =  new FormAttachment(0, 1000, 466);
				saveBtnLData.top =  new FormAttachment(0, 1000, 573);
				saveBtnLData.width = 60;
				saveBtnLData.height = 30;
				saveBtn.setLayoutData(saveBtnLData);
				saveBtn.setText("Save");
			}
			{
				VariationsGrp = new Group(dialogShell, SWT.NONE);
				VariationsGrp.setLayout(null);
				FormData VariationsGrpLData = new FormData();
				VariationsGrpLData.left =  new FormAttachment(0, 1000, 12);
				VariationsGrpLData.top =  new FormAttachment(0, 1000, 184);
				VariationsGrpLData.width = 614;
				VariationsGrpLData.height = 365;
				VariationsGrp.setLayoutData(VariationsGrpLData);
				VariationsGrp.setText("Variations");
				{
					variationsPointsPendingLbl = new Label(VariationsGrp, SWT.NONE);
					GridData variationsPointsPendingLblLData = new GridData();
					variationsPointsPendingLblLData.widthHint = 135;
					variationsPointsPendingLblLData.heightHint = 15;
					variationsPointsPendingLbl.setText("Variations Points pending");
					variationsPointsPendingLbl.setBounds(338, 23, 135, 15);
				}
				{
					variationsCreatedLbl = new Label(VariationsGrp, SWT.NONE);
					GridData variationsCreatedLblLData = new GridData();
					variationsCreatedLblLData.widthHint = 112;
					variationsCreatedLblLData.heightHint = 15;
					variationsCreatedLbl.setText("Variations completed");
					variationsCreatedLbl.setBounds(12, 23, 112, 15);
				}
				{
					conflictsLbl = new Label(VariationsGrp, SWT.NONE);
					conflictsLbl.setText("Conflicts");
					conflictsLbl.setBounds(229, 224, 60, 15);
				}
				{
					variationsCompletedTree = new Tree(VariationsGrp, SWT.NONE | SWT.V_SCROLL | SWT.BORDER);
					variationsCompletedTree.setBounds(12, 44, 270, 130);
					refreshTreeVariations();
				}
				{
					varPointsPendingList = new List(VariationsGrp, SWT.NONE | SWT.V_SCROLL | SWT.BORDER);
					varPointsPendingList.setBounds(343, 44, 270, 131);
					
					//Rellenamos la lista con los puntos de variacion entrantes
					for(Iterator iterator = auxVarPoints.iterator(); iterator.hasNext();){
						EObject element = (EObject) iterator.next();
						if(element instanceof VarElement){
							VarElement auxVarElement = (VarElement) element;
							varPointsPendingList.add(auxVarElement.getVpName());
						}
					}
				}
				{
					conflictsTree = new Tree(VariationsGrp, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL |SWT.BORDER);
					conflictsTree.setBounds(229, 245, 251, 126);

				}
				{
					addVariationVariationsCompletedBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					addVariationVariationsCompletedBtn.setText("Add Variation");
					addVariationVariationsCompletedBtn.setBounds(12, 180, 85, 23);
				}
				{
					seeVariationBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					seeVariationBtn.setText("See");
					seeVariationBtn.setBounds(103, 180, 60, 23);
					seeVariationBtn.setSize(60, 24);
				}
				{
					deleteVariationBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					deleteVariationBtn.setText("Delete");
					deleteVariationBtn.setBounds(169, 180, 60, 23);
				}
				{
					addVariationVarPointsBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					addVariationVarPointsBtn.setText("Add Variation");
					addVariationVarPointsBtn.setBounds(344, 180, 85, 23);
				}
				{
					seeVarPointBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					seeVarPointBtn.setText("See");
					seeVarPointBtn.setBounds(435, 180, 60, 23);
				}
				{
					seeConflictsBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					seeConflictsBtn.setText("See");
					seeConflictsBtn.setBounds(497, 303, 60, 30);
				}
				{
					deleteConflictsBtn = new Button(VariationsGrp, SWT.PUSH | SWT.CENTER);
					deleteConflictsBtn.setText("Delete Variation");
					deleteConflictsBtn.setBounds(497, 339, 97, 30);
				}
				{
					cardinalityConflictsTree = new Tree(VariationsGrp, SWT.NONE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
					cardinalityConflictsTree.setBounds(12, 245, 205, 126);
				}
				{
					cardinalityLbl = new Label(VariationsGrp, SWT.NONE);
					cardinalityLbl.setText("Cardinality");
					cardinalityLbl.setBounds(12, 224, 60, 15);
				}

			}
			{
				descriptionGrp = new Group(dialogShell, SWT.NONE);
				descriptionGrp.setText("Documentation");
				FormData descriptionGrpLData = new FormData();
				descriptionGrpLData.left =  new FormAttachment(0, 1000, 12);
				descriptionGrpLData.top =  new FormAttachment(0, 1000, 0);
				descriptionGrpLData.width = 614;
				descriptionGrpLData.height = 160;
				descriptionGrp.setLayoutData(descriptionGrpLData);
				descriptionGrp.setLayout(null);
				{
					nameLbl = new Label(descriptionGrp, SWT.NONE);
					nameLbl.setText("Name");
					nameLbl.setBounds(12, 30, 60, 28);
				}
				{
					descriptionLbl = new Label(descriptionGrp, SWT.NONE);
					descriptionLbl.setText("Description");
					descriptionLbl.setBounds(12, 64, 60, 14);
				}
				{
					descriptionTxt = new Text(descriptionGrp, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
					descriptionTxt.setBounds(12, 84, 596, 86);
					if(tailoredProcess.getBriefDescription() != null){
						descriptionTxt.setText(tailoredProcess.getBriefDescription());
					}
				}
				{
					nameTxt = new Text(descriptionGrp, SWT.NONE | SWT.BORDER);
					nameTxt.setBounds(78, 30, 172, 22);
					if(tailoredProcess.getName() != null){
						nameTxt.setText(tailoredProcess.getName());
					}
				}
				{
					helpBtn = new Button(descriptionGrp, SWT.PUSH | SWT.CENTER);
					helpBtn.setBounds(528, 12, 80, 66);
					helpBtn.setImage( LibraryEditPlugin.INSTANCE.getImage("full/others/Help"));
				}
			}
			//**Acciones**//
			//Acciones de inicializacion (Comprobacion de cardinalidad) Para todos los puntos de variación a utilizar
			if(checkConflicts()){
				okBtn.setEnabled(false); //Deshabilitamos
			}
			else{
				okBtn.setEnabled(true); //Habilitamos
			}
			
			//Eventos
			//Evento que se ejecuta al pulsar la visualización de una variación
			this.seeVariationBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent e) {
					//Si tenemos algun elemento seleccionado
					if(varElementsCompletedTreeSelected != null && varElementsCompletedTreeSelected.length > 0){
						VarPoint varPointTarget = null;
						Variant variantTarget= null;
						for(int i=0; i < varElementsCompletedTreeSelected.length; i++){
							 TreeItem treeItemSelected = varElementsCompletedTreeSelected[i];
							 if(treeItemSelected.getData() instanceof VarElement){
								 VarElement varElementSelected = (VarElement) treeItemSelected.getData();
								 if(varElementSelected instanceof Variant){
									 variantTarget = (Variant) varElementSelected;
									 TreeItem vpFatherItem = treeItemSelected.getParentItem();
									 if(vpFatherItem != null){
										 if(vpFatherItem.getData() instanceof VarPoint){
											 varPointTarget = (VarPoint) vpFatherItem.getData();		 	
										 }
									 }

								 }
								 else if(varElementSelected instanceof VarPoint){
									 //Que podriamos hacer aquí?Pintarlo?¿Como?
								 }
							 }
						}
						if( varPointTarget != null && variantTarget != null){		
							Occupation occupationTarget = TailoredProcessLogic.searchOccupation(varPointTarget, variantTarget, occupationList);
							if(occupationTarget != null){
								Display display = Display.getDefault();
								Shell shell = new Shell(display);
								SeeVariationDialog seeVariationDialog = new SeeVariationDialog(shell, SWT.NULL, varPointTarget, variantTarget, occupationTarget);
								seeVariationDialog.open();
							}
						}
					}
					
				}
				
			});
			
			
			//Evento que se ejecuta al pulsar visualizar un punto variación
			this.seeVarPointBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent e) {
					//Solo necesitará los datos básicos de información del punto de variación
					if(varPointsPendingList.getSelection().length > 0 ){
						//Si tenemos alguno punto de variación seleccionado lanzamos la ventana
						VarElement varElementSelected = getItemSelected();
						if (varElementSelected instanceof VarPoint){
							VarPoint varPointSelected = (VarPoint) varElementSelected;
							String auxName="";
							String auxElement="";
							Image imgIcon = null;
							
							////VPActividad
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
					}
				}
				
			});
			
			//Evento que se ejecuta al pulsar el botón Ok. (Generación de las Variaciones)
			this.okBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
					
				}

				public void widgetSelected(SelectionEvent e) {
					//Si no hay conflictos podremos generar las variaciones
					if(conflictsTree.getItemCount() == 0 && cardinalityConflictsTree.getItemCount() == 0){
						
							//Obtenemos el paquete de variaciones correspondiente al proceso adaptado
							VariationsPackage variationPkg = UmaUtil.findVariationsPackage(tailoredProcess);
							variationPkg.getVariations().clear();
							
							
							
							//Llenamos el paquete de las variaciones !FIXME Persistencia
							if(variationPkg != null){
								
								//Cuando se acaba un proceso adaptado, se pierden los datos anteriores, esto es asi por motivos de diseño
								//Si se desean dejar habría que eliminar .clear();
								//
								
								//*Guardamos el estado final, para ello debemos copiar las referencias*//
								Collection copyOccupationListToVarpkg = new ArrayList();
								for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
									Occupation occupation = (Occupation) iterator.next();
									
									Occupation copyOccupation  = UmaFactory.eINSTANCE.createOccupation();//El objeto nuevo
									copyOccupation.setEsocupado(occupation.getEsocupado());
									copyOccupation.setOcupadopor(occupation.getOcupadopor());
									copyOccupation.setDescription(occupation.getDescription());
									
									copyOccupationListToVarpkg.add(copyOccupation);
								}
								/**/
								
								
								variationPkg.getVariations().addAll(copyOccupationListToVarpkg);
								PersistenceLogic.saveVariations(variationPkg);//Persistencia***********
								
								
								//Una vez realizada la persistencia de las variaciones hay que que copiar los procesos que se utilizan (Capabilitty y Delivery)
								//No hacer una autoreferencia sino copiarlos! y posteriormente asignarle los elementos pertenecientes a cada uno
								//
								
								//Buscamos Capabilitty Package
								TailoredCoreProcessPackage capabilityPkg = (TailoredCoreProcessPackage) UmaUtil.createTailoredCoreProcessPackage(tailoredProcess,  modelStruct.tailoredCapabilityPatternPath);
								
								//Buscamos Delivery Package
								TailoredCoreProcessPackage deliveryPkg = (TailoredCoreProcessPackage) UmaUtil.createTailoredCoreProcessPackage(tailoredProcess, modelStruct.tailoredDeliveryProcessPath);
								
								//Procesos añadidos
								Collection capabilityAdded = new ArrayList();
								Collection deliveryAdded = new ArrayList();
								
								if(capabilityPkg != null && deliveryPkg != null){	
									for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
										//Buscamos los procesos que se estan utilizando
										EObject occupationObj = (EObject) iterator.next();
										if(occupationObj instanceof Occupation){
											Occupation occupationTarget = (Occupation) occupationObj;
											VarPoint varPointTarget = occupationTarget.getEsocupado();//Extraemos el Punto de Variación
											if(varPointTarget != null){		
												EObject processParentObj = UmaUtil.getProcessComponent((MethodElement) varPointTarget);
												if(processParentObj != null){
													if(processParentObj instanceof ProcessComponent){
														
														ProcessComponent processParent = (ProcessComponent) processParentObj;
														//Si es de tipo capability
														if(processParent.getProcess() instanceof CapabilityPattern){
															if(!capabilityAdded.contains(processParent)){
																capabilityAdded.add(processParent);
															}
														}
														//Si es de tipo delivery
														else if(processParent.getProcess() instanceof DeliveryProcess){
															if(!deliveryAdded.contains(processParent)){
																deliveryAdded.add(processParent);
															}
														}
													}
												}
											}
										}
									}
									
									//Capabilitties a copiar
									if(capabilityAdded.size() > 0){
										for(Iterator iter = capabilityAdded.iterator(); iter.hasNext();){					
											EObject processObj = (EObject) iter.next();		
											if(processObj instanceof ProcessComponent){
												//Creamos el proceso (Una replica pero con los elementos SPEM nada mas)	
												PersistenceLogic.createProcess((ProcessComponent) processObj, capabilityPkg, variationPkg, descriptionTxt.getText(),
														modelStruct, adaptedProcess, tailoredProcess);
											}
										}
									}
									//Deliveries a copiar
									if(deliveryAdded.size() > 0){
										for(Iterator iter = deliveryAdded.iterator(); iter.hasNext();){					
											EObject processObj = (EObject) iter.next();		
											if(processObj instanceof ProcessComponent){
												//Creamos el proceso (Una replica pero con los elementos SPEM nada mas)	
												PersistenceLogic.createProcess((ProcessComponent) processObj, deliveryPkg, variationPkg , descriptionTxt.getText(), 
														modelStruct, adaptedProcess, tailoredProcess);
											}
										}
									}
									
								}
								
								
								
								//*Guardamos el estado final, para ello debemos copiar las referencias*//
								Collection copyOccupationList = new ArrayList();
								for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
									Occupation occupation = (Occupation) iterator.next();
									
									Occupation copyOccupation  = UmaFactory.eINSTANCE.createOccupation();//El objeto nuevo
									copyOccupation.setEsocupado(occupation.getEsocupado());
									copyOccupation.setOcupadopor(occupation.getOcupadopor());
									copyOccupation.setDescription(occupation.getDescription());
									
									copyOccupationList.add(copyOccupation);
								}
								
								tailoredProcess.setName(nameTxt.getText());
								tailoredProcess.setBriefDescription(descriptionTxt.getText());
								tailoredProcess.getOccupations().clear();
								tailoredProcess.getOccupations().addAll(copyOccupationList);
								PersistenceLogic.saveTailoredProcess(tailoredProcess);//Incovacion a la persistencia
								/**/
										
								parent.dispose();//Cerramos la ventana
							
							}
						
					}
				}
				
			});
			//Eveneto al pulsar el botón de ayuda
			this.helpBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
					/* Declaro un objeto Desktop que es una nueva API en JAVA
				     Para mas detalle sobre ésta API ver la siguiente Página web > 
				     http://java.sun.com/developer/technicalArticles/J2SE/Desktop/javase6/desktop_api/
				        */
					File fileAux = new File("file://");
					fileAux.getAbsolutePath();
					Desktop desktop;
					File file = LibraryEditPlugin.INSTANCE.getHtml("/helpAux/Ayuda");
					
				    	if(file.exists()){
					       if (Desktop.isDesktopSupported()){// si éste Host soporta esta API 
					           desktop = Desktop.getDesktop();//obtengo una instancia del Desktop(Escritorio)de mi host 
					           try {
					                desktop.open(file);//abro el archivo con el programa predeterminado
					               }
					            catch (IOException ex) {
					                System.out.println("No se puede abrir el fichero");
					            }
					       }
					       else{ JOptionPane.showMessageDialog(null,"Lo lamento,no se puede abrir el archivo; ésta Maquina no soporta la API Desktop");
					       }
					    }
					}
				}
				
			);
			
			
			//Evento que borra la variación seleccionada en los conflictos
			this.deleteConflictsBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent e) {
					//Se hace el borrado del arbol de conflictos exclusivamente
					if(conflictsTreeSelected != null && conflictsTreeSelected.length > 0){
						for(int i=0; i< conflictsTreeSelected.length; i++){
							
							TreeItem treeItemDependence = conflictsTreeSelected[i];
							
							Boolean found = false;
							Dependences dependenceTreeItem;	
							//Primero obtenemos el tipo de dependencia que estamos seleccionando
							while(!found){
								//Si hemos encontrado la dependencia
								if(treeItemDependence.getData() instanceof Dependences ){
									found = true;
									dependenceTreeItem = (Dependences) treeItemDependence.getData();
									
									if(dependenceTreeItem instanceof varP2varP){
										varP2varP dep = (varP2varP) dependenceTreeItem;
										//Si es exclusivo borramos todas las ocupaciones DEL PUNTO DE VARIACION SUPPLIER que aparezcan en la lista
										if(!dep.isIsInclusive()){
											TailoredProcessLogic.removeVarElementFromList(occupationList,  dep.getSupplier());
										}
										//Si es inclusivo borramos todas las ocupaciones DEL PUNTO DE VARIACION CLIENT que aparezcan en la lista
										else{
											TailoredProcessLogic.removeVarElementFromList(occupationList,  dep.getClient());
										}
									}
									if(dependenceTreeItem instanceof variant2variant){
										variant2variant dep = (variant2variant) dependenceTreeItem;
										//Si es exclusivo -> No puede darse este caso ya que se trata en las variaciones
										if(!dep.isIsInclusive()){
											
										}
										//Si es inclusivo borramos la variacion donde esta variante aparezca como supplier
										else{
											Boolean foundVariant = false;
											for(Iterator iterator = occupationList.iterator(); iterator.hasNext() && !foundVariant;){
												EObject occupationObj = (EObject) iterator.next();
												if(occupationObj instanceof Occupation){
													Occupation occupationTarget = (Occupation) occupationObj;
													if(occupationTarget.getOcupadopor().equals(dep.getClient())){
															iterator.remove();
															foundVariant = true;
													}

												}
											}
										}
									}
									if(dependenceTreeItem instanceof varp2variant){
										varp2variant dep = (varp2variant) dependenceTreeItem;
										//Solo es inclusivo borramos TODAS LAS APARICIONES DEL PUNTO DE VARIACION CLIENTE que aparezcan en la lista
										if(dep.isIsInclusive()){
											TailoredProcessLogic.removeVarElementFromList(occupationList, dep.getClient());
										}
									}
									//En desuso actualmente
									if(dependenceTreeItem instanceof variant2varP){
										
									}

								}
								//Si no hemos encontrado la dependencia continuamos explorando
								else{
									treeItemDependence = treeItemDependence.getParentItem();
								}
							}
						}
						//Por ultimo refrescamos y comprobamos conflictos
						//Refresco el arbol de variaciones
						refreshTreeVariations();
						
						//Refrescamos el arbol de las colisiones de cardinalidad , varp2varp
						conflictsTree.removeAll();
						cardinalityConflictsTree.removeAll();
						if(checkConflicts()){
							okBtn.setEnabled(false); //Deshabilitamos
						}
						else{
							okBtn.setEnabled(true); //Habilitamos
						}	
					}
				}
				
			});
			
			//Evento que borra del arbol de las variaciones realizadas
			this.deleteVariationBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}

				public void widgetSelected(SelectionEvent e) {
					//Si tenemos algun elemento seleccionado
					if(varElementsCompletedTreeSelected != null && varElementsCompletedTreeSelected.length > 0){
						 for(int i=0; i < varElementsCompletedTreeSelected.length; i++){
							 TreeItem treeItemSelected = varElementsCompletedTreeSelected[i];
							 if(treeItemSelected.getData() instanceof VarElement){
								 VarElement varElementSelected = (VarElement) treeItemSelected.getData();
								 
								 //Si es una variante borramos esa Variacion concreta
								 if(varElementSelected instanceof Variant){
									 Variant variantSelected = (Variant) varElementSelected;
									 TreeItem vpFatherItem = treeItemSelected.getParentItem();
									 
									 if(vpFatherItem != null){
										 if(vpFatherItem.getData() instanceof VarPoint){
											VarPoint vpFather = (VarPoint) vpFatherItem.getData();
											deleteVariationFromVP(vpFather, variantSelected);
											//Refrescamos arbol de variaciones
											refreshTreeVariations();
											//Refrescamos arbol de cardinalidad y conflictos
											conflictsTree.removeAll();
											cardinalityConflictsTree.removeAll();
											if(checkConflicts()){
												okBtn.setEnabled(false); //Deshabilitamos
											}
											else{
												okBtn.setEnabled(true); //Habilitamos
											}		 
												
										 }
									 }

								 }
								 //Si es un punto de variación borramos todas las variaciones que tenga ese punto de variación (Operacion critica)
								 else if(varElementSelected instanceof VarPoint){
									 VarPoint varPointSelected = (VarPoint) varElementSelected;
									 
									 if(varPointSelected != null){
										deleteVarPointsVariations(varPointSelected);
										//Refrescamos arbol de variaciones
										refreshTreeVariations();
										//Refrescamos arbol de cardinalidad y conflictos
										conflictsTree.removeAll();
										cardinalityConflictsTree.removeAll();
										if(checkConflicts()){
											okBtn.setEnabled(false); //Deshabilitamos
										}
										else{
											okBtn.setEnabled(true); //Habilitamos
										}	
									 }
									 
								 }
							 }
						 }
					}
					
				}

				
			});
			
			//Evento que controla que elemento se selecciona un elemento del arbol de las variaciones realizadas
			this.variationsCompletedTree.addListener(SWT.Selection, new Listener() {
		    	
		        public void handleEvent(Event event) {
		        		if(variationsCompletedTree.getSelection() != null){
		        			varElementsCompletedTreeSelected = variationsCompletedTree.getSelection();//Lista de objetos seleccionados
		        		}
		        }
		        
		      });
			//Evento que controla que variacion se selecciona dentro del arbol de conflictos
			this.conflictsTree.addListener(SWT.Selection , new Listener() {

				public void handleEvent(Event event) {
					// TODO Auto-generated method stub
					if(conflictsTree.getSelection() != null){
						conflictsTreeSelected = conflictsTree.getSelection();//Lista de objetos seleccionados
					}
				}
				
			});
			//Evento que se dispara cuando seleccionamos un Item en la lista de varPointsPendingList
			this.varPointsPendingList.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {	
				}

				public void widgetSelected(SelectionEvent e) {
				}
				
			});
			
			
			//Evento Boton Add Variation para añadir una variacion nueva (Desde puntos de variación pendientes)
			this.addVariationVarPointsBtn.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {	
				}
				public void widgetSelected(SelectionEvent e) {

						
						if(varPointsPendingList.getSelection().length > 0 ){
							//Si tenemos alguno punto de variación seleccionado lanzamos la ventana
							VarElement varElementSelected = getItemSelected();
							
							Display display = Display.getDefault();
							Shell shell = new Shell(display);
							
							//Obtenemos el punto de variacion seleccionado (El objeto)
							VariationsDialog variationsDialog = new VariationsDialog(shell, SWT.NULL, varElementSelected, auxVariants, occupationList);
							variationsDialog.open();
							
							if(variationsDialog.execute){//No cancelo el usuario guardamos el resultado y refrescamos el arbol
								//Refresco el arbol de variaciones
								refreshTreeVariations();
								
								//Refrescamos el arbol de las colisiones de cardinalidad , varp2varp
								conflictsTree.removeAll();
								cardinalityConflictsTree.removeAll();
								if(checkConflicts()){
									okBtn.setEnabled(false); //Deshabilitamos
								}
								else{
									okBtn.setEnabled(true); //Habilitamos
								}									
							}
						}

						
				}
				
			});
			
			//Evento que guarda los cambios en un proceso adaptado
			this.saveBtn.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					tailoredProcess.setName(nameTxt.getText());
					tailoredProcess.setBriefDescription(descriptionTxt.getText());
					tailoredProcess.getOccupations().addAll(occupationList);
					PersistenceLogic.saveTailoredProcess(tailoredProcess);//Incovacion a la persistencia
				}

				public void widgetSelected(SelectionEvent e) {
					tailoredProcess.setName(nameTxt.getText());
					tailoredProcess.setBriefDescription(descriptionTxt.getText());
					tailoredProcess.getOccupations().addAll(occupationList);
					PersistenceLogic.saveTailoredProcess(tailoredProcess);//Incovacion a la persistencia
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
	 * Devuelve el elemento seleccionado de la lista de varPoints
	 * @return
	 */
	public VarElement getItemSelected(){
		String[] varElementNameSelected = varPointsPendingList.getSelection();
		Boolean found = false;
		VarElement varElement = null;
		
		for(Iterator iterator = auxVarPoints.iterator(); iterator.hasNext() && !found;){
			EObject element = (EObject) iterator.next();
			if(element instanceof VarElement){
				VarElement auxVarElement = (VarElement) element;
				if(varElementNameSelected[0].toString().equals(auxVarElement.getVpName())){
					varElement = auxVarElement;
					found=true;
				}
			}
		}
		return varElement;
	}
	//Metodo que devuelve si una ocupacion esta dentro de una lista de ocupaciones
	public Boolean containsOccupation(Collection occupationList, Occupation target){
		Boolean found = false;
		
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext() && !found;){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationOfList = (Occupation) occupationObj;
				if(occupationOfList.getOcupadopor().equals(target.getOcupadopor()) &&
						occupationOfList.getEsocupado().equals(target.getEsocupado())){
						found = true;
				}	
			}
		}
			
		return found;
		
	}
	/**
	 * Metodo que refresca el arbol de variaciones
	 */
	public void refreshTreeVariations(){
		//Limpio el arbol
		variationsCompletedTree.removeAll();
		//Recomponemos el arbol a partir de la lista actualizada de ocupaciones actuales
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				
				//Extraigo elementos para componer el arbol
				Variant auxVariant = occupationTarget.getOcupadopor();
				VarPoint auxVarPoint = occupationTarget.getEsocupado();
				
				//Compongo el arbol
				//
				
				//Buscamos en el arbol primero (Si esta el punto de variación añadimos la variante ahi)
				Boolean found = false;
				for(int i = 0; i<variationsCompletedTree.getItems().length && !found ;i++){
					//El punto de variacion esta
					if(auxVarPoint.getVpName().equals(variationsCompletedTree.getItems()[i].getText())){
						found = true;
						TreeItem subItemVariant = new TreeItem(variationsCompletedTree.getItems()[i], SWT.NONE);
						subItemVariant.setText(auxVariant.getVpName());
						subItemVariant.setData(auxVariant);
					}
				}
				//Si no lo encontramos añadimos un elemento entero nuevo
				if(!found){
					TreeItem itemVariant = new TreeItem(variationsCompletedTree, SWT.LEFT);
					itemVariant.setText(auxVarPoint.getVpName());
					itemVariant.setData(auxVarPoint);
				    
				    TreeItem subItemVarPoint = new TreeItem(itemVariant, SWT.NONE);
				    subItemVarPoint.setText(auxVariant.getVpName());
				    subItemVarPoint.setData(auxVariant);
					
				}
			}
		}
	}
	/**
	 * Metodo que borra una variacion con una variante de un punto de variacion
	 * @param vpFather
	 * @param variantSelected
	 */
	public void deleteVariationFromVP(VarPoint vpFather, Variant variantSelected){
		Boolean found = false;
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext() && !found;){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				
				if(occupationTarget.getEsocupado().equals(vpFather) && 
						occupationTarget.getOcupadopor().equals(variantSelected)){
						iterator.remove();
						found = true;
				}
			}
		}
	}
	/**
	 * Metodo que borra de un punto de variacion todas las variaciones (ATENCION!) 
	 * FIXME generar ventana de aviso
	 * @param varPointSelected
	 */
	private void deleteVarPointsVariations(VarPoint varPointSelected) {
		
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				
				if(occupationTarget.getEsocupado().equals(varPointSelected)){
						iterator.remove();

				}
			}
		}
	}
	/**
	 * Metodo que comprueba si se cumple todas las dependencias de cardinalidad para los puntos de variación
	 */
	private Boolean checkCardinalityCollisions(){
		Boolean collision = false;
		
		for(Iterator iterator = auxVarPoints.iterator(); iterator.hasNext();){
			EObject varPointObj = (EObject) iterator.next();
			
			if(varPointObj instanceof VarPoint){
				VarPoint varPointTarget = (VarPoint) varPointObj;
				
				int auxMin = varPointTarget.getMin();
				int auxMax = varPointTarget.getMax();
				
				int occupationsCounter = 0;
				
				//Obtengo las ocupciones de ese punto de variación
				Collection occupationsOfVP = TailoredProcessLogic.getOccupationList(occupationList, varPointTarget);
				
				//Minima
				//No cumple la cardinalidad Minima
				if(occupationsOfVP.size() < auxMin){
					System.out.print(cardinalityCollisionMin+varPointTarget.getVpName());
					collision = true;
					treeConstructorCardColli(varPointTarget, cardinalityCollisionMin);
				}
				
				//Maxima
				//Si es N entonces no sera posible hacer conflicto
				if(auxMax == -1){
					
				}
				//No cumple la cardinalidad maxima
				else if(occupationsOfVP.size() > auxMax){
					System.out.print(cardinalityCollisionMax+varPointTarget.getVpName());
					collision = true;
					treeConstructorCardColli(varPointTarget, cardinalityCollisionMax);
				}
			}
		}
		return collision;
	}
	
	/**
	 * Metodo que construye el arbol para una colision de cardinalidad
	 * @param varPointTarget
	 * @param collision 
	 */
	private void treeConstructorCardColli(VarPoint varPointTarget, String collision) {
		
		//raiz con el texto de la colisión
		TreeItem itemCardRootText = new TreeItem(cardinalityConflictsTree, SWT.LEFT);
		itemCardRootText.setText(collision);
		itemCardRootText.setData(collision);
		
			//Punto de variación que provoca la colisión
			TreeItem itemVarPointRootText = new TreeItem(itemCardRootText, SWT.LEFT);
			itemVarPointRootText.setText(varPointTarget.getVpName());
			itemVarPointRootText.setData(varPointTarget);
		
	}

	/**
	 * Metodo que comprueba si hay conflictos entre algun punto de variación que se este usando
	 * @return
	 */
	private Boolean checkVarPoint2VarPointConflict(){
		Boolean foundInclusive = false;
		Boolean foundExclusive = false;
		
		Collection auxClientsTracked = new ArrayList();//Guardamos los elmentos que ya hemos examinado para no repetirlos
		
		//Hay que comprobar en los puntos de variacion utilizados posibles conflictos
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				VarPoint varPointToExamine = occupationTarget.getEsocupado();
				
				//Si no has sido examinado anteriormente
				if(!auxClientsTracked.contains(varPointToExamine)){
					
					//varP2varP Inclusive
					if(checkVarP2VarPInclusive(varPointToExamine)){
						foundInclusive = true;
					}
					//varP2varP Exclusive
					if(checkVarP2VarPExclusive(varPointToExamine)){
						foundExclusive = true;
					}
					
					auxClientsTracked.add(varPointToExamine);
				}	
			}
		}
		
		return (foundInclusive || foundExclusive);
	}
	
	/**
	 * Metodo que comprueba si hay una dependencia del tipo: varP2varP Inclusive
	 * @param occupationTarget
	 * @return
	 */
	private boolean checkVarP2VarPInclusive(VarPoint varPoint) {
		// TODO Auto-generated method stub
		Boolean found = false;	

		Boolean foundVarPoint = false;
		
		VarPoint varPointOrigen = varPoint;
		
		for(Iterator iterator = varPointOrigen.getClient().iterator(); iterator.hasNext();){
			Dependences dependenceTarget = (Dependences) iterator.next();
			
			if(dependenceTarget instanceof varP2varP){
				
				
				varP2varP dep = (varP2varP) dependenceTarget;
				//Tiene una dependencia inclusiva, ahora hay que localizar si esta el elemento
				if(dep.isIsInclusive()){
					
					foundVarPoint = false;
					
					for(Iterator iter = occupationList.iterator(); iter.hasNext() && !foundVarPoint;){
						Occupation occupationTarget = (Occupation) iter.next();
						
						if(dep.getSupplier().equals(occupationTarget.getEsocupado())){
							foundVarPoint = true;//Es en las variantes solucionado
						}
					}
					
					if(!foundVarPoint){
						found = true;
						treeCollisionsConstructor(varP2varPICollision, varPointOrigen, dep.getSupplier(), dep);
					}
				}
			}
		}

		
		return found;
	}
	
	/**
	 * Metodo que comprueba si hay una dependencia del tipo: varP2varP Exclusive
	 * @param occupationTarget
	 * @return
	 */
	private boolean checkVarP2VarPExclusive(VarPoint varPoint) {
		// TODO Auto-generated method stub
		
		Boolean found = false;

		VarPoint varPointOrigen = varPoint;
		
			for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
				EObject occupationObj = (EObject) iterator.next();
				if(occupationObj instanceof Occupation){
					
					Occupation occupationDestino = (Occupation) occupationObj;
					VarPoint varPointDestino = occupationDestino.getEsocupado();
					
					//Que no sean el mismo objeto
					if(!varPointOrigen.equals(varPointDestino)){
						//Si al varPoint Destino como supplier es que hay alguna dependencia
						for(Iterator iter = varPointOrigen.getClient().iterator(); iter.hasNext();){
							EObject depObj = (EObject) iter.next();
							if(depObj instanceof Dependences){
								Dependences dep = (Dependences) depObj;
								
								//Dependencia del tipo varP2varP 
								if(dep instanceof varP2varP){
									varP2varP depVarP2varP = (varP2varP) dep;
									
									//Dependencia del tipo varP2varP Exclusiva
									if(!depVarP2varP.isIsInclusive()){
										if(depVarP2varP.getSupplier().equals(varPointDestino)){
											System.out.print("Dep Exclusividad: "+varPointOrigen.getVpName()+" ----> "
													+varPointDestino.getVpName());
											found = true;
											treeCollisionsConstructor(varP2varPECollision, varPointOrigen, varPointDestino, dep);
										}
									}	
								}
							}
						}
					}
				}
			}
		
		return found;
	}
	/**
	 * Metodo que construye el arbol de colisiones de conflictos conflictsTree
	 * @param dependence
	 * @param Client
	 * @param Supplier
	 * @param dep
	 */
	private void treeCollisionsConstructor(String dependence,
			VarElement client, VarElement supplier, Dependences dep) {
		// TODO Auto-generated method stub
		
		//raiz con el texto de la colisión
		TreeItem itemRootText = new TreeItem(conflictsTree, SWT.LEFT);
		itemRootText.setText(dependence);
		itemRootText.setData(dep);
		
			//Texto Cliente
			TreeItem itemClientRootText = new TreeItem(itemRootText, SWT.NONE);
			itemClientRootText.setText(clientDependence);
			itemClientRootText.setData(clientDependence);
			
				//Object VarElement Cliente
				TreeItem itemClientVarElement = new TreeItem(itemClientRootText, SWT.NONE);
				itemClientVarElement.setText(client.getVpName());
				itemClientVarElement.setData(client);
				
			//Texto Supplier
			TreeItem itemSupplierRootText = new TreeItem(itemRootText, SWT.NONE);
			itemSupplierRootText.setText(supplierDependence);
			itemSupplierRootText.setData(supplierDependence);
			
				//Object VarElement Supplier
				TreeItem itemSupplierVarElement = new TreeItem(itemSupplierRootText, SWT.NONE);
				itemSupplierVarElement.setText(supplier.getVpName());
				itemSupplierVarElement.setData(supplier);
		
	}
	/**
	 * Metodo que comprueba TODOS los conflictos
	 * @return
	 */
	private Boolean checkConflicts(){
		Boolean found = false;
		
		//Cardinalidad (VarPoints)
		if(checkCardinalityCollisions()){
			found = true;
		}
		//Dependencias varPoint2varPoint (Inclusividad & Exclusividad)
		if(checkVarPoint2VarPointConflict()){
			found = true;
		}
		//Dependencias variant2variant (Inclusividad)
		if(checkVariant2VariantConflict()){
			found = true;
		}
		//Dependencias varPoint2variant (Inclusividad)
//		if(checkVarPoint2VariantConflict()){
//			found = true;
//		}
		//Dependencia variant2VarPoint (Inclusividad)
//		if(checkVariant2VarPointConflict()){
//			found = true;
//		}
		return found;
	}
	/**
	 * Metodo que comprueba los conflictos entre variants y varPoints (variant2varP Inclusivo)
	 * @return
	 */
	private boolean checkVariant2VarPointConflict(){
		Boolean found = false;
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				
				Variant variantToExamine = occupationTarget.getOcupadopor();//VARIANTE
				VarPoint varPointToExamine = occupationTarget.getEsocupado();//PUNTO DE VARIACION
				
				for(Iterator iter = variantToExamine.getClient().iterator(); iter.hasNext();){
					
				}
				
			}
		}
		
		
		return found;
	}
	
	
	
	/**
	 * Metodo que comprueba los conflictos entre varPoint y variant (varPoint2variant Inclusivo)
	 * @return
	 */
	private boolean checkVarPoint2VariantConflict(){
		Boolean found = false;
		
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				
				Occupation occupationTarget = (Occupation) occupationObj;
				
				Variant variantToExamine = occupationTarget.getOcupadopor();//VARIANTE
				VarPoint varPointToExamine = occupationTarget.getEsocupado();//PUNTO DE VARIACION
				
				for(Iterator iter = varPointToExamine.getClient().iterator(); iter.hasNext();){
					
					Dependences dependenceTarget = (Dependences) iter.next();
					
					if(dependenceTarget instanceof varp2variant){
						varp2variant dep = (varp2variant) dependenceTarget;
						if(dep.isIsInclusive()){
							Collection variations = TailoredProcessLogic.getOccupationList(occupationList, varPointToExamine);
							Boolean foundVariant = false;
							
							for(Iterator iterVariations = variations.iterator(); iterator.hasNext() && !foundVariant;){
								Occupation occu = (Occupation) iterVariations.next();
								Variant varTarget = occu.getOcupadopor();
								if(dep.getSupplier().equals(varTarget)){
									foundVariant = true;//La hemos encontrado esa dependencia se cumple
								}
							}
							//Si no encontro la variante, entonces añadimos al arbol
							if(!foundVariant){
								found = true;
								treeCollisionsConstructor(varP2variantICollision, varPointToExamine, dep.getSupplier(), dep);
							}
						}
					}
					
				}
			}
		}
		
		return found;
	}
	
	
	/***
	 * Metodo que comprueba los conflictos entre variantes (variant2variant Inclusivo)
	 * @return
	 */
	private boolean checkVariant2VariantConflict() {
		Boolean found = false;
		//Comprobamos en todas las variantes que hay en las ocupaciones
		for (Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			
			EObject occupationObj = (EObject) iterator.next();
			if(occupationObj instanceof Occupation){
				Occupation occupationTarget = (Occupation) occupationObj;
				
				Variant variantToExamine = occupationTarget.getOcupadopor();//VARIANTE
				VarPoint varPointToExamine = occupationTarget.getEsocupado();//PUNTO DE VARIACION
				
				//Comprobamos las dependencias de esa variante si tiene de inclusividad variant2variant
				for(Iterator iter = variantToExamine.getClient().iterator(); iter.hasNext();){
					Dependences dependenceTarget = (Dependences) iter.next();
					//Nos centramos en las de tipo variant2variant inclusivas
					if(dependenceTarget instanceof variant2variant){
						variant2variant dep = (variant2variant) dependenceTarget;
						if(dep.isIsInclusive()){
							//Si hay dependencia inclusiva comprobamos que se cumple, sino añadimos conflicto
							//Recuerdar que esta variante debe estar dentro del mismo punto de variación (no fuera)
							
							//Obtenemos las variaciones de ese punto de variacion
							Collection variations = TailoredProcessLogic.getOccupationList(occupationList, varPointToExamine);
							Boolean foundVariant = false;
							
							for(Iterator iterVariations = variations.iterator(); iterVariations.hasNext() && !foundVariant;){
								Occupation occu = (Occupation) iterVariations.next();
								Variant varTarget = occu.getOcupadopor();
								if(!varTarget.equals(variantToExamine)){
									if(dep.getSupplier().equals(varTarget)){
										foundVariant = true;//La hemos encontrado esa dependencia se cumple
									}
								}
							}
							//Si no encontro la variante, entonces añadimos al arbol
							if(!foundVariant){
								found = true;
								treeCollisionsConstructor(variant2variantICollision, variantToExamine, dep.getSupplier(), dep);
							}
						}
					}
				}
			}
		}
		return found;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
