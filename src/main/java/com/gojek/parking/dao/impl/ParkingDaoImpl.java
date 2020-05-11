package com.gojek.parking.dao.impl;

import java.util.List;

import com.gojek.parking.dao.IParkingDao;
import com.gojek.parking.entiy.ParkingSlot;
import com.gojek.parking.entiy.Vehicle;
import com.gojek.parking.exception.ParkingEmptyException;
import com.gojek.parking.exception.ParkingFullException;

/**
 * Holds parking data
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ParkingDaoImpl implements IParkingDao {

	private DataBaseDriver db;

	public ParkingDaoImpl(int size) {
		db=new DataBaseDriver(size);
		for(int i=size-1;i>=0;i--){
			try{
				db.push(new ParkingSlot(i));
			}catch(Exception ex){

			}
		}
	}

	public List<ParkingSlot> findAllSlotByColour(String colour) {
		return db.findAllSlotByColour(colour);
	}

	public List<ParkingSlot> findAllOccupiedSlots() {
		return db.findAllOccupiedSlots();
	}

	public ParkingSlot findOneByRegistrationNumber(String regNumber) {
		return db.findOneByRegistrationNumber(regNumber);
	}

	@Override
	public ParkingSlot parkVehicle(Vehicle vehicle) throws ParkingFullException {
		ParkingSlot parkingSlot = db.pop();
		parkingSlot.setVehicle(vehicle);
		db.save(parkingSlot);
		return parkingSlot;
	}

	@Override
	public ParkingSlot leave(int parkingSlotNumber) throws ParkingEmptyException{
		return db.leave(parkingSlotNumber);
	}

}
