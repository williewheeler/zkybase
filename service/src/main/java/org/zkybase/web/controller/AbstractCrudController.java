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
package org.zkybase.web.controller;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

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
import org.zkybase.model.CI;
import org.zkybase.model.ListWrapper;
import org.zkybase.service.CIService;
import org.zkybase.web.sitemap.Paths;
import org.zkybase.web.view.ViewNames;

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
	
	// IMPORTANT: Only getCiClass() should access this directly!
	Class<T> ciClass;
	
	@SuppressWarnings("unchecked")
	public AbstractCrudController() {
		ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
		this.ciClass = (Class<T>) paramType.getActualTypeArguments()[0];
	}
	
	protected abstract CIService<T> getService();
	
	protected T newCiInstance() {
		try {
			return ciClass.newInstance();
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
	protected abstract String[] getAllowedFields();
	
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
		model.addAttribute(MK_FORM_DATA, newCiInstance());
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
		
		return viewNames.postCreateFormSuccessViewName(ciClass);
	}
	
	/**
	 * @param model model
	 * @return view name
	 */
	protected String prepareCreateForm(Model model) {
		populateReferenceData(model);
		model.addAttribute(MK_FORM_METHOD, "post");
		model.addAttribute(MK_SUBMIT_PATH, paths.getSubmitCreateFormPath(ciClass));
		model.addAttribute(MK_CANCEL_PATH, paths.getBasePath(ciClass) + "?a=cancelled");
		return addNavigation(model, sitemap.getCreateFormId(ciClass));
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
		return addNavigation(model, sitemap.getEntityListViewId(ciClass));
	}
	
	/**
	 * @return list of CIs
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=json", produces = "application/json")
	@ResponseBody
	public List<T> getListAsJson() { return getSortedList(); }
	
	/**
	 * @return list of CIs
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=xml", produces = "application/xml")
	@ResponseBody
	public ListWrapper<T> getListAsXml() throws Exception {
		
		// IMPORTANT: We can't access ciClass directly (need to call getCiClass() to guarantee initialization)
		String wrapperClassName = ciClass.getName() + "$" + ciClass.getSimpleName() + "ListWrapper";
		Class<ListWrapper<T>> wrapperClass = (Class<ListWrapper<T>>) Class.forName(wrapperClassName);
		ListWrapper<T> wrapper = wrapperClass.newInstance();
		wrapper.setList(getSortedList());
		return wrapper;
	}
	
	private List<T> getSortedList() { return getService().findAll(); }
	
	
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
		T ci = doGetDetails(id, model);
		model.addAttribute(ci);
		model.addAttribute("entity", ci);
		return addNavigation(model, sitemap.getCiDetailsViewId(ciClass));
	}
	
	/**
	 * Subclasses can override this method as necessary.
	 * 
	 * @param id ID
	 * @return entity
	 * @deprecated Override the service's findOne() method instead
	 */
	@Deprecated
	protected T doGetDetails(Long id, Model model) { return getDetails(id); }
	
	/**
	 * @param id
	 * @param out
	 * @throws IOException
	 */
	// From the reference docs: "Furthermore, use of the produces condition ensures the actual content type used to
	// generate the response respects the media types specified in the produces condition."
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=json", produces = "application/json")
	@ResponseBody
	public T getDetailsAsJson(@PathVariable Long id) { return getDetails(id); }
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=xml", produces = "application/xml")
	@ResponseBody
	public T getDetailsAsXml(@PathVariable Long id) { return getDetails(id); }
	
	private T getDetails(Long id) { return getService().findOne(id); }
	
	
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
		
		return viewNames.putEditFormSuccessViewName(ciClass, id);
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
		CI<?> ci = getService().findOne(id);
		model.addAttribute(ci);
		model.addAttribute(MK_ENTITY, ci);
		
		model.addAttribute(MK_FORM_METHOD, "put");
		model.addAttribute(MK_SUBMIT_PATH, paths.getSubmitEditFormPath(ciClass, id));
		model.addAttribute(MK_CANCEL_PATH, paths.getDetailsPath(ciClass, id) + "?a=cancelled");
		
		String editFormId = sitemap.getEditFormId(ciClass);
		notNull(editFormId, "Null edit form ID for CI class " + ciClass);
		return addNavigation(model, editFormId);
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
		return viewNames.deleteSuccessViewName(ciClass);
	}
}
