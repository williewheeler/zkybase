/* 
 * PersonControllerTests.java
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.service.PersonService;
import org.skydingo.skybase.web.navigation.Node;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PersonControllerTests {
	@InjectMocks private PersonController controller;
	
	// Dependencies
	@Mock private Sitemap sitemap;
	@Mock private PersonService personService;
	@Mock private PersonRepository personRepo;
	
	// Test objects
	@Mock private Node node;
	@Mock private BindingResult result;
	@Mock private Model model;
	@Mock private Person pkg;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = new PersonController();
		MockitoAnnotations.initMocks(this);
		when(sitemap.getNode((String) any())).thenReturn(node);
		when(personService.findPeople()).thenReturn(new ArrayList<Person>());
		when(personService.findPerson((Long) any())).thenReturn(pkg);
	}
	
	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Happy path test to get the person list.
	 */
	@Test
	public void testGetPersonList() {
		String viewName = controller.getPersonList(model);
		assertEquals("personList", viewName);
		
		// Once for navigation, once for farm list
		verify(model, times(2)).addAttribute(any());
	}
	
	/**
	 * Happy path test to get the person details page.
	 */
	@Test
	public void testGetPersonDetails() {
		String viewName = controller.getPerson(1L, model);
		assertEquals("personDetails", viewName);
	}
	
	/**
	 * Happy path test to get the edit person form.
	 */
	@Test
	public void testGetEditPersonForm() {
		String viewName = controller.getEditPersonForm(1L, model);
		assertEquals("editPersonForm", viewName);
	}
	
	/**
	 * Happy path test to submit the edit person form.
	 */
	@Test
	public void testPutEditPersonForm() {
		String viewName = controller.putEditPersonForm(1L, pkg, result, model);
		assertEquals("redirect:/people/" + 1L + "?a=updated", viewName);
	}
	
	/**
	 * Confirms that we handle validation errors properly.
	 */
	@Test
	public void testPutEditPersonFormWithErrors() {
		when(result.hasErrors()).thenReturn(true);
		String viewName = controller.putEditPersonForm(1L, pkg, result, model);
		assertEquals("editPersonForm", viewName);
	}
	
	/**
	 * Happy path test to delete a person.
	 */
	@Test
	public void testDeletePerson() {
		String viewName = controller.deletePerson(1L);
		assertEquals("redirect:/people?a=deleted", viewName);
	}
}