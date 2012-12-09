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
package org.zkybase.cmdb.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ReflectionUtils {
	
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T, U> U copySimpleProperties(T from, U to) {
		try {
			PropertyDescriptor[] fromDescs = BeanUtils.getPropertyDescriptors(from.getClass());
			for (PropertyDescriptor fromDesc : fromDescs) {
				String propName = fromDesc.getName();
				PropertyDescriptor toDesc = BeanUtils.getPropertyDescriptor(to.getClass(), propName);
				if (toDesc != null) {
					Method readMethod = fromDesc.getReadMethod();
					Object value = readMethod.invoke(from);
					boolean write = (value instanceof Long || value instanceof String);
					if (write) {
						Method writeMethod = toDesc.getWriteMethod();
						writeMethod.invoke(to, value);
					}
				}
			}
			return to;
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
