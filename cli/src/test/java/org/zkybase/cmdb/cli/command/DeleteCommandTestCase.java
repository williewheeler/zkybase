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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zkybase.cmdb.cli.ParseException;
import org.zkybase.cmdb.cli.request.DeleteRequest;
import org.zkybase.cmdb.connector.ApplicationOperations;
import org.zkybase.cmdb.connector.Zkybase;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class DeleteCommandTestCase {
	@InjectMocks private DeleteCommand command;
	
	@Mock private Zkybase zkybase;
	@Mock private ApplicationOperations applicationOps;
	@Mock private PrintStream out;

	private DeleteRequest request;
	
	@Before
	public void setUp() throws Exception {
		this.command = new DeleteCommand();
		MockitoAnnotations.initMocks(this);
		setUpTestObjects();
		setUpDependencies();
	}
	
	private void setUpTestObjects() {
		this.request = new DeleteRequest();
		request.id = 57L;
	}
	
	private void setUpDependencies() {
		when(zkybase.applications()).thenReturn(applicationOps);
	}
	
	@Test
	public void getName() {
		assertEquals("delete", command.getName());
	}
	
	@Test
	public void showUsage() {
		command.showUsage();
		verify(out).println(anyString());
	}
	
	@Test
	public void parse() {
		command.parse(new String[] { "57" });
	}
	
	@Test(expected = ParseException.class)
	public void parseWithTooFewArgs() {
		command.parse(new String[0]);
	}
	
	@Test(expected = ParseException.class)
	public void parseWithTooManyArgs() {
		command.parse(new String[] { "57", "chicken-bone" });
	}
	
	@Test(expected = ParseException.class)
	public void parseWithNonIntegerId() {
		command.parse(new String[] { "asdf" });
	}
	
	@Test
	public void execute() {
		command.execute(request);
		verify(applicationOps).delete(request.id);
	}
}
