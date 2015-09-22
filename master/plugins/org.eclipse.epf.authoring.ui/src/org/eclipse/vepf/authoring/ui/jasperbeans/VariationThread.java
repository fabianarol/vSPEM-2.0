package org.eclipse.vepf.authoring.ui.jasperbeans;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.epf.authoring.ui.vepfdialogs.ReportDialog;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Occupation;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TailoredProcessComponent;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VarPoint;
import org.eclipse.epf.uma.Variation;
import org.eclipse.epf.uma.VariationsPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class VariationThread extends Thread  implements Runnable{
	
	
	private TailoredProcessComponent tailoredProcessComponent;
	private String nameFile;
	private File fileToSave;
	private String formatFile;
	private File logoFile;
	
	private Boolean execute;
	
	public VariationThread(TailoredProcessComponent tailoredProcessComponent,
			File fileToSave, String formatFile) {
		this.tailoredProcessComponent = tailoredProcessComponent;
		this.fileToSave = fileToSave;
		this.formatFile = formatFile;
	}

	public void run() {
		// TODO Auto-generated method stub
		
		/*Check JasperFile*/
		File fileAux = new File("file://");
		fileAux.getAbsolutePath();
		File file = LibraryEditPlugin.INSTANCE.getJasper("/reports/reportVariations");
		/**/
		

		
		if(file.exists()){
			try {
				/*Informacion del proceso adaptado*/			
				String description = tailoredProcessComponent.getBriefDescription();
				String processName = tailoredProcessComponent.getName();
				String reportName = fileToSave.getName();
				String absolutePath = fileToSave.getAbsolutePath();
				String numberElements = "0";
				

	
				
				List variationsList= new ArrayList();
				
				VariationsPackage varPackage = UmaUtil.findVariationsPackage(tailoredProcessComponent);//Buscamos variaciones
				
				if(varPackage != null){
					if(varPackage.getVariations().size() >= 1){
						
						numberElements = Integer.toString(varPackage.getVariations().size());
						
						for(Iterator iterator = varPackage.getVariations().iterator(); iterator.hasNext();){
							Occupation occupation = (Occupation) iterator.next();
							
							String descOccupation = occupation.getDescription();
							String varPointName = occupation.getEsocupado().getVpName();
							String variantName = occupation.getOcupadopor().getVpName();
							String iconPath = findImageForVarPoint(occupation.getEsocupado());
							
							String typeName = UmaUtil.findVarPointType(occupation.getEsocupado());
							
							variationBean var = new variationBean(variantName, typeName, varPointName, variantName, iconPath);     
							variationsList.add(var);  
							
						}
					}
				}
				
				Collections.sort(variationsList, new TypeComparator());
				
				//Parametros
				Map<String, Object> parametrosDetalles = new HashMap<String, Object>();
				
				logoFile = LibraryEditPlugin.INSTANCE.getPng("/icons/full/Report");
				parametrosDetalles.put("logoPath", logoFile.getAbsolutePath());
				parametrosDetalles.put("processName", processName);
				parametrosDetalles.put("description", description);
				parametrosDetalles.put("numberElements", numberElements);
				parametrosDetalles.put("reportName", reportName);
				
				/**/
				JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametrosDetalles, new JRBeanCollectionDataSource(variationsList));
				
				JRExporter exporter = new JRPdfExporter(); 
			    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(fileToSave.getAbsolutePath()+formatFile));
			    exporter.exportReport();
			    
			    
				
			    execute=true;

			    
			} catch (JRException ex) {
				execute = false;
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que retorna la ruta de la imagen de un VarPoint (Imagen Padre) para el JasperReport
	 * @param varPoint
	 * @return
	 */
	public static String findImageForVarPoint(VarPoint varPoint){
		File file;
		
		if(varPoint instanceof Phase){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/phase");//Icon
		}
		else if(varPoint instanceof Iteration){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/iteration");//Icon
		}
		else if(varPoint instanceof Activity){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/activity");//Icon
		}
		else if(varPoint instanceof Milestone){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/milestone");//Icon
		}
		else if(varPoint instanceof TeamProfile){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/teamprofile");//Icon
		}
		else if(varPoint instanceof TaskDescriptor){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/taskdescriptor");//Icon
		}
		else if(varPoint instanceof WorkProductDescriptor){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/workproductdescriptor");//Icon
		}
		else if(varPoint instanceof RoleDescriptor){
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/roledescriptor");//Icon
		}
		else{
			file = LibraryEditPlugin.INSTANCE.getGif("icons/full/shape/toolmentor");//Icon
		}
		return file.getAbsolutePath();
	}
	
	public Boolean getExecute(){
		return execute;
	}
}
