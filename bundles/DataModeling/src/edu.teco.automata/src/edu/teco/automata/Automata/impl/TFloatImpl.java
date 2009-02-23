/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata.impl;

import edu.teco.automata.Automata.AutomataPackage;
import edu.teco.automata.Automata.TFloat;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TFloat</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.teco.automata.Automata.impl.TFloatImpl#getMin <em>Min</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TFloatImpl#getMax <em>Max</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TFloatImpl#getFractionDigits <em>Fraction Digits</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TFloatImpl#getStepping <em>Stepping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TFloatImpl extends DataTypeImpl implements TFloat {
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
	protected static final float FRACTION_DIGITS_EDEFAULT = 0.0F;

	/**
    * The cached value of the '{@link #getFractionDigits() <em>Fraction Digits</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getFractionDigits()
    * @generated
    * @ordered
    */
	protected float fractionDigits = FRACTION_DIGITS_EDEFAULT;

	/**
    * The default value of the '{@link #getStepping() <em>Stepping</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStepping()
    * @generated
    * @ordered
    */
   protected static final int STEPPING_EDEFAULT = 0;

   /**
    * The cached value of the '{@link #getStepping() <em>Stepping</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStepping()
    * @generated
    * @ordered
    */
   protected int stepping = STEPPING_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TFloatImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	protected EClass eStaticClass() {
      return AutomataPackage.Literals.TFLOAT;
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
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TFLOAT__MIN, oldMin, min));
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
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TFLOAT__MAX, oldMax, max));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public float getFractionDigits() {
      return fractionDigits;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFractionDigits(float newFractionDigits) {
      float oldFractionDigits = fractionDigits;
      fractionDigits = newFractionDigits;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TFLOAT__FRACTION_DIGITS, oldFractionDigits, fractionDigits));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getStepping() {
      return stepping;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setStepping(int newStepping) {
      int oldStepping = stepping;
      stepping = newStepping;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TFLOAT__STEPPING, oldStepping, stepping));
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case AutomataPackage.TFLOAT__MIN:
            return new Integer(getMin());
         case AutomataPackage.TFLOAT__MAX:
            return new Integer(getMax());
         case AutomataPackage.TFLOAT__FRACTION_DIGITS:
            return new Float(getFractionDigits());
         case AutomataPackage.TFLOAT__STEPPING:
            return new Integer(getStepping());
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
         case AutomataPackage.TFLOAT__MIN:
            setMin(((Integer)newValue).intValue());
            return;
         case AutomataPackage.TFLOAT__MAX:
            setMax(((Integer)newValue).intValue());
            return;
         case AutomataPackage.TFLOAT__FRACTION_DIGITS:
            setFractionDigits(((Float)newValue).floatValue());
            return;
         case AutomataPackage.TFLOAT__STEPPING:
            setStepping(((Integer)newValue).intValue());
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
         case AutomataPackage.TFLOAT__MIN:
            setMin(MIN_EDEFAULT);
            return;
         case AutomataPackage.TFLOAT__MAX:
            setMax(MAX_EDEFAULT);
            return;
         case AutomataPackage.TFLOAT__FRACTION_DIGITS:
            setFractionDigits(FRACTION_DIGITS_EDEFAULT);
            return;
         case AutomataPackage.TFLOAT__STEPPING:
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
         case AutomataPackage.TFLOAT__MIN:
            return min != MIN_EDEFAULT;
         case AutomataPackage.TFLOAT__MAX:
            return max != MAX_EDEFAULT;
         case AutomataPackage.TFLOAT__FRACTION_DIGITS:
            return fractionDigits != FRACTION_DIGITS_EDEFAULT;
         case AutomataPackage.TFLOAT__STEPPING:
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

} //TFloatImpl
