/* 
 * PersonScmControllerTests.java
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.web.sitemap.SitemapNode;
import org.skydingo.skybase.web.sitemap.Sitemap;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.social.github.api.UserOperations;
import org.springframework.ui.Model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PersonScmControllerTests {
	@InjectMocks private PersonScmController controller;
	
	@Mock private PersonRepository personRepository;
	@Mock private GitHub gitHub;
	@Mock private UserOperations userOperations;
	@Mock private Sitemap sitemap;
	
	@Mock private SitemapNode node;
	@Mock private Model model;
	@Mock private Person person;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = new PersonScmController();
		MockitoAnnotations.initMocks(this);
		when(personRepository.findOne(anyLong())).thenReturn(person);
		when(gitHub.userOperations()).thenReturn(userOperations);
		when(userOperations.getFollowers(anyString())).thenReturn(new ArrayList<GitHubUser>());
		when(userOperations.getFollowing(anyString())).thenReturn(new ArrayList<GitHubUser>());
		when(sitemap.getNode(anyString())).thenReturn(node);
	}
	
	/**
	 * Happy path test. Person has no GitHub user.
	 */
	@Test
	public void testGetFollowersNoGitHubUser() {
		String viewName = controller.getFollowers(1L, model);
		assertEquals("personScmFollowers", viewName);
		verify(model, times(1)).addAttribute(person);
		verify(model, times(1)).addAttribute("entity", person);
		verify(model, times(0)).addAttribute(eq("followerList"), anyObject());
		verify(model, times(0)).addAttribute(eq("followerRows"), anyObject());
	}
	
	/**
	 * Happy path test. Person has a GitHub user.
	 */
	@Test
	public void testGetFollowersHasGitHubUser() {
		when(person.getGitHubUser()).thenReturn("williewheeler");
		String viewName = controller.getFollowers(1L, model);
		assertEquals("personScmFollowers", viewName);
		verify(model, times(1)).addAttribute(person);
		verify(model, times(1)).addAttribute("entity", person);
		verify(model, times(1)).addAttribute(eq("followerList"), anyObject());
		verify(model, times(1)).addAttribute(eq("followerRows"), anyObject());
	}
	
	/**
	 * Happy path test. Person has no GitHub user.
	 */
	@Test
	public void testGetFollowingNoGitHubUser() {
		String viewName = controller.getFollowing(1L, model);
		assertEquals("personScmFollowing", viewName);
		verify(model, times(1)).addAttribute(person);
		verify(model, times(1)).addAttribute("entity", person);
	}
	
	/**
	 * Happy path test. Person has a GitHub user.
	 */
	@Test
	public void testGetFollowingHasGitHubUser() {
		when(person.getGitHubUser()).thenReturn("williewheeler");
		String viewName = controller.getFollowing(1L, model);
		assertEquals("personScmFollowing", viewName);
		verify(model, times(1)).addAttribute(person);
		verify(model, times(1)).addAttribute("entity", person);
		verify(model, times(1)).addAttribute(eq("followingList"), anyObject());
		verify(model, times(1)).addAttribute(eq("followingRows"), anyObject());
	}
}
