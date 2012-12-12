package org.zkybase.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkybase.api.domain.node.Node;
import org.zkybase.api.mapper.Mapper;
import org.zkybase.cmdb.dto.Dto;

@Controller
public class CIController implements BeanFactoryAware {
	private static final Logger log = LoggerFactory.getLogger(CIController.class);
	
	@Inject private ObjectMapper objectMapper;
	
	private BeanFactory beanFactory;
	
	// FIXME Externalize
	private Map<String, String> ciTypeMap = new HashMap<String, String>();
	
	public CIController() {
		ciTypeMap.put("applications", "Application");
		ciTypeMap.put("datacenters", "DataCenter");
		ciTypeMap.put("regions", "Region");
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	@RequestMapping(value = "/{ciPath}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Dto> list(@PathVariable String ciPath) {
		String ciType = getCiType(ciPath);
		List<Node> nodes = new ArrayList<Node>();
		GraphRepository<Node> repo = getRepo(ciType);
		Iterator<Node> iterator = repo.findAll().iterator();
		while (iterator.hasNext()) {
			nodes.add(iterator.next());
		}
		return getMapper(ciType).toDtos(nodes);
	}
	
	@RequestMapping(value = "/{ciPath}/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Dto get(@PathVariable String ciPath, @PathVariable Long id) {
		String ciType = getCiType(ciPath);
		GraphRepository<Node> repo = getRepo(ciType);
		Node node = repo.findOne(id);
		return getMapper(ciType).toDto(node);
	}
	
	@RequestMapping(value = "/{ciPath}", method = RequestMethod.POST, consumes = "application/json")
	public void post(@PathVariable String ciPath, @RequestBody String dtoStr, HttpServletResponse response)
			throws Exception {
		
		log.info("Posting CI: ciPath={}, dto={}", ciPath, dtoStr);
		
		// Map the DTO string to a DTO. We have to do it ourselves since @RequestBody doesn't work for interfaces.
		String ciType = getCiType(ciPath);
		Class<Dto> dtoClass = getDtoClass(ciType);
		Dto dto = (Dto) objectMapper.readValue(dtoStr, dtoClass);
		Node node = getMapper(ciType).toEntity(dto);
		getRepo(ciType).save(node);
		
		// Node now has an ID, so we can set it on the DTO. (Why am I doing this at all?)
//		Long id = node.getId();
//		dto.setId(id);
		
		// FIXME Resolve by DTO instead of by node?
//		String uri = getUriResolver().resolve(node.getClass(), id);
//		response.addHeader("Location", uri);
	}
	
	@RequestMapping(value = "/{ciPath}/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public void put(
			@PathVariable String ciPath,
			@PathVariable Long id,
			@RequestBody Dto dto,
			HttpServletResponse response) {
		
		String ciType = getCiType(ciPath);
		GraphRepository<Node> repo = getRepo(ciType);
		Node node = repo.findOne(id);
		getMapper(ciType).updateEntity(node, dto);
		repo.save(node);
	}
	
	@RequestMapping(value = "/{ciPath}/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String ciPath, @PathVariable Long id, HttpServletResponse response) {
		String ciType = getCiType(ciPath);
		GraphRepository<Node> repo = getRepo(ciType);
		repo.delete(id);
	}
	
	
	// =================================================================================================================
	// Helpers
	// =================================================================================================================
	
	private String getCiType(String ciPath) {
		String ciType = ciTypeMap.get(ciPath);
		if (ciType == null) {
			throw new IllegalArgumentException("Unknown CI path: " + ciPath);
		}
		return ciType;
	}
	
	private Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Class<Dto> getDtoClass(String ciType) {
		return (Class<Dto>) getClass("org.zkybase.cmdb.dto." + ciType);
	}
	
	private GraphRepository<Node> getRepo(String ciType) {
		String className = "org.zkybase.api.repo." + ciType + "Repo";
		return (GraphRepository<Node>) beanFactory.getBean(getClass(className));
	}
	
	private Mapper<Node, Dto> getMapper(String ciType) {
		String className = "org.zkybase.api.mapper." + ciType + "Mapper";
		return (Mapper<Node, Dto>) beanFactory.getBean(getClass(className));
	}
}
