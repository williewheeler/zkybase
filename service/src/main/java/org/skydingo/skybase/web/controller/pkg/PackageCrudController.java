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
package org.skydingo.skybase.web.controller.pkg;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.skydingo.skybase.exception.DuplicateCIException;
import org.skydingo.skybase.model.Module;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.service.CIService;
import org.skydingo.skybase.service.ModuleService;
import org.skydingo.skybase.service.PackageService;
import org.skydingo.skybase.web.controller.AbstractCrudController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Package controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/packages")
public class PackageCrudController extends AbstractCrudController<Package> {
	private static final Logger log = LoggerFactory.getLogger(PackageCrudController.class);
	
	private static final String[] ALLOWED_FIELDS = new String[] { "groupId", "moduleId", "version" };
	
	@Inject private ModuleService moduleService;
	@Inject private PackageService packageService;
	
	@Value("#{config['app.baseUrl']}")
	private String appBaseUrl;
	
	public CIService<Package> getService() { return packageService; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityFormController#getAllowedFields()
	 */
	@Override
	protected String[] getAllowedFields() { return ALLOWED_FIELDS; }
	
	
	// =================================================================================================================
	// Create
	// =================================================================================================================
	
	// consumes : Spring 3.1
	/**
	 * @param pkgData
	 * @param res
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/xml")
	public void postPackage(@RequestBody Package pkgData, HttpServletResponse res) {
		log.debug("Posting package: {}", pkgData);
		
		// Look up the actual module.
		Module moduleData = pkgData.getModule();
		String groupId = moduleData.getGroupId();
		String moduleId = moduleData.getModuleId();
		log.debug("Looking up module entity with groupId={}, moduleId={}", groupId, moduleId);
		Module module = moduleService.findByGroupIdAndModuleId(groupId, moduleId);
		log.debug("Found module: {}", module);
		
		// Prepare the package for saving.
		pkgData.setModule(module);
		
		try {
			log.debug("Creating package: {}", pkgData);
			packageService.create(pkgData);
			res.setHeader("Location", appBaseUrl + "/packages/" + pkgData.getId());
		} catch (DuplicateCIException e) {
			log.info("Package already exists; ignoring: {}", pkgData);
			
			// Using SC_OK:
			// http://stackoverflow.com/questions/283957/rest-correct-http-response-code-for-a-post-which-is-ignored
			// But see also:
			// http://stackoverflow.com/questions/3825990/http-response-code-for-post-when-resource-already-exists
			res.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
