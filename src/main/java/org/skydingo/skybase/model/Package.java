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

import org.skydingo.skybase.model.relationship.BuiltFrom;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
public class Package {
	@GraphId private Long nodeId;
	@Indexed private String name;
	
	@RelatedTo(type = "BUILT_FROM")
	private Project project;
	
	public Package() { }
	
	public Package(String name) { this.name = name; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public Project getProject() { return project; }
	
	public void setProject(Project project) { this.project = project; }
	
	public BuiltFrom builtFrom(Project project) {
		BuiltFrom builtFrom = new BuiltFrom(this, project);
		project.getPackages().add(this);
		return builtFrom;
	}
}
