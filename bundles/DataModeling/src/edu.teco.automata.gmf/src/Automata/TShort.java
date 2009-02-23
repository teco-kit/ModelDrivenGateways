/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TShort</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Automata.TShort#getMin <em>Min</em>}</li>
 *   <li>{@link Automata.TShort#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see Automata.AutomataPackage#getTShort()
 * @model
 * @generated
 */
public interface TShort extends DataType {
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
	 * @see Automata.AutomataPackage#getTShort_Min()
	 * @model default="0"
	 * @generated
	 */
	int getMin();

	/**
	 * Sets the value of the '{@link Automata.TShort#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(int value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(int)
	 * @see Automata.AutomataPackage#getTShort_Max()
	 * @model
	 * @generated
	 */
	int getMax();

	/**
	 * Sets the value of the '{@link Automata.TShort#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(int value);

} // TShort
