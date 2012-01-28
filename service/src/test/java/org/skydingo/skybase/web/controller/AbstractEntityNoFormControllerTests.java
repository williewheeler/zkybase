/*
 * AbstractEntityNoFormControllerTests.java
 *
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.skydingo.skybase.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.ParameterizedType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.web.navigation.Node;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.skydingo.skybase.web.view.ViewNames;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityNoFormControllerTests<T extends Entity<T>> {
	protected static final String VN_DELETE_ENTITY_SUCCESS = "deleteEntitySuccess";
	protected static final String VN_ENTITY_DETAILS = "entityDetails";
	protected static final String VN_ENTITY_LIST = "entityList";
	protected static final String VN_PUT_EDIT_ENTITY_FORM_SUCCESS = "putEditEntityFormSuccess";

	private static final String CONTROLLER_PACKAGE = "org.skydingo.skybase.web.controller.noform";

	// Class under test
	@InjectMocks protected AbstractEntityNoFormController<T> controller;

	// Dependencies
	@Mock protected Paths paths;
	@Mock protected Sitemap sitemap;
	@Mock protected ViewNames viewNames;

	// Test objects
	@Mock protected Node node;
	@Mock protected BindingResult result;
	@Mock protected Model model;

	protected Class<T> getEntityClass() {
		return controller.getEntityClass();
	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = newControllerInstance();
		MockitoAnnotations.initMocks(this);
		when(sitemap.getNode((String) any())).thenReturn(node);
		when(sitemap.getEntityListViewId(getEntityClass())).thenReturn(VN_ENTITY_LIST);
		when(sitemap.getEntityDetailsViewId(getEntityClass())).thenReturn(VN_ENTITY_DETAILS);
		when(viewNames.putEditFormSuccessViewName(getEntityClass(), 1L)).thenReturn(VN_PUT_EDIT_ENTITY_FORM_SUCCESS);
		when(viewNames.deleteSuccessViewName(getEntityClass())).thenReturn(VN_DELETE_ENTITY_SUCCESS);
		doSetUp();
	}

	@SuppressWarnings("unchecked")
	private Class<AbstractEntityNoFormController<T>> getControllerClass() {
		ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		String fullControllerClassName = CONTROLLER_PACKAGE + "." + entityClass.getSimpleName() + "NoFormController";
		try {
			return (Class<AbstractEntityNoFormController<T>>) Class.forName(fullControllerClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private AbstractEntityNoFormController<T> newControllerInstance() {
		try {
			return (AbstractEntityNoFormController<T>) getControllerClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void doSetUp() throws Exception { }

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		doTearDown();
	}

	protected void doTearDown() throws Exception { }

	@Test
	public void testGetEntityList() {
		String viewName = controller.getList(model);
		assertEquals(VN_ENTITY_LIST, viewName);

		// Once for navigation, once for farm list
		verify(model, times(2)).addAttribute(any());
	}

	@Test
	public void testGetEntityDetails() {
		String viewName = controller.getDetails(1L, model);
		assertEquals(VN_ENTITY_DETAILS, viewName);
	}

	@Test
	public void testDeleteEntity() {
		String viewName = controller.delete(1L);
		assertEquals(VN_DELETE_ENTITY_SUCCESS, viewName);
	}
}
