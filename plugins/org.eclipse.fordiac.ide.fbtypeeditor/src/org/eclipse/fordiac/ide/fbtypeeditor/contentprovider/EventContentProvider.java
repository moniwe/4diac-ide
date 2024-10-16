/*******************************************************************************
 * Copyright (c) 2011, 2013, 2016, 2017 fortiss GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Gerhard Ebenhofer, Alois Zoitl, Monika Wenger
 *     - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.fbtypeeditor.contentprovider;

import org.eclipse.fordiac.ide.model.libraryElement.FBType;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.jface.viewers.IStructuredContentProvider;

public class EventContentProvider implements IStructuredContentProvider {
	@Override
	public Object[] getElements(final Object inputElement) {
		if (inputElement instanceof VarDeclaration) {
			final VarDeclaration variable = (VarDeclaration) inputElement;
			final FBType fbtype = (FBType) variable.eContainer().eContainer();
			if (variable.isIsInput()) {
				return fbtype.getInterfaceList().getEventInputs().toArray();
			}
			return fbtype.getInterfaceList().getEventOutputs().toArray();
		}
		return new Object[0];
	}
}
