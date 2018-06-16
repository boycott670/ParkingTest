package com.sqli.echallenge.parking.visitors;

import com.sqli.echallenge.parking.entities.ParkingSlot;

public interface ParkingDrawerVisitor
{
	void visit(final ParkingSlot parkingSlot);
	
	void clear();

	String draw();
}
