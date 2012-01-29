/* 
 * ApplicationCrudControllerTests.java
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
package org.skydingo.skybase.web.controller.application;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.service.ApplicationService;
import org.skydingo.skybase.service.FarmService;
import org.skydingo.skybase.web.controller.AbstractCrudControllerTests;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ApplicationCrudControllerTests extends AbstractCrudControllerTests<Application> {
	
	// Dependencies
	@Mock private ApplicationService applicationService;
	@Mock private FarmService farmService;
	
	// Test objects
	@Mock private Application application;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractCrudControllerTests#doSetUp()
	 */
	@Override
	protected void doSetUp() throws Exception {
		when(applicationService.findOne(anyLong())).thenReturn(application);
	}
}
