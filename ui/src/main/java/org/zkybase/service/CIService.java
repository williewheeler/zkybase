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
package org.zkybase.service;

import java.util.List;

import org.springframework.validation.Errors;
import org.zkybase.model.CI;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface CIService<T extends CI<T>> {
	
	/**
	 * @param ci
	 * @throws DuplicateCIException if the CI already exists
	 */
	void create(T ci);
	
	/**
	 * @param ci
	 * @param errors
	 * @throws IllegalArgumentException if either argument is null
	 */
	void create(T ci, Errors errors);
	
	/**
	 * @return
	 */
	List<T> findAll();
	
	T findOne(Long id);
	
	void update(T ci);
	
	/**
	 * Updates the CI if there aren't any errors. Can potentially generate new errors (e.g. constraint violations).
	 * 
	 * @param ci CI to update
	 * @param errors errors object
	 */
	void update(T ci, Errors errors);
	
	/**
	 * @param ci CI to delete
	 */
	void delete(T ci);
	
	void delete(Long id);
}
