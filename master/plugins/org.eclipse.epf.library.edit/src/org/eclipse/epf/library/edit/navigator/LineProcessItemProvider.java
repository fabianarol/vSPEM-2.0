package org.eclipse.epf.library.edit.navigator;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;


public class LineProcessItemProvider extends
		org.eclipse.epf.uma.provider.LineProcessItemProvider {

	/**
	 * Creates a new instance.
	 */
	public LineProcessItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {

	}

	public Collection getChildrenFeatures(Object object) {
		return Collections.EMPTY_LIST;
	}

	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_LineProcess_type")); //$NON-NLS-1$
	}

	protected Command createInitializeCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementInitializeCopyCommand(domain, owner, helper);
	}

	protected Command createCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementCreateCopyCommand(domain, owner, helper);
	}

}
