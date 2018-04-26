package com.sqli.echallenge.parking.vehicles;

import com.sqli.echallenge.parking.slots.EnabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;

public final class MotorCycle extends Vehicle
{

  public static final char CODE = 'M';

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
