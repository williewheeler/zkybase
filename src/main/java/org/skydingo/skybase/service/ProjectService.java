/* 
 * ProjectService.java
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
package org.skydingo.skybase.service;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.neo4j.helpers.collection.ClosableIterable;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project service.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class ProjectService {
	@Inject private ProjectRepository projectRepo;
	
	/**
	 * @return projects
	 */
	public List<Project> getProjects() {
		ClosableIterable<Project> projectIt = projectRepo.findAll();
		List<Project> projects = new ArrayList<Project>();
		for (Project project : projectIt) {
			projects.add(project);
		}
		return projects;
	}
	
	/**
	 * @param id project ID
	 * @return project
	 */
	public Project getProject(String id) {
		notNull(id);
		return projectRepo.findProjectById(id);
	}
}
