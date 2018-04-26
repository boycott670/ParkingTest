package com.sqli.echallenge.parking.slots;

import com.sqli.echallenge.parking.vehicles.Vehicle;

public abstract class ParkingBay
{
  Vehicle parkedVehicle;

  public final boolean isAvailable()
  {
    return parkedVehicle == null;
  }

  public final void parkCar(final Vehicle vehicle)
  {
    parkedVehicle = vehicle;
  }

  public final boolean unparkCar()
  {
    if (!isAvailable())
    {
      parkedVehicle = null;
      return true;
    }

    return false;
  }

  @Override
  public String toString()
  {
    return parkedVehicle.toString();
  }

}
