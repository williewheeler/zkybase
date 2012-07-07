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
package org.zkybase.cmdb.api.service.impl;

import static org.zkybase.cmdb.api.util.Assert.verifyArgNotNull;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkybase.cmdb.api.repository.ApplicationRepository;
import org.zkybase.cmdb.api.service.ApplicationService;

/**
 * Service bean to support transactional operations. Operations like application deletion require this.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	@Inject private ApplicationRepository applicationRepo;

	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.api.service.ApplicationService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		verifyArgNotNull(id, "id");
		applicationRepo.delete(id);
	}

}
