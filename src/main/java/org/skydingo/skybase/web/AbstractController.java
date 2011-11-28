/* 
 * AbstractController.java
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
package org.skydingo.skybase.web;

import org.skydingo.skybase.web.navigation.Breadcrumb;
import org.skydingo.skybase.web.navigation.NavigationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractController {
	protected static final String MODE_CREATE = "create";
	protected static final String MODE_EDIT = "edit";
	
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	/**
	 * @param binder binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		doInitBinder(binder);
	}
	
	protected abstract void doInitBinder(WebDataBinder binder);
	
	protected void setMode(Model model, String mode) {
		model.addAttribute("mode", mode);
	}
	
	/**
	 * Puts the given breadcrumbs on the model. This method provides the Dashboard breadcrumb automatically.
	 * 
	 * @param model model
	 * @param breadcrumbs breadcrumbs
	 */
	protected void addBreadcrumbs(Model model, Breadcrumb... breadcrumbs) {
		model.addAttribute(NavigationUtils.createBreadcrumbs(breadcrumbs));
	}
}
