/*
 * AbstractEntityFormController.java
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

import javax.validation.Valid;

import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Abstract base class for implementing controllers that handle form data (i.e., create and edit handler methods).
 *
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityFormController<T extends Entity<T>> extends AbstractEntityController<T> {
	public static final String MK_FORM_DATA = "formData";
	public static final String MK_ENTITY = "entity";
	public static final String MK_HAS_ERRORS = "hasErrors";
	public static final String MK_FORM_METHOD = "formMethod";
	public static final String MK_SUBMIT_PATH = "submitPath";
	public static final String MK_CANCEL_PATH = "cancelPath";

	private static final Logger log = LoggerFactory.getLogger(AbstractEntityFormController.class);

	public abstract GraphRepository<T> getRepository();

	public abstract EntityService<T> getService();

	/**
	 * @param binder binder
	 */
	@InitBinder(MK_FORM_DATA)
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.setAllowedFields(getAllowedFields());
	}

	protected String[] getAllowedFields() { return null; }


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
		log.debug("Posting form data");
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
		model.addAttribute(MK_FORM_METHOD, "post");
		model.addAttribute(MK_SUBMIT_PATH, paths.getSubmitCreateFormPath(getEntityClass()));
		model.addAttribute(MK_CANCEL_PATH, paths.getBasePath(getEntityClass()) + "?a=cancelled");
		return addNavigation(model, sitemap.getCreateFormId(getEntityClass()));
	}


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
		log.debug("Getting edit form");
		model.addAttribute(MK_FORM_DATA, getRepository().findOne(id));
		String viewName = prepareEditForm(id, model);
		log.debug("Returning edit form");
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
		log.debug("Preparing edit form");

		// Need to load the entity itself since the entity drives title and breadcrumbs, and we don't want form data
		// changes to impact them. Can optimize this by giving the entities clone constructors.
		model.addAttribute(MK_ENTITY, getRepository().findOne(id));

		model.addAttribute(MK_FORM_METHOD, "put");
		model.addAttribute(MK_SUBMIT_PATH, paths.getSubmitEditFormPath(getEntityClass(), id));
		model.addAttribute(MK_CANCEL_PATH, paths.getDetailsPath(getEntityClass(), id) + "?a=cancelled");

		return addNavigation(model, sitemap.getEditFormId(getEntityClass()));
	}
}
