/*
 * AbstractEntityServiceImplTests.java
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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.skydingo.skybase.model.Entity;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityServiceImplTests<T extends Entity<T>> {

	protected abstract GraphRepository<T> getRepository();

	protected abstract AbstractEntityServiceImpl<T> getService();

	@Test
	public void testFindAll() {
		List<T> list = getService().findAll();
		assertNotNull(list);
	}

	@Test
	public void testFindOne() {
		T entity = getService().findOne(1L);
		assertNotNull(entity);
	}

	@Test
	public void testDelete() {
		getService().delete(1L);
		verify(getRepository(), times(1)).delete(1L);
	}
}
