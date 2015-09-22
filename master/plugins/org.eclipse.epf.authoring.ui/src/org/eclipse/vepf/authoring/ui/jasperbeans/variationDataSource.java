package org.eclipse.vepf.authoring.ui.jasperbeans;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class variationDataSource implements JRDataSource{

	private List<variationBean> variationList = new ArrayList<variationBean>();
	private int variationIndex = -1;
	
	public Object getFieldValue(JRField jrField) throws JRException {
		Object valor = null;  

	    if("name".equals(jrField.getName())) 
	    { 
	        valor = variationList.get(variationIndex).getName();
	    } 
	    else if("type".equals(jrField.getName())) 
	    { 
	        valor = variationList.get(variationIndex).getType();
	    } 
	    else if("varPointName".equals(jrField.getName())) 
	    { 
	        valor = variationList.get(variationIndex).getVarPointName();
	    } 
	    else if("variantName".equals(jrField.getName())) 
	    { 
	        valor = variationList.get(variationIndex).getVariantName();
	    }
	 
	    return valor;
	}

	public boolean next() throws JRException {
		return ++variationIndex < variationList.size();
	}

	public void addVariation(variationBean variation)
	{
	    this.variationList.add(variation);
	}
}
