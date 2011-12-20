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

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.exception.DuplicateEntityException;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class PackageServiceImpl implements PackageService {
	@Inject private PackageRepository packageRepo;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PackageService#createPackage(org.skydingo.skybase.model.Package)
	 */
	@Override
	public void createPackage(Package pkg) {
		notNull(pkg);
		
		List<Package> duplicates =
			packageRepo.findByGroupIdAndPackageIdAndVersion(pkg.getGroupId(), pkg.getPackageId(), pkg.getVersion());
		
		if (!duplicates.isEmpty()) {
			throw new DuplicateEntityException();
		}
		
		packageRepo.save(pkg);
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PackageService#createPackage(java.lang.Package, org.springframework.validation.Errors)
	 */
	@Override
	public void createPackage(Package pkg, Errors errors) {
		notNull(pkg);
		notNull(errors);
		
		if (!errors.hasErrors()) {
			try {
				createPackage(pkg);
			} catch (DuplicateEntityException e) {
				errors.reject("error.package.duplicatePackage");
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PackageService#findPackages()
	 */
	@Override
	public List<Package> findPackages() {
		return CollectionsUtil.asSortedList(packageRepo.findAll());
	}
}
