/* 
 * PackageController.java
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

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
public class PackageController extends AbstractController {
	@Inject private PackageService packageService;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "groupId", "packageId", "version" });
	}
	
	
	// =================================================================================================================
	// Create
	// =================================================================================================================
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/packages/new", method = RequestMethod.GET)
	public String getCreatePackageForm(Model model) {
		model.addAttribute(new Package());
		return addNavigation(model, Sitemap.CREATE_PACKAGE_ID);
	}
	
	/**
	 * @param pkg
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/packages", method = RequestMethod.POST)
	public String postCreatePackageForm(
			@ModelAttribute("package") @Valid Package pkg,
			BindingResult result,
			Model model) {
		
		packageService.createPackage(pkg, result);
		
		if (result.hasErrors()) {
			return addNavigation(model, Sitemap.CREATE_PACKAGE_ID);
		}
		
		return "redirect:/packages?a=created";
	}
	
	
	// =================================================================================================================
	// Read
	// =================================================================================================================
	
	/**
	 * @param model
	 * @param req
	 * @param out
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/packages", method = RequestMethod.GET)
	public String getPackageList(Model model) {
		List<Package> pkgs = packageService.findPackages();
		model.addAttribute(pkgs);
		return addNavigation(model, Sitemap.PACKAGE_LIST_ID);
	}
	
	@RequestMapping(value = "/packages.json", method = RequestMethod.GET)
	@ResponseBody
	public List<Package> getPackageListAsJson() {
		return packageService.findPackages();
	}
	
	@RequestMapping(value = "/packages.xml", method = RequestMethod.GET)
	public void getPackageListAsXml(Model model) {
		model.addAttribute(new Package.ListWrapper(packageService.findPackages()));
	}

	
	// =================================================================================================================
	// Update
	// =================================================================================================================
	
	
	// =================================================================================================================
	// Delete
	// =================================================================================================================
	
}
