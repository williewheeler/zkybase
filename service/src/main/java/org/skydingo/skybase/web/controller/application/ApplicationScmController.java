/* 
 * ApplicationScmController.java
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
package org.skydingo.skybase.web.controller.application;

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.GitHubScm;
import org.skydingo.skybase.repository.ApplicationRepository;
import org.skydingo.skybase.service.ApplicationService;
import org.skydingo.skybase.web.controller.AbstractEntityController;
import org.skydingo.skybase.web.controller.WebUtil;
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
public class ApplicationScmController extends AbstractEntityController<Application> {
	@Inject private ApplicationRepository applicationRepository;
	@Inject private ApplicationService applicationService;
	@Inject private GitHub gitHub;
	
	/**
	 * @param id application ID
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/{id}/scm", method = RequestMethod.GET)
	public String getScm(@PathVariable Long id, Model model) {
		Application app = applicationService.findOneWithScm(id);
		model.addAttribute(app);
		model.addAttribute("entity", app);
		return addNavigation(model, "applicationScm");
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/scm/collaborators", method = RequestMethod.GET)
	public String getCollaborators(@PathVariable Long id, Model model) {
		Application app = applicationRepository.findOne(id);
		
		// FIXME Currently assuming GitHub.
		GitHubScm scm = (GitHubScm) app.getScm();
		
		// TODO Would like to put this inside GitHubScm, but don't want to use Spring Social GitHub as the data model
		// here since that is intended more for data transfer. We need to control the OJM/OXM here, and don't want
		// changes to the Spring Social GitHub API to produce changes to our web service API. But I'm not necessarily
		// excited about implementing that entire data model here. Considering options.
		List<GitHubUser> collaborators = gitHub.repoOperations().getCollaborators(scm.getUser(), scm.getRepo());
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
	@RequestMapping(value = "/{id}/scm/commits", method = RequestMethod.GET)
	public String getCommits(@PathVariable Long id, Model model) {
		Application app = applicationRepository.findOne(id);
		
		// FIXME Currently assuming GitHub.
		GitHubScm scm = (GitHubScm) app.getScm();
		List<GitHubCommit> commits = gitHub.repoOperations().getCommits(scm.getUser(), scm.getRepo());
		
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
	@RequestMapping(value = "/{id}/scm/watchers", method = RequestMethod.GET)
	public String getWatchers(@PathVariable Long id, Model model) {
		Application app = applicationRepository.findOne(id);
		
		// FIXME Currently assuming GitHub.
		GitHubScm scm = (GitHubScm) app.getScm();
		String user = scm.getUser();
		String repo = scm.getRepo();
		List<GitHubUser> watchers = gitHub.repoOperations().getWatchers(user, repo);
		List<List<GitHubUser>> watcherRows = WebUtil.toRows(watchers, 3);
		
		model.addAttribute(app);
		model.addAttribute("entity", app);
		model.addAttribute("watcherList", watchers);
		model.addAttribute("watcherRows", watcherRows);
		
		return addNavigation(model, "applicationScmWatchers");
	}
}
