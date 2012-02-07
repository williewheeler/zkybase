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
package org.skydingo.skybase.web.controller.application;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.Module;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.service.ApplicationService;
import org.skydingo.skybase.service.ModuleService;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.web.controller.AbstractController;
import org.skydingo.skybase.web.controller.AbstractCrudController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
@RequestMapping("/applications")
public class ApplicationPackageController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(ApplicationPackageController.class);
	
	@Inject private ApplicationService applicationService;
	@Inject private ModuleService moduleService;
	@Inject private PackageService packageService;
	
	/**
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.setAllowedFields("version");
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{applicationId}/modules/{moduleId}/packages/new", method = RequestMethod.GET)
	public String newPackage(@PathVariable Long applicationId, @PathVariable Long moduleId, Model model) {
		Application app = applicationService.findOne(applicationId);
		Module module = moduleService.findOne(moduleId);
		model.addAttribute(AbstractCrudController.MK_FORM_DATA, new Package());
		return prepareCreateForm(app, module, model);
	}
	
	@RequestMapping(value = "/{applicationId}/modules/{moduleId}/packages", method = RequestMethod.POST)
	public String createPackage(
			@PathVariable Long applicationId,
			@PathVariable Long moduleId,
			@ModelAttribute(AbstractCrudController.MK_FORM_DATA) @Valid Package pkg,
			BindingResult result,
			Model model) {
		
		log.debug("Creating package: {}", pkg);
		
		Application app = applicationService.findOne(applicationId);
		Module module = moduleService.findOne(moduleId);
		pkg.setModule(module);
		
		packageService.create(pkg, result);
		
		if (result.hasErrors()) {
			log.debug("Invalid package: {}", pkg);
			return prepareCreateForm(app, module, model);
		}
		
		log.debug("Created package: {}", pkg);
		return "redirect:/applications/" + applicationId + "/modules/" + module.getId();
	}
	
	private String prepareCreateForm(Application app, Module module, Model model) {
		model.addAttribute(app);
		model.addAttribute(module);
		model.addAttribute(AbstractCrudController.MK_FORM_METHOD, "post");
		return addNavigation(model, "createApplicationPackageForm");
	}
}
