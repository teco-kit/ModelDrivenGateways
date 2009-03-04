/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata.impl;

import Automata.AutomataPackage;
import Automata.StartState;
import Automata.State;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Automata.impl.StartStateImpl#getOut <em>Out</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartStateImpl extends StateImpl implements StartState {
   /**
	 * The cached value of the '{@link #getOut() <em>Out</em>}' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getOut()
	 * @generated
	 * @ordered
	 */
   protected State out;

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected StartStateImpl() {
		super();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   protected EClass eStaticClass() {
		return AutomataPackage.Literals.START_STATE;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public State getOut() {
		if (out != null && out.eIsProxy()) {
			InternalEObject oldOut = (InternalEObject)out;
			out = (State)eResolveProxy(oldOut);
			if (out != oldOut) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AutomataPackage.START_STATE__OUT, oldOut, out));
			}
		}
		return out;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public State basicGetOut() {
		return out;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setOut(State newOut) {
		State oldOut = out;
		out = newOut;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.START_STATE__OUT, oldOut, out));
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AutomataPackage.START_STATE__OUT:
				if (resolve) return getOut();
				return basicGetOut();
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
			case AutomataPackage.START_STATE__OUT:
				setOut((State)newValue);
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
			case AutomataPackage.START_STATE__OUT:
				setOut((State)null);
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
			case AutomataPackage.START_STATE__OUT:
				return out != null;
		}
		return super.eIsSet(featureID);
	}

} //StartStateImpl
