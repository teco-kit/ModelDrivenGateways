package Automata.diagram.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class StartStateCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public StartStateCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return Automata.AutomataPackage.eINSTANCE.getStateMachine();
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		Automata.StartState newElement = Automata.AutomataFactory.eINSTANCE
				.createStartState();

		Automata.StateMachine owner = (Automata.StateMachine) getElementToEdit();
		owner.getStates().add(newElement);

		Automata.diagram.providers.AutomataElementTypes
				.init_StartState_2003(newElement);
		return newElement;
	}

}
