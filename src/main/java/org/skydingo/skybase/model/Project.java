/* 
 * Project.java
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
package org.skydingo.skybase.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Project domain object.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public class Project {
	@GraphId private Long nodeId;
	private String name;

	/**
	 * @return the nodeId
	 */
	public Long getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	/**
	 * Returns the project name.
	 * 
	 * @return project name
	 */
	public String getName() { return name; }
	
	/**
	 * Sets the project name.
	 * 
	 * @param name project name
	 */
	public void setName(String name) { this.name = name; }
}
