/* 
 * AbstractCrudController.java
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

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.skydingo.skybase.model.CI;
import org.skydingo.skybase.model.ListWrapper;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.view.ViewNames;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractCrudController<T extends CI<T>> extends AbstractController {
	public static final String MK_FORM_DATA = "formData";
	public static final String MK_ENTITY = "entity";
	public static final String MK_HAS_ERRORS = "hasErrors";
	public static final String MK_FORM_METHOD = "formMethod";
	public static final String MK_SUBMIT_PATH = "submitPath";
	public static final String MK_CANCEL_PATH = "cancelPath";
	
	@Inject protected Paths paths;
	@Inject protected ViewNames viewNames;
	@Inject protected ObjectMapper objectMapper;
	
	// IMPORTANT: Only getEntityClass() should access this directly!
	private Class<T> entityClass;
	
	// FIXME Make this abstract once the refactoring is done
	protected EntityService<T> getService() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (this.entityClass == null) {
			ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
			this.entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	protected T newEntityInstance() {
		try {
			return getEntityClass().newInstance();
		} catch (InstantiationException e) {
			// Shouldn't happen
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// Shouldn't happen
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param binder binder
	 */
	@InitBinder(MK_FORM_DATA)
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.setAllowedFields(getAllowedFields());
	}
	
	// FIXME Make abstract after refactoring is done
	protected String[] getAllowedFields() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Places reference data on the model. The default implementation is a no-op, but subclasses can override this as
	 * necessary.
	 * 
	 * @param model model
	 */
	protected void populateReferenceData(Model model) { }
	
	
	// =================================================================================================================
	// Create
	// =================================================================================================================
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(MK_FORM_DATA, newEntityInstance());
		return prepareCreateForm(model);
	}
	
	/**
	 * @param model
	 * @param formData
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postCreateForm(Model model, @ModelAttribute(MK_FORM_DATA) @Valid T formData, BindingResult result) {
		getService().create(formData, result);
		
		if (result.hasErrors()) {
			model.addAttribute(MK_HAS_ERRORS, true);
			return prepareCreateForm(model);
		}
		
		return viewNames.postCreateFormSuccessViewName(getEntityClass());
	}
	
	/**
	 * @param model model
	 * @return view name
	 */
	protected String prepareCreateForm(Model model) {
		populateReferenceData(model);
		model.addAttribute(MK_FORM_METHOD, "post");
		model.addAttribute(MK_SUBMIT_PATH, paths.getSubmitCreateFormPath(getEntityClass()));
		model.addAttribute(MK_CANCEL_PATH, paths.getBasePath(getEntityClass()) + "?a=cancelled");
		return addNavigation(model, sitemap.getCreateFormId(getEntityClass()));
	}
	
	
	// =================================================================================================================
	// Read list
	// =================================================================================================================
	
	/**
	 * Places a sorted list of the controller's entities on the model.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getList(Model model) {
		model.addAttribute(getSortedList());
		return addNavigation(model, sitemap.getEntityListViewId(getEntityClass())); 
	}
	
	/**
	 * @return list of entities
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=json")
	@ResponseBody
	public List<T> getListAsJson() {
		// This method requires the JAXB-aware object mapper! Make sure it's visible on this app context.
		return getSortedList();
	}
	
	/**
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=xml")
	@ResponseBody
	public ListWrapper<T> getListAsXml() throws Exception {
		
		// IMPORTANT: We can't access entityClass directly (need to call getEntityClass() to guarantee initialization)
		Class<T> ec = getEntityClass();
		String wrapperClassName = ec.getName() + "$" + ec.getSimpleName() + "ListWrapper";
		Class<ListWrapper<T>> wrapperClass = (Class<ListWrapper<T>>) Class.forName(wrapperClassName);
		ListWrapper<T> wrapper = wrapperClass.newInstance();
		wrapper.setList(getSortedList());
		return wrapper;
	}
	
	// FIXME Make this private once the refactoring is done
	protected List<T> getSortedList() { return getService().findAll(); }
	
	
	// =================================================================================================================
	// Read details
	// =================================================================================================================
	
	/**
	 * Gets the requested details entity, placing it on the  model under two separate keys: as "entity" to support
	 * generic capabilities like navigation, and as the autogenerated key for JSP clarity.
	 * 
	 * @param id entity ID
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getDetails(@PathVariable Long id, Model model) {
		T entity = doGetDetails(id, model);
		model.addAttribute("entity", entity);
		model.addAttribute(entity);
		return addNavigation(model, sitemap.getEntityDetailsViewId(getEntityClass()));
	}
	
	/**
	 * Subclasses can override this method as necessary.
	 * 
	 * @param id ID
	 * @return entity
	 * @deprecated Override the service's findOne() method instead
	 */
	@Deprecated
	protected T doGetDetails(Long id, Model model) { return getService().findOne(id); }
	
	/**
	 * @param id
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=json")
	public void getDetailsAsJson(@PathVariable Long id, Writer out) throws IOException {
		// We have to do this instead of using @ResponseBody, because the JAXB HTTP message converter apparently beats
		// the Jackson HTTP message converter when it comes to matching the @XmlRootElement annotation.
		objectMapper.writeValue(out, getService().findOne(id));
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=xml")
	@ResponseBody
	public T getDetailsAsXml(@PathVariable Long id) { return getService().findOne(id); }
	
	
	// =================================================================================================================
	// Update
	// =================================================================================================================
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditForm(@PathVariable Long id, Model model) {
		model.addAttribute(MK_FORM_DATA, getService().findOne(id));
		String viewName = prepareEditForm(id, model);
		return viewName;
	}
	
	/**
	 * @param id entity ID
	 * @param formData entity form data
	 * @param result result object
	 * @param model model
	 * @return view name
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putEditForm(
			@PathVariable Long id,
			@ModelAttribute(MK_FORM_DATA) @Valid T formData,
			BindingResult result,
			Model model) {
		
		formData.setId(id);
		getService().update(formData, result);
		
		if (result.hasErrors()) {
			model.addAttribute(MK_HAS_ERRORS, true);
			return prepareEditForm(id, model);
		}
		
		return viewNames.putEditFormSuccessViewName(getEntityClass(), id);
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	protected String prepareEditForm(Long id, Model model) {
		populateReferenceData(model);
		
		// Need to load the entity itself since the entity drives title and breadcrumbs, and we don't want form data
		// changes to impact them. Can optimize this by giving the entities clone constructors.
		model.addAttribute(MK_ENTITY, getService().findOne(id));
		
		model.addAttribute(MK_FORM_METHOD, "put");
		model.addAttribute(MK_SUBMIT_PATH, paths.getSubmitEditFormPath(getEntityClass(), id));
		model.addAttribute(MK_CANCEL_PATH, paths.getDetailsPath(getEntityClass(), id) + "?a=cancelled");
		
		return addNavigation(model, sitemap.getEditFormId(getEntityClass()));
	}
	
	
	// =================================================================================================================
	// Delete
	// =================================================================================================================
	
	/**
	 * @param id entity ID
	 * @return view name
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		notNull(id);
		getService().delete(id);
		return viewNames.deleteSuccessViewName(getEntityClass());
	}
}
