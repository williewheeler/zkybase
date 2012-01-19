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

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Email;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * Person entity.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
public class Person extends AbstractEntity<Person> {
	
	// TODO Figure out why it's not good enough to put @Indexed here without the index name or type.
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "searchByUsername")
	private String username;
	
//	@Indexed(indexType = IndexType.FULLTEXT, indexName = "searchByFirstName")
	private String firstName;
	
//	@Indexed(indexType = IndexType.FULLTEXT, indexName = "searchByLastName")
	private String lastName;
	
	private String title;
	private String workPhone;
	private String mobilePhone;
	private String email;
	
	// FIXME Don't allow graph cycles here
	@Fetch
	@RelatedTo(type = "REPORTS_TO")
	private Person manager;
	
	@RelatedTo(type = "REPORTS_TO", direction = Direction.INCOMING)
	private Set<Person> directReports;
	
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
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * @return username
	 */
	@NotNull
	@Size(max = 40)
	@Pattern(regexp = "^[\\w\\-]*$")
	@XmlElement
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
	@XmlElement
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
	@XmlElement
	public String getLastName() { return lastName; }

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	/**
	 * @return first and last names
	 */
	@XmlTransient
	public String getFirstNameLastName() {
		return firstName + " " + lastName;
	}
	
	/**
	 * @return title
	 */
	@Size(max = 80)
	@XmlElement
	public String getTitle() { return title; }
	
	/**
	 * @param title title
	 */
	public void setTitle(String title) { this.title = title; }
	
	/**
	 * @return work phone
	 */
	@Size(max = 20)
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$")
	@XmlElement
	public String getWorkPhone() { return workPhone; }
	
	/**
	 * @param workPhone work phone
	 */
	public void setWorkPhone(String workPhone) { this.workPhone = workPhone; }
	
	/**
	 * @return mobile phone
	 */
	@Size(max = 20)
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$")
	@XmlElement
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
	@XmlElement
	public String getEmail() { return email; }
	
	/**
	 * @param email e-mail
	 */
	public void setEmail(String email) { this.email = email; }
	
	/**
	 * @return
	 */
	public Person getManager() { return manager; }
	
	/**
	 * @param manager
	 */
	public void setManager(Person manager) { this.manager = manager; }
	
	/**
	 * @return
	 */
	public Set<Person> getDirectReports() { return directReports; }
	
	/**
	 * @param directReports
	 */
	public void setDirectReports(Set<Person> directReports) { this.directReports = directReports; }
		
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	@XmlTransient
	public String getDisplayName() { return getFirstNameLastName(); }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }
		Person that = (Person) o;
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
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Person that) {
		int comp = getFirstNameLastName().compareTo(that.getFirstNameLastName());
		return (comp == 0 ? getUsername().compareTo(that.getUsername()) : comp);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Person"
				+ ": id=" + getId()
				+ ", username=" + username
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", email=" + email
				+ "]";
	}
	
	@XmlRootElement(name = "people")
	public static class PersonListWrapper implements ListWrapper<Person> {
		private List<Person> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "person")
		public List<Person> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Person> list) { this.list = list; }
	}
}
