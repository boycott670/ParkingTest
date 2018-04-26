package com.sqli.echallenge.parking.vehicles;

import com.sqli.echallenge.parking.slots.ParkingBay;

public abstract class Vehicle
{
  public abstract Class<? extends ParkingBay> appropriateSlot();
}
