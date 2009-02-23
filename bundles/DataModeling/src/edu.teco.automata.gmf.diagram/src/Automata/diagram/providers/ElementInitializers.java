package Automata.diagram.providers;

/**
 * @generated
 */
public class ElementInitializers {
	/**
	 * @generated
	 */
	public static void init_StopState_2002(Automata.StopState instance) {
		try {
			Object value0 = Automata.diagram.expressions.AutomataOCLFactory
					.getExpression("\'Stop\'",
							Automata.AutomataPackage.eINSTANCE.getStopState())
					.evaluate(instance);
			instance.setName((String) value0);
		} catch (RuntimeException e) {
			Automata.diagram.part.AutomataDiagramEditorPlugin.getInstance()
					.logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public static void init_StartState_2003(Automata.StartState instance) {
		try {
			Object value0 = Automata.diagram.expressions.AutomataOCLFactory
					.getExpression("\'Start\'",
							Automata.AutomataPackage.eINSTANCE.getStartState())
					.evaluate(instance);
			instance.setName((String) value0);
		} catch (RuntimeException e) {
			Automata.diagram.part.AutomataDiagramEditorPlugin.getInstance()
					.logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

}
