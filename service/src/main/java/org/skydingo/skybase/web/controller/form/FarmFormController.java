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
import javax.validation.Valid;

import org.skydingo.skybase.model.DataCenter;
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.repository.DataCenterRepository;
import org.skydingo.skybase.repository.EnvironmentRepository;
import org.skydingo.skybase.service.FarmService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.controller.AbstractEntityFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/farms")
public class FarmFormController extends AbstractEntityFormController<Farm> {
	private static final Logger log = LoggerFactory.getLogger(FarmFormController.class);
	
	@Inject private DataCenterRepository dataCenterRepo;
	@Inject private EnvironmentRepository environmentRepo;
	@Inject private FarmService farmService;
	
	/**
	 * @param binder
	 */
	@InitBinder
	public void initBinderSpecial(WebDataBinder binder) {
		log.debug("Initializing binder: {}", binder.getObjectName());
		binder.setAllowedFields("name", "environment", "dataCenter");
	}
	
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
	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putEditForm(
			@PathVariable Long id,
			@ModelAttribute(MK_FORM_DATA) @Valid Farm formData,
			BindingResult result,
			Model model) {
		
		farmService.create(formData, result);
		
		if (result.hasErrors()) {
			model.addAttribute(MK_HAS_ERRORS, true);
			return prepareEditForm(id, model);
		}
		
		formData.setId(id);
		return viewNames.putEditFormSuccessViewName(getEntityClass(), id);
	}
}
