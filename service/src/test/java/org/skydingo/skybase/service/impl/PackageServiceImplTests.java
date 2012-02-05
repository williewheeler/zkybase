/* 
 * PackageServiceImplTests.java
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
package org.skydingo.skybase.service.impl;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PackageServiceImplTests extends AbstractEntityServiceImplTests<Package> {
	@InjectMocks private PackageServiceImpl packageService;
	@Mock private PackageRepository packageRepo;
	@Mock private Package pkg;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.impl.AbstractEntityServiceImplTests#getRepository()
	 */
	@Override
	protected GraphRepository<Package> getRepository() { return packageRepo; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.impl.AbstractEntityServiceImplTests#getService()
	 */
	@Override
	protected AbstractCIService<Package> getService() { return packageService; }
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.packageService = new PackageServiceImpl();
		MockitoAnnotations.initMocks(this);
		when(packageRepo.findOne(anyLong())).thenReturn(pkg);
	}

}
