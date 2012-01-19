/* 
 * ApplicationNoFormController.java
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
package org.skydingo.skybase.web.controller.noform;

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.repository.ApplicationRepository;
import org.skydingo.skybase.repository.FarmRepository;
import org.skydingo.skybase.service.ApplicationService;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.skydingo.skybase.web.controller.AbstractEntityNoFormController;
import org.skydingo.skybase.web.controller.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubCommit;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/applications")
public class ApplicationNoFormController extends AbstractEntityNoFormController<Application> {
	private static final Logger log = LoggerFactory.getLogger(ApplicationNoFormController.class);
	
	private static final String USER = "williewheeler";
	private static final String REPO = "skybase";
	
	@Inject private ApplicationRepository applicationRepository;
	@Inject private ApplicationService applicationService;
	@Inject private FarmRepository farmRepository;
	@Inject private GitHub gitHub;
	
	public GraphRepository<Application> getRepository() { return applicationRepository; }
	
	public EntityService<Application> getService() { return applicationService; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityNoFormController#doGetDetails(java.lang.Long, org.springframework.ui.Model)
	 */
	@Override
	protected Application doGetDetails(Long id, Model model) {
		log.debug("Finding application");
		Application app = getService().findOne(id);
		log.debug("Finding farms");
		model.addAttribute(CollectionsUtil.asSortedList(farmRepository.findByApplication(app)));
		return app;
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/collaborators", method = RequestMethod.GET)
	public String getScmCollaboratorsPage(@PathVariable Long id, Model model) {
		Application app = applicationRepository.findOne(id);
		
		// FIXME Currently assuming that the project is a GitHub project, which of course we don't want to do.
		List<GitHubUser> collaborators = gitHub.repoOperations().getCollaborators(USER, REPO);
		List<List<GitHubUser>> collaboratorRows = WebUtil.toRows(collaborators, 3);
		
		model.addAttribute(app);
		model.addAttribute("entity", app);
		model.addAttribute("collaboratorList", collaborators);
		model.addAttribute("collaboratorRows", collaboratorRows);
		
		return addNavigation(model, "applicationScmCollaborators");
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/commits", method = RequestMethod.GET)
	public String getScmCommitsPage(@PathVariable Long id, Model model) {
		Application app = applicationRepository.findOne(id);
		
		// FIXME Currently assuming that the project is a GitHub project, which of course we don't want to do.
		List<GitHubCommit> commits = gitHub.repoOperations().getCommits(USER, REPO);
		
		model.addAttribute(app);
		model.addAttribute("entity", app);
		model.addAttribute("commitList", commits);
		
		return addNavigation(model, "applicationScmCommits");
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/watchers", method = RequestMethod.GET)
	public String getScmWatchersPage(@PathVariable Long id, Model model) {
		Application app = applicationRepository.findOne(id);
		
		// FIXME Currently assuming that the project is a GitHub project, which of course we don't want to do.
		List<GitHubUser> watchers = gitHub.repoOperations().getWatchers(USER, REPO);
		List<List<GitHubUser>> watcherRows = WebUtil.toRows(watchers, 3);
		
		model.addAttribute(app);
		model.addAttribute("entity", app);
		model.addAttribute("watcherList", watchers);
		model.addAttribute("watcherRows", watcherRows);
		
		return addNavigation(model, "applicationScmWatchers");
	}
}
