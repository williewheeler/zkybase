/* 
 * Tester.java
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Tester {
	private static final Logger log = LoggerFactory.getLogger(Tester.class);
	
	public static void main(String[] args) {
		SkybaseClient client = new SkybaseClient();
		client.setRestTemplate(new RestTemplate());
		List<Package> pkgs = client.getPackages();
		for (Package pkg : pkgs) {
			log.debug("Package: {}", pkg);
		}
	}
}
