package com.gojek.parking.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gojek.parking.entiy.ParkingSlot;
import com.gojek.parking.exception.ParkingEmptyException;
import com.gojek.parking.exception.ParkingFullException;

/**
 * Hold the data and query manupulation
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class DataBaseDriver {

	private ParkingSlot[] table;
	private ParkingSlot[] stackOfEmptySlot;
	private int size;
	private int top;

	public DataBaseDriver(int size){
		this.stackOfEmptySlot = new ParkingSlot[size];
		this.table=new ParkingSlot[size];
		this.top = -1;
		this.size = size;
	}

	public void push(ParkingSlot obj) throws ParkingFullException{
		if (top >= size)
			throw new ParkingFullException("Parking is full");
		stackOfEmptySlot[++top] = obj;
	}

	public ParkingSlot pop() throws ParkingFullException{
		if (top < 0) throw new ParkingFullException("No Vehicle is parked");
		ParkingSlot obj = stackOfEmptySlot[top--];
		stackOfEmptySlot[top + 1] = null;
		return obj;
	}

	public int size(){
		return size;
	}

	public int elements(){
		return top + 1;
	}

	public List<ParkingSlot> findAllSlotByColour(String colour) {
		List<ParkingSlot> response = findAllOccupiedSlots().stream()
				.filter(s->s!=null)
				.filter(s->s.getVehicle()!=null)
				.filter(s->s.getVehicle().getColour().equalsIgnoreCase(colour))
				.collect(Collectors.toList());
		return response;
	}

	public List<ParkingSlot> findAllOccupiedSlots() {
		List<ParkingSlot> response= new ArrayList<>();
		for (ParkingSlot parkingSlot : table) {
			if(null==parkingSlot || parkingSlot.getVehicle()==null){
				continue;
			}
			response.add(parkingSlot);
		}
		return response;
	}

	public ParkingSlot findOneByRegistrationNumber(String regNumber) {
		Optional<ParkingSlot> findFirst = findAllOccupiedSlots().stream()
				.filter(s->s!=null)
				.filter(s->s.getVehicle()!=null)
				.filter(s->s.getVehicle().getRegistrationNumber().equalsIgnoreCase(regNumber))
				.findFirst();
		if(findFirst.isPresent())
			return findFirst.get();
		else
			return null;
	}

	public void save(ParkingSlot parkingSlot) {
		table[parkingSlot.getSlotLocation()]=parkingSlot;
	}

	public ParkingSlot leave(int parkingSlotNumber) throws ParkingEmptyException {
		if(null==table[parkingSlotNumber]){
			throw new ParkingEmptyException("Parking lot is empty");
		}
		table[parkingSlotNumber]=null;
		ParkingSlot parkingSlot = new ParkingSlot(parkingSlotNumber);
		try {
			sortParkingSlot();
		} catch (ParkingFullException e) {
			e.printStackTrace();
		}
		return parkingSlot;
	}

	private void sortParkingSlot() {
		this.top = -1;			
		for(int i=size-1;i>=0;i--){
			try{
				if(null==table[i])
					push(new ParkingSlot(i));
			}catch(Exception ex){

			}
		}
	}
}
