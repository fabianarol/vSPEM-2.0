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
package org.eclipse.epf.library.edit;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.epf.common.plugin.AbstractPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.BundleContext;

/**
 * The Library Edit plug-in class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryEditPlugin extends AbstractPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.epf.library.edit"; //$NON-NLS-1$
	
	// The shared plug-in instance.
	private static LibraryEditPlugin plugin;

	public static LibraryEditPlugin INSTANCE;

	// The shared image hash map.
	private static Map sharedImages = new HashMap();

	/**
	 * Creates a new instance.
	 */
	public LibraryEditPlugin() {
		super();
		plugin = this;
		INSTANCE = this;
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static LibraryEditPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static LibraryEditPlugin getPlugin() {
		return plugin;
	}

	/**
	 * Returns the symbolic name of this plug-in.
	 * 
	 * @return The symbolic name of this plug-in.
	 */
	public String getSymbolicName() {
		return getId();
	}

	/**
	 * @see org.eclipse.epf.common.plugin.AbstractPlugin#getImage(String)
	 */
	public Image getImage(String relativePath) {
		return super.getSharedImage(relativePath + ".gif"); //$NON-NLS-1$
	}
	
	/**
	 * @see org.eclipse.epf.common.plugin.AbstractPlugin#getImage(String)
	 */
	
	/***/
	//VEPF --> GetsFIles
	public File getHtml(String relativePath) {
		return super.getSharedHtml(relativePath + ".html"); //$NON-NLS-1$
	}
	
	public File getJasper(String relativePath) {
		return super.getSharedJasper(relativePath + ".jasper"); //$NON-NLS-1$
	}
	
	public File getGif(String relativePath){
		return super.getSharedGif(relativePath + ".gif");
	}
	
	public File getPng(String relativePath){
		return super.getSharedGif(relativePath + ".png");
	}
	/***/
	
	
	/**
	 * @see org.eclipse.epf.common.plugin.AbstractPlugin#getSharedImage(String)
	 */
	public Image getSharedImage(String relativePath) {
		return super.getSharedImage(relativePath + ".gif"); //$NON-NLS-1$
	}

	/**
	 * Returns the image descriptor given the URI.
	 * 
	 * @param imageURI
	 *            The image's URI.
	 * @return The image descriptor.
	 */
	public ImageDescriptor getImageDescriptor(URI imageURI) {
		try {
			return ImageDescriptor.createFromURL(imageURI.toURL());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the shared image given the URI.
	 * 
	 * @param imageURI
	 *            The image's URI.
	 * @return The image.
	 */
	public Image getSharedImage(URI imageURI) {
		if (imageURI == null) {
			return null;
		}
		Image image = (Image) sharedImages.get(imageURI);
		if (image != null) {
			return image;
		}

		ImageDescriptor imageDescriptor = getImageDescriptor(imageURI);
		if (imageDescriptor != null) {
			image = imageDescriptor.createImage(false);
			if (image != null) {
				sharedImages.put(imageURI, image);
			}
		}

		return image;
	}

	/**
	 * Logs the given object or message.
	 */
	public void log(Object logEntry) {
		if (logEntry instanceof Throwable) {
			((Throwable) logEntry).printStackTrace(System.err);
		} else {
			System.err.println(logEntry);
		}
	}

}