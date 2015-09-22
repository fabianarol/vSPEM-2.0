package org.eclipse.epf.authoring.ui.vepfdialogs;



import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;

public class SucessfullDialog extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Button okBtn;
	private Label infoLbl;
	private Label iconLbl;
	
	private String message;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			SucessfullDialog inst = new SucessfullDialog(shell, SWT.NULL, "Example");
			inst.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SucessfullDialog(Shell parent, int style, String message) {
		super(parent, style);
		this.message = message;
	}

	public void open() {
		try {
			final Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			
			dialogShell.setText("Message Window");
			dialogShell.setLayout(null);
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setSize(260, 150);
			{
				okBtn = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				okBtn.setBounds(90, 77, 60, 30);
				okBtn.setText("OK");
			}
			{
				iconLbl = new Label(dialogShell, SWT.NONE);
				iconLbl.setBounds(12, 12, 61, 61);
				iconLbl.setImage( LibraryEditPlugin.INSTANCE.getImage("full/others/Done"));
			}
			{
				infoLbl = new Label(dialogShell, SWT.NONE);
				infoLbl.setBounds(68, 21, 176, 33);
				
				Display display = Display.getCurrent();
				FontData[] fD = infoLbl.getFont().getFontData();
				fD[0].setHeight(8);
				fD[0].setStyle(SWT.BOLD);
				infoLbl.setFont( new Font(display,fD[0]));
				infoLbl.setBounds(90, 31, 154, 22);
				infoLbl.setText("Report generated sucesfully");
				
				if(this.message.length() > 0){
					infoLbl.setText(message);
				}
				
			}
			
			this.okBtn.addSelectionListener(new SelectionListener(){

				public void widgetDefaultSelected(SelectionEvent e) {
			    	  System.out.print("Cancel Btn");
			    	  parent.dispose();
				      
					
				}

				public void widgetSelected(SelectionEvent e) {
			    	  System.out.print("Cancel Btn");
			    	  parent.dispose();
				      
					
				}
				
			});
			
			
			
			Monitor primary = Display.getDefault().getPrimaryMonitor();
			Rectangle bounds = primary.getBounds();
			Rectangle rect = dialogShell.getBounds();
			    
			int x = bounds.x + (bounds.width - rect.width) / 2;
			int y = bounds.y + (bounds.height - rect.height) / 2;
			
			dialogShell.setLocation(x,y);
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
