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
package org.skydingo.skybase.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.service.PersonService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.navigation.Sitemap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Inject private PersonService personService;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
			"username", "firstName", "lastName", "title", "workPhone", "mobilePhone", "email"
		});
	}
	
	/**
	 * Generates the add person form.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getCreatePersonForm(Model model) {
		model.addAttribute(new Person());
		return doGetCreatePersonForm(model);
	}
	
	/**
	 * @param person person
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createPerson(Model model, @ModelAttribute @Valid Person person, BindingResult result) {
		if (result.hasErrors()) {
			log.debug("Invalid person");
			return doGetCreatePersonForm(model);
		} else {
			log.debug("Valid person. Saving.");
			personRepo.save(person);
			return "redirect:/people?a=created";
		}
	}
	
	private String doGetCreatePersonForm(Model model) {
		return addNavigation(model, Sitemap.CREATE_PERSON_ID);
	}
	
	/**
	 * <p>
	 * Returns a sorted list of all people.
	 * </p>
	 * <p>
	 * Currently this is just a single list containing everybody. In the future this will be pageable.
	 * </p> 
	 * 
	 * @param model model model
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getPersonList(Model model) {
		List<Person> people = CollectionsUtil.asList(personRepo.findAll());
		Collections.sort(people);
		model.addAttribute(people);
		return addNavigation(model, Sitemap.PERSON_LIST_ID);
	}
	
	/**
	 * @param query search query
	 * @return search results
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody List<String> getPeopleSearchResults(@RequestParam("q") String query) {
		List<String> results = new ArrayList<String>();
		Iterable<Person> personIt = personRepo.findAll();
		for (Person person : personIt) {
			results.add(person.getFirstNameLastName() + " (" + person.getUsername() + ")");
		}
		return results;
	}
	
	/**
	 * @param id person ID
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getPersonDetails(@PathVariable Long id, Model model) {
		Person person = personRepo.findOne(id);
		List<ProjectMembership> memberships = CollectionsUtil.asList(person.getMemberships());
		List<Person> directReports = CollectionsUtil.asList(person.getDirectReports());
		List<Person> collaborators = CollectionsUtil.asList(personRepo.findCollaborators(person));
		
//		Collections.sort(memberships);
		Collections.sort(directReports);
		Collections.sort(collaborators);
		
		model.addAttribute(person);
		model.addAttribute("memberships", memberships);
		model.addAttribute("directReports", directReports);
		model.addAttribute("collaborators", collaborators);
		
		return addNavigation(model, Sitemap.PERSON_DETAILS_ID);
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditPersonForm(@PathVariable Long id, Model model) {
		Person person = personRepo.findOne(id);
		model.addAttribute(person);
		return doGetEditPersonForm(person, model);
	}
	
	/**
	 * @param id
	 * @param person
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String editPerson(
			@PathVariable Long id,
			@ModelAttribute @Valid Person person,
			BindingResult result,
			Model model) {
		
		person.setId(id);
		
		// FIXME Need to check for errors following the attempt to save.
		
		if (result.hasErrors()) {
			
			// FIXME This person isn't on the navigation context, so the breadcrumbs are going to have the wrong name.
			// Fix is to use "personData" for the form bean.
			Person pPerson = personRepo.findOne(id);
			
			return doGetEditPersonForm(pPerson, model);
		} else {
			personRepo.save(person);
			return "redirect:/people/" + id;
		}
	}
	
	/**
	 * @param model
	 * @return
	 */
	private String doGetEditPersonForm(Person person, Model model) {
		return addNavigation(model, Sitemap.EDIT_PERSON_ID);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
		return "redirect:/people?a=deleted";
	}
}
