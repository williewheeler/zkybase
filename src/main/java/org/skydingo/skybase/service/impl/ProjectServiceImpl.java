/* 
 * ProjectServiceImpl.java
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
package org.skydingo.skybase.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Set;

import javax.inject.Inject;

import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.skydingo.skybase.repository.ProjectRepository;
import org.skydingo.skybase.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Inject private ProjectRepository projectRepo;
	@Inject private Neo4jTemplate template;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.ProjectService#findProjectByKey(java.lang.String)
	 */
	@Override
	@Transactional
	public Project findProjectByKey(String key) {
		notNull(key);
		Project project = projectRepo.findProjectByKey(key);
		log.debug("Found project: {}", project);
		Set<Person> members = project.getMembers();
		for (Person member : members) {
			log.debug("Member: {}", member);
		}
		return project;
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.ProjectService#addMember
	 * (org.skydingo.skybase.model.Project, org.skydingo.skybase.model.Person, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void addMember(Project project, Person member, String role) {
		notNull(project);
		notNull(member);
		
//		Project savedProject = projectRepo.save(project);
//		member.memberOf(savedProject, role);
//		personRepo.save(member);
		
		ProjectMembership membership = member.memberOf(project, role);
		
		// Save this membership! It doesn't seem to work correctly if we save the project or the person. For example,
		// this saves the __type__ property on the relationship, and also links the project and the person.
		template.save(membership);
	}

}
