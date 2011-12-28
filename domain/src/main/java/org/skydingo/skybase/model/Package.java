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

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.neo4j.annotation.Indexed;

/**
 * Package entity.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Package extends AbstractEntity<Package> {
	@Indexed private String groupId;
	@Indexed private String packageId;
	@Indexed private String version;
	
	/**
	 * 
	 */
	public Package() { }
	
	/**
	 * @param groupId
	 * @param packageId
	 * @param version
	 */
	public Package(String groupId, String packageId, String version) {
		this.groupId = groupId;
		this.packageId = packageId;
		this.version = version;
	}
	
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
		if (groupComp != 0) { return groupComp; }
		int packageComp = packageId.compareTo(that.packageId);
		if (packageComp != 0) { return packageComp; }
		return version.compareTo(that.version);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Package: id=" + getId()
			+ ", groupId=" + groupId
			+ ", packageId=" + packageId
			+ ", version=" + version
			+ "]";
	}
	
	@XmlRootElement(name = "packages")
	public static class ListWrapper {
		private List<Package> list;
		
		public ListWrapper() { }
		
		public ListWrapper(List<Package> list) { this.list = list; }
		
		@XmlElement(name = "package")
		public List<Package> getList() { return list; }
		
		public void setList(List<Package> list) { this.list = list; }
	}
}
