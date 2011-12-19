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

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Sitemap {
	public static final String DASHBOARD_ID = "dashboard";
	
	public static final String PACKAGE_LIST_ID = "packageList";
	public static final String CREATE_PACKAGE_ID = "createPackageForm";
	
	public static final String TEAM_DETAILS_ID = "teamDetails";
	
	public static final String PERSON_LIST_ID = "personList";
	public static final String CREATE_PERSON_ID = "createPersonForm";
	public static final String PERSON_DETAILS_ID = "personDetails";
	public static final String EDIT_PERSON_ID = "editPersonForm";
	
	private ExpressionParser exprParser;
	
	private final Map<String, Node> nodes = new HashMap<String, Node>();
	
	public Sitemap() {
		Node dashboard = buildNode(DASHBOARD_ID, "'Dashboard'", getDashboardPath(), null);
		
		Node packageList = buildNode(PACKAGE_LIST_ID, "'Packages'", getPackageListPath(), dashboard);
		Node createPackage = buildNode(CREATE_PACKAGE_ID, "'Create package'", getCreatePackagePath(), packageList);
		
		Node teamDetails = buildNode(TEAM_DETAILS_ID, "#this[project].name", getProjectPath(), dashboard);
		
		Node personList = buildNode(PERSON_LIST_ID, "'People'", getPersonListPath(), dashboard);
		Node createPerson = buildNode(CREATE_PERSON_ID, "'Create person'", getCreatePersonPath(), personList);
		Node personDetails = buildNode(PERSON_DETAILS_ID, "#this[person].firstNameLastName", getPersonPath(), personList);
		Node editPerson = buildNode(EDIT_PERSON_ID, "'Edit person'", getEditPersonPath(), personDetails);
	}
	
	private Node buildNode(String id, String name, String path, Node parent) {
		Node node = new Node(id, name, path);
		if (parent != null) { parent.addChild(node); }
		nodes.put(id, node);
		return node;
	}
	
	public void setExpressionParser(ExpressionParser exprParser) {
		this.exprParser = exprParser;
	}
	
	public Node getNode(String id) { return nodes.get(id); }
	
	public String resolve(String exprStr, Map<String, Object> context) {
		Expression expr = exprParser.parseExpression(exprStr);
		EvaluationContext evalContext = new StandardEvaluationContext(context);
		return (String) expr.getValue(evalContext);
	}
	
	private String getDashboardPath() { return "'/'"; }
	
	private String getProjectPath() { return "'/project/' + #this[project].id"; }
	
	private String getPackageListPath() { return "'/packages'"; }
	
	private String getCreatePackagePath() { return getPackageListPath() + " + '/new'"; }
	
	private String getPackagePath() { return getPackageListPath() + " + '/' + #this[package].id"; }
	
	private String getPersonListPath() { return "'/people'"; }
	
	private String getCreatePersonPath() { return getPersonListPath() + " + '/new'"; }
	
	private String getPersonPath() { return getPersonListPath() + " + '/' + #this[person].id"; }
	
	private String getEditPersonPath() { return getPersonPath() + " + '/edit'"; }
}
