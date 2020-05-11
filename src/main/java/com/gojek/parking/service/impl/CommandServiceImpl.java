package com.gojek.parking.service.impl;

import com.gojek.parking.Command;
import com.gojek.parking.StringConstants;
import com.gojek.parking.exception.NotAValidCommandException;
import com.gojek.parking.input.validator.ValidatorUtil;
import com.gojek.parking.service.ICommandService;
import com.gojek.parking.service.IParkingService;

/**
 * Command Service
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class CommandServiceImpl implements ICommandService {

	private IParkingService parkingService;

	public CommandServiceImpl(){
		parkingService= new ParkingServiceImpl();
	}

	@Override
	public String executeCommands(String commandString) {
		Command command=null;
		try {
			command=ValidatorUtil.isValid(commandString);
		} catch (NotAValidCommandException e) {
			return StringConstants.COMMAND_NOT_VALID;
		}
		String response=null;
		try{
			switch(command.getCommand()){
			case StringConstants.CREATE_PARKING_LOT:
				response=parkingService.create(command.getArgument()[0]);
				break;
			case StringConstants.PARK:
				response=parkingService.park(command.getArgument()[0], command.getArgument()[1]);
				break;
			case StringConstants.LEAVE:
				response=parkingService.leave(command.getArgument()[0]);
				break;
			case StringConstants.STATUS:
				response=parkingService.status();
				break;
			case StringConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
				response=parkingService.findRegistrationNumbersByColour(command.getArgument()[0]);
				break;
			case StringConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
				response=parkingService.findSlotNumbersByColour(command.getArgument()[0]);
				break;
			case StringConstants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
				response=parkingService.findSlotNumberByRegistrationNumber(command.getArgument()[0]);
				break;
			}
		}catch(NotAValidCommandException ex){
			response=StringConstants.COMMAND_NOT_VALID;
		}catch(Exception ex){
			ex.printStackTrace();
			response=StringConstants.SERVER_ERROR;
		}
		return response;
	}

}
