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
package org.zkybase.cmdb.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zkybase.cmdb.cli.command.DeleteCommand;
import org.zkybase.cmdb.cli.command.ListCommand;
import org.zkybase.cmdb.cli.command.QuitCommand;
import org.zkybase.cmdb.cli.request.ListRequest;
import org.zkybase.cmdb.cli.util.IoUtils;
import org.zkybase.cmdb.connector.Zkybase;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ShellTestCase {
	private static final String[] GOOD_LIST_ARGS = new String[] { };
	
	private Shell shell;
	
	@Mock private Zkybase zkybase;
	@Mock private InputStream in;
	@Mock private PrintStream out;
	@Mock private IoUtils utils;
	
	@Mock private BufferedReader bufferedReader;
	@Mock private InputStreamReader inputStreamReader;
	@Mock private DeleteCommand deleteCommand;
	@Mock private ListCommand listCommand;
	@Mock private QuitCommand quitCommand;
	@Mock private ListRequest listRequest;
	
	private List<Command> commandList;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		setUpTestObjects();
		setUpDependencies();
		this.shell = new Shell(zkybase, commandList, in, out, utils);
	}
	
	private void setUpTestObjects() {
		this.commandList = Arrays.asList(new Command[] { deleteCommand, listCommand, quitCommand });
		
		when(deleteCommand.getName()).thenReturn("delete");
		
		when(listCommand.getName()).thenReturn("list");
		when(listCommand.parse(GOOD_LIST_ARGS)).thenReturn(listRequest);
		
		when(quitCommand.getName()).thenReturn("quit");
	}
	
	private void setUpDependencies() {
		when(utils.newBufferedReader(inputStreamReader)).thenReturn(bufferedReader);
		when(utils.newInputStreamReader(in)).thenReturn(inputStreamReader);
	}
	
	@Test
	public void getUserInput() throws Exception {
		when(bufferedReader.readLine()).thenReturn("list");
		
		String input = shell.getUserInput();
		assertEquals("list", input);
	}
	
	@Test
	public void doCycle() throws Exception {
		when(bufferedReader.readLine()).thenReturn("list");
		
		boolean keepGoing = shell.doCycle();
		assertTrue(keepGoing);
	}
	
	@Test
	public void doCycleWithQuitCommand() throws Exception {
		when(bufferedReader.readLine()).thenReturn("quit");
		
		boolean keepGoing = shell.doCycle();
		assertFalse(keepGoing);
	}
	
	@Test
	public void parseCommands() {
		// We're testing for the existence of the mock commands here, not the real commands.
		assertNotNull(shell.parseCommand("delete"));
		assertNotNull(shell.parseCommand("list"));
		assertNotNull(shell.parseCommand("quit"));
	}
	
	@Test(expected = ParseException.class)
	public void parseUnknownCommand() {
		assertNotNull(shell.parseCommand("someUnknownCommand"));
	}
	
	@Test
	public void parseRequest() {
		Request request = shell.parseRequest(listCommand, GOOD_LIST_ARGS);
		assertNotNull(request);
	}
	
	@Test(expected = ParseException.class)
	public void parseBadRequest() {
		String[] args = new String[] { "asdf" };
		doThrow(new ParseException("error")).when(deleteCommand).parse(args);
		
		try {
			shell.parseRequest(deleteCommand, args);
		} catch (ParseException e) {
			verify(deleteCommand).showUsage();
			throw e;
		}
	}
}
