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
package org.zkybase.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.zkybase.model.CI;
import org.zkybase.model.ListWrapper;
import org.zkybase.web.sitemap.Paths;
import org.zkybase.web.sitemap.Sitemap;
import org.zkybase.web.sitemap.SitemapNode;
import org.zkybase.web.view.ViewNames;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractCrudControllerTests<T extends CI<T>> {
	protected static final String VN_CREATE_ENTITY_FORM = "createEntityForm";
	protected static final String VN_DELETE_ENTITY_SUCCESS = "deleteEntitySuccess";
	protected static final String VN_EDIT_ENTITY_FORM = "editEntityForm";
	protected static final String VN_EDIT_ENTITY_SUCCESS = "editEntitySuccess";
	protected static final String VN_ENTITY_DETAILS = "entityDetails";
	protected static final String VN_ENTITY_LIST = "entityList";
	protected static final String VN_PUT_EDIT_ENTITY_FORM_SUCCESS = "putEditEntityFormSuccess";
	
	private static final String CONTROLLER_PACKAGE = "org.zkybase.web.controller";
	
	// Class under test
	@InjectMocks protected AbstractCrudController<T> controller;
	
	// Dependencies
	@Mock protected Paths paths;
	@Mock protected Sitemap sitemap;
	@Mock protected ViewNames viewNames;
	@Mock protected ObjectMapper objectMapper;
	
	// Test objects
	@Mock protected T entity;
	@Mock protected SitemapNode node;
	@Mock protected BindingResult result;
	@Mock protected Model model;
	@Mock protected Writer out;
	
	protected Class<T> getCiClass() {
		return controller.ciClass;
	}
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = newControllerInstance();
		MockitoAnnotations.initMocks(this);
		
		when(sitemap.getNode((String) any())).thenReturn(node);
		when(sitemap.getEntityListViewId(getCiClass())).thenReturn(VN_ENTITY_LIST);
		when(sitemap.getCiDetailsViewId(getCiClass())).thenReturn(VN_ENTITY_DETAILS);
		when(sitemap.getCreateFormId(getCiClass())).thenReturn(VN_CREATE_ENTITY_FORM);
		when(sitemap.getEditFormId(getCiClass())).thenReturn(VN_EDIT_ENTITY_FORM);
		when(viewNames.putEditFormSuccessViewName(getCiClass(), 1L)).thenReturn(VN_EDIT_ENTITY_SUCCESS);
		
		when(viewNames.putEditFormSuccessViewName(getCiClass(), 1L)).thenReturn(VN_PUT_EDIT_ENTITY_FORM_SUCCESS);
		when(viewNames.deleteSuccessViewName(getCiClass())).thenReturn(VN_DELETE_ENTITY_SUCCESS);
		when(viewNames.putEditFormSuccessViewName(getCiClass(), 1L)).thenReturn(VN_EDIT_ENTITY_SUCCESS);
		
		doSetUp();
	}
	
	@SuppressWarnings("unchecked")
	private Class<AbstractCrudController<T>> getControllerClass() {
		ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		String ecName = entityClass.getSimpleName();
		String pkgName = ecName.toLowerCase();
		
		// Packages are an exception since "package" is a reserved word.
		// FIXME Move this somewhere else if we want to be purist here. Not worried about it for now.
		if (org.zkybase.model.Package.class.equals(entityClass)) { pkgName = "pkg"; }
		
		String ccName = CONTROLLER_PACKAGE + "." + pkgName + "." + ecName + "CrudController";
		try {
			return (Class<AbstractCrudController<T>>) Class.forName(ccName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private AbstractCrudController<T> newControllerInstance() {
		try {
			return (AbstractCrudController<T>) getControllerClass().newInstance();
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
	
	
	// =================================================================================================================
	// Create list
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
	
	
	// =================================================================================================================
	// Read list
	// =================================================================================================================
	
	@Test
	public void testGetEntityList() {
		String viewName = controller.getList(model);
		assertEquals(VN_ENTITY_LIST, viewName);
		
		// Once for navigation, once for farm list
		verify(model, times(2)).addAttribute(any());
	}
	
	@Test
	public void testGetEntityListAsJson() {
		List<T> entities = controller.getListAsJson();
		assertNotNull(entities);
	}
	
	@Test
	public void testGetEntityListAsXml() throws Exception {
		ListWrapper<T> wrapper = controller.getListAsXml();
		assertNotNull(wrapper);
		
		List<T> list = wrapper.getList();
		assertNotNull(list);
	}
	
	
	// =================================================================================================================
	// Read details
	// =================================================================================================================
	
	@Test
	public void testGetEntityDetails() {
		String viewName = controller.getDetails(1L, model);
		assertEquals(VN_ENTITY_DETAILS, viewName);
	}
	
//	@Test
//	public void testGetDetailsAsJson() throws IOException {
//		controller.getDetailsAsJson(1L, out);
//		verify(objectMapper, times(1)).writeValue(eq(out), anyObject());
//	}
	
	@Test
	public void testGetDetailsAsXml() {
		T entity = controller.getDetailsAsXml(1L);
		assertNotNull(entity);
	}
	
	
	// =================================================================================================================
	// Update
	// =================================================================================================================
	
	@Test
	public void testGetEditForm() {
		String viewName = controller.getEditForm(1L, model);
		assertEquals(VN_EDIT_ENTITY_FORM, viewName);
	}
	
	@Test
	public void testPutEditEntityForm() {
		String viewName = controller.putEditForm(1L, entity, result, model);
		assertEquals(VN_EDIT_ENTITY_SUCCESS, viewName);
	}
	
	/**
	 * Confirms that we handle validation errors properly.
	 */
	@Test
	public void testPutEditEntityFormWithErrors() {
		when(result.hasErrors()).thenReturn(true);
		String viewName = controller.putEditForm(1L, entity, result, model);
		assertEquals(VN_EDIT_ENTITY_FORM, viewName);
	}
	
	
	// =================================================================================================================
	// Delete
	// =================================================================================================================
	
	@Test
	public void testDeleteEntity() {
		String viewName = controller.delete(1L);
		assertEquals(VN_DELETE_ENTITY_SUCCESS, viewName);
	}
}
