/* 
 * UserAccountNoFormController.java
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
package org.skydingo.skybase.web.controller.useraccount;

import javax.inject.Inject;

import org.skydingo.skybase.model.UserAccount;
import org.skydingo.skybase.service.CIService;
import org.skydingo.skybase.service.UserAccountService;
import org.skydingo.skybase.web.controller.AbstractCrudController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/useraccounts")
public class UserAccountCrudController extends AbstractCrudController<UserAccount> {
	private static final Logger log = LoggerFactory.getLogger(UserAccountCrudController.class);
	
	@Inject private UserAccountService userAccountService;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractCrudController#getAllowedFields()
	 */
	@Override
	protected String[] getAllowedFields() {
		return new String[] { "username" };
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityNoFormController#getService()
	 */
	@Override
	public CIService<UserAccount> getService() {
		return userAccountService;
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityNoFormController#getDetails(java.lang.Long, org.springframework.ui.Model)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getDetails(@PathVariable Long id, Model model) {
		
		// FIXME For now do this so we can see the GitHub link
		UserAccount dummy = new UserAccount();
		dummy.setId(1L);
		dummy.setUsername("willie");
		model.addAttribute(dummy);
		model.addAttribute("entity", dummy);
		
		return addNavigation(model, sitemap.getCiDetailsViewId(UserAccount.class));
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public String getCurrentUserDetails(Model model) {
		
		// FIXME Do lookup
		UserAccount dummy = new UserAccount();
		dummy.setId(1L);
		dummy.setUsername("willie");
		model.addAttribute(dummy);
		model.addAttribute("entity", dummy);
		
		// GitHub profile
		GitHubUserProfile gitHubUser = userAccountService.getCurrentUserProfile();
		if (gitHubUser != null) {
			model.addAttribute(gitHubUser);
		}
		
		return addNavigation(model, sitemap.getCiDetailsViewId(UserAccount.class));
	}
}
