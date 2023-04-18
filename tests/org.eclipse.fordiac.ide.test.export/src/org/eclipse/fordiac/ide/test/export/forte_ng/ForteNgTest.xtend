/*******************************************************************************
 * Copyright (c) 2020 Johannes Kepler University
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Ernst Blecha
 *     - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.test.export.forte_ng

import org.eclipse.fordiac.ide.test.export.ExporterTestBase
import org.eclipse.fordiac.ide.test.export.ExporterTestBasicFBTypeBase
import org.junit.jupiter.api.Test

import static org.eclipse.fordiac.ide.model.FordiacKeywords.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertNull
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.fail

class ForteNgTest extends ExporterTestBasicFBTypeBase {

	@Test
	def emptyExpression() {
		var generatedCode = generateExpression(functionBlock, "", errors) // $NON-NLS-1$
		assertNoErrors(errors) // Expression can be empty
		assertNull(generatedCode)
	}

	@Test
	def assignmentExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, BOOL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» := 1''', errors) // $NON-NLS-1$
		assertErrors(errors) // Expression can not be an assignment
		assertNull(generatedCode)
	}

	@Test
	def simpleAssignmentAlgorithm() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, BOOL))
		functionBlock.callables.add(createSTAlgorithm(ALGORITHM_NAME, '''«VARIABLE_NAME» := 1;''')) // $NON-NLS-1$
		var generatedCode = generateAlgorithm(functionBlock, ALGORITHM_NAME, errors)

		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''
			
			«EXPORTED_VARIABLE_NAME»() = CIEC_BOOL(1);
		'''.toString(), generatedCode.toString()) // $NON-NLS-1$
	}

	@Test
	def functionSQRTExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''SQRT(«VARIABLE_NAME») = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_SQRT(«EXPORTED_VARIABLE_NAME»()), CIEC_SINT(0))'''.toString(), generatedCode.toString()) // $NON-NLS-1$
	}

	@Test
	def powerExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, REAL))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» ** «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_EXPT<CIEC_REAL>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}

	@Test
	def timeAssignmentAlgorithm() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, "TIME")) // $NON-NLS-1$
		functionBlock.getCallables().add(createSTAlgorithm(ALGORITHM_NAME, '''«VARIABLE_NAME» := TIME#1m;'''))

		var generatedCode = generateAlgorithm(functionBlock, ALGORITHM_NAME, errors)

		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''
			
			«EXPORTED_VARIABLE_NAME»() = CIEC_TIME(60000000000);
		'''.toString(), generatedCode.toString())
	}

	@Test
	def dateAssignmentAlgorithm() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, "DATE")) // $NON-NLS-1$
		functionBlock.getCallables().add(createSTAlgorithm(ALGORITHM_NAME, '''«VARIABLE_NAME» := D#1996-08-12;'''))

		var generatedCode = generateAlgorithm(functionBlock, ALGORITHM_NAME, errors)

		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''
			
			«EXPORTED_VARIABLE_NAME»() = CIEC_DATE(839808000000000000);
		'''.toString(), generatedCode.toString())
	}

	@Test
	def todAssignmentAlgorithm() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, "TOD")) // $NON-NLS-1$
		functionBlock.getCallables().add(createSTAlgorithm(ALGORITHM_NAME, '''«VARIABLE_NAME» := TOD#06:06:59;'''))

		var generatedCode = generateAlgorithm(functionBlock, ALGORITHM_NAME, errors)

		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''
			
			«EXPORTED_VARIABLE_NAME»() = CIEC_TIME_OF_DAY(22019000000000);
		'''.toString(), generatedCode.toString())
	}

	@Test
	def datetimeAssignmentAlgorithm() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, "DT")) // $NON-NLS-1$
		functionBlock.getCallables().add(
			createSTAlgorithm(ALGORITHM_NAME, '''«VARIABLE_NAME» := DT#1989-06-15-13:56:14.77;'''))

		var generatedCode = generateAlgorithm(functionBlock, ALGORITHM_NAME, errors)

		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''
			
			«EXPORTED_VARIABLE_NAME»() = CIEC_DATE_AND_TIME(613922174770000000);
		'''.toString(), generatedCode.toString())
	}

	@Test
	def addExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, REAL))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» + «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_ADD<CIEC_REAL>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}

	@Test
	def subExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, REAL))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» - «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_SUB<CIEC_REAL>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}

	@Test
	def divExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, REAL))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» / «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_DIV<CIEC_REAL>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}

	@Test
	def mulExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, REAL))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» * «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_MUL<CIEC_REAL>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}
	
	@Test
	def mulTimeRealExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, TIME))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, REAL))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» * «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_MUL<CIEC_TIME>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}
	
	@Test
	def mulTimeLintExpression() {
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE_NAME, TIME))
		functionBlock.getInterfaceList().getInputVars().add(createVarDeclaration(VARIABLE2_NAME, LINT))

		var generatedCode = generateExpression(functionBlock, '''«VARIABLE_NAME» * «VARIABLE2_NAME» = 0''', errors) // $NON-NLS-1$
		assertNoErrors(errors)
		assertNotNull(generatedCode)
		assertEquals('''func_EQ(func_MUL<CIEC_TIME>(«EXPORTED_VARIABLE_NAME»(), «EXPORTED_VARIABLE2_NAME»()), CIEC_SINT(0))'''.toString(), // $NON-NLS-1$
		generatedCode.toString())
	}

	@Test
	def void otherAlgorithmBasic() {
		val ALGORITHM_TEXT = '''int i = 0; i++;'''

		functionBlock.callables.add(createOtherAlgorithm(ALGORITHM_NAME, ALGORITHM_TEXT, "C++"))

		val exports = generateFunctionBlock(functionBlock)

		var headerfileFound = false
		var cppfileFound = false

		for (export : exports) {
			switch export.getName() {
				case '''«ExporterTestBase.BASICFUNCTIONBLOCK_NAME».h''': {
					headerfileFound = true

					assertEquals('''
						/*************************************************************************
						 *** FORTE Library Element
						 ***
						 *** This file was generated using the 4DIAC FORTE Export Filter V1.0.x NG!
						 ***
						 *** Name: «ExporterTestBase.BASICFUNCTIONBLOCK_NAME»
						 *** Description: 
						 *** Version:
						 *************************************************************************/
						
						#ifndef _«ExporterTestBase.BASICFUNCTIONBLOCK_NAME.toUpperCase»_H_
						#define _«ExporterTestBase.BASICFUNCTIONBLOCK_NAME.toUpperCase»_H_
						
						#include "basicfb.h"
						#include "iec61131_functions.h"
						#include "forte_array_common.h"
						#include "forte_array.h"
						#include "forte_array_fixed.h"
						#include "forte_array_variable.h"
						
						
						class «EXPORTED_FUNCTIONBLOCK_NAME»: public CBasicFB {
						  DECLARE_FIRMWARE_FB(«EXPORTED_FUNCTIONBLOCK_NAME»)
						
						private:
						  
						  
						  
						  
						
						  static const SFBInterfaceSpec scm_stFBInterfaceSpec;
						  void «EXPORTED_ALGORITHM_NAME»(void);
						  static const TForteInt16 scm_nStateINIT = 0;
						  
						  void enterStateINIT(void);
						
						  virtual void executeEvent(int pa_nEIID);
						
						  FORTE_BASIC_FB_DATA_ARRAY(0, 0, 0, 0, 0);
						
						public:
						  «EXPORTED_FUNCTIONBLOCK_NAME»(CStringDictionary::TStringId pa_nInstanceNameId, CResource *pa_poSrcRes) :
						    CBasicFB(pa_poSrcRes, &scm_stFBInterfaceSpec, pa_nInstanceNameId, nullptr, m_anFBConnData, m_anFBVarsData) {
						  };
						
						  ~«EXPORTED_FUNCTIONBLOCK_NAME»() override = default;
						
						
						};
						
						#endif // _«ExporterTestBase.BASICFUNCTIONBLOCK_NAME.toUpperCase»_H_
						
					'''.toString(), export.data.toString())
					assertNoErrors(export.errors)
					assertNoErrors(export.warnings)
					assertNoErrors(export.infos)
				}
				case '''«ExporterTestBase.BASICFUNCTIONBLOCK_NAME».cpp''': {
					cppfileFound = true

					assertEquals('''
						/*************************************************************************
						 *** FORTE Library Element
						 ***
						 *** This file was generated using the 4DIAC FORTE Export Filter V1.0.x NG!
						 ***
						 *** Name: «ExporterTestBase.BASICFUNCTIONBLOCK_NAME»
						 *** Description: 
						 *** Version:
						 *************************************************************************/
						
						#include "«ExporterTestBase.BASICFUNCTIONBLOCK_NAME».h"
						#ifdef FORTE_ENABLE_GENERATED_SOURCE_CPP
						#include "«ExporterTestBase.BASICFUNCTIONBLOCK_NAME»_gen.cpp"
						#endif
						
						#include "iec61131_functions.h"
						#include "forte_array_common.h"
						#include "forte_array.h"
						#include "forte_array_fixed.h"
						#include "forte_array_variable.h"
						
						DEFINE_FIRMWARE_FB(«EXPORTED_FUNCTIONBLOCK_NAME», g_nStringIdfunctionblock)
						
						
						
						
						
						
						const SFBInterfaceSpec «EXPORTED_FUNCTIONBLOCK_NAME»::scm_stFBInterfaceSpec = {
						  0, nullptr, nullptr, nullptr,
						  0, nullptr, nullptr, nullptr,
						  0, nullptr, nullptr,
						  0, nullptr, nullptr,
						  0, nullptr
						};
						
						void FORTE_«ExporterTestBase.BASICFUNCTIONBLOCK_NAME»::«EXPORTED_ALGORITHM_NAME»(void) {
						  #pragma GCC warning "Algorithm of type: 'C++' may lead to unexpected results!"
						  #pragma message ("warning Algorithm of type: 'C++' may lead to unexpected results!")
						  «ALGORITHM_TEXT»
						}
						
						
						void FORTE_«ExporterTestBase.BASICFUNCTIONBLOCK_NAME»::executeEvent(int pa_nEIID){
						  do {
						    switch(m_nECCState) {
						      case scm_nStateINIT:
						        return; //no transition cleared
						      default:
						        DEVLOG_ERROR("The state is not in the valid range! The state value is: %d. The max value can be: 1.", m_nECCState.operator TForteUInt16 ());
						        m_nECCState = 0; // 0 is always the initial state
						        return;
						    }
						    pa_nEIID = cg_nInvalidEventID; // we have to clear the event after the first check in order to ensure correct behavior
						  } while(true);
						}
						
						
						void FORTE_functionblock::enterStateINIT(void) {
						  m_nECCState = scm_nStateINIT;
						}
						
						
					'''.toString(), export.data.toString())
					assertNoErrors(export.errors)
					assertNoErrors(export.warnings)
					assertNoErrors(export.infos)
				}
				default:
					fail("unexpected export file")
			}
		}
		assertTrue(headerfileFound, "Header-File missing")
		assertTrue(cppfileFound, "CPP-File missing")
	}

}
