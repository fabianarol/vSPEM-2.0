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
package org.eclipse.epf.persistence.migration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * Migrates one version of UMA library to another
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IMigrator {

	void migrate(String libPath, IProgressMonitor monitor) throws Exception;
	void migrate(String libPath, IProgressMonitor monitor, UpgradeCallerInfo info) throws Exception;
	void migrateXmlImportedLib(MethodLibrary lib, IProgressMonitor monitor) throws Exception;

}
