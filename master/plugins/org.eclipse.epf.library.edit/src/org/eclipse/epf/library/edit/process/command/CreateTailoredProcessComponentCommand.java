package org.eclipse.epf.library.edit.process.command;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.eclipse.epf.common.serviceability.MsgBox;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;

import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.command.UserInput;

import org.eclipse.epf.library.edit.navigator.TailoredProcessesPackageItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessFamily;
import org.eclipse.epf.uma.ProcessLineComponent;

import org.eclipse.epf.uma.ProcessPlanningTemplate;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;


public class CreateTailoredProcessComponentCommand extends CreateChildCommand {

	private static final Collection<EClass> ECLASSES = new HashSet<EClass>();

	private Process process;

	private IStatus status;
	
	private ModelStructure modelStruct;
	
	private MethodPlugin plugin;

	static {
		ECLASSES.add(UmaPackage.eINSTANCE.getMethodPackage());
	}

	public static class CompareByName implements Comparator<MethodElement> {

		public int compare(MethodElement obj1, MethodElement obj2) {
			String name1 = ((MethodElement) obj1).getName();
			String name2 = ((MethodElement) obj2).getName();
			return name1.compareToIgnoreCase(name2);
		}
	}

	public class ConfigValidator implements IValidator {

		public IStatus isValid(Object obj) {
			if (obj == null) {
				String msg = LibraryEditResources.noDefaultConfigError_msg;
				return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
						.getId(), 0, msg, null);
			}
			return Status.OK_STATUS;
		}

		public String isValid(String name) {
			return null;
		}
	}
	/**
	 * Creates a new instance.
	 */
	public CreateTailoredProcessComponentCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Object child, int index,
			Collection<?> selection, Helper helper, MethodPlugin plugin, ModelStructure modelStruct) {
		super(domain, owner, feature, child, index, selection, helper);
		this.plugin = plugin;
		this.modelStruct = modelStruct;
	}

	public boolean canUndo() {
		return false;
	}

	public void execute() {
		MethodLibrary lib = UmaUtil.getMethodLibrary(owner);
		
		Shell shell = MsgBox.getDefaultShell();
		
		// The owner must be updatable.
		//
		status = UserInteractionHelper.checkModify(owner, shell);
		if (!status.isOK()) {
			return;
		}
		
		List configs = lib.getPredefinedConfigurations();
		List methodConfigs = new ArrayList();
		for (Iterator iter = configs.iterator(); iter.hasNext();) {
			Object element = iter.next();
			//Si no es instancia de ProcessFamily, nuestros procesos adaptados no van a pertenecer a ninguna familia - A�ado nuestro objeto a la configuracion
			if (!(element instanceof ProcessFamily)) {
				methodConfigs.add(element);
			}
		}
		//Si no tenemos ning�n m�todo de configuraci�n error - Aqu� tuve una incidencia, pero se resolvio a�adiendo el elemento a la configuracion
		if (methodConfigs.isEmpty()) {
			status = new Status(IStatus.ERROR, LibraryEditPlugin.getDefault().getId(),
								0, LibraryEditResources.noConfigError_msg, null);			
			return;
		}

		TailoredProcessesPackageItemProvider adapter = (TailoredProcessesPackageItemProvider) helper;

		
		ArrayList procClasses = new ArrayList();
		
		MethodPlugin plugin = UmaUtil.getMethodPlugin((Element) owner);//FIXME Coje de plugin la Linea A, en teor�a es asi como deber�a de ser
		//---> Generacion de lineas de procesos (De las que hay ya existentes en el paquete)
		List baseProcList = TngUtil.getAvailableBaseLineProcesses(plugin,
				procClasses);
		//<---
		

		// sort by name
		Collections.sort(methodConfigs, new CompareByName());

		MethodConfiguration[] procCtxs = new MethodConfiguration[methodConfigs
				.size()];
		methodConfigs.toArray(procCtxs);

		Process[] baseProcesses = new Process[baseProcList.size()];
		baseProcList.toArray(baseProcesses);

		final TailoredProcessComponent procComp = (TailoredProcessComponent) child;
		EClass procType = adapter.getProcessType();
		procComp.setTailoredProcess((Process) UmaFactory.eINSTANCE.create(procType));
		
		IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
		List userInputs = new ArrayList();
		IValidator nameValidator = IValidatorFactory.INSTANCE.createNameValidator(
				owner, procComp);
		// name
		UserInput nameInput = new UserInput(LibraryEditResources.nameLabel_text, UserInput.TEXT,
				false, null, null, nameValidator, null);
		userInputs.add(nameInput);
		
		// default configuration
		//
		
		///Recupero la configuracion de la linea de proceso Padre
		ProcessLineComponent auxLine = UmaUtil.getProcessLine(owner);
		UserInput defaultConfigInput = null;
		
		//Por defecto recuperamos la configuracion de la linea, y si no tiene configuracion usaremos las existentes
		if(auxLine != null){
			List configDefault = new ArrayList();
			
			if(auxLine.getProcessLine()!= null){
				if(auxLine.getProcessLine().getDefaultContext()!=null){
					configDefault.add(auxLine.getProcessLine().getDefaultContext());
					methodConfigs = configDefault;
				}
			}	
		}
		defaultConfigInput = new UserInput(LibraryEditResources.defaultConfigLabel_text, UserInput.SELECTION,
				false, methodConfigs, new LabelProvider() {
			public String getText(Object element) {
				if (element instanceof MethodElement) {
					return ((MethodElement) element).getName();
				} else {
					return element.toString();
				}
			}
		}, new ConfigValidator(), null);

		
		
		
		userInputs.add(defaultConfigInput);
		// base process
		UserInput baseProcInput = null;

		
		
		//Se crean variables en la LibraryEditResources de donde se van a editar los textos de las ventanas
		boolean canExecute = false;
		String msg = NLS.bind(LibraryEditResources.CreateTailoredProcessComponentCommand_Message, 
				TngUtil.getTypeText(procType.getName()).toLowerCase());
		

		// request input - Rellena la ventana de creaci�n con informaci�n
		if (uiHandler.requestInput(LibraryEditResources.newTailoredProcessComponentDialog_title, 
				msg, userInputs)) {
			String name = (String) nameInput.getInput();
			procComp.setName(name);
			process = procComp.getTailoredProcess();
			process.setName(name);
			process.setPresentationName(name);
			process.setDefaultContext((MethodConfiguration) defaultConfigInput.getInput());
			if(baseProcInput != null) {
				List list = (List) baseProcInput.getInput();
				if(!list.isEmpty()) {
					((ProcessPlanningTemplate)procComp).getBasedOnProcesses().addAll(list);
				}
			}
			canExecute = true;
		}
		if (canExecute) {
			// create process component need to update the configuration that
			// has been selected as
			// default context of its process. Check if the configuration file
			// is updatable
			//
			status = UserInteractionHelper.checkModify(procComp.getTailoredProcess()
					.getDefaultContext(), shell);
			if (!status.isOK()) {
				return;
			}
			
			super.execute();

			Command cmd = getCommand();
			if (cmd instanceof MethodElementAddCommand) {
				IStatus status = ((MethodElementAddCommand) cmd).getStatus();
				if (status != null && !status.isOK()) {
					this.status = status;
					return;
				}
			}

			final MethodConfiguration procCtx = process.getDefaultContext();
			
			// need to add the parent packages and plugin into the configuration
			// as well
			// New process in new plug-in not automatically visible in
			// configuration view
			List pkgs = procCtx.getMethodPackageSelection();
			for (EObject obj = procComp; obj != null; obj = obj.eContainer()) {
				if (obj instanceof MethodPackage) {
					pkgs.add(obj);
				}
			}
			procCtx.getMethodPluginSelection().add(plugin);
			process.getValidContext().add(procCtx);
			process.setPresentation(ContentDescriptionFactory
					.createContentDescription(process));

			Runnable runnable = new Runnable() {
				public void run() {
					// save the resource of parent TailoredProcessesPackage
					Resource resource = ((EObject) owner).eResource();
					if (resource != null) {
						ILibraryPersister.FailSafeMethodLibraryPersister persister = Services.getDefaultLibraryPersister()
								.getFailSafePersister();
						try {
							// save the resource of newly created
							// ProcessComponent again after creating process's
							// presentation
							persister.save(procComp.eResource());

							persister.save(resource);

							// save the resource of the process's default
							// context
							persister.save(procCtx.eResource());

							persister.commit();
							
							// create new diagram file
							//
							
						} catch (Exception e) {
							try {
								persister.rollback();
							} catch (Exception ex) {
								LibraryEditPlugin.INSTANCE.log(ex);
								LibraryEditPlugin.INSTANCE.log(e);
							}
							
							status = Status.CANCEL_STATUS;
							throw new MessageException(
									NLS
											.bind(
													LibraryEditResources.saveProcessError_reason,
													procComp.getName()), e);
						}
					}
				}
			};

			UserInteractionHelper
					.runWithProgress(
							runnable,
							MessageFormat
									.format(
											LibraryEditResources.creatingProcessComponentTask_name,
											new Object[] { procComp.getName() })); 
		}
	}

	public void redo() {
		super.redo();
		TailoredProcessComponent procComp = (TailoredProcessComponent) child;
		MethodConfiguration procCtx = process.getDefaultContext();
		if (procCtx instanceof MethodConfiguration) {
			((MethodConfiguration) procCtx).getMethodPackageSelection().add(
					procComp);
		}
	}

	/**
	 * @see org.eclipse.emf.edit.command.CreateChildCommand#undo()
	 */
	public void undo() {
		MethodConfiguration procCtx = process.getDefaultContext();
		if (procCtx instanceof MethodConfiguration) {
			((MethodConfiguration) procCtx).getMethodPackageSelection().remove(
					child);
		}
		super.undo();
	}

	public IStatus getStatus() {
		return status;
	}
}