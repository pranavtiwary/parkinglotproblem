package com.gojek.parking;

public class Command {

	private String command;
	private String[] argument;

	public Command(String command, String[] argument) {
		super();
		this.command = command;
		this.argument = argument;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String[] getArgument() {
		return argument;
	}
	public void setArgument(String[] argument) {
		this.argument = argument;
	}
}
