/* 
 * PackageServiceImpl.java
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

import javax.inject.Inject;

import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.service.PackageService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class PackageServiceImpl implements PackageService {
	@Inject private PackageRepository packageRepo;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PackageService#createPackage(java.lang.Package, org.springframework.validation.Errors)
	 */
	@Override
	public void createPackage(Package pkg, Errors errors) {
		if (!errors.hasErrors()) {
			// TODO Check for duplicates
			packageRepo.save(pkg);
		}
	}

}
