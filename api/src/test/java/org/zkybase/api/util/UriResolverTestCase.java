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
package org.zkybase.api.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zkybase.api.domain.node.ApplicationNode;
import org.zkybase.api.util.UriResolver;


/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class UriResolverTestCase {
	private static final String BASE_URI = "https://api.zkybase.org/v1";
	private static final Long APPLICATION_ID = 299L;
	
	private UriResolver resolver;
	
	@Mock private ApplicationNode application;
	
	@Before
	public void setUp() throws Exception {
		this.resolver = new UriResolver();
		MockitoAnnotations.initMocks(this);
		setUpTestObjects();
	}
	
	private void setUpTestObjects() {
		when(application.getId()).thenReturn(APPLICATION_ID);
	}
	
	@Test
	public void resolveUri() {
		String uri = resolver.resolve(application);
		assertEquals(BASE_URI + "/applications/" + application.getId(), uri);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void resolveUriWithNullCi() {
		resolver.resolve(null);
	}
	
	@Test
	public void resolveUriByCiClassAndId() {
		String uri = resolver.resolve(ApplicationNode.class, APPLICATION_ID);
		assertEquals(BASE_URI + "/applications/" + APPLICATION_ID, uri);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void resolveUriByCiClassAndIdWithNullClass() {
		resolver.resolve(null, APPLICATION_ID);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void resolveUriByCiClassAndIdWithNullId() {
		resolver.resolve(ApplicationNode.class, null);
	}
}
