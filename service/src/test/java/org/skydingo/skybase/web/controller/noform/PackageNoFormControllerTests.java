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
package org.skydingo.skybase.web.controller.noform;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.mockito.Mock;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.web.controller.AbstractEntityNoFormControllerTests;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PackageNoFormControllerTests extends AbstractEntityNoFormControllerTests<Package> {
	
	// Dependencies
	@Mock private PackageRepository pkgRepo;
	@Mock private PackageService pkgService;
	
	// Test objects
	@Mock private Package pkg;
	
	@Override
	protected void doSetUp() throws Exception {
		when(pkgService.findPackages()).thenReturn(new ArrayList<Package>());
		when(pkgService.findPackage((Long) any())).thenReturn(pkg);
	}
}
