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
package org.zkybase.cmdb.cli;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkybase.cmdb.connector.Zkybase;
import org.zkybase.cmdb.connector.impl.ZkybaseTemplate;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	private Zkybase zkybase;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.createApplication();
		main.listApplications();
//		main.deleteApplications();
	}
	
	public Main() {
		this.zkybase = new ZkybaseTemplate("http://localhost:8080");
	}
	
	public void createApplication() {
		Application application = new Application();
		zkybase.applications().create(application);
	}
	
	public void listApplications() {
		List<Application> applications = zkybase.applications().list();
		for (Application application : applications) {
			log.info(application.toString());
		}
	}
	
	public void deleteApplications() {
		zkybase.applications().delete(44L);
		zkybase.applications().delete(45L);
		zkybase.applications().delete(46L);
		zkybase.applications().delete(47L);
	}
}
