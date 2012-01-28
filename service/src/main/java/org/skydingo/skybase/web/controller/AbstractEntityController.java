/*
 * AbstractEntityController.java
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
package org.skydingo.skybase.web.controller;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.skydingo.skybase.model.Entity;
import org.skydingo.skybase.web.navigation.Navigation;
import org.skydingo.skybase.web.navigation.Paths;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.skydingo.skybase.web.view.ViewNames;
import org.springframework.ui.Model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityController<T extends Entity<T>> {
	@Inject protected Paths paths;
	@Inject protected Sitemap sitemap;
	@Inject protected ViewNames viewNames;
	@Inject protected ObjectMapper objectMapper;

	// IMPORTANT: Only getEntityClass() should access this directly!
	private Class<T> entityClass;

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

	protected T newEntityInstance() {
		try {
			return getEntityClass().newInstance();
		} catch (InstantiationException e) {
			// Shouldn't happen
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// Shouldn't happen
			throw new RuntimeException(e);
		}
	}

	// TODO Figure out a way to use @ModelAttribute here
	protected String addNavigation(Model model, String currentNodeId) {
		model.addAttribute(new Navigation(sitemap, currentNodeId, model.asMap()));
		return currentNodeId;
	}
}
