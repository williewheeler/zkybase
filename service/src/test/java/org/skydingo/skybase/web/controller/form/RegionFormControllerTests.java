/* 
 * RegionFormControllerTests.java
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

import org.mockito.Mock;
import org.skydingo.skybase.model.Region;
import org.skydingo.skybase.repository.RegionRepository;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.service.RegionService;
import org.skydingo.skybase.web.controller.AbstractEntityFormController;
import org.skydingo.skybase.web.controller.AbstractEntityFormControllerTests;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class RegionFormControllerTests extends AbstractEntityFormControllerTests<Region> {
	
	// Dependencies
	@Mock private RegionRepository regionRepo;
	@Mock private RegionService regionService;
	
	// Test objects
	@Mock private Region region;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormControllerTests#initController()
	 */
	@Override
	protected void initController() {
		this.controller = new RegionFormController();
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormControllerTests#getController()
	 */
	@Override
	protected AbstractEntityFormController<Region> getController() { return controller; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormControllerTests#getRepository()
	 */
	@Override
	protected GraphRepository<Region> getRepository() { return regionRepo; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormControllerTests#getService()
	 */
	@Override
	protected EntityService<Region> getService() { return regionService; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormControllerTests#getEntity()
	 */
	@Override
	protected Region getEntity() { return region; }

}
