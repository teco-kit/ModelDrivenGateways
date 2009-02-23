package Automata.diagram.edit.parts;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class TIntEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3007;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public TIntEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new Automata.diagram.edit.policies.TIntItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

			protected Command createAddCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command createMoveChildCommand(EditPart child,
					EditPart after) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		TDoubleFigure figure = new TDoubleFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public TDoubleFigure getPrimaryShape() {
		return (TDoubleFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof Automata.diagram.edit.parts.WrappingLabel8EditPart) {
			((Automata.diagram.edit.parts.WrappingLabel8EditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureTDoubleNameFigure());
			return true;
		}
		if (childEditPart instanceof Automata.diagram.edit.parts.TDoubleMinEditPart) {
			((Automata.diagram.edit.parts.TDoubleMinEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureTDoubleMinFigure());
			return true;
		}
		if (childEditPart instanceof Automata.diagram.edit.parts.TDoubleMaxEditPart) {
			((Automata.diagram.edit.parts.TDoubleMaxEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureTDoubleMaxFigure());
			return true;
		}
		if (childEditPart instanceof Automata.diagram.edit.parts.WrappingLabel5EditPart) {
			((Automata.diagram.edit.parts.WrappingLabel5EditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureTDoubleFractionDigitsFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {

		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {

		return super.getContentPaneFor(editPart);
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(40), getMapMode().DPtoLP(40));
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(Automata.diagram.part.AutomataVisualIDRegistry
				.getType(Automata.diagram.edit.parts.WrappingLabel8EditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class TDoubleFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureTDoubleMinFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureTDoubleMaxFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureTDoubleFractionDigitsFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureTDoubleNameFigure;

		/**
		 * @generated
		 */
		public TDoubleFigure() {

			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(false);

			this.setLayoutManager(layoutThis);

			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			WrappingLabel tDoubleName0 = new WrappingLabel();
			tDoubleName0.setText("TDouble");

			this.add(tDoubleName0);

			fFigureTDoubleMinFigure = new WrappingLabel();
			fFigureTDoubleMinFigure.setText("<...>");

			this.add(fFigureTDoubleMinFigure);

			fFigureTDoubleMaxFigure = new WrappingLabel();
			fFigureTDoubleMaxFigure.setText("<...>");

			this.add(fFigureTDoubleMaxFigure);

			fFigureTDoubleFractionDigitsFigure = new WrappingLabel();
			fFigureTDoubleFractionDigitsFigure.setText("<...>");

			this.add(fFigureTDoubleFractionDigitsFigure);

		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTDoubleMinFigure() {
			return fFigureTDoubleMinFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTDoubleMaxFigure() {
			return fFigureTDoubleMaxFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTDoubleFractionDigitsFigure() {
			return fFigureTDoubleFractionDigitsFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTDoubleNameFigure() {
			return fFigureTDoubleNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 255, 255, 152);

}
