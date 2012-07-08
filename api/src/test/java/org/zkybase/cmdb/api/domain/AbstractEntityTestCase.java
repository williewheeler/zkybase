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
package org.zkybase.cmdb.api.domain;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public abstract class AbstractEntityTestCase<T extends Entity> {
	private static final Map<Class<?>, Object> dummyValues = new HashMap<Class<?>, Object>();
	private static final Logger log = LoggerFactory.getLogger(AbstractEntityTestCase.class);
	
	private final Class<T> entityClass;
	
	private T entity;
	
	static {
		dummyValues.put(Double.class, 0.0);
		dummyValues.put(Integer.class, 0);
		dummyValues.put(Float.class, 0.0f);
		dummyValues.put(Long.class, 0L);
	}
	
	@SuppressWarnings("unchecked")
	public AbstractEntityTestCase() {
		ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) superclass.getActualTypeArguments()[0];
	}
	
	@Before
	public void setUp() throws Exception {
		this.entity = entityClass.newInstance();
	}
	
	@Test
	public void accessors() throws Exception {
		PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(entityClass);
		for (PropertyDescriptor prop : props) {
			String propName = prop.getName();
			if ("class".equals(propName)) { continue; }
			
			log.debug("Testing property accessors: {}.{}", entityClass, prop.getName());
			Method readMethod = prop.getReadMethod();
			Method writeMethod = prop.getWriteMethod();
			
			assertNull(readMethod.invoke(entity));
			Object dummyValue = getDummyValue(prop.getPropertyType());
			writeMethod.invoke(entity, dummyValue);
			assertSame(dummyValue, readMethod.invoke(entity));
		}
	}
	
	private Object getDummyValue(Class<?> propType) throws Exception {
		Object dummyValue = dummyValues.get(propType);
		return (dummyValue == null ? propType.newInstance() : dummyValue);
	}
}
