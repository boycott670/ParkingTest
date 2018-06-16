package com.sqli.echallenge.parking.entities;

public final class DisabledParkingBay extends ParkingBay
{
	@Override
	public char draw()
	{
		return isEmpty() ? '@' : super.draw();
	}
}
