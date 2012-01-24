/* 
 * PersonNoFormControllerTests.java
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
package org.skydingo.skybase.web.controller.person;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.mockito.Mock;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.service.PersonService;
import org.skydingo.skybase.web.controller.AbstractEntityNoFormControllerTests;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.social.github.api.UserOperations;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PersonNoFormControllerTests extends AbstractEntityNoFormControllerTests<Person> {
	
	// Dependencies
	@Mock private PersonRepository personRepo;
	@Mock private PersonService personService;
	@Mock private GitHub gitHub;
	@Mock private UserOperations userOperations;
	
	// Test objects
	@Mock private Person person;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityNoFormControllerTests#setUp()
	 */
	@Override
	protected void doSetUp() throws Exception {
		when(personService.findPersonDetails((Long) any())).thenReturn(person);
		when(gitHub.userOperations()).thenReturn(userOperations);
		when(userOperations.getFollowers(anyString())).thenReturn(new ArrayList<GitHubUser>());
		when(userOperations.getFollowing(anyString())).thenReturn(new ArrayList<GitHubUser>());
	}
}
