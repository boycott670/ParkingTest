package com.sqli.echallenge.parking.entities;

public final class NonDisabledParkingBay extends ParkingBay
{

	@Override
	public char draw()
	{
		return isEmpty() ? 'U' : super.draw();
	}

}
