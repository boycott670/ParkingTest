package com.sqli.echallenge.parking.parkers;

import com.sqli.echallenge.parking.slots.ParkingBay;
import com.sqli.echallenge.parking.vehicles.Vehicle;

public interface VehiclesParker
{
  int parkVehicle(final Vehicle vehicle, final ParkingBay[] parkingBays);
}
