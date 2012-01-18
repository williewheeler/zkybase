/* 
 * FarmFormController.java
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
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.repository.DataCenterRepository;
import org.skydingo.skybase.repository.EnvironmentRepository;
import org.skydingo.skybase.repository.FarmRepository;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.service.FarmService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.controller.AbstractEntityFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/farms")
public class FarmFormController extends AbstractEntityFormController<Farm> {
	private static final String[] ALLOWED_FIELDS = new String[] { "name", "environment", "dataCenter" };
	
	private static final Logger log = LoggerFactory.getLogger(FarmFormController.class);
	
	@Inject private DataCenterRepository dataCenterRepo;
	@Inject private EnvironmentRepository environmentRepo;
	@Inject private FarmRepository farmRepo;
	@Inject private FarmService farmService;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityController#getRepository()
	 */
	@Override
	public GraphRepository<Farm> getRepository() { return farmRepo; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityController#getService()
	 */
	@Override
	public EntityService<Farm> getService() { return farmService; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormController#getAllowedFields()
	 */
	@Override
	protected String[] getAllowedFields() { return ALLOWED_FIELDS; }
	
	/**
	 * @return
	 */
	@ModelAttribute("dataCenterList")
	public List<DataCenter> populateDataCenters() {
		return CollectionsUtil.asSortedList(dataCenterRepo.findAll());
	}
	
	/**
	 * @return
	 */
	@ModelAttribute("environmentList")
	public List<Environment> populateEnvironments() {
		return CollectionsUtil.asSortedList(environmentRepo.findAll());
	}
}
