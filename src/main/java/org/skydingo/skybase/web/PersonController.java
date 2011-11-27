/* 
 * ProjectController.java
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
package org.skydingo.skybase.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.navigation.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/people")
public class PersonController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	
	@Inject private PersonRepository personRepo;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "username", "firstName", "lastName", "email" });
	}
	
	/**
	 * Generates the add person form.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public String getAddPersonForm(Model model) {
		model.addAttribute(new Person());
		return doGetAddPersonForm(model);
	}
	
	protected String doGetAddPersonForm(Model model) {
		addBreadcrumbs(model, new Breadcrumb("People", "/people"));
		return "person/personForm";
	}
	
	/**
	 * @param person person
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createPerson(Model model, @ModelAttribute @Valid Person person, BindingResult result) {
		if (result.hasErrors()) {
			log.debug("Invalid person");
			return doGetAddPersonForm(model);
		} else {
			log.debug("Valid person. Saving.");
			personRepo.save(person);
			return "redirect:/people?created=true";
		}
	}
	
	/**
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getPeople(Model model) {
		addBreadcrumbs(model);
		model.addAttribute(CollectionsUtil.toList(personRepo.findAll()));
		return "person/personList";
	}
	
	/**
	 * @param username username
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String getPerson(@PathVariable String username, Model model) {
		Person person = personRepo.findPersonByUsername(username);
		addBreadcrumbs(model, new Breadcrumb("People", "/people"));
		model.addAttribute(person);
		return "person/personDetails";
	}
}
