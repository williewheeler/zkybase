/* 
 * FarmServiceImpl.java
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

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.model.Instance;
import org.skydingo.skybase.repository.FarmRepository;
import org.skydingo.skybase.service.FarmService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.springframework.stereotype.Service;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class FarmServiceImpl extends AbstractEntityServiceImpl<Farm> implements FarmService {
	@Inject private FarmRepository farmRepo;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.impl.AbstractEntityServiceImpl#findOne(java.lang.Long)
	 */
	@Override
	public Farm findOne(Long id) {
		notNull(id);
		
		// See http://springinpractice.com/2011/12/28/initializing-lazy-loaded-collections-with-spring-data-neo4j/
		Farm farm = super.findOne(id);
		Iterable<Instance> instances = farm.getInstances();
		for (Instance instance : instances) {
			neo4jTemplate.fetch(instance);
		}
		return farm;
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.FarmService#findByApplication(org.skydingo.skybase.model.Application)
	 */
	@Override
	public List<Farm> findByApplication(Application application) {
		notNull(application);
		return CollectionsUtil.asSortedList(farmRepo.findByApplication(application));
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.FarmService#findByEnvironment(org.skydingo.skybase.model.Environment)
	 */
	@Override
	public List<Farm> findByEnvironment(Environment environment) {
		notNull(environment);
		return CollectionsUtil.asSortedList(farmRepo.findByEnvironment(environment));
	}
}
