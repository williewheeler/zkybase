/* 
 * EnvironmentNoFormControllerTests.java
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
package org.zkybase.web.controller.environment;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.zkybase.model.Environment;
import org.zkybase.service.EnvironmentService;
import org.zkybase.service.FarmService;
import org.zkybase.web.controller.AbstractCrudControllerTests;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class EnvironmentCrudControllerTests extends AbstractCrudControllerTests<Environment> {
	
	// Dependencies
	@Mock private EnvironmentService environmentService;
	@Mock private FarmService farmService;
	
	// Test objects
	@Mock private Environment environment;
	
	@Override
	protected void doSetUp() throws Exception {
		when(environmentService.findOne(anyLong())).thenReturn(environment);
	}
}
