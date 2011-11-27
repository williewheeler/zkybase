/* 
 * ProjectRepository.java
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
package org.skydingo.skybase.repository;

import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface ProjectRepository extends GraphRepository<Project> {
	
	/**
	 * Returns the project having the given ID.
	 * 
	 * @param id project ID
	 * @return project with the given ID
	 */
	Project findProjectById(String id);
	
	Project findProjectByName(String name);
	
	@Query("start person=node({0}) match person-->project return project")
	Iterable<Project> getProjects(Person person);
}
