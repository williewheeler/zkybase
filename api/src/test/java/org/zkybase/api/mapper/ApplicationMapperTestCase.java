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
package org.zkybase.api.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;
import org.zkybase.api.domain.node.ApplicationNode;
import org.zkybase.api.mapper.ApplicationMapper;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class ApplicationMapperTestCase {
	private static final Long APPLICATION_ID = 9L;
	private static final String APPLICATION_NAME = "Zkybase CMDB";
	private static final int APP_COUNT = 4;
	
	@InjectMocks private ApplicationMapper applicationMapper;
	
	@Mock private Application applicationDto;
	@Mock private ApplicationNode applicationEntity;
	@Mock private List<ApplicationNode> applicationEntities;
	
	private Iterator<ApplicationNode> applicationEntityIterator;
		
	@Before
	public void setUp() throws Exception {
		this.applicationMapper = new ApplicationMapper();
		MockitoAnnotations.initMocks(this);
		setUpTestObjects();
		setUpDependencies();
	}
	
	private void setUpTestObjects() {
		this.applicationEntityIterator = new Iterator<ApplicationNode>() {
			private int count = APP_COUNT;
			
			@Override
			public boolean hasNext() { return (count-- > 0); }

			@Override
			public ApplicationNode next() { return applicationEntity; }

			@Override
			public void remove() { throw new UnsupportedOperationException(); }
		};
		
		when(applicationEntities.iterator()).thenReturn(applicationEntityIterator);
		
		when(applicationEntity.getId()).thenReturn(APPLICATION_ID);
		when(applicationEntity.getName()).thenReturn(APPLICATION_NAME);
		
		when(applicationDto.getId()).thenReturn(APPLICATION_ID);
		when(applicationDto.getName()).thenReturn(APPLICATION_NAME);
	}
	
	private void setUpDependencies() {
	}
	
	@Test
	public void toEntity() {
		ApplicationNode actualEntity = applicationMapper.toEntity(applicationDto);
		assertNotNull(actualEntity);
		assertEquals(APPLICATION_ID, actualEntity.getId());
		assertEquals(APPLICATION_NAME, actualEntity.getName());
	}
	
	@Test
	public void toEntityWithNullDto() {
		ApplicationNode actualEntity = applicationMapper.toEntity(null);
		assertNull(actualEntity);
	}
	
	@Test
	public void toDto() {
		Application actualDto = applicationMapper.toDto(applicationEntity);
		assertNotNull(actualDto);
		assertEquals(APPLICATION_ID, actualDto.getId());
		assertEquals(APPLICATION_NAME, actualDto.getName());
	}
	
	@Test
	public void toDtoWithNullEntity() {
		Application actualDto = applicationMapper.toDto(null);
		assertNull(actualDto);
	}
	
	@Test
	public void toDtoList() {
		List<Application> actualDtoList = applicationMapper.toDtos(applicationEntities);
		assertNotNull(actualDtoList);
	}
	
	@Test
	public void toDtoListWithNullEntities() {
		List<Application> actualDtoList = applicationMapper.toDtos(null);
		assertNull(actualDtoList);
	}
}
