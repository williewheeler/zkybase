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
package org.zkybase.cmdb.connector.impl;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.zkybase.cmdb.connector.ApplicationOperations;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ApplicationTemplate implements ApplicationOperations {
	private RestTemplate restTemplate;
	private String baseUri;
	
	public ApplicationTemplate(RestTemplate restTemplate, String baseUri) {
		this.restTemplate = restTemplate;
		this.baseUri = baseUri;
	}
	
	public RestTemplate getRestTemplate() { return restTemplate; }
	
	public String getBaseUri() { return baseUri; }

	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.connector.ApplicationOperations#create(org.zkybase.cmdb.dto.Application)
	 */
	@Override
	public String create(Application application) {
		ResponseEntity<String> response = restTemplate.postForEntity(getListUri(), application, String.class);
		HttpHeaders headers = response.getHeaders();
		List<String> locations = headers.get("Location");
		
		// There's only one.
		return locations.get(0);
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.connector.ApplicationOperations#getAll()
	 */
	@Override
	public List<Application> getAll() {
		return asList(restTemplate.getForObject(getListUri(), Application[].class));
	}
	
	private String getListUri() {
		return baseUri + "/applications";
	}
}
