package com.sqli.echallenge.parking.vehicles;

import com.sqli.echallenge.parking.slots.EnabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;

public final class Bike extends Vehicle
{

  public static final char CODE = 'V';

  @Override
  public Class<? extends ParkingBay> appropriateSlot()
  {
    return EnabledParkingBay.class;
  }

  @Override
  public String toString()
  {
    return String.valueOf(CODE);
  }

}
