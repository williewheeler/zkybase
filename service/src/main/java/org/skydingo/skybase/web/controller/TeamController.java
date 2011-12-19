/* 
 * TeamController.java
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

import javax.inject.Inject;

import org.skydingo.skybase.repository.ProjectRepository;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Team controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
public class TeamController extends AbstractController {
	@Inject private ProjectRepository projectRepo;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/projects/{projectId}/team", method = RequestMethod.GET)
	public String getTeam(@PathVariable Long projectId, Model model) {
		model.addAttribute(projectRepo.findOne(projectId));
		return addNavigation(model, Sitemap.TEAM_DETAILS_ID);
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

}
