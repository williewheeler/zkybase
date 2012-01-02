/* 
 * PackageControllerTests.java
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
package org.skydingo.skybase.web.controller.form;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.web.navigation.Node;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.skydingo.skybase.web.view.ViewNames;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PackageFormControllerTests {
	@InjectMocks private PackageFormController controller;
	
	// Dependencies
	@Mock private Paths paths;
	@Mock private Sitemap sitemap;
	@Mock private ViewNames viewNames;
	@Mock private PackageRepository pkgRepo;
	@Mock private PackageService pkgService;
	
	// Test objects
	@Mock private Node node;
	@Mock private BindingResult result;
	@Mock private Model model;
	@Mock private Package pkg;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = new PackageFormController();
		MockitoAnnotations.initMocks(this);
		when(sitemap.getNode((String) any())).thenReturn(node);
		when(sitemap.getEntityListViewId(Package.class)).thenReturn("packageList");
		when(sitemap.getEntityDetailsViewId(Package.class)).thenReturn("packageDetails");
		when(sitemap.getEditFormId(Package.class)).thenReturn("editPackageForm");
		when(viewNames.putEditFormSuccessViewName(Package.class, 1L)).thenReturn("redirect:/packages/1?a=updated");
		when(viewNames.deleteSuccessViewName(Package.class)).thenReturn("redirect:/packages?a=deleted");
		when(pkgService.findPackages()).thenReturn(new ArrayList<Package>());
		when(pkgService.findPackage((Long) any())).thenReturn(pkg);
	}
	
	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Happy path test to get the edit package form.
	 */
	@Test
	public void testGetEditPackageForm() {
		String viewName = controller.getEditForm(1L, model);
		assertEquals("editPackageForm", viewName);
	}
	
	/**
	 * Happy path test to submit the edit package form.
	 */
	@Test
	public void testPutEditPackageForm() {
		String viewName = controller.putEditForm(1L, pkg, result, model);
		assertEquals("redirect:/packages/" + 1L + "?a=updated", viewName);
	}
	
	/**
	 * Confirms that we handle validation errors properly.
	 */
	@Test
	public void testPutEditPackageFormWithErrors() {
		when(result.hasErrors()).thenReturn(true);
		String viewName = controller.putEditForm(1L, pkg, result, model);
		assertEquals("editPackageForm", viewName);
	}
}
