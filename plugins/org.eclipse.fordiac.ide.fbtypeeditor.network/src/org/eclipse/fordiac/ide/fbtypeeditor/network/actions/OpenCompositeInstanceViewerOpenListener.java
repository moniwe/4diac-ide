/*******************************************************************************
 * Copyright (c) 2021 Primemetals Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Michael Oberlehner - initial API and implementation and/or initial
 *                        documentation
 *   Alois Zoitl        - added support for cfb viewers in compiste and subapp
 *                        types
 *******************************************************************************/
package org.eclipse.fordiac.ide.fbtypeeditor.network.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.fordiac.ide.model.Palette.FBTypePaletteEntry;
import org.eclipse.fordiac.ide.model.Palette.PaletteEntry;
import org.eclipse.fordiac.ide.model.Palette.SubApplicationTypePaletteEntry;
import org.eclipse.fordiac.ide.model.Palette.SystemPaletteEntry;
import org.eclipse.fordiac.ide.model.libraryElement.AutomationSystem;
import org.eclipse.fordiac.ide.model.libraryElement.CompositeFBType;
import org.eclipse.fordiac.ide.model.libraryElement.FB;
import org.eclipse.fordiac.ide.model.libraryElement.SubAppType;
import org.eclipse.fordiac.ide.model.ui.actions.AbstractOpenSystemElementListener;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class OpenCompositeInstanceViewerOpenListener extends AbstractOpenSystemElementListener {

	private static final String OPEN_COMPOSITE_LISTENER_ID = "org.eclipse.fordiac.ide.fbtypeeditor.network.actions.OpenCompositeInstanceViewerOpenListener"; //$NON-NLS-1$

	private FB compositeFBInstance;

	public OpenCompositeInstanceViewerOpenListener() {
		// empty constructor for OpenListener
	}

	@Override
	public void run(final IAction action) {
		final EObject root = EcoreUtil.getRootContainer(compositeFBInstance);
		if (root instanceof AutomationSystem) {
			openInSystemEditor(((AutomationSystem) root).getSystemFile(), compositeFBInstance);
		} else if (root instanceof SubAppType) {
			openInSubappTypeEditor((SubAppType) root, compositeFBInstance);
		} else if (root instanceof CompositeFBType) {
			openInFBTypeEditor((CompositeFBType) root, compositeFBInstance);
		} else if (root instanceof SystemPaletteEntry) {
			openInSystemEditor(((PaletteEntry) root).getFile(), compositeFBInstance);
		} else if (root instanceof SubApplicationTypePaletteEntry) {
			openInSubappTypeEditor(((SubApplicationTypePaletteEntry) root).getType(), compositeFBInstance);
		} else if (root instanceof FBTypePaletteEntry) {
			openInFBTypeEditor(((FBTypePaletteEntry) root).getType(), compositeFBInstance);
		}
	}

	@Override
	public void selectionChanged(final IAction action, final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structuredSel = (IStructuredSelection) selection;
			if (structuredSel.getFirstElement() instanceof FB) {
				compositeFBInstance = (FB) structuredSel.getFirstElement();
			}
		}
	}

	@Override
	public Class<? extends EObject> getHandledClass() {
		return FB.class;
	}

	@Override
	public String getOpenListenerID() {
		return OPEN_COMPOSITE_LISTENER_ID;
	}
}
