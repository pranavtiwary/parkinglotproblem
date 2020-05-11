package com.gojek.parking.entiy;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ParkingSlot {

	private int slotLocation;
	private Vehicle vehicle;

	public ParkingSlot(int i) {
		slotLocation=i;
	}

	public int getSlotLocation() {
		return slotLocation;
	}

	public void setSlotLocation(int slotLocation) {
		this.slotLocation = slotLocation;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
