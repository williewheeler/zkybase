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
package org.skydingo.skybase.web.sitemap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.DataCenter;
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Region;
import org.skydingo.skybase.model.Team;
import org.skydingo.skybase.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Sitemap {
	private static final Logger log = LoggerFactory.getLogger(Sitemap.class);
	
	private MessageSource messageSource;
	private Paths paths;
	private ExpressionParser exprParser;
	
	private final Map<String, SitemapNode> nodes = new HashMap<String, SitemapNode>();
	
	
	// =================================================================================================================
	// Build sitemap
	// =================================================================================================================
	
	@PostConstruct
	public void postConstruct() {
		SitemapNode dashboard = buildNode(getDashboardId(), wrap("Dashboard"), wrap("/"), null);
		buildCrudNodes(Application.class, dashboard);
		buildCrudNodes(DataCenter.class, dashboard);
		buildCrudNodes(Environment.class, dashboard);
		buildCrudNodes(Farm.class, dashboard);
//		buildCrudNodes(Package.class, dashboard);
		buildCrudNodes(Person.class, dashboard);
		buildCrudNodes(Region.class, dashboard);
		buildCrudNodes(Team.class, dashboard);
		buildCrudNodes(UserAccount.class, dashboard);
		
		buildApplicationNodes();
		buildPersonNodes();
	}
	
	private void buildCrudNodes(Class<?> entityClass, SitemapNode dashboard) {
		String simpleName = entityClass.getSimpleName();
		String uncapSimpleName = StringUtils.uncapitalize(simpleName);
		
		// List node
		String listTitleCode = "entity." + uncapSimpleName + ".sentenceCase.plural";
		log.debug("Looking up message code: {}", listTitleCode);
		String listTitle = wrap(messageSource.getMessage(listTitleCode, null, null));
		String listPath = wrap(paths.getListPath(entityClass));
		SitemapNode listNode = buildNode(getEntityListViewId(entityClass), listTitle, listPath, dashboard);
		
		// Details node
		// FIXME Consider replacing 'entity' with 'application', 'database', etc.
		String detailsTitle = "#this[" + uncapSimpleName + "].displayName";
		String detailsPath = listPath + " + '/' + #this[" + uncapSimpleName + "].id";
		SitemapNode detailsNode = buildNode(getCiDetailsViewId(entityClass), detailsTitle, detailsPath, listNode);
		detailsNode.setShowInDetailsSidebar(true);
		
		// Create node
		String createTitleCode = "entity." + uncapSimpleName + ".lowercase.singular";
		log.debug("Looking up message code: {}", createTitleCode);
		String createTitle = wrap("Create " + messageSource.getMessage(createTitleCode, null, null));
		String createPath = wrap(paths.getCreateFormPath(entityClass));
		buildNode(getCreateFormId(entityClass), createTitle, createPath, listNode);
		
		// Edit node
		String editTitle = "'Edit ' + #this[entity].displayName";
		String editPath = detailsPath + " + '/edit'";
		buildNode(getEditFormId(entityClass), editTitle, editPath, detailsNode);
	}
	
	private void buildApplicationNodes() {
		SitemapNode appNode = getNode("applicationDetails");
		
		// FIXME We're referencing the app using 'entity', but that kind of sucks.
		String modulesPath = appNode.getPath() + " + '/modules'";
		SitemapNode modulesNode = buildNode("applicationModuleList", "'Modules'", false, modulesPath, appNode);
		modulesNode.setShowInDetailsSidebar(true);
		
		String modulePath = modulesPath + " + '/' + #this[module].id";
		SitemapNode moduleNode = buildNode("applicationModule", "#this[module].displayName", true, modulePath, modulesNode);
		
		String createPackagePath = modulePath + " + '/packages/new'";
		buildNode("createApplicationPackageForm", "'Create package'", true, createPackagePath, moduleNode);
		
		String teamsPath = appNode.getPath() + " + '/teams'";
		SitemapNode teamsNode = buildNode("applicationTeamList", "'Teams'", false, teamsPath, appNode);
		teamsNode.setShowInDetailsSidebar(true);
		
		String scmPath = appNode.getPath() + " + '/scm'";
		SitemapNode scmNode = buildNode("applicationScm", "'SCM'", false, scmPath, appNode);
		SitemapNode collaboratorsNode = buildNode("applicationScmCollaborators", "'Collaborators'", false, scmPath + " + '/collaborators'", scmNode);
		SitemapNode commitsNode = buildNode("applicationScmCommits", "'Commits'", false, scmPath + " + '/commits'", scmNode);
		SitemapNode downloadsNode = buildNode("applicationScmDownloads", "'Downloads'", false, scmPath + " + '/downloads'", scmNode);
		SitemapNode watchersNode = buildNode("applicationScmWatchers", "'Watchers'", false, scmPath + " + '/watchers'", scmNode);
		SitemapNode hooksNode = buildNode("applicationScmHooks", "'Hooks'", false, scmPath + " + '/hooks'", scmNode);
		
		scmNode.setShowInDetailsSidebar(true);
		collaboratorsNode.setShowInDetailsSidebar(true);
		commitsNode.setShowInDetailsSidebar(true);
		downloadsNode.setShowInDetailsSidebar(true);
		collaboratorsNode.setShowInDetailsSidebar(true);
		watchersNode.setShowInDetailsSidebar(true);
		hooksNode.setShowInDetailsSidebar(true);
	}
	
	private void buildPersonNodes() {
		SitemapNode personNode = getNode("personDetails");
		
		String scmPath = personNode.getPath() + " + '/scm'";
		SitemapNode scmNode = buildNode("personScm", "'SCM'", false, scmPath, personNode);
		SitemapNode followersNode = buildNode("personScmFollowers", "'Followers'", false, scmPath + " + '/followers'", scmNode);
		SitemapNode followingNode = buildNode("personScmFollowing", "'Following'", false, scmPath + " + '/following'", scmNode);
		
		scmNode.setShowInDetailsSidebar(true);
		followersNode.setShowInDetailsSidebar(true);
		followingNode.setShowInDetailsSidebar(true);
	}
	
	/**
	 * @param path
	 * @return
	 */
	@Deprecated
	private String wrap(String path) { return "'" + StringUtils.replace(path, "'", "''") + "'"; }
	
	private SitemapNode buildNode(String id, String name, String path, SitemapNode parent) {
		return buildNode(id, name, true, path, parent);
	}
	
	private SitemapNode buildNode(String id, String name, boolean useNameAsPageTitle, String path, SitemapNode parent) {
		SitemapNode node = new SitemapNode(id, name, useNameAsPageTitle, path);
		log.debug("Built node: {}", node);
		if (parent != null) { parent.addChild(node); }
		nodes.put(id, node);
		return node;
	}
	
	
	// =================================================================================================================
	// Accessor methods
	// =================================================================================================================
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public void setPaths(Paths paths) {
		this.paths = paths;
	}
	
	public void setExpressionParser(ExpressionParser exprParser) {
		this.exprParser = exprParser;
	}
	
	
	// =================================================================================================================
	// Node IDs
	// =================================================================================================================
	
	public String getDashboardId() { return "dashboard"; }
	
	/**
	 * @param entityClass entity class
	 * @return node ID
	 */
	public String getCreateFormId(Class<?> entityClass) {
		return "create" + entityClass.getSimpleName() + "Form";
	}
	
	/**
	 * @param entityClass entity class
	 * @return node ID
	 */
	public String getEntityListViewId(Class<?> entityClass) {
		return StringUtils.uncapitalize(entityClass.getSimpleName()) + "List";
	}
	
	/**
	 * @param entityClass entity class
	 * @return node ID
	 */
	public String getCiDetailsViewId(Class<?> entityClass) {
		return StringUtils.uncapitalize(entityClass.getSimpleName()) + "Details";
	}
	
	/**
	 * @param entityClass
	 * @return
	 */
	public String getEditFormId(Class<?> entityClass) {
		return "edit" + entityClass.getSimpleName() + "Form";
	}
	
	
	// =================================================================================================================
	// Nodes
	// =================================================================================================================
	
	public SitemapNode getNode(String id) { return nodes.get(id); }
	
	public String resolve(String exprStr, Map<String, Object> context) {
		log.debug("Resolving expression: {}", exprStr);
		Expression expr = exprParser.parseExpression(exprStr);
		EvaluationContext evalContext = new StandardEvaluationContext(context);
		return (String) expr.getValue(evalContext);
	}
}
