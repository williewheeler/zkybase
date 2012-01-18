/* 
 * FarmRepository.java
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

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface FarmRepository extends GraphRepository<Farm> {
	
	/**
	 * Implementation note: shouldn't need to use a Cypher query here, but Spring Data Neo4j isn't generating the
	 * implementation here. See http://groups.google.com/group/neo4j/browse_thread/thread/606565b163ee3d21
	 * 
	 * @param environment environment
	 * @return farms in the environment
	 */
	@Query("start environment=node({0}) match environment<-[:IN_ENVIRONMENT]-farm return farm") 	
	Iterable<Farm> findByEnvironment(Environment environment);
	
	/**
	 * @param application application
	 * @return farms supporting the application
	 */
	@Query("start application=node({0}) match application<-[:FARM_SUPPORTS_APPLICATION]-farm return farm")
	Iterable<Farm> findByApplication(Application application);
}
