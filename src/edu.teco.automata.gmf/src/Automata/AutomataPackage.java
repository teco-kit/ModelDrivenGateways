/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see Automata.AutomataFactory
 * @model kind="package"
 * @generated
 */
public interface AutomataPackage extends EPackage {
   /**
	 * The package name.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   String eNAME = "Automata";

   /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   String eNS_URI = "http://www.teco.edu/automata";

   /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   String eNS_PREFIX = "Automata";

   /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   AutomataPackage eINSTANCE = Automata.impl.AutomataPackageImpl.init();

   /**
	 * The meta object id for the '{@link Automata.impl.StateMachineImpl <em>State Machine</em>}' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see Automata.impl.StateMachineImpl
	 * @see Automata.impl.AutomataPackageImpl#getStateMachine()
	 * @generated
	 */
   int STATE_MACHINE = 0;

   /**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int STATE_MACHINE__STATES = 0;

   /**
	 * The number of structural features of the '<em>State Machine</em>' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int STATE_MACHINE_FEATURE_COUNT = 1;

   /**
	 * The meta object id for the '{@link Automata.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see Automata.impl.StateImpl
	 * @see Automata.impl.AutomataPackageImpl#getState()
	 * @generated
	 */
   int STATE = 1;

   /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int STATE__NAME = 0;

   /**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int STATE_FEATURE_COUNT = 1;

   /**
	 * The meta object id for the '{@link Automata.impl.SimpleStateImpl <em>Simple State</em>}' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see Automata.impl.SimpleStateImpl
	 * @see Automata.impl.AutomataPackageImpl#getSimpleState()
	 * @generated
	 */
   int SIMPLE_STATE = 2;

   /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__NAME = STATE__NAME;

   /**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__OUT = STATE_FEATURE_COUNT + 0;

   /**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__LOWER_BOUND = STATE_FEATURE_COUNT + 1;

   /**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__UPPER_BOUND = STATE_FEATURE_COUNT + 2;

   /**
	 * The feature id for the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__DEPTH = STATE_FEATURE_COUNT + 3;

   /**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__TYPE = STATE_FEATURE_COUNT + 4;

   /**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE__KIND = STATE_FEATURE_COUNT + 5;

   /**
	 * The number of structural features of the '<em>Simple State</em>' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int SIMPLE_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 6;

   /**
	 * The meta object id for the '{@link Automata.impl.StartStateImpl <em>Start State</em>}' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see Automata.impl.StartStateImpl
	 * @see Automata.impl.AutomataPackageImpl#getStartState()
	 * @generated
	 */
   int START_STATE = 3;

   /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int START_STATE__NAME = STATE__NAME;

   /**
	 * The feature id for the '<em><b>Out</b></em>' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int START_STATE__OUT = STATE_FEATURE_COUNT + 0;

   /**
	 * The number of structural features of the '<em>Start State</em>' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int START_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 1;

   /**
	 * The meta object id for the '{@link Automata.impl.StopStateImpl <em>Stop State</em>}' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see Automata.impl.StopStateImpl
	 * @see Automata.impl.AutomataPackageImpl#getStopState()
	 * @generated
	 */
   int STOP_STATE = 4;

   /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int STOP_STATE__NAME = STATE__NAME;

   /**
	 * The number of structural features of the '<em>Stop State</em>' class.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
   int STOP_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

   /**
	 * The meta object id for the '{@link Automata.impl.DataTypeImpl <em>Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.DataTypeImpl
	 * @see Automata.impl.AutomataPackageImpl#getDataType()
	 * @generated
	 */
	int DATA_TYPE = 5;

			/**
	 * The number of structural features of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_FEATURE_COUNT = 0;

			/**
	 * The meta object id for the '{@link Automata.impl.complexTypeImpl <em>complex Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.complexTypeImpl
	 * @see Automata.impl.AutomataPackageImpl#getcomplexType()
	 * @generated
	 */
	int COMPLEX_TYPE = 6;

			/**
	 * The number of structural features of the '<em>complex Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The meta object id for the '{@link Automata.impl.TByteImpl <em>TByte</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TByteImpl
	 * @see Automata.impl.AutomataPackageImpl#getTByte()
	 * @generated
	 */
	int TBYTE = 7;

			/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBYTE__MIN = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBYTE__MAX = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * The number of structural features of the '<em>TByte</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBYTE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 2;

			/**
	 * The meta object id for the '{@link Automata.impl.TCharImpl <em>TChar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TCharImpl
	 * @see Automata.impl.AutomataPackageImpl#getTChar()
	 * @generated
	 */
	int TCHAR = 8;

			/**
	 * The number of structural features of the '<em>TChar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHAR_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The meta object id for the '{@link Automata.impl.TShortImpl <em>TShort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TShortImpl
	 * @see Automata.impl.AutomataPackageImpl#getTShort()
	 * @generated
	 */
	int TSHORT = 9;

			/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSHORT__MIN = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSHORT__MAX = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * The number of structural features of the '<em>TShort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSHORT_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 2;

			/**
	 * The meta object id for the '{@link Automata.impl.TIntImpl <em>TInt</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TIntImpl
	 * @see Automata.impl.AutomataPackageImpl#getTInt()
	 * @generated
	 */
	int TINT = 10;

			/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINT__MIN = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINT__MAX = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * The number of structural features of the '<em>TInt</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINT_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 2;

			/**
	 * The meta object id for the '{@link Automata.impl.TLongImpl <em>TLong</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TLongImpl
	 * @see Automata.impl.AutomataPackageImpl#getTLong()
	 * @generated
	 */
	int TLONG = 11;

			/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLONG__MIN = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLONG__MAX = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * The number of structural features of the '<em>TLong</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLONG_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 2;

			/**
	 * The meta object id for the '{@link Automata.impl.TFloatImpl <em>TFloat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TFloatImpl
	 * @see Automata.impl.AutomataPackageImpl#getTFloat()
	 * @generated
	 */
	int TFLOAT = 12;

			/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT__MIN = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT__MAX = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * The feature id for the '<em><b>Fraction Digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT__FRACTION_DIGITS = DATA_TYPE_FEATURE_COUNT + 2;

			/**
	 * The number of structural features of the '<em>TFloat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 3;

			/**
	 * The meta object id for the '{@link Automata.impl.TDoubleImpl <em>TDouble</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TDoubleImpl
	 * @see Automata.impl.AutomataPackageImpl#getTDouble()
	 * @generated
	 */
	int TDOUBLE = 13;

			/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOUBLE__MIN = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOUBLE__MAX = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * The feature id for the '<em><b>Fraction Digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOUBLE__FRACTION_DIGITS = DATA_TYPE_FEATURE_COUNT + 2;

			/**
	 * The number of structural features of the '<em>TDouble</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOUBLE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 3;

			/**
	 * The meta object id for the '{@link Automata.impl.TStringImpl <em>TString</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Automata.impl.TStringImpl
	 * @see Automata.impl.AutomataPackageImpl#getTString()
	 * @generated
	 */
	int TSTRING = 14;

			/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTRING__LENGTH = DATA_TYPE_FEATURE_COUNT + 0;

			/**
	 * The number of structural features of the '<em>TString</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTRING_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 1;

			/**
	 * Returns the meta object for class '{@link Automata.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine</em>'.
	 * @see Automata.StateMachine
	 * @generated
	 */
   EClass getStateMachine();

   /**
	 * Returns the meta object for the containment reference list '{@link Automata.StateMachine#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see Automata.StateMachine#getStates()
	 * @see #getStateMachine()
	 * @generated
	 */
   EReference getStateMachine_States();

   /**
	 * Returns the meta object for class '{@link Automata.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see Automata.State
	 * @generated
	 */
   EClass getState();

   /**
	 * Returns the meta object for the attribute '{@link Automata.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Automata.State#getName()
	 * @see #getState()
	 * @generated
	 */
   EAttribute getState_Name();

   /**
	 * Returns the meta object for class '{@link Automata.SimpleState <em>Simple State</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple State</em>'.
	 * @see Automata.SimpleState
	 * @generated
	 */
   EClass getSimpleState();

   /**
	 * Returns the meta object for the reference list '{@link Automata.SimpleState#getOut <em>Out</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out</em>'.
	 * @see Automata.SimpleState#getOut()
	 * @see #getSimpleState()
	 * @generated
	 */
   EReference getSimpleState_Out();

   /**
	 * Returns the meta object for the attribute '{@link Automata.SimpleState#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see Automata.SimpleState#getLowerBound()
	 * @see #getSimpleState()
	 * @generated
	 */
   EAttribute getSimpleState_LowerBound();

   /**
	 * Returns the meta object for the attribute '{@link Automata.SimpleState#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see Automata.SimpleState#getUpperBound()
	 * @see #getSimpleState()
	 * @generated
	 */
   EAttribute getSimpleState_UpperBound();

   /**
	 * Returns the meta object for the attribute '{@link Automata.SimpleState#getDepth <em>Depth</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Depth</em>'.
	 * @see Automata.SimpleState#getDepth()
	 * @see #getSimpleState()
	 * @generated
	 */
   EAttribute getSimpleState_Depth();

   /**
	 * Returns the meta object for the containment reference '{@link Automata.SimpleState#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see Automata.SimpleState#getType()
	 * @see #getSimpleState()
	 * @generated
	 */
   EReference getSimpleState_Type();

   /**
	 * Returns the meta object for the attribute '{@link Automata.SimpleState#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see Automata.SimpleState#getKind()
	 * @see #getSimpleState()
	 * @generated
	 */
   EAttribute getSimpleState_Kind();

   /**
	 * Returns the meta object for class '{@link Automata.StartState <em>Start State</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start State</em>'.
	 * @see Automata.StartState
	 * @generated
	 */
   EClass getStartState();

   /**
	 * Returns the meta object for the reference '{@link Automata.StartState#getOut <em>Out</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Out</em>'.
	 * @see Automata.StartState#getOut()
	 * @see #getStartState()
	 * @generated
	 */
   EReference getStartState_Out();

   /**
	 * Returns the meta object for class '{@link Automata.StopState <em>Stop State</em>}'.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stop State</em>'.
	 * @see Automata.StopState
	 * @generated
	 */
   EClass getStopState();

   /**
	 * Returns the meta object for class '{@link Automata.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type</em>'.
	 * @see Automata.DataType
	 * @generated
	 */
	EClass getDataType();

			/**
	 * Returns the meta object for class '{@link Automata.complexType <em>complex Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>complex Type</em>'.
	 * @see Automata.complexType
	 * @generated
	 */
	EClass getcomplexType();

			/**
	 * Returns the meta object for class '{@link Automata.TByte <em>TByte</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TByte</em>'.
	 * @see Automata.TByte
	 * @generated
	 */
	EClass getTByte();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TByte#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see Automata.TByte#getMin()
	 * @see #getTByte()
	 * @generated
	 */
	EAttribute getTByte_Min();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TByte#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see Automata.TByte#getMax()
	 * @see #getTByte()
	 * @generated
	 */
	EAttribute getTByte_Max();

			/**
	 * Returns the meta object for class '{@link Automata.TChar <em>TChar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TChar</em>'.
	 * @see Automata.TChar
	 * @generated
	 */
	EClass getTChar();

			/**
	 * Returns the meta object for class '{@link Automata.TShort <em>TShort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TShort</em>'.
	 * @see Automata.TShort
	 * @generated
	 */
	EClass getTShort();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TShort#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see Automata.TShort#getMin()
	 * @see #getTShort()
	 * @generated
	 */
	EAttribute getTShort_Min();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TShort#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see Automata.TShort#getMax()
	 * @see #getTShort()
	 * @generated
	 */
	EAttribute getTShort_Max();

			/**
	 * Returns the meta object for class '{@link Automata.TInt <em>TInt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TInt</em>'.
	 * @see Automata.TInt
	 * @generated
	 */
	EClass getTInt();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TInt#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see Automata.TInt#getMin()
	 * @see #getTInt()
	 * @generated
	 */
	EAttribute getTInt_Min();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TInt#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see Automata.TInt#getMax()
	 * @see #getTInt()
	 * @generated
	 */
	EAttribute getTInt_Max();

			/**
	 * Returns the meta object for class '{@link Automata.TLong <em>TLong</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TLong</em>'.
	 * @see Automata.TLong
	 * @generated
	 */
	EClass getTLong();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TLong#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see Automata.TLong#getMin()
	 * @see #getTLong()
	 * @generated
	 */
	EAttribute getTLong_Min();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TLong#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see Automata.TLong#getMax()
	 * @see #getTLong()
	 * @generated
	 */
	EAttribute getTLong_Max();

			/**
	 * Returns the meta object for class '{@link Automata.TFloat <em>TFloat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TFloat</em>'.
	 * @see Automata.TFloat
	 * @generated
	 */
	EClass getTFloat();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TFloat#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see Automata.TFloat#getMin()
	 * @see #getTFloat()
	 * @generated
	 */
	EAttribute getTFloat_Min();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TFloat#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see Automata.TFloat#getMax()
	 * @see #getTFloat()
	 * @generated
	 */
	EAttribute getTFloat_Max();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TFloat#getFractionDigits <em>Fraction Digits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fraction Digits</em>'.
	 * @see Automata.TFloat#getFractionDigits()
	 * @see #getTFloat()
	 * @generated
	 */
	EAttribute getTFloat_FractionDigits();

			/**
	 * Returns the meta object for class '{@link Automata.TDouble <em>TDouble</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TDouble</em>'.
	 * @see Automata.TDouble
	 * @generated
	 */
	EClass getTDouble();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TDouble#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see Automata.TDouble#getMin()
	 * @see #getTDouble()
	 * @generated
	 */
	EAttribute getTDouble_Min();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TDouble#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see Automata.TDouble#getMax()
	 * @see #getTDouble()
	 * @generated
	 */
	EAttribute getTDouble_Max();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TDouble#getFractionDigits <em>Fraction Digits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fraction Digits</em>'.
	 * @see Automata.TDouble#getFractionDigits()
	 * @see #getTDouble()
	 * @generated
	 */
	EAttribute getTDouble_FractionDigits();

			/**
	 * Returns the meta object for class '{@link Automata.TString <em>TString</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TString</em>'.
	 * @see Automata.TString
	 * @generated
	 */
	EClass getTString();

			/**
	 * Returns the meta object for the attribute '{@link Automata.TString#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see Automata.TString#getLength()
	 * @see #getTString()
	 * @generated
	 */
	EAttribute getTString_Length();

			/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
   AutomataFactory getAutomataFactory();

   /**
	 * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
	 * @generated
	 */
   interface Literals {
      /**
		 * The meta object literal for the '{@link Automata.impl.StateMachineImpl <em>State Machine</em>}' class.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @see Automata.impl.StateMachineImpl
		 * @see Automata.impl.AutomataPackageImpl#getStateMachine()
		 * @generated
		 */
      EClass STATE_MACHINE = eINSTANCE.getStateMachine();

      /**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EReference STATE_MACHINE__STATES = eINSTANCE.getStateMachine_States();

      /**
		 * The meta object literal for the '{@link Automata.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @see Automata.impl.StateImpl
		 * @see Automata.impl.AutomataPackageImpl#getState()
		 * @generated
		 */
      EClass STATE = eINSTANCE.getState();

      /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EAttribute STATE__NAME = eINSTANCE.getState_Name();

      /**
		 * The meta object literal for the '{@link Automata.impl.SimpleStateImpl <em>Simple State</em>}' class.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @see Automata.impl.SimpleStateImpl
		 * @see Automata.impl.AutomataPackageImpl#getSimpleState()
		 * @generated
		 */
      EClass SIMPLE_STATE = eINSTANCE.getSimpleState();

      /**
		 * The meta object literal for the '<em><b>Out</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EReference SIMPLE_STATE__OUT = eINSTANCE.getSimpleState_Out();

      /**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EAttribute SIMPLE_STATE__LOWER_BOUND = eINSTANCE.getSimpleState_LowerBound();

      /**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EAttribute SIMPLE_STATE__UPPER_BOUND = eINSTANCE.getSimpleState_UpperBound();

      /**
		 * The meta object literal for the '<em><b>Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EAttribute SIMPLE_STATE__DEPTH = eINSTANCE.getSimpleState_Depth();

      /**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EReference SIMPLE_STATE__TYPE = eINSTANCE.getSimpleState_Type();

      /**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EAttribute SIMPLE_STATE__KIND = eINSTANCE.getSimpleState_Kind();

      /**
		 * The meta object literal for the '{@link Automata.impl.StartStateImpl <em>Start State</em>}' class.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @see Automata.impl.StartStateImpl
		 * @see Automata.impl.AutomataPackageImpl#getStartState()
		 * @generated
		 */
      EClass START_STATE = eINSTANCE.getStartState();

      /**
		 * The meta object literal for the '<em><b>Out</b></em>' reference feature.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @generated
		 */
      EReference START_STATE__OUT = eINSTANCE.getStartState_Out();

      /**
		 * The meta object literal for the '{@link Automata.impl.StopStateImpl <em>Stop State</em>}' class.
		 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
		 * @see Automata.impl.StopStateImpl
		 * @see Automata.impl.AutomataPackageImpl#getStopState()
		 * @generated
		 */
      EClass STOP_STATE = eINSTANCE.getStopState();

      /**
		 * The meta object literal for the '{@link Automata.impl.DataTypeImpl <em>Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.DataTypeImpl
		 * @see Automata.impl.AutomataPackageImpl#getDataType()
		 * @generated
		 */
		EClass DATA_TYPE = eINSTANCE.getDataType();

						/**
		 * The meta object literal for the '{@link Automata.impl.complexTypeImpl <em>complex Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.complexTypeImpl
		 * @see Automata.impl.AutomataPackageImpl#getcomplexType()
		 * @generated
		 */
		EClass COMPLEX_TYPE = eINSTANCE.getcomplexType();

						/**
		 * The meta object literal for the '{@link Automata.impl.TByteImpl <em>TByte</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TByteImpl
		 * @see Automata.impl.AutomataPackageImpl#getTByte()
		 * @generated
		 */
		EClass TBYTE = eINSTANCE.getTByte();

						/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBYTE__MIN = eINSTANCE.getTByte_Min();

						/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBYTE__MAX = eINSTANCE.getTByte_Max();

						/**
		 * The meta object literal for the '{@link Automata.impl.TCharImpl <em>TChar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TCharImpl
		 * @see Automata.impl.AutomataPackageImpl#getTChar()
		 * @generated
		 */
		EClass TCHAR = eINSTANCE.getTChar();

						/**
		 * The meta object literal for the '{@link Automata.impl.TShortImpl <em>TShort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TShortImpl
		 * @see Automata.impl.AutomataPackageImpl#getTShort()
		 * @generated
		 */
		EClass TSHORT = eINSTANCE.getTShort();

						/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSHORT__MIN = eINSTANCE.getTShort_Min();

						/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSHORT__MAX = eINSTANCE.getTShort_Max();

						/**
		 * The meta object literal for the '{@link Automata.impl.TIntImpl <em>TInt</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TIntImpl
		 * @see Automata.impl.AutomataPackageImpl#getTInt()
		 * @generated
		 */
		EClass TINT = eINSTANCE.getTInt();

						/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINT__MIN = eINSTANCE.getTInt_Min();

						/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINT__MAX = eINSTANCE.getTInt_Max();

						/**
		 * The meta object literal for the '{@link Automata.impl.TLongImpl <em>TLong</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TLongImpl
		 * @see Automata.impl.AutomataPackageImpl#getTLong()
		 * @generated
		 */
		EClass TLONG = eINSTANCE.getTLong();

						/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLONG__MIN = eINSTANCE.getTLong_Min();

						/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLONG__MAX = eINSTANCE.getTLong_Max();

						/**
		 * The meta object literal for the '{@link Automata.impl.TFloatImpl <em>TFloat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TFloatImpl
		 * @see Automata.impl.AutomataPackageImpl#getTFloat()
		 * @generated
		 */
		EClass TFLOAT = eINSTANCE.getTFloat();

						/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOAT__MIN = eINSTANCE.getTFloat_Min();

						/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOAT__MAX = eINSTANCE.getTFloat_Max();

						/**
		 * The meta object literal for the '<em><b>Fraction Digits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOAT__FRACTION_DIGITS = eINSTANCE.getTFloat_FractionDigits();

						/**
		 * The meta object literal for the '{@link Automata.impl.TDoubleImpl <em>TDouble</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TDoubleImpl
		 * @see Automata.impl.AutomataPackageImpl#getTDouble()
		 * @generated
		 */
		EClass TDOUBLE = eINSTANCE.getTDouble();

						/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOUBLE__MIN = eINSTANCE.getTDouble_Min();

						/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOUBLE__MAX = eINSTANCE.getTDouble_Max();

						/**
		 * The meta object literal for the '<em><b>Fraction Digits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOUBLE__FRACTION_DIGITS = eINSTANCE.getTDouble_FractionDigits();

						/**
		 * The meta object literal for the '{@link Automata.impl.TStringImpl <em>TString</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Automata.impl.TStringImpl
		 * @see Automata.impl.AutomataPackageImpl#getTString()
		 * @generated
		 */
		EClass TSTRING = eINSTANCE.getTString();

						/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSTRING__LENGTH = eINSTANCE.getTString_Length();

   }

} //AutomataPackage
