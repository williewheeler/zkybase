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

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.neo4j.annotation.Indexed;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "name" })
public class Team extends AbstractCI<Team> {
	
	// TODO Verify this index definition
//	@Indexed(indexType = IndexType.FULLTEXT, indexName="findByName")
	@Indexed
	private String name;
	
	/**
	 * @return team name
	 */
	@XmlElement
	@Size(max = 80)
	public String getName() { return name; }
	
	/**
	 * @param name team name
	 */
	public void setName(String name) { this.name = name; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.CI#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return name; }
	
	@XmlRootElement(name = "teams")
	public static class TeamListWrapper implements ListWrapper<Team> {
		private List<Team> list;
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#getList()
		 */
		@Override
		@XmlElement(name = "team")
		public List<Team> getList() { return list; }
		
		/* (non-Javadoc)
		 * @see org.skydingo.skybase.model.ListWrapper#setList(java.util.List)
		 */
		@Override
		public void setList(List<Team> list) { this.list = list; }
	}
}
