/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.teco.automata.Automata.AutomataPackage
 * @generated
 */
public interface AutomataFactory extends EFactory {
	/**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	AutomataFactory eINSTANCE = edu.teco.automata.Automata.impl.AutomataFactoryImpl.init();

	/**
    * Returns a new object of class '<em>State Machine</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>State Machine</em>'.
    * @generated
    */
	StateMachine createStateMachine();

	/**
    * Returns a new object of class '<em>Simple State</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Simple State</em>'.
    * @generated
    */
	SimpleState createSimpleState();

	/**
    * Returns a new object of class '<em>Start State</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Start State</em>'.
    * @generated
    */
	StartState createStartState();

	/**
    * Returns a new object of class '<em>Stop State</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Stop State</em>'.
    * @generated
    */
	StopState createStopState();

	/**
    * Returns a new object of class '<em>complex Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>complex Type</em>'.
    * @generated
    */
	complexType createcomplexType();

	/**
    * Returns a new object of class '<em>TByte</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TByte</em>'.
    * @generated
    */
	TByte createTByte();

	/**
    * Returns a new object of class '<em>TChar</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TChar</em>'.
    * @generated
    */
	TChar createTChar();

	/**
    * Returns a new object of class '<em>TShort</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TShort</em>'.
    * @generated
    */
	TShort createTShort();

	/**
    * Returns a new object of class '<em>TInt</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TInt</em>'.
    * @generated
    */
	TInt createTInt();

	/**
    * Returns a new object of class '<em>TLong</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TLong</em>'.
    * @generated
    */
	TLong createTLong();

	/**
    * Returns a new object of class '<em>TFloat</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TFloat</em>'.
    * @generated
    */
	TFloat createTFloat();

	/**
    * Returns a new object of class '<em>TDouble</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TDouble</em>'.
    * @generated
    */
	TDouble createTDouble();

	/**
    * Returns a new object of class '<em>TString</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>TString</em>'.
    * @generated
    */
	TString createTString();

	/**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
	AutomataPackage getAutomataPackage();

} //AutomataFactory
