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
package org.zkybase.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubHook;
import org.springframework.social.github.api.GitHubRepo;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.zkybase.model.Application;
import org.zkybase.model.GitHubScm;
import org.zkybase.model.Module;
import org.zkybase.model.Team;
import org.zkybase.model.relationship.ApplicationTeam;
import org.zkybase.model.relationship.ApplicationTeam.TeamType;
import org.zkybase.repository.ApplicationRepository;
import org.zkybase.service.ApplicationService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ApplicationServiceImpl extends AbstractCIService<Application> implements ApplicationService {
	private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	
	@Inject private ApplicationRepository applicationRepository;
	@Inject private Neo4jTemplate template;
//	@Inject private GitHub gitHub;
	@Inject private ConnectionRepository connectionRepo;
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractCIService#getRepository()
	 */
	@Override
	protected GraphRepository<Application> getRepository() {
		return applicationRepository;
	}
	
	/**
	 * Returns the application, with modules loaded.
	 */
	@Override
	public Application findOne(Long id) {
		notNull(id);
		Application app = applicationRepository.findOne(id);
		
		// Clear out the modules so they don't appear in the web service call.
		app.setModules(null);
		
		return app;
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findOneWithModules(java.lang.Long)
	 */
	@Override
	public Application findOneWithModules(Long id) {
		notNull(id);
		Application app = applicationRepository.findOne(id);
		
		// For now this is how you do it.
		// http://stackoverflow.com/questions/8218864/fetch-annotation-in-sdg-2-0-fetching-strategy-questions
		// http://springinpractice.com/2011/12/28/initializing-lazy-loaded-collections-with-spring-data-neo4j/
		template.fetch(app.getModules());
		
		return app;
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findOneWithTeams(java.lang.Long)
	 */
	@Override
	public Application findOneWithTeams(Long id) {
		notNull(id);
		Application app = applicationRepository.findOne(id);
		Iterable<ApplicationTeam> appTeams = app.getTeams();
		for (ApplicationTeam appTeam : appTeams) {
			template.fetch(appTeam.getTeam());
		}
		return app;
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findOneWithScm(java.lang.Long)
	 */
	@Override
	public Application findOneWithScm(Long id) {
		notNull(id);
		
		// Main data comes from app repo
		Application app = applicationRepository.findOne(id);
		GitHubScm scm = app.getScm();
		
		// Supporting data comes from GitHub
		// FIXME This web service call shouldn't be part of the transaction
		GitHubRepo repo = gitHub().repoOperations().getRepo(scm.getUser(), scm.getRepo());
		scm.setId(repo.getId());
		scm.setName(repo.getName());
		scm.setDescription(repo.getDescription());
		scm.setUrl(repo.getUrl());
		scm.setHtmlUrl(repo.getHtmlUrl());
		scm.setGitUrl(repo.getGitUrl());
		scm.setSshUrl(repo.getSshUrl());
		scm.setSvnUrl(repo.getSvnUrl());
		
		return app;
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findOneWithCollaborators(java.lang.Long)
	 */
	@Override
	public Application findOneWithCollaborators(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findOneWithCommits(java.lang.Long)
	 */
	@Override
	public Application findOneWithCommits(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findOneWithWatchers(java.lang.Long)
	 */
	@Override
	public Application findOneWithWatchers(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#findHooks(java.lang.String, java.lang.String)
	 */
	@Override
	public List<GitHubHook> findHooks(String user, String repo) {
		notNull(user);
		notNull(repo);
		return gitHub().repoOperations().getHooks(user, repo);
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#addModule
	 * (java.lang.Long, org.zkybase.model.Module, org.springframework.validation.Errors)
	 */
	@Override
	public void addModule(Long id, Module module, Errors errors) {
		Application app = findOneWithModules(id);
		app.getModules().add(module);
		update(app, errors);
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.ApplicationService#addTeam(org.zkybase.model.Application,
	 * org.zkybase.model.Team, org.zkybase.model.relationship.ApplicationTeam.TeamType)
	 */
	@Override
	public void addTeam(Application application, Team team, TeamType type) {
		notNull(application);
		notNull(team);
		notNull(type);
		
		ApplicationTeam appTeam = application.addTeam(team, type);
		
		// This allows duplicates to be created.
//		template.save(appTeam);
		
		// This filters out duplicates because the application stores the relationships in a set.
		template.save(application);
	}
	
	private GitHub gitHub() {
		Connection<GitHub> conn = connectionRepo.findPrimaryConnection(GitHub.class);
		log.debug("Found connection: displayName={}, hasExpired={}", conn.getDisplayName(), conn.hasExpired());
		return (conn != null ? conn.getApi() : new GitHubTemplate());
	}
}
