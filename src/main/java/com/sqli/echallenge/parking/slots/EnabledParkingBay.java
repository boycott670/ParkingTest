package com.sqli.echallenge.parking.slots;

public final class EnabledParkingBay extends ParkingBay
{

  @Override
  public String toString()
  {
    return isAvailable() ? "U" : super.toString();
  }

}
