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
package org.skydingo.skybase.web.controller;

import static org.springframework.util.Assert.notNull;

import javax.inject.Inject;

import org.skydingo.skybase.web.navigation.Navigation;
import org.skydingo.skybase.web.navigation.Sitemap;
import org.springframework.ui.Model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class AbstractController {
	@Inject protected Sitemap sitemap;
	
	/**
	 * @param model model
	 * @param nodeId node ID
	 * @return node ID
	 */
	protected String addNavigation(Model model, String nodeId) {
		notNull(model);
		notNull(nodeId);
		model.addAttribute(new Navigation(sitemap, nodeId, model.asMap()));
		return nodeId;
	}
}
