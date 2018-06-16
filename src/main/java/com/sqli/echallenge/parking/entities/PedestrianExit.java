package com.sqli.echallenge.parking.entities;

public final class PedestrianExit implements ParkingSlot
{

	@Override
	public char draw()
	{
		return '=';
	}

}
