/* 
 * Person.java
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

import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Person domain object.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public class Person {
	@GraphId private Long id;
	private String firstName;
	private String lastName;
	
	@RelatedTo(type = "MEMBER_OF")
	private Set<Project> projects = new HashSet<Project>();
	
	/**
	 * Creates a new person.
	 */
	public Person() {
	}
	
	/**
	 * Creates a person with the given first name and last name.
	 * 
	 * @param firstName first name
	 * @param lastName last name
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return projects of which this person is a member
	 */
	public Set<Project> getProjects() { return projects; }
	
	/**
	 * @param projects projects of which this person is a member
	 */
	public void setProjects(Set<Project> projects) { this.projects = projects; }
	
	public ProjectMembership memberOf(Project project, String role) {
		ProjectMembership membership = new ProjectMembership(project, this, role);
		project.getMembers().add(this);
		return membership;
	}
}
