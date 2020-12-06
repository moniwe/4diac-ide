/*******************************************************************************
 * Copyright (c) 2015 fortiss GmbH
 * 				 2020 Johannes Kepler University Linz
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alois Zoitl, Gerd Kainz
 *     - initial API and implementation and/or initial documentation
 *   Alois Zoitl - Reworked system explorer layout
 *   Daniel Lindhuber - Changed getText method to suppress file endings
 *******************************************************************************/
package org.eclipse.fordiac.ide.systemmanagement.ui.systemexplorer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.fordiac.ide.model.data.provider.DataItemProviderAdapterFactory;
import org.eclipse.fordiac.ide.model.libraryElement.provider.LibraryElementItemProviderAdapterFactory;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibrary;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibraryTags;
import org.eclipse.fordiac.ide.ui.imageprovider.FordiacImage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.IDescriptionProvider;

public class SystemLabelProvider extends AdapterFactoryLabelProvider implements IDescriptionProvider {

	private static ComposedAdapterFactory systemAdapterFactory = new ComposedAdapterFactory(createFactoryList());

	public SystemLabelProvider() {
		super(systemAdapterFactory);
	}

	@Override
	public String getText(final Object object) {
		if (object instanceof IFile) {
			return getTextForFiles((IFile) object);
		}
		if (object instanceof IResource) {
			return null;
		}
		return super.getText(object);
	}

	private static String getTextForFiles(final IFile element) {
		String text = null;
		if (TypeLibraryTags.DATA_TYPE_FILE_ENDING.equalsIgnoreCase(element.getFileExtension())
				|| TypeLibraryTags.DEVICE_TYPE_FILE_ENDING.equalsIgnoreCase(element.getFileExtension())
				|| TypeLibraryTags.RESOURCE_TYPE_FILE_ENDING.equalsIgnoreCase(element.getFileExtension())
				|| TypeLibraryTags.SEGMENT_TYPE_FILE_ENDING.equalsIgnoreCase(element.getFileExtension())
				|| TypeLibraryTags.SYSTEM_TYPE_FILE_ENDING.equalsIgnoreCase(element.getFileExtension())) {
			text = TypeLibrary.getTypeNameFromFile(element);
		}
		return text;
	}

	@Override
	public Image getImage(final Object object) {
		if (object instanceof IResource) {
			return getImageForResource((IResource) object);
		}
		return super.getImage(object);
	}

	private static Image getImageForResource(final IResource resource) {
		if (FordiacProjectSorter.isTypeLibFolder(resource)) {
			return FordiacImage.ICON_TYPE_NAVIGATOR.getImage();
		}

		return null;
	}

	@Override
	public String getDescription(final Object anElement) {
		// TODO provide descriptive tooltip text here
		return super.getText(anElement);
	}

	private static List<AdapterFactory> createFactoryList() {
		final ArrayList<AdapterFactory> factories = new ArrayList<>();
		factories.add(new LibraryElementItemProviderAdapterFactory());
		factories.add(new DataItemProviderAdapterFactory());
		return factories;
	}
}
