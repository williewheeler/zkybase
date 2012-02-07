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
package org.skydingo.skybase.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A module, which is a version-free abstraction over packages.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "shortDescription" })
public class Module extends AbstractCI<Module> {
	private String name;
	private String shortDescription;
	private String groupId;
	private String moduleId;
	
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
	@Size(max = 200)
	@XmlElement
	public String getShortDescription() { return shortDescription; }
	
	/**
	 * @param shortDescription
	 */
	public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
	
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
	public String getModuleId() { return moduleId; }
	
	/**
	 * @param moduleId
	 */
	public void setModuleId(String moduleId) { this.moduleId = moduleId; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.CI#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Module: name=" + name
			+ ", shortDescription=" + shortDescription
			+ ", groupId=" + groupId
			+ ", packageId=" + moduleId
			+ "]";
	}
	
	/**
	 * @param that
	 * @return
	 */
	@Override
	public int compareTo(Module that) {
		String thatGroupId = that.getGroupId();
		String thatModuleId = that.getModuleId();
		
		// Ugh, this is more complicated than it should be. groupId and moduleId are required, so we can get rid of
		// the null checks at some point.
		if (groupId == null) {
			return (thatGroupId == null ? 0 : 1);
		} else if (thatGroupId == null) {
			return -1;
		} else {
			int groupIdComp = groupId.compareTo(thatGroupId);
			if (groupIdComp != 0) {
				return groupIdComp;
			} else if (moduleId == null) {
				return (thatModuleId == null ? 0 : 1);
			} else if (thatModuleId == null) {
				return -1;
			} else {
				return moduleId.compareTo(that.getModuleId());
			}
		}
	}
	
	@XmlRootElement(name = "modules")
	public static class ModuleListWrapper implements ListWrapper<Module> {
		private List<Module> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "module")
		public List<Module> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Module> list) { this.list = list; }
	}
}
