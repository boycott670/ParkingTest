package com.sqli.echallenge.parking.vehicles;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;

public final class DisabledVehcile extends Vehicle
{

  public static final char CODE = 'D';

  @Override
  public Class<? extends ParkingBay> appropriateSlot()
  {
    return DisabledParkingBay.class;
  }

  @Override
  public String toString()
  {
    return String.valueOf(CODE);
  }

}
