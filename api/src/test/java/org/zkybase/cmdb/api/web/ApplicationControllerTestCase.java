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
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.neo4j.helpers.collection.ClosableIterable;
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
	private static final String APPLICATION_LOCATION = "someLocation";
	
	@InjectMocks private ApplicationController controller;
	
	@Mock private ApplicationRepository applicationRepo;
	@Mock private ApplicationMapper applicationMapper;
	@Mock private UriResolver resolver;
	
	@Mock private ClosableIterable<ApplicationEntity> applicationEntities;
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
		
		when(applicationDto.getId()).thenReturn(APPLICATION_ID);
		
		when(applicationDtoForPost.getId()).thenReturn(null);
	}
	
	private void setUpDependencies() {
		when(applicationRepo.findAll()).thenReturn(applicationEntities);
		when(applicationRepo.findOne(APPLICATION_ID)).thenReturn(applicationEntity);
		
		when(applicationMapper.toEntity(applicationDtoForPost)).thenReturn(applicationEntity);
		when(applicationMapper.toDtoList(applicationEntities)).thenReturn(applicationDtos);
		when(applicationMapper.toDto(applicationEntity)).thenReturn(applicationDto);
		
		when(resolver.resolve(ApplicationEntity.class, APPLICATION_ID)).thenReturn(APPLICATION_LOCATION);
	}
	
	@Test
	public void post() {
		controller.post(applicationDtoForPost, response);
		verify(applicationRepo).save((ApplicationEntity) anyObject());
		verify(response).addHeader("Location", APPLICATION_LOCATION);
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
	public void getAll() {
		List<Application> actualApps = controller.getAll();
		assertNotNull(actualApps);
		verify(applicationRepo).findAll();
		verify(applicationMapper).toDtoList(applicationEntities);
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

}
