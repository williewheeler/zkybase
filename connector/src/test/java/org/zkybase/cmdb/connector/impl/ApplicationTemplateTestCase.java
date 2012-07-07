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
package org.zkybase.cmdb.connector.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ApplicationTemplateTestCase {
	private static final String BASE_URI = "https://api.zkybase.org/v1";
	private static final String SOME_LOCATION = "someLocation";
	private static final Long APPLICATION_ID = 399L;
	private static final int NUM_APPS = 4;
	
	private ApplicationTemplate applicationTemplate;
	
	@Mock private RestTemplate restTemplate;
	
	@Mock private Application application;
	@Mock private ResponseEntity<String> responseEntity;
	@Mock private HttpHeaders httpHeaders;
	
	private Application[] applications;
	private List<String> locations;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.applicationTemplate = new ApplicationTemplate(restTemplate, BASE_URI);
		setUpTestObjects();
		setUpDependencies();
	}
	
	private void setUpTestObjects() {
		this.applications = new Application[NUM_APPS];
		Arrays.fill(applications, application);
		
		this.locations = asList(SOME_LOCATION);
	}
	
	private void setUpDependencies() {
		when(restTemplate.postForEntity(anyString(), anyObject(), eq(String.class))).thenReturn(responseEntity);
		when(responseEntity.getHeaders()).thenReturn(httpHeaders);
		when(httpHeaders.get("Location")).thenReturn(locations);
		when(restTemplate.getForObject(anyString(), eq(Application[].class))).thenReturn(applications);
	}
	
	@Test
	public void getRestTemplate() {
		assertSame(restTemplate, applicationTemplate.getRestTemplate());
	}
	
	@Test
	public void getBaseUri() {
		assertSame(BASE_URI, applicationTemplate.getBaseUri());
	}
	
	@Test
	public void create() {
		String location = applicationTemplate.create(application);
		verify(restTemplate).postForEntity(anyString(), anyObject(), eq(String.class));
		assertNotNull(location);
		assertEquals(SOME_LOCATION, location);
	}
	
	@Test
	public void list() {
		List<Application> actualApplications = applicationTemplate.list();
		assertNotNull(actualApplications);
		assertEquals(NUM_APPS, actualApplications.size());
	}
	
	@Test
	public void delete() {
		applicationTemplate.delete(APPLICATION_ID);
		verify(restTemplate).delete(anyString(), eq(APPLICATION_ID));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteWithNullId() {
		applicationTemplate.delete(null);
	}
}
