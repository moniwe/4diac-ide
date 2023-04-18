/*******************************************************************************
 * Copyright (c) 2022 - 2023 Martin Erich Jobst
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

import org.eclipse.fordiac.ide.model.data.WcharType
import org.eclipse.fordiac.ide.model.data.WstringType
import org.eclipse.fordiac.ide.model.datatype.helper.IecTypes.ElementaryTypes
import org.eclipse.fordiac.ide.model.eval.value.AnyStringValue
import org.eclipse.fordiac.ide.model.eval.value.Value
import org.eclipse.fordiac.ide.model.eval.value.WCharValue
import org.eclipse.fordiac.ide.model.eval.value.WStringValue

class WStringCharacterVariable extends AbstractVariable<WCharValue> {
	final Variable<WStringValue> delegate
	final int index

	new(Variable<WStringValue> delegate, int index) {
		super('''«delegate.name»[«index»]''', ElementaryTypes.WCHAR)
		this.delegate = delegate
		this.index = index
	}

	override getValue() {
		delegate.value.charAt(index)
	}

	override setValue(Value value) {
		if (value instanceof WCharValue) {
			val type = delegate.type as WstringType
			if (index > (type.setMaxLength ? type.maxLength : AnyStringValue.MAX_LENGTH)) {
				throw new StringIndexOutOfBoundsException(index);
			}
			delegate.value = delegate.value.withCharAt(index, value)
		} else
			throw new ClassCastException('''Cannot assign value with incompatible type «value.type.name» as «type.name»''')
	}

	override setValue(String value) {
		value = VariableOperations.evaluateValue(type, value)
	}

	override validateValue(String value) {
		VariableOperations.validateValue(type, value).nullOrEmpty
	}

	override WcharType getType() {
		super.type as WcharType
	}
}
