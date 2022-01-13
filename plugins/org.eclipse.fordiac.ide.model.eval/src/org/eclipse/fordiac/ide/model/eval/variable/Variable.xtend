/*******************************************************************************
 * Copyright (c) 2022 Martin Erich Jobst
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Martin Jobst - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.model.eval.variable

import org.eclipse.fordiac.ide.model.eval.value.Value
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration

interface Variable {
	def String getName()
	
	def VarDeclaration getDeclaration()
	
	def Value getValue()
	
	def void setValue(Value value)
	
	def void setValue(String value)
	
	def boolean validateValue(String value)
}