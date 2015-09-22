//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.serviceability.MsgBox;
import org.eclipse.epf.common.serviceability.MsgDialog;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Helper utilities for implementing method library UI.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryUIUtil {

	/**
	 * Updates the application shell title to display the application name and
	 * the default method library path.
	 */
	public static void updateShellTitle() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			Shell shell = window.getShell();
			if (shell != null) {
				String libPath = ""; //$NON-NLS-1$
				if (LibraryService.getInstance().getCurrentMethodLibrary() != null) {
					libPath = LibraryServiceUtil.getCurrentMethodLibraryPath();
					if (libPath == null) {
						libPath = LibraryService.getInstance()
								.getCurrentMethodLibrary().getName();
					}
				}
				String appName = Platform.getProduct().getName();
				String appTitle = NLS.bind(
						LibraryUIResources.application_title, new Object[] {
								appName, libPath });
				shell.setText(appTitle);
			}
		}
	}

	/**
	 * Displays a dialog to prompts the user to save the open library if it has
	 * been modified.
	 * 
	 * @return <code>SWT.YES</code>, <code>SWT.NO</code> or
	 *         <code>SWT.CANCEL</code>
	 */
	public static int displaySaveDirtyEditorsDialog() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryModified()) {
			int ret = MsgBox.prompt(LibraryUIResources.saveLibraryDialog_title,
					LibraryUIResources.saveLibraryDialog_text, SWT.YES | SWT.NO
							| SWT.CANCEL);
			switch (ret) {
			case SWT.YES:
				try {
					LibraryService.getInstance().saveCurrentMethodLibrary();
				} catch (Exception e) {
					MsgDialog dialog = LibraryUIPlugin.getDefault()
							.getMsgDialog();
					dialog.displayError(
							LibraryUIResources.saveLibraryDialog_title,
							LibraryUIResources.saveLibraryError_msg,
							LibraryUIResources.error_reason, e);
				}
				return SWT.YES;
			case SWT.NO:
				// Discard all changes by resetting all resources as unchanged.
				manager.discardMethodLibraryChanges();
				return SWT.NO;
			case SWT.CANCEL:
				return SWT.CANCEL;
			}
		}

		return SWT.CANCEL;
	}

}
