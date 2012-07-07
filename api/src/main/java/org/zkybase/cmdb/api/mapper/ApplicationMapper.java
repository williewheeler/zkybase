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
package org.zkybase.cmdb.api.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.stereotype.Component;
import org.zkybase.cmdb.api.domain.ApplicationEntity;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class ApplicationMapper {
	
	public ApplicationEntity toEntity(Application dto) {
		if (dto == null) { return null; }
		ApplicationEntity entity = new ApplicationEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
	
	public List<Application> toDtoList(ClosableIterable<ApplicationEntity> entities) {
		if (entities == null) { return null; }
		List<Application> dtos = new ArrayList<Application>();
		Iterator<ApplicationEntity> entityIterator = entities.iterator();
		while (entityIterator.hasNext()) {
			dtos.add(toDto(entityIterator.next()));
		}
		return dtos;
	}
	
	public Application toDto(ApplicationEntity entity) {
		if (entity == null) { return null; }
		Application dto = new Application();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
}
