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
package org.skydingo.skybase.model.relationship;

import org.skydingo.skybase.model.Application;
import org.skydingo.skybase.model.Team;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RelationshipEntity(type = "APPLICATION_TEAM")
public class ApplicationTeam {
	public enum TeamType {
		DEVELOPMENT("Development"),
		OPERATIONS("Operations");
		
		private String name;
		
		/**
		 * @param name
		 */
		private TeamType(String name) { this.name = name; }
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() { return name; }
	}
	
	@GraphId private Long id;
	@Fetch @StartNode private Application application;
	@Fetch @EndNode private Team team;
	private TeamType type;
	
	/**
	 * 
	 */
	public ApplicationTeam() { }
	
	/**
	 * @param application
	 * @param team
	 * @param type
	 */
	public ApplicationTeam(Application application, Team team, TeamType type) {
		this.application = application;
		this.team = team;
		this.type = type;
	}

	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the type
	 */
	public TeamType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TeamType type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (application == null || team == null || type == null) {
			return super.equals(o);
		} else if (this == o) {
			return true;
		} else if (o == null || !(o instanceof ApplicationTeam)) {
			return false;
		} else {
			ApplicationTeam that = (ApplicationTeam) o;
			return application.equals(that.application) && team.equals(that.team) && type.equals(that.type);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (application == null || team == null || type == null) {
			return super.hashCode();
		}
		
		int hash = 1;
		hash = hash * 43 + application.hashCode();
		hash = hash * 43 + team.hashCode();
		hash = hash * 43 + type.hashCode();
		return hash;
	}
	
	@Override
	public String toString() {
		return "[ApplicationTeam: application=" + application
			+ ", team=" + team
			+ ", type=" + type
			+ "]";
	}
}
