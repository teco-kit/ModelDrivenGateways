package Automata.diagram.edit.parts;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
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
public class SimpleStateEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

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
	public SimpleStateEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new Automata.diagram.edit.policies.SimpleStateItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(
				EditPolicyRoles.CANONICAL_ROLE,
				new Automata.diagram.edit.policies.SimpleStateCanonicalEditPolicy());
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
		SimpleStateFigure figure = new SimpleStateFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public SimpleStateFigure getPrimaryShape() {
		return (SimpleStateFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof Automata.diagram.edit.parts.SimpleStateNameEditPart) {
			((Automata.diagram.edit.parts.SimpleStateNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureSimpleStateNameFigure());
			return true;
		}
		if (childEditPart instanceof Automata.diagram.edit.parts.SimpleStateDepthEditPart) {
			((Automata.diagram.edit.parts.SimpleStateDepthEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureSimpleStateDepthFigure());
			return true;
		}
		if (childEditPart instanceof Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart) {
			((Automata.diagram.edit.parts.SimpleStateLowerBoundEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureSimpleStateLowerBoundFigure());
			return true;
		}
		if (childEditPart instanceof Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart) {
			((Automata.diagram.edit.parts.SimpleStateUpperBoundEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureSimpleStateUpperBoundFigure());
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
				.getType(Automata.diagram.edit.parts.SimpleStateNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class SimpleStateFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureSimpleStateNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureSimpleStateLowerBoundFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureSimpleStateUpperBoundFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureSimpleStateDepthFigure;

		/**
		 * @generated
		 */
		public SimpleStateFigure() {

			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(false);

			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(8),
					getMapMode().DPtoLP(8)));
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureSimpleStateNameFigure = new WrappingLabel();
			fFigureSimpleStateNameFigure.setText("<...>");

			this.add(fFigureSimpleStateNameFigure);

			fFigureSimpleStateLowerBoundFigure = new WrappingLabel();
			fFigureSimpleStateLowerBoundFigure.setText("<...>");

			this.add(fFigureSimpleStateLowerBoundFigure);

			fFigureSimpleStateUpperBoundFigure = new WrappingLabel();
			fFigureSimpleStateUpperBoundFigure.setText("<...>");

			this.add(fFigureSimpleStateUpperBoundFigure);

			fFigureSimpleStateDepthFigure = new WrappingLabel();
			fFigureSimpleStateDepthFigure.setText("<...>");

			this.add(fFigureSimpleStateDepthFigure);

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
		public WrappingLabel getFigureSimpleStateNameFigure() {
			return fFigureSimpleStateNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureSimpleStateLowerBoundFigure() {
			return fFigureSimpleStateLowerBoundFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureSimpleStateUpperBoundFigure() {
			return fFigureSimpleStateUpperBoundFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureSimpleStateDepthFigure() {
			return fFigureSimpleStateDepthFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 155, 199, 204);

}
