/* 
 * PackageRepository.java
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

import java.util.List;

import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.model.Project;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface PackageRepository extends GraphRepository<Package> {
	
	/**
	 * @param groupId
	 * @param packageId
	 * @param version
	 * @return
	 */
	// http://stackoverflow.com/questions/8032979/cypher-query-to-get-nodes-with-given-property-values
	List<Package> findByGroupIdAndPackageIdAndVersion(String groupId, String packageId, String version);
	
	/**
	 * Returns the packages for the given project.
	 * 
	 * @param project project
	 * @return packages for the given project
	 */
	@Query("start project=node({0}) match package-->project return package")
	Iterable<Package> findPackagesByProject(Project project);
}
