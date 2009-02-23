package Automata.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class AutomataVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "edu.teco.automata.gmf.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
					.equals(view.getType())) {
				return Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return Automata.diagram.part.AutomataVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				Automata.diagram.part.AutomataDiagramEditorPlugin.getInstance()
						.logError(
								"Unable to parse view type as a visualID number: "
										+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (Automata.AutomataPackage.eINSTANCE.getStateMachine().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Automata.StateMachine) domainElement)) {
			return Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = Automata.diagram.part.AutomataVisualIDRegistry
				.getModelID(containerView);
		if (!Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
				.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			if (Automata.AutomataPackage.eINSTANCE.getTByte().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTChar().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTShort().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTInt().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTLong().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTFloat().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTDouble().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getTString().isSuperTypeOf(
					domainElement.eClass())) {
				return Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getcomplexType()
					.isSuperTypeOf(domainElement.eClass())) {
				return Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID;
			}
			break;
		case Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID:
			if (Automata.AutomataPackage.eINSTANCE.getSimpleState()
					.isSuperTypeOf(domainElement.eClass())) {
				return Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getStopState()
					.isSuperTypeOf(domainElement.eClass())) {
				return Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID;
			}
			if (Automata.AutomataPackage.eINSTANCE.getStartState()
					.isSuperTypeOf(domainElement.eClass())) {
				return Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = Automata.diagram.part.AutomataVisualIDRegistry
				.getModelID(containerView);
		if (!Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
				.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.SimpleStateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.SimpleStateDepthEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.StopStateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.StartStateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TByteMinEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TByteMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TShortMinEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TShortMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TIntMinEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TIntMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TLongMinEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TLongMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel7EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TFloatMinEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TFloatMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TFloatFractionDigitsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel8EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TDoubleMinEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TDoubleMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.WrappingLabel5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel9EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.TStringLengthEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.WrappingLabel10EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID:
			if (Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Automata.StateMachine element) {
		return true;
	}

}
