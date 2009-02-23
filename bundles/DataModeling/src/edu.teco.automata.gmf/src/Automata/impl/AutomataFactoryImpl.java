/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata.impl;

import Automata.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AutomataFactoryImpl extends EFactoryImpl implements AutomataFactory {
   /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public static AutomataFactory init() {
		try {
			AutomataFactory theAutomataFactory = (AutomataFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.teco.edu/automata"); 
			if (theAutomataFactory != null) {
				return theAutomataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AutomataFactoryImpl();
	}

   /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public AutomataFactoryImpl() {
		super();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AutomataPackage.STATE_MACHINE: return createStateMachine();
			case AutomataPackage.SIMPLE_STATE: return createSimpleState();
			case AutomataPackage.START_STATE: return createStartState();
			case AutomataPackage.STOP_STATE: return createStopState();
			case AutomataPackage.COMPLEX_TYPE: return createcomplexType();
			case AutomataPackage.TBYTE: return createTByte();
			case AutomataPackage.TCHAR: return createTChar();
			case AutomataPackage.TSHORT: return createTShort();
			case AutomataPackage.TINT: return createTInt();
			case AutomataPackage.TLONG: return createTLong();
			case AutomataPackage.TFLOAT: return createTFloat();
			case AutomataPackage.TDOUBLE: return createTDouble();
			case AutomataPackage.TSTRING: return createTString();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public StateMachine createStateMachine() {
		StateMachineImpl stateMachine = new StateMachineImpl();
		return stateMachine;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public SimpleState createSimpleState() {
		SimpleStateImpl simpleState = new SimpleStateImpl();
		return simpleState;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public StartState createStartState() {
		StartStateImpl startState = new StartStateImpl();
		return startState;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public StopState createStopState() {
		StopStateImpl stopState = new StopStateImpl();
		return stopState;
	}

   /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public complexType createcomplexType() {
		complexTypeImpl complexType = new complexTypeImpl();
		return complexType;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TByte createTByte() {
		TByteImpl tByte = new TByteImpl();
		return tByte;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChar createTChar() {
		TCharImpl tChar = new TCharImpl();
		return tChar;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TShort createTShort() {
		TShortImpl tShort = new TShortImpl();
		return tShort;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInt createTInt() {
		TIntImpl tInt = new TIntImpl();
		return tInt;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLong createTLong() {
		TLongImpl tLong = new TLongImpl();
		return tLong;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloat createTFloat() {
		TFloatImpl tFloat = new TFloatImpl();
		return tFloat;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDouble createTDouble() {
		TDoubleImpl tDouble = new TDoubleImpl();
		return tDouble;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TString createTString() {
		TStringImpl tString = new TStringImpl();
		return tString;
	}

			/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public AutomataPackage getAutomataPackage() {
		return (AutomataPackage)getEPackage();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
   @Deprecated
   public static AutomataPackage getPackage() {
		return AutomataPackage.eINSTANCE;
	}

} //AutomataFactoryImpl
