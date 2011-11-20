/* 
 * Instance.java
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
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public class Instance {
	
	// Internal node ID
	@GraphId private Long nodeId;
	
	// External ID
	@Indexed private String id;
	
	private String ipAddress;
	
	public Instance() { }
	
	/**
	 * Creates an instance with the given ID.
	 * 
	 * @param id instance ID
	 */
	public Instance(String id) { this.id = id; }
	
	/**
	 * @return instance ID
	 */
	public String getId() { return id; }
	
	/**
	 * @param id instance ID
	 */
	public void setId(String id) { this.id = id; }
	
	/**
	 * @return IP address
	 */
	public String getIpAddress() { return ipAddress; }
	
	/**
	 * @param ipAddress IP address
	 */
	public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
