/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getOut <em>Out</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getDepth <em>Depth</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getType <em>Type</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getKind <em>Kind</em>}</li>
 *   <li>{@link edu.teco.automata.Automata.SimpleState#getEcore <em>Ecore</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState()
 * @model
 * @generated
 */
public interface SimpleState extends State {
	/**
	 * Returns the value of the '<em><b>Out</b></em>' reference list.
	 * The list contents are of type {@link edu.teco.automata.Automata.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out</em>' reference list.
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_Out()
	 * @model required="true"
	 * @generated
	 */
	EList<State> getOut();

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_LowerBound()
	 * @model required="true"
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link edu.teco.automata.Automata.SimpleState#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_UpperBound()
	 * @model required="true"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link edu.teco.automata.Automata.SimpleState#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * Returns the value of the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depth</em>' attribute.
	 * @see #setDepth(int)
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_Depth()
	 * @model required="true"
	 * @generated
	 */
	int getDepth();

	/**
	 * Sets the value of the '{@link edu.teco.automata.Automata.SimpleState#getDepth <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depth</em>' attribute.
	 * @see #getDepth()
	 * @generated
	 */
	void setDepth(int value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(DataType)
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_Type()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DataType getType();

	/**
	 * Sets the value of the '{@link edu.teco.automata.Automata.SimpleState#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(DataType value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link edu.teco.automata.Automata.SimpleState#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

	/**
	 * Returns the value of the '<em><b>Ecore</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ecore</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ecore</em>' reference.
	 * @see #setEcore(EClassifier)
	 * @see edu.teco.automata.Automata.AutomataPackage#getSimpleState_Ecore()
	 * @model
	 * @generated
	 */
	EClassifier getEcore();

	/**
	 * Sets the value of the '{@link edu.teco.automata.Automata.SimpleState#getEcore <em>Ecore</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ecore</em>' reference.
	 * @see #getEcore()
	 * @generated
	 */
	void setEcore(EClassifier value);

} // SimpleState
