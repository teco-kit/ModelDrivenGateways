package Automata.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class SimpleStateCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = Automata.diagram.part.AutomataDiagramUpdater
				.getSimpleState_2001SemanticChildren(viewObject).iterator(); it
				.hasNext();) {
			result.add(((Automata.diagram.part.AutomataNodeDescriptor) it
					.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = Automata.diagram.part.AutomataVisualIDRegistry
				.getVisualID(view);
		switch (visualID) {
		case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
		case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
			if (!semanticChildren.contains(view.getElement())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(Automata.AutomataPackage.eINSTANCE
					.getSimpleState_Type());
		}
		return myFeaturesToSynchronize;
	}

}
