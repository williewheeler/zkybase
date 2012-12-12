/* 
 * RegionControllerTests.java
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
package org.zkybase.web.controller.region;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mock;
import org.zkybase.api.domain.entity.DataCenter;
import org.zkybase.api.domain.entity.Region;
import org.zkybase.service.RegionService;
import org.zkybase.web.controller.AbstractCrudControllerTests;
import org.zkybase.web.controller.region.RegionCrudController;
import org.zkybase.web.view.JitNode;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class RegionCrudControllerTests extends AbstractCrudControllerTests<Region> {
	
	// Dependencies
	@Mock private RegionService regionService;
	
	// Test objects
	@Mock private Region region;
	@Mock private DataCenter dataCenter;
	private Set<DataCenter> dataCenters;
	
	/* (non-Javadoc)
	 * @see org.zkybase.web.controller.AbstractCrudControllerTests#doSetUp()
	 */
	@Override
	protected void doSetUp() throws Exception {
		this.dataCenters = new HashSet<DataCenter>();
		dataCenters.add(dataCenter);
		dataCenters.add(dataCenter);
		
		when(region.getDataCenters()).thenReturn(dataCenters);
		
		when(regionService.findOne(anyLong())).thenReturn(region);
	}
	
	/**
	 * @return
	 */
	private RegionCrudController getController() {
		return (RegionCrudController) controller;
	}
	
	@Test
	public void testGetDetailsAsJit() {
		JitNode regionNode = getController().getDetailsAsJit(1L);
		assertNotNull(regionNode);
		
		Set<JitNode> dataCenterNodes = regionNode.getChildren();
		assertEquals(dataCenters.size(), dataCenterNodes.size());
	}
}
