/* 
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Application entity.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "shortDescription", "scm" })
public class Application extends AbstractCI<Application> {
	
	// FIXME Temporary
	private static final GitHubScm SKYBASE_SCM = new GitHubScm("williewheeler", "skybase");
	
	private String name;
	private String shortDescription;
	private GitHubScm scm;
	
	public Application() { }
	
	public Application(Long id) { setId(id); }
	
	/**
	 * @return
	 */
	@NotNull
	@Size(max = 80)
	@XmlElement
	public String getName() { return name; }
	
	/**
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * @return
	 */
	@Size(max = 200)
	@XmlElement
	public String getShortDescription() { return shortDescription; }
	
	/**
	 * @param shortDescription
	 */
	public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
	
	/**
	 * @return
	 */
	@Valid
	@XmlElement
	public GitHubScm getScm() {
		// FIXME Temporary
		return SKYBASE_SCM;
	}
	
	/**
	 * @param scm
	 */
	public void setScm(GitHubScm scm) { this.scm = scm; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }

	@XmlRootElement(name = "applications")
	public static class ApplicationListWrapper implements ListWrapper<Application> {
		private List<Application> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "application")
		public List<Application> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Application> list) { this.list = list; }
	}
}
