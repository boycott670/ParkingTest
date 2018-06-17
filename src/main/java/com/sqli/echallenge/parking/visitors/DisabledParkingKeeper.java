package com.sqli.echallenge.parking.visitors;

import java.util.function.Predicate;

import com.sqli.echallenge.parking.entities.DisabledParkingBay;
import com.sqli.echallenge.parking.entities.ParkingBay;

public final class DisabledParkingKeeper extends ParkingKeeper
{

	@Override
	Predicate<ParkingBay> validAsNextAvailableParkingBayValidator()
	{
		return DisabledParkingBay.class::isInstance;
	}

}
