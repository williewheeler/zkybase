/* 
 * PackageService.java
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
package org.skydingo.skybase.service;

import java.util.List;

import org.skydingo.skybase.exception.DuplicateEntityException;
import org.skydingo.skybase.model.Package;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface PackageService {
	
	/**
	 * @param pkg
	 * @throws DuplicateEntityException if the package already exists
	 */
	void createPackage(Package pkg);
	
	/**
	 * @param pkg package to create
	 * @param errors errors
	 */
	void createPackage(Package pkg, Errors errors);
	
	/**
	 * @return all packages, sorted
	 */
	List<Package> findPackages();
}
