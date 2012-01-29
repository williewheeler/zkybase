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
package org.skydingo.skybase.model.relationship;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.Farm;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RelationshipEntity(type = "FARM_SUPPORTS_APPLICATION")
public class FarmSupportsApplication {
	@GraphId private Long id;
	@Fetch @StartNode private Farm farm;
	@Fetch @EndNode private Application application;
	
	public FarmSupportsApplication() { }
	
	public FarmSupportsApplication(Farm farm, Application application) {
		this.farm = farm;
		this.application = application;
	}
	
	public Farm getFarm() { return farm; }
	
	public void setFarm(Farm farm) { this.farm = farm; }
	
	public Application getApplication() { return application; }
	
	public void setApplication(Application application) { this.application = application; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null || !(o instanceof FarmSupportsApplication)) {
			return false;
		} else if (id == null) {
			return super.equals(o);
		} else {
			FarmSupportsApplication that = (FarmSupportsApplication) o;
			return id.equals(that.id);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (id == null ? super.hashCode() : id.hashCode());
	}
}
