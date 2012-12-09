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
package org.zkybase.cmdb.api.web;

import static org.zkybase.cmdb.util.Assert.isNull;
import static org.zkybase.cmdb.util.Assert.notNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.neo4j.helpers.collection.ClosableIterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkybase.cmdb.api.domain.Entity;
import org.zkybase.cmdb.api.mapper.Mapper;
import org.zkybase.cmdb.api.util.UriResolver;
import org.zkybase.cmdb.dto.Dto;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractController<T extends Entity, U extends Dto> implements Controller<T, U> {
	protected static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Inject private UriResolver resolver;
	
	protected abstract GraphRepository<T> getRepo();
	
	protected abstract Mapper<T, U> getMapper();
	
	protected UriResolver getUriResolver() { return resolver; }
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	public void post(@RequestBody U dto, HttpServletResponse response) {
		notNull(dto, "dto");
		notNull(response, "response");
		isNull(dto.getId(), "dto.id");
		
		final T entity = getMapper().toEntity(dto);
		getRepo().save(entity);
		
		// Entity now has an ID, so we can set it on the DTO.
		Long id = entity.getId();
		dto.setId(id);
		
		// FIXME Resolve by DTO instead of by entity?
		String uri = getUriResolver().resolve(entity.getClass(), id);
		
		log.debug("Adding header: Location={}", uri);
		response.addHeader("Location", uri);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<U> getAll() {
		log.debug("Getting all");
		ClosableIterable<T> iterable = getRepo().findAll();
		Iterator<T> iterator = iterable.iterator();
		List<T> entities = new ArrayList<T>();
		while (iterator.hasNext()) {
			entities.add(iterator.next());
		}
		return getMapper().toDtos(entities);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public U get(@PathVariable Long id) {
		notNull(id, "id");
		return getMapper().toDto(getRepo().findOne(id));
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json")
	public void put(@PathVariable Long id, @RequestBody U dto, HttpServletResponse response) {
		notNull(id, "id");
		notNull(dto, "dto");
		notNull(response, "response");
		
		final T entity = getRepo().findOne(id);
		getMapper().updateEntity(entity, dto);
		getRepo().save(entity);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		notNull(id, "id");
		notNull(response, "response");
		getRepo().delete(id);
	}
}
