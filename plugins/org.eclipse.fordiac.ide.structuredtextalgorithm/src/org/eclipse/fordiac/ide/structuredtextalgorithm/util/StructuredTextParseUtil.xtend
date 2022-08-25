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
package org.eclipse.fordiac.ide.structuredtextalgorithm.util

import java.util.Collection
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.fordiac.ide.model.libraryElement.BaseFBType
import org.eclipse.fordiac.ide.model.libraryElement.FBType
import org.eclipse.fordiac.ide.model.libraryElement.STAlgorithm
import org.eclipse.fordiac.ide.model.libraryElement.STMethod
import org.eclipse.fordiac.ide.structuredtextalgorithm.parser.antlr.STAlgorithmParser
import org.eclipse.fordiac.ide.structuredtextalgorithm.resource.STAlgorithmResource
import org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STAlgorithmSource
import org.eclipse.fordiac.ide.structuredtextcore.stcore.STExpression
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.parser.IParseResult
import org.eclipse.xtext.parser.IParser
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.validation.Issue

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*

class StructuredTextParseUtil extends ParseUtil{
	static final URI SYNTHETIC_URI = URI.createURI("__synthetic.stalg")
	static final IResourceServiceProvider SERVICE_PROVIDER = IResourceServiceProvider.Registry.INSTANCE.
		getResourceServiceProvider(SYNTHETIC_URI)
	static final String EXPRESSION_DEFAULT_NAME = "anonymous"

	private new() {
	}

	def static org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STAlgorithm parse(STAlgorithm algorithm,
		List<String> errors, List<String> warnings, List<String> infos) {
		val parser = SERVICE_PROVIDER.get(IParser) as STAlgorithmParser
		extension val partitioner = SERVICE_PROVIDER.get(STAlgorithmPartitioner)
		switch (root : algorithm.rootContainer) {
			BaseFBType:
				(root.combine.parse(parser.grammarAccess.STAlgorithmSourceRule, root.name, root, errors, warnings,
					infos)?.rootASTElement as STAlgorithmSource)?.elements?.filter(
					org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STAlgorithm)?.findFirst [
					name == algorithm.name
				]
			default:
				(algorithm.toSTText.parse(parser.grammarAccess.STAlgorithmRule, algorithm.name, null, errors, warnings,
					infos)?.rootASTElement as org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STAlgorithm)
		}
	}

	def static org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STMethod parse(STMethod method,
		List<String> errors, List<String> warnings, List<String> infos) {
		val parser = SERVICE_PROVIDER.get(IParser) as STAlgorithmParser
		extension val partitioner = SERVICE_PROVIDER.get(STAlgorithmPartitioner)
		switch (root : method.rootContainer) {
			BaseFBType:
				(root.combine.parse(parser.grammarAccess.STAlgorithmSourceRule, root.name, root, errors, warnings,
					infos)?.rootASTElement as STAlgorithmSource)?.elements?.filter(
					org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STMethod)?.findFirst [
					name == method.name
				]
			default:
				(method.toSTText.parse(parser.grammarAccess.STAlgorithmRule, method.name, null, errors, warnings,
					infos)?.rootASTElement as org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STMethod)
		}
	}

	def static STAlgorithmSource parse(BaseFBType fbType, List<String> errors, List<String> warnings,
		List<String> infos) {
		val parser = SERVICE_PROVIDER.get(IParser) as STAlgorithmParser
		extension val partitioner = SERVICE_PROVIDER.get(STAlgorithmPartitioner)
		fbType.combine.parse(parser.grammarAccess.STAlgorithmSourceRule, fbType.name, fbType, errors, warnings, infos)?.
			rootASTElement as STAlgorithmSource
	}

	def static List<Issue> validate(BaseFBType fbType) {
		val issues = newArrayList
		val parser = SERVICE_PROVIDER.get(IParser) as STAlgorithmParser
		extension val partitioner = SERVICE_PROVIDER.get(STAlgorithmPartitioner)
		fbType.combine.parse(parser.grammarAccess.STAlgorithmSourceRule, fbType, issues)
		return issues
	}

	def static STExpression parse(String expression, FBType fbType, List<String> errors, List<String> warnings,
		List<String> infos) {
		expression.parse(fbType, null, errors, warnings, infos)
	}

	def static STExpression parse(String expression, FBType fbType, Collection<? extends EObject> additionalContent,
		List<String> errors, List<String> warnings, List<String> infos) {
		val parser = SERVICE_PROVIDER.get(IParser) as STAlgorithmParser
		expression.parse(parser.grammarAccess.STExpressionRule, EXPRESSION_DEFAULT_NAME, fbType, additionalContent,
			errors, warnings, infos)?.rootASTElement as STExpression
	}

	def private static IParseResult parse(String text, ParserRule entryPoint, String name, FBType fbType,
		List<String> errors, List<String> warnings, List<String> infos) {
		text.parse(entryPoint, name, fbType, null, errors, warnings, infos)
	}

	def private static IParseResult parse(String text, ParserRule entryPoint, String name, FBType fbType,
		Collection<? extends EObject> additionalContent, List<String> errors, List<String> warnings,
		List<String> infos) {
		val issues = newArrayList
		val parseResult = text.parse(entryPoint, fbType, additionalContent, issues)
		name.postProcess(errors,warnings,infos,issues,parseResult)
	}

	def private static IParseResult parse(String text, ParserRule entryPoint, FBType fbType, List<Issue> issues) {
		text.parse(entryPoint, fbType, null, issues)
	}

	def private static IParseResult parse(String text, ParserRule entryPoint, FBType fbType,
		Collection<? extends EObject> additionalContent, List<Issue> issues) {
		val resourceSet = SERVICE_PROVIDER.get(ResourceSet) as XtextResourceSet
		resourceSet.loadOptions.putAll(#{
			STAlgorithmResource.OPTION_PLAIN_ST -> Boolean.TRUE
		})
		SERVICE_PROVIDER.postProcess(resourceSet,text,entryPoint,fbType,null,issues,SYNTHETIC_URI)
	}
}
