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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkybase.cmdb.cli.command.DeleteCommand;
import org.zkybase.cmdb.cli.command.ListCommand;
import org.zkybase.cmdb.cli.util.IoUtils;
import org.zkybase.cmdb.connector.Zkybase;
import org.zkybase.cmdb.connector.impl.ZkybaseTemplate;

/**
 * Interactive Zkybase API command shell.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Shell {
	private static final List<Command> COMMANDS = Arrays.asList(new Command[] {
		new DeleteCommand(),
		new ListCommand()
	});
	
	private Zkybase zkybase;
	private InputStream in;
	private PrintStream out;
	private IoUtils utils;
	
	private final Map<String, Command> commandMap = new HashMap<String, Command>();
	
	public static void main(String[] args) {
		Zkybase zkybase = new ZkybaseTemplate("http://localhost:8080");
		new Shell(zkybase, COMMANDS, System.in, System.out, new IoUtils()).start();
	}
	
	public Shell(Zkybase zkybase, List<Command> commandList, InputStream in, PrintStream out, IoUtils utils) {
		this.zkybase = zkybase;
		this.in = in;
		this.out = out;
		this.utils = utils;
		
		for (Command command : commandList) { register(command); }
	}
	
	private void register(Command command) {
		command.setZkybase(zkybase);
		command.setOut(System.out);
		commandMap.put(command.getName(), command);
	}
	
	public void start() {
		while (true) {
			try {
				if (!doCycle()) { break; }
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
	
	boolean doCycle() throws IOException {
		String[] tokens = getUserInput().split(" ");
		Command cmd = parseCommand(tokens[0]);
		Request request = parseRequest(cmd, Arrays.copyOfRange(tokens, 1, tokens.length));
		cmd.execute(request);
		return true;
	}
	
	String getUserInput() throws IOException {
		out.print(": ");
		return utils.newBufferedReader(utils.newInputStreamReader(in)).readLine();
	}
	
	Command parseCommand(String cmdName) {
		Command cmd = commandMap.get(cmdName);
		if (cmd == null) {
			throw new ParseException("Unknown command: " + cmdName);
		}
		return cmd;
	}
	
	Request parseRequest(Command cmd, String[] args) {
		try {
			return cmd.parse(args);
		} catch (ParseException e) {
			cmd.showUsage();
			throw e;
		}
	}
}
