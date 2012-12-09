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
package org.zkybase.web.sitemap.navigation;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class NavigationNode {
	private String id;
	private String name;
	private String path;
	private boolean header;
	private boolean active;
	
	public NavigationNode(String id, String name, String path, boolean header, boolean active) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.header = header;
		this.active = active;
	}
	
	/**
	 * @return
	 */
	public String getId() { return id; }
	
	/**
	 * @param id
	 */
	public void setId(String id) { this.id = id; }
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the header
	 */
	public boolean isHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(boolean header) {
		this.header = header;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

}
