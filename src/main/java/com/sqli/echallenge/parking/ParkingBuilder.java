package com.sqli.echallenge.parking;

import java.util.stream.Stream;

import com.sqli.echallenge.parking.entities.DisabledParkingBay;
import com.sqli.echallenge.parking.entities.NonDisabledParkingBay;
import com.sqli.echallenge.parking.entities.ParkingSlot;
import com.sqli.echallenge.parking.entities.PedestrianExit;

public final class ParkingBuilder
{
	private ParkingSlot[] parkingSlots;
	
	public ParkingBuilder withSquareSize(final int squareSize)
	{
		parkingSlots = Stream.generate(NonDisabledParkingBay::new)
				.limit(squareSize * squareSize)
				.toArray(ParkingSlot[]::new);
		
		return this;
	}
	
	public ParkingBuilder withPedestrianExit(final int pedestrianExit)
	{
		parkingSlots[pedestrianExit] = new PedestrianExit();
		
		return this;
	}
	
	public ParkingBuilder withDisabledBay(final int disabledBay)
	{
		parkingSlots[disabledBay] = new DisabledParkingBay();
		
		return this;
	}
	
	public Parking build()
	{
		return new Parking(parkingSlots);
	}
}
