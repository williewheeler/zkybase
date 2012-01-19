/* 
 * ClasspathScanningJaxb2Marshaller.java
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
package org.skydingo.skybase.oxm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * <p>
 * Binds classes using Spring's classpath scanning mechanism.
 * </p>
 * <p>
 * Original version of this class was by Jarno Walgemoed. Minor modifications by Willie Wheeler. Licensed under Apache
 * 2.0 with Walgemoed's kind permission.
 * </p>
 * 
 * @author Jarno Walgemoed (Twitter @jwalgemoed)
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ClasspathScanningJaxb2Marshaller extends Jaxb2Marshaller {
	private static final Logger log = LoggerFactory.getLogger(ClasspathScanningJaxb2Marshaller.class);
	
	private List<String> basePackages;
	
	/**
	 * @return base packages
	 */
	public List<String> getBasePackages() { return basePackages; }
	
	/**
	 * @param basePackages base packages
	 */
	public void setBasePackages(List<String> basePackages) { this.basePackages = basePackages; }
	
	/**
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {
		setClassesToBeBound(getXmlRootElementClasses());
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	private Class<?>[] getXmlRootElementClasses() throws Exception {
		ClassPathScanningCandidateComponentProvider scanner =
			new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(XmlRootElement.class));
		
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (String basePackage : basePackages) {
			Set<BeanDefinition> definitions = scanner.findCandidateComponents(basePackage);
			for (BeanDefinition definition : definitions) {
				String className = definition.getBeanClassName();
				log.info("Found class: {}", className);
				classes.add(Class.forName(className));
			}
		}
		
		return classes.toArray(new Class<?>[0]);
	}
}
