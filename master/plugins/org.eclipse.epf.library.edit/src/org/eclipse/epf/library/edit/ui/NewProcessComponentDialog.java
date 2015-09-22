//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.ProcessPlanningTemplate;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * Displays the New Process dialog.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class NewProcessComponentDialog extends Dialog {

	private Composite area;

	private Text ctrl_name;

	private ComboViewer configComboViewer;

	private MethodConfiguration[] availableConfigurations;

	private ProcessComponent newProcComp;

	private Process[] availableBaseProcesses;

	private CheckboxTableViewer processTableViewer;

	// private ProcessPackage owner;

	private static final Collection ECLASS = new HashSet();

	private IValidator validator;

	static {
		ECLASS.add(UmaPackage.eINSTANCE.getProcessComponent());
	}

	/**
	 * Creates a new instance.
	 */
	public NewProcessComponentDialog(Shell parent,
			MethodConfiguration[] availableConfigurations,
			ProcessComponent newComp, Process[] availableBaseProcesses,
			MethodPlugin currentPlugin, ProcessPackage owner) {
		super(parent);
		this.availableConfigurations = availableConfigurations;
		this.newProcComp = newComp;
		this.availableBaseProcesses = availableBaseProcesses;
		// this.owner = owner;
		validator = IValidatorFactory.INSTANCE.createNameValidator(owner,
				newProcComp);
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(Composite parent)
	 */
	protected Control createDialogArea(Composite parent) {
		area = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		area.setLayout(gridLayout);
		GridData areaGridData = new GridData();
//		areaGridData.widthHint = 300;
		area.setLayoutData(areaGridData);		
		
		// Name
		Label nameLabel = new Label(area, SWT.NONE);
		nameLabel.setText(LibraryEditResources.nameLabel_text); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			nameLabel.setLayoutData(gridData);
		}

		ctrl_name = new Text(area, SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.BEGINNING
					| GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 2;
			gridData.widthHint = 200;
			ctrl_name.setLayoutData(gridData);
		}

		// Ref. Models
		Label refModelLabel = new Label(area, SWT.NONE);
		refModelLabel.setText(LibraryEditResources.defaultConfigLabel_text); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			refModelLabel.setLayoutData(gridData);
		}

		Composite comp = new Composite(area, SWT.NONE);
		{
			GridData gridData = new GridData(GridData.BEGINNING
					| GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 2;
			comp.setLayoutData(gridData);
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			comp.setLayout(layout);
		}

		configComboViewer = new ComboViewer(comp, SWT.BORDER | SWT.READ_ONLY);
		{
			GridData gridData = new GridData(GridData.BEGINNING | GridData.FILL_HORIZONTAL);
			gridData.widthHint = 200;
			configComboViewer.getCombo().setLayoutData(gridData);
		}
		

		ILabelProvider labelProvider = new LabelProvider() {
			public String getText(Object element) {
				if (element instanceof MethodElement) {
					return ((MethodElement) element).getName();
				} else {
					return element.toString();
				}
			}
		};

		// configListViewer = new ListViewer(comp, SWT.BORDER | SWT.SINGLE);
		// configListViewer.setLabelProvider(labelProvider);
		// configListViewer.add(availableConfigurations);
		// if(newProcComp.getProcess().getDefaultContext() != null) {
		// configListViewer.setSelection(new
		// StructuredSelection(newProcComp.getProcess().getDefaultContext()));
		// }

		configComboViewer.setLabelProvider(labelProvider);
		configComboViewer.add(availableConfigurations);
		if (newProcComp.getProcess().getDefaultContext() != null) {
			configComboViewer.setSelection(new StructuredSelection(newProcComp
					.getProcess().getDefaultContext()));
		}
		// configComboViewer.getControl().setSize(100,
		// configComboViewer.getControl().getSize().y);

		if (newProcComp.getProcess() instanceof ProcessPlanningTemplate) {
			// based on processes
			//
			Label label = new Label(area, SWT.NONE);
			label.setText(LibraryEditResources.basedOnProcessesLabel_text); 
			{
				GridData gridData = new GridData(GridData.BEGINNING);
				label.setLayoutData(gridData);
			}

			comp = new Composite(area, SWT.NONE);
			{
				GridData gridData = new GridData(GridData.BEGINNING
						| GridData.FILL_HORIZONTAL);
				gridData.horizontalSpan = 2;
				comp.setLayoutData(gridData);
				GridLayout layout = new GridLayout();
				layout.marginWidth = 0;
				layout.marginHeight = 0;
				comp.setLayout(layout);
			}

			processTableViewer = CheckboxTableViewer.newCheckList(comp,
					SWT.BORDER);
			processTableViewer.getTable()
					.setLayoutData(
							new GridData(GridData.BEGINNING
									| GridData.FILL_HORIZONTAL));
			AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory();
			processTableViewer
					.setContentProvider(new AdapterFactoryContentProvider(
							adapterFactory));
			processTableViewer
					.setLabelProvider(new AdapterFactoryLabelProvider(
							adapterFactory));
			processTableViewer.add(availableBaseProcesses);
		}

		if (newProcComp.getName() != null)
			ctrl_name.setText(newProcComp.getName());

		super
				.getShell()
				.setText(
						LibraryEditResources.newProcessComponentDialog_title); 

		// addListeners();

		return area;
	}

	// protected void addListeners() {
	// // DON'T use FocusLost listener because otherwise it goes infinite loop
	// // and user cannot cancel out of it.
	// ctrl_name.addTraverseListener(new TraverseListener() {
	// public void keyTraversed(TraverseEvent e) {
	// if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail ==
	// SWT.TRAVERSE_TAB_PREVIOUS) {
	// String name = ctrl_name.getText();
	// String msg = validator.isValid(name);
	// if (msg != null) {
	// LibraryEditPlugin.getDefault().getMsgDialog().displayError(
	// LibraryEditResources.getString("LibraryEdit.createProcess.text"),
	// //$NON-NLS-1$
	// msg);
	//						
	// ctrl_name.getDisplay().asyncExec(new Runnable() {
	// public void run () {
	// ctrl_name.setFocus();
	// }
	// });
	// ctrl_name.selectAll();
	// }
	// }
	// }
	// });
	// }

	/**
	 * Creates the dialog buttons.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Create the OK button.
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);

		// Create the Cancel button.
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		// Set help context for the OK button.
		super.getButton(IDialogConstants.OK_ID);

		// Set help context for the Cancel button.
		super.getButton(IDialogConstants.CANCEL_ID);
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		// Need to do validation again since TraverseListener only listens to
		// tab out of field
		// and doesn't take care of mouse clicks
		String name = ctrl_name.getText().trim();
		name = StrUtil.makeValidFileName(name);
		String msg = validator.isValid(name);
		if (msg != null) {
			LibraryEditPlugin.getDefault().getMsgDialog().displayError(
					LibraryEditResources.createProcess_text, 
					msg);
			ctrl_name.setFocus();
			ctrl_name.selectAll();
			return;
		}

		IStructuredSelection selected = (IStructuredSelection) configComboViewer
				.getSelection();
		if (selected.isEmpty()) {
			LibraryEditPlugin.getDefault().getMsgDialog().displayError(
					LibraryEditResources.createProcess_text, 
					LibraryEditResources.noDefaultConfigError_msg); 
			return;
		}

		newProcComp.setName(name);
		newProcComp.getProcess().setName(name);
		newProcComp.getProcess().setPresentationName(name);
		newProcComp.getProcess().setDefaultContext(
				(MethodConfiguration) ((IStructuredSelection) configComboViewer
						.getSelection()).getFirstElement());
		if (newProcComp.getProcess() instanceof ProcessPlanningTemplate) {
			ProcessPlanningTemplate procPlanningTempl = (ProcessPlanningTemplate) newProcComp
					.getProcess();
			procPlanningTempl.getBasedOnProcesses().addAll(
					Arrays.asList(processTableViewer.getCheckedElements()));
		}
		super.okPressed();
	}

}
