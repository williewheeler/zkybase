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
package org.skydingo.skybase.service.impl;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skydingo.skybase.exception.DuplicateCIException;
import org.skydingo.skybase.model.Module;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.service.ModuleService;
import org.skydingo.skybase.service.PackageService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring/beans-service.xml" })
@Transactional
public class PackageServiceImplIT {
	@Inject private ModuleService moduleService;
	@Inject private PackageService pkgService;
	
	private Module module;
	private Package pkg;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.module = new Module();
		module.setName("Skybase Maven Plugin");
		module.setGroupId("org.skydingo.skybase");
		module.setModuleId("org.skydingo.skybase.maven");
		
		this.pkg = new Package();
		pkg.setModule(module);
		pkg.setVersion("1.0.0-SNAPSHOT");
	}
	
	/**
	 * Happy path create package test.
	 */
	@Test
	public void testCreatePackage() {
		moduleService.create(module);
		assertTrue(module.getId() > 0L);
		pkgService.create(pkg);
	}
	
	/**
	 * Sad path create package test--creating a duplicate should throw an exception.
	 */
	@Test(expected = DuplicateCIException.class)
	public void testCreateDuplicatePackage() {
		moduleService.create(module);
		assertTrue(module.getId() > 0L);
		pkgService.create(pkg);
		
		Package duplicate = new Package();
		duplicate.setModule(pkg.getModule());
		duplicate.setVersion(pkg.getVersion());
		pkgService.create(duplicate);
	}
}
