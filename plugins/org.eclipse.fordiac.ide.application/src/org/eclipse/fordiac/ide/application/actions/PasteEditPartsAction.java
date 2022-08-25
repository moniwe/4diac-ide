/*******************************************************************************
 * Copyright (c) 2008 - 2017 Profactor GmbH, TU Wien ACIN, AIT, fortiss GmbH
 * 		 2018 - 2020 Johannes Kepler University
 * 		 2021 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Gerhard Ebenhofer, Alois Zoitl, Filip Andren
 *     - initial API and implementation and/or initial documentation
 *   Alois Zoitl - fixed copy/paste handling
 *   Bianca Wiesmayr - fixed copy/paste position
 *   Bianca Wiesmayr, Daniel Lindhuber, Lukas Wais - fixed ctrl+c, ctrl+v, ctrl+v
 *   Fabio Gandolfi - growing frame for copy in group & subapp
 *******************************************************************************/
package org.eclipse.fordiac.ide.application.actions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.fordiac.ide.application.Messages;
import org.eclipse.fordiac.ide.application.actions.CopyPasteMessage.CopyStatus;
import org.eclipse.fordiac.ide.application.commands.AddElementsToSubAppCommand;
import org.eclipse.fordiac.ide.application.commands.CopyElementsToGroupCommand;
import org.eclipse.fordiac.ide.application.commands.CutAndPasteFromSubAppCommand;
import org.eclipse.fordiac.ide.application.commands.PasteCommand;
import org.eclipse.fordiac.ide.application.editors.FBNetworkEditor;
import org.eclipse.fordiac.ide.application.editparts.AbstractContainerContentEditPart;
import org.eclipse.fordiac.ide.application.editparts.FBNetworkEditPart;
import org.eclipse.fordiac.ide.application.editparts.FBNetworkRootEditPart;
import org.eclipse.fordiac.ide.application.editparts.GroupContentEditPart;
import org.eclipse.fordiac.ide.application.editparts.SubAppForFBNetworkEditPart;
import org.eclipse.fordiac.ide.application.editparts.UISubAppNetworkEditPart;
import org.eclipse.fordiac.ide.application.editparts.UnfoldedSubappContentEditPart;
import org.eclipse.fordiac.ide.application.policies.ContainerContentLayoutPolicy;
import org.eclipse.fordiac.ide.model.helpers.FBNetworkHelper;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetwork;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement;
import org.eclipse.fordiac.ide.model.libraryElement.Group;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;
import org.eclipse.fordiac.ide.ui.FordiacClipboard;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/** The Class PasteEditPartsAction. */
public class PasteEditPartsAction extends SelectionAction {

	private Point pasteRefPosition;

	/**
	 * Instantiates a new paste edit parts action.
	 *
	 * @param editor the editor
	 */
	public PasteEditPartsAction(final IWorkbenchPart editor) {
		super(editor);
	}

	@Override
	protected boolean calculateEnabled() {
		final FBNetwork fbNetwork = getFBNetwork();
		return (null != fbNetwork) && !getClipboardContents().isEmpty();
	}

	protected Command createPasteCommand() {
		final FBNetwork fbNetwork = getFBNetwork();

		if (null != fbNetwork) {
			final AbstractContainerContentEditPart editPart = findAbstractContainerContentEditPartUnderMouse(fbNetwork);
			if (editPart != null) {
				final Rectangle newContentBoundsWithValueBounds = getNewContentBoundsWithValueBounds();
				final Rectangle editPartContentBounds = ContainerContentLayoutPolicy.getContainerAreaBounds(editPart);
				if ((editPart instanceof GroupContentEditPart)
						&& getClipboardContents().stream().noneMatch(Group.class::isInstance)) {
					return createPasteCommandForGroup((GroupContentEditPart) editPart, newContentBoundsWithValueBounds,
							editPartContentBounds);
				}

				if (editPart instanceof UnfoldedSubappContentEditPart) {
					return createPasteCommandForSubApp((UnfoldedSubappContentEditPart) editPart,
							newContentBoundsWithValueBounds, editPartContentBounds);
				}
			}
			return new PasteCommand(getClipboardContents(), fbNetwork, pasteRefPosition);
		}
		return new CompoundCommand();
	}

	private Command createPasteCommandForSubApp(final UnfoldedSubappContentEditPart subApp,
			final Rectangle contentBoundsWithValueBounds, final Rectangle subAppContentBounds) {

		final Point pastePointInSubApp = new Point(pasteRefPosition.x - subAppContentBounds.x,
				pasteRefPosition.y - subAppContentBounds.y);

		final PasteCommand pasteCommand = new PasteCommand(getClipboardContents(),
				subApp.getModel().getSubapp().getSubAppNetwork(), pastePointInSubApp);

		if (!subAppContentBounds.contains(contentBoundsWithValueBounds)) {
			// we need to increase the size of the SubApp
			return copyElementsToSubAppAndResizeCommand(subApp.getModel().getSubapp(), pasteCommand,
					subAppContentBounds, contentBoundsWithValueBounds);
		}
		return pasteCommand;
	}

	private Command createPasteCommandForGroup(final GroupContentEditPart group,
			final Rectangle contentBoundsWithValueBounds, final Rectangle groupContentBounds) {
		final PasteCommand pasteCommand = new PasteCommand(getClipboardContents(),
				group.getModel().getGroup().getFbNetwork(), pasteRefPosition);

		if (!groupContentBounds.contains(contentBoundsWithValueBounds)) {
			// we need to increase the size of the group
			return createCopyElementsToGroupAndResizeCommand(group.getModel().getGroup(),
					new CopyElementsToGroupCommand(group.getModel().getGroup(), pasteCommand, getOffsetPosition(group)),
					groupContentBounds, contentBoundsWithValueBounds);
		}
		return new CopyElementsToGroupCommand(group.getModel().getGroup(), pasteCommand, getOffsetPosition(group));
	}

	private static Command createCopyElementsToGroupAndResizeCommand(final Group dropGroup,
			final CopyElementsToGroupCommand copyElementsToGroup, final Rectangle groupContentBounds,
			final Rectangle newContentBounds) {
		final CompoundCommand cmd = new CompoundCommand();
		newContentBounds.union(groupContentBounds);
		cmd.add(ContainerContentLayoutPolicy.createChangeBoundsCommand(dropGroup, groupContentBounds,
				newContentBounds));
		final org.eclipse.draw2d.geometry.Point offset = copyElementsToGroup.getOffset();
		offset.translate(newContentBounds.x - groupContentBounds.x, newContentBounds.y - groupContentBounds.y);
		copyElementsToGroup.setOffset(offset);
		cmd.add(copyElementsToGroup);
		return cmd;
	}

	private static Command copyElementsToSubAppAndResizeCommand(final SubApp dropSubApp,
			final PasteCommand pasteCommand, final Rectangle subAppContentBounds, final Rectangle newContentBounds) {
		final CompoundCommand cmd = new CompoundCommand();
		newContentBounds.union(subAppContentBounds);
		cmd.add(ContainerContentLayoutPolicy.createChangeBoundsCommand(dropSubApp, subAppContentBounds,
				newContentBounds));
		cmd.add(pasteCommand);
		return cmd;
	}

	private AbstractContainerContentEditPart findAbstractContainerContentEditPartUnderMouse(final FBNetwork fbNetwork) {
		final GraphicalViewer graphicalViewer = getWorkbenchPart().getAdapter(GraphicalViewer.class);
		final Object object = graphicalViewer.getEditPartRegistry().get(fbNetwork);
		if (object instanceof GraphicalEditPart) {
			final IFigure figure = ((GraphicalEditPart) object).getFigure().findFigureAt(pasteRefPosition.x,
					pasteRefPosition.y);
			if (figure != null) {
				final Object targetObject = graphicalViewer.getVisualPartMap().get(figure);
				if (targetObject instanceof AbstractContainerContentEditPart) {
					return ((AbstractContainerContentEditPart) targetObject);
				}
			}
		}
		return null;
	}

	private Rectangle getNewContentBounds(final List<? extends Object> list, final boolean withValueBounds) {
		Rectangle selectionExtend = null;
		for (final Object selElem : list) {
			final GraphicalViewer graphicalViewer = getWorkbenchPart().getAdapter(GraphicalViewer.class);
			final Object object = graphicalViewer.getEditPartRegistry().get(selElem);

			if ((object instanceof GraphicalEditPart) && (((EditPart) object).getModel() instanceof FBNetworkElement)) {
				// only consider the selected FBNetworkElements
				final Rectangle fbBounds = ((GraphicalEditPart) object).getFigure().getBounds();

				if (selectionExtend == null) {
					selectionExtend = fbBounds.getCopy();
				} else {
					selectionExtend.union(fbBounds);
				}

				if (withValueBounds) {
					addValueBounds((FBNetworkElement) ((EditPart) object).getModel(), selectionExtend, graphicalViewer);
				}
			}
		}
		return (selectionExtend != null) ? selectionExtend : new Rectangle();
	}

	private Rectangle getNewContentBoundsWithValueBounds() {
		final Rectangle newContentBounds = getNewContentBounds(getClipboardContents(), false);
		final Rectangle newContentBoundsWithValueBounds = getNewContentBounds(getClipboardContents(), true);
		newContentBoundsWithValueBounds.x = pasteRefPosition.x
				- (newContentBoundsWithValueBounds.width - newContentBounds.width);
		newContentBoundsWithValueBounds.y = pasteRefPosition.y;

		return newContentBoundsWithValueBounds;
	}

	private static void addValueBounds(final FBNetworkElement model, final Rectangle selectionExtend,
			final GraphicalViewer graphicalViewer) {
		final Map<Object, Object> editPartRegistry = graphicalViewer.getEditPartRegistry();
		model.getInterface().getInputVars().stream().filter(Objects::nonNull)
				.map(ie -> editPartRegistry.get(ie.getValue())).filter(GraphicalEditPart.class::isInstance)
				.forEach(ep -> {
					final Rectangle pin = ((GraphicalEditPart) ep).getFigure().getBounds();
					selectionExtend.union(pin);

				});
	}

	private static org.eclipse.draw2d.geometry.Point getOffsetPosition(final GroupContentEditPart group) {
		return ContainerContentLayoutPolicy.getContainerAreaBounds(group).getTopLeft();
	}

	private static List<? extends Object> getClipboardContents() {
		final Object obj = FordiacClipboard.INSTANCE.getGraphicalContents();
		if (obj instanceof CopyPasteMessage) {
			final CopyPasteMessage copyPasteMessage = (CopyPasteMessage) obj;
			return copyPasteMessage.getData();
		}
		return Collections.emptyList();
	}

	@Override
	protected void init() {
		setId(ActionFactory.PASTE.getId());
		setText(Messages.PasteEditPartsAction_Text);
		final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
	}

	@Override
	public void runWithEvent(final Event event) {
		if (event.widget instanceof FigureCanvas) {
			// handles insertion via copy&paste
			setMouseLocationAsPastePos(event);
		}
		super.runWithEvent(event);
	}

	public void setMouseLocationAsPastePos(final Event event) {
		final FigureCanvas figureCanvas = (FigureCanvas) event.widget;
		final org.eclipse.draw2d.geometry.Point viewLocation = figureCanvas.getViewport().getViewLocation();
		org.eclipse.swt.graphics.Point mouseLocation = Display.getCurrent().getCursorLocation();
		mouseLocation = figureCanvas.toControl(mouseLocation.x, mouseLocation.y);

		if (figureCanvas.getBounds().contains(mouseLocation.x, mouseLocation.y)) {
			final ZoomManager zoomManager = ((FBNetworkEditor) getWorkbenchPart()).getZoomManger();
			mouseLocation.x += viewLocation.x;
			mouseLocation.y += viewLocation.y;
			setPastRefPosition(new org.eclipse.draw2d.geometry.Point(mouseLocation.x, mouseLocation.y)
					.scale(1.0 / zoomManager.getZoom()));
		} else {
			final Dimension visibleArea = figureCanvas.getViewport().getSize();
			setPastRefPosition(
					new Point(viewLocation.x + (visibleArea.width / 2), viewLocation.y + (visibleArea.height / 2)));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		final FordiacClipboard clipboard = FordiacClipboard.INSTANCE;
		if (clipboard.getGraphicalContents() instanceof CopyPasteMessage) {
			final CopyPasteMessage copyPasteMessage = (CopyPasteMessage) clipboard.getGraphicalContents();
			if ((pasteRefPosition != null) && (copyPasteMessage.getCutAndPasteFromSubAppCommandos() != null)) {
				copyPasteMessage.getCutAndPasteFromSubAppCommandos().setPastePos(pasteRefPosition);
			}
			if ((copyPasteMessage.getCopyStatus() == CopyStatus.CUT_PASTED)
					|| (copyPasteMessage.getCopyStatus() == CopyStatus.COPY)) {
				execute(createPasteCommand());
			} else if (isCutFromSubappToParent(copyPasteMessage)) {
				handleCutFromSubappToParent(copyPasteMessage);
			} else if (isCutFromRootToSubapp(copyPasteMessage)) {
				handleCutFromRootToSubapp(copyPasteMessage);
			} else if (isCutFromSubappToChildSubapp(copyPasteMessage)) {
				handleCutFromSubappToChildSubapp(copyPasteMessage);
			} else {
				handleCutFromSubappToAnywhere(copyPasteMessage);
			}

		}
		pasteRefPosition = null;
	}

	public void handleCutFromSubappToAnywhere(final CopyPasteMessage copyPasteMessage) {
		if (copyPasteMessage.getCopyStatus() == CopyStatus.CUT_FROM_SUBAPP) {
			copyPasteMessage.getCutAndPasteFromSubAppCommandos().undo();
			execute(copyPasteMessage.getDeleteCommandos());
		}
		final Command createPasteCommand = createPasteCommand();
		if ((createPasteCommand instanceof PasteCommand)
				&& (copyPasteMessage.getCutAndPasteFromSubAppCommandos() != null)) {
			((PasteCommand) createPasteCommand).setCutPasteCmd(copyPasteMessage.getCutAndPasteFromSubAppCommandos());
		}

		execute(createPasteCommand);
	}

	@Override
	protected void execute(final Command command) {
		if ((command != null) && command.canExecute()) {
			final CopyPasteMessage copyPasteMessage = (CopyPasteMessage) FordiacClipboard.INSTANCE.getGraphicalContents();
			if (copyPasteMessage.getCopyStatus() == CopyStatus.CUT_FROM_ROOT) {
				copyPasteMessage.setCopyInfo(CopyStatus.CUT_PASTED);
			}
		}
		super.execute(command);
	}

	private void handleCutFromSubappToChildSubapp(final CopyPasteMessage copyPasteMessage) {
		final Object selection = getSelectedObjects().get(0);
		final SubApp targetSubapp = getSubapp(selection);
		copyPasteMessage.getCutAndPasteFromSubAppCommandos().undo();
		execute(new AddElementsToSubAppCommand(targetSubapp, copyPasteMessage.getData()));
	}

	private boolean isCutFromSubappToChildSubapp(final CopyPasteMessage copyPasteMessage) {
		if (!isSelectionSubapp()) {
			return false;
		}
		final SubApp targetSubapp = getSubapp(getSelectedObjects().get(0));

		final List<FBNetworkElement> elements = copyPasteMessage.getCutAndPasteFromSubAppCommandos().getElements();

		if (copyPasteMessage.getCopyStatus() == CopyStatus.CUT_FROM_SUBAPP) {
			// we need to undo the delete because otherwise it would not be possible to
			// check whether they are in the
			// same network
			copyPasteMessage.getCutAndPasteFromSubAppCommandos().undo();
			if (!FBNetworkHelper.targetSubappIsInSameFbNetwork(elements, targetSubapp)) {
				copyPasteMessage.getCutAndPasteFromSubAppCommandos().redo();
				return false;
			}
			copyPasteMessage.getCutAndPasteFromSubAppCommandos().redo();
		}

		return copyPasteMessage.getCopyStatus() == CopyStatus.CUT_FROM_SUBAPP;
	}

	private void handleCutFromRootToSubapp(final CopyPasteMessage copyPasteMessage) {
		final Object selection = getSelectedObjects().get(0);
		final SubApp targetSubapp = getSubapp(selection);
		copyPasteMessage.getDeleteCommandos().undo();
		execute(new AddElementsToSubAppCommand(targetSubapp, copyPasteMessage.getData()));

	}

	protected static SubApp getSubapp(final Object object) {
		SubApp targetSubapp = null;
		if (object instanceof UISubAppNetworkEditPart) {
			targetSubapp = ((UISubAppNetworkEditPart) object).getSubApp();
		} else {
			targetSubapp = ((SubAppForFBNetworkEditPart) object).getModel();
		}
		return targetSubapp;
	}

	private boolean isCutFromRootToSubapp(final CopyPasteMessage copyPasteMessage) {
		if (!isSelectionSubapp()) {
			return false;
		}
		return copyPasteMessage.getCopyStatus() == CopyStatus.CUT_FROM_ROOT;
	}

	protected boolean isSelectionSubapp() {
		return (getSelectedObjects().get(0) instanceof SubAppForFBNetworkEditPart)
				|| (getSelectedObjects().get(0) instanceof UISubAppNetworkEditPart);
	}

	protected boolean isCutFromSubappToParent(final CopyPasteMessage copyPasteMessage) {

		if (copyPasteMessage.getCopyStatus() != CopyStatus.CUT_FROM_SUBAPP) {
			return false;
		}

		final List<?> selectedObjects = getSelectedObjects();
		if (selectedObjects.size() > 1) {
			return false;
		}

		final CutAndPasteFromSubAppCommand cutAndPasteFromSubAppCommand = copyPasteMessage
				.getCutAndPasteFromSubAppCommandos();
		final SubApp sourceSubApp = cutAndPasteFromSubAppCommand.getSourceSubApp();

		if (selectedObjects.get(0) instanceof FBNetworkEditPart) {
			final FBNetworkEditPart networkEdit = (FBNetworkEditPart) selectedObjects.get(0);
			if ((networkEdit.getParent() instanceof FBNetworkRootEditPart)
					&& (sourceSubApp.eContainer().equals(networkEdit.getModel()))) {
				return true;
			}
		}

		if (!isSelectionSubapp()) {
			return false;
		}

		final FBNetworkElement outerFBNetworkElement = sourceSubApp.getOuterFBNetworkElement();

		if (outerFBNetworkElement == null) {
			return false;
		}
		final SubApp selectedSubapp = getSubapp(selectedObjects.get(0));

		if (!outerFBNetworkElement.equals(selectedSubapp)) {
			return false;
		}

		return copyPasteMessage.isCutFromSubApp();
	}

	protected void handleCutFromSubappToParent(final CopyPasteMessage copyPasteMessage) {
		execute(copyPasteMessage.getCutAndPasteFromSubAppCommandos());
		FordiacClipboard.INSTANCE.setGraphicalContents(new Object()); // Clear clipboard afterwards to prevent double
		// pasting
	}

	protected FBNetwork getFBNetwork() {
		if (getWorkbenchPart() instanceof IEditorPart) {
			return getWorkbenchPart().getAdapter(FBNetwork.class);
		}
		return null;
	}

	public void setPastRefPosition(final Point pt) {
		pasteRefPosition = pt;
	}

	public void setPastRefPosition(final org.eclipse.draw2d.geometry.Point point) {
		setPastRefPosition(new Point(point.x, point.y));
	}

}
