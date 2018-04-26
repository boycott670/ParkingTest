package com.sqli.echallenge.parking;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import com.sqli.echallenge.parking.parkers.DefaultVehiclesParker;
import com.sqli.echallenge.parking.parkers.VehiclesParker;
import com.sqli.echallenge.parking.slots.ParkingBay;

final class Parking
{
  private final VehiclesParker parker = new DefaultVehiclesParker();
  private final ParkingBay[] bays;

  Parking(final ParkingBay[] bays)
  {
    this.bays = bays;
  }

  long getAvailableBays()
  {
    return Arrays.stream(bays)
        .filter(Objects::nonNull)
        .filter(ParkingBay::isAvailable)
        .count();
  }

  int parkVehicle(final char vehcile)
  {
    return parker.parkVehicle(VehiclesFactory.get(vehcile), bays);
  }

  boolean unparkCar(final int slotIndexToUnpark)
  {
    return Optional.ofNullable(bays[slotIndexToUnpark])
        .map(ParkingBay::unparkCar)
        .orElse(false);
  }

  @Override
  public String toString()
  {
    final StringBuilder report = new StringBuilder();

    final int numberOfLanes = (int) Math.sqrt(bays.length);

    boolean reverseLane = false;

    for (int laneIndex = 0; laneIndex < numberOfLanes; laneIndex++)
    {
      final StringBuilder laneReport = new StringBuilder();

      for (int slotIndex = laneIndex * numberOfLanes; slotIndex < laneIndex * numberOfLanes + numberOfLanes; slotIndex++)
      {
        laneReport.append(Optional.ofNullable(bays[slotIndex])
            .map(Object::toString)
            .orElse("="));
      }

      report.append(reverseLane ? laneReport.reverse() : laneReport)
          .append("\n");

      reverseLane = !reverseLane;
    }

    return report.toString()
        .trim();
  }
}
