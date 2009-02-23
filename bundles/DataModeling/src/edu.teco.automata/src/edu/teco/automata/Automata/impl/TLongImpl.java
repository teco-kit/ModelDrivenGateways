/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata.impl;

import edu.teco.automata.Automata.AutomataPackage;
import edu.teco.automata.Automata.TLong;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TLong</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.teco.automata.Automata.impl.TLongImpl#getMin <em>Min</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TLongImpl#getMax <em>Max</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.TLongImpl#getStepping <em>Stepping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TLongImpl extends DataTypeImpl implements TLong {
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
	protected TLongImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	protected EClass eStaticClass() {
      return AutomataPackage.Literals.TLONG;
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
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TLONG__MIN, oldMin, min));
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
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TLONG__MAX, oldMax, max));
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
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TLONG__STEPPING, oldStepping, stepping));
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case AutomataPackage.TLONG__MIN:
            return new Integer(getMin());
         case AutomataPackage.TLONG__MAX:
            return new Integer(getMax());
         case AutomataPackage.TLONG__STEPPING:
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
         case AutomataPackage.TLONG__MIN:
            setMin(((Integer)newValue).intValue());
            return;
         case AutomataPackage.TLONG__MAX:
            setMax(((Integer)newValue).intValue());
            return;
         case AutomataPackage.TLONG__STEPPING:
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
         case AutomataPackage.TLONG__MIN:
            setMin(MIN_EDEFAULT);
            return;
         case AutomataPackage.TLONG__MAX:
            setMax(MAX_EDEFAULT);
            return;
         case AutomataPackage.TLONG__STEPPING:
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
         case AutomataPackage.TLONG__MIN:
            return min != MIN_EDEFAULT;
         case AutomataPackage.TLONG__MAX:
            return max != MAX_EDEFAULT;
         case AutomataPackage.TLONG__STEPPING:
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
      result.append(", stepping: ");
      result.append(stepping);
      result.append(')');
      return result.toString();
   }

} //TLongImpl
