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

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

// TODO From a deployment perspective, the type should be defined up at the farm level so as to avoid deploying farms
// with a mix of physical and virtual instances. From an as-is perspective, though, it's entirely possible for a farm
// to have that undesired mix. Will need to think about this more. [WLW]

/**
 * A machine instance, which can be either physical or virtual.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "fqdn", "ipAddress", "type", "farm" })
public class Instance extends AbstractEntity<Instance> {
	public enum Type { PHYSICAL, VIRTUAL }
	
	private String name;
	private String fqdn;
	private String ipAddress;
	private Type type;
	
	@Fetch
	@RelatedTo(type = "IN_FARM", direction = Direction.OUTGOING)
	private Farm farm;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
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
	 * Returns the instance's fully-qualified domain name (FQDN).
	 * 
	 * @return FQDN
	 */
	@NotNull
	@Size(max = 120)
	@XmlElement
	public String getFqdn() { return fqdn; }
	
	/**
	 * Sets the instance's fully-qualified domain name (FQDN).
	 * 
	 * @param fqdn FQDN
	 */
	public void setFqdn(String fqdn) { this.fqdn = fqdn; }
	
	/**
	 * @return IP address (can be either IPv4 or IPv6)
	 */
	@NotNull
	// http://stackoverflow.com/questions/166132/maximum-length-of-the-textual-representation-of-an-ipv6-address
	@Size(max = 45)
	@XmlElement
	public String getIpAddress() { return ipAddress; }
	
	public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
	
	/**
	 * Returns the instance type, which is either PHYSICAL or VIRTUAL.
	 * 
	 * @return type (either PHYSICAL or VIRTUAL)
	 */
	@XmlElement
	public Type getType() { return type; }
	
	/**
	 * @param type
	 */
	public void setType(Type type) { this.type = type; }
	
	/**
	 * @return farm containing the instance
	 */
	@NotNull
	@XmlElement
	public Farm getFarm() { return farm; }
	
	/**
	 * @param farm farm containing the instance
	 */
	public void setFarm(Farm farm) { this.farm = farm; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }
	
	@XmlRootElement(name = "instances")
	public static class InstanceListWrapper implements ListWrapper<Instance> {
		private List<Instance> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "instance")
		public List<Instance> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Instance> list) { this.list = list; }
	}
}
