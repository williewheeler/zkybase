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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
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
	private String username;
	private String firstName;
	private String lastName;
	private String title;
	private String workPhone;
	private String mobilePhone;
	private String email;
	
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
	 * @param username username
	 * @param firstName first name
	 * @param lastName last name
	 */
	public Person(String username, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	/**
	 * @return username
	 */
	@NotNull
	@Size(max = 40)
	public String getUsername() { return username; }
	
	/**
	 * @param username username
	 */
	public void setUsername(String username) { this.username = username; }
	
	/**
	 * @return the firstName
	 */
	@NotNull
	@Size(max = 40)
	public String getFirstName() { return firstName; }

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) { this.firstName = firstName; }

	/**
	 * @return the lastName
	 */
	@NotNull
	@Size(max = 40)
	public String getLastName() { return lastName; }

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	/**
	 * @return first and last names
	 */
	public String getFirstNameLastName() {
		return firstName + " " + lastName;
	}
	
	/**
	 * @return title
	 */
	@Size(max = 80)
	public String getTitle() { return title; }
	
	/**
	 * @param title title
	 */
	public void setTitle(String title) { this.title = title; }
	
	/**
	 * @return work phone
	 */
	@Size(max = 20)
	public String getWorkPhone() { return workPhone; }
	
	/**
	 * @param workPhone work phone
	 */
	public void setWorkPhone(String workPhone) { this.workPhone = workPhone; }
	
	/**
	 * @return mobile phone
	 */
	@Size(max = 20)
	public String getMobilePhone() { return mobilePhone; }
	
	/**
	 * @param mobilePhone mobile phone
	 */
	public void setMobilePhone(String mobilePhone) { this.mobilePhone = mobilePhone; }
	
	/**
	 * @return e-mail
	 */
	@Email
	@Size(max = 80)
	public String getEmail() { return email; }
	
	/**
	 * @param email e-mail
	 */
	public void setEmail(String email) { this.email = email; }
	
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Person "
				+ ": username=" + username
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", email=" + email
				+ "]";
	}
}
