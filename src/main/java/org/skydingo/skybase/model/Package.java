/* 
 * Package.java
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
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Package entity.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public class Package {
	@GraphId private Long id;
	
	@RelatedTo(type = "BUILT_FROM")
	private Project project;
	
	@Indexed private String groupId;
	@Indexed private String packageId;
	@Indexed private String version;
	
	/**
	 * 
	 */
	public Package() { }
	
	/**
	 * @param project
	 * @param groupId
	 * @param packageId
	 * @param version
	 */
	public Package(Project project, String groupId, String packageId, String version) {
		this.project = project;
		this.groupId = groupId;
		this.packageId = packageId;
		this.version = version;
	}
	
	/**
	 * @return
	 */
	public Long getId() { return id; }
	
	/**
	 * @param id
	 */
	public void setId(Long id) { this.id = id; }
	
	/**
	 * @return project this package was built from
	 */
	public Project getProject() { return project; }
	
	/**
	 * @param project project this package was built from
	 */
	public void setProject(Project project) { this.project = project; }
	
	/**
	 * @return
	 */
	public String getGroupId() { return groupId; }
	
	/**
	 * @param groupId
	 */
	public void setGroupId(String groupId) { this.groupId = groupId; }
	
	/**
	 * @return
	 */
	public String getPackageId() { return packageId; }
	
	/**
	 * @param packageId
	 */
	public void setPackageId(String packageId) { this.packageId = packageId; }
	
	/**
	 * @return
	 */
	public String getVersion() { return version; }
	
	/**
	 * @param version
	 */
	public void setVersion(String version) { this.version = version; }
}
