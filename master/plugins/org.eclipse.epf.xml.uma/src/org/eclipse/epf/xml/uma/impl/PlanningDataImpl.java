/**
 * <copyright>
 * </copyright>
 *
 * $Id: PlanningDataImpl.java,v 1.4 2007/06/05 21:14:59 klow Exp $
 */
package org.eclipse.epf.xml.uma.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.xml.uma.PlanningData;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Planning Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.PlanningDataImpl#getFinishDate <em>Finish Date</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.PlanningDataImpl#getRank <em>Rank</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.PlanningDataImpl#getStartDate <em>Start Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlanningDataImpl extends ProcessElementImpl implements PlanningData {
	/**
	 * The default value of the '{@link #getFinishDate() <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object FINISH_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinishDate() <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishDate()
	 * @generated
	 * @ordered
	 */
	protected Object finishDate = FINISH_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected static final String RANK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected String rank = RANK_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected Object startDate = START_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanningDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UmaPackage.Literals.PLANNING_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getFinishDate() {
		return finishDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinishDate(Object newFinishDate) {
		Object oldFinishDate = finishDate;
		finishDate = newFinishDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.PLANNING_DATA__FINISH_DATE, oldFinishDate, finishDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRank(String newRank) {
		String oldRank = rank;
		rank = newRank;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.PLANNING_DATA__RANK, oldRank, rank));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getStartDate() {
		return startDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(Object newStartDate) {
		Object oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.PLANNING_DATA__START_DATE, oldStartDate, startDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UmaPackage.PLANNING_DATA__FINISH_DATE:
				return getFinishDate();
			case UmaPackage.PLANNING_DATA__RANK:
				return getRank();
			case UmaPackage.PLANNING_DATA__START_DATE:
				return getStartDate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UmaPackage.PLANNING_DATA__FINISH_DATE:
				setFinishDate(newValue);
				return;
			case UmaPackage.PLANNING_DATA__RANK:
				setRank((String)newValue);
				return;
			case UmaPackage.PLANNING_DATA__START_DATE:
				setStartDate(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case UmaPackage.PLANNING_DATA__FINISH_DATE:
				setFinishDate(FINISH_DATE_EDEFAULT);
				return;
			case UmaPackage.PLANNING_DATA__RANK:
				setRank(RANK_EDEFAULT);
				return;
			case UmaPackage.PLANNING_DATA__START_DATE:
				setStartDate(START_DATE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UmaPackage.PLANNING_DATA__FINISH_DATE:
				return FINISH_DATE_EDEFAULT == null ? finishDate != null : !FINISH_DATE_EDEFAULT.equals(finishDate);
			case UmaPackage.PLANNING_DATA__RANK:
				return RANK_EDEFAULT == null ? rank != null : !RANK_EDEFAULT.equals(rank);
			case UmaPackage.PLANNING_DATA__START_DATE:
				return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (finishDate: ");
		result.append(finishDate);
		result.append(", rank: ");
		result.append(rank);
		result.append(", startDate: ");
		result.append(startDate);
		result.append(')');
		return result.toString();
	}

} //PlanningDataImpl