/* 
 * Farm.java
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
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public abstract class AbstractFarm {
	@GraphId private Long nodeId;
	private String name;
	private String instanceType;
	private String imageId;
	private String securityGroup;
	private String keyPair;
	private String ipAddress;
	
	@RelatedTo(type = "SUPPORTS")
	private Project project;
	
	/**
	 * @return
	 */
	public Long getId() { return nodeId; }
	
	/**
	 * @param id
	 */
	public void setId(Long id) { this.nodeId = id; }
	
	/**
	 * @return
	 */
	public String getName() { return name; }
	
	/**
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * @return
	 */
	public String getInstanceType() { return instanceType; }
	
	/**
	 * @param instanceType
	 */
	public void setInstanceType(String instanceType) { this.instanceType = instanceType; }
	
	/**
	 * @return
	 */
	public String getImageId() { return imageId; }
	
	/**
	 * @param imageId
	 */
	public void setImageId(String imageId) { this.imageId = imageId; }
	
	/**
	 * @return
	 */
	public String getSecurityGroup() { return securityGroup; }
	
	/**
	 * @param securityGroup
	 */
	public void setSecurityGroup(String securityGroup) { this.securityGroup = securityGroup; }
	
	/**
	 * @return
	 */
	public String getKeyPair() { return keyPair; }
	
	/**
	 * @param keyPair
	 */
	public void setKeyPair(String keyPair) { this.keyPair = keyPair; }
	
	/**
	 * @return
	 */
	public String getIpAddress() { return ipAddress; }
	
	/**
	 * @param ipAddress
	 */
	public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
	
	/**
	 * @return
	 */
	public Project getProject() { return project; }
	
	/**
	 * @param project
	 */
	public void setProject(Project project) { this.project = project; }
}
