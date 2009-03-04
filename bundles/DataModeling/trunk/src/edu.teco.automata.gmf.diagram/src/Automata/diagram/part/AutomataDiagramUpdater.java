package Automata.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class AutomataDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (Automata.diagram.part.AutomataVisualIDRegistry
				.getVisualID(view)) {
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			return getSimpleState_2001SemanticChildren(view);
		case Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID:
			return getStateMachine_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSimpleState_2001SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Automata.SimpleState modelElement = (Automata.SimpleState) view
				.getElement();
		List result = new LinkedList();
		{
			Automata.DataType childElement = modelElement.getType();
			int visualID = Automata.diagram.part.AutomataVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
			if (visualID == Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStateMachine_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Automata.StateMachine modelElement = (Automata.StateMachine) view
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getStates().iterator(); it.hasNext();) {
			Automata.State childElement = (Automata.State) it.next();
			int visualID = Automata.diagram.part.AutomataVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID) {
				result.add(new Automata.diagram.part.AutomataNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (Automata.diagram.part.AutomataVisualIDRegistry
				.getVisualID(view)) {
		case Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID:
			return getStateMachine_1000ContainedLinks(view);
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			return getSimpleState_2001ContainedLinks(view);
		case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
			return getStopState_2002ContainedLinks(view);
		case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
			return getStartState_2003ContainedLinks(view);
		case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
			return getTByte_3001ContainedLinks(view);
		case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
			return getTChar_3002ContainedLinks(view);
		case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
			return getTShort_3003ContainedLinks(view);
		case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
			return getTInt_3004ContainedLinks(view);
		case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
			return getTLong_3005ContainedLinks(view);
		case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
			return getTFloat_3006ContainedLinks(view);
		case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
			return getTDouble_3007ContainedLinks(view);
		case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
			return getTString_3008ContainedLinks(view);
		case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
			return getComplexType_3009ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (Automata.diagram.part.AutomataVisualIDRegistry
				.getVisualID(view)) {
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			return getSimpleState_2001IncomingLinks(view);
		case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
			return getStopState_2002IncomingLinks(view);
		case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
			return getStartState_2003IncomingLinks(view);
		case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
			return getTByte_3001IncomingLinks(view);
		case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
			return getTChar_3002IncomingLinks(view);
		case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
			return getTShort_3003IncomingLinks(view);
		case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
			return getTInt_3004IncomingLinks(view);
		case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
			return getTLong_3005IncomingLinks(view);
		case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
			return getTFloat_3006IncomingLinks(view);
		case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
			return getTDouble_3007IncomingLinks(view);
		case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
			return getTString_3008IncomingLinks(view);
		case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
			return getComplexType_3009IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (Automata.diagram.part.AutomataVisualIDRegistry
				.getVisualID(view)) {
		case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
			return getSimpleState_2001OutgoingLinks(view);
		case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
			return getStopState_2002OutgoingLinks(view);
		case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
			return getStartState_2003OutgoingLinks(view);
		case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
			return getTByte_3001OutgoingLinks(view);
		case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
			return getTChar_3002OutgoingLinks(view);
		case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
			return getTShort_3003OutgoingLinks(view);
		case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
			return getTInt_3004OutgoingLinks(view);
		case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
			return getTLong_3005OutgoingLinks(view);
		case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
			return getTFloat_3006OutgoingLinks(view);
		case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
			return getTDouble_3007OutgoingLinks(view);
		case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
			return getTString_3008OutgoingLinks(view);
		case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
			return getComplexType_3009OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStateMachine_1000ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSimpleState_2001ContainedLinks(View view) {
		Automata.SimpleState modelElement = (Automata.SimpleState) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_SimpleState_Out_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStopState_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStartState_2003ContainedLinks(View view) {
		Automata.StartState modelElement = (Automata.StartState) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_StartState_Out_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTByte_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTChar_3002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTShort_3003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTInt_3004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTLong_3005ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTFloat_3006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTDouble_3007ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTString_3008ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getComplexType_3009ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSimpleState_2001IncomingLinks(View view) {
		Automata.SimpleState modelElement = (Automata.SimpleState) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_StartState_Out_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_SimpleState_Out_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStopState_2002IncomingLinks(View view) {
		Automata.StopState modelElement = (Automata.StopState) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_StartState_Out_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_SimpleState_Out_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStartState_2003IncomingLinks(View view) {
		Automata.StartState modelElement = (Automata.StartState) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_StartState_Out_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_SimpleState_Out_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTByte_3001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTChar_3002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTShort_3003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTInt_3004IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTLong_3005IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTFloat_3006IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTDouble_3007IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTString_3008IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getComplexType_3009IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSimpleState_2001OutgoingLinks(View view) {
		Automata.SimpleState modelElement = (Automata.SimpleState) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_SimpleState_Out_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStopState_2002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStartState_2003OutgoingLinks(View view) {
		Automata.StartState modelElement = (Automata.StartState) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_StartState_Out_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTByte_3001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTChar_3002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTShort_3003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTInt_3004OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTLong_3005OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTFloat_3006OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTDouble_3007OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTString_3008OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getComplexType_3009OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_StartState_Out_4001(
			Automata.State target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == Automata.AutomataPackage.eINSTANCE
					.getStartState_Out()) {
				result
						.add(new Automata.diagram.part.AutomataLinkDescriptor(
								setting.getEObject(),
								target,
								Automata.diagram.providers.AutomataElementTypes.StartStateOut_4001,
								Automata.diagram.edit.parts.StartStateOutEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_SimpleState_Out_4002(
			Automata.State target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == Automata.AutomataPackage.eINSTANCE
					.getSimpleState_Out()) {
				result
						.add(new Automata.diagram.part.AutomataLinkDescriptor(
								setting.getEObject(),
								target,
								Automata.diagram.providers.AutomataElementTypes.SimpleStateOut_4002,
								Automata.diagram.edit.parts.SimpleStateOutEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_StartState_Out_4001(
			Automata.StartState source) {
		Collection result = new LinkedList();
		Automata.State destination = source.getOut();
		if (destination == null) {
			return result;
		}
		result
				.add(new Automata.diagram.part.AutomataLinkDescriptor(
						source,
						destination,
						Automata.diagram.providers.AutomataElementTypes.StartStateOut_4001,
						Automata.diagram.edit.parts.StartStateOutEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_SimpleState_Out_4002(
			Automata.SimpleState source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getOut().iterator(); destinations
				.hasNext();) {
			Automata.State destination = (Automata.State) destinations.next();
			result
					.add(new Automata.diagram.part.AutomataLinkDescriptor(
							source,
							destination,
							Automata.diagram.providers.AutomataElementTypes.SimpleStateOut_4002,
							Automata.diagram.edit.parts.SimpleStateOutEditPart.VISUAL_ID));
		}
		return result;
	}

}
