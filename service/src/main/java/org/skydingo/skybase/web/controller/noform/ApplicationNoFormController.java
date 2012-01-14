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

import javax.inject.Inject;

import org.skydingo.skybase.integrations.github.model.Watcher;
import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.repository.ApplicationRepository;
import org.skydingo.skybase.service.ApplicationService;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.web.controller.AbstractEntityNoFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/applications")
public class ApplicationNoFormController extends AbstractEntityNoFormController<Application> {
	private static final Logger log = LoggerFactory.getLogger(ApplicationNoFormController.class);
	
	@Inject private ApplicationRepository repository;
	@Inject private ApplicationService service;
	@Inject private RestTemplate restTemplate;
	
	public GraphRepository<Application> getRepository() { return repository; }
	
	public EntityService<Application> getService() { return service; }
	
	@RequestMapping(value = "/{id}/scm", method = RequestMethod.GET)
	public String getScmPage(@PathVariable Long id, Model model) {
		Application app = repository.findOne(id);
		
		// FIXME Currently assuming that the project is a GitHub project, which of course we don't want to do.
		log.debug("Loading GitHub repo watchers");
		try {
			Watcher[] watchers = restTemplate.getForObject(
					"https://api.github.com/repos/{user}/{repo}/watchers",
					Watcher[].class,
					"williewheeler",
					"skybase");
			log.debug("Loaded GitHub repo watchers: {}", watchers.length);
			model.addAttribute(app);
			model.addAttribute("watcherList", watchers);
			return "applicationScm";
			
		} catch (HttpMessageNotReadableException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
