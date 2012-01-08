/* 
 * AbstractEntityService.java
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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Transactional
public abstract class AbstractEntityServiceImpl<T extends Entity<T>> implements EntityService<T> {
	
	// IMPORTANT: Only getEntityClass() should access this directly!
	private Class<T> entityClass;
	
	private GraphRepository<T> repository;
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (this.entityClass == null) {
			ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
			this.entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	/**
	 * @return
	 */
	protected GraphRepository<T> getRepository() { return repository; }
	
	/**
	 * @param repository
	 */
	@Inject
	@SuppressWarnings("unchecked")
	protected void setRepository(List<GraphRepository<?>> repositories) {
		
		// FIXME This doesn't feel like a great implementation.
		String matchName = getEntityClass().getSimpleName() + "Repository";
		
		for (GraphRepository<?> repo : repositories) {
			Type[] types = repo.getClass().getGenericInterfaces();
			for (Type type : types) {
				
				// FIXME Nasty
				if (type.toString().endsWith(matchName)) {
					this.repository = (GraphRepository<T>) repo;
					return;
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.EntityService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return CollectionsUtil.asSortedList(getRepository().findAll());
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.EntityService#findOne(java.lang.Long)
	 */
	@Override
	public T findOne(Long id) {
		return getRepository().findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.EntityService#delete(org.skydingo.skybase.model.Entity)
	 */
	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.EntityService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		getRepository().delete(id);
	}
}
