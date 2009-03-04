package Automata.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class AutomataViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
				.equals(diagramKind)
				&& Automata.diagram.part.AutomataVisualIDRegistry
						.getDiagramVisualID(semanticElement) != -1) {
			return Automata.diagram.view.factories.StateMachineViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = Automata.diagram.part.AutomataVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		} else {
			visualID = Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!Automata.diagram.providers.AutomataElementTypes
						.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != Automata.diagram.part.AutomataVisualIDRegistry
								.getNodeVisualID(containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!Automata.diagram.edit.parts.StateMachineEditPart.MODEL_ID
						.equals(Automata.diagram.part.AutomataVisualIDRegistry
								.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != Automata.diagram.part.AutomataVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case Automata.diagram.edit.parts.SimpleStateNameEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.SimpleStateDepthEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.StopStateNameEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.StartStateNameEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabelEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TByteMinEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TByteMaxEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel2EditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TShortMinEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TShortMaxEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel4EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TIntMinEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TIntMaxEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel6EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TLongMinEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TLongMaxEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel7EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TFloatMinEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TFloatMaxEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TFloatFractionDigitsEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel8EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TDoubleMinEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TDoubleMaxEditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.WrappingLabel5EditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel9EditPart.VISUAL_ID:
				case Automata.diagram.edit.parts.TStringLengthEditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case Automata.diagram.edit.parts.WrappingLabel10EditPart.VISUAL_ID:
					if (Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID != Automata.diagram.part.AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !Automata.diagram.part.AutomataVisualIDRegistry
						.canCreateNode(containerView, visualID)) {
			return null;
		}
		switch (visualID) {
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.SimpleStateViewFactory.class;
		case Automata.diagram.edit.parts.SimpleStateNameEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.SimpleStateNameViewFactory.class;
		case Automata.diagram.edit.parts.SimpleStateDepthEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.SimpleStateDepthViewFactory.class;
		case Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.SimpleStateLowerBoundViewFactory.class;
		case Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.SimpleStateUpperBoundViewFactory.class;
		case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.StopStateViewFactory.class;
		case Automata.diagram.edit.parts.StopStateNameEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.StopStateNameViewFactory.class;
		case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.StartStateViewFactory.class;
		case Automata.diagram.edit.parts.StartStateNameEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.StartStateNameViewFactory.class;
		case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TByte2ViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabelEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabelViewFactory.class;
		case Automata.diagram.edit.parts.TByteMinEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TByteMinViewFactory.class;
		case Automata.diagram.edit.parts.TByteMaxEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TByteMaxViewFactory.class;
		case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TChar2ViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel2EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel2ViewFactory.class;
		case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TShort2ViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel3ViewFactory.class;
		case Automata.diagram.edit.parts.TShortMinEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TShortMinViewFactory.class;
		case Automata.diagram.edit.parts.TShortMaxEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TShortMaxViewFactory.class;
		case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TByteViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel4EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel4ViewFactory.class;
		case Automata.diagram.edit.parts.TIntMinEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TIntMinViewFactory.class;
		case Automata.diagram.edit.parts.TIntMaxEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TIntMaxViewFactory.class;
		case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TCharViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel6EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel6ViewFactory.class;
		case Automata.diagram.edit.parts.TLongMinEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TLongMinViewFactory.class;
		case Automata.diagram.edit.parts.TLongMaxEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TLongMaxViewFactory.class;
		case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TShortViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel7EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel7ViewFactory.class;
		case Automata.diagram.edit.parts.TFloatMinEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TFloatMinViewFactory.class;
		case Automata.diagram.edit.parts.TFloatMaxEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TFloatMaxViewFactory.class;
		case Automata.diagram.edit.parts.TFloatFractionDigitsEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TFloatFractionDigitsViewFactory.class;
		case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TIntViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel8EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel8ViewFactory.class;
		case Automata.diagram.edit.parts.TDoubleMinEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TDoubleMinViewFactory.class;
		case Automata.diagram.edit.parts.TDoubleMaxEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TDoubleMaxViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel5EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel5ViewFactory.class;
		case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TLongViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel9EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel9ViewFactory.class;
		case Automata.diagram.edit.parts.TStringLengthEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TStringLengthViewFactory.class;
		case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.TFloatViewFactory.class;
		case Automata.diagram.edit.parts.WrappingLabel10EditPart.VISUAL_ID:
			return Automata.diagram.view.factories.WrappingLabel10ViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!Automata.diagram.providers.AutomataElementTypes
				.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = Automata.diagram.part.AutomataVisualIDRegistry
				.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != Automata.diagram.part.AutomataVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		case Automata.diagram.edit.parts.StartStateOutEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.StartStateOutViewFactory.class;
		case Automata.diagram.edit.parts.SimpleStateOutEditPart.VISUAL_ID:
			return Automata.diagram.view.factories.SimpleStateOutViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}
}
