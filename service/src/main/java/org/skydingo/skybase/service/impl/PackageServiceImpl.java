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

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.exception.DuplicateCIException;
import org.skydingo.skybase.model.Module;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.service.PackageService;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class PackageServiceImpl extends AbstractCIService<Package> implements PackageService {
	@Inject private PackageRepository packageRepo;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.impl.AbstractCIService#getRepository()
	 */
	@Override
	protected GraphRepository<Package> getRepository() {
		return packageRepo;
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.impl.AbstractCIService#create(org.skydingo.skybase.model.CI)
	 */
	@Override
	public void create(Package pkg) {
		notNull(pkg);
		Package duplicatePkg = packageRepo.findByModuleAndVersion(pkg.getModule(), pkg.getVersion());
		if (duplicatePkg != null) {
			throw new DuplicateCIException();
		}
		super.create(pkg);
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.impl.AbstractCIService#create(org.skydingo.skybase.model.CI,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void create(Package pkg, Errors errors) {
		notNull(pkg);
		notNull(errors);
		
		if (!errors.hasErrors()) {
			try {
				create(pkg);
			} catch (DuplicateCIException e) {
				errors.reject("error.package.duplicatePackage");
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PackageService#findByModule(org.skydingo.skybase.model.Module)
	 */
	@Override
	public List<Package> findByModule(Module module) {
		return packageRepo.findByModule(module);
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PackageService#findByModuleAndVersion(org.skydingo.skybase.model.Module, java.lang.String)
	 */
	@Override
	public Package findByModuleAndVersion(Module module, String version) {
		return packageRepo.findByModuleAndVersion(module, version);
	}
}
