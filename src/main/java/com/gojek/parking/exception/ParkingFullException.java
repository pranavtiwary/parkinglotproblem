package com.gojek.parking.exception;

/**
 * ParkingFull Exception
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ParkingFullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParkingFullException(String message) {
		super(message);
	}
}
