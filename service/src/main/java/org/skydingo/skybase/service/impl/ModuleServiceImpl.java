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
package org.skydingo.skybase.service.impl;

import static org.springframework.util.Assert.notNull;

import javax.inject.Inject;

import org.skydingo.skybase.model.Module;
import org.skydingo.skybase.repository.ModuleRepository;
import org.skydingo.skybase.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ModuleServiceImpl extends AbstractCIService<Module> implements ModuleService {
	private static final Logger log = LoggerFactory.getLogger(ModuleServiceImpl.class);
	
	@Inject private ModuleRepository moduleRepository;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.ModuleService#findByGroupIdAndModuleId(java.lang.String, java.lang.String)
	 */
	@Override
	public Module findByGroupIdAndModuleId(String groupId, String moduleId) {
		notNull(groupId);
		notNull(moduleId);
		
		log.debug("Finding module for groupId={}, moduleId={}", groupId, moduleId);
		return moduleRepository.findByGroupIdAndModuleId(groupId, moduleId);
		
//		int numModules = modules.size();
//		log.debug("Found {} modules", numModules);
//		
//		for (Module module : modules) {
//			log.debug("Found module: {}", module);
//		}
//		
//		switch (numModules) {
//		case 0:
//			return null;
//		case 1:
//			return modules.iterator().next();
//		default:
//			throw new RuntimeException("Expected one module; found " + numModules);
//		}
	}
}
