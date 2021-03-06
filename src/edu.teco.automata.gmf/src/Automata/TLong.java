/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TLong</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Automata.TLong#getMin <em>Min</em>}</li>
 *   <li>{@link Automata.TLong#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see Automata.AutomataPackage#getTLong()
 * @model
 * @generated
 */
public interface TLong extends DataType {
	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(int)
	 * @see Automata.AutomataPackage#getTLong_Min()
	 * @model default="0"
	 * @generated
	 */
	int getMin();

	/**
	 * Sets the value of the '{@link Automata.TLong#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(int value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(int)
	 * @see Automata.AutomataPackage#getTLong_Max()
	 * @model default="0"
	 * @generated
	 */
	int getMax();

	/**
	 * Sets the value of the '{@link Automata.TLong#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(int value);

} // TLong
