package com.gojek.parking;

import com.gojek.input.handler.IInputHandler;
import com.gojek.input.handler.impl.InputHandlerImpl;

/**
 * Entry Class
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		IInputHandler inputHandler = new InputHandlerImpl();
		if(null!=args && args.length==1){
			inputHandler.handleFile(args[0]);
		}else{
			inputHandler.handleInput();
		}
	}
}
