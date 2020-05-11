package com.gojek.parking.input.validator;

import java.util.HashMap;
import java.util.Map;

import com.gojek.parking.Command;
import com.gojek.parking.StringConstants;
import com.gojek.parking.exception.NotAValidCommandException;

/**
 * Validator
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ValidatorUtil {
	
	public static final Map<String, Integer> commands= new HashMap<>();

	static{
		commands.put(StringConstants.CREATE_PARKING_LOT, new Integer(2));
		commands.put(StringConstants.PARK, new Integer(3));
		commands.put(StringConstants.LEAVE, new Integer(2));
		commands.put(StringConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR, new Integer(2));
		commands.put(StringConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, new Integer(2));
		commands.put(StringConstants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER, new Integer(2));
		commands.put(StringConstants.STATUS,new Integer(1));
	}
	
	public static Command isValid(String command) throws NotAValidCommandException{
		String[] split = command.split(StringConstants.SPACE);
		Integer argumentLength = commands.get(split[0]);
		if(null==argumentLength || argumentLength.intValue()!=split.length){
			throw new NotAValidCommandException(StringConstants.COMMAND_NOT_VALID);
		}
		String[] arg = new String[argumentLength.intValue()-1];
		for(int i=1; i<argumentLength.intValue();i++){
			arg[i-1]=split[i];
		}
		return new Command(split[0],arg);
	}
}
