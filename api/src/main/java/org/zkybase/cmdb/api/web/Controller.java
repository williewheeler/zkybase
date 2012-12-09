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
package org.zkybase.cmdb.api.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.zkybase.cmdb.api.domain.Entity;
import org.zkybase.cmdb.dto.Dto;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface Controller<T extends Entity, U extends Dto> {
	
	public void post(U dto, HttpServletResponse response);
	
	List<U> getAll();
	
	public U get(Long id);
	
	void put(Long id, U dto, HttpServletResponse response);
	
	void delete(Long id, HttpServletResponse response);

}
