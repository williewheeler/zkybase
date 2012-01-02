/* 
 * AbstractEntityNoFormController.java
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

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.model.ListWrapper;
import org.skydingo.skybase.util.CollectionsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityNoFormController<T extends Entity<T>> extends AbstractEntityController<T> {
	private static final Logger log = LoggerFactory.getLogger(AbstractEntityNoFormController.class);
	
	
	// =================================================================================================================
	// Read
	// =================================================================================================================
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getList(Model model) {
		model.addAttribute(getSortedList());
		return addNavigation(model, sitemap.getEntityListViewId(getEntityClass())); 
	}
	
	// This one doesn't work correctly. Not sure exactly what the problem is, but basically the object mapper doesn't
	// seem to be using the JAXB annotations to serialize the output here.
//	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=json")
//	@ResponseBody
//	public List<T> getListAsJson() {
//		log.debug("Getting list as JSON");
//		return getSortedList();
//	}
	
	// But in this case the object mapper *does* respect the JAXB annotations. Weird.
	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=json")
	public void getListAsJson(HttpServletResponse res) throws IOException {
		log.debug("Getting list as JSON");
		objectMapper.writeValue(res.getWriter(), getSortedList());
	}
	
	/**
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = RequestMethod.GET, params = "format=xml")
	@ResponseBody
	public ListWrapper<T> getListAsXml(Model model) throws Exception {
		log.debug("Getting list as XML");
		
		// IMPORTANT: We can't access entityClass directly (need to call getEntityClass() to guarantee initialization)
		Class<T> eClass = getEntityClass();
		String wrapperClassName = eClass.getName() + "$" + eClass.getSimpleName() + "ListWrapper";
		Class<ListWrapper<T>> wrapperClass = (Class<ListWrapper<T>>) Class.forName(wrapperClassName);
		ListWrapper<T> wrapper = wrapperClass.newInstance();
		wrapper.setList(getSortedList());
		
		// Let's use an HTTP message converter here instead of a view. Cleaner.
//		model.addAttribute(wrapper);
		return wrapper;
	}
	
	private List<T> getSortedList() {
		List<T> entities = CollectionsUtil.asList(getRepository().findAll());
		Collections.sort(entities);
		return entities;
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
	
	// This won't work, because we've annotated the entity classes with @XmlRootElement. The JAXB HTTP message converter
	// wins out over the Jackson HTTP message converter.
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=json")
//	@ResponseBody
//	public T getDetailsAsJson(@PathVariable Long id, Model model) {
//		log.debug("Getting details as JSON");
//		return doGetDetails(id, model);
//	}
	
	// So do this instead.
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=json")
	public void getDetailsAsJson(@PathVariable Long id, Model model, HttpServletResponse res) throws IOException {
		log.debug("Getting details as JSON");
		objectMapper.writeValue(res.getWriter(), doGetDetails(id, model));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=xml")
	@ResponseBody
	public T getDetailsAsXml(@PathVariable Long id, Model model) {
		log.debug("Getting details as XML");
		
		// Let's use an HTTP message converter here instead of a view. Cleaner.
//		model.addAttribute(doGetDetails(id, model));
		return doGetDetails(id, model);
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
