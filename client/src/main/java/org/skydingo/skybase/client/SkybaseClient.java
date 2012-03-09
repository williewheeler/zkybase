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
package org.skydingo.skybase.client;

import static org.springframework.util.Assert.notNull;

import java.net.URI;
import java.util.List;

import org.skydingo.skybase.model.Package;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class SkybaseClient {
	private static final Logger log = LoggerFactory.getLogger(SkybaseClient.class);
	
	private RestTemplate template;
	private String skybaseUrl;
	
	/**
	 * Note: This constructor appends a "/" to the URL if the URL doesn't already end with one.
	 * 
	 * @param template REST template
	 * @param skybaseUrl Skybase URL
	 */
	public SkybaseClient(RestTemplate template, String skybaseUrl) {
		this.template = template;
		if (!skybaseUrl.endsWith("/")) { skybaseUrl += "/"; }
		this.skybaseUrl = skybaseUrl;
	}
	
	/**
	 * @return REST template
	 */
	public RestTemplate getRestTemplate() { return template; }
	
	public String getSkybaseUrl() { return skybaseUrl; }
	
	/**
	 * @param pkg package
	 */
	public void createPackage(Package pkg) {
		notNull(pkg);
		URI location = template.postForLocation(getPackagesUrl(), pkg);
		log.info("Created package: {}", location);
	}
	
	/**
	 * @return all packages, sorted
	 */
	public List<Package> getPackages() {
		return template
			.getForObject(getPackagesUrl() + "?format=xml", Package.PackageListWrapper.class)
			.getList();
	}
	
	private String getPackagesUrl() { return skybaseUrl + "packages"; }
}
