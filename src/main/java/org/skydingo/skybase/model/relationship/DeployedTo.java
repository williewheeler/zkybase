/* 
 * DeployedTo.java
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
package org.skydingo.skybase.model.relationship;

import org.skydingo.skybase.model.Instance;
import org.skydingo.skybase.model.Package;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RelationshipEntity(type = "DEPLOYED_TO")
public class DeployedTo {
	@GraphId private Long id;
	@StartNode private Package pkg;
	@EndNode private Instance instance;
	
	public DeployedTo() { }
	
	public DeployedTo(Package pkg, Instance instance) {
		this.pkg = pkg;
		this.instance = instance;
	}
	
	public Package getPackage() { return pkg; }
	
	public void setPackage(Package pkg) { this.pkg = pkg; }
	
	public Instance getInstance() { return instance; }
	
	public void setInstance(Instance instance) { this.instance = instance; }
}
