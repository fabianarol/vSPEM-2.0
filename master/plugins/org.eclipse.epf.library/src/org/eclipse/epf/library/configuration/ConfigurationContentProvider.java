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
package org.eclipse.epf.library.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigurationContentProvider implements ITreeContentProvider {
	
	private MethodConfiguration config = null;

	/**
	 * constructor
	 *
	 */
	public ConfigurationContentProvider() {
	}

	/**
	 * get the children
	 * @param parentElement Object
	 * @return an array of Objects for the given parent object
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MethodConfiguration) {
			List sels = ((MethodConfiguration) parentElement)
					.getMethodPluginSelection();
			if (sels != null) {
				return sels.toArray();
			} else {
				return null;
			}
		} else if (parentElement instanceof MethodPlugin) {
			List sels = ((MethodPlugin) parentElement).getMethodPackages();
			if (sels != null) {
				List items = new ArrayList();
				List selectedPackages = config.getMethodPackageSelection();

				for (Iterator it = sels.iterator(); it.hasNext();) {
					MethodPackage pkg = (MethodPackage) it.next();
					if (selectedPackages != null
							&& selectedPackages.contains(pkg)) {
						items.add(pkg);
					}
				}

				return items.toArray();
			}
		} else if (parentElement instanceof EObject) {
			List children = ((EObject) parentElement).eContents();
			if (children != null) {
				List showItems = new ArrayList();
				for (Iterator it = children.iterator(); it.hasNext();) {
					Object child = it.next();
					if ((!(child instanceof MethodElement))
							|| ConfigurationHelper.canShow(
									(MethodElement) child, config)) {
						showItems.add(child);
					}
				}
				return showItems.toArray();
			}
		}

		return null;
	}

	/**
	 * get the elements assiciated with the given element. same as getChildren()
	 * @param inputElement Object
	 * @return an array of Objects
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * get the parant object for the given element
	 * @param element Object
	 * @return Object
	 */
	public Object getParent(Object element) {
		if (element instanceof MethodConfiguration) {
			return null;
		} else if (element instanceof EObject) {
			return ((EObject) element).eContainer();
		} else {
			return null;
		}
	}

	/**
	 * check if there are children for the given element
	 * @param element Object
	 * @return boolean
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof MethodConfiguration
				|| element instanceof MethodPlugin) {
			return true;
		} else if (element instanceof EObject) {
			List children = ((EObject) element).eContents();
			return children != null && children.size() > 0;
		} else {
			return false;
		}
	}

	/**
	 * notify input changed
	 * @param viewer Viewer
	 * @param oldInput Object
	 * @param newInput Object
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput instanceof MethodConfiguration) {
			config = (MethodConfiguration) newInput;
		}
	}

	/**
	 * dispose the resources allocated by this object
	 */
	public void dispose() {
	}
	
}
