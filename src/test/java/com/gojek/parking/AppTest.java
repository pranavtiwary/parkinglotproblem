package com.gojek.parking;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.gojek.parking.service.ICommandService;
import com.gojek.parking.service.impl.CommandServiceImpl;

public class AppTest {

	private List<String> commands= null;

	private ICommandService service;

	public static String FILE_NAME="command.txt";

	@Before
	public void init(){
		try{
			Path path = Paths.get(getClass().getClassLoader().getResource(FILE_NAME).toURI());
			commands = Files.lines(path).collect(Collectors.toList());
			service=new CommandServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOne(){
		List<String> commandsResponse=commands.stream()
				.map(s->{
					return service.executeCommands(s);
				}).collect(Collectors.toList());
		commandsResponse.forEach(System.out::println);
	}
}
