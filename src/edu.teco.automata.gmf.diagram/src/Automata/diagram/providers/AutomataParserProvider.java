package Automata.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class AutomataParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser simpleStateName_5025Parser;

	/**
	 * @generated
	 */
	private IParser getSimpleStateName_5025Parser() {
		if (simpleStateName_5025Parser == null) {
			simpleStateName_5025Parser = createSimpleStateName_5025Parser();
		}
		return simpleStateName_5025Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSimpleStateName_5025Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getState_Name(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser simpleStateDepth_5026Parser;

	/**
	 * @generated
	 */
	private IParser getSimpleStateDepth_5026Parser() {
		if (simpleStateDepth_5026Parser == null) {
			simpleStateDepth_5026Parser = createSimpleStateDepth_5026Parser();
		}
		return simpleStateDepth_5026Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSimpleStateDepth_5026Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getSimpleState_Depth(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("depth: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser simpleStateLowerBound_5027Parser;

	/**
	 * @generated
	 */
	private IParser getSimpleStateLowerBound_5027Parser() {
		if (simpleStateLowerBound_5027Parser == null) {
			simpleStateLowerBound_5027Parser = createSimpleStateLowerBound_5027Parser();
		}
		return simpleStateLowerBound_5027Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSimpleStateLowerBound_5027Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getSimpleState_LowerBound(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("lBound: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser simpleStateUpperBound_5028Parser;

	/**
	 * @generated
	 */
	private IParser getSimpleStateUpperBound_5028Parser() {
		if (simpleStateUpperBound_5028Parser == null) {
			simpleStateUpperBound_5028Parser = createSimpleStateUpperBound_5028Parser();
		}
		return simpleStateUpperBound_5028Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSimpleStateUpperBound_5028Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getSimpleState_UpperBound(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("uBound: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser stopStateName_5029Parser;

	/**
	 * @generated
	 */
	private IParser getStopStateName_5029Parser() {
		if (stopStateName_5029Parser == null) {
			stopStateName_5029Parser = createStopStateName_5029Parser();
		}
		return stopStateName_5029Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStopStateName_5029Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getState_Name(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser startStateName_5030Parser;

	/**
	 * @generated
	 */
	private IParser getStartStateName_5030Parser() {
		if (startStateName_5030Parser == null) {
			startStateName_5030Parser = createStartStateName_5030Parser();
		}
		return startStateName_5030Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStartStateName_5030Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getState_Name(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tByteMin_5002Parser;

	/**
	 * @generated
	 */
	private IParser getTByteMin_5002Parser() {
		if (tByteMin_5002Parser == null) {
			tByteMin_5002Parser = createTByteMin_5002Parser();
		}
		return tByteMin_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTByteMin_5002Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTByte_Min(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("min: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tByteMax_5003Parser;

	/**
	 * @generated
	 */
	private IParser getTByteMax_5003Parser() {
		if (tByteMax_5003Parser == null) {
			tByteMax_5003Parser = createTByteMax_5003Parser();
		}
		return tByteMax_5003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTByteMax_5003Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTByte_Max(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("max: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tShortMin_5006Parser;

	/**
	 * @generated
	 */
	private IParser getTShortMin_5006Parser() {
		if (tShortMin_5006Parser == null) {
			tShortMin_5006Parser = createTShortMin_5006Parser();
		}
		return tShortMin_5006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTShortMin_5006Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTShort_Min(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("min: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tShortMax_5007Parser;

	/**
	 * @generated
	 */
	private IParser getTShortMax_5007Parser() {
		if (tShortMax_5007Parser == null) {
			tShortMax_5007Parser = createTShortMax_5007Parser();
		}
		return tShortMax_5007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTShortMax_5007Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTShort_Max(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("max: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tIntMin_5009Parser;

	/**
	 * @generated
	 */
	private IParser getTIntMin_5009Parser() {
		if (tIntMin_5009Parser == null) {
			tIntMin_5009Parser = createTIntMin_5009Parser();
		}
		return tIntMin_5009Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTIntMin_5009Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTInt_Min(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("min: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tIntMax_5010Parser;

	/**
	 * @generated
	 */
	private IParser getTIntMax_5010Parser() {
		if (tIntMax_5010Parser == null) {
			tIntMax_5010Parser = createTIntMax_5010Parser();
		}
		return tIntMax_5010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTIntMax_5010Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTInt_Max(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("max: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tLongMin_5012Parser;

	/**
	 * @generated
	 */
	private IParser getTLongMin_5012Parser() {
		if (tLongMin_5012Parser == null) {
			tLongMin_5012Parser = createTLongMin_5012Parser();
		}
		return tLongMin_5012Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTLongMin_5012Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTLong_Min(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("min: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tLongMax_5013Parser;

	/**
	 * @generated
	 */
	private IParser getTLongMax_5013Parser() {
		if (tLongMax_5013Parser == null) {
			tLongMax_5013Parser = createTLongMax_5013Parser();
		}
		return tLongMax_5013Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTLongMax_5013Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTLong_Max(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("max: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tFloatMin_5015Parser;

	/**
	 * @generated
	 */
	private IParser getTFloatMin_5015Parser() {
		if (tFloatMin_5015Parser == null) {
			tFloatMin_5015Parser = createTFloatMin_5015Parser();
		}
		return tFloatMin_5015Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTFloatMin_5015Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTFloat_Min(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("min: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tFloatMax_5016Parser;

	/**
	 * @generated
	 */
	private IParser getTFloatMax_5016Parser() {
		if (tFloatMax_5016Parser == null) {
			tFloatMax_5016Parser = createTFloatMax_5016Parser();
		}
		return tFloatMax_5016Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTFloatMax_5016Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTFloat_Max(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("max: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tFloatFractionDigits_5017Parser;

	/**
	 * @generated
	 */
	private IParser getTFloatFractionDigits_5017Parser() {
		if (tFloatFractionDigits_5017Parser == null) {
			tFloatFractionDigits_5017Parser = createTFloatFractionDigits_5017Parser();
		}
		return tFloatFractionDigits_5017Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTFloatFractionDigits_5017Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTFloat_FractionDigits(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("fDigits: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tDoubleMin_5019Parser;

	/**
	 * @generated
	 */
	private IParser getTDoubleMin_5019Parser() {
		if (tDoubleMin_5019Parser == null) {
			tDoubleMin_5019Parser = createTDoubleMin_5019Parser();
		}
		return tDoubleMin_5019Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTDoubleMin_5019Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTDouble_Min(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("min: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tDoubleMax_5020Parser;

	/**
	 * @generated
	 */
	private IParser getTDoubleMax_5020Parser() {
		if (tDoubleMax_5020Parser == null) {
			tDoubleMax_5020Parser = createTDoubleMax_5020Parser();
		}
		return tDoubleMax_5020Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTDoubleMax_5020Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTDouble_Max(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("max: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tDoubleFractionDigits_5021Parser;

	/**
	 * @generated
	 */
	private IParser getTDoubleFractionDigits_5021Parser() {
		if (tDoubleFractionDigits_5021Parser == null) {
			tDoubleFractionDigits_5021Parser = createTDoubleFractionDigits_5021Parser();
		}
		return tDoubleFractionDigits_5021Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTDoubleFractionDigits_5021Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTDouble_FractionDigits(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("fDigits: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser tStringLength_5023Parser;

	/**
	 * @generated
	 */
	private IParser getTStringLength_5023Parser() {
		if (tStringLength_5023Parser == null) {
			tStringLength_5023Parser = createTStringLength_5023Parser();
		}
		return tStringLength_5023Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTStringLength_5023Parser() {
		EAttribute[] features = new EAttribute[] { Automata.AutomataPackage.eINSTANCE
				.getTString_Length(), };
		Automata.diagram.parsers.MessageFormatParser parser = new Automata.diagram.parsers.MessageFormatParser(
				features);
		parser.setViewPattern("len: {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case Automata.diagram.edit.parts.SimpleStateNameEditPart.VISUAL_ID:
			return getSimpleStateName_5025Parser();
		case Automata.diagram.edit.parts.SimpleStateDepthEditPart.VISUAL_ID:
			return getSimpleStateDepth_5026Parser();
		case Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart.VISUAL_ID:
			return getSimpleStateLowerBound_5027Parser();
		case Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart.VISUAL_ID:
			return getSimpleStateUpperBound_5028Parser();
		case Automata.diagram.edit.parts.StopStateNameEditPart.VISUAL_ID:
			return getStopStateName_5029Parser();
		case Automata.diagram.edit.parts.StartStateNameEditPart.VISUAL_ID:
			return getStartStateName_5030Parser();
		case Automata.diagram.edit.parts.TByteMinEditPart.VISUAL_ID:
			return getTByteMin_5002Parser();
		case Automata.diagram.edit.parts.TByteMaxEditPart.VISUAL_ID:
			return getTByteMax_5003Parser();
		case Automata.diagram.edit.parts.TShortMinEditPart.VISUAL_ID:
			return getTShortMin_5006Parser();
		case Automata.diagram.edit.parts.TShortMaxEditPart.VISUAL_ID:
			return getTShortMax_5007Parser();
		case Automata.diagram.edit.parts.TIntMinEditPart.VISUAL_ID:
			return getTIntMin_5009Parser();
		case Automata.diagram.edit.parts.TIntMaxEditPart.VISUAL_ID:
			return getTIntMax_5010Parser();
		case Automata.diagram.edit.parts.TLongMinEditPart.VISUAL_ID:
			return getTLongMin_5012Parser();
		case Automata.diagram.edit.parts.TLongMaxEditPart.VISUAL_ID:
			return getTLongMax_5013Parser();
		case Automata.diagram.edit.parts.TFloatMinEditPart.VISUAL_ID:
			return getTFloatMin_5015Parser();
		case Automata.diagram.edit.parts.TFloatMaxEditPart.VISUAL_ID:
			return getTFloatMax_5016Parser();
		case Automata.diagram.edit.parts.TFloatFractionDigitsEditPart.VISUAL_ID:
			return getTFloatFractionDigits_5017Parser();
		case Automata.diagram.edit.parts.TDoubleMinEditPart.VISUAL_ID:
			return getTDoubleMin_5019Parser();
		case Automata.diagram.edit.parts.TDoubleMaxEditPart.VISUAL_ID:
			return getTDoubleMax_5020Parser();
		case Automata.diagram.edit.parts.WrappingLabel5EditPart.VISUAL_ID:
			return getTDoubleFractionDigits_5021Parser();
		case Automata.diagram.edit.parts.TStringLengthEditPart.VISUAL_ID:
			return getTStringLength_5023Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (Automata.diagram.providers.AutomataElementTypes
					.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
