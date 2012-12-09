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
package org.zkybase.comparator;

import java.util.Comparator;

import org.zkybase.model.relationship.ApplicationTeam;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ApplicationTeamByTypeComparator implements Comparator<ApplicationTeam> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ApplicationTeam thisTeam, ApplicationTeam thatTeam) {
		int compType = thisTeam.getType().compareTo(thatTeam.getType());
		if (compType != 0) {
			return compType;
		} else {
			// Currently assuming that we're applying this comparator to a given app's teams.
			return thisTeam.getTeam().compareTo(thatTeam.getTeam());
		}
		
	}

}
