/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata.impl;

import Automata.AutomataFactory;
import Automata.AutomataPackage;
import Automata.DataType;
import Automata.DataTypes;
import Automata.SimpleState;
import Automata.StartState;
import Automata.State;
import Automata.StateMachine;
import Automata.StopState;

import Automata.TByte;
import Automata.TChar;
import Automata.TDouble;
import Automata.TFloat;
import Automata.TInt;
import Automata.TLong;
import Automata.TShort;
import Automata.TString;
import Automata.complexType;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AutomataPackageImpl extends EPackageImpl implements AutomataPackage {
   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private EClass stateMachineEClass = null;

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private EClass stateEClass = null;

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private EClass simpleStateEClass = null;

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private EClass startStateEClass = null;

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private EClass stopStateEClass = null;

   /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass complexTypeEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tByteEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCharEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tShortEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tIntEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tLongEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tFloatEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDoubleEClass = null;

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tStringEClass = null;

			/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see Automata.AutomataPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
   private AutomataPackageImpl() {
		super(eNS_URI, AutomataFactory.eINSTANCE);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private static boolean isInited = false;

   /**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
   public static AutomataPackage init() {
		if (isInited) return (AutomataPackage)EPackage.Registry.INSTANCE.getEPackage(AutomataPackage.eNS_URI);

		// Obtain or create and register package
		AutomataPackageImpl theAutomataPackage = (AutomataPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof AutomataPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new AutomataPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theAutomataPackage.createPackageContents();

		// Initialize created meta-data
		theAutomataPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAutomataPackage.freeze();

		return theAutomataPackage;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EClass getStateMachine() {
		return stateMachineEClass;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EReference getStateMachine_States() {
		return (EReference)stateMachineEClass.getEStructuralFeatures().get(0);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EClass getState() {
		return stateEClass;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EAttribute getState_Name() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(0);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EClass getSimpleState() {
		return simpleStateEClass;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EReference getSimpleState_Out() {
		return (EReference)simpleStateEClass.getEStructuralFeatures().get(0);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EAttribute getSimpleState_LowerBound() {
		return (EAttribute)simpleStateEClass.getEStructuralFeatures().get(1);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EAttribute getSimpleState_UpperBound() {
		return (EAttribute)simpleStateEClass.getEStructuralFeatures().get(2);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EAttribute getSimpleState_Depth() {
		return (EAttribute)simpleStateEClass.getEStructuralFeatures().get(3);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EReference getSimpleState_Type() {
		return (EReference)simpleStateEClass.getEStructuralFeatures().get(4);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EAttribute getSimpleState_Kind() {
		return (EAttribute)simpleStateEClass.getEStructuralFeatures().get(5);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EClass getStartState() {
		return startStateEClass;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EReference getStartState_Out() {
		return (EReference)startStateEClass.getEStructuralFeatures().get(0);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EClass getStopState() {
		return stopStateEClass;
	}

   /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataType() {
		return dataTypeEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getcomplexType() {
		return complexTypeEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTByte() {
		return tByteEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTByte_Min() {
		return (EAttribute)tByteEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTByte_Max() {
		return (EAttribute)tByteEClass.getEStructuralFeatures().get(1);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTChar() {
		return tCharEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTShort() {
		return tShortEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTShort_Min() {
		return (EAttribute)tShortEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTShort_Max() {
		return (EAttribute)tShortEClass.getEStructuralFeatures().get(1);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTInt() {
		return tIntEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInt_Min() {
		return (EAttribute)tIntEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInt_Max() {
		return (EAttribute)tIntEClass.getEStructuralFeatures().get(1);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTLong() {
		return tLongEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLong_Min() {
		return (EAttribute)tLongEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLong_Max() {
		return (EAttribute)tLongEClass.getEStructuralFeatures().get(1);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTFloat() {
		return tFloatEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFloat_Min() {
		return (EAttribute)tFloatEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFloat_Max() {
		return (EAttribute)tFloatEClass.getEStructuralFeatures().get(1);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFloat_FractionDigits() {
		return (EAttribute)tFloatEClass.getEStructuralFeatures().get(2);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDouble() {
		return tDoubleEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDouble_Min() {
		return (EAttribute)tDoubleEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDouble_Max() {
		return (EAttribute)tDoubleEClass.getEStructuralFeatures().get(1);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDouble_FractionDigits() {
		return (EAttribute)tDoubleEClass.getEStructuralFeatures().get(2);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTString() {
		return tStringEClass;
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTString_Length() {
		return (EAttribute)tStringEClass.getEStructuralFeatures().get(0);
	}

			/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public AutomataFactory getAutomataFactory() {
		return (AutomataFactory)getEFactoryInstance();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private boolean isCreated = false;

   /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		stateMachineEClass = createEClass(STATE_MACHINE);
		createEReference(stateMachineEClass, STATE_MACHINE__STATES);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__NAME);

		simpleStateEClass = createEClass(SIMPLE_STATE);
		createEReference(simpleStateEClass, SIMPLE_STATE__OUT);
		createEAttribute(simpleStateEClass, SIMPLE_STATE__LOWER_BOUND);
		createEAttribute(simpleStateEClass, SIMPLE_STATE__UPPER_BOUND);
		createEAttribute(simpleStateEClass, SIMPLE_STATE__DEPTH);
		createEReference(simpleStateEClass, SIMPLE_STATE__TYPE);
		createEAttribute(simpleStateEClass, SIMPLE_STATE__KIND);

		startStateEClass = createEClass(START_STATE);
		createEReference(startStateEClass, START_STATE__OUT);

		stopStateEClass = createEClass(STOP_STATE);

		dataTypeEClass = createEClass(DATA_TYPE);

		complexTypeEClass = createEClass(COMPLEX_TYPE);

		tByteEClass = createEClass(TBYTE);
		createEAttribute(tByteEClass, TBYTE__MIN);
		createEAttribute(tByteEClass, TBYTE__MAX);

		tCharEClass = createEClass(TCHAR);

		tShortEClass = createEClass(TSHORT);
		createEAttribute(tShortEClass, TSHORT__MIN);
		createEAttribute(tShortEClass, TSHORT__MAX);

		tIntEClass = createEClass(TINT);
		createEAttribute(tIntEClass, TINT__MIN);
		createEAttribute(tIntEClass, TINT__MAX);

		tLongEClass = createEClass(TLONG);
		createEAttribute(tLongEClass, TLONG__MIN);
		createEAttribute(tLongEClass, TLONG__MAX);

		tFloatEClass = createEClass(TFLOAT);
		createEAttribute(tFloatEClass, TFLOAT__MIN);
		createEAttribute(tFloatEClass, TFLOAT__MAX);
		createEAttribute(tFloatEClass, TFLOAT__FRACTION_DIGITS);

		tDoubleEClass = createEClass(TDOUBLE);
		createEAttribute(tDoubleEClass, TDOUBLE__MIN);
		createEAttribute(tDoubleEClass, TDOUBLE__MAX);
		createEAttribute(tDoubleEClass, TDOUBLE__FRACTION_DIGITS);

		tStringEClass = createEClass(TSTRING);
		createEAttribute(tStringEClass, TSTRING__LENGTH);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   private boolean isInitialized = false;

   /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		simpleStateEClass.getESuperTypes().add(this.getState());
		startStateEClass.getESuperTypes().add(this.getState());
		stopStateEClass.getESuperTypes().add(this.getState());
		complexTypeEClass.getESuperTypes().add(this.getDataType());
		tByteEClass.getESuperTypes().add(this.getDataType());
		tCharEClass.getESuperTypes().add(this.getDataType());
		tShortEClass.getESuperTypes().add(this.getDataType());
		tIntEClass.getESuperTypes().add(this.getDataType());
		tLongEClass.getESuperTypes().add(this.getDataType());
		tFloatEClass.getESuperTypes().add(this.getDataType());
		tDoubleEClass.getESuperTypes().add(this.getDataType());
		tStringEClass.getESuperTypes().add(this.getDataType());

		// Initialize classes and features; add operations and parameters
		initEClass(stateMachineEClass, StateMachine.class, "StateMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateMachine_States(), this.getState(), null, "states", null, 1, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_Name(), ecorePackage.getEString(), "name", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simpleStateEClass, SimpleState.class, "SimpleState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimpleState_Out(), this.getState(), null, "out", null, 1, -1, SimpleState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimpleState_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 1, 1, SimpleState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimpleState_UpperBound(), ecorePackage.getEInt(), "upperBound", null, 1, 1, SimpleState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimpleState_Depth(), ecorePackage.getEInt(), "depth", null, 1, 1, SimpleState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSimpleState_Type(), this.getDataType(), null, "type", null, 1, 1, SimpleState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimpleState_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, SimpleState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startStateEClass, StartState.class, "StartState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStartState_Out(), this.getState(), null, "out", null, 1, 1, StartState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stopStateEClass, StopState.class, "StopState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataTypeEClass, DataType.class, "DataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(complexTypeEClass, complexType.class, "complexType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tByteEClass, TByte.class, "TByte", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTByte_Min(), ecorePackage.getEInt(), "min", "0", 0, 1, TByte.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTByte_Max(), ecorePackage.getEInt(), "max", "0", 0, 1, TByte.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tCharEClass, TChar.class, "TChar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tShortEClass, TShort.class, "TShort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTShort_Min(), ecorePackage.getEInt(), "min", "0", 0, 1, TShort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTShort_Max(), ecorePackage.getEInt(), "max", null, 0, 1, TShort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tIntEClass, TInt.class, "TInt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTInt_Min(), ecorePackage.getEInt(), "min", "0", 0, 1, TInt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTInt_Max(), ecorePackage.getEInt(), "max", "0", 0, 1, TInt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tLongEClass, TLong.class, "TLong", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTLong_Min(), ecorePackage.getEInt(), "min", "0", 0, 1, TLong.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTLong_Max(), ecorePackage.getEInt(), "max", "0", 0, 1, TLong.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tFloatEClass, TFloat.class, "TFloat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTFloat_Min(), ecorePackage.getEInt(), "min", "0", 0, 1, TFloat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTFloat_Max(), ecorePackage.getEInt(), "max", "0", 0, 1, TFloat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTFloat_FractionDigits(), ecorePackage.getEInt(), "fractionDigits", null, 0, 1, TFloat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tDoubleEClass, TDouble.class, "TDouble", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTDouble_Min(), ecorePackage.getEInt(), "min", "0", 0, 1, TDouble.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTDouble_Max(), ecorePackage.getEInt(), "max", "0", 0, 1, TDouble.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTDouble_FractionDigits(), ecorePackage.getEInt(), "fractionDigits", null, 0, 1, TDouble.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tStringEClass, TString.class, "TString", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTString_Length(), ecorePackage.getEInt(), "length", "0", 0, 1, TString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/ocl/examples/OCL
		createOCLAnnotations();
	}

			/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (stateMachineEClass, 
		   source, 
		   new String[] {
			 "constraint", "justOnce"
		   });	
	}

			/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ocl/examples/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/ocl/examples/OCL";			
		addAnnotation
		  (stateMachineEClass, 
		   source, 
		   new String[] {
			 "justOnce", "states.typeSelect(StartState).length == 1"
		   });
	}

} //AutomataPackageImpl
