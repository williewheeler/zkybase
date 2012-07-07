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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:/spring/beans-service.xml",
	"classpath:/spring/beans-web.xml"
})
@Transactional
public class ApplicationControllerIT {
	private static final Long APPLICATION_ID = 144L;
	
	@Inject private ApplicationController controller;
	
	@Mock private HttpServletResponse response;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void post() {
		Application application = null;
		
		application = new Application();
		controller.post(application, response);
		Long id = application.getId();
		assertNotNull(id);
		
		application = controller.get(id);
		assertNotNull(application);
	}
	
	@Test
	public void getNonexistent() {
		Application application = controller.get(APPLICATION_ID);
		assertNull(application);
	}
}
