package com.sqli.echallenge.parking;

import java.util.Arrays;

import com.sqli.echallenge.parking.entities.ParkingBay;
import com.sqli.echallenge.parking.entities.ParkingSlot;
import com.sqli.echallenge.parking.visitors.DefaultParkingDrawerVisitor;
import com.sqli.echallenge.parking.visitors.ParkingDrawerVisitor;

public final class Parking
{
	private final ParkingDrawerVisitor parkingDrawerVisitor;
	
	private final ParkingSlot[] slots;

	public Parking(ParkingSlot[] slots)
	{
		parkingDrawerVisitor = new DefaultParkingDrawerVisitor();
		
		this.slots = slots;
	}
	
	@Override
	public String toString()
	{
		parkingDrawerVisitor.clear();
		
		Arrays.stream(slots).forEachOrdered(parkingDrawerVisitor::visit);
		
		return parkingDrawerVisitor.draw();
	}
	
	public long getAvailableBays()
	{
		return Arrays.stream(slots)
				.filter(ParkingBay.class::isInstance)
				.map(ParkingBay.class::cast)
				.filter(ParkingBay::isEmpty)
				.count();
	}
}
