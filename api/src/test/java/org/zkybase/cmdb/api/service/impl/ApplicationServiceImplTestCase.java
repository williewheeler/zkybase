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
package org.zkybase.cmdb.api.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zkybase.cmdb.api.domain.ApplicationEntity;
import org.zkybase.cmdb.api.repository.ApplicationRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ApplicationServiceImplTestCase {
	private static final Long APPLICATION_ID = 49994L;
	
	@InjectMocks private ApplicationServiceImpl service;
	
	@Mock private ApplicationRepository applicationRepo;
	
	@Mock private ApplicationEntity application;
	
	@Before
	public void setUp() throws Exception {
		this.service = new ApplicationServiceImpl();
		MockitoAnnotations.initMocks(this);
		setUpTestObjects();
		setUpDependencies();
	}
	
	private void setUpTestObjects() {
	}
	
	private void setUpDependencies() {
	}
	
	@Test
	public void update() {
		service.update(application);
		verify(applicationRepo).save(application);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateWithNullApplication() {
		service.update(null);
	}
	
	@Test
	public void delete() {
		service.delete(APPLICATION_ID);
		verify(applicationRepo).delete(APPLICATION_ID);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteWithNullId() {
		service.delete(null);
	}
}
