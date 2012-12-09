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
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.neo4j.annotation.Indexed;

/**
 * A module, which is a version-free abstraction over packages.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "shortDescription", "groupId", "moduleId" })
public class Module extends AbstractCI<Module> {
	private String name;
	private String shortDescription;
	
	// These need to be indexed so we can use them for querying.
	@Indexed private String groupId;
	@Indexed private String moduleId;
	
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
	@XmlElement
	public String getGroupId() { return groupId; }
	
	/**
	 * @param groupId
	 */
	public void setGroupId(String groupId) { this.groupId = groupId; }
	
	/**
	 * @return
	 */
	@XmlElement
	public String getModuleId() { return moduleId; }
	
	/**
	 * @param moduleId
	 */
	public void setModuleId(String moduleId) { this.moduleId = moduleId; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.model.CI#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.model.AbstractCI#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Module)) {
			return false;
		} else if (groupId == null || moduleId == null) {
			return super.equals(o);
		} else {
			Module that = (Module) o;
			return groupId.equals(that.groupId) && moduleId.equals(that.moduleId); 
		}
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.model.AbstractCI#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = 67 * hashCode + (groupId == null ? 0 : groupId.hashCode());
		hashCode = 67 * hashCode + (moduleId == null ? 0 : moduleId.hashCode());
		return hashCode;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Module: id=" + getId()
			+ ", name=" + name
			+ ", shortDescription=" + shortDescription
			+ ", groupId=" + groupId
			+ ", moduleId=" + moduleId
			+ "]";
	}
	
	@XmlRootElement(name = "modules")
	public static class ModuleListWrapper implements ListWrapper<Module> {
		private List<Module> list;
		
		/* (non-Javadoc)
		 * @see org.zkybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "module")
		public List<Module> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.zkybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Module> list) { this.list = list; }
	}
}
