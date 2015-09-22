package org.eclipse.vepf.authoring.ui.jasperbeans;

import java.util.Comparator;

import org.eclipse.swt.graphics.Image;





public class variationBean {

	public String name;
	public String type;
	public String varPointName;
	public String variantName;
	public String iconPath;
	
	
	public variationBean(String name, String type, String varPointName, String variantName, String iconPath){
		this.name = name;
		this.type = type;
		this.varPointName = varPointName;
		this.variantName = variantName;
		this.iconPath = iconPath;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVarPointName() {
		return varPointName;
	}

	public void setVarPointName(String varPointName) {
		this.varPointName = varPointName;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
}


