/*
 * generated by Xtext 2.25.0
 */
package org.eclipse.foridac.ide.structuredtextfunctioneditor.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.fordiac.ide.structuredtextcore.services.STCoreGrammarAccess;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class STFunctionGrammarAccess extends AbstractElementFinder.AbstractGrammarElementFinder {
	
	public class STFunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.foridac.ide.structuredtextfunctioneditor.STFunction.STFunction");
		private final Assignment cFunctionsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cFunctionsFunctionDefinitionParserRuleCall_0 = (RuleCall)cFunctionsAssignment.eContents().get(0);
		
		//STFunction:
		//    functions+=FunctionDefinition*;
		@Override public ParserRule getRule() { return rule; }
		
		//functions+=FunctionDefinition*
		public Assignment getFunctionsAssignment() { return cFunctionsAssignment; }
		
		//FunctionDefinition
		public RuleCall getFunctionsFunctionDefinitionParserRuleCall_0() { return cFunctionsFunctionDefinitionParserRuleCall_0; }
	}
	public class FunctionDefinitionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.foridac.ide.structuredtextfunctioneditor.STFunction.FunctionDefinition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFUNCTIONKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cColonKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cReturnTypeAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final CrossReference cReturnTypeDataTypeCrossReference_2_1_0 = (CrossReference)cReturnTypeAssignment_2_1.eContents().get(0);
		private final RuleCall cReturnTypeDataTypeIDTerminalRuleCall_2_1_0_1 = (RuleCall)cReturnTypeDataTypeCrossReference_2_1_0.eContents().get(1);
		private final Alternatives cAlternatives_3 = (Alternatives)cGroup.eContents().get(3);
		private final Assignment cVarDeclarationsAssignment_3_0 = (Assignment)cAlternatives_3.eContents().get(0);
		private final RuleCall cVarDeclarationsVarDeclarationParserRuleCall_3_0_0 = (RuleCall)cVarDeclarationsAssignment_3_0.eContents().get(0);
		private final Assignment cVarTempDeclarationsAssignment_3_1 = (Assignment)cAlternatives_3.eContents().get(1);
		private final RuleCall cVarTempDeclarationsVarTempDeclarationBlockParserRuleCall_3_1_0 = (RuleCall)cVarTempDeclarationsAssignment_3_1.eContents().get(0);
		private final Assignment cVarInpuDeclarationsAssignment_3_2 = (Assignment)cAlternatives_3.eContents().get(2);
		private final RuleCall cVarInpuDeclarationsVarInputDeclarationBlockParserRuleCall_3_2_0 = (RuleCall)cVarInpuDeclarationsAssignment_3_2.eContents().get(0);
		private final Assignment cVarOutputDeclarationsAssignment_3_3 = (Assignment)cAlternatives_3.eContents().get(3);
		private final RuleCall cVarOutputDeclarationsVarOutputDeclarationBlockParserRuleCall_3_3_0 = (RuleCall)cVarOutputDeclarationsAssignment_3_3.eContents().get(0);
		private final Assignment cCodeAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cCodeSTStatementParserRuleCall_4_0 = (RuleCall)cCodeAssignment_4.eContents().get(0);
		private final Keyword cEND_FUNCTIONKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//FunctionDefinition:
		//    'FUNCTION' name=ID (':' returnType=[datatype::DataType])?
		//        (varDeclarations+=VarDeclaration | varTempDeclarations+=VarTempDeclarationBlock |
		//        varInpuDeclarations+=VarInputDeclarationBlock | varOutputDeclarations+=VarOutputDeclarationBlock)*
		//        code += STStatement*
		//    'END_FUNCTION';
		@Override public ParserRule getRule() { return rule; }
		
		//'FUNCTION' name=ID (':' returnType=[datatype::DataType])?
		//    (varDeclarations+=VarDeclaration | varTempDeclarations+=VarTempDeclarationBlock |
		//    varInpuDeclarations+=VarInputDeclarationBlock | varOutputDeclarations+=VarOutputDeclarationBlock)*
		//    code += STStatement*
		//'END_FUNCTION'
		public Group getGroup() { return cGroup; }
		
		//'FUNCTION'
		public Keyword getFUNCTIONKeyword_0() { return cFUNCTIONKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//(':' returnType=[datatype::DataType])?
		public Group getGroup_2() { return cGroup_2; }
		
		//':'
		public Keyword getColonKeyword_2_0() { return cColonKeyword_2_0; }
		
		//returnType=[datatype::DataType]
		public Assignment getReturnTypeAssignment_2_1() { return cReturnTypeAssignment_2_1; }
		
		//[datatype::DataType]
		public CrossReference getReturnTypeDataTypeCrossReference_2_1_0() { return cReturnTypeDataTypeCrossReference_2_1_0; }
		
		//ID
		public RuleCall getReturnTypeDataTypeIDTerminalRuleCall_2_1_0_1() { return cReturnTypeDataTypeIDTerminalRuleCall_2_1_0_1; }
		
		//(varDeclarations+=VarDeclaration | varTempDeclarations+=VarTempDeclarationBlock |
		//varInpuDeclarations+=VarInputDeclarationBlock | varOutputDeclarations+=VarOutputDeclarationBlock)*
		public Alternatives getAlternatives_3() { return cAlternatives_3; }
		
		//varDeclarations+=VarDeclaration
		public Assignment getVarDeclarationsAssignment_3_0() { return cVarDeclarationsAssignment_3_0; }
		
		//VarDeclaration
		public RuleCall getVarDeclarationsVarDeclarationParserRuleCall_3_0_0() { return cVarDeclarationsVarDeclarationParserRuleCall_3_0_0; }
		
		//varTempDeclarations+=VarTempDeclarationBlock
		public Assignment getVarTempDeclarationsAssignment_3_1() { return cVarTempDeclarationsAssignment_3_1; }
		
		//VarTempDeclarationBlock
		public RuleCall getVarTempDeclarationsVarTempDeclarationBlockParserRuleCall_3_1_0() { return cVarTempDeclarationsVarTempDeclarationBlockParserRuleCall_3_1_0; }
		
		//varInpuDeclarations+=VarInputDeclarationBlock
		public Assignment getVarInpuDeclarationsAssignment_3_2() { return cVarInpuDeclarationsAssignment_3_2; }
		
		//VarInputDeclarationBlock
		public RuleCall getVarInpuDeclarationsVarInputDeclarationBlockParserRuleCall_3_2_0() { return cVarInpuDeclarationsVarInputDeclarationBlockParserRuleCall_3_2_0; }
		
		//varOutputDeclarations+=VarOutputDeclarationBlock
		public Assignment getVarOutputDeclarationsAssignment_3_3() { return cVarOutputDeclarationsAssignment_3_3; }
		
		//VarOutputDeclarationBlock
		public RuleCall getVarOutputDeclarationsVarOutputDeclarationBlockParserRuleCall_3_3_0() { return cVarOutputDeclarationsVarOutputDeclarationBlockParserRuleCall_3_3_0; }
		
		//code += STStatement*
		public Assignment getCodeAssignment_4() { return cCodeAssignment_4; }
		
		//STStatement
		public RuleCall getCodeSTStatementParserRuleCall_4_0() { return cCodeSTStatementParserRuleCall_4_0; }
		
		//'END_FUNCTION'
		public Keyword getEND_FUNCTIONKeyword_5() { return cEND_FUNCTIONKeyword_5; }
	}
	
	
	private final STFunctionElements pSTFunction;
	private final FunctionDefinitionElements pFunctionDefinition;
	
	private final Grammar grammar;
	
	private final STCoreGrammarAccess gaSTCore;

	@Inject
	public STFunctionGrammarAccess(GrammarProvider grammarProvider,
			STCoreGrammarAccess gaSTCore) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaSTCore = gaSTCore;
		this.pSTFunction = new STFunctionElements();
		this.pFunctionDefinition = new FunctionDefinitionElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.eclipse.foridac.ide.structuredtextfunctioneditor.STFunction".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public STCoreGrammarAccess getSTCoreGrammarAccess() {
		return gaSTCore;
	}

	
	//STFunction:
	//    functions+=FunctionDefinition*;
	public STFunctionElements getSTFunctionAccess() {
		return pSTFunction;
	}
	
	public ParserRule getSTFunctionRule() {
		return getSTFunctionAccess().getRule();
	}
	
	//FunctionDefinition:
	//    'FUNCTION' name=ID (':' returnType=[datatype::DataType])?
	//        (varDeclarations+=VarDeclaration | varTempDeclarations+=VarTempDeclarationBlock |
	//        varInpuDeclarations+=VarInputDeclarationBlock | varOutputDeclarations+=VarOutputDeclarationBlock)*
	//        code += STStatement*
	//    'END_FUNCTION';
	public FunctionDefinitionElements getFunctionDefinitionAccess() {
		return pFunctionDefinition;
	}
	
	public ParserRule getFunctionDefinitionRule() {
		return getFunctionDefinitionAccess().getRule();
	}
	
	//Code:
	//    statements+=STStatement*;
	public STCoreGrammarAccess.CodeElements getCodeAccess() {
		return gaSTCore.getCodeAccess();
	}
	
	public ParserRule getCodeRule() {
		return getCodeAccess().getRule();
	}
	
	//VarDeclarationBlock returns VarDeclarationBlock:
	//    {VarDeclarationBlock} 'VAR' (constant?='CONSTANT')?
	//    varDeclarations+=VarDeclaration*
	//    'END_VAR';
	public STCoreGrammarAccess.VarDeclarationBlockElements getVarDeclarationBlockAccess() {
		return gaSTCore.getVarDeclarationBlockAccess();
	}
	
	public ParserRule getVarDeclarationBlockRule() {
		return getVarDeclarationBlockAccess().getRule();
	}
	
	//VarTempDeclarationBlock returns VarDeclarationBlock:
	//    {VarDeclarationBlock} 'VAR_TEMP' (constant?='CONSTANT')?
	//    varDeclarations+=VarDeclaration*
	//    'END_VAR';
	public STCoreGrammarAccess.VarTempDeclarationBlockElements getVarTempDeclarationBlockAccess() {
		return gaSTCore.getVarTempDeclarationBlockAccess();
	}
	
	public ParserRule getVarTempDeclarationBlockRule() {
		return getVarTempDeclarationBlockAccess().getRule();
	}
	
	//VarInputDeclarationBlock returns VarDeclarationBlock:
	//    {VarDeclarationBlock} 'VAR_INPUT' (constant?='CONSTANT')?
	//    varDeclarations+=VarDeclaration*
	//    'END_VAR';
	public STCoreGrammarAccess.VarInputDeclarationBlockElements getVarInputDeclarationBlockAccess() {
		return gaSTCore.getVarInputDeclarationBlockAccess();
	}
	
	public ParserRule getVarInputDeclarationBlockRule() {
		return getVarInputDeclarationBlockAccess().getRule();
	}
	
	//VarOutputDeclarationBlock returns VarDeclarationBlock:
	//    {VarDeclarationBlock} 'VAR_OUTPUT' (constant?='CONSTANT')?
	//    varDeclarations+=VarDeclaration*
	//    'END_VAR';
	public STCoreGrammarAccess.VarOutputDeclarationBlockElements getVarOutputDeclarationBlockAccess() {
		return gaSTCore.getVarOutputDeclarationBlockAccess();
	}
	
	public ParserRule getVarOutputDeclarationBlockRule() {
		return getVarOutputDeclarationBlockAccess().getRule();
	}
	
	//VarDeclaration returns VarDeclaration:
	//    name=ID ('AT' locatedAt=[VarDeclaration])? ':' (array?='ARRAY' (('[' ranges+=(STExpression) (','
	//    ranges+=STExpression)* ']') | ('[' count+='*' (',' count+='*')* ']')) 'OF')? (type=[libraryElement::LibraryElement]) ('[' maxLength=STExpression ']')? (':='
	//    defaultValue=InitializerExpression)? ';';
	public STCoreGrammarAccess.VarDeclarationElements getVarDeclarationAccess() {
		return gaSTCore.getVarDeclarationAccess();
	}
	
	public ParserRule getVarDeclarationRule() {
		return getVarDeclarationAccess().getRule();
	}
	
	//InitializerExpression:
	//    STExpression | ArrayInitializerExpression;
	public STCoreGrammarAccess.InitializerExpressionElements getInitializerExpressionAccess() {
		return gaSTCore.getInitializerExpressionAccess();
	}
	
	public ParserRule getInitializerExpressionRule() {
		return getInitializerExpressionAccess().getRule();
	}
	
	//ArrayInitializerExpression:
	//    '[' values+=ArrayInitElement (',' values+=ArrayInitElement)* ']';
	public STCoreGrammarAccess.ArrayInitializerExpressionElements getArrayInitializerExpressionAccess() {
		return gaSTCore.getArrayInitializerExpressionAccess();
	}
	
	public ParserRule getArrayInitializerExpressionRule() {
		return getArrayInitializerExpressionAccess().getRule();
	}
	
	//ArrayInitElement:
	//    indexOrInitExpression=STExpression ('(' initExpression=STExpression ')')?;
	public STCoreGrammarAccess.ArrayInitElementElements getArrayInitElementAccess() {
		return gaSTCore.getArrayInitElementAccess();
	}
	
	public ParserRule getArrayInitElementRule() {
		return getArrayInitElementAccess().getRule();
	}
	
	//STStatement:
	//    ((STBranchStatements | STLoopStatements | STAssignmentStatement)) ';' |
	//    {STStatements} ';';
	public STCoreGrammarAccess.STStatementElements getSTStatementAccess() {
		return gaSTCore.getSTStatementAccess();
	}
	
	public ParserRule getSTStatementRule() {
		return getSTStatementAccess().getRule();
	}
	
	//STAssignmentStatement:
	//    lhs=[VarDeclaration] op=(':=' | '=>') rhs=STExpression
	//;
	public STCoreGrammarAccess.STAssignmentStatementElements getSTAssignmentStatementAccess() {
		return gaSTCore.getSTAssignmentStatementAccess();
	}
	
	public ParserRule getSTAssignmentStatementRule() {
		return getSTAssignmentStatementAccess().getRule();
	}
	
	//STBranchStatements:
	//    STIfStatment | STCaseStatement;
	public STCoreGrammarAccess.STBranchStatementsElements getSTBranchStatementsAccess() {
		return gaSTCore.getSTBranchStatementsAccess();
	}
	
	public ParserRule getSTBranchStatementsRule() {
		return getSTBranchStatementsAccess().getRule();
	}
	
	//STIfStatment:
	//    'IF' condition=STExpression 'THEN' statements+=STStatement* elseifs+=(STElseIfPart)* (else=STElsePart)? 'END_IF';
	public STCoreGrammarAccess.STIfStatmentElements getSTIfStatmentAccess() {
		return gaSTCore.getSTIfStatmentAccess();
	}
	
	public ParserRule getSTIfStatmentRule() {
		return getSTIfStatmentAccess().getRule();
	}
	
	//STElseIfPart:
	//    'ELSIF' condition=STExpression 'THEN' statements+=STStatement*;
	public STCoreGrammarAccess.STElseIfPartElements getSTElseIfPartAccess() {
		return gaSTCore.getSTElseIfPartAccess();
	}
	
	public ParserRule getSTElseIfPartRule() {
		return getSTElseIfPartAccess().getRule();
	}
	
	//STCaseStatement:
	//    'CASE' selector=STExpression 'OF' cases+=STCaseCases+ (else=STElsePart)? 'END_CASE';
	public STCoreGrammarAccess.STCaseStatementElements getSTCaseStatementAccess() {
		return gaSTCore.getSTCaseStatementAccess();
	}
	
	public ParserRule getSTCaseStatementRule() {
		return getSTCaseStatementAccess().getRule();
	}
	
	//STCaseCases:
	//    conditions+=STExpression (',' conditions+=STExpression)* ':'=> statements+=STStatement*;
	public STCoreGrammarAccess.STCaseCasesElements getSTCaseCasesAccess() {
		return gaSTCore.getSTCaseCasesAccess();
	}
	
	public ParserRule getSTCaseCasesRule() {
		return getSTCaseCasesAccess().getRule();
	}
	
	//STElsePart:
	//    {STElsePart} 'ELSE' statements+=STStatement*;
	public STCoreGrammarAccess.STElsePartElements getSTElsePartAccess() {
		return gaSTCore.getSTElsePartAccess();
	}
	
	public ParserRule getSTElsePartRule() {
		return getSTElsePartAccess().getRule();
	}
	
	//STLoopStatements:
	//    STForStatement | STWhileStatement | STRepeatStatement;
	public STCoreGrammarAccess.STLoopStatementsElements getSTLoopStatementsAccess() {
		return gaSTCore.getSTLoopStatementsAccess();
	}
	
	public ParserRule getSTLoopStatementsRule() {
		return getSTLoopStatementsAccess().getRule();
	}
	
	//STForStatement:
	//    'FOR' for=STExpression 'TO' to=STExpression ('BY' by=STExpression)? 'DO'
	//    statements+=STStatement*
	//    'END_FOR';
	public STCoreGrammarAccess.STForStatementElements getSTForStatementAccess() {
		return gaSTCore.getSTForStatementAccess();
	}
	
	public ParserRule getSTForStatementRule() {
		return getSTForStatementAccess().getRule();
	}
	
	//STWhileStatement:
	//    'WHILE' condition=STExpression 'DO'
	//    statements+=STStatement*
	//    'END_WHILE';
	public STCoreGrammarAccess.STWhileStatementElements getSTWhileStatementAccess() {
		return gaSTCore.getSTWhileStatementAccess();
	}
	
	public ParserRule getSTWhileStatementRule() {
		return getSTWhileStatementAccess().getRule();
	}
	
	//STRepeatStatement:
	//    'REPEAT'
	//    statements+=STStatement*
	//    'UNTIL' condition=STExpression
	//    'END_REPEAT';
	public STCoreGrammarAccess.STRepeatStatementElements getSTRepeatStatementAccess() {
		return gaSTCore.getSTRepeatStatementAccess();
	}
	
	public ParserRule getSTRepeatStatementRule() {
		return getSTRepeatStatementAccess().getRule();
	}
	
	//STExpression returns STExpression:
	//    STSubrangeExpression;
	public STCoreGrammarAccess.STExpressionElements getSTExpressionAccess() {
		return gaSTCore.getSTExpressionAccess();
	}
	
	public ParserRule getSTExpressionRule() {
		return getSTExpressionAccess().getRule();
	}
	
	//STSubrangeExpression returns STExpression:
	//    STOrExpression (({STSubrangeExpression.lowerBound=current} '..') upperBound=STOrExpression)*;
	public STCoreGrammarAccess.STSubrangeExpressionElements getSTSubrangeExpressionAccess() {
		return gaSTCore.getSTSubrangeExpressionAccess();
	}
	
	public ParserRule getSTSubrangeExpressionRule() {
		return getSTSubrangeExpressionAccess().getRule();
	}
	
	//STOrExpression returns STExpression:
	//    STXorExpression (({STOrExpression.left=current} op='OR') right=STXorExpression)*;
	public STCoreGrammarAccess.STOrExpressionElements getSTOrExpressionAccess() {
		return gaSTCore.getSTOrExpressionAccess();
	}
	
	public ParserRule getSTOrExpressionRule() {
		return getSTOrExpressionAccess().getRule();
	}
	
	//STXorExpression returns STExpression:
	//    STAndExpression (({STXorExpression.left=current} op='XOR') right=STAndExpression)*;
	public STCoreGrammarAccess.STXorExpressionElements getSTXorExpressionAccess() {
		return gaSTCore.getSTXorExpressionAccess();
	}
	
	public ParserRule getSTXorExpressionRule() {
		return getSTXorExpressionAccess().getRule();
	}
	
	//STAndExpression returns STExpression:
	//    STEqualityExpression (({STAndExpression.left=current} op=('&' | 'AND')) right=STEqualityExpression)*;
	public STCoreGrammarAccess.STAndExpressionElements getSTAndExpressionAccess() {
		return gaSTCore.getSTAndExpressionAccess();
	}
	
	public ParserRule getSTAndExpressionRule() {
		return getSTAndExpressionAccess().getRule();
	}
	
	//STEqualityExpression returns STExpression:
	//    STComparisonExpression (({STEqualityExpression.left=current} op=('=' | '<>')) right=STComparisonExpression)*;
	public STCoreGrammarAccess.STEqualityExpressionElements getSTEqualityExpressionAccess() {
		return gaSTCore.getSTEqualityExpressionAccess();
	}
	
	public ParserRule getSTEqualityExpressionRule() {
		return getSTEqualityExpressionAccess().getRule();
	}
	
	//STComparisonExpression returns STExpression:
	//    STAddSubExpression (({STComparisonExpression.left=current} op=('<' | '>' | '<=' | '>=')) right=STAddSubExpression)*;
	public STCoreGrammarAccess.STComparisonExpressionElements getSTComparisonExpressionAccess() {
		return gaSTCore.getSTComparisonExpressionAccess();
	}
	
	public ParserRule getSTComparisonExpressionRule() {
		return getSTComparisonExpressionAccess().getRule();
	}
	
	//STAddSubExpression returns STExpression:
	//    STMulDivModExpression (({STAddSubExpression.left=current} op=('+' | '-')) right=STMulDivModExpression)*;
	public STCoreGrammarAccess.STAddSubExpressionElements getSTAddSubExpressionAccess() {
		return gaSTCore.getSTAddSubExpressionAccess();
	}
	
	public ParserRule getSTAddSubExpressionRule() {
		return getSTAddSubExpressionAccess().getRule();
	}
	
	//STMulDivModExpression returns STExpression:
	//    STPowerExpression (({STMulDivModExpression.left=current} op=('*' | '/' | 'MOD')) right=STPowerExpression)*;
	public STCoreGrammarAccess.STMulDivModExpressionElements getSTMulDivModExpressionAccess() {
		return gaSTCore.getSTMulDivModExpressionAccess();
	}
	
	public ParserRule getSTMulDivModExpressionRule() {
		return getSTMulDivModExpressionAccess().getRule();
	}
	
	//STPowerExpression returns STExpression:
	//    STSignumExpression (({STPowerExpression.left=current} op='**') right=STSignumExpression)*;
	public STCoreGrammarAccess.STPowerExpressionElements getSTPowerExpressionAccess() {
		return gaSTCore.getSTPowerExpressionAccess();
	}
	
	public ParserRule getSTPowerExpressionRule() {
		return getSTPowerExpressionAccess().getRule();
	}
	
	//STSignumExpression returns STExpression:
	//    STLiteralExpressions | STSelectionExpression | ({STSignumExpression} signum=('-' | '+' | 'NOT')
	//    expression=STSelectionExpression);
	public STCoreGrammarAccess.STSignumExpressionElements getSTSignumExpressionAccess() {
		return gaSTCore.getSTSignumExpressionAccess();
	}
	
	public ParserRule getSTSignumExpressionRule() {
		return getSTSignumExpressionAccess().getRule();
	}
	
	//STSelectionExpression returns STExpression:
	//    STAtomicExpression
	//    ({STMemberSelection.receiver=current} (structAccess?='.' member=[VarDeclaration] | arrayAccess?='['
	//    index+=STExpression (',' index+=STExpression)* ']')
	//    (=>poeInvocation?='(' (parameters+=STExpression (',' parameters+=STExpression)*)? ')')?
	//    (=>bitaccessor=MultibitPartialAccess)?
	//    )*;
	public STCoreGrammarAccess.STSelectionExpressionElements getSTSelectionExpressionAccess() {
		return gaSTCore.getSTSelectionExpressionAccess();
	}
	
	public ParserRule getSTSelectionExpressionRule() {
		return getSTSelectionExpressionAccess().getRule();
	}
	
	//MultibitPartialAccess:
	//    (accessSpecifier=MultiBitAccessSpecifier) index=INT;
	public STCoreGrammarAccess.MultibitPartialAccessElements getMultibitPartialAccessAccess() {
		return gaSTCore.getMultibitPartialAccessAccess();
	}
	
	public ParserRule getMultibitPartialAccessRule() {
		return getMultibitPartialAccessAccess().getRule();
	}
	
	//enum MultiBitAccessSpecifier:
	//    lwordAccess='.%L' | dwordAccess='.%D' | wordAccess='.%W' | byteAccess='.%B' | bitAccess='.%X' |
	//    bitAccessShortcut='.';
	public STCoreGrammarAccess.MultiBitAccessSpecifierElements getMultiBitAccessSpecifierAccess() {
		return gaSTCore.getMultiBitAccessSpecifierAccess();
	}
	
	public EnumRule getMultiBitAccessSpecifierRule() {
		return getMultiBitAccessSpecifierAccess().getRule();
	}
	
	//STAtomicExpression returns STExpression:
	//    '(' STExpression ')' |
	//    {STSymbol} (type=[datatype::DataType] '#')? symbol=[VarDeclaration] (bitaccessor=MultibitPartialAccess)? (=>poeInvocation?=
	//    '(' (parameters+=STExpression (',' parameters+=STExpression)*)? ')')? |
	//    {STReturn} 'RETURN' |
	//    {STContinue} 'CONTINUE' |
	//    {STExit} 'EXIT';
	public STCoreGrammarAccess.STAtomicExpressionElements getSTAtomicExpressionAccess() {
		return gaSTCore.getSTAtomicExpressionAccess();
	}
	
	public ParserRule getSTAtomicExpressionRule() {
		return getSTAtomicExpressionAccess().getRule();
	}
	
	//STLiteralExpressions returns STExpression:
	//    {STBoolLiteral} boolLiteral=BOOL_LITERAL |
	//    {STNumericLiteral} numericLiteral=NUMERIC_LITERAL |
	//    {STDateLiteral} dateLiteral=DATE_LITERAL |
	//    {STTimeLiteral} timeLiteral=TIME_LITERAL |
	//    {STTimeOfDayLiteral} timeOfDayLiteral=TIME_OF_DAY_LITERAL |
	//    {STDateAndTimeLiteral} timeLiteral=DATE_AND_TIME_LITERAL |
	//    {STStringLiteral} stringLiteral=STRING_LITERAL;
	public STCoreGrammarAccess.STLiteralExpressionsElements getSTLiteralExpressionsAccess() {
		return gaSTCore.getSTLiteralExpressionsAccess();
	}
	
	public ParserRule getSTLiteralExpressionsRule() {
		return getSTLiteralExpressionsAccess().getRule();
	}
	
	//BOOL_LITERAL:
	//    (not='NOT')? ('BOOL#')? keyWordValue=BOOL_VALUES
	//;
	public STCoreGrammarAccess.BOOL_LITERALElements getBOOL_LITERALAccess() {
		return gaSTCore.getBOOL_LITERALAccess();
	}
	
	public ParserRule getBOOL_LITERALRule() {
		return getBOOL_LITERALAccess().getRule();
	}
	
	//NUMERIC_LITERAL:
	//    (not='NOT')?
	//    (keyword=('BYTE#' | 'WORD#' | 'DWORD#' | 'LWORD#' | 'SINT#' | 'INT#' | 'DINT#' | 'LINT#' | 'USINT#' |
	//    'UINT#' | 'UDINT#' | 'ULINT#' |    'REAL#' | 'LREAL#'))?
	//    (intValue=INTEGER | realValue=REAL | hexValue=NON_DECIMAL);
	public STCoreGrammarAccess.NUMERIC_LITERALElements getNUMERIC_LITERALAccess() {
		return gaSTCore.getNUMERIC_LITERALAccess();
	}
	
	public ParserRule getNUMERIC_LITERALRule() {
		return getNUMERIC_LITERALAccess().getRule();
	}
	
	//DATE_LITERAL:
	//    keyword=('DATE#' | 'LDATE#' | 'D#' | 'LD#') value=DATE;
	public STCoreGrammarAccess.DATE_LITERALElements getDATE_LITERALAccess() {
		return gaSTCore.getDATE_LITERALAccess();
	}
	
	public ParserRule getDATE_LITERALRule() {
		return getDATE_LITERALAccess().getRule();
	}
	
	//TIME_LITERAL:
	//    keyword=('TIME#' | 'LTIME#' | 'T#' | 'LT#') value=TIME;
	public STCoreGrammarAccess.TIME_LITERALElements getTIME_LITERALAccess() {
		return gaSTCore.getTIME_LITERALAccess();
	}
	
	public ParserRule getTIME_LITERALRule() {
		return getTIME_LITERALAccess().getRule();
	}
	
	//TIME_OF_DAY_LITERAL:
	//    keyword=('TIME_OF_DAY#' | 'TOD#' | 'LTOD#') value=TIME_OF_DAY;
	public STCoreGrammarAccess.TIME_OF_DAY_LITERALElements getTIME_OF_DAY_LITERALAccess() {
		return gaSTCore.getTIME_OF_DAY_LITERALAccess();
	}
	
	public ParserRule getTIME_OF_DAY_LITERALRule() {
		return getTIME_OF_DAY_LITERALAccess().getRule();
	}
	
	//DATE_AND_TIME_LITERAL:
	//    keyword=('DATE_AND_TIME#' | 'LDATE_AND_TIME#' | 'DT#' | 'LDT#') dateValue=DATE '-' timeOfDayValue=TIME_OF_DAY;
	public STCoreGrammarAccess.DATE_AND_TIME_LITERALElements getDATE_AND_TIME_LITERALAccess() {
		return gaSTCore.getDATE_AND_TIME_LITERALAccess();
	}
	
	public ParserRule getDATE_AND_TIME_LITERALRule() {
		return getDATE_AND_TIME_LITERALAccess().getRule();
	}
	
	//STRING_LITERAL:
	//    (keyword='STRING#' | 'WSTRING#' | 'CHAR#' | 'WCHAR#')? value=STRING;
	public STCoreGrammarAccess.STRING_LITERALElements getSTRING_LITERALAccess() {
		return gaSTCore.getSTRING_LITERALAccess();
	}
	
	public ParserRule getSTRING_LITERALRule() {
		return getSTRING_LITERALAccess().getRule();
	}
	
	//terminal BOOL_VALUES returns ecore::EBoolean:
	//    'TRUE' | 'FALSE';
	public TerminalRule getBOOL_VALUESRule() {
		return gaSTCore.getBOOL_VALUESRule();
	}
	
	//terminal fragment HEX_DIGIT:
	//    '0'..'9' | 'a'..'f' | 'A'..'F' | '_';
	public TerminalRule getHEX_DIGITRule() {
		return gaSTCore.getHEX_DIGITRule();
	}
	
	//terminal NON_DECIMAL returns ecore::EBigInteger:
	//    ('2#' | '8#' | '16#') HEX_DIGIT+;
	public TerminalRule getNON_DECIMALRule() {
		return gaSTCore.getNON_DECIMALRule();
	}
	
	//terminal INT returns ecore::EBigInteger:
	//    ('0'..'9') ('_'? '0'..'9')*;
	public TerminalRule getINTRule() {
		return gaSTCore.getINTRule();
	}
	
	//QualifiedName:
	//    ID ('.' ID)*;
	public STCoreGrammarAccess.QualifiedNameElements getQualifiedNameAccess() {
		return gaSTCore.getQualifiedNameAccess();
	}
	
	public ParserRule getQualifiedNameRule() {
		return getQualifiedNameAccess().getRule();
	}
	
	//INTEGER returns ecore::EBigInteger:
	//    ('+' | '-')? INT;
	public STCoreGrammarAccess.INTEGERElements getINTEGERAccess() {
		return gaSTCore.getINTEGERAccess();
	}
	
	public ParserRule getINTEGERRule() {
		return getINTEGERAccess().getRule();
	}
	
	//REAL returns ecore::EBigDecimal:
	//    INTEGER '.' (EXT_INT | INT);
	public STCoreGrammarAccess.REALElements getREALAccess() {
		return gaSTCore.getREALAccess();
	}
	
	public ParserRule getREALRule() {
		return getREALAccess().getRule();
	}
	
	// // INT ? '.' (EXT_INT | INT);
	//DATE returns ecore::EDate:
	//    INT '-' INT '-' INT;
	public STCoreGrammarAccess.DATEElements getDATEAccess() {
		return gaSTCore.getDATEAccess();
	}
	
	public ParserRule getDATERule() {
		return getDATEAccess().getRule();
	}
	
	//terminal TIME:
	//    ('+' | '-')? (INT ('.' INT)? ('D' | 'H' | 'M' | 'S' | 'MS' | 'US' | 'NS') ('_')?)+;
	public TerminalRule getTIMERule() {
		return gaSTCore.getTIMERule();
	}
	
	//TIME_OF_DAY:
	//    INT ':' INT ':' INT ('.' INT)?;
	public STCoreGrammarAccess.TIME_OF_DAYElements getTIME_OF_DAYAccess() {
		return gaSTCore.getTIME_OF_DAYAccess();
	}
	
	public ParserRule getTIME_OF_DAYRule() {
		return getTIME_OF_DAYAccess().getRule();
	}
	
	//terminal EXT_INT:
	//    INT ('e' | 'E') ('-' | '+')? INT;
	public TerminalRule getEXT_INTRule() {
		return gaSTCore.getEXT_INTRule();
	}
	
	////DATA_TYPE_KEYWORDS:
	////    ANY_TYPE_KEYWORDS | BOOL_KEYWORD | MULTIBITS_TYPE_KEYWORDS | SIGNED_INT_TYPE_KEYWORDS | UNSIGNED_INT_TYPE_KEYWORDS |
	////    REAL_TYPE_KEYWORDS | DATE_TYPE_KEYWORDS | TIME_TYPE_KEYWORDS | DATE_AND_TIME_TYPE_KEYWORDS | TOD_TYPE_KEYWORDS |
	////    STRING_TYPE_KEYWORDS;
	////terminal ANY_TYPE_KEYWORDS:
	////    'ANY' | 'ANY_DERIVED' | 'ANY_ELEMENTARY' | 'ANY_MAGNITUDE' | 'ANY_NUM' | 'ANY_REAL' | 'ANY_INT' | 'ANY_UNSIGNED' |
	////    'ANY_SIGNED' | 'ANY_DURATION' | 'ANY_BIT' | 'ANY_CHARS' | 'ANY_STRING' | 'ANY_CHAR' | 'ANY_DATE';
	////
	//terminal ID:
	//    '^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaSTCore.getIDRule();
	}
	
	//terminal STRING returns ecore::EString:
	//    '"' ('$' . /* 'L'|'N'|'P'|'R'|'T'|'"'|'$' */ | !('$' | '"'))* '"';
	public TerminalRule getSTRINGRule() {
		return gaSTCore.getSTRINGRule();
	}
	
	//terminal WSTRING returns ecore::EString:
	//    "'" ('$' . /* "L"|"N"|"P"|"R"|"T"|"'"|"$" */ | !('$' | "'"))* "'";
	public TerminalRule getWSTRINGRule() {
		return gaSTCore.getWSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//    '/*'->'*/' | '(*'->'*)';
	public TerminalRule getML_COMMENTRule() {
		return gaSTCore.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//    '//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaSTCore.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//    (' ' | '\t' | '\r' | '\n')+;
	public TerminalRule getWSRule() {
		return gaSTCore.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//    .;
	public TerminalRule getANY_OTHERRule() {
		return gaSTCore.getANY_OTHERRule();
	}
}
