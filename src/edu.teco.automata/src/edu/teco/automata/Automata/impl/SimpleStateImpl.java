/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata.impl;

import edu.teco.automata.Automata.AutomataPackage;
import edu.teco.automata.Automata.DataType;
import edu.teco.automata.Automata.SimpleState;
import edu.teco.automata.Automata.State;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.teco.automata.Automata.impl.SimpleStateImpl#getOut <em>Out</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.SimpleStateImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.SimpleStateImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.SimpleStateImpl#getDepth <em>Depth</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.SimpleStateImpl#getType <em>Type</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.impl.SimpleStateImpl#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimpleStateImpl extends StateImpl implements SimpleState {
	/**
    * The cached value of the '{@link #getOut() <em>Out</em>}' reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getOut()
    * @generated
    * @ordered
    */
	protected EList<State> out;

	/**
    * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getLowerBound()
    * @generated
    * @ordered
    */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
    * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getLowerBound()
    * @generated
    * @ordered
    */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
    * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getUpperBound()
    * @generated
    * @ordered
    */
	protected static final int UPPER_BOUND_EDEFAULT = 0;

	/**
    * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getUpperBound()
    * @generated
    * @ordered
    */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
    * The default value of the '{@link #getDepth() <em>Depth</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDepth()
    * @generated
    * @ordered
    */
	protected static final int DEPTH_EDEFAULT = 0;

	/**
    * The cached value of the '{@link #getDepth() <em>Depth</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDepth()
    * @generated
    * @ordered
    */
	protected int depth = DEPTH_EDEFAULT;

	/**
    * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
	protected DataType type;

	/**
    * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getKind()
    * @generated
    * @ordered
    */
	protected static final String KIND_EDEFAULT = null;

	/**
    * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getKind()
    * @generated
    * @ordered
    */
	protected String kind = KIND_EDEFAULT;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected SimpleStateImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	protected EClass eStaticClass() {
      return AutomataPackage.Literals.SIMPLE_STATE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EList<State> getOut() {
      if (out == null) {
         out = new EObjectResolvingEList<State>(State.class, this, AutomataPackage.SIMPLE_STATE__OUT);
      }
      return out;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public int getLowerBound() {
      return lowerBound;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setLowerBound(int newLowerBound) {
      int oldLowerBound = lowerBound;
      lowerBound = newLowerBound;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.SIMPLE_STATE__LOWER_BOUND, oldLowerBound, lowerBound));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public int getUpperBound() {
      return upperBound;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setUpperBound(int newUpperBound) {
      int oldUpperBound = upperBound;
      upperBound = newUpperBound;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.SIMPLE_STATE__UPPER_BOUND, oldUpperBound, upperBound));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public int getDepth() {
      return depth;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setDepth(int newDepth) {
      int oldDepth = depth;
      depth = newDepth;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.SIMPLE_STATE__DEPTH, oldDepth, depth));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DataType getType() {
      return type;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetType(DataType newType, NotificationChain msgs) {
      DataType oldType = type;
      type = newType;
      if (eNotificationRequired()) {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AutomataPackage.SIMPLE_STATE__TYPE, oldType, newType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setType(DataType newType) {
      if (newType != type) {
         NotificationChain msgs = null;
         if (type != null)
            msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AutomataPackage.SIMPLE_STATE__TYPE, null, msgs);
         if (newType != null)
            msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AutomataPackage.SIMPLE_STATE__TYPE, null, msgs);
         msgs = basicSetType(newType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.SIMPLE_STATE__TYPE, newType, newType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String getKind() {
      return kind;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setKind(String newKind) {
      String oldKind = kind;
      kind = newKind;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.SIMPLE_STATE__KIND, oldKind, kind));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case AutomataPackage.SIMPLE_STATE__TYPE:
            return basicSetType(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case AutomataPackage.SIMPLE_STATE__OUT:
            return getOut();
         case AutomataPackage.SIMPLE_STATE__LOWER_BOUND:
            return new Integer(getLowerBound());
         case AutomataPackage.SIMPLE_STATE__UPPER_BOUND:
            return new Integer(getUpperBound());
         case AutomataPackage.SIMPLE_STATE__DEPTH:
            return new Integer(getDepth());
         case AutomataPackage.SIMPLE_STATE__TYPE:
            return getType();
         case AutomataPackage.SIMPLE_STATE__KIND:
            return getKind();
      }
      return super.eGet(featureID, resolve, coreType);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case AutomataPackage.SIMPLE_STATE__OUT:
            getOut().clear();
            getOut().addAll((Collection<? extends State>)newValue);
            return;
         case AutomataPackage.SIMPLE_STATE__LOWER_BOUND:
            setLowerBound(((Integer)newValue).intValue());
            return;
         case AutomataPackage.SIMPLE_STATE__UPPER_BOUND:
            setUpperBound(((Integer)newValue).intValue());
            return;
         case AutomataPackage.SIMPLE_STATE__DEPTH:
            setDepth(((Integer)newValue).intValue());
            return;
         case AutomataPackage.SIMPLE_STATE__TYPE:
            setType((DataType)newValue);
            return;
         case AutomataPackage.SIMPLE_STATE__KIND:
            setKind((String)newValue);
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
         case AutomataPackage.SIMPLE_STATE__OUT:
            getOut().clear();
            return;
         case AutomataPackage.SIMPLE_STATE__LOWER_BOUND:
            setLowerBound(LOWER_BOUND_EDEFAULT);
            return;
         case AutomataPackage.SIMPLE_STATE__UPPER_BOUND:
            setUpperBound(UPPER_BOUND_EDEFAULT);
            return;
         case AutomataPackage.SIMPLE_STATE__DEPTH:
            setDepth(DEPTH_EDEFAULT);
            return;
         case AutomataPackage.SIMPLE_STATE__TYPE:
            setType((DataType)null);
            return;
         case AutomataPackage.SIMPLE_STATE__KIND:
            setKind(KIND_EDEFAULT);
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
         case AutomataPackage.SIMPLE_STATE__OUT:
            return out != null && !out.isEmpty();
         case AutomataPackage.SIMPLE_STATE__LOWER_BOUND:
            return lowerBound != LOWER_BOUND_EDEFAULT;
         case AutomataPackage.SIMPLE_STATE__UPPER_BOUND:
            return upperBound != UPPER_BOUND_EDEFAULT;
         case AutomataPackage.SIMPLE_STATE__DEPTH:
            return depth != DEPTH_EDEFAULT;
         case AutomataPackage.SIMPLE_STATE__TYPE:
            return type != null;
         case AutomataPackage.SIMPLE_STATE__KIND:
            return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
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
      result.append(" (lowerBound: ");
      result.append(lowerBound);
      result.append(", upperBound: ");
      result.append(upperBound);
      result.append(", depth: ");
      result.append(depth);
      result.append(", kind: ");
      result.append(kind);
      result.append(')');
      return result.toString();
   }

} //SimpleStateImpl
