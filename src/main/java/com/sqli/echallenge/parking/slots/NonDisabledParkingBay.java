package com.sqli.echallenge.parking.slots;

public final class NonDisabledParkingBay extends ParkingBay
{

  @Override
  public String toString()
  {
    return isAvailable() ? "U" : parkedCar.toString();
  }

}
