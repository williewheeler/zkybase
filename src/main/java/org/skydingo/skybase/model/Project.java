/* 
 * Project.java
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
package org.skydingo.skybase.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.skydingo.skybase.model.relationship.BuiltFrom;
import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Project domain object.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public class Project {
	
	// Internal node ID
	@GraphId private Long nodeId;
	
	// External ID
	@Indexed private String id;
	
	private String name;
	
	@RelatedTo(type = "MEMBER_OF", direction = Direction.INCOMING)
	private Set<Person> members = new HashSet<Person>();
	
//	@RelatedToVia(type = "MEMBER_OF", direction = Direction.INCOMING)
//	private Iterable<ProjectMembership> memberships;
	
	@RelatedTo(type = "BUILT_FROM", direction = Direction.INCOMING)
	private Set<Package> packages = new HashSet<Package>();
	
//	@RelatedToVia(type = "BUILT_FROM", direction = Direction.INCOMING)
//	private Iterable<BuiltFrom> builtFroms;
	
	public Project() { }
	
	/**
	 * @param id project ID
	 * @param name project name
	 */
	public Project(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() { return id; }
	
	public void setId(String id) { this.id = id; }
	
	/**
	 * Returns the project name.
	 * 
	 * @return project name
	 */
	public String getName() { return name; }
	
	/**
	 * Sets the project name.
	 * 
	 * @param name project name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * @return project members
	 */
	public Set<Person> getMembers() { return members; }
	
	/**
	 * @param members project members
	 */
	public void setMembers(Set<Person> members) { this.members = members; }

//	public Iterable<ProjectMembership> getMemberships() { return memberships; }
	
	/**
	 * @return packages built from this project
	 */
	public Set<Package> getPackages() { return packages; }
	
	/**
	 * @param packages packages built from this project
	 */
	public void setPackages(Set<Package> packages) { this.packages = packages; }
	
//	public Iterable<BuiltFrom> getBuiltFroms() { return builtFroms; }
}
