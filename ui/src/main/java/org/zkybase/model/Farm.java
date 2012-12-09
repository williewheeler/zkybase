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
package org.zkybase.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.zkybase.util.CollectionsUtil;

/**
 * Farm entity. A farm is a load-balanced set of functionally equivalent instances.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "environment", "dataCenter", "instancesAsList" })
public class Farm extends AbstractCI<Farm> {
	private String name;
	
	// IMPORTANT: The relationship names IN_ENVIRONMENT and IN_DATA_CENTER need to be distinct; we can't, e.g., use the
	// single name IN for both. Otherwise Spring Data Neo4j will try to convert DataCenters to Environments (or vice
	// versa). Ugh. [WLW]
	
	@Fetch
	@RelatedTo(type = "IN_ENVIRONMENT", direction = Direction.OUTGOING)
	private Environment environment;
	
	@Fetch
	@RelatedTo(type = "IN_DATA_CENTER", direction = Direction.OUTGOING)
	private DataCenter dataCenter;
	
	// For now, fetch this eagerly since we want to display instance counts on our master list. There's a good chance,
	// however, that this will prove too expensive, and we may need to revisit it.
	@Fetch
	@RelatedTo(type = "IN_FARM", direction = Direction.INCOMING)
	private Iterable<Instance> instances;
	
	/**
	 * @return
	 */
	@NotNull
	@Size(max = 80)
	@XmlElement
	public String getName() { return name; }
	
	/**
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * Returns the farm's environment.
	 * 
	 * @return environment
	 */
	@NotNull
	@XmlElement
	public Environment getEnvironment() { return environment; }
	
	/**
	 * @param environment environment
	 */
	public void setEnvironment(Environment environment) { this.environment = environment; }
	
	/**
	 * @return
	 */
	@NotNull
	@XmlElement
	public DataCenter getDataCenter() { return dataCenter; }
	
	/**
	 * @param dataCenter
	 */
	public void setDataCenter(DataCenter dataCenter) { this.dataCenter = dataCenter; }
	
	/**
	 * @return
	 */
	@XmlTransient
	public Iterable<Instance> getInstances() { return instances; }
	
	/**
	 * @return instances as a list (for JAXB2, which can't handle Iterable)
	 */
	@XmlElement(name = "instances")
	public List<Instance> getInstancesAsList() {
		return CollectionsUtil.asSortedList(instances);
	}
	
	/**
	 * @param instances
	 */
	public void setInstances(Iterable<Instance> instances) { this.instances = instances; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.model.Entity#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }
	
	@XmlRootElement(name = "farms")
	public static class FarmListWrapper implements ListWrapper<Farm> {
		private List<Farm> list;
		
		/* (non-Javadoc)
		 * @see org.zkybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "farm")
		public List<Farm> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.zkybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Farm> list) { this.list = list; }
	}
}
