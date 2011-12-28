/* 
 * FarmControllerTests.java
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.repository.FarmRepository;
import org.skydingo.skybase.web.navigation.Node;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.skydingo.skybase.web.view.ViewNames;
import org.springframework.ui.Model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class FarmControllerTests {
	@InjectMocks private FarmController controller;
	
	// Dependencies
	@Mock private Paths paths;
	@Mock private Sitemap sitemap;
	@Mock private ViewNames viewNames;
	@Mock private FarmRepository farmRepo;
	
	// Test objects
	@Mock private Node node;
	@Mock private Model model;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = new FarmController();
		MockitoAnnotations.initMocks(this);
		when(sitemap.getNode((String) any())).thenReturn(node);
		when(sitemap.getEntityListViewId(Farm.class)).thenReturn("farmList");
	}
	
	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetFarmList() {
		String viewName = controller.getList(model);
		assertEquals("farmList", viewName);
		
		// Once for navigation, once for farm list
		verify(model, times(2)).addAttribute(any());
	}
}
