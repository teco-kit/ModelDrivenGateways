package Automata.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class AutomataPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createAutomata1Group());
	}

	/**
	 * Creates "Automata" palette tool group
	 * @generated
	 */
	private PaletteContainer createAutomata1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Automata.diagram.part.Messages.Automata1Group_title);
		paletteContainer.add(createSimpleState1CreationTool());
		paletteContainer.add(createTransition2CreationTool());
		paletteContainer.add(createStartState3CreationTool());
		paletteContainer.add(createStopState4CreationTool());
		paletteContainer.add(createComplexType5CreationTool());
		paletteContainer.add(createTByte6CreationTool());
		paletteContainer.add(createTChar7CreationTool());
		paletteContainer.add(createTShort8CreationTool());
		paletteContainer.add(createTInt9CreationTool());
		paletteContainer.add(createTLong10CreationTool());
		paletteContainer.add(createTFloat11CreationTool());
		paletteContainer.add(createTDouble12CreationTool());
		paletteContainer.add(createTString13CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSimpleState1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(Automata.diagram.providers.AutomataElementTypes.SimpleState_2001);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.SimpleState1CreationTool_title,
				Automata.diagram.part.Messages.SimpleState1CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.SimpleState_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransition2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types
				.add(Automata.diagram.providers.AutomataElementTypes.StartStateOut_4001);
		types
				.add(Automata.diagram.providers.AutomataElementTypes.SimpleStateOut_4002);
		LinkToolEntry entry = new LinkToolEntry(
				Automata.diagram.part.Messages.Transition2CreationTool_title,
				Automata.diagram.part.Messages.Transition2CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.StartStateOut_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createStartState3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(Automata.diagram.providers.AutomataElementTypes.StartState_2003);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.StartState3CreationTool_title,
				Automata.diagram.part.Messages.StartState3CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.StartState_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createStopState4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(Automata.diagram.providers.AutomataElementTypes.StopState_2002);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.StopState4CreationTool_title,
				Automata.diagram.part.Messages.StopState4CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.StopState_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComplexType5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(Automata.diagram.providers.AutomataElementTypes.ComplexType_3009);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.ComplexType5CreationTool_title,
				Automata.diagram.part.Messages.ComplexType5CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.ComplexType_3009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTByte6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TByte_3001);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TByte6CreationTool_title,
				Automata.diagram.part.Messages.TByte6CreationTool_desc, types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TByte_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTChar7CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TChar_3002);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TChar7CreationTool_title,
				Automata.diagram.part.Messages.TChar7CreationTool_desc, types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TChar_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTShort8CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TShort_3003);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TShort8CreationTool_title,
				Automata.diagram.part.Messages.TShort8CreationTool_desc, types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TShort_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTInt9CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TInt_3004);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TInt9CreationTool_title,
				Automata.diagram.part.Messages.TInt9CreationTool_desc, types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TInt_3004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTLong10CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TLong_3005);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TLong10CreationTool_title,
				Automata.diagram.part.Messages.TLong10CreationTool_desc, types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TLong_3005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTFloat11CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TFloat_3006);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TFloat11CreationTool_title,
				Automata.diagram.part.Messages.TFloat11CreationTool_desc, types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TFloat_3006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTDouble12CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TDouble_3007);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TDouble12CreationTool_title,
				Automata.diagram.part.Messages.TDouble12CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TDouble_3007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTString13CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(Automata.diagram.providers.AutomataElementTypes.TString_3008);
		NodeToolEntry entry = new NodeToolEntry(
				Automata.diagram.part.Messages.TString13CreationTool_title,
				Automata.diagram.part.Messages.TString13CreationTool_desc,
				types);
		entry
				.setSmallIcon(Automata.diagram.providers.AutomataElementTypes
						.getImageDescriptor(Automata.diagram.providers.AutomataElementTypes.TString_3008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
