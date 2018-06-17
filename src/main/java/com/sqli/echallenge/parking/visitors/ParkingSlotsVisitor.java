package com.sqli.echallenge.parking.visitors;

import java.util.ArrayList;
import java.util.List;

import com.sqli.echallenge.parking.entities.ParkingSlot;

public abstract class ParkingSlotsVisitor
{
	final List<ParkingSlot> parkingSlots = new ArrayList<>();
	
	public final void visit(final ParkingSlot parkingSlot)
	{
		parkingSlots.add(parkingSlot);
	}

	public final void clear()
	{
		parkingSlots.clear();
	}
}
