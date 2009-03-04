package Automata.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class AutomataEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (Automata.diagram.part.AutomataVisualIDRegistry
					.getVisualID(view)) {

			case Automata.diagram.edit.parts.StateMachineEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.StateMachineEditPart(
						view);

			case Automata.diagram.edit.parts.SimpleStateEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.SimpleStateEditPart(view);

			case Automata.diagram.edit.parts.SimpleStateNameEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.SimpleStateNameEditPart(
						view);

			case Automata.diagram.edit.parts.SimpleStateDepthEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.SimpleStateDepthEditPart(
						view);

			case Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart(
						view);

			case Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart(
						view);

			case Automata.diagram.edit.parts.StopStateEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.StopStateEditPart(view);

			case Automata.diagram.edit.parts.StopStateNameEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.StopStateNameEditPart(
						view);

			case Automata.diagram.edit.parts.StartStateEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.StartStateEditPart(view);

			case Automata.diagram.edit.parts.StartStateNameEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.StartStateNameEditPart(
						view);

			case Automata.diagram.edit.parts.TByte2EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TByte2EditPart(view);

			case Automata.diagram.edit.parts.WrappingLabelEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabelEditPart(
						view);

			case Automata.diagram.edit.parts.TByteMinEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TByteMinEditPart(view);

			case Automata.diagram.edit.parts.TByteMaxEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TByteMaxEditPart(view);

			case Automata.diagram.edit.parts.TChar2EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TChar2EditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel2EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel2EditPart(
						view);

			case Automata.diagram.edit.parts.TShort2EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TShort2EditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel3EditPart(
						view);

			case Automata.diagram.edit.parts.TShortMinEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TShortMinEditPart(view);

			case Automata.diagram.edit.parts.TShortMaxEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TShortMaxEditPart(view);

			case Automata.diagram.edit.parts.TByteEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TByteEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel4EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel4EditPart(
						view);

			case Automata.diagram.edit.parts.TIntMinEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TIntMinEditPart(view);

			case Automata.diagram.edit.parts.TIntMaxEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TIntMaxEditPart(view);

			case Automata.diagram.edit.parts.TCharEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TCharEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel6EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel6EditPart(
						view);

			case Automata.diagram.edit.parts.TLongMinEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TLongMinEditPart(view);

			case Automata.diagram.edit.parts.TLongMaxEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TLongMaxEditPart(view);

			case Automata.diagram.edit.parts.TShortEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TShortEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel7EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel7EditPart(
						view);

			case Automata.diagram.edit.parts.TFloatMinEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TFloatMinEditPart(view);

			case Automata.diagram.edit.parts.TFloatMaxEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TFloatMaxEditPart(view);

			case Automata.diagram.edit.parts.TFloatFractionDigitsEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TFloatFractionDigitsEditPart(
						view);

			case Automata.diagram.edit.parts.TIntEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TIntEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel8EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel8EditPart(
						view);

			case Automata.diagram.edit.parts.TDoubleMinEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TDoubleMinEditPart(view);

			case Automata.diagram.edit.parts.TDoubleMaxEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TDoubleMaxEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel5EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel5EditPart(
						view);

			case Automata.diagram.edit.parts.TLongEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TLongEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel9EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel9EditPart(
						view);

			case Automata.diagram.edit.parts.TStringLengthEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TStringLengthEditPart(
						view);

			case Automata.diagram.edit.parts.TFloatEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.TFloatEditPart(view);

			case Automata.diagram.edit.parts.WrappingLabel10EditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.WrappingLabel10EditPart(
						view);

			case Automata.diagram.edit.parts.StartStateOutEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.StartStateOutEditPart(
						view);

			case Automata.diagram.edit.parts.SimpleStateOutEditPart.VISUAL_ID:
				return new Automata.diagram.edit.parts.SimpleStateOutEditPart(
						view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (getWrapLabel().isTextWrapOn()
					&& getWrapLabel().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			} else {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont())
					.getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
					SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
