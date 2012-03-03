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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.exception.NoSuchCIException;
import org.skydingo.skybase.model.CI;
import org.skydingo.skybase.service.CIService;
import org.skydingo.skybase.util.CollectionsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Transactional
public abstract class AbstractCIService<T extends CI<T>> implements CIService<T> {
	private static final Logger log = LoggerFactory.getLogger(AbstractCIService.class);
	
	@Inject protected Neo4jTemplate neo4jTemplate;
	
	// IMPORTANT: Only getCiClass() should access this directly!
	private Class<T> ciClass;
	
	private GraphRepository<T> repository;
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getCiClass() {
		if (this.ciClass == null) {
			ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
			this.ciClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
		return ciClass;
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
		String matchName = getCiClass().getSimpleName() + "Repository";
		
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
	 * @see org.skydingo.skybase.service.CIService#create(org.skydingo.skybase.model.CI)
	 */
	@Override
	public void create(T ci) {
		notNull(ci);
		create(ci, null);
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.CIService#create(org.skydingo.skybase.model.CI, org.springframework.validation.Errors)
	 */
	@Override
	public void create(T ci, Errors errors) {
		notNull(ci);
		
		// FIXME Need to check for errors here, like duplicates
		
		if (errors == null || !errors.hasErrors()) {
			ci.setDateCreated(new Date());
			getRepository().save(ci);
		} else {
			log.debug("Invalid CI; not saving");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.CIService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return CollectionsUtil.asSortedList(getRepository().findAll());
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.CIService#findOne(java.lang.Long)
	 */
	@Override
	public T findOne(Long id) {
		notNull(id);
		T ci = getRepository().findOne(id);
		
		if (ci == null) {
			throw new NoSuchCIException();
		}
		
		return ci;
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.CIService#update(org.skydingo.skybase.model.CI, org.springframework.validation.Errors)
	 */
	@Override
	public void update(T ci, Errors errors) {
		notNull(ci);
		
		if (errors == null || !errors.hasErrors()) {
			ci.setDateModified(new Date());
			getRepository().save(ci);
		}
		
		// TODO Need to have a way to generate new errors here. For example, if the user changes the name of the CI to
		// conflict with an existing CI, that would generate an error.
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.CIService#delete(org.skydingo.skybase.model.CI)
	 */
	@Override
	public void delete(T ci) {
		notNull(ci);
		getRepository().delete(ci);
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.CIService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		notNull(id);
		getRepository().delete(id);
	}
}
