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
package org.zkybase.api.mapper;

import static org.zkybase.cmdb.util.ReflectionUtils.copySimpleProperties;
import static org.zkybase.cmdb.util.ReflectionUtils.newInstance;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.zkybase.api.domain.node.Node;
import org.zkybase.cmdb.dto.Dto;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractMapper<T extends Node, U extends Dto> implements Mapper<T, U> {
	private Class<T> entityClass;
	private Class<U> dtoClass;
	
	@SuppressWarnings("unchecked")
	public AbstractMapper() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		Type[] typeArgs = type.getActualTypeArguments();
		this.entityClass = (Class<T>) typeArgs[0];
		this.dtoClass = (Class<U>) typeArgs[1];
	}
	
	@Override
	public T toEntity(U dto) {
		if (dto == null) { return null; }
		return updateEntity(newInstance(entityClass), dto);
	}
	
	@Override
	public T updateEntity(T entity, U dto) {
		return copySimpleProperties(dto, entity);
	}
	
	@Override
	public List<U> toDtos(List<T> entities) {
		if (entities == null) { return null; }
		List<U> dtos = new ArrayList<U>();
		for (T entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}
	
	@Override
	public U toDto(T entity) {
		if (entity == null) { return null; }
		return updateDto(newInstance(dtoClass), entity);
	}
	
	@Override
	public U updateDto(U dto, T entity) {
		return copySimpleProperties(entity, dto);
	}
}
