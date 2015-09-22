package org.eclipse.epf.authoring.ui.vepfdialogs;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.Occupation;
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
import org.eclipse.epf.uma.vpActivity;
import org.eclipse.epf.uma.vpIteration;
import org.eclipse.epf.uma.vpMilestone;
import org.eclipse.epf.uma.vpPhase;
import org.eclipse.epf.uma.vpRoleDescriptor;
import org.eclipse.epf.uma.vpTaskDescriptor;
import org.eclipse.epf.uma.vpTeamProfile;
import org.eclipse.epf.uma.vpWorkProductDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;


public class SeeVariationDialog extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Label varPointLbl;
	private Label Variant;
	private Label arrowImg;
	private Label variantImg;
	private Label varPointImg;
	
	private Group descriptionGrp;
	private Label typeLbl;
	private Text typeTxt;
	private Text namevarTxt;
	private Text namevpTxt;
	private Label namevarLbl;
	private Label namevpLbl;
	private Text descriptionTxt;
	private Group informationGrp;
	
	private VarPoint varPointTarget;
	private Variant variantTarget;
	private Occupation occupationTarget;
	
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			SeeVariationDialog inst = new SeeVariationDialog(shell, SWT.NULL, null, null, null);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SeeVariationDialog(Shell parent, int style, VarPoint varPointTarget, Variant variantTarget, Occupation occupationTarget) {
		super(parent, style);
		this.varPointTarget = varPointTarget;
		this.variantTarget = variantTarget;
		this.occupationTarget = occupationTarget;
	}

	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			
			dialogShell.setText("Preview Variation Window");
			dialogShell.setToolTipText("Window for preview a Variation");
			dialogShell.setLayout(null);
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(400, 500);
			{
				varPointLbl = new Label(dialogShell, SWT.NONE);
				varPointLbl.setText("VarPoint");
				varPointLbl.setBounds(44, 53, 60, 16);
			}
			{
				Variant = new Label(dialogShell, SWT.NONE);
				Variant.setText("Variant");
				Variant.setBounds(294, 53, 44, 16);
			}
			{
				varPointImg = new Label(dialogShell, SWT.NONE | SWT.BORDER);
				varPointImg.setBounds(34, 75, 60, 51);
				varPointImg.setSize(70, 70);
			}
			{
				variantImg = new Label(dialogShell, SWT.NONE | SWT.BORDER);
				variantImg.setBounds(282, 75, 60, 51);
				variantImg.setSize(68, 68);
			}
			{
				arrowImg = new Label(dialogShell, SWT.NONE);
				arrowImg.setBounds(162, 75, 70, 70);
			}
			{
				descriptionGrp = new Group(dialogShell, SWT.NONE);
				descriptionGrp.setLayout(null);
				descriptionGrp.setText("Description");
				descriptionGrp.setBounds(34, 315, 308, 135);
				{
					descriptionTxt = new Text(descriptionGrp, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
					descriptionTxt.setBounds(12, 19, 284, 104);
					descriptionTxt.setEnabled(false);
				}
			}
			{
				informationGrp = new Group(dialogShell, SWT.NONE);
				informationGrp.setLayout(null);
				informationGrp.setText("Information");
				informationGrp.setBounds(34, 157, 308, 146);
				{
					namevpLbl = new Label(informationGrp, SWT.NONE);
					namevpLbl.setText("Name");
					namevpLbl.setBounds(12, 21, 60, 18);
				}
				{
					namevarLbl = new Label(informationGrp, SWT.NONE);
					namevarLbl.setText("Name");
					namevarLbl.setBounds(261, 21, 35, 18);
				}
				{
					namevpTxt = new Text(informationGrp, SWT.NONE | SWT.BORDER);
					namevpTxt.setBounds(12, 45, 122, 23);
					namevpTxt.setSize(134, 23);
					namevpTxt.setEnabled(false);
				}
				{
					namevarTxt = new Text(informationGrp, SWT.NONE | SWT.BORDER);
					namevarTxt.setBounds(158, 45, 140, 23);
					namevarTxt.setEnabled(false);
				}
				{
					typeLbl = new Label(informationGrp, SWT.NONE);
					typeLbl.setText("Type");
					typeLbl.setBounds(12, 80, 60, 17);
				}
				{
					typeTxt = new Text(informationGrp, SWT.NONE | SWT.BORDER);
					typeTxt.setBounds(12, 103, 122, 31);
					typeTxt.setSize(116, 23);
					typeTxt.setEnabled(false);
				}
			}	
			{
				//Rellenamos la interfaz
				
				//VarPoint
				String vpName="";
				String vptElement="";
				String vpRealName="";
				
				if(varPointTarget instanceof VarElement){
					VarElement auxVarElement = varPointTarget;
					vpRealName = auxVarElement.getVpName();
					this.namevpTxt.setText(vpRealName);
				}
				
				Image vpIcon = null;
				
				////VPActividad
				if(varPointTarget instanceof vpActivity){//Instancia de VarActivity
					vpName="vpActivity";
					vptElement="Activity";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpactivity");
				}
				////VPPhase
				if(varPointTarget instanceof vpPhase){//Instancia de VarActivity
					vpName="vpPhase";
					vptElement="Phase";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpphase");
				}
				////VPIteration
				if(varPointTarget instanceof vpIteration){//Instancia de VarActivity
					vpName="vpIteration";
					vptElement="Iteration";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpiteration");
				}
				////VPRoleDescriptor
				if(varPointTarget instanceof vpRoleDescriptor){//Instancia de VarActivity
					vpName="vpRoleDescriptor";
					vptElement="RoleDescriptor";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vproledescriptor");
				}
				////VPTaskDescriptor
				if(varPointTarget instanceof vpTaskDescriptor){//Instancia de VarActivity
					vpName="vpTaskDescriptor";
					vptElement="TaskDescriptor";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vptaskdescriptor");
				}
				////VPWorkProductDescriptor
				if(varPointTarget instanceof vpWorkProductDescriptor){
					vpName="vpWorkProduct Descriptor";
					vptElement="WorkProduct Descriptor";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpworkproductdescriptor");
				}
				////VPTeamProfile
				if(varPointTarget instanceof vpTeamProfile){
					vpName="vpTeamProfile";
					vptElement="TeamProfile";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpteamprofile");
				}
				////VPMilestone
				if(varPointTarget instanceof vpMilestone){
					vpName="vpMilestone";
					vptElement="Milestone";
					vpIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpmilestone");
				}
				
				//Variant
				String varName="";
				String varElement="";
				String varRealName="";
				Image varIcon = null;
				
				if(variantTarget instanceof VarElement){
					VarElement auxVarElement = variantTarget;
					varRealName = auxVarElement.getVpName();
					this.namevarTxt.setText(varRealName);
				}
				
				
				////VarActivity
				if(variantTarget instanceof VarActivity){//Instancia de VarActivity
					varName="VarActivity";
					varElement="Activity";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vactivity");//Icon
				}
				////Phase
				if(variantTarget instanceof VarPhase){//Instancia de VarPhase
					varName="VarPhase";
					varElement="Phase";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/varphase");//Icon
				}
				////Iteration
				if(variantTarget instanceof VarIteration){//Instancia de VarIteration
					varName="VarIteration";
					varElement="Iteration";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/variteration");//Icon
				}
				////RoleDescriptor
				if(variantTarget instanceof VarRoleDescriptor){//Instancia de VarRoleDescriptor
					varName="VarRoleDescriptor";
					varElement="Role Descriptor";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/varroledescriptor");//Icon
				}
				////TaskDescriptor
				if(variantTarget instanceof VarTaskDescriptor){//Instancia de VarTaskDescriptor
					varName="VarTaskDescriptor";
					varElement="Task Descriptor";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vartaskdescriptor");//Icon
				}
				
				////VarWorkProductDescriptor
				if(variantTarget instanceof VarWorkProductDescriptor){
					varName="VarWorkProductDescriptor";
					varElement="WorkProduct Descriptor";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vworkproductdescriptor");//Icon
				}
				////VarTeamProfile
				if(variantTarget instanceof VarTeamProfile){
					varName="VarTeamProfile";
					varElement="Team Profile";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vteamprofile");//Icon
				}
				
				////VarMilestone
				if(variantTarget instanceof VarMilestone){
					varName="VarMilestone";
					varElement="Milestone";
					varIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vmilestone");//Icon
				}
				
				this.variantImg.setImage(varIcon);
				this.varPointImg.setImage(vpIcon);
				this.arrowImg.setImage(LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/arrows"));
				
				//Descripcion
				if(occupationTarget.getDescription() !=null)
					this.descriptionTxt.setText(occupationTarget.getDescription());
				
				//Cardinalidad (Type)
				if(varPointTarget.getMin() == 1 && varPointTarget.getMax() == 1){
					this.typeTxt.setText("Obligatory");
				}
				else if (varPointTarget.getMin() == 0){
					this.typeTxt.setText("Optative");
				}
				else if (varPointTarget.getMax() == -1){
					this.typeTxt.setText("Alternative");
				}
				
				
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
}
