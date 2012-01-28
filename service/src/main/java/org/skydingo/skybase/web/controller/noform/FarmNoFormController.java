/*
 * FarmNoFormController.java
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
package org.skydingo.skybase.web.controller.noform;

import javax.inject.Inject;

import org.skydingo.skybase.model.Farm;
import org.skydingo.skybase.repository.FarmRepository;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.service.FarmService;
import org.skydingo.skybase.web.controller.AbstractEntityNoFormController;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/farms")
public class FarmNoFormController extends AbstractEntityNoFormController<Farm> {
	@Inject private FarmRepository repository;
	@Inject private FarmService service;

	public GraphRepository<Farm> getRepository() { return repository; }

	public EntityService<Farm> getService() { return service; }
}
