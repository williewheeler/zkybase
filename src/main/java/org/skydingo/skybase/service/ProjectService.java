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

import javax.inject.Inject;

import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ProjectService {
	@Inject private ProjectRepository projectRepo;
	
	@Transactional
	public Project populateDatabase() {
		Project skybase = new Project("skybase", "Skybase");
		
		Person willie = new Person("Willie", "Wheeler");
		willie.memberOf(skybase, "Developer");
		
		Person eric = new Person("Eric", "Barrier");
		eric.memberOf(skybase, "Rapper");
		
		projectRepo.save(skybase);
		
		return skybase;
	}
}
