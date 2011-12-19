/* 
 * CollectionsUtil.java
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
package org.skydingo.skybase.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.neo4j.helpers.collection.IteratorUtil;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public final class CollectionsUtil {
	
	/**
	 * @param iterable iterable
	 * @return list
	 */
	public static <E> List<E> asList(Iterable<E> iterable) {
		return new ArrayList<E>(IteratorUtil.asCollection(iterable));
	}
	
	/**
	 * @param iterable
	 * @return
	 */
	public static <E extends Comparable<E>> List<E> asSortedList(Iterable<E> iterable) {
		List<E> list = asList(iterable);
		Collections.sort(list);
		return list;
	}
}
