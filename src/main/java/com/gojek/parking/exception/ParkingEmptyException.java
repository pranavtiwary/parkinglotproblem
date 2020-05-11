package com.gojek.parking.exception;

/**
 * ParkingEmpty Exception
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ParkingEmptyException extends RuntimeException {

	private static final long serialVersionUID = 2L;

	public ParkingEmptyException(String message) {
		super(message);
	}
}
