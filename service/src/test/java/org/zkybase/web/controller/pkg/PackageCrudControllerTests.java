/* 
 * PackageNoFormControllerTests.java
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
package org.zkybase.web.controller.pkg;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.mockito.Mock;
import org.zkybase.model.Package;
import org.zkybase.service.PackageService;
import org.zkybase.web.controller.AbstractCrudControllerTests;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PackageCrudControllerTests extends AbstractCrudControllerTests<Package> {
	
	// Dependencies
	@Mock private PackageService pkgService;
	
	// Test objects
	@Mock private Package pkg;
	
	@Override
	protected void doSetUp() throws Exception {
		when(pkgService.findAll()).thenReturn(new ArrayList<Package>());
		when(pkgService.findOne((Long) any())).thenReturn(pkg);
	}
}
