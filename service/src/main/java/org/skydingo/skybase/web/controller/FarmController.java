/* 
 * FarmController.java
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
package org.skydingo.skybase.web.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.repository.FarmRepository;
import org.skydingo.skybase.service.FarmService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/farms")
public class FarmController extends AbstractController {
	@Inject private FarmService farmService;
	@Inject private FarmRepository farmRepo;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractController#doInitBinder(
	 * org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}
	
	
	// =================================================================================================================
	// Create
	// =================================================================================================================
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getCreateFarmForm(Model model) {
		model.addAttribute(new Farm());
		return addNavigation(model, Sitemap.CREATE_FARM_ID);
	}
	
	/**
	 * @param farm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postCreateFarmForm(
			@ModelAttribute("farm") @Valid Farm farm,
			BindingResult result,
			Model model) {
		
		farmService.createFarm(farm, result);
		if (result.hasErrors()) {
			return addNavigation(model, Sitemap.CREATE_FARM_ID);
		}
		return "redirect:/farms?a=created";
	}
	
	
	// =================================================================================================================
	// Read
	// =================================================================================================================
	
	/**
	 * @param model model
	 * @return view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getFarmList(Model model) {
		List<Farm> farms = CollectionsUtil.asList(farmRepo.findAll());
		model.addAttribute(farms);
		return addNavigation(model, Sitemap.FARM_LIST_ID);
	}
}
