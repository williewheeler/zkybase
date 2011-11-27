/* 
 * ProjectController.java
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
package org.skydingo.skybase.web;

import static org.springframework.util.Assert.isTrue;

import javax.inject.Inject;
import javax.validation.Valid;

import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.repository.ProjectRepository;
import org.skydingo.skybase.web.navigation.Breadcrumb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/projects")
public class ProjectController extends AbstractController {
	@Inject private ProjectRepository projectRepo;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "name", "shortDescription" });
	}
	
	/**
	 * Generates the project creator form.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public String getProjectCreator(Model model) {
		model.addAttribute(new Project());
		return doGetProjectCreator(model);
	}
	
	/**
	 * @param project project
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createProject(@ModelAttribute @Valid Project project, BindingResult result, Model model) {
		
		// FIXME Need to do these checks in the same transaction and the create to avoid races.
		String key = project.getId();
		String name = project.getName();
		
		// Check for duplicate name or key. Currently we have to do this manually because Neo4j doesn't support
		// unique constraints. :-/
		// http://forum.springsource.org/showthread.php?110179-does-neo4j-support-unique-index-constraint
		if (key != null && projectRepo.findProjectById(key) != null) {
			result.rejectValue("id", "error.project.duplicateKey");
		}
		
		if (name != null && projectRepo.findProjectByName(name) != null) {
			result.rejectValue("name", "error.project.duplicateName");
		}
		
		if (result.hasErrors()) {
			return doGetProjectCreator(model);
		}
		
		projectRepo.save(project);
		return "redirect:/?created=true";
	}
	
	/**
	 * Generates the project details view.
	 * 
	 * @param key project key
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public String getProject(@PathVariable String key, Model model) {
		addBreadcrumbs(model);
		model.addAttribute(projectRepo.findProjectById(key));
		return "project/projectDetails";
	}
	
	/**
	 * Generates the project editor form.
	 * 
	 * @param key project key
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{key}/edit", method = RequestMethod.GET)
	public String getProjectEditor(@PathVariable String key, Model model) {
		Project project = projectRepo.findProjectById(key);
		model.addAttribute(project);
		return doGetProjectEditor(project, model);
	}
	
	/**
	 * @param project project
	 * @return logical view name
	 */
	@RequestMapping(value = "/{key}", method = RequestMethod.PUT)
	public String updateProject(
			@PathVariable String key,
			@ModelAttribute @Valid Project project,
			BindingResult result,
			Model model) {
		
		// Prevent POJO injection.
		isTrue(key.equals(project.getId()));
		
		// Check for duplicate name.
		// http://forum.springsource.org/showthread.php?110179-does-neo4j-support-unique-index-constraint
		// FIXME Need to do this check in the same transaction and the create to avoid races.
		String name = project.getName();
		if (name != null) {
			Project found = projectRepo.findProjectByName(name);
			if (found != null && !key.equals(found.getId())) {
				result.rejectValue("name", "error.project.duplicateName");
			}
		}
		
		if (result.hasErrors()) {
			return doGetProjectEditor(project, model);
		}
		
		// Copy the form data into the persistent project and save the latter. Otherwise we're going to create a new
		// project with a new node ID.
		Project pProject = projectRepo.findProjectById(key);
		pProject.setName(project.getName());
		pProject.setShortDescription(project.getShortDescription());
		
		projectRepo.save(pProject);
		return "redirect:/projects/" + key + "?updated=true";
	}
	
	
	// =================================================================================================================
	// Helpers
	// =================================================================================================================
	
	private String doGetProjectCreator(Model model) {
		setMode(model, MODE_CREATE);
		addBreadcrumbs(model);
		return doGetProjectForm();
	}
	
	private String doGetProjectEditor(Project project, Model model) {
		setMode(model, MODE_EDIT);
		
		// Have to do this in case the user edited the project name.
		String key = project.getId();
		Project origProject = projectRepo.findProjectById(key);
		
		addBreadcrumbs(model, new Breadcrumb(origProject.getName(), "/projects/" + key));
		return doGetProjectForm();
	}
	
	private String doGetProjectForm() {
		return "project/projectForm";
	}
}
