/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TString</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.teco.automata.Automata.TString#getLength <em>Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.teco.automata.Automata.AutomataPackage#getTString()
 * @model
 * @generated
 */
public interface TString extends DataType {
	/**
    * Returns the value of the '<em><b>Length</b></em>' attribute.
    * The default value is <code>"0"</code>.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Length</em>' attribute.
    * @see #setLength(int)
    * @see edu.teco.automata.Automata.AutomataPackage#getTString_Length()
    * @model default="0"
    * @generated
    */
	int getLength();

	/**
    * Sets the value of the '{@link edu.teco.automata.Automata.TString#getLength <em>Length</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Length</em>' attribute.
    * @see #getLength()
    * @generated
    */
	void setLength(int value);

} // TString
