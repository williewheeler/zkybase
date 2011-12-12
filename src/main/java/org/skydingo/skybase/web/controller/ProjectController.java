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
package org.skydingo.skybase.web.controller;

import static org.springframework.util.Assert.isTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.neo4j.helpers.collection.IteratorUtil;
import org.skydingo.skybase.converter.KeyToProjectConverter;
import org.skydingo.skybase.model.FarmTemplate;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.skydingo.skybase.repository.FarmTemplateRepository;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.repository.ProjectRepository;
import org.skydingo.skybase.service.ProjectService;
import org.skydingo.skybase.web.jit.JitNode;
import org.skydingo.skybase.web.navigation.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Project controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/projects")
public class ProjectController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	@Inject private KeyToProjectConverter keyToProjectConverter;
	
	@Inject private FarmTemplateRepository farmTemplateRepo;
	@Inject private PersonRepository personRepo;
	@Inject private ProjectRepository projectRepo;
	
	@Inject private ProjectService projectService;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "key", "name", "shortDescription" });
	}
	
	
	// =================================================================================================================
	// CRUD
	// =================================================================================================================
	
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
		String key = project.getKey();
		String name = project.getName();
		
		// Check for duplicate name or key. Currently we have to do this manually because Neo4j doesn't support
		// unique constraints. :-/
		// http://forum.springsource.org/showthread.php?110179-does-neo4j-support-unique-index-constraint
		if (key != null && projectRepo.findProjectByKey(key) != null) {
			result.rejectValue("id", "error.project.duplicateKey");
		}
		
		if (name != null && projectRepo.findProjectByName(name) != null) {
			result.rejectValue("name", "error.project.duplicateName");
		}
		
		if (result.hasErrors()) {
			return doGetProjectCreator(model);
		}
		
		projectRepo.save(project);
		return "redirect:/?a=created";
	}
	
	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public String getProjectDetails(@PathVariable String key, Model model) {
		addBreadcrumbs(model);
		model.addAttribute(projectService.findProjectByKey(key));
		return "redirect:/projects/" + key + "/packages";
	}
	
	/**
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{key}/deployment", method = RequestMethod.GET)
	public String getDeployments(@PathVariable String key, Model model) {
		Project project = projectService.findProjectByKey(key);
		List<FarmTemplate> farmTemplates = new ArrayList<FarmTemplate>(
				IteratorUtil.asCollection(farmTemplateRepo.findFarmTemplatesByProject(project)));
		
		addBreadcrumbs(model);
		model.addAttribute(project);
		model.addAttribute(farmTemplates);
		model.addAttribute(new FarmTemplate());
		return "project/details/deployments";
	}
	
	@RequestMapping(value = "/{key}/team", method = RequestMethod.GET)
	public String getTeam(@PathVariable String key, Model model) {
		addBreadcrumbs(model);
		model.addAttribute(projectService.findProjectByKey(key));
		return "project/details/team";
	}
	
	// FIXME TEMPORARY
//	@RequestMapping(value = "/{key}/team.json", method = RequestMethod.GET)
//	public String getTeamAsJit(@PathVariable String key, Model model) {
//		Project project = projectService.findProjectByKey(key);
//		Collection<ProjectMembership> memberships = project.getMemberships();
//		
//		JitNode projectNode = new JitNode();
//		projectNode.setId("project_" + project.getId());
//		projectNode.setName(project.getName());
//		
//		Set<JitNode> children = new HashSet<JitNode>();
//		for (ProjectMembership membership : memberships) {
//			Person member = membership.getPerson();
//			String role = membership.getRole();
//			
//			JitNode memberNode = new JitNode();
//			memberNode.setId("member_" + member.getId());
//			memberNode.setName(member.getFirstNameLastName());
//			Map<String, String> data = new HashMap<String, String>();
//			data.put("role", role);
//			memberNode.setData(data);
//			memberNode.setChildren(new HashSet<JitNode>());
//			children.add(memberNode);
//		}
//		projectNode.setChildren(children);
//		
//		model.addAttribute("json", projectNode);
//		return "project/details/team";
//	}
	
	/**
	 * Generates the project editor form.
	 * 
	 * @param key project key
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{key}/edit", method = RequestMethod.GET)
	public String getProjectEditor(@PathVariable String key, Model model) {
		Project project = projectRepo.findProjectByKey(key);
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
		isTrue(key.equals(project.getKey()));
		
		// Check for duplicate name.
		// http://forum.springsource.org/showthread.php?110179-does-neo4j-support-unique-index-constraint
		// FIXME Need to do this check in the same transaction and the create to avoid races.
		String name = project.getName();
		if (name != null) {
			Project found = projectRepo.findProjectByName(name);
			if (found != null && !key.equals(found.getKey())) {
				result.rejectValue("name", "error.project.duplicateName");
			}
		}
		
		if (result.hasErrors()) {
			return doGetProjectEditor(project, model);
		}
		
		// Copy the form data into the persistent project and save the latter. Otherwise we're going to create a new
		// project with a new node ID.
		Project pProject = projectRepo.findProjectByKey(key);
		pProject.setName(project.getName());
		pProject.setShortDescription(project.getShortDescription());
		
		projectRepo.save(pProject);
		return "redirect:/projects/" + key + "?a=updated";
	}
	
	
	// =================================================================================================================
	// Associations
	// =================================================================================================================
	
	/**
	 * @param key
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/{key}/members", method = RequestMethod.POST)
	public String addMember(
			@PathVariable String key,
			@RequestParam("member") String memberStr,
			HttpServletResponse res) {
		
		log.debug("Adding member {} to project {}", memberStr, key);
		
		// FIXME Think I need to look up the member in a transaction so that the entity will be persistent
//		Project project = projectRepo.findProjectByKey(key);
		
		// FIXME Figure out how to get the converter to kick in automatically. Should be possible; see
		// http://blog.springsource.org/2009/11/17/spring-3-type-conversion-and-validation/
		Project project = keyToProjectConverter.convert(key);
		log.debug("Loaded project: {}", project);
		
		// FIXME Temporary parsing approach
		int start = memberStr.indexOf('(') + 1;
		int end = memberStr.indexOf(')');
		String username = memberStr.substring(start, end);
		
		Person member = personRepo.findByUsername(username);
		log.debug("Loaded person: {}", member);
		projectService.addMember(project, member, "Developer");
		
		return null;
	}
	
	
	// =================================================================================================================
	// Helpers
	// =================================================================================================================
	
	private String doGetProjectCreator(Model model) {
		setMode(model, MODE_CREATE);
		addBreadcrumbs(model);
		return "project/form/createForm";
	}
	
	private String doGetProjectEditor(Project project, Model model) {
		setMode(model, MODE_EDIT);
		
		// Have to do this in case the user edited the project name.
		String key = project.getKey();
		Project origProject = projectRepo.findProjectByKey(key);
		
		addBreadcrumbs(model, new Breadcrumb(origProject.getName(), "/projects/" + key));
		return "project/form/editForm";
	}
}
