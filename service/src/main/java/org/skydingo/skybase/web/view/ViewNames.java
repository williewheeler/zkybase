/*
 * ViewNames.java
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
package org.skydingo.skybase.web.view;

import javax.inject.Inject;

import org.skydingo.skybase.web.navigation.Paths;
import org.springframework.stereotype.Component;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class ViewNames {
	@Inject private Paths paths;

	/**
	 * @param entityClass entity class
	 * @return view name
	 */
	public String postCreateFormSuccessViewName(Class<?> entityClass) {
		return getRedirectPath(entityClass, "created");
	}

	public String putEditFormSuccessViewName(Class<?> entityClass, Long id) {
		return getRedirectPath(entityClass, "updated");
	}

	/**
	 * @param entityClass entity class
	 * @return view name
	 */
	public String deleteSuccessViewName(Class<?> entityClass) {
		return getRedirectPath(entityClass, "deleted");
	}

	private String getRedirectPath(Class<?> entityClass, String operation) {
		return "redirect:" + paths.getBasePath(entityClass) + "?a=" + operation;
	}
}
