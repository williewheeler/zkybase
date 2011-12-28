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

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.neo4j.graphdb.Direction;
import org.skydingo.skybase.model.relationship.ProjectMembership;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * Project domain object.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Project extends AbstractEntity<Project> {
	
	// External ID
	@Indexed private String key;
	@Indexed private String name;
	
	private String shortDescription;
	
	@RelatedTo(type = "BUILT_FROM", direction = Direction.INCOMING)
	private Set<Package> packages;
	
//	@RelatedTo(type = "MEMBER_OF", direction = Direction.INCOMING)
//	private Set<Person> members;
	
	// Use Iterable (read-only) for incoming??
	@Fetch
	@RelatedToVia(type = "MEMBER_OF", direction = Direction.INCOMING)
	private Iterable<ProjectMembership> memberships;
	
	public Project() { }
	
	/**
	 * @param key project key
	 * @param name project name
	 */
	public Project(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	/**
	 * Returns the project key, which is globally unique across projects and serves as an external reference.
	 * 
	 * @return project key
	 */
	@NotNull
	@Size(max = 40)
	@Pattern(regexp = "^[\\w\\-]*$")
	@XmlAttribute
	public String getKey() { return key; }
	
	/**
	 * @param key project key
	 */
	public void setKey(String key) { this.key = key; }
	
	/**
	 * Returns the project name.
	 * 
	 * @return project name
	 */
	@NotNull
	@Size(max = 160)
	@XmlElement
	public String getName() { return name; }
	
	/**
	 * Sets the project name.
	 * 
	 * @param name project name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * @return short description
	 */
	@Size(max = 160)
	@XmlElement
	public String getShortDescription() { return shortDescription; }
	
	/**
	 * @param shortDescription short description
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	/**
	 * @return
	 */
	@XmlTransient
	public Set<Package> getPackages() { return packages; }
	
	/**
	 * @return project members
	 */
//	@XmlTransient
//	public Set<Person> getMembers() { return members; }
	
	/**
	 * @param members project members
	 */
//	public void setMembers(Set<Person> members) { this.members = members; }

	/**
	 * @return read-only view of the project memberships
	 */
//	@XmlElementWrapper(name = "memberships")
//	@XmlElement(name = "membership")
	@XmlTransient
	public Iterable<ProjectMembership> getMemberships() { return memberships; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	@XmlTransient
	public String getDisplayName() { return name; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }
		Project that = (Project) o;
		if (getId() == null) { return super.equals(o); }
		return getId().equals(that.getId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (getId() != null ? getId().hashCode() : super.hashCode());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Project"
				+ ": id=" + getId()
				+ ", key=" + key
				+ ", name=" + name
				+ "]";
	}

}
