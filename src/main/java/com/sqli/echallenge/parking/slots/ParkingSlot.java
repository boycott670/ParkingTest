package com.sqli.echallenge.parking.slots;

public abstract class ParkingSlot
{
  public abstract String toString();

  public abstract boolean isAvailable();

  public abstract void parkCar(final char car);
}
