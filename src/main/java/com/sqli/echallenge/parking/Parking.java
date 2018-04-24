package com.sqli.echallenge.parking;

import java.util.Arrays;

import com.sqli.echallenge.parking.slots.ParkingSlot;

final class Parking
{
  private final ParkingSlot[] slots;

  Parking(final ParkingSlot[] slots)
  {
    this.slots = slots;
  }

  long getAvailableBays()
  {
    return Arrays.stream(slots)
        .filter(ParkingSlot::isAvailable)
        .count();
  }

  @Override
  public String toString()
  {
    final StringBuilder report = new StringBuilder();

    final int numberOfLanes = (int) Math.sqrt(slots.length);

    boolean reverseLane = false;

    for (int laneIndex = 0; laneIndex < numberOfLanes; laneIndex++)
    {
      final StringBuilder laneReport = new StringBuilder();

      for (int slotIndex = laneIndex * numberOfLanes; slotIndex < laneIndex * numberOfLanes + numberOfLanes; slotIndex++)
      {
        laneReport.append(slots[slotIndex]);
      }

      report.append(reverseLane ? laneReport.reverse() : laneReport).append("\n");

      reverseLane = !reverseLane;
    }

    return report.toString().trim();
  }
}
