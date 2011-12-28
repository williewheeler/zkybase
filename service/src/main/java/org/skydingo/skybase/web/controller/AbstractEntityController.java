/* 
 * AbstractController.java
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

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.navigation.Navigation;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.skydingo.skybase.web.view.ViewNames;
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
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityController<T extends Entity<T>> {
	public static final String MK_FORM_DATA = "formData";
	
	@Inject protected Paths paths;
	@Inject protected Sitemap sitemap;
	@Inject protected ViewNames viewNames;
	
	private Class<T> entityClass;
	
	
	// =================================================================================================================
	// General
	// =================================================================================================================
	
	/**
	 * @return
	 */
	public abstract GraphRepository<T> getRepository();
	
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
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		doInitBinder(binder);
	}
	
	protected abstract void doInitBinder(WebDataBinder binder);
	
	
	// =================================================================================================================
	// Navigation
	// =================================================================================================================
	
	protected String addNavigation(Model model, String currentNodeId) {
		model.addAttribute(new Navigation(sitemap, currentNodeId, model.asMap()));
		return currentNodeId;
	}
	
	
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
		if (result.hasErrors()) {
			return prepareCreateForm(model);
		} else {
			// FIXME Need to handle cases where save attempts generate validation errors (e.g. duplicate entities).
			getRepository().save(formData);
			return viewNames.postCreateFormSuccessViewName(getEntityClass());
		}
	}
	
	/**
	 * @param model model
	 * @return view name
	 */
	private String prepareCreateForm(Model model) {
		model.addAttribute("formMethod", "post");
		model.addAttribute("submitPath", paths.getSubmitCreateFormPath(getEntityClass()));
		model.addAttribute("cancelPath", paths.getBasePath(getEntityClass()) + "?a=cancelled");
		return addNavigation(model, sitemap.getCreateFormId(getEntityClass()));
	}
	
	
	// =================================================================================================================
	// Read
	// =================================================================================================================
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getList(Model model) {
		List<T> entities = CollectionsUtil.asList(getRepository().findAll());
		Collections.sort(entities);
		model.addAttribute(entities);
		return addNavigation(model, sitemap.getEntityListViewId(getEntityClass())); 
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getDetails(@PathVariable Long id, Model model) {
		T entity = doGetDetails(id, model);
		
		// For generic capabilities (e.g. navigation)
		model.addAttribute("entity", entity);
		
		// For custom JSP clarity
		model.addAttribute(entity);
		
		return addNavigation(model, sitemap.getEntityDetailsViewId(getEntityClass()));
	}
	
	/**
	 * Subclasses can override this method as necessary.
	 * 
	 * @param id ID
	 * @return entity
	 */
	protected T doGetDetails(Long id, Model model) {
		return getRepository().findOne(id);
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
		model.addAttribute(MK_FORM_DATA, getRepository().findOne(id));
		return prepareEditForm(id, model);
	}
	
	/**
	 * @param id
	 * @param pkg
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putEditForm(
			@PathVariable Long id,
			@ModelAttribute(MK_FORM_DATA) @Valid T formData,
			BindingResult result,
			Model model) {
		
		// FIXME Need to check for errors *after* the update attempt.
		if (result.hasErrors()) {
			return prepareEditForm(id, model);
		} else {
			// FIXME
			formData.setId(id);
			getRepository().save(formData);
			return viewNames.putEditFormSuccessViewName(getEntityClass(), id);
		}
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	private String prepareEditForm(Long id, Model model) {
		
		// Need to load the entity itself since the entity drives title and breadcrumbs, and we don't want form data
		// changes to impact them. Can optimize this by giving the entities clone constructors.
		model.addAttribute("entity", getRepository().findOne(id));
		
		model.addAttribute("formMethod", "put");
		model.addAttribute("submitPath", paths.getSubmitEditFormPath(getEntityClass(), id));
		model.addAttribute("cancelPath", paths.getDetailsPath(getEntityClass(), id) + "?a=cancelled");
		
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
		getRepository().delete(id);
		return viewNames.deleteSuccessViewName(getEntityClass());
	}
}
