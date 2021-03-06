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
package org.zkybase.cmdb.cli.command;

import java.util.List;

import org.zkybase.cmdb.cli.ParseException;
import org.zkybase.cmdb.cli.Request;
import org.zkybase.cmdb.cli.request.ListRequest;
import org.zkybase.cmdb.dto.Application;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ListCommand extends AbstractCommand {

	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#getName()
	 */
	@Override
	public String getName() { return "list"; }
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#showUsage()
	 */
	@Override
	public void showUsage() { out.println("Usage: list"); }
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#parse(java.lang.String[])
	 */
	@Override
	public Request parse(String[] tokens) {
		if (tokens.length > 0) {
			throw new ParseException("list expects an empty list of arguments.");
		}
		return new ListRequest();
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#execute()
	 */
	@Override
	public void execute(Request request) {
		List<Application> applications = zkybase.applications().list();
		for (Application application : applications) {
			out.println(application.toString());
		}
	}
}
