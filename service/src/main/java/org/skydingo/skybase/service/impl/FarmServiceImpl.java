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

import org.skydingo.skybase.model.DataCenter;
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.service.FarmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class FarmServiceImpl extends AbstractEntityServiceImpl<Farm> implements FarmService {

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.FarmService#create(org.skydingo.skybase.model.Farm)
	 */
	@Override
	public void create(Farm farm) {
		notNull(farm);
		
		// TODO Check for duplicates?
		
		getRepository().save(farm);
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.FarmService#create(org.skydingo.skybase.model.Farm,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void create(Farm farm, Errors errors) {
		notNull(farm);
		notNull(errors);
		
		// Haven't figured out how to do this with the framework, so I'm doing it manually for now.
		
		// First check for null.
		Environment environment = farm.getEnvironment();
		if (environment == null || environment.getId() == -1L) {
			// FIXME This code doesn't work.
//			errors.rejectValue("environment", "javax.validation.constraints.NotNull");
			throw new RuntimeException("Value rejected");
		} else {
			// TODO Check for existence
		}
		
		DataCenter dataCenter = farm.getDataCenter();
		if (dataCenter == null || dataCenter.getId() == -1L) {
			// FIXME This code doesn't work.
//			errors.rejectValue("dataCenter", "javax.validation.constraints.NotNull");
			throw new RuntimeException("Value rejected");
		} else {
			// TODO Check for existence
		}
		
		if (!errors.hasErrors()) {
			create(farm);
		}
	}

}
