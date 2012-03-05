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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.neo4j.graphdb.Direction;
import org.skydingo.skybase.model.relationship.ApplicationTeam;
import org.skydingo.skybase.util.CollectionsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * Application CI.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "shortDescription", "modulesAsList", "scm" })
public class Application extends AbstractCI<Application> {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	// FIXME Temporary
	private static final GitHubScm SKYBASE_SCM = new GitHubScm("williewheeler", "skybase");
	
	@Indexed private String name;
	private String shortDescription;
	private GitHubScm scm;
	
	@RelatedTo(type = "APPLICATION_MODULE", direction = Direction.OUTGOING)
	private Set<Module> modules;
	
//	@RelatedToVia(type = "APPLICATION_TEAM", direction = Direction.OUTGOING)
	
	// Don't need the type since the ApplicationTeam already has it.
	@RelatedToVia(direction = Direction.OUTGOING)
	private Set<ApplicationTeam> teams;
	
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
	
	/**
	 * @return
	 */
	public Set<Module> getModules() { return modules; }
	
	/**
	 * @param modules
	 */
	public void setModules(Set<Module> modules) { this.modules = modules; }
	
	/**
	 * @return
	 */
	@XmlElementWrapper(name = "modules")
	@XmlElement(name = "module")
	public List<Module> getModulesAsList() {
		return CollectionsUtil.asSortedList(modules);
	}
	
	/**
	 * @param modules
	 */
	public void setModulesAsList(List<Module> modules) {
		this.modules = new HashSet<Module>(modules);
	}
	
	/**
	 * @return
	 */
	public Iterable<ApplicationTeam> getTeams() { return teams; }
	
	/**
	 * Adds a team to this application.
	 * 
	 * @param team
	 * @param type
	 * @return
	 */
	public ApplicationTeam addTeam(Team team, ApplicationTeam.TeamType type) {
		ApplicationTeam appTeam = new ApplicationTeam(this, team, type);
		log.debug("applicationTeam={}", appTeam);
		teams.add(appTeam);
		return appTeam;
	}
	
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
