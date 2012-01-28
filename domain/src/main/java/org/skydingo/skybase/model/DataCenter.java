/*
 * DataCenter.java
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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.neo4j.graphdb.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name", "region" })
public class DataCenter extends AbstractEntity<DataCenter> {
	private static final Logger log = LoggerFactory.getLogger(DataCenter.class);

	private String name;

	@Fetch
	@RelatedTo(type = "CONTAINS", direction = Direction.INCOMING)
	private Region region;

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
	public Region getRegion() { return region; }

	/**
	 * @param region
	 */
	public void setRegion(Region region) { this.region = region; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	@XmlTransient
	public String getDisplayName() { return name; }

	@XmlRootElement(name = "dataCenters")
	public static class DataCenterListWrapper implements ListWrapper<DataCenter> {
		private List<DataCenter> list;

		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "dataCenter")
		public List<DataCenter> getList() { return list; }

		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<DataCenter> list) { this.list = list; }
	}
}
