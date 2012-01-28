/*
 * DataCenterFormController.java
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

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.model.DataCenter;
import org.skydingo.skybase.model.Region;
import org.skydingo.skybase.repository.DataCenterRepository;
import org.skydingo.skybase.service.DataCenterService;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.service.RegionService;
import org.skydingo.skybase.web.controller.AbstractEntityFormController;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/datacenters")
public class DataCenterFormController extends AbstractEntityFormController<DataCenter> {
	private static final String[] ALLOWED_FIELDS = new String[] { "name", "region" };

	@Inject private DataCenterRepository dataCenterRepo;
	@Inject private DataCenterService dataCenterService;
	@Inject private RegionService regionService;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityController#getRepository()
	 */
	@Override
	public GraphRepository<DataCenter> getRepository() { return dataCenterRepo; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityController#getService()
	 */
	@Override
	public EntityService<DataCenter> getService() { return dataCenterService; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormController#getAllowedFields()
	 */
	@Override
	protected String[] getAllowedFields() { return ALLOWED_FIELDS; }

	/**
	 * @return
	 */
	@ModelAttribute("regionList")
	public List<Region> populateRegions() {
		return regionService.findAll();
	}

}
