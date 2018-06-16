package com.sqli.echallenge.parking.entities;

public abstract class ParkingBay implements ParkingSlot
{
	private Character parkedCar;

	public final void parkCar(final char car)
	{
		parkedCar = car;
	}

	public final void unparkCar()
	{
		parkedCar = null;
	}

	public final boolean isEmpty()
	{
		return parkedCar == null;
	}

	@Override
	public char draw()
	{
		return parkedCar;
	}
}
