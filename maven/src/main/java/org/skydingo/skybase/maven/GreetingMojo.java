/* 
 * GreetingMojo.java
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
package org.skydingo.skybase.maven;

import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.skydingo.skybase.client.SkybaseClient;
import org.skydingo.skybase.model.Package;
import org.springframework.web.client.RestTemplate;

/**
 * @goal sayhi
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class GreetingMojo extends AbstractMojo {

	/* (non-Javadoc)
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		// Say hi
		getLog().info("Hello, world.");
		
		// Display packages
		SkybaseClient client = new SkybaseClient();
		client.setRestTemplate(new RestTemplate());
		List<Package> pkgs = client.getPackages();
		for (Package pkg : pkgs) {
			getLog().info(pkg.toString());
		}
	}

}
