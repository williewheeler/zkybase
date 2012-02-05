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

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;

// Hm, with the @XmlRootElement annotation here, the JAXB message converter always beats out the Jackson message
// converter. (This happens only when getting an individual entity--it doesn't affect the list views because the
// JSON list is an unannotated java.util.List, whereas the XML list is an annotated ListWrapper.)
// 
// See AbstractEntityController.getDetailsAsJson() for the workaround.

/**
 * Package entity.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "module", "groupId", "packageId", "version" })
public class Package extends AbstractCI<Package> {
	
	@Fetch
	@RelatedTo(type = "FROM_MODULE", direction = Direction.OUTGOING)
	private Module module;
	
	@Indexed private String groupId;
	@Indexed private String packageId;
	@Indexed private String version;
	
	/**
	 * 
	 */
	public Package() { }
	
	/**
	 * @param module module
	 * @param groupId group ID
	 * @param packageId package ID
	 * @param version version
	 */
	public Package(Module module, String groupId, String packageId, String version) {
		this.module = module;
		this.groupId = groupId;
		this.packageId = packageId;
		this.version = version;
	}
	
	/**
	 * @return
	 */
	@NotNull
	@XmlElement
	public Module getModule() { return module; }
	
	/**
	 * @param module
	 */
	public void setModule(Module module) { this.module = module; }
	
	/**
	 * @return
	 */
	@NotNull
	@Size(min = 1, max = 200)
	@XmlElement
	public String getGroupId() { return groupId; }
	
	/**
	 * @param groupId
	 */
	public void setGroupId(String groupId) { this.groupId = groupId; }
	
	/**
	 * @return
	 */
	@NotNull
	@Size(min = 1, max = 200)
	@XmlElement
	public String getPackageId() { return packageId; }
	
	/**
	 * @param packageId
	 */
	public void setPackageId(String packageId) { this.packageId = packageId; }
	
	/**
	 * @return
	 */
	@NotNull
	@Size(min = 1, max = 80)
	@XmlElement
	public String getVersion() { return version; }
	
	/**
	 * @param version
	 */
	public void setVersion(String version) { this.version = version; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return packageId; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.AbstractEntity#compareTo(org.skydingo.skybase.model.Entity)
	 */
	@Override
	public int compareTo(Package that) {
		int groupComp = groupId.compareTo(that.groupId);
		if (groupComp != 0) {
			return groupComp;
		} else {
			int packageComp = packageId.compareTo(that.packageId);
			if (packageComp != 0) {
				return packageComp;
			} else {
				return version.compareTo(that.version);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Package: id=" + getId()
			+ ", module=" + module
			+ ", groupId=" + groupId
			+ ", packageId=" + packageId
			+ ", version=" + version
			+ "]";
	}
	
	@XmlRootElement(name = "packages")
	public static class PackageListWrapper implements ListWrapper<Package> {
		private List<Package> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "package")
		public List<Package> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Package> list) { this.list = list; }
	}
}
