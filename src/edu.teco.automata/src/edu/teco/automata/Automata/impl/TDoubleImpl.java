/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata.impl;

import edu.teco.automata.Automata.AutomataPackage;
import edu.teco.automata.Automata.TDouble;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TDouble</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.teco.automata.Automata.impl.TDoubleImpl#getMin <em>Min</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TDoubleImpl#getMax <em>Max</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TDoubleImpl#getFractionDigits <em>Fraction Digits</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TDoubleImpl#getStepping <em>Stepping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TDoubleImpl extends DataTypeImpl implements TDouble {
	/**
	 * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected int min = MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected int max = MAX_EDEFAULT;

	/**
	 * The default value of the '{@link #getFractionDigits() <em>Fraction Digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFractionDigits()
	 * @generated
	 * @ordered
	 */
	protected static final int FRACTION_DIGITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFractionDigits() <em>Fraction Digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFractionDigits()
	 * @generated
	 * @ordered
	 */
	protected int fractionDigits = FRACTION_DIGITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getStepping() <em>Stepping</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getStepping()
	 * @generated
	 * @ordered
	 */
   protected static final double STEPPING_EDEFAULT = 0.0;

   /**
	 * The cached value of the '{@link #getStepping() <em>Stepping</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getStepping()
	 * @generated
	 * @ordered
	 */
   protected double stepping = STEPPING_EDEFAULT;

   /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TDoubleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AutomataPackage.Literals.TDOUBLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin() {
		return min;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin(int newMin) {
		int oldMin = min;
		min = newMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TDOUBLE__MIN, oldMin, min));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax() {
		return max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax(int newMax) {
		int oldMax = max;
		max = newMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TDOUBLE__MAX, oldMax, max));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFractionDigits() {
		return fractionDigits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFractionDigits(int newFractionDigits) {
		int oldFractionDigits = fractionDigits;
		fractionDigits = newFractionDigits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TDOUBLE__FRACTION_DIGITS, oldFractionDigits, fractionDigits));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public double getStepping() {
		return stepping;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setStepping(double newStepping) {
		double oldStepping = stepping;
		stepping = newStepping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TDOUBLE__STEPPING, oldStepping, stepping));
	}

   /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AutomataPackage.TDOUBLE__MIN:
				return getMin();
			case AutomataPackage.TDOUBLE__MAX:
				return getMax();
			case AutomataPackage.TDOUBLE__FRACTION_DIGITS:
				return getFractionDigits();
			case AutomataPackage.TDOUBLE__STEPPING:
				return getStepping();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AutomataPackage.TDOUBLE__MIN:
				setMin((Integer)newValue);
				return;
			case AutomataPackage.TDOUBLE__MAX:
				setMax((Integer)newValue);
				return;
			case AutomataPackage.TDOUBLE__FRACTION_DIGITS:
				setFractionDigits((Integer)newValue);
				return;
			case AutomataPackage.TDOUBLE__STEPPING:
				setStepping((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AutomataPackage.TDOUBLE__MIN:
				setMin(MIN_EDEFAULT);
				return;
			case AutomataPackage.TDOUBLE__MAX:
				setMax(MAX_EDEFAULT);
				return;
			case AutomataPackage.TDOUBLE__FRACTION_DIGITS:
				setFractionDigits(FRACTION_DIGITS_EDEFAULT);
				return;
			case AutomataPackage.TDOUBLE__STEPPING:
				setStepping(STEPPING_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AutomataPackage.TDOUBLE__MIN:
				return min != MIN_EDEFAULT;
			case AutomataPackage.TDOUBLE__MAX:
				return max != MAX_EDEFAULT;
			case AutomataPackage.TDOUBLE__FRACTION_DIGITS:
				return fractionDigits != FRACTION_DIGITS_EDEFAULT;
			case AutomataPackage.TDOUBLE__STEPPING:
				return stepping != STEPPING_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (min: ");
		result.append(min);
		result.append(", max: ");
		result.append(max);
		result.append(", fractionDigits: ");
		result.append(fractionDigits);
		result.append(", stepping: ");
		result.append(stepping);
		result.append(')');
		return result.toString();
	}

} //TDoubleImpl
