/*
 * AbstractEntity.java
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractEntity<T extends Entity<T>> implements Entity<T> {
	@GraphId private Long id;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getId()
	 */
	@Override
	@XmlAttribute
	public Long getId() { return id; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) { this.id = id; }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (!getClass().equals(o.getClass())) { return false; }

		T that = (T) o;
		Long thisId = this.getId();
		Long thatId = that.getId();

		// FIXME
		if (thisId == null && thatId == null) {
			return true;
		} else if (thisId == null || thatId == null) {
			return false;
		} else {
			return thisId.equals(thatId);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		Long id = getId();

		// FIXME
		return (id == null ? 0 : id.hashCode());
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(T that) {
		String thisDisplayName = getDisplayName();
		String thatDisplayName = that.getDisplayName();
		if (thisDisplayName == null) {
			return (thatDisplayName == null ? 0 : 1);
		} else if (thatDisplayName == null) {
			return -1;
		} else {
			return thisDisplayName.compareTo(thatDisplayName);
		}
	}

	// NO! Don't do this. When the display name is null, it makes it look like the whole dang entity is null. UGH.
//	@Override
//	public String toString() { return getDisplayName(); }

}
