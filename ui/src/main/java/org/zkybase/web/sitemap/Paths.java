/* 
 * Paths.java
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
package org.zkybase.web.sitemap;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.zkybase.model.Application;
import org.zkybase.model.DataCenter;
import org.zkybase.model.Environment;
import org.zkybase.model.Farm;
import org.zkybase.model.Package;
import org.zkybase.model.Person;
import org.zkybase.model.Region;
import org.zkybase.model.Team;
import org.zkybase.model.UserAccount;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class Paths {
	private Map<Class<?>, String> basePaths = new HashMap<Class<?>, String>();
	
	public Paths() {
		basePaths.put(Application.class, "/applications");
		basePaths.put(DataCenter.class, "/datacenters");
		basePaths.put(Environment.class, "/environments");
		basePaths.put(Farm.class, "/farms");
		basePaths.put(Package.class, "/packages");
		basePaths.put(Person.class, "/people");
		basePaths.put(Region.class, "/regions");
		basePaths.put(Team.class, "/teams");
		basePaths.put(UserAccount.class, "/useraccounts");
	}
	
	public String getBasePath(Class<?> ciClass) {
		String path = basePaths.get(ciClass);
		if (path == null) {
			throw new IllegalArgumentException("No path for CI class: " + ciClass);
		}
		return path;
	}
	
	public String getListPath(Class<?> ciClass) {
		return getBasePath(ciClass);
	}
	
	public String getDetailsPath(Class<?> ciClass, Long id) {
		return getBasePath(ciClass) + "/" + id;
	}
	
	public String getCreateFormPath(Class<?> ciClass) {
		return getBasePath(ciClass) + "/new";
	}
	
	public String getSubmitCreateFormPath(Class<?> ciClass) {
		return getBasePath(ciClass);
	}
	
	public String getEditFormPath(Class<?> ciClass, Long id) {
		return getDetailsPath(ciClass, id) + "/edit";
	}
	
	public String getSubmitEditFormPath(Class<?> ciClass, Long id) {
		return getDetailsPath(ciClass, id);
	}
}
