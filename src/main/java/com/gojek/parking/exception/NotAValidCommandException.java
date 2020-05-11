package com.gojek.parking.exception;

/**
 * ParkingEmpty Exception
 * @author pranav.tiwary@vuclip.com
 *
 */
public class NotAValidCommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320157768094168083L;

	public NotAValidCommandException(String message) {
		super(message);
	}
}
