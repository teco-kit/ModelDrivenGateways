/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.teco.automata.Automata.util;

import edu.teco.automata.Automata.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see edu.teco.automata.Automata.AutomataPackage
 * @generated
 */
public class AutomataAdapterFactory extends AdapterFactoryImpl {
	/**
    * The cached model package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static AutomataPackage modelPackage;

	/**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public AutomataAdapterFactory() {
      if (modelPackage == null) {
         modelPackage = AutomataPackage.eINSTANCE;
      }
   }

	/**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
	@Override
	public boolean isFactoryForType(Object object) {
      if (object == modelPackage) {
         return true;
      }
      if (object instanceof EObject) {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

	/**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected AutomataSwitch<Adapter> modelSwitch =
		new AutomataSwitch<Adapter>() {
         @Override
         public Adapter caseStateMachine(StateMachine object) {
            return createStateMachineAdapter();
         }
         @Override
         public Adapter caseState(State object) {
            return createStateAdapter();
         }
         @Override
         public Adapter caseSimpleState(SimpleState object) {
            return createSimpleStateAdapter();
         }
         @Override
         public Adapter caseStartState(StartState object) {
            return createStartStateAdapter();
         }
         @Override
         public Adapter caseStopState(StopState object) {
            return createStopStateAdapter();
         }
         @Override
         public Adapter caseDataType(DataType object) {
            return createDataTypeAdapter();
         }
         @Override
         public Adapter casecomplexType(complexType object) {
            return createcomplexTypeAdapter();
         }
         @Override
         public Adapter caseTByte(TByte object) {
            return createTByteAdapter();
         }
         @Override
         public Adapter caseTChar(TChar object) {
            return createTCharAdapter();
         }
         @Override
         public Adapter caseTShort(TShort object) {
            return createTShortAdapter();
         }
         @Override
         public Adapter caseTInt(TInt object) {
            return createTIntAdapter();
         }
         @Override
         public Adapter caseTLong(TLong object) {
            return createTLongAdapter();
         }
         @Override
         public Adapter caseTFloat(TFloat object) {
            return createTFloatAdapter();
         }
         @Override
         public Adapter caseTDouble(TDouble object) {
            return createTDoubleAdapter();
         }
         @Override
         public Adapter caseTString(TString object) {
            return createTStringAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
         }
      };

	/**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
	@Override
	public Adapter createAdapter(Notifier target) {
      return modelSwitch.doSwitch((EObject)target);
   }


	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.StateMachine <em>State Machine</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.StateMachine
    * @generated
    */
	public Adapter createStateMachineAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.State <em>State</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.State
    * @generated
    */
	public Adapter createStateAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.SimpleState <em>Simple State</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.SimpleState
    * @generated
    */
	public Adapter createSimpleStateAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.StartState <em>Start State</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.StartState
    * @generated
    */
	public Adapter createStartStateAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.StopState <em>Stop State</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.StopState
    * @generated
    */
	public Adapter createStopStateAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.DataType <em>Data Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.DataType
    * @generated
    */
	public Adapter createDataTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.complexType <em>complex Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.complexType
    * @generated
    */
	public Adapter createcomplexTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TByte <em>TByte</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TByte
    * @generated
    */
	public Adapter createTByteAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TChar <em>TChar</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TChar
    * @generated
    */
	public Adapter createTCharAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TShort <em>TShort</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TShort
    * @generated
    */
	public Adapter createTShortAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TInt <em>TInt</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TInt
    * @generated
    */
	public Adapter createTIntAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TLong <em>TLong</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TLong
    * @generated
    */
	public Adapter createTLongAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TFloat <em>TFloat</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TFloat
    * @generated
    */
	public Adapter createTFloatAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TDouble <em>TDouble</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TDouble
    * @generated
    */
	public Adapter createTDoubleAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link edu.teco.automata.Automata.TString <em>TString</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see edu.teco.automata.Automata.TString
    * @generated
    */
	public Adapter createTStringAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
	public Adapter createEObjectAdapter() {
      return null;
   }

} //AutomataAdapterFactory
