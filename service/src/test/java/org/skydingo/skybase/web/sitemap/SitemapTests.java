/* 
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
package org.skydingo.skybase.web.sitemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.web.sitemap.SitemapNode;
import org.skydingo.skybase.web.sitemap.Paths;
import org.skydingo.skybase.web.sitemap.Sitemap;
import org.springframework.context.MessageSource;
import org.springframework.expression.ExpressionParser;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class SitemapTests {
	@InjectMocks private Sitemap sitemap;
	@Mock private MessageSource messageSource;
	@Mock private Paths paths;
	@Mock private ExpressionParser expressionParser;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.sitemap = new Sitemap();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPostConstruct() {
		sitemap.postConstruct();
		
		// Get a node
		SitemapNode appModulesNode = sitemap.getNode("applicationModuleList");
		assertNotNull(appModulesNode);
		assertEquals("applicationModuleList", appModulesNode.getId());
		assertEquals("'Modules'", appModulesNode.getName());
		
		// Resolve an expression
		// TODO
	}
}
