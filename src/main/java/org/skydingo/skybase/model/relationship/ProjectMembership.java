/* 
 * ProjectMembership.java
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
package org.skydingo.skybase.model.relationship;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Project;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RelationshipEntity(type = "MEMBER_OF")
@XmlAccessorType(XmlAccessType.NONE)
public class ProjectMembership {
	@GraphId private Long id;
	@Fetch @StartNode private Person person;
	@EndNode private Project project;
	private String role;
	
	/**
	 * 
	 */
	public ProjectMembership() { }
	
	/**
	 * @param person
	 * @param project
	 * @param role
	 */
	public ProjectMembership(Person person, Project project, String role) {
		this.person = person;
		this.project = project;
		this.role = role;
	}

	/**
	 * @return the person
	 */
	@XmlElement
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the project
	 */
	@JsonIgnore
	@XmlTransient
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the role
	 */
	@XmlElement
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("[ProjectMembership: project=%s, person=%s, role=%]", project, person, role);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ProjectMembership that = (ProjectMembership) o;
		if (id == null) {
			return super.equals(o);
		}
		return id.equals(that.id);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : super.hashCode();
	}

}
