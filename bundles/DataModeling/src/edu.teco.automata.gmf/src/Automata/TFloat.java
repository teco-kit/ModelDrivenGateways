/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TFloat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Automata.TFloat#getMin <em>Min</em>}</li>
 *   <li>{@link Automata.TFloat#getMax <em>Max</em>}</li>
 *   <li>{@link Automata.TFloat#getFractionDigits <em>Fraction Digits</em>}</li>
 * </ul>
 * </p>
 *
 * @see Automata.AutomataPackage#getTFloat()
 * @model
 * @generated
 */
public interface TFloat extends DataType {
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
	 * @see Automata.AutomataPackage#getTFloat_Min()
	 * @model default="0"
	 * @generated
	 */
	int getMin();

	/**
	 * Sets the value of the '{@link Automata.TFloat#getMin <em>Min</em>}' attribute.
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
	 * @see Automata.AutomataPackage#getTFloat_Max()
	 * @model default="0"
	 * @generated
	 */
	int getMax();

	/**
	 * Sets the value of the '{@link Automata.TFloat#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(int value);

	/**
	 * Returns the value of the '<em><b>Fraction Digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fraction Digits</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fraction Digits</em>' attribute.
	 * @see #setFractionDigits(int)
	 * @see Automata.AutomataPackage#getTFloat_FractionDigits()
	 * @model
	 * @generated
	 */
	int getFractionDigits();

	/**
	 * Sets the value of the '{@link Automata.TFloat#getFractionDigits <em>Fraction Digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fraction Digits</em>' attribute.
	 * @see #getFractionDigits()
	 * @generated
	 */
	void setFractionDigits(int value);

} // TFloat
