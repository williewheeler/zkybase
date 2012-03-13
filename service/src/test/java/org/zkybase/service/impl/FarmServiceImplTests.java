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

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.zkybase.model.Farm;
import org.zkybase.model.Instance;
import org.zkybase.repository.FarmRepository;
import org.zkybase.service.impl.AbstractCIService;
import org.zkybase.service.impl.FarmServiceImpl;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class FarmServiceImplTests extends AbstractEntityServiceImplTests<Farm> {
	@InjectMocks private FarmServiceImpl farmService;
	@Mock private FarmRepository farmRepository;
	
	// Test objects
	@Mock private Farm farm;
	@Mock private ClosableIterable<Farm> farms;
	@Mock private Iterator<Farm> farmIterator;

	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractEntityServiceImplTests#getRepository()
	 */
	@Override
	protected GraphRepository<Farm> getRepository() { return farmRepository; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractEntityServiceImplTests#getService()
	 */
	@Override
	public AbstractCIService<Farm> getService() { return farmService; }
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.farmService = new FarmServiceImpl();
		
		MockitoAnnotations.initMocks(this);
		
		when(farmRepository.findOne(anyLong())).thenReturn(farm);
		when(farmRepository.findAll()).thenReturn(farms);
		when(farm.getInstances()).thenReturn(new ArrayList<Instance>());
		when(farms.iterator()).thenReturn(farmIterator);
	}
}
