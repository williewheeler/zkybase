/* 
 * ApplicationServiceImpl.java
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
package org.skydingo.skybase.service.impl;

import static org.springframework.util.Assert.notNull;

import javax.inject.Inject;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.GitHubScm;
import org.skydingo.skybase.repository.ApplicationRepository;
import org.skydingo.skybase.service.ApplicationService;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubRepo;
import org.springframework.stereotype.Service;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ApplicationServiceImpl extends AbstractEntityServiceImpl<Application> implements ApplicationService {
	@Inject private ApplicationRepository applicationRepository;
	@Inject private GitHub gitHub;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.ApplicationService#findOneWithScm(java.lang.Long)
	 */
	@Override
	public Application findOneWithScm(Long id) {
		notNull(id);
		
		// Main data comes from app repo
		Application app = applicationRepository.findOne(id);
		GitHubScm scm = app.getScm();
		
		// Supporting data comes from GitHub
		// FIXME This web service call shouldn't be part of the transaction
		GitHubRepo repo = gitHub.repoOperations().getRepo(scm.getUser(), scm.getRepo());
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
	 * @see org.skydingo.skybase.service.ApplicationService#findOneWithCollaborators(java.lang.Long)
	 */
	@Override
	public Application findOneWithCollaborators(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.ApplicationService#findOneWithCommits(java.lang.Long)
	 */
	@Override
	public Application findOneWithCommits(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.ApplicationService#findOneWithWatchers(java.lang.Long)
	 */
	@Override
	public Application findOneWithWatchers(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
