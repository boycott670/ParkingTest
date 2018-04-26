package com.sqli.echallenge.parking.slots;

public abstract class ParkingBay
{
  Character parkedCar;

  public final boolean isAvailable()
  {
    return parkedCar == null;
  }

  public final void parkCar(char car)
  {
    parkedCar = car;
  }

  public final boolean unparkCar()
  {
    if (!isAvailable())
    {
      parkedCar = null;
      return true;
    }

    return false;
  }

}
