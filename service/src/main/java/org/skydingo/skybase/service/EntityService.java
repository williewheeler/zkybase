/*
 * EntityService.java
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

import org.skydingo.skybase.model.Entity;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface EntityService<T extends Entity<T>> {

	/**
	 * @param entity
	 * @param errors
	 */
	void create(T entity, Errors errors);

	/**
	 * @return
	 */
	List<T> findAll();

	T findOne(Long id);

	/**
	 * Updates the entity if there aren't any errors. Can potentially generate new errors (e.g. constraint violations).
	 *
	 * @param entity entity to update
	 * @param errors errors object
	 */
	void update(T entity, Errors errors);

	/**
	 * @param entity entity to delete
	 */
	void delete(T entity);

	void delete(Long id);
}
