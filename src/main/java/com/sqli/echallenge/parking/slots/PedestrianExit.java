package com.sqli.echallenge.parking.slots;

public final class PedestrianExit extends ParkingSlot
{

  @Override
  public String toString()
  {
    return "=";
  }

  @Override
  public boolean isAvailable()
  {
    return false;
  }

  @Override
  public void parkCar(char car)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean unparkCar()
  {
    return false;
  }

}
