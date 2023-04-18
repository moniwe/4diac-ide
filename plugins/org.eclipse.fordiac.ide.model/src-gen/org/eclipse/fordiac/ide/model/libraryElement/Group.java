/**
 * *******************************************************************************
 * Copyright (c) 2008 - 2018 Profactor GmbH, TU Wien ACIN, fortiss GmbH
 *               2022 Martin Erich Jobst
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Gerhard Ebenhofer, Alois Zoitl, Ingo Hegny, Monika Wenger, Martin Jobst
 *      - initial API and implementation and/or initial documentation
 * *******************************************************************************
 */
package org.eclipse.fordiac.ide.model.libraryElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.model.libraryElement.Group#getGroupElements <em>Group Elements</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.model.libraryElement.Group#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.model.libraryElement.Group#getHeight <em>Height</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends FBNetworkElement {
	/**
	 * Returns the value of the '<em><b>Group Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Elements</em>' reference list.
	 * @see org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage#getGroup_GroupElements()
	 * @see org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement#getGroup
	 * @model opposite="group" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='FB' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<FBNetworkElement> getGroupElements();

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * The default value is <code>"200"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage#getGroup_Width()
	 * @model default="200" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.fordiac.ide.model.libraryElement.Group#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * The default value is <code>"100"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage#getGroup_Height()
	 * @model default="100" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.fordiac.ide.model.libraryElement.Group#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	InterfaceList getInterface();

} // Group
