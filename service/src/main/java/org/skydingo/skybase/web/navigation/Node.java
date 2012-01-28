/*
 * Node.java
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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Node {
	private static final Logger log = LoggerFactory.getLogger(Node.class);

	private String id;
	private String name;
	private boolean useNameAsPageTitle;
	private String path;
	private Node parent;
	private final List<Node> children = new ArrayList<Node>();

	public Node(String id, String name, String path) {
		this(id, name, true, path);
	}

	public Node(String id, String name, boolean useNameAsPageTitle, String path) {
		this.id = id;
		this.name = name;
		this.useNameAsPageTitle = useNameAsPageTitle;
		this.path = path;
	}

	public String getId() { return id; }

	public String getName() { return name; }

	public boolean getUseNameAsPageTitle() { return useNameAsPageTitle; }

	public void setUseNameAsPageTitle(boolean flag) { this.useNameAsPageTitle = flag; }

	public String getPageTitle() {
		Node node = this;
		while (!(node == null || node.getUseNameAsPageTitle())) {
			log.debug("Not using as page title: {}", node);
			node = node.getParent();
		}
		String pageTitle = (node.getUseNameAsPageTitle() ? node.getName() : null);
		log.debug("pageTitle={}", pageTitle);
		return pageTitle;
	}

	public String getPath() { return path; }

	public boolean hasParent() { return parent != null; }

	public Node getParent() { return parent; }

	private void setParent(Node parent) { this.parent = parent; }

	public void addChild(Node child) {
		child.setParent(this);
		children.add(child);
	}

	// Recursive method
	public String getCurrentArea() {
		if (hasParent() && parent.hasParent()) {
			return parent.getCurrentArea();
		} else {
			return id;
		}
	}

	public List<Node> getBreadcrumbs() {
		List<Node> breadcrumbs = new ArrayList<Node>();
		doGetBreadcrumbs(breadcrumbs);
//		breadcrumbs.remove(breadcrumbs.size() - 1);
		return breadcrumbs;
	}

	// Recursive method
	private void doGetBreadcrumbs(List<Node> breadcrumbs) {
		if (hasParent()) {
			parent.doGetBreadcrumbs(breadcrumbs);
		}
		breadcrumbs.add(this);
	}

	@Override
	public String toString() {
		return "[Node: id=" + id
				+ ", name=" + name
				+ ", useNameAsPageTitle=" + useNameAsPageTitle
				+ ", path=" + path
				+ "]";
	}
}
