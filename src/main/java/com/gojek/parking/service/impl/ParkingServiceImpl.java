package com.gojek.parking.service.impl;

import java.util.List;

import com.gojek.parking.StringConstants;
import com.gojek.parking.dao.IParkingDao;
import com.gojek.parking.dao.impl.ParkingDaoImpl;
import com.gojek.parking.entiy.ParkingSlot;
import com.gojek.parking.entiy.Vehicle;
import com.gojek.parking.exception.NotAValidCommandException;
import com.gojek.parking.exception.ParkingEmptyException;
import com.gojek.parking.exception.ParkingFullException;
import com.gojek.parking.exception.ParkingSlotNotInitilizedException;
import com.gojek.parking.service.IParkingService;

/**
 * Parking service
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ParkingServiceImpl implements IParkingService {

	private IParkingDao dao;

	public ParkingServiceImpl(){
	}

	@Override
	public String park(String registrationNumber, String colour) {
		if(null==dao){
			return StringConstants.PARKING_NOT_INTIALIZED;
		}
		ParkingSlot slot=null;
		try {
			slot = dao.parkVehicle(new Vehicle(registrationNumber, colour));
		} catch (ParkingFullException e) {
			return StringConstants.PARKINGLOT_FULL;
		}
		return StringConstants.PARKINGLOT_ALLOTED_SUCCESSFUL.replaceFirst("%X%", ""+(1+slot.getSlotLocation()));
	}

	@Override
	public String leave(String slotNumber)throws NotAValidCommandException {
		if(null==dao){
			return StringConstants.PARKING_NOT_INTIALIZED;
		}
		ParkingSlot slot=null;
		try {
			int parseInt = Integer.parseInt(slotNumber);
			slot = dao.leave(parseInt-1);
		} catch (NumberFormatException e) {
			throw new NotAValidCommandException("Command not valid");
		} catch (ParkingEmptyException e) {
			return StringConstants.PARKINGLOT_EMPTY;
		}
		return StringConstants.PARKINGLOT_LEAVE_SUCCESS.replaceFirst("%X%", ""+(1+slot.getSlotLocation()));
	}

	@Override
	public String status() throws ParkingSlotNotInitilizedException{
		if(null==dao){
			return StringConstants.PARKING_NOT_INTIALIZED;
		}
		List<ParkingSlot> slots = dao.findAllOccupiedSlots();
		StringBuilder sb = new StringBuilder(StringConstants.STATUS_HEADER);
		for (ParkingSlot parkingSlot : slots) {
			sb.append("\n");
			sb.append(""+(1+parkingSlot.getSlotLocation()));
			sb.append("\t");
			sb.append(parkingSlot.getVehicle().getRegistrationNumber());
			sb.append("\t");
			sb.append(parkingSlot.getVehicle().getColour());
		}
		return sb.toString();
	}

	@Override
	public String findRegistrationNumbersByColour(String colour) {
		if(null==dao){
			return StringConstants.PARKING_NOT_INTIALIZED;
		}
		List<ParkingSlot> slotsByVehicleColour = dao.findAllSlotByColour(colour);
		StringBuilder sb =  new StringBuilder();
		for (ParkingSlot parkingSlot : slotsByVehicleColour) {
			sb.append(parkingSlot.getVehicle().getRegistrationNumber());
			sb.append(", ");
		}
		return sb.substring(0, sb.length()-2);
	}

	@Override
	public String findSlotNumbersByColour(String colour){
		if(null==dao){
			return StringConstants.PARKING_NOT_INTIALIZED;
		}
		List<ParkingSlot> slotsByVehicleColour = dao.findAllSlotByColour(colour);
		StringBuilder sb =  new StringBuilder();
		for (ParkingSlot parkingSlot : slotsByVehicleColour) {
			sb.append(1+parkingSlot.getSlotLocation());
			sb.append(", ");
		}
		return sb.substring(0, sb.length()-2);
	}

	@Override
	public String findSlotNumberByRegistrationNumber(String registrationNumber){
		if(null==dao){
			return StringConstants.PARKING_NOT_INTIALIZED;
		}
		ParkingSlot parkingSlot = dao.findOneByRegistrationNumber(registrationNumber);
		if(null==parkingSlot){
			return StringConstants.REGISTRATION_NOT_FOUND;
		}
		return ""+(parkingSlot.getSlotLocation()+1);
	}

	@Override
	public String create(String size) {
		dao=new ParkingDaoImpl(Integer.parseInt(size));
		return StringConstants.CREATED_SUCCESSFULL.replaceFirst("%X%", size);
	}

}
