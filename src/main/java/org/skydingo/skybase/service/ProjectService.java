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

import org.skydingo.skybase.model.Instance;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ProjectService {
	@Inject private ProjectRepository projectRepo;
	@Inject private PackageRepository packageRepo;
	
	@Transactional
	public Project populateDatabase() {
		
		// Entities
		Project skybase = new Project("skybase", "Skybase");
		
		Person willie = new Person("Willie", "Wheeler");
		Person eric = new Person("Eric", "Barrier");
		
		Instance inst1 = new Instance("192.168.1.101");
		Instance inst2 = new Instance("192.168.1.102");
		Instance inst3 = new Instance("192.168.1.103");
		Instance inst4 = new Instance("192.168.1.104");
		
		Package pkg100 = new Package("skybase-1.0.0");
		Package pkg101 = new Package("skybase-1.0.1");
		
		// Relationships
		willie.memberOf(skybase, "Developer");
		eric.memberOf(skybase, "Rapper");
		
		pkg100.builtFrom(skybase);
		pkg100.deployedTo(inst1);
		pkg100.deployedTo(inst2);
		
		pkg101.builtFrom(skybase);
		pkg101.deployedTo(inst3);
		pkg101.deployedTo(inst4);
		
		projectRepo.save(skybase);
		packageRepo.save(pkg100);
		packageRepo.save(pkg101);
		
		return skybase;
	}
}
