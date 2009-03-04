package Automata.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class StopStateItemSemanticEditPolicy extends
		Automata.diagram.edit.policies.AutomataBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (Automata.diagram.providers.AutomataElementTypes.StartStateOut_4001 == req
				.getElementType()) {
			return null;
		}
		if (Automata.diagram.providers.AutomataElementTypes.SimpleStateOut_4002 == req
				.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (Automata.diagram.providers.AutomataElementTypes.StartStateOut_4001 == req
				.getElementType()) {
			return getGEFWrapper(new Automata.diagram.edit.commands.StartStateOutCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (Automata.diagram.providers.AutomataElementTypes.SimpleStateOut_4002 == req
				.getElementType()) {
			return getGEFWrapper(new Automata.diagram.edit.commands.SimpleStateOutCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case Automata.diagram.edit.parts.StartStateOutEditPart.VISUAL_ID:
			return getGEFWrapper(new Automata.diagram.edit.commands.StartStateOutReorientCommand(
					req));
		case Automata.diagram.edit.parts.SimpleStateOutEditPart.VISUAL_ID:
			return getGEFWrapper(new Automata.diagram.edit.commands.SimpleStateOutReorientCommand(
					req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
