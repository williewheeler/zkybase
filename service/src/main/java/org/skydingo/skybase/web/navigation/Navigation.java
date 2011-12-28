/* 
 * Navigation.java
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

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Navigation {
	private static final Logger log = LoggerFactory.getLogger(Navigation.class);
	
	private Sitemap sitemap;
	private Map<String, Object> context;
	private Node currentNode;
	
	public Navigation(Sitemap sitemap, String currentNodeId, Map<String, Object> context) {
		this.sitemap = sitemap;
		this.context = context;
		this.currentNode = buildNavigationNode(sitemap.getNode(currentNodeId));
	}
	
	// Recursive method
	private Node buildNavigationNode(Node sitemapNode) {
		notNull(sitemapNode, "sitemapNode can't be null. Add relevant nodes to Sitemap.");
		log.debug("Building navigation node: {}", sitemapNode.getId());
		Node node = new Node(
			sitemapNode.getId(),
			sitemap.resolve(sitemapNode.getName(), context),
			sitemap.resolve(sitemapNode.getPath(), context));
		if (sitemapNode.hasParent()) {
			buildNavigationNode(sitemapNode.getParent()).addChild(node);
		}
		return node;
	}
	
	public Node getCurrentNode() { return currentNode; }
	
	public String getCurrentArea() { return currentNode.getCurrentArea(); }
	
	public List<Node> getBreadcrumbs() { return currentNode.getBreadcrumbs(); }
}
