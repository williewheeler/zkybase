/* 
 * SkybaseClient.java
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
package org.skydingo.skybase.client;

import java.util.List;

import org.skydingo.skybase.model.Package;
import org.springframework.web.client.RestTemplate;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class SkybaseClient {
	private RestTemplate template;
	
	/**
	 * @return REST template
	 */
	public RestTemplate getRestTemplate() { return template; }
	
	/**
	 * @param template REST template
	 */
	public void setRestTemplate(RestTemplate template) { this.template = template; }
	
	/**
	 * @param pkg package
	 * @return package ID
	 */
	public Long createPackage(Package pkg) {
		// TODO Create package
		return 1L;
	}
	
	/**
	 * @return all packages, sorted
	 */
	public List<Package> getPackages() {
		return template
			.getForObject("http://localhost:8080/packages.xml", Package.ListWrapper.class)
			.getList();
	}
}
