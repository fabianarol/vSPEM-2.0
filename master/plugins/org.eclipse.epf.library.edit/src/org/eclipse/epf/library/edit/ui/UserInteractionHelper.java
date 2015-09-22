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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.common.serviceability.MsgBox;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.services.IAccessController;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * Defines static methods that interact with user via dialog boxes to retrieve
 * more information or confirmation from the users.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public final class UserInteractionHelper {
	private static final boolean canInteract = true;

	private UserInteractionHelper() {
		super();
	}

	public static boolean canInteract() {
		return canInteract;
	}

	/**
	 * Checks against default configuration of the given process whether the
	 * given (method) object can be used in the process. This method will prompt
	 * user to add the object and those elements that it is associated with to
	 * the default configuration if the default configuration does not have them
	 * yet.
	 * 
	 * @param proc
	 *            the process
	 * @param object
	 *            the method object
	 * @return
	 *            <li>1 if the process'es default configuration already has the
	 *            object
	 *            <li>2 if the object is not in the configuration, but user
	 *            wants to add it now
	 *            <li>0 if the object is not in the configuration and user
	 *            don't want to add it
	 *            <li>-1 if user cancel the prompt to add the objects and
	 *            associated dependencies to the default configuration
	 */
	public static int checkAgainstDefaultConfiguration(Process proc,
			Object object) {
		return checkAgainstDefaultConfiguration(proc, object, null);
	}

	/**
	 * Checks against default configuration of the given process whether the
	 * given (method) object can be used in the process. This method will prompt
	 * user to add the object and those elements that it is associated with to
	 * the default configuration if the default configuration does not have them
	 * yet.
	 * 
	 * @param proc
	 *            the process
	 * @param object
	 *            the method object
	 * @return
	 *            <li>1 if the process'es default configuration already has the
	 *            object
	 *            <li>2 if the object is not in the configuration, but user
	 *            wants to add it now
	 *            <li>0 if the object is not in the configuration and user
	 *            don't want to add it
	 *            <li>-1 if user cancel the prompt to add the objects and
	 *            associated dependencies to the default configuration
	 */
	public static int checkAgainstDefaultConfiguration(Process proc,
			Object object, IConfigurator configurator) {
		Object e = TngUtil.unwrap(object);
		if (!(e instanceof MethodElement))
			return 0;

		if (configurator == null)
			configurator = Providers.getConfiguratorFactory()
					.createConfigurator(proc.getDefaultContext());
		if (e instanceof VariabilityElement) {
			// check all the base elements if there is any
			//
			boolean allIn = true;
			for (VariabilityElement c = ((VariabilityElement) object); c != null; c = (VariabilityElement) c
					.getVariabilityBasedOnElement()) {
				if (!configurator.accept(c)) {
					allIn = false;
					break;
				}
			}
			if (allIn) {
				return 1;
			}
		}
		if (configurator.accept(e))
			return 1;

		// object is not in the configuration
		// ask user if he/she want to add it to the default configuration
		//
		String msg = NLS
				.bind(
						LibraryEditResources.ui_UserInteractionHelper_defaultconfigcheck,
						((MethodElement) e).getName());

		int ret = MsgBox.prompt(msg);
		if (TngUtil.DEBUG) {
			System.out
					.println("UserInteractionHelper.checkAgainstDefaultConfiguration(): element=" //$NON-NLS-1$
							+ e + ", path=" + TngUtil.getLabelWithPath(e)); //$NON-NLS-1$
		}
		switch (ret) {
		case SWT.YES:
			IStatus status = TngUtil.checkEdit(proc.getDefaultContext(), null);
			if (!status.isOK()) {
				return 0;
			}
			return 2;
		case SWT.NO:
			return 0;
		case SWT.CANCEL:
			return -1;
		}

		// WrappedMessageDialog msgBox = new
		// WrappedMessageDialog(MsgBox.getDefaultShell(), null, null, msg,
		// MessageDialog.QUESTION,
		// new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL,
		// IDialogConstants.CANCEL_LABEL },
		// 0);
		// switch (msgBox.open()) {
		// case IDialogConstants.OK_ID:
		// IStatus status = TngUtil.checkEdit(proc.getDefaultContext(), null);
		// if (!status.isOK()) {
		// return 0;
		// }
		// return 2;
		// case IDialogConstants.NO_ID:
		// return 0;
		// case IDialogConstants.CANCEL_ID:
		// return -1;
		// };

		return 0;
	}

	/**
	 * Select tasks which has given workproduct as output.
	 * 
	 * @param taskList
	 * @param wp
	 * @return
	 */
	public static List selectTasks(List taskList, WorkProduct wp) {
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public String getColumnText(Object obj, int column) {
				if (obj instanceof MethodElement) {
					return TngUtil.getPresentationName(obj);
				}
				return super.getText(obj);
			}
		};

		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return ((List) object).toArray();
			}
		};

		try {
			ProcessListSelectionDialog dlg = new ProcessListSelectionDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getShell(),
					taskList,
					contentProvider,
					labelProvider,
					NLS
							.bind(
									LibraryEditResources.ui_UserInteractionHelper_wplistdlg_msg,
									wp.getName()));

			dlg.setTitle(LibraryEditResources.ui_UserInteractionHelper_tasks);
			dlg.setBlockOnOpen(true);
			dlg.open();
			Object[] objs = dlg.getResult();

			List selectedTasks = new ArrayList();
			if ((objs != null) && (objs.length > 0)) {
				for (int i = 0; i < objs.length; i++) {
					selectedTasks.add(objs[i]);
				}
			}
			return selectedTasks;
		} finally {
			// dispose
			labelProvider.dispose();
			contentProvider.dispose();
		}
	}

	/**
	 * Select responsible work products for the given role
	 * 
	 * @param wpList
	 * @param role
	 * @return
	 */
	public static List selectWorkProducts(List wpList, Role role) {
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public String getColumnText(Object obj, int column) {
				if (obj instanceof MethodElement) {
					return TngUtil.getPresentationName(obj);
				}
				return super.getText(obj);
			}
		};

		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return ((List) object).toArray();
			}
		};

		try {
			ProcessListSelectionDialog dlg = new ProcessListSelectionDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getShell(),
					// MsgBox.getDefaultShell(),
					wpList,
					contentProvider,
					labelProvider,
					NLS
							.bind(
									LibraryEditResources.ui_UserInteractionHelper_rolelistdlg_msg,
									role.getName()));

			dlg
					.setTitle(LibraryEditResources.ui_UserInteractionHelper_workproducts); 
			dlg.setBlockOnOpen(true);
			dlg.open();
			Object[] objs = dlg.getResult();

			List selectedWps = new ArrayList();
			if ((objs != null) && (objs.length > 0)) {
				for (int i = 0; i < objs.length; i++) {
					selectedWps.add(objs[i]);
				}
			}
			return selectedWps;
		} finally {
			// dispose
			labelProvider.dispose();
			contentProvider.dispose();
		}
	}

	/**
	 * Requests name from user
	 * 
	 * @param object
	 * @param nameFeature
	 * @return the requested name of null if user canceled
	 */
	public static String requestName(Object child,
			EStructuralFeature nameFeature, String title,
			final IValidator validator) {
		IInputValidator inputValidator = new IInputValidator() {

			public String isValid(String newText) {
				if (validator != null) {
					return getSimpleErrorMessage(validator.isValid(newText));
				}
				return null;
			}

		};
		String name = ""; //$NON-NLS-1$
		if (child instanceof EObject) {
			EObject e = (EObject) child;
			String str = (String) e.eGet(nameFeature);
			if (str != null) {
				name = str;
			}
		}
		InputDialog inputDialog = new InputDialog(Display.getCurrent()
				.getActiveShell(), title,
				LibraryEditResources.UserInteractionHelper_ProcessPackage_Name,
				name, inputValidator); 
		if (inputDialog.open() == Window.OK) {
			return inputDialog.getValue().trim();		
		}
		return null;
	}

	public static String getSimpleErrorMessage(String msg) {
		if (msg == null)
			return null;

		int s = msg.indexOf(':');

		String prefix = ""; //$NON-NLS-1$
		if (s >= 0) {
			prefix = msg.substring(0, s);

			String emptyElementNameErrorMsg = LibraryEditResources.emptyElementNameError_msg; 
			String dupContentFileErrorMsg = LibraryEditResources.duplicateContentFileError_msg; 
			String dupElementNameErrorMsg = LibraryEditResources.duplicateElementNameError_msg; 

			if (emptyElementNameErrorMsg != null
					&& emptyElementNameErrorMsg.startsWith(prefix))
				return LibraryEditResources.emptyElementNameError_simple_msg; 
			else if (dupContentFileErrorMsg != null
					&& dupContentFileErrorMsg.startsWith(prefix))
				return LibraryEditResources.duplicateContentFileError_simple_msg; 
			else if (dupElementNameErrorMsg != null
					&& dupElementNameErrorMsg.startsWith(prefix))
				return LibraryEditResources.duplicateElementNameError_simple_msg; 
		}

		if (s < 0)
			s = 0;
		else
			s++;
		int t = msg.indexOf('\n');
		if (t < 0)
			t = msg.length();

		return msg.substring(s, t).trim();
	}

	/**
	 * Return team in which user would automatically assign a role into
	 * 
	 * @param activity
	 * @param role
	 * @return
	 * 
	 */
	public static TeamProfile getTeam(Activity activity, Role role) {
		return getTeam(activity, role, null);
	}

	public static TeamProfile getTeam(Activity activity, Role role,
			Object UIContext) {
		List teamList = new ArrayList();
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		// find out all team in visible scope
		getTeamsInScope(adapterFactory, activity, role, teamList);
		if (teamList.size() == 1) {
			return (TeamProfile) teamList.get(0);
		}
		if (teamList.size() > 1) {
			return TeamSelection.getSelectedTeam(teamList, role,
					UIContext instanceof Shell ? (Shell) UIContext : null);
		}
		// there are no teams to assign
		return null;
	}

	/**
	 * Get teams in scope
	 * 
	 * @param adapterFactory
	 * @param e
	 * @param role
	 * @param teamList
	 */
	private static void getTeamsInScope(AdapterFactory adapterFactory,
			BreakdownElement e, Role role, List teamList) {
		// get children for activity
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(e, ITreeItemContentProvider.class);
		Collection children = itemProvider.getChildren(e);
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof TeamProfile) {
				TeamProfile team = (TeamProfile) obj;
				List allTeams = new ArrayList();
				// get all sub teams as well
				ProcessUtil.getAllSubTeams(team, allTeams);

				for (Iterator teamItor = allTeams.iterator(); teamItor
						.hasNext();) {
					Object o = teamItor.next();
					if (o instanceof TeamProfile) {
						// get roles from teams
						List roles = ProcessUtil.getRoles(((TeamProfile) o)
								.getTeamRoles());
						if (roles.contains(role)) {
							teamList.add(o);
						}
					}
				}
			}
		}

		// get parent
		Object currentParent = itemProvider.getParent(e);
		if (currentParent != null) {
			// go up
			getTeamsInScope(adapterFactory, (BreakdownElement) currentParent,
					role, teamList);
		}
	}

	private static IRunnableContext getRunnableContext() {
		return ExtensionManager.getDefaultUserInteractionHandler()
				.getRunnableContext();
	}

	public static final boolean runWithProgress(final Runnable runnable,
			final String msg) {
		final MultiStatus status = new MultiStatus(LibraryEditPlugin.INSTANCE
				.getSymbolicName(), IStatus.OK,
				LibraryEditResources.error_reason, null); 

		final IRunnableWithProgress operation = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask(msg, IProgressMonitor.UNKNOWN);
				monitor.subTask(""); //$NON-NLS-1$
				try {
					runnable.run();
				} catch (RuntimeException e) {
					String msg;
					Throwable ex = null;
					if (e instanceof MessageException) {
						msg = e.getMessage();
					} else {
						StringWriter strWriter = new StringWriter();
						e.printStackTrace(new PrintWriter(strWriter));
						msg = strWriter.toString();
						ex = e;
					}
					status.add(new Status(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(), 0,
							msg, ex));
				} finally {
					monitor.done();
				}
			}

		};

		try {
			// This runs the operation, and shows progress.
			//
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						try {
							getRunnableContext().run(true, false, operation);
						} catch (Exception e) {
							LibraryEditPlugin.getDefault().getLogger().logError(e);
						}
					}

				});
			} else {
				getRunnableContext().run(true, false, operation);
			}

			

			if (!status.isOK()) {
				Messenger.INSTANCE.showError(
						LibraryEditResources.errorDialog_title, 
						LibraryEditResources.error_msgWithDetails, 
						status);
			} else {
				return true;
			}
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			//
			LibraryEditPlugin.getDefault().getLogger().logError(exception);
		}

		return false;
	}

	public static final boolean runWithProgress(
			final IRunnableWithProgress runnable, final String msg) {
		return runWithProgress(runnable, false, msg);
	}

	public static final boolean runWithProgress(
			final IRunnableWithProgress runnable, final boolean canCancel,
			final String msg) {
		return runWithProgress(runnable, getRunnableContext(), canCancel, msg);
	}

	public static final IStatus runAsJob(IRunnableWithProgress runnable,
			String taskName) {
		Shell shell = MsgBox.getDefaultShell();
		if (shell == null) {
			try {
				runnable.run(new NullProgressMonitor());
				return Status.OK_STATUS;
			} catch (Exception e) {
				return new Status(IStatus.ERROR, LibraryEditPlugin.getPlugin()
						.getId(), 0, e.toString(), e);
			}
		} else {
			return runAsJob(runnable, taskName, shell);
		}
	}

	public static final IStatus runAsJob(final IRunnableWithProgress runnable,
			final String taskName, Shell shell) {
		Job job = new WorkspaceJob(taskName) {

			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				try {
					// monitor.beginTask(taskName, IProgressMonitor.UNKNOWN);
					runnable.run(monitor);
					return Status.OK_STATUS;
				} catch (InvocationTargetException e) {
					Throwable ex;
					if (e.getCause() != null) {
						ex = e.getCause();
					} else {
						ex = e;
					}
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, ex.toString(), ex);
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, e.toString(), e);
				} finally {
					monitor.done();
				}
			}

		};
		PlatformUI.getWorkbench().getProgressService().showInDialog(shell, job);
		job.schedule();
		return job.getResult();
	}

	public static void runInUI(final IRunnableWithProgress runnable,
			final String taskName) {
		Shell shell = MsgBox.getDefaultShell();
		if (shell == null) {
			try {
				runnable.run(new NullProgressMonitor());
				return;
			} catch (Exception e) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);
				throw new WrappedException(e);
			}
		}
		Job job = new WorkbenchJob(taskName) {

			public IStatus runInUIThread(IProgressMonitor monitor) {
				monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
				try {
					runnable.run(monitor);
					return Status.OK_STATUS;
				} catch (InvocationTargetException e) {
					Throwable ex;
					if (e.getCause() != null) {
						ex = e.getCause();
					} else {
						ex = e;
					}
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, ex.toString(), ex);
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, e.toString(), e);
				} finally {
					monitor.done();
				}
			}

		};
		PlatformUI.getWorkbench().getProgressService().showInDialog(shell, job);
		job.schedule();
	}
	
	public static final void runInUIThread(Runnable runnable) {
		runInUIThread(runnable, false);
	}
	
	public static final void runInUIThread(Runnable runnable, boolean async) {
		if(Display.getCurrent() == null) {
			// current thread is not a user-interface thread
			//
			Display display = null;
			try {
				display = Display.getDefault();
			}
			catch(Exception e) {
				
			}
			if(display != null) {
				if(async) {
					display.asyncExec(runnable);
				}
				else {
					display.syncExec(runnable);
				}
			}
			else {
				runnable.run();
			}
		}
		else {
			runnable.run();
		}
		
	}

	public static final boolean runInUI(final Runnable runnable,
			final String taskName) {
		return runInUI(runnable, taskName, null);
	}

	public static final boolean runInUI(final Runnable runnable,
			final String taskName, ISchedulingRule rule) {
		Shell shell = MsgBox.getDefaultShell();
		if (shell == null) {
			runnable.run();
			return true;
		} else {
			IRunnableWithProgress runner = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						monitor.beginTask(taskName, 2);
						monitor.worked(1);
						runnable.run();
					} finally {
						monitor.done();
					}
				}

			};
			return runInUI(runner, rule, shell);
		}
	}

	public static final boolean runInUI(IRunnableWithProgress runnable,
			Shell shell) {
		return runInUI(runnable, null, shell);
	}

	public static final boolean runInUI(IRunnableWithProgress runnable,
			ISchedulingRule rule, Shell shell) {
		if (shell == null) {
			shell = MsgBox.getDefaultShell();
		}
		IRunnableContext context = new ProgressMonitorDialog(shell);
		try {
			PlatformUI.getWorkbench().getProgressService().runInUI(context,
					runnable, rule);
			return true;
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
			String title = LibraryEditResources.errorDialog_title;
			ExtensionManager.getDefaultUserInteractionHandler().getMessenger()
				.showError(title, e.toString(), null, e);
//			LibraryEditPlugin.getDefault().getMsgDialog().displayError(title,
//					e.toString(), e);
		}
		return false;
	}

	public static final boolean runWithProgress(
			final IRunnableWithProgress runnable,
			final IRunnableContext runnableContext, final boolean canCancel,
			final String msg) {
		final MultiStatus status = new MultiStatus(LibraryEditPlugin.INSTANCE
				.getSymbolicName(), IStatus.OK,
				LibraryEditResources.error_reason, null); 

		final IRunnableWithProgress operation = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask(msg, IProgressMonitor.UNKNOWN);
				monitor.subTask(""); //$NON-NLS-1$
				try {
					runnable.run(monitor);
				} catch (RuntimeException e) {
					String msg;
					if (e instanceof MessageException) {
						msg = e.getMessage();
					} else {
						StringWriter strWriter = new StringWriter();
						e.printStackTrace(new PrintWriter(strWriter));
						msg = strWriter.toString();
					}
					status.add(new Status(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(), 0,
							msg, e));
				} finally {
					monitor.done();
				}
			}

		};

		try {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						try {
							runnableContext.run(true, canCancel, operation);
						} catch (Exception e) {
							LibraryEditPlugin.getDefault().getLogger().logError(e);
						}
					}

				});
			} else {
				runnableContext.run(true, canCancel, operation);
			}

			if (!status.isOK()) {
				ExtensionManager.getDefaultUserInteractionHandler()
						.getMessenger().showError(
								LibraryEditResources.errorDialog_title,
								LibraryEditResources.error_msgWithDetails,
								status);
			} else {
				return true;
			}
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			//
			LibraryEditPlugin.getDefault().getLogger().logError(exception);
		}

		return false;
	}

	/**
	 * Return Deliverable in which user would automatically assign a wp into
	 * 
	 * @param activity
	 * @param wp
	 * @return
	 * 
	 */
	public static WorkProductDescriptor getDeliverable(Activity activity,
			WorkProduct wp) {

		// PLEAE DON'T CLEAN UP
		// commented out for now since we shut-off automatic assignment
		// of deliverable for now
		// List deliverableList = new ArrayList();
		// AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
		// .getPBS_ComposedAdapterFactory();
		// // find out all deliverables in visible scope
		// getDeliverablesInScope(adapterFactory, activity, wp,
		// deliverableList);
		// if (deliverableList.size() == 1) {
		// return (WorkProductDescriptor) deliverableList.get(0);
		// }
		// if (deliverableList.size() > 1) {
		// return DeliverableSelection.getSelectedDeliverable(deliverableList,
		// wp);
		// }
		// there are no deliverable to assign
		return null;
	}

	/**
	 * PLEASE DON'T CLEAN UP. This method is currently not used since we
	 * shut-off automatic assignment of deliverable. Get deliverable in scope
	 * 
	 * @param adapterFactory
	 * @param e
	 * @param WorkProductDescriptor
	 * @param deliverableList
	 */
	private static void getDeliverablesInScope(AdapterFactory adapterFactory,
			BreakdownElement e, WorkProduct wp, List deliverableList) {
		// get children for activity
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(e, ITreeItemContentProvider.class);
		Collection children = itemProvider.getChildren(e);
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if ((obj instanceof WorkProductDescriptor)
					&& (ProcessUtil
							.getAssociatedElement((WorkProductDescriptor) obj) instanceof Deliverable)) {
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) obj;

				// get deliverable parts from deliverable
				List parts = ProcessUtil.getAssociatedElementList(wpDesc
						.getDeliverableParts());
				if (parts.contains(wp)) {
					deliverableList.add(obj);
				}
			}
		}

		// get parent
		Object currentParent = itemProvider.getParent(e);
		if (currentParent != null) {
			// go up
			getDeliverablesInScope(adapterFactory,
					(BreakdownElement) currentParent, wp, deliverableList);
		}
	}

	/**
	 * Checks if the given element can be modified. This includes lock check and
	 * edit check.
	 * 
	 * @param element
	 * @param shell
	 * @return
	 */
	public static IStatus checkModify(EObject element, Shell shell) {
		if (TngUtil.isLocked(element)) {
			String msg = MessageFormat
					.format(
							LibraryEditResources.UserInteractionHelper_lockedPlugin,
							new Object[] { UmaUtil.getMethodPlugin(element)
									.getName() }); 
			return new Status(IStatus.ERROR,
					LibraryEditPlugin.INSTANCE.getId(), 0, msg, null);
		}
		return TngUtil.checkEdit((EObject) element, shell);
	}

	/**
	 * Checks for unmodifiable resources.
	 * 
	 * @param modifiedResources
	 *            A collection of resources.
	 * @param shell
	 *            The parent shell.
	 * @return An <code>IStatus</code> object.
	 */
	public static IStatus checkModify(Collection modifiedResources, Shell shell) {
		IAccessController ac = Services.getAccessController();
		if (ac == null) {
			return Status.OK_STATUS;
		}
		Resource[] resources = new Resource[modifiedResources.size()];
		modifiedResources.toArray(resources);
		return ac.checkModify(resources, shell);
	}

	public static IResource getWorkspaceResource(Resource resource) {
		if (!resource.getURI().isFile()) {
			return null;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath path = new Path(resource.getURI().toFileString());
		return workspaceRoot.getFileForLocation(path);
	}

	public static boolean checkOutOfSynch(Collection resources) {
		ArrayList outOfSynchResources = new ArrayList();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			IResource wsResource = getWorkspaceResource(resource);
			if (wsResource != null
					&& !wsResource.isSynchronized(IResource.DEPTH_ZERO)) {
				outOfSynchResources.add(wsResource);
			}
		}
		if (outOfSynchResources.isEmpty()) {
			return true;
		} else {
			MultiStatus multiStatus = new MultiStatus(
					LibraryEditPlugin.INSTANCE.getSymbolicName(), IStatus.OK,
					"", null); //$NON-NLS-1$
			for (Iterator iter = outOfSynchResources.iterator(); iter.hasNext();) {
				IResource wsResource = (IResource) iter.next();
				IStatus status = new Status(IStatus.INFO,
						LibraryEditPlugin.INSTANCE.getSymbolicName(),
						IStatus.OK, wsResource.getLocation().toOSString(), null);
				multiStatus.add(status);
			}
			String title = LibraryEditResources.update_outofsynch_title;
			String msg = LibraryEditResources.update_outofsynch_msg;

			IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
			return uiHandler.selectOne(new int[] {IUserInteractionHandler.ACTION_OK,
					IUserInteractionHandler.ACTION_CANCEL}, title, msg, multiStatus) != IUserInteractionHandler.ACTION_CANCEL;
		}
	}

	/**
	 * Check if change to the given feature of the given featureOwner will
	 * modify the element in opposite feature value
	 * 
	 * @param featureOwner
	 * @param feature
	 * @param element
	 * @return
	 */
	public static boolean checkModifyOpposite(MethodElement featureOwner,
			EStructuralFeature feature, MethodElement element) {
		OppositeFeature oppositeFeature = OppositeFeature
				.getOppositeFeature(feature);
		MultiResourceEObject mrEObj = (MultiResourceEObject) element;
		if (oppositeFeature != null && !oppositeFeature.isMany()) {
			NamedElement oldOppositeFeatureValue = (NamedElement) mrEObj
					.getOppositeFeatureMap().get(oppositeFeature);
			if (oldOppositeFeatureValue != null
					// make sure the element is still in the library and not
					// deleted yet.
					//
					&& (oldOppositeFeatureValue instanceof MethodLibrary || oldOppositeFeatureValue
							.eContainer() != null)
					&& oldOppositeFeatureValue.eResource() != null) {
				// simple reject for 7.0.0 release
				//
				String msg = MessageFormat
						.format(
								LibraryEditResources.UserInteractionHelper_errRelationshipExists,
								new Object[] {
										element.getName(),
										TngUtil
												.getLabelWithPath(oldOppositeFeatureValue),
										featureOwner.getName() });
				Messenger.INSTANCE.showWarning(
						LibraryEditResources.errorDialog_title, msg);
				return false;

				// TODO: uncomment to use this code for 7.0.1 release
				//
				// String title = "Update Relationship";
				// String msg = MessageFormat.format("Adding ''{0}'' to ''{1}''
				// will remove ''{0}'' from ''{2}''. Do you want to continue?"
				// , new Object[] { element.getName(), featureOwner.getName(),
				// oldOppositeFeatureValue.getName() });
				// if(!LibraryEditPlugin.INSTANCE.getMsgDialog().displayConfirmation(title,
				// msg)) {
				// return false;
				// }
				//				
				// IStatus status =
				// UserInteractionHelper.checkModify(oldOppositeFeatureValue,
				// null);
				// if(!status.isOK()) {
				// LibraryEditPlugin.INSTANCE.getMsgDialog().displayError(title,
				// "Cannot update relationship", status);
				// return false;
				// }
			}
		}

		return true;
	}

	/**
	 * Checks if
	 * 
	 * @return
	 */
	public static IStatus checkConfigurationsToUpdate(AddCommand addCommand,
			Shell shell) {
		EObject parent = addCommand.getOwner();
		if (!(parent instanceof MethodPackage)) {
			return Status.OK_STATUS;
		}
		EStructuralFeature feature = addCommand.getFeature();
		if (!(feature instanceof EReference)
				|| !((EReference) feature).isContainment()) {
			return Status.OK_STATUS;
		}

		// check if the configurations that will be updated after this command
		// can be modified
		//
		ArrayList configsToUpdate = new ArrayList();
		MethodPackage parentPkg = (MethodPackage) parent;
		for (Iterator iter = addCommand.getCollection().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodPackage) {
				TngUtil.getConfigurationsToUpdate(parentPkg,
						(MethodPackage) element, configsToUpdate);
			}
		}
		if (!configsToUpdate.isEmpty()) {
			Collection resourcesToCheck = new HashSet();
			for (Iterator iter = configsToUpdate.iterator(); iter.hasNext();) {
				EObject config = (EObject) iter.next();
				Resource resource = config.eResource();
				if (resource != null) {
					resourcesToCheck.add(resource);
				}
			}
			// check affected resources for unmodifiable
			return UserInteractionHelper.checkModify(resourcesToCheck, shell);
		}
		return Status.OK_STATUS;
	}

	public static boolean confirmDeepCopy(Collection activities) {
		if (UserInteractionHelper.canInteract()) {
			// checks if the pattern or activity contains any extending elements
			// and prompts the user if he really
			// wants to deep copy dynamically linked activities warning the
			// process author that he will get a copy
			// of all dynamically linked elements that he needs to maintain
			// separately from now on
			boolean promptNeeded = false;
			for (Iterator iter = activities.iterator(); iter.hasNext();) {
				Object e = (Object) iter.next();
				if (e instanceof Activity
						&& ProcessUtil.hasInherited((Activity) e)) {
					promptNeeded = true;
					break;
				}
			}
			if (promptNeeded) {
				String title = LibraryEditResources.deepCopy_title;
				String msg = LibraryEditResources.deepCopy_promptMsg;
				int ret = ExtensionManager.getDefaultUserInteractionHandler()
					.selectOne(new int[] {
							IUserInteractionHandler.ACTION_YES,
							IUserInteractionHandler.ACTION_NO
					}, title, msg, null);
				if (ret == IUserInteractionHandler.ACTION_NO) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Prompts user to choose configuration for deep copy
	 * 
	 * @param targetProcess
	 * @param adapterFactory
	 * @return
	 * @exception OperationCanceledException
	 *                if user cancelled
	 */
	public static MethodConfiguration chooseDeepCopyConfiguration(
			Process targetProcess, AdapterFactory adapterFactory) {
		IFilter filter = ProcessUtil.getFilter(adapterFactory);
		MethodConfiguration deepCopyConfig = null;
		if (filter instanceof IConfigurator) {
			MethodConfiguration currentConfig = ((IConfigurator) filter)
					.getMethodConfiguration();
			if (currentConfig != null
					&& currentConfig != targetProcess.getDefaultContext()) {
				if (UserInteractionHelper.canInteract()) {
					String msg = LibraryEditResources.ActivityDropCommand_deepCopy_promptConfigurationMsg;
					switch (MsgBox.prompt(msg)) {
					case SWT.YES:
						break;
					case SWT.NO:
						deepCopyConfig = currentConfig;
						break;
					case SWT.CANCEL:
						throw new OperationCanceledException();
					}
				}
			}
		}
		return deepCopyConfig;
	}
}