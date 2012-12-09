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
package org.zkybase.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;
import org.zkybase.exception.DuplicateCIException;
import org.zkybase.model.Module;
import org.zkybase.model.Package;
import org.zkybase.repository.PackageRepository;
import org.zkybase.service.PackageService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class PackageServiceImpl extends AbstractCIService<Package> implements PackageService {
	@Inject private PackageRepository packageRepo;
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractCIService#getRepository()
	 */
	@Override
	protected GraphRepository<Package> getRepository() {
		return packageRepo;
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.service.impl.AbstractCIService#checkForDuplicate(org.zkybase.model.CI)
	 */
	@Override
	protected void checkForDuplicate(Package pkg) {
		notNull(pkg);
		Package duplicate = packageRepo.findByModuleAndVersion(pkg.getModule(), pkg.getVersion());
		if (duplicate != null) {
			throw new DuplicateCIException(pkg);
		}
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.PackageService#findByModule(org.zkybase.model.Module)
	 */
	@Override
	public List<Package> findByModule(Module module) {
		return packageRepo.findByModule(module);
	}

	/* (non-Javadoc)
	 * @see org.zkybase.service.PackageService#findByModuleAndVersion(org.zkybase.model.Module, java.lang.String)
	 */
	@Override
	public Package findByModuleAndVersion(Module module, String version) {
		return packageRepo.findByModuleAndVersion(module, version);
	}
}
