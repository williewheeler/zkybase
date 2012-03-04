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
package org.skydingo.skybase.service;

import java.util.List;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.Module;
import org.skydingo.skybase.model.Team;
import org.skydingo.skybase.model.relationship.ApplicationTeam;
import org.springframework.social.github.api.GitHubHook;
import org.springframework.validation.Errors;

/**
 * Application service.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface ApplicationService extends CIService<Application> {
	
	/**
	 * Finds an application with its modules eagerly loaded.
	 * 
	 * @param id application ID
	 * @return application with modules
	 */
	Application findOneWithModules(Long id);
	
	Application findOneWithTeams(Long id);
	
	Application findOneWithScm(Long id);
	
	Application findOneWithCollaborators(Long id);
	
	Application findOneWithCommits(Long id);
	
	Application findOneWithWatchers(Long id);
	
	List<GitHubHook> findHooks(String user, String repo);
	
	/**
	 * @param id application ID
	 * @param module
	 * @param errors
	 */
	void addModule(Long id, Module module, Errors errors);
	
	/**
	 * @param application
	 * @param team
	 * @param type
	 */
	void addTeam(Application application, Team team, ApplicationTeam.TeamType type);
}
