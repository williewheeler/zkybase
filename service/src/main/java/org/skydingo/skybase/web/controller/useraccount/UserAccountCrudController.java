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

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.skydingo.skybase.model.UserAccount;
import org.skydingo.skybase.service.CIService;
import org.skydingo.skybase.service.UserAccountService;
import org.skydingo.skybase.web.controller.AbstractCrudController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/useraccounts")
public class UserAccountCrudController extends AbstractCrudController<UserAccount> {
	private static final Logger log = LoggerFactory.getLogger(UserAccountCrudController.class);
	
	@Inject private UserAccountService userAccountService;
	@Inject private GitHub gitHub;
	@Inject private GitHubConnectionFactory gitHubConnectionFactory;

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
		return addNavigation(model, sitemap.getEntityDetailsViewId(getCiClass()));
	}
	
	/**
	 * @param id
	 * @param model
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}/access/github", method = RequestMethod.GET)
	public void acquireGitHubAccessToken(@PathVariable Long id, Model model, HttpServletResponse response)
			throws IOException {
		
		log.debug("Acquiring GitHub access token");
		
		// FIXME Hardcoded redirect URI
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8080/useraccounts/" + id + "/connections/github");
		
		OAuth2Operations ops = gitHubConnectionFactory.getOAuthOperations();
		response.sendRedirect(ops.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params));
	}
	
	@RequestMapping(value = "/{id}/connections/github", method = RequestMethod.GET)
	public void acquireGitHubConnection(@PathVariable Long id, @RequestParam String code, Model model) {
		log.debug("Acquiring GitHub connection");
		
		OAuth2Operations ops = gitHubConnectionFactory.getOAuthOperations();
		
		AccessGrant accessGrant =
			ops.exchangeForAccess(code, "http://localhost:8080/useraccounts/" + id, null);
		log.debug("accessGrant={}", accessGrant);
		Connection<GitHub> connection = gitHubConnectionFactory.createConnection(accessGrant);
	}

}
