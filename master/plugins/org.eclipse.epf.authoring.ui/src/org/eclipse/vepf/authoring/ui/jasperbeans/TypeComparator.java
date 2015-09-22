package org.eclipse.vepf.authoring.ui.jasperbeans;

import java.util.Comparator;

public class TypeComparator implements Comparator{

    public int compare(Object varBean1, Object varBean2){
    	   
    	String varBean1Name = ((variationBean)varBean1).getType();        
        String varBean2Name = ((variationBean)varBean2).getType();
       
        return varBean1Name.compareTo(varBean2Name);
    }
	
}
