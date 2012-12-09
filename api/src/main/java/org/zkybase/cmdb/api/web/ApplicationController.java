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

import javax.inject.Inject;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkybase.cmdb.api.domain.ApplicationEntity;
import org.zkybase.cmdb.api.mapper.ApplicationMapper;
import org.zkybase.cmdb.api.mapper.Mapper;
import org.zkybase.cmdb.api.repository.ApplicationRepository;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/applications")
public class ApplicationController extends AbstractController<ApplicationEntity, Application> {
	@Inject private ApplicationRepository applicationRepo;
	@Inject private ApplicationMapper applicationMapper;
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.api.web.AbstractController#getService()
	 */
	@Override
	public GraphRepository<ApplicationEntity> getRepo() { return applicationRepo; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.api.web.AbstractController#getMapper()
	 */
	@Override
	public Mapper<ApplicationEntity, Application> getMapper() { return applicationMapper; }
}
