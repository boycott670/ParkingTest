package com.sqli.echallenge.parking.vehicles;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;

public final class DisabledVehcile extends Vehicle
{

  @Override
  public Class<? extends ParkingBay> appropriateSlot()
  {
    return DisabledParkingBay.class;
  }

}
