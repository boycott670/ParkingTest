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

}
