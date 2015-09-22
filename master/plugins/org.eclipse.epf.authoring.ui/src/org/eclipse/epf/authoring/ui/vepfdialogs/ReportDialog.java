package org.eclipse.epf.authoring.ui.vepfdialogs;




import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import java.awt.FileDialog;
//import java.awt.Frame;

import javax.swing.JFileChooser;


import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperExportManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.JasperReport; 
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.swt.graphics.Color;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;


import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
 
import org.eclipse.vepf.authoring.ui.jasperbeans.VariationThread;
import org.eclipse.vepf.authoring.ui.jasperbeans.variationBean;

public class ReportDialog extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Button okBtn;
	private Button pathButton;
	private Text nameTxt;
	private Label nameLbl;
	private Combo formatCombo;
	private Label formatLbl;
	private Label errorLbl;
	private Button cancelBtn;
	private Label iconLbl;
	private ProgressBar progressBar;
	
	private JFileChooser fileChooser = new JFileChooser();
	
	private Boolean execute;
	private TailoredProcessComponent tailoredComponent;
	
	private String nameOfFile;
	private File fileToSave;
	private String path;
	
	/*Hilos*/
	private Object result;
    private Shell shell;
    private Display display = null;
    protected volatile boolean isClosed = false;//closed state
    
    /*ProgressBar*/
    protected int executeTime = 1;//process times
    
	/*Constantes*/
	private static String errorNameFile = "You should specify a correct name";
	private static String fileBrowserTitle = "Report file browser";
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			ReportDialog inst = new ReportDialog(shell, SWT.NULL, null);
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReportDialog(Shell parent, int style, TailoredProcessComponent tailoredComponent) {
		super(parent, style);
		this.tailoredComponent = tailoredComponent;
	}

	public void open() {
		try {
			final Shell parent = getParent();
			
			shell = parent;
			display = getParent().getDisplay();
			
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

			dialogShell.setText("Report Generator Window");
			dialogShell.setToolTipText("Generate a report in differents formats");
			dialogShell.setLayout(null);
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(400, 200);
			
			{
				okBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				okBtn.setText("OK");
				okBtn.setBounds(146, 120, 60, 30);
			}
			{
				cancelBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				cancelBtn.setText("Cancel");
				cancelBtn.setBounds(212, 120, 60, 30);
			}
			{
				formatLbl = new Label(dialogShell, SWT.NONE);
				formatLbl.setText("Format");
				formatLbl.setBounds(79, 21, 44, 17);
			}
			{
				formatCombo = new Combo(dialogShell, SWT.NONE | SWT.READ_ONLY);
				formatCombo.setBounds(129, 15, 124, 23);
				formatCombo.setDragDetect(false);

				formatCombo.add(".pdf");
				formatCombo.select(0);
				
			}
			{
				nameLbl = new Label(dialogShell, SWT.NONE);
				nameLbl.setText("Path");
				nameLbl.setBounds(79, 62, 37, 15);
			}
			{
				nameTxt = new Text(dialogShell, SWT.NONE | SWT.BORDER);
				nameTxt.setBounds(128, 62, 203, 20);
				nameTxt.setEnabled(false);
			}
			{
				errorLbl = new Label(dialogShell, SWT.NONE);
				errorLbl.setBounds(128, 85, 199, 38);
				
//				Display display = Display.getCurrent();
				Color red = display.getSystemColor(SWT.COLOR_RED);
				errorLbl.setForeground(red);

			}
			{
				iconLbl = new Label(dialogShell, SWT.NONE);
				iconLbl.setBounds(12, 15, 55, 61);
				iconLbl.setSize(61, 61);//61x61
				iconLbl.setImage( LibraryEditPlugin.INSTANCE.getImage("full/others/ReportWindow"));
			}
			{
				pathButton = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				pathButton.setText("...");
				pathButton.setBounds(337, 62, 25, 18);
			}
//			{
//				progressBar = new ProgressBar(dialogShell, SWT.NONE);
//				progressBar.setBounds(24, 120, 110, 30);
//			}
			
			
			//Boton Ok
			this.okBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent e) {
					
					
					if(nameTxt.getText() != null && nameTxt.getText().length() > 1){
						//FIXME Poner interfaz de espera
//						String path = nameTxt.getText();
						File newFile = new File(nameTxt.getText());
//						VariationThread varThread = new VariationThread(tailoredComponent, fileToSave, formatCombo.getText());
						VariationThread varThread = new VariationThread(tailoredComponent, newFile, formatCombo.getText());
						varThread.run();
						//Fin del hilo
				    	parent.dispose();
					    execute=varThread.getExecute();
					}
					else{
						errorLbl.setText(errorNameFile);
						execute=false;
					}

							
				}
				
			});
			this.cancelBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
			    	  System.out.print("Cancel Btn");
			    	  parent.dispose();
				      execute=false;
					
				}

				public void widgetSelected(SelectionEvent e) {
			    	  System.out.print("Cancel Btn");
			    	  parent.dispose();
				      execute=false;
					
				}
				
			});
			
			this.pathButton.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
				}

				public void widgetSelected(SelectionEvent e) {
					
//					JFileChooser chooser = new JFileChooser();
//					int retval = chooser.showSaveDialog(null);
//					
//					
//		            if (retval == JFileChooser.APPROVE_OPTION) {
//		                //Archivo seleccionado
//		            	fileToSave = chooser.getSelectedFile();
//		                //Path
//		                nameTxt.setText(fileToSave.getAbsolutePath());
//		            }
		            
		            FileDialog chooser = new FileDialog(dialogShell, SWT.SAVE);
		            chooser.setFilterPath(".\\");
		            String path = chooser.open();

		            
		            
		            if( path != null){
		            	nameTxt.setText(path);
		            }
		            
				}
				
			});
			
			
			
			
			Monitor primary = Display.getDefault().getPrimaryMonitor();
			Rectangle bounds = primary.getBounds();
			Rectangle rect = dialogShell.getBounds();
			    
			int x = bounds.x + (bounds.width - rect.width) / 2;
			int y = bounds.y + (bounds.height - rect.height) / 2;
			    		
			dialogShell.setLocation(x, y);
			
			dialogShell.open();
//			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public Boolean getExecute(){
		return execute;
	}
	
	class ProcessThread extends Thread {
        private int max = 0;
        private volatile boolean shouldStop = false;

        ProcessThread(int max) {
            this.max = max;
        }

        public void run() {
          doBefore();
            for (final int[] i = new int[] {1}; i[0] <= max; i[0]++) 
            {
                if (display.isDisposed()) {
                    return;
                }
                display.syncExec(new Runnable() {
                    public void run() {
                        if (progressBar.isDisposed()) {
                            return;
                        }
                        progressBar.setSelection(i[0]);
                        //
                        if (i[0] == max || isClosed) {
                            if (isClosed) {
                                shouldStop = true;//
                                cleanUp();//
                            }
                            shell.close();//
                        }
                    }
                });

                if (shouldStop) {
                    break;
                }
            }
            doAfter();
        }
    }
	
    protected void cleanUp()
    {
      
    }
    

    protected void doBefore()
    {
      
    }
    
    protected void doAfter()
    {
      
    }
	
}
