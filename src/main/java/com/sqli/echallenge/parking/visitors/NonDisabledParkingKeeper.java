package com.sqli.echallenge.parking.visitors;

import java.util.function.Predicate;

import com.sqli.echallenge.parking.entities.NonDisabledParkingBay;
import com.sqli.echallenge.parking.entities.ParkingBay;

public final class NonDisabledParkingKeeper extends ParkingKeeper
{

	@Override
	Predicate<ParkingBay> validAsNextAvailableParkingBayValidator()
	{
		return NonDisabledParkingBay.class::isInstance;
	}

}
