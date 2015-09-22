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

import java.util.List;

import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


/**
 * UI Dialog class which will ask user to assign a wp to deliverable
 * automatically
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class DeliverableSelection {

	/**
	 * It shows dialog box all deliverable. Returns deliverable user selected
	 * 
	 * @param element
	 * @return
	 */
//	public static WorkProductDescriptor getSelectedDeliverable(
//			List deliverableList, WorkProduct wp) {
//		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
//				TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory()) {
//			public String getText(Object obj) {
//				if (obj instanceof WorkProductDescriptor) {
//					return ((WorkProductDescriptor) obj).getName();
//				}
//				return ""; //$NON-NLS-1$
//			}
//		};
//
//		ElementListSelectionDialog dlg = new ElementListSelectionDialog(null,
//				labelProvider);
//
//		dlg.setTitle(LibraryEditResources.deliverables_text); //$NON-NLS-1$
//		dlg.setBlockOnOpen(true);
//		dlg.setElements(deliverableList.toArray());
//		dlg.setMultipleSelection(false);
//		dlg.setMessage(NLS.bind(LibraryEditResources.selectDeliverablesDialog_text, wp.getName())); 
//		dlg.setTitle(LibraryEditResources.selectDeliverablesDialog_title); //$NON-NLS-1$
//		dlg.setFilter(null);
//		dlg.open();
//
//		Object obj = dlg.getFirstResult();
//
//		// dispose
//		labelProvider.dispose();
//		return (WorkProductDescriptor) obj;
//	}

}
