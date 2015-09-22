package org.eclipse.epf.authoring.ui.vepfpersistence;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epf.uma.util.TailoredProcessLogic;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TailoredCoreProcessPackage;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VarElement;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variant;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.TailoredProcessLogic;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Clase que se encarga de la lógica de persistencia
 * @author flucas
 *
 */
public class PersistenceLogic {

	public PersistenceLogic(){
		
	}
	
	/**
	 * Metodo que hace el Loading de la persistencia
	 * @param e1
	 * @param variantSelected
	 */
	public static void handlePersistenceException(Exception e1, Object variantSelected) {
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
	
	public static ILibraryPersister.FailSafeMethodLibraryPersister getPersister(Object variantSelected) {
		return LibraryServiceUtil.getPersisterFor(((EObject) variantSelected).eResource())
					.getFailSafePersister();
	}
	
	/**
	 * Metodo que se encarga de guardar un elemento de tipo TailoredProcessComponent
	 * @param tailoredProcess
	 */
	public static void saveTailoredProcess(final TailoredProcessComponent tailoredProcess){
		Runnable runnable = new Runnable() {
			
			public void run() {
				// Persistencia
				//
				ILibraryPersister.FailSafeMethodLibraryPersister persister = getPersister(tailoredProcess);
				try {
					try {
						persister.save(((EObject) tailoredProcess).eResource());
						persister.commit();		
					} catch (Exception e1) {		
						try {
							persister.rollback();
						}
						catch(Exception e) {
							AuthoringUIPlugin.getDefault().getLogger().logError(e);
						}
						handlePersistenceException(e1, tailoredProcess);
					}
				}
				catch(RuntimeException e) {				
					throw e;
				}
				
				// cambiamos el diagrama si es un proceso
				if (tailoredProcess instanceof ProcessComponent) {
					Process proc = ((ProcessComponent) tailoredProcess).getProcess();	
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
				AuthoringUIResources.saving_TailoredProcess_element);
	}
	
	/**
	 * Hace una copia de todos los elementos de un collection a un nuevo componente de proceso
	 * @param elementsToCopy
	 * @param processComponentNew
	 */
	public static void copyProcessPackages(Collection elementsToCopy, ProcessComponent processComponentNew) {
		ArrayList procPackages = null;
		if (procPackages == null) {
				boolean showWarning = false;
				procPackages = new ArrayList();
				ArrayList<Activity> copiedActivities = new ArrayList();
				ArrayList<ProcessElement> copiedProcessElements = new ArrayList();
				
				
				if (elementsToCopy != null
						&& elementsToCopy.size() > 0) {
					for (Iterator iter = elementsToCopy.iterator(); iter
							.hasNext();) {
						Object element = iter.next();
						
						if (element instanceof Activity) {
							Activity orig = (Activity) element;
							if (orig != null) {
								if (orig.eContainer() != null) {
									procPackages.add(orig.eContainer());
									copiedActivities.add((Activity) element);
								}
							} else {
								showWarning = true;
							}
						}
						else if(element instanceof ProcessElement){
							ProcessElement orig = (ProcessElement) element;
							if(orig != null){
								copiedProcessElements.add((ProcessElement) element);
							}
						}
					}
				}

				//Elementos de procesos
				if(!copiedProcessElements.isEmpty()){
					
					 AdapterFactoryEditingDomain domain = new
					 AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
					 , new BasicCommandStack());
					 
					Command command = CopyCommand.create(domain, copiedProcessElements);
					try {
						command.execute();
						
						Collection collection = new ArrayList(elementsToCopy);
						Collection col = command.getResult();
						ArrayList<ProcessPackage> procPkgCopies = new ArrayList<ProcessPackage>(col);

						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							Object element = (Object) iter.next();
							int index = copiedProcessElements.indexOf(element);
							
							if(index != -1) {//Es de activity
								ProcessElement copy = (ProcessElement) procPkgCopies.get(index);
								element = TailoredProcessLogic.getProcessElementCopy(copy);
								if(element != null) {
									if(element instanceof BreakdownElement){
										BreakdownElement breakDownElement = (BreakdownElement) element;
										breakDownElement.setSuperActivities(processComponentNew.getProcess());
									}
								}
							}
						}
						processComponentNew.getProcessElements().addAll(procPkgCopies);
						
					} finally {
					}
				}
				//Elementos de actividades
				if (!procPackages.isEmpty()) {

					 AdapterFactoryEditingDomain domain = new
					 AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
					 , new BasicCommandStack());
					 
					Command command = CopyCommand.create(domain, procPackages);
					try {
						command.execute();	
						Collection collection = new ArrayList(elementsToCopy);
						Collection col = command.getResult();
						ArrayList<ProcessPackage> procPkgCopies = new ArrayList<ProcessPackage>(col);

						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							Object element = (Object) iter.next();
							int index = copiedActivities.indexOf(element);
							
							if(index != -1) {//Es de activity
								ProcessPackage copy = procPkgCopies.get(index);
								element = TailoredProcessLogic.getActivityCopy(copy);
								if(element != null) {
									if(element instanceof BreakdownElement){
										BreakdownElement breakDownElement = (BreakdownElement) element;
										breakDownElement.setSuperActivities(processComponentNew.getProcess());
									}
								}
							}
						}
						processComponentNew.getChildPackages().addAll(procPkgCopies);
						
					} finally {
					}
				}
		}
	}
	
	/**
	 * Hace la copia de un collection de elements hacia un processComponent
	 * @param elementsToCopy
	 * @param processComponentNew
	 */
	private void copyProcess(Collection elementsToCopy, ProcessComponent processComponentNew){
		ArrayList procPackages = null;
		if (procPackages == null) {
			
			 AdapterFactoryEditingDomain domain = new
			 AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
			 , new BasicCommandStack());
			 
			 Command command = CopyCommand.create(domain, elementsToCopy);
			
				try{
					boolean showWarning = false;
					procPackages = new ArrayList();
					ArrayList<Activity> copiedActivities = new ArrayList();
					ArrayList<ProcessElement> copiedProcessElements = new ArrayList();
					
					for(Iterator iter = elementsToCopy.iterator(); iter.hasNext();){
						Object elementObj = iter.next();
						
						
						if(elementObj instanceof ProcessPackage){
							ProcessPackage pkg = (ProcessPackage) elementObj;
							Object element = pkg.getProcessElements().get(0);
							
							if (element instanceof Activity) {
								Activity orig = (Activity) element;
								if (orig != null) {
									if (orig.eContainer() != null) {
										procPackages.add(orig.eContainer());
										copiedActivities.add((Activity) element);
									}
								} else {
									showWarning = true;
								}
							}
						}
						else if(elementObj instanceof ProcessElement){
							ProcessElement orig = (ProcessElement) elementObj;
							if(orig != null){
								copiedProcessElements.add((ProcessElement) elementObj);
							}
						}
					}
					

					command.execute();
					Collection collection = new ArrayList(elementsToCopy);
//					Collection elements = elementsToCopy;						
//					elements.clear();	
					Collection col = command.getResult();
					
					ArrayList<ProcessPackage> procPkgCopiesActivity = new ArrayList<ProcessPackage>(col);
					ArrayList<ProcessPackage> procPkgCopiesElement= new ArrayList<ProcessPackage>(col);
					
					for(Iterator iter = procPkgCopiesActivity.iterator(); iter.hasNext();){
							Object element = iter.next();
							if( !(element instanceof ProcessPackage)){
								iter.remove();
							}
					}
					for(Iterator iter = procPkgCopiesElement.iterator(); iter.hasNext();){
						Object element = iter.next();
						if( element instanceof ProcessPackage ){
							iter.remove();
						}
					}

					//Elementos de actividades
					if (!procPackages.isEmpty()) {
	
							for (Iterator iter = collection.iterator(); iter
									.hasNext();) {
								
								Object auxElement = (Object) iter.next();
								Object element = null;
								if( auxElement instanceof ProcessPackage){
									ProcessPackage processPkg = (ProcessPackage) auxElement;
									element = processPkg.getProcessElements().get(0);
								}
								else{
									element = auxElement;
								}
								
								int index = copiedActivities.indexOf(element);
								
								if(index != -1) {//Es de activity
									ProcessPackage copy = procPkgCopiesActivity.get(index);
									element = TailoredProcessLogic.getActivityCopy(copy);
									
									if(element != null){
										if(element instanceof BreakdownElement){
											BreakdownElement breakDownElement = (BreakdownElement) element;
											breakDownElement.setSuperActivities(processComponentNew.getProcess());
										}
									}

								}
							}
							processComponentNew.getChildPackages().addAll(procPkgCopiesActivity);
					}
					//Elementos de procesos
					if(!copiedProcessElements.isEmpty()){
							for (Iterator iter = collection.iterator(); iter
									.hasNext();) {
								Object element = (Object) iter.next();
								int index = copiedProcessElements.indexOf(element);
								
								if(index != -1) {//Es de activity
									ProcessElement copy = (ProcessElement) procPkgCopiesElement.get(index);
									element = TailoredProcessLogic.getProcessElementCopy(copy);
									
									if(element != null){
										if(element instanceof BreakdownElement){
											BreakdownElement breakDownElement = (BreakdownElement) element;
											breakDownElement.setSuperActivities(processComponentNew.getProcess());
										}
									}
								}
							}
							processComponentNew.getProcessElements().addAll(procPkgCopiesElement);
					}
			}finally{
				if(command != null) {
					command.dispose();
				}
			}
		}
	}
	
	/***
	 * Metodo que genera un elemento completo SPEM a partir de una variante y un punto de variacion
	 * @param copyVarPoint
	 * @param variant
	 * @return
	 */
	public static Activity getCompletedElement(ProcessPackage copyVarPoint, Variant variant){
		
		Collection auxChilds = new ArrayList();
		
		if (copyVarPoint instanceof ProcessComponent) {
			Activity actCopy = ((ProcessComponent) copyVarPoint)
					.getProcess();
			// copia datos del proceso Package anterior al nuevo
			ProcessPackage pkgCopy = UmaFactory.eINSTANCE
					.createProcessPackage();
			pkgCopy.setName(actCopy.getName());
			pkgCopy.getProcessElements().add(actCopy);
			pkgCopy.getProcessElements().addAll(
					copyVarPoint.getProcessElements());
			pkgCopy.getDiagrams()
					.addAll(copyVarPoint.getDiagrams());
			pkgCopy.getChildPackages().addAll(
					copyVarPoint.getChildPackages());

			return actCopy;
		} else {
			Activity newActivity = null;
		
			for (Iterator iterator = copyVarPoint.getProcessElements().iterator(); iterator
					.hasNext();) {
				Object element = iterator.next();
				if ((element instanceof Activity)) {
					
					Activity varActivity = null;
					
					/*Intanciación de tipo concreto (Atendiendo al punto de variación)*/
					if(element instanceof Phase){
						varActivity = (Phase) variant;
						newActivity = UmaFactory.eINSTANCE.createPhase();
					}
					else if(element instanceof Iteration){
						varActivity = (Iteration) variant;
						newActivity = UmaFactory.eINSTANCE.createIteration();
					}
					else if(element instanceof Activity){
						varActivity = (Activity) variant;
						newActivity = UmaFactory.eINSTANCE.createActivity();
					}
					
					TailoredProcessLogic.fillActivity(newActivity, varActivity);
					TailoredProcessLogic.fillWorkDefinition(newActivity, varActivity);
					TailoredProcessLogic.fillWorkBreakDownElement(newActivity, varActivity);
					TailoredProcessLogic.fillBreakDownElement(newActivity, varActivity);
					TailoredProcessLogic.fillProcessElement(newActivity, varActivity);
					TailoredProcessLogic.fillDescribableElement(newActivity, varActivity);
					TailoredProcessLogic.fillMethodElement(newActivity, varActivity);
					
					
					auxChilds.add(newActivity);
					iterator.remove();
				}
			}
			copyVarPoint.getProcessElements().addAll(auxChilds);
			return newActivity;
		}
		
	}
	
	/**
	 * Metodo que refresca la lista de ocupaciones para que no haya elementos con referencias iguales (refresca para que sean elementos únicos)
	 * @param occupationList
	 * @return
	 */
	public static Collection filterReferencesOccupationList(Collection occupationList){
		Collection newList = new ArrayList();
		
		Collection collectionVarPoints = new ArrayList();
		Collection collectionVariants = new ArrayList();
		
		for(Iterator iterator = occupationList.iterator(); iterator.hasNext();){
			Occupation occTarget = (Occupation) iterator.next();
			Occupation occNew = UmaFactory.eINSTANCE.createOccupation();//El objeto nuevo
			
			
			VarPoint vpTarget = occTarget.getEsocupado();
			Variant varTarget = occTarget.getOcupadopor();
			if((vpTarget instanceof Activity) && (varTarget instanceof Activity)){
				ProcessPackage vpNewFather = (ProcessPackage) copyObject(vpTarget.eContainer());
				ProcessPackage varNewFather = (ProcessPackage) copyObject(varTarget.eContainer());
				
				occNew.setEsocupado((VarPoint) vpNewFather.getProcessElements().get(0));
				occNew.setOcupadopor((Variant) varNewFather.getProcessElements().get(0));
				newList.add(occNew);
			}
			else{
				occNew.setEsocupado((VarPoint) copyObject(vpTarget));
				occNew.setOcupadopor((Variant) copyObject(varTarget));
				newList.add(occNew);
			}
		}
		return newList;
	}
	
	/***
	 * Metodo que crea los componentes completos a partir de las variaciones
	 * @param processComponentNew
	 * @param variationPkg
	 */
	public static void createCompleteObjectsSPEM(
			ProcessComponent processComponentNew, VariationsPackage variationPkg) {
		
		if(variationPkg != null && variationPkg.getVariations().size() > 0){
			Collection variations = variationPkg.getVariations();
			
			PersistenceLogic.copyCompletedObjects(processComponentNew, variations);//Añadimos al paquete
		}
			
	}
	
	/**
	 * Metodo que construye lo componentes completos y los copia al proceso (Para evitar eliminar la variación) (ACTIVITY ELEMENTS)
	 * @param processComponentNew
	 * @param variations
	 */
	public static void copyCompletedObjects(ProcessComponent processComponentNew, Collection variations){
		ArrayList procPackages = null;
		if (procPackages == null) {
				boolean showWarning = false;
				procPackages = new ArrayList();
				ArrayList<Activity> copiedActivities = new ArrayList();
				
				ArrayList copiedVarPointsPElement = new ArrayList();
				ArrayList copiedVariantsPElement = new ArrayList();
				
				variations = filterReferencesOccupationList(variations);
				
				if (variations != null
						&& variations.size() > 0) {
					for (Iterator iter = variations.iterator(); iter
							.hasNext();) {
						Object element = iter.next();
						if(element instanceof Occupation){
							Occupation occuOrig = (Occupation) element;
							VarPoint varPoint = occuOrig.getEsocupado();
							Variant variant = occuOrig.getOcupadopor();
							
							//Del VarPoint se extrae el tipo
							
							//Actividad
							//
							if (varPoint instanceof Activity) {
								Activity orig = (Activity) varPoint;
								if (orig != null) {
									if (orig.eContainer() != null) {
										//Si el paquete esta hay que crear una copia del objeto
										if(procPackages.contains(orig.eContainer())){
											

										}
										else{
											procPackages.add(orig.eContainer());
											copiedActivities.add((Activity) varPoint);
										}

									}
								} else {
									showWarning = true;
								}
							}
							//ProcessElement
							//
							else if(varPoint instanceof ProcessElement) {
								ProcessElement orig = (ProcessElement) varPoint;
								if(orig != null){
									
										copiedVariantsPElement.add(variant);
										copiedVarPointsPElement.add(varPoint);
									
								}
							}
						}
						

					}
				}
				//Objectos de Actividad (ACTIVITY OBJECTS)
				if (!procPackages.isEmpty()) {

					 AdapterFactoryEditingDomain domain = new
					 AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
					 , new BasicCommandStack());
					 
					Command command = CopyCommand.create(domain, procPackages);
					try {
						command.execute();
						Collection collection = new ArrayList(variations);
						Collection elements = new ArrayList();					
						elements.clear();	
						Collection col = command.getResult();
						ArrayList<ProcessPackage> procPkgCopies = new ArrayList<ProcessPackage>(col);

						
							for (Iterator iter = collection.iterator(); iter
									.hasNext();) {
								
								Occupation occu = (Occupation) iter.next();
								
								
								Object element = occu.getEsocupado();//Pto variación
								Variant variant = occu.getOcupadopor();//Variante
								
								int index = copiedActivities.indexOf(element);
								
								//Es un elemento que hereda de actividad
								if(index != -1) {//Es de activity -> Construimos el objeto
									ProcessPackage copy = procPkgCopies.get(index);
									element = getCompletedElement(copy, variant);
									if(element != null) {
										elements.add(element);
										
										if(element instanceof BreakdownElement){
											BreakdownElement breakDownElement = (BreakdownElement) element;
											breakDownElement.setSuperActivities(processComponentNew.getProcess());
										}
									}
								}

							}
							processComponentNew.getChildPackages().addAll(procPkgCopies);
							
						} finally {
							if (command != null) {
								command.dispose();
							}
						}
				}
				//Objetos de processElements (PROCESS ELEMENTS)
				if(!copiedVariantsPElement.isEmpty()){
					 AdapterFactoryEditingDomain domain = new
					 AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
					 , new BasicCommandStack());
					 
					Command command = CopyCommand.create(domain, copiedVariantsPElement);
					try {
						command.execute();
						Collection processElements = new ArrayList();
						Collection collection = new ArrayList(variations);
						Collection elements = new ArrayList();					
						elements.clear();	
						Collection col = command.getResult();
						ArrayList<Variant> procPkgCopies = new ArrayList<Variant>(col);
						

						
							for (Iterator iter = collection.iterator(); iter
									.hasNext();) {
								
								Occupation occu = (Occupation) iter.next();
								
								
								VarPoint varPoint = occu.getEsocupado();
								Variant variant = occu.getOcupadopor();
								Object element = new Object();
								
								int index = copiedVarPointsPElement.indexOf(varPoint);
								
								//Si esta la variante la retornamos
								if(index != -1) {//Es de activity -> Construimos el objeto
									Variant variantToCopy = (Variant) procPkgCopies.get(index);
									element = getCompletedProcessElement(variantToCopy, varPoint);
									if(element != null) {
										elements.add(element);
										
										if(element instanceof BreakdownElement){		
											BreakdownElement breakDownElement = (BreakdownElement) element;
											breakDownElement.setSuperActivities(processComponentNew.getProcess());
										}
									}
								}

							}
							processComponentNew.getProcessElements().addAll(elements);
							
						} finally {
							if (command != null) {
								command.dispose();
							}
						}
				}
		}
	}
	
	
	/**
	 * Metodo para copiar un objeto Process Element, se encarga de setear los valores correspondientes
	 * @param variantToCopy
	 * @param varPoint
	 * @return
	 */
	public static ProcessElement getCompletedProcessElement(Variant variantToCopy, VarPoint varPoint){
		
		
			
			/*Intanciación del tipo concreto -- damos los datos de acuerdo al tipo --- Uma para consultar atributos*/
			if(varPoint instanceof RoleDescriptor){
				RoleDescriptor newElement = UmaFactory.eINSTANCE.createRoleDescriptor();//El objeto nuevo
				RoleDescriptor roleVariant = (RoleDescriptor) variantToCopy;//El que tiene la informacion
				
				//RoleDescriptor
				newElement.setRole(roleVariant.getRole());
				newElement.getModifies().addAll(roleVariant.getModifies());
				newElement.getResponsibleFor().addAll(roleVariant.getResponsibleFor());
				
				
				TailoredProcessLogic.fillDescriptorFields(newElement, roleVariant);
				TailoredProcessLogic.fillBreakDownElement(newElement, roleVariant);
				TailoredProcessLogic.fillProcessElement(newElement, roleVariant);
				TailoredProcessLogic.fillDescribableElement(newElement, roleVariant);
				TailoredProcessLogic.fillMethodElement(newElement, roleVariant);
				
				return newElement;
				
			}
			else if(varPoint instanceof TaskDescriptor){
				
				TaskDescriptor newElement  = UmaFactory.eINSTANCE.createTaskDescriptor();//El objeto nuevo			
				TaskDescriptor taskVariant = (TaskDescriptor) variantToCopy;//El que tiene la informacion
				
				//TaskDescriptor
				newElement.setTask(taskVariant.getTask());
				newElement.getAdditionallyPerformedBy().addAll(taskVariant.getAdditionallyPerformedBy());
				newElement.getAssistedBy().addAll(taskVariant.getAssistedBy());
				newElement.getExternalInput().addAll(taskVariant.getExternalInput());
				newElement.getMandatoryInput().addAll(taskVariant.getMandatoryInput());
				newElement.getOptionalInput().addAll(taskVariant.getOptionalInput());
				newElement.getOutput().addAll(taskVariant.getOutput());
				newElement.setPerformedPrimarilyBy(taskVariant.getPerformedPrimarilyBy());
				newElement.getSelectedSteps().addAll(taskVariant.getSelectedSteps());
				
				//Herencia
				TailoredProcessLogic.fillDescriptorFields(newElement, taskVariant);
				TailoredProcessLogic.fillBreakDownElement(newElement, taskVariant);
				TailoredProcessLogic.fillProcessElement(newElement, taskVariant);
				TailoredProcessLogic.fillDescribableElement(newElement, taskVariant);
				TailoredProcessLogic.fillMethodElement(newElement, taskVariant);
				TailoredProcessLogic.fillWorkBreakDownElement(newElement, taskVariant);
				
				return newElement;
			}
			else if(varPoint instanceof WorkProductDescriptor){
				
				WorkProductDescriptor newElement = UmaFactory.eINSTANCE.createWorkProductDescriptor();
				WorkProductDescriptor workVariant = (WorkProductDescriptor) variantToCopy;
				
				//WorkProductDescriptor
				newElement.setActivityEntryState(workVariant.getActivityEntryState());
				newElement.setActivityExitState(workVariant.getActivityExitState());
				newElement.setWorkProduct(workVariant.getWorkProduct());
				newElement.getImpactedBy().addAll(workVariant.getImpactedBy());
				newElement.getImpacts().addAll(workVariant.getImpacts());
				newElement.getDeliverableParts().addAll(workVariant.getDeliverableParts());
				
				//Herencia
				TailoredProcessLogic.fillDescriptorFields(newElement, workVariant);
				TailoredProcessLogic.fillBreakDownElement(newElement, workVariant);
				TailoredProcessLogic.fillProcessElement(newElement, workVariant);
				TailoredProcessLogic.fillDescribableElement(newElement, workVariant);
				TailoredProcessLogic.fillMethodElement(newElement, workVariant);
				
				return newElement;
			}
			else if(varPoint instanceof Milestone){
				
				Milestone newElement = UmaFactory.eINSTANCE.createMilestone();
				Milestone milestoneVariant = (Milestone) variantToCopy;
				
				//Milestone
				
				//Herencia
				TailoredProcessLogic.fillWorkBreakDownElement(newElement, milestoneVariant);
				TailoredProcessLogic.fillBreakDownElement(newElement, milestoneVariant);
				TailoredProcessLogic.fillProcessElement(newElement, milestoneVariant);
				TailoredProcessLogic.fillDescribableElement(newElement, milestoneVariant);
				TailoredProcessLogic.fillMethodElement(newElement, milestoneVariant);
				
				return newElement;
				
			}
			else if(varPoint instanceof TeamProfile){
				
				TeamProfile newElement = UmaFactory.eINSTANCE.createTeamProfile();
				TeamProfile teamProfileVariant = (TeamProfile) variantToCopy;
				
				//TeamProfile
				newElement.getTeamRoles().addAll(teamProfileVariant.getTeamRoles());
				newElement.setSuperTeam(teamProfileVariant.getSuperTeam());
				newElement.getSubTeam().addAll(teamProfileVariant.getSubTeam());
				
				//Herencia
				TailoredProcessLogic.fillBreakDownElement(newElement, teamProfileVariant);
				TailoredProcessLogic.fillProcessElement(newElement, teamProfileVariant);
				TailoredProcessLogic.fillDescribableElement(newElement, teamProfileVariant);
				TailoredProcessLogic.fillMethodElement(newElement, teamProfileVariant);
				
				return newElement;
			}
		
		return null;
	}
	
	/**
	 * Metodo que copia un objeto y lo devuelve en forma de colección
	 * @param object
	 * @return
	 */
	public static EObject copyObject(EObject object){
		 AdapterFactoryEditingDomain domain = new
		 AdapterFactoryEditingDomain(TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory()
		 , new BasicCommandStack());
		Command command = CopyCommand.create(domain, object);
		try{
			command.execute();
			Collection col = command.getResult();
			return (EObject) col.iterator().next();
		}
		finally {
			if (command != null) {
				command.dispose();
			}
		}

	}
	
	/**
	 * Metodo que guarda las variaciones generadas
	 * @param variationsPackage
	 */
	public static void saveVariations(final VariationsPackage variationsPackage){
		Runnable runnable = new Runnable() {
			
			public void run() {
				// Persistencia
				//
				ILibraryPersister.FailSafeMethodLibraryPersister persister = PersistenceLogic.getPersister(variationsPackage);
				try {
					try {
						persister.save(((EObject) variationsPackage).eResource());
						persister.commit();		
					} catch (Exception e1) {		
						try {
							persister.rollback();
						}
						catch(Exception e) {
							AuthoringUIPlugin.getDefault().getLogger().logError(e);
						}
						PersistenceLogic.handlePersistenceException(e1, variationsPackage);
					}
				}
				catch(RuntimeException e) {				
					throw e;
				}
				
				// cambiamos el diagrama si es un proceso
				if (variationsPackage instanceof ProcessComponent) {
					Process proc = ((ProcessComponent) variationsPackage).getProcess();	
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
				AuthoringUIResources.saving_TailoredProcess_element);//FIXME CAMBIAR MENSAJE
	}
	
	
	/**
	 * Metodo que guarda el proceso generado 
	 * @param processComponent
	 */
	public static void saveProcess(final ProcessComponent processComponent){
		Runnable runnable = new Runnable() {
			
			public void run() {
				// Persistencia
				//
				ILibraryPersister.FailSafeMethodLibraryPersister persister = PersistenceLogic.getPersister(processComponent);
				try {
					try {
						persister.save(((EObject) processComponent).eResource());
						persister.commit();		
					} catch (Exception e1) {		
						try {
							persister.rollback();
						}
						catch(Exception e) {
							AuthoringUIPlugin.getDefault().getLogger().logError(e);
						}
						PersistenceLogic.handlePersistenceException(e1, processComponent);
					}
				}
				catch(RuntimeException e) {				
					throw e;
				}
				
				// cambiamos el diagrama si es un proceso
				if (processComponent instanceof ProcessComponent) {
					Process proc = ((ProcessComponent) processComponent).getProcess();	
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
				AuthoringUIResources.saving_TailoredProcess_element);//FIXME CAMBIAR MENSAJE
	}
	
	/**
	 * Metodo que crea un proceso nuevo a partir de los datos del original (processObj)
	 * @param processObj
	 * @param pkg
	 * @param variationPkg
	 * @param description
	 * @param modelStruct
	 * @param adaptedProcess
	 * @param tailoredProcess
	 */
	public static void createProcess(ProcessComponent processObj, TailoredCoreProcessPackage pkg, VariationsPackage variationPkg, String description, 
			ModelStructure modelStruct, String adaptedProcess, TailoredProcessComponent tailoredProcess){
		
			//Objeto original
			ProcessComponent processOrig = (ProcessComponent) processObj;
			String name = processOrig.getName()+" "+adaptedProcess;
			
			if(!UmaUtil.checkProcess(name, pkg)){//Si el proceso no existe (Nunca debería de existir ya que no podemos adaptarlo si hay procesos)
					//Objeto copia
					ProcessComponent processComponentNew = UmaFactory.eINSTANCE.createProcessComponent();
					Process processElement;
				
					//Seteamos variables
					MethodPlugin plugin = UmaUtil.getMethodPlugin((Element) pkg);
					
					//Si es Capabilitty
					if(pkg.getName().equals(modelStruct.tailoredCapabilityPatternPath[0].toString())){
						processComponentNew.setProcess((Process) UmaFactory.eINSTANCE.createCapabilityPattern());
					}//Si es delivery
					else{
						processComponentNew.setProcess((Process) UmaFactory.eINSTANCE.createDeliveryProcess());
					}
					
					//Proceso real
					processElement = processComponentNew.getProcess();
					processElement.setDefaultContext(tailoredProcess.getTailoredProcess().getDefaultContext());
					processElement.setName(name);
					processElement.setPresentationName(name);
					processElement.setBriefDescription(description);//Descripcion del proceso adaptado
					
					//Encapsulado
					processComponentNew.setAuthors(processOrig.getName());
					processComponentNew.setVersion(processOrig.getVersion());
					processComponentNew.setName(name);
					processComponentNew.setBriefDescription(processOrig.getBriefDescription());
					
					//Llenado del proceso con los objetos SPEM
					Collection elementsToCopy = new ArrayList();
					
					//Elementos hijos
					for(Iterator iterator = processOrig.getChildPackages().iterator(); iterator.hasNext();){
						EObject elementObj = (EObject) iterator.next();
						//Si no es instancia de un elemento vSPEM es que lo es de SPEM
						if((elementObj instanceof ProcessPackage)){
							ProcessPackage elementCapsule = (ProcessPackage) elementObj;		
							if(!(elementCapsule.getProcessElements().get(0) instanceof VarElement)){
								//Tenemos que hacer una copia de cada elemento
								if(elementCapsule.getProcessElements().get(0) instanceof Activity){
									Activity activity = (Activity) elementCapsule.getProcessElements().get(0);
									elementsToCopy.add(activity);
//									elementsToCopy.add(elementCapsule);
								}
								
							}
						}
					}
					//Elementos procesos
					for(Iterator iterator = processOrig.getProcessElements().iterator(); iterator.hasNext();){
 						EObject elementObj = (EObject) iterator.next();
						//Instancia de elemento de proceso y Si no es instancia de un elemento vSPEM es que lo es de SPEM
						if((elementObj instanceof ProcessElement) && !(elementObj instanceof VarElement)){
							ProcessElement processE = (ProcessElement) elementObj;
							elementsToCopy.add(processE);
						}
					}
					TailoredProcessLogic.prepareAddList(elementsToCopy);	
					PersistenceLogic.copyProcessPackages(elementsToCopy, processComponentNew);
					
					//(vSPEM Occupations -> SPEM Elements) Generamos los objetos COMPLETOS a partir de las ocupaciones
					//
					PersistenceLogic.createCompleteObjectsSPEM(processComponentNew, variationPkg);
					
					//Persistencia del proceso
					//
					pkg.getChildPackages().add(processComponentNew);
					PersistenceLogic.saveProcess(processComponentNew);
			}
//			else{//Si el proceso existe simplemente lo modificamos con los nuevos elementos
//				
//				ProcessComponent processAdapted = UmaUtil.getProcess(name, pkg);
//				processAdapted.getChildPackages().clear();//Limpiamos hijos
//				processAdapted.getProcessElements().clear();//Limpiamos los procesos de elementos
//				pkg.getChildPackages().clear();
//				
//				if(processAdapted != null){
//					//Editamos el proceso con todo lo nuevo de este
//					Process processElement;
//					
//					//Proceso real
//					processElement = processAdapted.getProcess();
//					processElement.setDefaultContext(tailoredProcess.getTailoredProcess().getDefaultContext());
//					processElement.setName(name);
//					processElement.setPresentationName(name);
//					processElement.setBriefDescription(processOrig.getBriefDescription());
//					
//					//Encapsulado
//					processAdapted.setAuthors(processOrig.getName());
//					processAdapted.setVersion(processOrig.getVersion());
//					processAdapted.setName(name);
//					processAdapted.setBriefDescription(processOrig.getBriefDescription());
//					
//					
//					//Llenado del proceso con los objetos SPEM
//					Collection elementsToCopy = new ArrayList();
//					
//					//Elementos hijos puros de SPEM a copiar
//					for(Iterator iterator = processOrig.getChildPackages().iterator(); iterator.hasNext();){
//						EObject elementObj = (EObject) iterator.next();
//						//Si no es instancia de un elemento vSPEM es que lo es de SPEM
//						if((elementObj instanceof ProcessPackage)){
//							ProcessPackage elementCapsule = (ProcessPackage) elementObj;		
//							if(!(elementCapsule.getProcessElements().get(0) instanceof VarElement)){
//								//Tenemos que hacer una copia de cada elemento
//								if(elementCapsule.getProcessElements().get(0) instanceof Activity){
//									Activity activity = (Activity) elementCapsule.getProcessElements().get(0);
//									elementsToCopy.add(activity);
//								}
//								
//							}
//						}
//					}
//					//Elementos procesos puros de SPEM a copiar
//					for(Iterator iterator = processOrig.getProcessElements().iterator(); iterator.hasNext();){
//						EObject elementObj = (EObject) iterator.next();
//						//Si no es instancia de un elemento vSPEM es que lo es de SPEM
//						if((elementObj instanceof ProcessElement)){
//							if(!(elementObj instanceof VarElement)){
//								ProcessElement processElements = (ProcessElement) elementObj;
//								elementsToCopy.add(processElements);
//							}
//						}
//					}
//					
//					//Copia de los elementos
//					prepareAddList(elementsToCopy);
//					copyProcessPackages(elementsToCopy, processAdapted);
//					
//					//Construccion de las variaciones a elementos completos de SPEM
//					createCompleteObjectsSPEM(processAdapted, variationPkg);
//					
//					
//					//Persistencia del proceso
//					pkg.getChildPackages().add(processAdapted);
//					
//					saveProcess(processAdapted);
//					
//				}
//			}

				
	}
}
