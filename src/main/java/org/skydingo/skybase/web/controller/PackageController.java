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

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.repository.ProjectRepository;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Package controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
public class PackageController extends AbstractController {
	@Inject private PackageService packageService;
	@Inject private PackageRepository packageRepo;
	@Inject private ProjectRepository projectRepo;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "groupId", "packageId", "version" });
	}
	
	/**
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping("/projects/{projectId}/packages/new")
	public String getCreatePackageForm(@PathVariable Long projectId, Model model) {
		Project project = projectRepo.findOne(projectId);
		model.addAttribute("package", new Package());
		return doGetCreatePackageForm(project, model);
	}
	
	/**
	 * @param projectId
	 * @param pkg
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/projects/{projectId}/packages")
	public String postCreatePackageForm(
			@PathVariable Long projectId,
			@ModelAttribute("package") @Valid Package pkg,
			BindingResult result,
			Model model) {
		
		Project project = projectRepo.findOne(projectId);
		pkg.setProject(project);
		packageService.createPackage(pkg, result);
		
		if (result.hasErrors()) {
			return doGetCreatePackageForm(project, model);
		}
		
		return "redirect:/projects/" + projectId + "/packages?a=created";
	}
	
	/**
	 * @param projectId project ID
	 * @param pkg package
	 * @param result result
	 */
//	@RequestMapping("/projects/{projectId}/packages")
//	public void postCreatePackageBody(
//			@PathVariable Long projectId,
//			@RequestBody @Valid Package pkg,
//			BindingResult result) {
//		
//		
//	}
	
	private String doGetCreatePackageForm(Project project, Model model) {
		model.addAttribute(project);
		return addNavigation(model, Sitemap.CREATE_PACKAGE_ID);
	}
	
	@RequestMapping(value = "/projects/{projectId}/packages", method = RequestMethod.GET)
	public String getPackageList(@PathVariable Long projectId, Model model) {
		Project project = projectRepo.findOne(projectId);
		List<Package> packages = CollectionsUtil.asSortedList(packageRepo.findPackagesByProject(project));
		
		model.addAttribute(project);
		model.addAttribute(packages);
		
		return addNavigation(model, Sitemap.PACKAGE_LIST_ID);
	}

}
