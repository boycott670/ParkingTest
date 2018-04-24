package com.sqli.echallenge.parking.slots;

public abstract class ParkingBay extends ParkingSlot
{
  Character parkedCar;

  @Override
  public final boolean isAvailable()
  {
    return parkedCar == null;
  }
}
