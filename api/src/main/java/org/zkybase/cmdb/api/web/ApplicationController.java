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
package org.zkybase.cmdb.api.web;

import static org.zkybase.cmdb.util.Assert.isNull;
import static org.zkybase.cmdb.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkybase.cmdb.api.domain.ApplicationEntity;
import org.zkybase.cmdb.api.mapper.ApplicationMapper;
import org.zkybase.cmdb.api.repository.ApplicationRepository;
import org.zkybase.cmdb.api.service.ApplicationService;
import org.zkybase.cmdb.api.util.UriResolver;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/applications")
public class ApplicationController {
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
	
	@Inject private ApplicationRepository applicationRepo;
	@Inject private ApplicationService applicationService;
	@Inject private ApplicationMapper applicationMapper;
	@Inject private UriResolver resolver;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	public void post(@RequestBody Application application, HttpServletResponse response) {
		notNull(application, "application");
		notNull(response, "response");
		isNull(application.getId(), "application.id");
		
		ApplicationEntity entity = applicationMapper.toEntity(application);
		applicationRepo.save(entity);
		
		// Entity now has an ID, so we can set it on the DTO.
		Long id = entity.getId();
		application.setId(id);
		
		String uri = resolver.resolve(ApplicationEntity.class, id);
		log.debug("Adding header: Location={}", uri);
		response.addHeader("Location", uri);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Application> getAll() {
		log.debug("Getting all applications");
		return applicationMapper.toDtoList(applicationRepo.findAll());
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Application get(@PathVariable Long id) {
		notNull(id, "id");
		return applicationMapper.toDto(applicationRepo.findOne(id));
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json")
	public void put(@PathVariable Long id, @RequestBody Application application, HttpServletResponse response) {
		notNull(id, "id");
		notNull(application, "application");
		notNull(response, "response");
		
		ApplicationEntity entity = applicationRepo.findOne(id);
		
		// Merge DTO into entity. Eventually this will be more generic.
		entity.setName(application.getName());
		
		applicationService.update(entity);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		notNull(id, "id");
		notNull(response, "response");
		applicationService.delete(id);
	}
}
