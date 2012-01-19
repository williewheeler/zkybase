/* 
 * RegionNoFormController.java
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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.skydingo.skybase.model.DataCenter;
import org.skydingo.skybase.model.Region;
import org.skydingo.skybase.repository.RegionRepository;
import org.skydingo.skybase.service.EntityService;
import org.skydingo.skybase.service.RegionService;
import org.skydingo.skybase.web.controller.AbstractEntityNoFormController;
import org.skydingo.skybase.web.jit.JitNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/regions")
public class RegionNoFormController extends AbstractEntityNoFormController<Region> {
	@Inject private RegionRepository repository;
	@Inject private RegionService service;
	
	public GraphRepository<Region> getRepository() { return repository; }
	
	public EntityService<Region> getService() { return service; }
	
	// Not using @ResponseBody. See AbstractEntityNoFormController.
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=jit")
	public void getDetailsAsJson(@PathVariable Long id, Model model, HttpServletResponse res) throws IOException {
		Region region = doGetDetails(id, model);
		
		JitNode regionNode = new JitNode();
		regionNode.setId(region.getId().toString());
		regionNode.setName(region.getName());
		
		Set<DataCenter> dataCenters = region.getDataCenters();
		Set<JitNode> dataCenterNodes = new HashSet<JitNode>();
		for (DataCenter dataCenter : dataCenters) {
			JitNode dataCenterNode = new JitNode();
			dataCenterNode.setId(dataCenter.getId().toString());
			dataCenterNode.setName(dataCenter.getName());
			dataCenterNodes.add(dataCenterNode);
			
			Set<JitNode> farmNodes = new HashSet<JitNode>();
			for (int i = 0; i < 8; i++) {
				JitNode farmNode = new JitNode();
				farmNode.setId(dataCenter.getId() + "_" + i);
				farmNode.setName("Farm " + (i + 1));
				farmNodes.add(farmNode);
			}
			dataCenterNode.setChildren(farmNodes);
		}
		regionNode.setChildren(dataCenterNodes);
		
		objectMapper.writeValue(res.getWriter(), regionNode);
	}
}
