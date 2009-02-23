package Automata.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class SimpleStateItemSemanticEditPolicy extends
		Automata.diagram.edit.policies.AutomataBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (Automata.diagram.providers.AutomataElementTypes.TByte_3001 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TByte2CreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TChar_3002 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TChar2CreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TShort_3003 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TShort2CreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TInt_3004 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TByteCreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TLong_3005 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TCharCreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TFloat_3006 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TShortCreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TDouble_3007 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TIntCreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.TString_3008 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TLongCreateCommand(
					req));
		}
		if (Automata.diagram.providers.AutomataElementTypes.ComplexType_3009 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(Automata.AutomataPackage.eINSTANCE
						.getSimpleState_Type());
			}
			return getGEFWrapper(new Automata.diagram.edit.commands.TFloatCreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyChildNodesCommand(cc);
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
	protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
		View view = (View) getHost().getModel();
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return;
		}
		for (Iterator it = view.getChildren().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			switch (Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(node)) {
			case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			}
		}
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
			return getGEFWrapper(new Automata.diagram.edit.commands.SimpleStateOutCreateCommand(
					req, req.getSource(), req.getTarget()));
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
