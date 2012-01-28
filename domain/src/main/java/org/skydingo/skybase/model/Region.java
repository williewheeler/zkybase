/*
 * Region.java
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
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
public class Region extends AbstractEntity<Region> {
	private String name;

	// FIXME For now we'll just fetch this. Eventually we'll want to stop doing that.
	@Fetch
	@RelatedTo(type = "CONTAINS")
	private Set<DataCenter> dataCenters;

	/**
	 * @return
	 */
	@XmlElement
	public String getName() { return name; }

	/**
	 * @param name
	 */
	public void setName(String name) { this.name = name; }

	/**
	 * @return
	 */
	public Set<DataCenter> getDataCenters() { return dataCenters; }

	/**
	 * @param dataCenters
	 */
	public void setDataCenter(Set<DataCenter> dataCenters) { this.dataCenters = dataCenters; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	@XmlTransient
	public String getDisplayName() { return name; }

	@XmlRootElement(name = "regions")
	public static class RegionListWrapper implements ListWrapper<Region> {
		private List<Region> list;

		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "region")
		public List<Region> getList() { return list; }

		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Region> list) { this.list = list; }
	}
}
