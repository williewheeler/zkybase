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

import org.zkybase.cmdb.cli.ParseException;
import org.zkybase.cmdb.cli.Request;
import org.zkybase.cmdb.cli.request.DeleteRequest;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class DeleteCommand extends AbstractCommand {

	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#getName()
	 */
	@Override
	public String getName() { return "delete"; }

	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#showUsage()
	 */
	@Override
	public void showUsage() {
		out.println("Usage: delete [id]");
	}

	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#parse(java.lang.String[])
	 */
	@Override
	public Request parse(String[] tokens) {
		if (tokens.length != 1) {
			throw new ParseException("delete requires exactly one argument.");
		}
		
		DeleteRequest request = new DeleteRequest();
		try {
			request.id = Long.parseLong(tokens[0]);
		} catch (NumberFormatException e) {
			throw new ParseException("id must be an integer.");
		}
		return request;
	}
	
	/* (non-Javadoc)
	 * @see org.zkybase.cmdb.cli.Command#execute(org.zkybase.cmdb.cli.Request)
	 */
	@Override
	public void execute(Request request) {
		DeleteRequest deleteRequest = (DeleteRequest) request;
		zkybase.applications().delete(deleteRequest.id);
	}
}
