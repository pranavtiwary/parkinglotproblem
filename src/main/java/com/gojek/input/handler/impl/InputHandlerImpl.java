package com.gojek.input.handler.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.gojek.input.handler.IInputHandler;
import com.gojek.parking.service.ICommandService;
import com.gojek.parking.service.impl.CommandServiceImpl;
/**
 * Class handles the input
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class InputHandlerImpl implements IInputHandler {

	private ICommandService service;

	@Override
	public void handleFile(String filename) {
		List<String> commands= null;
		try{
			Path path = Paths.get(filename);
			commands = Files.lines(path).collect(Collectors.toList());
			service=new CommandServiceImpl();
			List<String> commandsResponse=commands.stream()
					.map(s->{
						return service.executeCommands(s);
					}).collect(Collectors.toList());
			commandsResponse.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleInput() {
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);  
		service=new CommandServiceImpl();
		while(true){
			System.out.println("Input:");
			String command = sc.nextLine();
			try{
				String response = service.executeCommands(command);
				System.out.println(response);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
