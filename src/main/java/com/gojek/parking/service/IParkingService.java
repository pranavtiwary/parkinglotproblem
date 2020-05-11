package com.gojek.parking.service;

import com.gojek.parking.exception.NotAValidCommandException;
import com.gojek.parking.exception.ParkingSlotNotInitilizedException;

public interface IParkingService {

	String park(String registrationNumber, String colour)throws ParkingSlotNotInitilizedException;

	String leave(String slotNumber)throws ParkingSlotNotInitilizedException, NotAValidCommandException;

	String status()throws ParkingSlotNotInitilizedException;

	String findRegistrationNumbersByColour(String colour)throws ParkingSlotNotInitilizedException;

	String findSlotNumbersByColour(String colour)throws ParkingSlotNotInitilizedException;

	String findSlotNumberByRegistrationNumber(String registrationNumber)throws ParkingSlotNotInitilizedException;

	String create(String string);
}
