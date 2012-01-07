/* 
 * PersonServiceImpl.java
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
package org.skydingo.skybase.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Set;

import javax.inject.Inject;

import org.skydingo.skybase.model.Person;
import org.skydingo.skybase.repository.PersonRepository;
import org.skydingo.skybase.service.PersonService;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class PersonServiceImpl extends AbstractEntityServiceImpl<Person> implements PersonService {
	@Inject private Neo4jTemplate template;
	@Inject private PersonRepository personRepo;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PersonService#createPerson(org.skydingo.skybase.model.Person, org.springframework.validation.Errors)
	 */
	@Override
	public void createPerson(Person person, Errors errors) {
		if (!errors.hasErrors()) {
			personRepo.save(person);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PersonService#findPersonDetails(java.lang.Long)
	 */
	@Override
	public Person findPersonDetails(Long id) {
		notNull(id);
		Person person = personRepo.findOne(id);
		
		// For now this is how you do it.
		// http://stackoverflow.com/questions/8218864/fetch-annotation-in-sdg-2-0-fetching-strategy-questions
		// http://springinpractice.com/2011/12/28/initializing-lazy-loaded-collections-with-spring-data-neo4j/
		Set<Person> reports = person.getDirectReports();
		for (Person report : reports) {
			template.fetch(report);
		}
		
		return person;
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.service.PersonService#updatePerson(org.skydingo.skybase.model.Person, org.springframework.validation.Errors)
	 */
	@Override
	public void updatePerson(Person person, Errors errors) {
		if (!errors.hasErrors()) {
			personRepo.save(person);
		}
	}
}
