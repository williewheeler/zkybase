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

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Farm entity. A farm is a load-balanced set of functionally equivalent servers.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
public class Farm extends AbstractEntity<Farm> {
	private String name;
	private Environment environment;
	private DataCenter dataCenter;
	
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
	 * @return
	 */
	@NotNull
	@XmlElement
	public Environment getEnvironment() { return environment; }
	
	/**
	 * @param environment
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
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }
	
	@XmlRootElement(name = "farms")
	public static class FarmListWrapper implements ListWrapper<Farm> {
		private List<Farm> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "farm")
		public List<Farm> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Farm> list) { this.list = list; }
	}
}
