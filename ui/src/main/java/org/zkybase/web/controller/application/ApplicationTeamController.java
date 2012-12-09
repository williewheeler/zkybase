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
package org.zkybase.web.controller.application;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkybase.comparator.ApplicationTeamByTypeComparator;
import org.zkybase.model.Application;
import org.zkybase.model.relationship.ApplicationTeam;
import org.zkybase.service.ApplicationService;
import org.zkybase.util.CollectionsUtil;
import org.zkybase.web.controller.AbstractController;

// TODO Hm, we want CRUD operations here, but this isn't a top-level CI. Need to think about how best to handle this.

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/applications")
public class ApplicationTeamController extends AbstractController {
	@Inject private ApplicationService applicationService;
	private Comparator<ApplicationTeam> comparator = new ApplicationTeamByTypeComparator();
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/teams", method = RequestMethod.GET)
	public String getTeams(@PathVariable Long id, Model model) {
		Application app = applicationService.findOneWithTeams(id);
		model.addAttribute(app);

		List<ApplicationTeam> appTeams = CollectionsUtil.asList(app.getTeams());
		Collections.sort(appTeams, comparator);
		model.addAttribute(appTeams);
		
		return addNavigation(model, "applicationTeamList");
	}
}
