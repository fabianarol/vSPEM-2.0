package org.eclipse.epf.library.edit.process;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;


public class VarTaskDescriptorItemProvider extends DescriptorItemProvider {

	/**
	 * @param adapterFactory
	 */
	public VarTaskDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(WorkBreakdownElement.class)) {
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), false, true));
			return;
		}

		super.notifyChanged(notification);
	}

	public Collection getEClasses() {
		return ProcessUtil.getWBSEclasses();
	}

}
