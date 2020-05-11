package com.gojek.parking.dao;

import java.util.List;

import com.gojek.parking.entiy.ParkingSlot;
import com.gojek.parking.entiy.Vehicle;
import com.gojek.parking.exception.ParkingEmptyException;
import com.gojek.parking.exception.ParkingFullException;

public interface IParkingDao {

	public List<ParkingSlot> findAllSlotByColour(String colour);
	
	public List<ParkingSlot> findAllOccupiedSlots();
	
	public ParkingSlot findOneByRegistrationNumber(String regNumber);
	
	public ParkingSlot parkVehicle(Vehicle vehicle) throws ParkingFullException;

	public ParkingSlot leave(int parseInt) throws ParkingEmptyException;
}
