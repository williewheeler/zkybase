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
package org.zkybase.service.impl;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.zkybase.api.domain.entity.Region;
import org.zkybase.cmdb.api.repository.RegionRepository;
import org.zkybase.service.impl.AbstractCIService;
import org.zkybase.service.impl.RegionServiceImpl;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class RegionServiceImplTests extends AbstractEntityServiceImplTests<Region> {
	@InjectMocks private RegionServiceImpl regionService;
	@Mock private RegionRepository regionRepo;
	
	// Test objects
	@Mock private Region region;
	@Mock private ClosableIterable<Region> regions;
	@Mock private Iterator<Region> regionIterator;

	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractEntityServiceImplTests#getRepository()
	 */
	@Override
	protected GraphRepository<Region> getRepository() { return regionRepo; }

	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractEntityServiceImplTests#getService()
	 */
	@Override
	protected AbstractCIService<Region> getService() { return regionService; }
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.regionService = new RegionServiceImpl();
		
		MockitoAnnotations.initMocks(this);
		
		when(regionRepo.findOne(anyLong())).thenReturn(region);
		when(regionRepo.findAll()).thenReturn(regions);
		when(regions.iterator()).thenReturn(regionIterator);
	}
}
