/*******************************************************************************
 * Copyright (c) 2019 fortiss GmbH
 *               2020 Johannes Kepler University Linz
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Martin Jobst - initial API and implementation and/or initial documentation
 *   Alois Zoitl  - added support for structured types
 *   Ernst Blecha - added support for exporting CMakeLists.txt
 *******************************************************************************/
package org.eclipse.fordiac.ide.export.forte_ng

import java.nio.file.Paths
import org.eclipse.fordiac.ide.export.TemplateExportFilter
import org.eclipse.fordiac.ide.export.forte_ng.adapter.AdapterFBHeaderTemplate
import org.eclipse.fordiac.ide.export.forte_ng.adapter.AdapterFBImplTemplate
import org.eclipse.fordiac.ide.export.forte_ng.basic.BasicFBHeaderTemplate
import org.eclipse.fordiac.ide.export.forte_ng.basic.BasicFBImplTemplate
import org.eclipse.fordiac.ide.export.forte_ng.composite.CompositeFBHeaderTemplate
import org.eclipse.fordiac.ide.export.forte_ng.composite.CompositeFBImplTemplate
import org.eclipse.fordiac.ide.export.forte_ng.service.ServiceInterfaceFBHeaderTemplate
import org.eclipse.fordiac.ide.export.forte_ng.service.ServiceInterfaceFBImplTemplate
import org.eclipse.fordiac.ide.export.forte_ng.simple.SimpleFBHeaderTemplate
import org.eclipse.fordiac.ide.export.forte_ng.simple.SimpleFBImplTemplate
import org.eclipse.fordiac.ide.export.forte_ng.struct.StructBaseTemplate
import org.eclipse.fordiac.ide.export.forte_ng.struct.StructuredTypeHeaderTemplate
import org.eclipse.fordiac.ide.export.forte_ng.struct.StructuredTypeImplTemplate
import org.eclipse.fordiac.ide.model.data.StructuredType
import org.eclipse.fordiac.ide.model.libraryElement.AdapterType
import org.eclipse.fordiac.ide.model.libraryElement.BasicFBType
import org.eclipse.fordiac.ide.model.libraryElement.CompositeFBType
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElement
import org.eclipse.fordiac.ide.model.libraryElement.ServiceInterfaceFBType
import org.eclipse.fordiac.ide.model.libraryElement.SimpleFBType
import org.eclipse.fordiac.ide.export.forte_ng.cmake.CMakeListsTemplate
import org.eclipse.fordiac.ide.model.typelibrary.CMakeListsMarker

class ForteNgExportFilter extends TemplateExportFilter {

	override protected getTemplates(LibraryElement type) {
		switch (type) {
			BasicFBType:
				#{
					new BasicFBHeaderTemplate(type, '''«type.name».h''', Paths.get("")),
					new BasicFBImplTemplate(type, '''«type.name».cpp''', Paths.get(""))
				}
			SimpleFBType:
				#{
					new SimpleFBHeaderTemplate(type, '''«type.name».h''', Paths.get("")),
					new SimpleFBImplTemplate(type, '''«type.name».cpp''', Paths.get(""))
				}
			CompositeFBType:
				#{
					new CompositeFBHeaderTemplate(type, '''«type.name».h''', Paths.get("")),
					new CompositeFBImplTemplate(type, '''«type.name».cpp''', Paths.get(""))
				}
			AdapterType:
				#{
					new AdapterFBHeaderTemplate(type.adapterFBType, '''«type.name».h''', Paths.get("")),
					new AdapterFBImplTemplate(type.adapterFBType, '''«type.name».cpp''', Paths.get(""))
				}
			ServiceInterfaceFBType:
				#{
					new ServiceInterfaceFBHeaderTemplate(type, '''«type.name».h''', Paths.get("")),
					new ServiceInterfaceFBImplTemplate(type, '''«type.name».cpp''', Paths.get(""))
				}
			StructuredType:
				#{
					new StructuredTypeHeaderTemplate(type, '''«StructBaseTemplate.structuredTypeFileName(type)».h''', Paths.get("")),
					new StructuredTypeImplTemplate(type, '''«StructBaseTemplate.structuredTypeFileName(type)».cpp''', Paths.get(""))
				}
			CMakeListsMarker:
				#{
					new CMakeListsTemplate('''CMakeLists.txt''', Paths.get(""))
				}
			default: {
				errors.add('''Unknown library element type «type.class.name»''')
				emptySet
			}
		}
	}

}
