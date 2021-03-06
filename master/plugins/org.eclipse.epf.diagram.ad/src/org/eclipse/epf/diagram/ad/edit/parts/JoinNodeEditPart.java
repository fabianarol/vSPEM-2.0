//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.ad.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.custom.policies.DynamicResizeDirectionsEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.JoinNodeItemSemanticEditPolicy;
import org.eclipse.epf.diagram.core.editparts.InternalNodeEditPart;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class JoinNodeEditPart extends ShapeNodeEditPart implements InternalNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1006;

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
	public JoinNodeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new JoinNodeItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
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
		ForkNodeFigure figure = new ForkNodeFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ForkNodeFigure getPrimaryShape() {
		return (ForkNodeFigure) primaryShape;
	}

	/**
	 * @modified
	 */
	protected NodeFigure createNodePlate() {
		return new DefaultSizeNodeFigure(getMapMode().DPtoLP(100), getMapMode()
				.DPtoLP(5));
	}

	/**
	 * @generated
	 */
	public EditPolicy getPrimaryDragEditPolicy() {
//		EditPolicy result = super.getPrimaryDragEditPolicy();
//		if (result instanceof ResizableEditPolicy) {
//			ResizableEditPolicy ep = (ResizableEditPolicy) result;
//			ep.setResizeDirections(PositionConstants.WEST
//					| PositionConstants.EAST);
//		}
//		return result;
		
		return new DynamicResizeDirectionsEditPolicy();
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
	public class ForkNodeFigure extends RectangleFigure {

		/**
		 * @modified
		 */
		public ForkNodeFigure() {

			FillStyle style = (FillStyle) getPrimaryView().getStyle(
					NotationPackage.Literals.FILL_STYLE);
			if (style != null
					&& style.getFillColor() != DiagramConstants.FILL_COLOR_EDEFAULT) {
				setBackgroundColor(DiagramColorRegistry.getInstance().getColor(
						new Integer(style.getFillColor())));
			} else {
				this.setBackgroundColor(FORKNODEFIGURE_BACK);
			}
			this.setPreferredSize(getMapMode().DPtoLP(100), getMapMode()
					.DPtoLP(8));
			this.setSize(getMapMode().DPtoLP(100), getMapMode().DPtoLP(8));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
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

	}

	/**
	 * @generated
	 */
	public static final Color FORKNODEFIGURE_BACK = new Color(null, 45, 85, 130);

	@Override
	protected void handleNotificationEvent(Notification notification) {
		// TODO Auto-generated method stub
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getSize_Width().equals(feature)
				|| NotationPackage.eINSTANCE.getSize_Height().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_X().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_Y().equals(feature)) {
			refreshBounds();
		} else if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(
				feature)) {
			Integer c = (Integer) notification.getNewValue();
			setBackgroundColor(DiagramColorRegistry.getInstance().getColor(c));
			getPrimaryShape().setBackgroundColor(
					DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getLineStyle_LineColor().equals(
				feature)) {
			Integer c = (Integer) notification.getNewValue();
			setForegroundColor(DiagramColorRegistry.getInstance().getColor(c));
			getPrimaryShape().setForegroundColor(
					DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle().isInstance(
				notification.getNotifier()))
			refreshFont();
		else if (notification.getFeature() == NotationPackage.eINSTANCE
				.getView_Element()
				&& ((EObject) notification.getNotifier()) == getNotationView())
			handleMajorSemanticChange();
		else
			super.handleNotificationEvent(notification);
	}

	@Override
	public void primAddSourceConnection(ConnectionEditPart connection,
			int index) {
		super.primAddSourceConnection(connection, index);
	}
	
	@Override
	public void primAddTargetConnection(ConnectionEditPart connection,
			int index) {
		super.primAddTargetConnection(connection, index);
	}

}
