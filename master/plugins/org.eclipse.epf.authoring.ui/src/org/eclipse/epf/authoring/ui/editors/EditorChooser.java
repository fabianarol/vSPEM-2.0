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
package org.eclipse.epf.authoring.ui.editors;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.authoring.ui.vepfdialogs.TailoredProcessDialog;
import org.eclipse.epf.authoring.ui.vepfdialogs.VariantDialog;
import org.eclipse.epf.authoring.ui.vepfdialogs.VarPointDialog;
import org.eclipse.epf.common.serviceability.MsgDialog;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.ui.editors.IMethodEditor;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessLineComponent;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VarActivity;
import org.eclipse.epf.uma.VarIteration;
import org.eclipse.epf.uma.VarMilestone;
import org.eclipse.epf.uma.VarPhase;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.VarPointsPackage;
import org.eclipse.epf.uma.VarRoleDescriptor;
import org.eclipse.epf.uma.VarTaskDescriptor;
import org.eclipse.epf.uma.VarTeamProfile;
import org.eclipse.epf.uma.VarWorkProductDescriptor;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariantsPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
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
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Launches a Method editor that is appropriate for a given method element.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class EditorChooser implements IEditorKeeper {

	private static EditorChooser singleton = null;

	/**
	 * Returns a singleton instance
	 * 
	 */
	public static EditorChooser getInstance() {
		if (singleton == null) {
			synchronized (EditorChooser.class) {
				if (singleton == null) {
					singleton = new EditorChooser();
					IEditorKeeper.REFERENCE.setEditorKeeper(singleton);
				}
			}
		}
		return singleton;
	}

	public EditorChooser() {
		
	}
	
	
	/**
	 * Opens the respective editor depending on the given Method element object.
	 * 
	 * @param obj
	 *            A Method element.
	 */
	public void openEditor(Object obj) {
		try {
			obj = ViewHelper.handleDangling(obj);
			if (obj == null)
				return;

			obj = LibraryUtil.unwrap(obj);

			ArrayList errors = new ArrayList();
			if (obj instanceof EObject) {
				Resource res = ((EObject) obj).eResource();
				if (res != null && !res.getErrors().isEmpty()) {
					errors.addAll(res.getErrors());
				}
				if (obj instanceof DescribableElement) {
					Resource presRes = ((DescribableElement) obj)
							.getPresentation().eResource();
					if (presRes != null && !presRes.getErrors().isEmpty()) {
						errors.addAll(presRes.getErrors());
					}
				}
				if (!errors.isEmpty()) {
					final MultiStatus multiStatus = new MultiStatus(
							AuthoringUIPlugin.getDefault().getId(),
							0,
							AuthoringUIResources.EditorChooser_ResourcesError, null); 
					for (Iterator iter = errors.iterator(); iter.hasNext();) {
						Diagnostic e = (Diagnostic) iter.next();
						IStatus status = new Status(IStatus.WARNING,
								LibraryPlugin.getDefault().getId(), 0, e
										.getMessage(), null);
						multiStatus.add(status);
					}
					try {
						if (AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayConfirmation(
										AuthoringUIResources.EditorChooser_loaddErrorDlgTitle,
										AuthoringUIResources.EditorChooser_ResourcesError, 
										multiStatus) == Dialog.CANCEL) {
							return;
						}

					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					}
				}
			}

			EditorOpener opener = EditorOpenerFactory.getInstance().getOpener(obj);
			if ( opener != null && opener.canOpen(obj) ) {
				opener.openEditor(obj);
			} else if ((obj instanceof MethodPlugin)
					|| (obj instanceof ContentPackage) || (obj instanceof Role)
					|| (obj instanceof Task) || (obj instanceof WorkProduct)
					|| (obj instanceof Guidance) || (obj instanceof Discipline)
					|| (obj instanceof DisciplineGrouping)
					|| (obj instanceof Domain)
					|| (obj instanceof WorkProductType)
					|| (obj instanceof RoleSet) || (obj instanceof Tool)
					|| (obj instanceof RoleSetGrouping)
					|| (obj instanceof MethodLibrary)) {
				openEditor((MethodElement) obj, MethodElementEditor.EDITOR_ID);
			} else if (obj instanceof CustomCategory) {
				CustomCategory custCat = (CustomCategory) obj;
				if(TngUtil.isRootCustomCategory(custCat)) {
					return;
				}
				openEditor((MethodElement) obj, MethodElementEditor.EDITOR_ID);
			} else if (obj instanceof ProcessComponent) {
				openEditor((MethodElement) obj, ProcessEditor.EDITOR_ID);

				// open properties view by default when we open Process Editor
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().showView(
									"org.eclipse.ui.views.PropertySheet", null, IWorkbenchPage.VIEW_VISIBLE); //$NON-NLS-1$
				} catch (PartInitException exception) {
					AuthoringUIPlugin.getDefault().getLogger().logError(
							exception);
				}
			} else if (obj instanceof Process) {
				Object container = ((Process) obj).eContainer();
				if (container instanceof ProcessComponent) {
					openEditor((MethodElement) container,
							ProcessEditor.EDITOR_ID);
				}
			}else if (obj instanceof MethodConfiguration) {
				openEditor((MethodConfiguration) obj,
						ConfigurationEditor.EDITOR_ID);
			}else if(obj instanceof BreakdownElement){
				Process process = TngUtil.getOwningProcess((BreakdownElement)obj);
				Object container = process.eContainer();
				if (container instanceof ProcessComponent) {
					openEditor((MethodElement) container,
							ProcessEditor.EDITOR_ID);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			String title = AuthoringUIResources.editors_EditorChooser_systemErrorDialog_title; 
			String message = AuthoringUIResources.editors_EditorChooser_systemErrorDialog_message; 
			String reason = AuthoringUIResources.editors_EditorChooser_systemErrorDialog_reason; 
			MsgDialog dialog = AuthoringUIPlugin.getDefault().getMsgDialog();
			dialog.displayError(title, message, reason, t);
		}
	}

	/**
	 * Opens the Configuration editor.
	 * 
	 * @param config
	 * @param editorId
	 */
	private void openEditor(MethodConfiguration config, String editorId)
			throws PartInitException {
		ConfigurationEditorInput editorInput = new ConfigurationEditorInput(
				config);
		AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().openEditor(
						editorInput, editorId);
	}

	/**
	 * Opens the Method editor for the given method element.
	 * 
	 * @param element
	 *            A Method element.
	 * @param editorId
	 *            The editor ID.
	 */
	private void openEditor(MethodElement element, String editorId)
			throws PartInitException {
		MethodElementEditorInput editorInput = new MethodElementEditorInput(
				element);
		AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().openEditor(
						editorInput, editorId);
	}

	/**
	 * Selects (brings to front, but does not give focus) the Method editor for
	 * the given object. If there is no open editor for the object, does
	 * nothing.
	 * 
	 * @param obj
	 */
	public void selectEditor(Object obj) {
		try {
			obj = ViewHelper.handleDangling(obj);
			if (obj == null)
				return;
			obj = LibraryUtil.unwrap(obj);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		IEditorInput editorInput = null;
		if (obj instanceof MethodConfiguration)
			editorInput = new ConfigurationEditorInput(
					(MethodConfiguration) obj);
		else if (obj instanceof MethodElement)
			editorInput = new MethodElementEditorInput((MethodElement) obj);
		if (editorInput != null) {
			IWorkbenchPage page = AuthoringUIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IEditorPart editorPart = page.findEditor(editorInput);
			if (editorPart != null)
				page.bringToTop(editorPart);
		}
	}

	/**
	 * 
	 * Returns selected editor input
	 * @return The object which the currently selected Method editor is editing (or
	 *         null if there is no editor or if the editor is not a Method editor).
	 */
	public Object getActiveMethodEditorInput() {
		IEditorPart editorPart = AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		return getMethodEditorInput(editorPart);
	}

	public Object getMethodEditorInput(IEditorPart editorPart) {
		if (editorPart != null) {
			IEditorInput editorInput = editorPart.getEditorInput();
			if (editorInput instanceof ConfigurationEditorInput)
				return ((ConfigurationEditorInput) editorInput)
						.getConfiguration();
			if (editorInput instanceof MethodElementEditorInput)
				return ((MethodElementEditorInput) editorInput)
						.getMethodElement();
		}
		return null;
	}

	/**
	 * Closes the Method editor for the given Method element.
	 * 
	 * @param obj
	 *            A Method element.
	 */
	public void closeEditor(Object obj) {
		try {
			IEditorPart editor = findEditor(obj);
			if (editor != null) {
				AuthoringUIPlugin.getDefault().getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.closeEditor(editor, false);
			}
		} catch (NullPointerException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public IEditorPart findEditor(Object obj) {
		try {
			MethodElementEditorInput editorInput = new MethodElementEditorInput(
					(MethodElement) obj);
			IEditorPart editor = AuthoringUIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().findEditor(
							editorInput);
			return editor;
		} catch (NullPointerException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	/**
	 * Returns list of dirty editors for the given elements
	 * @param elements
	 * @return	List of dirty editor
	 */
	public Collection getElementsWithDirtyEditor(Collection elements) {
		ArrayList result = new ArrayList();

		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		// ArrayList closeEditorRefs = new ArrayList();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null && editor.isDirty()) {
				IEditorInput input = editor.getEditorInput();
				MethodElement element = null;
				if (input instanceof MethodElementEditorInput) {
					element = ((MethodElementEditorInput) input)
							.getMethodElement();
				} else if (input instanceof ConfigurationEditorInput) {
					element = ((ConfigurationEditorInput) input)
							.getConfiguration();
				}
				if (element != null) {
					for (Iterator iter = elements.iterator(); iter.hasNext();) {
						Object e = iter.next();
						if (e == element || UmaUtil.isContainedBy(element, e)) {
							result.add(element);
							iter.remove();
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Closes the all open Method editors ofor the given (deleted) element and
	 * all of its children and grand children.
	 * 
	 * @param e
	 *            the deleted MethodElement
	 */
	public void closeEditorsOnDeletion(Object e) {
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		ArrayList closeEditorRefs = new ArrayList();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				MethodElement element = null;
				if (input instanceof MethodElementEditorInput) {
					element = ((MethodElementEditorInput) input)
							.getMethodElement();
				} else if (input instanceof ConfigurationEditorInput) {
					element = ((ConfigurationEditorInput) input)
							.getConfiguration();
				}
				if (element != null
						&& (element.eContainer() == null || UmaUtil
								.isContainedBy(element, e))) {
					closeEditorRefs.add(reference);
				}
			}
		}
		int size = closeEditorRefs.size();
		IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			references[i] = (IEditorReference) closeEditorRefs.get(i);
		}
		workbenchPage.closeEditors(references, false);
	}
	
	/**
	 * Closes all Method Editors without saving.
	 *
	 */
	public void closeAllMethodEditors() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {
					IWorkbenchPage workbenchPage = window.getActivePage();
					List<IEditorReference> closeEditorRefs = getOpenMethodEditors();
					workbenchPage.closeEditors(closeEditorRefs.toArray(new IEditorReference[closeEditorRefs.size()]), false);
				}
			}
		});
	}
	
	/**
	 * @return a list of all open Method Editors
	 */
	public List<IEditorReference> getOpenMethodEditors() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final List<IEditorReference> methodEditorRefs = new ArrayList<IEditorReference>();
		workbench.getDisplay().syncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {
					IWorkbenchPage workbenchPage = window.getActivePage();
					IEditorReference[] editorReferences = workbenchPage.getEditorReferences();
					for (int i = 0; i < editorReferences.length; i++) {
						IEditorReference reference = editorReferences[i];
						IEditorPart editorPart = reference.getEditor(false);
						String editorId = reference.getId();
						if (editorId.startsWith("org.eclipse.epf") || //$NON-NLS-1$
								editorPart instanceof IMethodEditor) {
							methodEditorRefs.add(reference);
						}
					}
				}
			}
		});
		return methodEditorRefs;
	}

	/**
	 * Close all MethodEditors associated with the given Plugin.
	 * Will cause a user-prompt for each dirty editor
	 * 
	 */
	public void closeMethodEditorsForPluginElements(MethodPlugin methodplugin) {
		if (AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow() != null
				&& AuthoringUIPlugin.getDefault().getWorkbench()
						.getActiveWorkbenchWindow().getActivePage() != null) {
			IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			List<IEditorReference> list = getOpenMethodEditors();
			List<IEditorReference> editorRefsToClose = new ArrayList<IEditorReference>();
			for (IEditorReference editorReference : list) {
				IEditorPart editor = editorReference.getEditor(true);
				if (editor != null) {
					IEditorInput input = editor.getEditorInput();
					MethodElement element = null;
					if (input instanceof MethodElementEditorInput) {
						element = ((MethodElementEditorInput) input)
								.getMethodElement();
					} else if (input instanceof ConfigurationEditorInput) {
						element = ((ConfigurationEditorInput) input)
								.getConfiguration();
					} else if(input instanceof DiagramEditorInputProxy){
						DiagramEditorInput eInput = ((DiagramEditorInputProxy)input).getDiagramEditorInput();
						element = eInput.getMethodElement();
						if(element == null){
							if(eInput.getWrapper() != null){
								element = (MethodElement)TngUtil.unwrap(eInput.getWrapper());
							}
						}
					}
					if (element != null
							&& (element.eContainer() == null || UmaUtil
									.isContainedBy(element, methodplugin))) {
						if (!element.equals(methodplugin))
							editorRefsToClose.add(editorReference);
					}
				}
			}
			workbenchPage.closeEditors(editorRefsToClose.toArray(new IEditorReference[editorRefsToClose.size()]), true);
		}
	}
	
	
	/***vEPF****/
	//Abre el editor de adaptacion de proceso
	public void openAdaptProcessEditor(Object element) {
		
		/**Datos***/
		//Recuperamos los argumentos para la ventana
		TailoredProcessComponent tailoredProcess = (TailoredProcessComponent) element;//Proceso a adaptar
		
		String processLineName=null;
		ProcessLineComponent processLine=null;
		
		Boolean found = false;
		
		ModelStructure aux = new ModelStructure();
		ModelStructure modelStruct = aux.DEFAULT;//ModelStructure
		
		Collection auxVariants = new ArrayList();
		Collection auxVarPoints = new ArrayList();
		
		for (EObject obj = (EObject) element; obj != null && !found; obj = obj.eContainer()) {//Linea de proceso perteneciente
			if (obj instanceof MethodPlugin && (obj instanceof ProcessLineComponent)) {
				processLine = (ProcessLineComponent) obj;
				processLineName = processLine.getName();
				found = true;
			}
		}
		
		if(processLine != null && processLineName !=null){
			//Variantes
			VariantsPackage variantsPkg = (VariantsPackage) UmaUtil.findVariantsPackage(processLine, modelStruct.variantsPath, processLineName);
			if (variantsPkg != null){
				auxVariants = variantsPkg.getVariant();
			}
			//Puntos de variacion
			VarPointsPackage varPointsPkg = (VarPointsPackage) UmaUtil.findVarPointsPackage(processLine, modelStruct.varPointsPath, processLineName);
			if(varPointsPkg != null){
				auxVarPoints = varPointsPkg.getVarPoints();
			}
		}
		
		/***Interfaz de Usuario***/
		//Si todos los datos estan listos lanzamos la ventana de lo contrario generamos una accion de ERROR FIXME generar accion de error
		if(auxVariants.size() > 0 && auxVarPoints.size() > 0){
			//Preparamos ventana
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			

			TailoredProcessDialog tailoredProcessDialog = new TailoredProcessDialog(shell, SWT.NULL, tailoredProcess, auxVariants, auxVarPoints);
			tailoredProcessDialog.open();
		}
	}
	//Abre el editor para modificar una variante
	public void openModifyVariantEditor(final Object variantSelected){
		
		//Preparamos los datos
		if (variantSelected instanceof Variant){
			
			Variant variant = (Variant) variantSelected;
			BreakdownElement activity = (BreakdownElement) variantSelected;
			
			Collection auxVariants = new ArrayList();
			Collection auxVarPoints = new ArrayList();
			String auxName="";
			String auxElement="";
			Image imgIcon = null;
			
			ProcessLineComponent auxFather = (ProcessLineComponent) UmaUtil.getProcessLine((EObject) variant);
			String processLineNameOwner = auxFather.getName();
			
			ModelStructure aux = new ModelStructure();
			ModelStructure modelStruct = aux.DEFAULT;//ModelStructure
			
			//Obtenemos lista de variantes de esa linea
			//
			VariantsPackage variantsPkg = (VariantsPackage) UmaUtil.findVariantsPackage(auxFather, modelStruct.variantsPath, processLineNameOwner);
			if (variantsPkg != null){
				auxVariants = variantsPkg.getVariant();
			}
			
			//Obtenemos lista de puntos de variacion de esa linea para el mismo tipo
			//
			VarPointsPackage varPointsPkg = (VarPointsPackage) UmaUtil.findVarPointsPackage(auxFather, modelStruct.varPointsPath, processLineNameOwner);
			////Actividad
			if(variant instanceof VarActivity){//Instancia de VarActivity
				auxName="VarActivity";
				auxElement="Activity";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vactivity");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpActivity){
						auxVarPoints.add(vpElement);
					}
				}
			}
			////Phase
			if(variant instanceof VarPhase){//Instancia de VarPhase
				auxName="VarPhase";
				auxElement="Phase";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/varphase");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpPhase){
						auxVarPoints.add(vpElement);
					}
				}
			}
			////Iteration
			if(variant instanceof VarIteration){//Instancia de VarIteration
				auxName="VarIteration";
				auxElement="Iteration";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/variteration");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpIteration){
						auxVarPoints.add(vpElement);
					}
				}
			}
			////RoleDescriptor
			if(variant instanceof VarRoleDescriptor){//Instancia de VarRoleDescriptor
				auxName="VarRoleDescriptor";
				auxElement="Role Descriptor";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/varroledescriptor");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpRoleDescriptor){
						auxVarPoints.add(vpElement);
					}
				}
			}
			////TaskDescriptor
			if(variant instanceof VarTaskDescriptor){//Instancia de VarTaskDescriptor
				auxName="VarTaskDescriptor";
				auxElement="Task Descriptor";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vartaskdescriptor");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpTaskDescriptor){
						auxVarPoints.add(vpElement);
					}
				}
			}
			
			////VarWorkProductDescriptor
			if(variant instanceof VarWorkProductDescriptor){
				auxName="VarWorkProductDescriptor";
				auxElement="WorkProduct Descriptor";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vworkproductdescriptor");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpWorkProductDescriptor){
						auxVarPoints.add(vpElement);
					}
				}
			}
			
			////VarTeamProfile
			if(variant instanceof VarTeamProfile){
				auxName="VarTeamProfile";
				auxElement="Team Profile";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vteamprofile");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpTeamProfile){
						auxVarPoints.add(vpElement);
					}
				}
			}
			
			////VarMilestone
			if(variant instanceof VarMilestone){
				auxName="VarMilestone";
				auxElement="Milestone";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vmilestone");//Icon
				
				for(Iterator iterator = varPointsPkg.getVarPoints().iterator(); iterator.hasNext();){
					EObject vpElement = (EObject) iterator.next();
					if(vpElement instanceof vpMilestone){
						auxVarPoints.add(vpElement);
					}
				}
			}
			
			//Preparamos ventana
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			
			VariantDialog variantDialog = new VariantDialog(shell, SWT.NULL, auxName, auxElement, imgIcon, variant, auxVariants, auxVarPoints, true, false);
			variantDialog.open();
			
			
			//Si todo se ejecuto de forma correcta entonces procedemos a guardar el elemento FIXME! Hay datos que no se tratan bien
			if(variantDialog.execute == true){
								
				//variant es una referencia a element - Datos
				//
				variant.setDescription(variantDialog.descripcion);//Descripcion
				variant.setVId(variantDialog.vpId);//Identificador
				variant.setVpName(variantDialog.nombre);//Nombre
				activity.setName(variantDialog.nombre);//Nombre
				activity.setPresentationName(variantDialog.nombre);//Nombre
				
				//Dependenias - Nos las generamos dependiendo de las listas llenadas en la interfaz
				//
				
				//Limpiamos primero para que no se dupliquen dependencias que antes estaban
				variant.getClient().clear();
//				variant.getSupplier().clear();
				
				
				//variant2variant (Inclusive)
				if(variantDialog.inclusiveVariant2VariantPersistence.size() > 0){
					Collection auxListClient = new ArrayList();
					
					
					for(Iterator iterator = variantDialog.inclusiveVariant2VariantPersistence.iterator(); iterator.hasNext();){
						Collection auxListSupplier = new ArrayList();
						
						EObject variantSupplier = (EObject) iterator.next();
						variant2variant variant2variantI = UmaFactory.eINSTANCE.createvariant2variant();
						variant2variant variant2variantIForSupplier = UmaFactory.eINSTANCE.createvariant2variant();
						
						if(variantSupplier instanceof Variant){
							Variant varAuxSupplier = (Variant) variantSupplier;
							//Client
							variant2variantI.setClient(variant);//Cliente
							variant2variantI.setSupplier(varAuxSupplier);//Supplier
							variant2variantI.setIsInclusive(true);//Inclusivo
							//Supplier
							variant2variantIForSupplier.setClient(variant);//Cliente
							variant2variantIForSupplier.setSupplier(varAuxSupplier);//Supplier
							variant2variantIForSupplier.setIsInclusive(true);//Inclusivo

							
							auxListClient.add(variant2variantI);
							auxListSupplier.add(variant2variantIForSupplier);
							
//							if(!varAuxSupplier.getSupplier().contains(variant2variantIForSupplier)){
//								varAuxSupplier.getSupplier().addAll(auxListSupplier);
//							}
							if(!UmaUtil.collectionContainsDependence(varAuxSupplier.getSupplier(), variant2variantIForSupplier)){
								varAuxSupplier.getSupplier().addAll(auxListSupplier);
							}
						}
					}
					variant.getClient().addAll(auxListClient);
				}
				
				//variant2varPoint (Inclusive)
				if(variantDialog.inclusiveVariant2VarPointPersistence.size() > 0){
					
					Collection auxListClient = new ArrayList();
					
					
					for(Iterator iterator = variantDialog.inclusiveVariant2VarPointPersistence.iterator(); iterator.hasNext();){
						Collection auxListSupplier = new ArrayList();
						
						EObject varPointSupplier = (EObject) iterator.next();
						variant2varP variant2varPointI = UmaFactory.eINSTANCE.createvariant2varP();
						variant2varP variant2varPointIForSupplier = UmaFactory.eINSTANCE.createvariant2varP();
						
						if(varPointSupplier instanceof VarPoint){
							VarPoint varPointAuxSupplier = (VarPoint) varPointSupplier;
							//Client
							variant2varPointI.setClient(variant);//Cliente
							variant2varPointI.setSupplier(varPointAuxSupplier);//Supplier
							variant2varPointI.setIsInclusive(true);//Inclusivo
							//Supplier
							variant2varPointIForSupplier.setClient(variant);//Cliente
							variant2varPointIForSupplier.setSupplier(varPointAuxSupplier);//Supplier
							variant2varPointIForSupplier.setIsInclusive(true);//Inclusivo
							
							auxListClient.add(variant2varPointI);
							auxListSupplier.add(variant2varPointIForSupplier);
							
//							if(!varPointAuxSupplier.getSupplier().contains(variant2varPointIForSupplier)){
//								varPointAuxSupplier.getSupplier().addAll(auxListSupplier);
//							}
							if(!UmaUtil.collectionContainsDependence(varPointAuxSupplier.getSupplier(), variant2varPointIForSupplier)){
								varPointAuxSupplier.getSupplier().addAll(auxListSupplier);
							}
							
						}
						
						
					}
					variant.getClient().addAll(auxListClient);
				}
				//variant2variant (Exclusive)
				if(variantDialog.exclusiveVariant2VariantPersistence.size() > 0){
					
					Collection auxListClient = new ArrayList();
					
					
					for(Iterator iterator = variantDialog.exclusiveVariant2VariantPersistence.iterator(); iterator.hasNext();){
						
						Collection auxListSupplier = new ArrayList();
						
						EObject variantSupplier = (EObject) iterator.next();
						variant2variant variant2variantE = UmaFactory.eINSTANCE.createvariant2variant();
						variant2variant variant2variantEForSupplier = UmaFactory.eINSTANCE.createvariant2variant();
						
						if(variantSupplier instanceof Variant){
							Variant varAuxSupplier = (Variant) variantSupplier;
							
							//Client
							variant2variantE.setClient(variant);//Cliente
							variant2variantE.setSupplier(varAuxSupplier);//Supplier
							variant2variantE.setIsInclusive(false);//Exclusivo
							//Supplier
							variant2variantEForSupplier.setClient(variant);
							variant2variantEForSupplier.setSupplier(varAuxSupplier);
							variant2variantEForSupplier.setIsInclusive(false);
							
							auxListClient.add(variant2variantE);
							auxListSupplier.add(variant2variantEForSupplier);
							
//							if(!varAuxSupplier.getSupplier().contains(variant2variantEForSupplier)){
//								varAuxSupplier.getSupplier().addAll(auxListSupplier);
//							}
							if(!UmaUtil.collectionContainsDependence(varAuxSupplier.getSupplier(), variant2variantEForSupplier)){
								varAuxSupplier.getSupplier().addAll(auxListSupplier);
							}
						}
						
						
					}
					variant.getClient().addAll(auxListClient);
				}
				
				
				// Salvamos el elemento modificado
				//
				Runnable runnable = new Runnable() {
					
					public void run() {
						// Persistencia
						//
						ILibraryPersister.FailSafeMethodLibraryPersister persister = getPersister(variantSelected);
						try {
							try {
								persister.save(((EObject) variantSelected).eResource());
								persister.commit();		
							} catch (Exception e1) {		
								try {
									persister.rollback();
								}
								catch(Exception e) {
									AuthoringUIPlugin.getDefault().getLogger().logError(e);
								}
								handlePersistenceException(e1, variantSelected);
							}
						}
						catch(RuntimeException e) {				
							throw e;
						}
						
						// cambiamos el diagrama si es un proceso
						if (variantSelected instanceof ProcessComponent) {
							Process proc = ((ProcessComponent) variantSelected).getProcess();	
							DiagramManager mgr = DiagramManager.getInstance(proc, this);
							if(mgr != null) {
								try {
									mgr.updateResourceURI();
								}
								catch(Exception e) {
									AuthoringUIPlugin.getDefault().getLogger().logError(e);
								}
								finally {
									try { 
										mgr.removeConsumer(this);
									}
									catch(Exception e) {
										AuthoringUIPlugin.getDefault().getLogger().logError(e);
									}
								}
							}
							
						}
					}
			
				};
			
				UserInteractionHelper.runWithProgress(runnable,
						AuthoringUIResources.modify_Variant_element);
				
			}//Fin de la ejecucion execute = true;
		
		
		}

	}
	/**
	 * Abre el editor para modificar un punto de variacion
	 * @param varPointSelected
	 */
	public void openModifyVarPointEditor(final Object varPointSelected){
		
		if(varPointSelected instanceof VarPoint){
			
			VarPoint varPoint = (VarPoint) varPointSelected;
			BreakdownElement activity = (BreakdownElement) varPointSelected;
			
			Collection auxVariants = new ArrayList();
			Collection auxVarPoints = new ArrayList();
			String auxName="";
			String auxElement="";
			Image imgIcon = null;
			
			ProcessLineComponent auxFather = (ProcessLineComponent) UmaUtil.getProcessLine((EObject) varPoint);
			String processLineNameOwner = auxFather.getName();
			
			ModelStructure aux = new ModelStructure();
			ModelStructure modelStruct = aux.DEFAULT;//ModelStructure
			
			//Obtenemos lista de puntos de variación de esa linea
			//
			VarPointsPackage varPointsPkg = (VarPointsPackage) UmaUtil.findVarPointsPackage(auxFather, modelStruct.varPointsPath, processLineNameOwner);
			if(varPointsPkg != null){
				auxVarPoints = varPointsPkg.getVarPoints();
			}
			
			//Obtenemos la lista de variantes de esa linea para el mismo tipo
			//
			VariantsPackage variantsPkg = (VariantsPackage) UmaUtil.findVariantsPackage(auxFather, modelStruct.variantsPath, processLineNameOwner);
			////VPActividad
			if(varPointSelected instanceof vpActivity){//Instancia de VarActivity
				auxName="vpActivity";
				auxElement="Activity";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpactivity");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarActivity){
						auxVariants.add(varElement);
					}
				}
			}
			////VPPhase
			if(varPointSelected instanceof vpPhase){//Instancia de VarActivity
				auxName="vpPhase";
				auxElement="Phase";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpphase");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarPhase){
						auxVariants.add(varElement);
					}
				}
			}
			////VPIteration
			if(varPointSelected instanceof vpIteration){//Instancia de VarActivity
				auxName="vpIteration";
				auxElement="Iteration";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpiteration");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarIteration){
						auxVariants.add(varElement);
					}
				}
			}
			////VPRoleDescriptor
			if(varPointSelected instanceof vpRoleDescriptor){//Instancia de VarActivity
				auxName="vpRoleDescriptor";
				auxElement="RoleDescriptor";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vproledescriptor");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarRoleDescriptor){
						auxVariants.add(varElement);
					}
				}
			}
			////VPTaskDescriptor
			if(varPointSelected instanceof vpTaskDescriptor){//Instancia de VarActivity
				auxName="vpTaskDescriptor";
				auxElement="TaskDescriptor";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vptaskdescriptor");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarTaskDescriptor){
						auxVariants.add(varElement);
					}
				}
			}
			////VPWorkProductDescriptor
			if(varPointSelected instanceof vpWorkProductDescriptor){
				auxName="vpWorkProduct Descriptor";
				auxElement="WorkProduct Descriptor";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpworkproductdescriptor");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarWorkProductDescriptor){
						auxVariants.add(varElement);
					}
				}
			}
			////VPTeamProfile
			if(varPointSelected instanceof vpTeamProfile){
				auxName="vpTeamProfile";
				auxElement="TeamProfile";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpteamprofile");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarTeamProfile){
						auxVariants.add(varElement);
					}
				}
			}
			////VPMilestone
			if(varPointSelected instanceof vpMilestone){
				auxName="vpMilestone";
				auxElement="Milestone";
				imgIcon = LibraryEditPlugin.INSTANCE.getImage("full/vSPEM70x70/vpmilestone");
				
				for(Iterator iterator = variantsPkg.getVariant().iterator(); iterator.hasNext();){
					EObject varElement = (EObject) iterator.next();
					if(varElement instanceof VarMilestone){
						auxVariants.add(varElement);
					}
				}
			}
			
			///Ventana - Composicion SWT
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			VarPointDialog varPointDialog = new VarPointDialog(shell, SWT.NULL, auxName, auxElement, imgIcon, varPoint, auxVariants, auxVarPoints, true, false);
			varPointDialog.open();
			
			if(varPointDialog.execute == true){
				
				//Recogo datos, varPoint es una referencia a element
				varPoint.setIsImplicit(varPointDialog.isImplicit);
				varPoint.setIsOpen(varPointDialog.isOpen);
				varPoint.setMin(varPointDialog.cardMin);//
				varPoint.setMax(varPointDialog.cardMax);//
				varPoint.setVId(varPointDialog.vpId);
				varPoint.setVpName(varPointDialog.nombre);
				varPoint.setPathIcon(varPointDialog.icono);
				varPoint.setDescription(varPointDialog.descripcion);
				
				activity.setName(varPoint.getVpName());
				activity.setPresentationName(varPoint.getVpName());
				
				
				//Dependenias - Nos las generamos dependiendo de las listas llenadas en la interfaz
				//
				
				//Limpiamos las dependencias para que no se repitan
				varPoint.getClient().clear();
//				varPoint.getSupplier().clear();
				
				//varPoint2varPoint (Inclusive)
				if(varPointDialog.inclusiveVarPoint2VarPointPersistence.size() > 0){
					
					Collection auxListClient = new ArrayList();
					
					
					for(Iterator iterator = varPointDialog.inclusiveVarPoint2VarPointPersistence.iterator(); iterator.hasNext();){
						Collection auxListSupplier = new ArrayList();
						
						EObject varPointSupplier = (EObject) iterator.next();
						
						varP2varP varP2varPI = UmaFactory.eINSTANCE.createvarP2varP();
						varP2varP varP2varPIForSupplier = UmaFactory.eINSTANCE.createvarP2varP();
						
						if(varPointSupplier instanceof VarPoint){
							VarPoint varPointAuxSupplier = (VarPoint) varPointSupplier;

							varP2varPI.setClient(varPoint);//Cliente
							varP2varPI.setSupplier(varPointAuxSupplier);//Supplier
							varP2varPI.setIsInclusive(true);//Inclusivo
							
							varP2varPIForSupplier.setClient(varPoint);//Cliente
							varP2varPIForSupplier.setSupplier(varPointAuxSupplier);//Supplier
							varP2varPIForSupplier.setIsInclusive(true);//Inclusivo
							
							auxListClient.add(varP2varPI);
							auxListSupplier.add(varP2varPIForSupplier);
							
							if(!UmaUtil.collectionContainsDependence(varPointAuxSupplier.getSupplier(), varP2varPIForSupplier)){
								varPointAuxSupplier.getSupplier().addAll(auxListSupplier);
							}
						}
					}
					varPoint.getClient().addAll(auxListClient);
				}
				//varPoint2variant (Inclusive)
				if(varPointDialog.inclusiveVarPoint2VariantPersistence.size() > 0){
					
					Collection auxListClient = new ArrayList();
					
					
					for(Iterator iterator = varPointDialog.inclusiveVarPoint2VariantPersistence.iterator(); iterator.hasNext();){
						Collection auxListSupplier = new ArrayList();
						
						EObject variantSupplier = (EObject) iterator.next();
						
						varp2variant varP2variantI = UmaFactory.eINSTANCE.createvarp2variant();
						varp2variant varP2variantIForSupplier = UmaFactory.eINSTANCE.createvarp2variant();
						
						if(variantSupplier instanceof Variant){
							Variant variantAuxSupplier = (Variant) variantSupplier;
							
							varP2variantI.setClient(varPoint);//Cliente
							varP2variantI.setSupplier(variantAuxSupplier);//Supplier
							varP2variantI.setIsInclusive(true);//Inclusivo
							
							varP2variantIForSupplier.setClient(varPoint);//Cliente
							varP2variantIForSupplier.setSupplier(variantAuxSupplier);//Supplier
							varP2variantIForSupplier.setIsInclusive(true);//Inclusivo
							
							auxListClient.add(varP2variantI);
							auxListSupplier.add(varP2variantIForSupplier);
							
//							if(!variantAuxSupplier.getSupplier().contains(varP2variantIForSupplier)){
//								variantAuxSupplier.getSupplier().addAll(auxListSupplier);
//							}
							if(!UmaUtil.collectionContainsDependence(variantAuxSupplier.getSupplier(), varP2variantIForSupplier)){
								variantAuxSupplier.getSupplier().addAll(auxListSupplier);
							}
						}
					}
					varPoint.getClient().addAll(auxListClient);
				}
				//varP2varP (Exclusive)
				if(varPointDialog.exclusiveVarPoint2VarPointPersistence.size() > 0){
					
					Collection auxListClient = new ArrayList();
					
					
					for(Iterator iterator = varPointDialog.exclusiveVarPoint2VarPointPersistence.iterator(); iterator.hasNext();){
						Collection auxListSupplier = new ArrayList();

						EObject varPointSupplier = (EObject) iterator.next();
						varP2varP varP2varPE = UmaFactory.eINSTANCE.createvarP2varP();
						varP2varP varP2varPEForSupplier = UmaFactory.eINSTANCE.createvarP2varP();
						
						if(varPointSupplier instanceof VarPoint){
							VarPoint varPointAuxSupplier = (VarPoint) varPointSupplier;
							//Client
							varP2varPE.setClient(varPoint);//Cliente
							varP2varPE.setSupplier(varPointAuxSupplier);//Supplier
							varP2varPE.setIsInclusive(false);//Exclusivo
							
							//Supplier
							varP2varPEForSupplier.setClient(varPoint);//Cliente
							varP2varPEForSupplier.setSupplier(varPointAuxSupplier);//Supplier
							varP2varPEForSupplier.setIsInclusive(false);//Exclusivo
							
							auxListClient.add(varP2varPE);
							auxListSupplier.add(varP2varPEForSupplier);
							
//							if(!varPointAuxSupplier.getSupplier().contains(auxListSupplier)){
//								varPointAuxSupplier.getSupplier().addAll(auxListSupplier);
//							}
							if(!UmaUtil.collectionContainsDependence(varPointAuxSupplier.getSupplier(), varP2varPEForSupplier)){
								varPointAuxSupplier.getSupplier().addAll(auxListSupplier);
							}
						}
					}
					varPoint.getClient().addAll(auxListClient);
				}
				
				// Salvamos el elemento modificado
				//
				Runnable runnable = new Runnable() {
					
					public void run() {
						// Persistencia
						//
						ILibraryPersister.FailSafeMethodLibraryPersister persister = getPersister(varPointSelected);
						try {
							try {
								persister.save(((EObject) varPointSelected).eResource());
								persister.commit();		
							} catch (Exception e1) {		
								try {
									persister.rollback();
								}
								catch(Exception e) {
									AuthoringUIPlugin.getDefault().getLogger().logError(e);
								}
								handlePersistenceException(e1, varPointSelected);
							}
						}
						catch(RuntimeException e) {				
							throw e;
						}
						
						// cambiamos el diagrama si es un proceso
						if (varPointSelected instanceof ProcessComponent) {
							Process proc = ((ProcessComponent) varPointSelected).getProcess();	
							DiagramManager mgr = DiagramManager.getInstance(proc, this);
							if(mgr != null) {
								try {
									mgr.updateResourceURI();
								}
								catch(Exception e) {
									AuthoringUIPlugin.getDefault().getLogger().logError(e);
								}
								finally {
									try { 
										mgr.removeConsumer(this);
									}
									catch(Exception e) {
										AuthoringUIPlugin.getDefault().getLogger().logError(e);
									}
								}
							}
							
						}
					}
			
				};
				UserInteractionHelper.runWithProgress(runnable,
						AuthoringUIResources.modify_VarPoint_element);

			}//Fin de Execute
			
			
		}
		
	}
	
	
	private ILibraryPersister.FailSafeMethodLibraryPersister getPersister(Object variantSelected) {
		return LibraryServiceUtil.getPersisterFor(((EObject) variantSelected).eResource())
					.getFailSafePersister();
	}
	
	private void handlePersistenceException(Exception e1, Object variantSelected) {
		AuthoringUIPlugin.getDefault().getLogger().logError(e1);

		String details = e1.getMessage() != null ? MessageFormat
				.format(": {0}", new Object[] { e1.getMessage() }) : ""; //$NON-NLS-1$ //$NON-NLS-2$
		String msg = MessageFormat.format(
				AuthoringUIResources.ElementsView_err_saving,
				new Object[] {
						((EObject) variantSelected).eResource().getURI().toFileString(),
						details });
		throw new MessageException(msg);
	}
	
	// Keep track of the active editor.
	protected IViewPart activeViewPart;
	
	protected void refreshViewer(Viewer viewer) {
		viewer.refresh();
	}
	
	protected IAction refreshViewerAction = new Action(
			AuthoringUIResources._UI_RefreshViewer_menu_item) { 
		public boolean isEnabled() {
			return activeViewPart instanceof IViewerProvider;
		}

		public void run() {
			if (activeViewPart instanceof IViewerProvider) {
				Viewer viewer = ((IViewerProvider) activeViewPart).getViewer();
				if (viewer != null) {
					refreshViewer(viewer);
				}
			}
		}
	};
	
	/****/

	
}