package com.gojek.parking.exception;

public class ParkingSlotNotInitilizedException extends Exception {

	
	private static final long serialVersionUID = 1903238345056004306L;

	public ParkingSlotNotInitilizedException(String message) {
		super(message);
	}
}
