/* 
 * Sitemap.java
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
package org.skydingo.skybase.web.navigation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.DataCenter;
import org.skydingo.skybase.model.Environment;
import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.model.Region;
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
	
	private final Map<String, Node> nodes = new HashMap<String, Node>();
	
	
	// =================================================================================================================
	// Build sitemap
	// =================================================================================================================
	
	@PostConstruct
	public void postConstruct() {
		Node dashboard = buildNode(getDashboardId(), wrap("Dashboard"), wrap("/"), null);
		buildCrudNodes(Application.class, dashboard);
		buildCrudNodes(DataCenter.class, dashboard);
		buildCrudNodes(Environment.class, dashboard);
		buildCrudNodes(Farm.class, dashboard);
		buildCrudNodes(Package.class, dashboard);
		buildCrudNodes(Person.class, dashboard);
		buildCrudNodes(Region.class, dashboard);
	}
	
	private void buildCrudNodes(Class<?> entityClass, Node dashboard) {
		String simpleName = entityClass.getSimpleName();
		String uncapSimpleName = StringUtils.uncapitalize(simpleName);
		
		String listTitleCode = "entity." + uncapSimpleName + ".sentenceCase.plural";
		log.debug("Looking up message code: {}", listTitleCode);
		String listTitle = wrap(messageSource.getMessage(listTitleCode, null, null));
		String listPath = wrap(paths.getListPath(entityClass));
		
		String createTitleCode = "entity." + uncapSimpleName + ".lowercase.singular";
		log.debug("Looking up message code: {}", createTitleCode);
		String createTitle = wrap("Create " + messageSource.getMessage(createTitleCode, null, null));
		String createPath = wrap(paths.getCreateFormPath(entityClass));
		
		String detailsTitle = "#this[entity].displayName";
		String detailsPath = listPath + " + '/' + #this[entity].id";
		
		String editTitle = "'Edit ' + #this[entity].displayName";
		String editPath = detailsPath + " + '/edit'";
		
		Node listNode = buildNode(getEntityListViewId(entityClass), listTitle, listPath, dashboard);
		Node detailsNode = buildNode(getEntityDetailsViewId(entityClass), detailsTitle, detailsPath, listNode);
		buildNode(getCreateFormId(entityClass), createTitle, createPath, listNode);
		buildNode(getEditFormId(entityClass), editTitle, editPath, detailsNode);
	}
	
	/**
	 * @param path
	 * @return
	 */
	@Deprecated
	private String wrap(String path) { return "'" + StringUtils.replace(path, "'", "''") + "'"; }
	
	private Node buildNode(String id, String name, String path, Node parent) {
		Node node = new Node(id, name, path);
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
	public String getEntityDetailsViewId(Class<?> entityClass) {
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
	
	public Node getNode(String id) { return nodes.get(id); }
	
	public String resolve(String exprStr, Map<String, Object> context) {
		Expression expr = exprParser.parseExpression(exprStr);
		EvaluationContext evalContext = new StandardEvaluationContext(context);
		return (String) expr.getValue(evalContext);
	}
}
