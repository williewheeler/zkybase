/* 
 * PersonNoFormController.java
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
package org.zkybase.web.controller.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkybase.model.Person;
import org.zkybase.service.CIService;
import org.zkybase.service.PersonService;
import org.zkybase.util.CollectionsUtil;
import org.zkybase.web.controller.AbstractCrudController;
import org.zkybase.web.view.ViewUtil;

/**
 * Project controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/people")
public class PersonCrudController extends AbstractCrudController<Person> {
	private static final String[] ALLOWED_FIELDS = new String[] {
		"username", "firstName", "lastName", "title", "workPhone", "mobilePhone", "email", "gitHubUser"
	};
	
	private static final Logger log = LoggerFactory.getLogger(PersonCrudController.class);
	
	@Inject private PersonService personService;
	
	// FIXME Will move to service
	private GitHub gitHub = new GitHubTemplate();
	
	/* (non-Javadoc)
	 * @see org.zkybase.web.controller.AbstractCrudController#getService()
	 */
	@Override
	public CIService<Person> getService() { return personService; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.web.controller.AbstractCrudController#getAllowedFields()
	 */
	@Override
	protected String[] getAllowedFields() { return ALLOWED_FIELDS; }

	
	// =================================================================================================================
	// Read
	// =================================================================================================================
	
	/* (non-Javadoc)
	 * @see org.zkybase.web.controller.AbstractEntityController#doGetDetails
	 * (java.lang.Long, org.springframework.ui.Model)
	 */
	@Override
	protected Person doGetDetails(Long id, Model model) {
		log.debug("Getting extended person details");
		Person person = personService.findOne(id);
		List<Person> directReports = CollectionsUtil.asList(person.getDirectReports());
		Collections.sort(directReports);
		model.addAttribute("directReports", directReports);
		
		// FIXME Hardcode, plus this is generating test errors
		List<GitHubUser> followers = gitHub.userOperations().getFollowers("williewheeler");
		List<List<GitHubUser>> followerRows = ViewUtil.toRows(followers, 3);
		model.addAttribute("followerList", followers);
		model.addAttribute("followerRows", followerRows);
		
		// FIXME Hardcode, plus this is generating test errors
		List<GitHubUser> following = gitHub.userOperations().getFollowing("williewheeler");
		List<List<GitHubUser>> followingRows = ViewUtil.toRows(following, 3);
		model.addAttribute("followingList", following);
		model.addAttribute("followingRows", followingRows);
		
		return person;
	}
	
	/**
	 * @param query search query
	 * @return search results
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody List<String> getPeopleSearchResults(@RequestParam("q") String query) {
		List<String> results = new ArrayList<String>();
		Iterable<Person> personIt = personService.findAll();
		for (Person person : personIt) {
			results.add(person.getFirstNameLastName() + " (" + person.getUsername() + ")");
		}
		return results;
	}
}
