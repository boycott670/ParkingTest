package com.sqli.echallenge.parking.slots;

public final class DisabledParkingBay extends ParkingBay
{

  @Override
  public String toString()
  {
    return isAvailable() ? "@" : "D";
  }

}
