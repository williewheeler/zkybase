/* 
 * JitView.java
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
package org.skydingo.skybase.web.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.skydingo.skybase.web.jit.JitNode;
import org.springframework.web.servlet.View;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class JitView implements View {
	private ObjectMapper objectMapper;
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#getContentType()
	 */
	@Override
	public String getContentType() {
		return "application/jit";
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Project project = (Project) model.get("project");
		Iterable<ProjectMembership> memberships = project.getMemberships();
		
		JitNode projectNode = new JitNode();
		projectNode.setId("project_" + project.getId());
		projectNode.setName(project.getName());
		
		Set<JitNode> children = new HashSet<JitNode>();
		for (ProjectMembership membership : memberships) {
			Person member = membership.getPerson();
			String role = membership.getRole();
			
			JitNode memberNode = new JitNode();
			memberNode.setId("member_" + member.getId());
			memberNode.setName(member.getFirstNameLastName());
			Map<String, String> data = new HashMap<String, String>();
			data.put("role", role);
			memberNode.setData(data);
			memberNode.setChildren(new HashSet<JitNode>());
			children.add(memberNode);
		}
		projectNode.setChildren(children);
		
		String json = objectMapper.writeValueAsString(projectNode);
		response.getWriter().write(json);
//		response.flushBuffer();
	}
}
