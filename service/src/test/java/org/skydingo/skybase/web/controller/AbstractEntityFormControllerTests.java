/* 
 * AbstractEntityFormControllerTests.java
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
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.web.navigation.Node;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.skydingo.skybase.web.view.ViewNames;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityFormControllerTests<T extends Entity<T>> {
	protected static final String VN_CREATE_ENTITY_FORM = "createEntityForm";
	protected static final String VN_EDIT_ENTITY_FORM = "editEntityForm";
	protected static final String VN_EDIT_ENTITY_SUCCESS = "editEntitySuccess";
	
//	private static final String REPOSITORY_PACKAGE = "org.skydingo.skybase.repository";
//	private static final String SERVICE_PACKAGE = "org.skydingo.skybase.service";
	private static final String CONTROLLER_PACKAGE = "org.skydingo.skybase.web.controller.form";
	
	private Class<T> entityClass;
	
	// Class under test
	@InjectMocks protected AbstractEntityFormController<T> controller;
	
	// Dependencies
	@Mock protected Paths paths;
	@Mock protected Sitemap sitemap;
	@Mock protected ViewNames viewNames;
	protected GraphRepository<T> repository;
	protected EntityService<T> service;
	
	// Test objects
	@Mock protected Node node;
	@Mock protected BindingResult result;
	@Mock protected Model model;
	
	protected abstract GraphRepository<T> getRepository();
	
	protected abstract EntityService<T> getService();
	
	protected abstract T getEntity();
	
	
	// =================================================================================================================
	// Lifecycle
	// =================================================================================================================
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = newControllerInstance();
		
		MockitoAnnotations.initMocks(this);
		
		when(sitemap.getNode((String) any())).thenReturn(node);
		when(sitemap.getCreateFormId(getEntityClass())).thenReturn(VN_CREATE_ENTITY_FORM);
		when(sitemap.getEditFormId(getEntityClass())).thenReturn(VN_EDIT_ENTITY_FORM);
		when(viewNames.putEditFormSuccessViewName(getEntityClass(), 1L)).thenReturn(VN_EDIT_ENTITY_SUCCESS);
		
		when(getService().findAll()).thenReturn(new ArrayList<T>());
		when(getService().findOne(anyLong())).thenReturn(getEntity());

		doSetUp();
	}
	
	protected void doSetUp() throws Exception {
		// Override as needed
	}
	
	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		if (entityClass == null) {
			ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
			this.entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	@SuppressWarnings("unchecked")
	private Class<AbstractEntityFormController<T>> getControllerClass() throws Exception {
		String fullClassName = CONTROLLER_PACKAGE + "." + getEntityClass().getSimpleName() + "FormController";
		return (Class<AbstractEntityFormController<T>>) Class.forName(fullClassName);
	}
	
	private AbstractEntityFormController<T> newControllerInstance() throws Exception {
		return (AbstractEntityFormController<T>) getControllerClass().newInstance();
	}
	
	
	// =================================================================================================================
	// Tests
	// =================================================================================================================
	
	@Test
	public void testGetCreateForm() {
		String viewName = controller.getCreateForm(model);
		assertEquals(VN_CREATE_ENTITY_FORM, viewName);
	}
	
	@Test
	public void testPostCreateForm() {
		String viewName = controller.getCreateForm(model);
		assertEquals(VN_CREATE_ENTITY_FORM, viewName);
	}
	
	@Test
	public void testPostCreateFormWithErrors() {
		when(result.hasErrors()).thenReturn(true);
		String viewName = controller.getCreateForm(model);
		assertEquals(VN_CREATE_ENTITY_FORM, viewName);
	}
	
	@Test
	public void testGetEditForm() {
		String viewName = controller.getEditForm(1L, model);
		assertEquals(VN_EDIT_ENTITY_FORM, viewName);
	}
	
	@Test
	public void testPutEditEntityForm() {
		String viewName = controller.putEditForm(1L, getEntity(), result, model);
		assertEquals(VN_EDIT_ENTITY_SUCCESS, viewName);
	}
	
	/**
	 * Confirms that we handle validation errors properly.
	 */
	@Test
	public void testPutEditEntityFormWithErrors() {
		when(result.hasErrors()).thenReturn(true);
		String viewName = controller.putEditForm(1L, getEntity(), result, model);
		assertEquals(VN_EDIT_ENTITY_FORM, viewName);
	}
}
