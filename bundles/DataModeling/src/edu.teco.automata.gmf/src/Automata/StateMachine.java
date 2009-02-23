/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Automata.StateMachine#getStates <em>States</em>}</li>
 * </ul>
 * </p>
 *
 * @see Automata.AutomataPackage#getStateMachine()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraint='justOnce'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL justOnce='states.typeSelect(StartState).length == 1'"
 * @generated
 */
public interface StateMachine extends EObject {
   /**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link Automata.State}.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>States</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see Automata.AutomataPackage#getStateMachine_States()
	 * @model containment="true" required="true"
	 * @generated
	 */
   EList<State> getStates();

} // StateMachine
