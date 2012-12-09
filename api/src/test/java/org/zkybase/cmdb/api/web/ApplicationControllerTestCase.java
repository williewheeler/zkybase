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
package org.zkybase.cmdb.api.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.transaction.support.TransactionTemplate;
import org.zkybase.cmdb.api.domain.ApplicationEntity;
import org.zkybase.cmdb.api.mapper.ApplicationMapper;
import org.zkybase.cmdb.api.repository.ApplicationRepository;
import org.zkybase.cmdb.api.util.UriResolver;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ApplicationControllerTestCase {
	private static final Long APPLICATION_ID = 4000L;
	private static final String APPLICATION_NAME = "Zkybase CMDB";
	private static final String APPLICATION_LOCATION = "someLocation";
	
	@InjectMocks private ApplicationController controller;
	
	@Mock private ApplicationRepository applicationRepo;
	@Mock private ApplicationMapper applicationMapper;
	@Mock private UriResolver resolver;
	@Mock private TransactionTemplate txTemplate;
	
	@Mock private ClosableIterable<ApplicationEntity> applicationEntitiesIterable;
	@Mock private List<ApplicationEntity> applicationEntities;
	@Mock private ApplicationEntity applicationEntity;
	@Mock private ArrayList<Application> applicationDtos;
	@Mock private Application applicationDto;
	@Mock private Application applicationDtoForPost;
	@Mock private HttpServletResponse response;
	
	@Before
	public void setUp() throws Exception {
		this.controller = new ApplicationController();
		MockitoAnnotations.initMocks(this);
		setUpTestObjects();
		setUpDependencies();
	}
	
	private void setUpTestObjects() {
		when(applicationEntity.getId()).thenReturn(APPLICATION_ID);
		when(applicationEntity.getName()).thenReturn(APPLICATION_NAME);
		
		when(applicationDto.getId()).thenReturn(APPLICATION_ID);
		when(applicationDto.getName()).thenReturn(APPLICATION_NAME);
		
		when(applicationDtoForPost.getId()).thenReturn(null);
		when(applicationDtoForPost.getName()).thenReturn(APPLICATION_NAME);
	}
	
	private void setUpDependencies() {
		when(applicationRepo.findAll()).thenReturn(applicationEntitiesIterable);
		when(applicationRepo.findOne(APPLICATION_ID)).thenReturn(applicationEntity);
		
		when(applicationMapper.toEntity(applicationDtoForPost)).thenReturn(applicationEntity);
		when(applicationMapper.toDtos(applicationEntities)).thenReturn(applicationDtos);
		when(applicationMapper.toDto(applicationEntity)).thenReturn(applicationDto);
		
		when(resolver.resolve(ApplicationEntity.class, APPLICATION_ID)).thenReturn(APPLICATION_LOCATION);
	}
	
	@Test
	public void post() {
		controller.post(applicationDtoForPost, response);
//		verify(applicationRepo).save((ApplicationEntity) anyObject());
//		verify(response).addHeader("Location", APPLICATION_LOCATION);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void postWithApplicationIdNotNull() {
		assertNotNull(applicationDto.getId());
		controller.post(applicationDto, response);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void postWithNullApplication() {
		controller.post(null, response);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void postWithNullResponse() {
		controller.post(applicationDto, null);
	}
	
	@Test
	@Ignore
	public void getAll() {
		List<Application> actualApps = controller.getAll();
		assertNotNull(actualApps);
		verify(applicationRepo).findAll();
		verify(applicationMapper).toDtos(applicationEntities);
	}
	
	@Test
	public void get() {
		Application actualApp = controller.get(APPLICATION_ID);
		assertNotNull(actualApp);
		assertEquals(APPLICATION_ID, actualApp.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getWithNullId() {
		controller.get(null);
	}
	
	@Test
	public void put() {
		final String newAppName = "Wrath of Zkybase";
		when(applicationDto.getName()).thenReturn(newAppName);
		controller.put(APPLICATION_ID, applicationDto, response);
//		verify(applicationEntity).setName(newAppName);
//		verify(applicationRepo).save(applicationEntity);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putWithNullId() {
		controller.put(null, applicationDto, response);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putWithNullApplication() {
		controller.put(APPLICATION_ID, null, response);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putWithNullResponse() {
		controller.put(APPLICATION_ID, applicationDto, null);
	}
	
	@Test
	public void delete() {
		controller.delete(APPLICATION_ID, response);
		verify(applicationRepo).delete(APPLICATION_ID);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteWithNullId() {
		controller.delete(null, response);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteWithNullResponse() {
		controller.delete(APPLICATION_ID, null);
	}
}
