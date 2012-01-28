/*
 * EntityFormatter.java
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
package org.skydingo.skybase.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.skydingo.skybase.model.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

/**
 * See http://forum.springsource.org/showthread.php?95196-JSR-303-Validation-on-child-objects
 *
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class EntityFormatter<T extends Entity<T>> implements Formatter<T> {
	private static final Logger log = LoggerFactory.getLogger(EntityFormatter.class);

	private Class<T> entityClass;

	public EntityFormatter(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(T entity, Locale locale) {
		String id = entity.getId().toString();
		log.debug("Printing entity into id={}", id);
		return id;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public T parse(String id, Locale locale) throws ParseException {

		// Spring calls this method on the way into the controller.
		try {
			log.debug("Creating new entity instance for class={}, id={}", entityClass, id);
			T entity = entityClass.newInstance();
			entity.setId(Long.parseLong(id));
			log.debug("Created new entity instance: {}", entity);
			return entity;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
