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

import javax.inject.Inject;

import org.skydingo.skybase.service.ApplicationService;
import org.skydingo.skybase.web.sitemap.Sitemap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Dashboard controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
public class DashboardController extends AbstractController {
	@Inject private ApplicationService applicationService;
	@Inject private Sitemap sitemap;
	
	/**
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getDashboard(Model model) {
		model.addAttribute(applicationService.findAll());
		return addNavigation(model, sitemap.getDashboardId());
	}

}
