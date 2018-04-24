package com.sqli.echallenge.parking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.NonDisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingSlot;
import com.sqli.echallenge.parking.slots.PedestrianExit;

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

  private int closestDistanceToPedestrianExit(final int slotIndex)
  {
    return IntStream.range(0, slots.length)
        .filter(index -> index != slotIndex)
        .filter(index -> slots[index] instanceof PedestrianExit)
        .map(index -> index - slotIndex)
        .map(Math::abs)
        .min()
        .getAsInt();
  }

  int parkCar(final char car)
  {
    final int slotIndexToPark = IntStream.range(0, slots.length)
        .filter(index -> (car != 'D' ? NonDisabledParkingBay.class : DisabledParkingBay.class)
            .isAssignableFrom(slots[index].getClass()))
        .filter(index -> slots[index].isAvailable())
        .mapToObj(Integer::valueOf)
        .collect(Collectors.toMap(Function.identity(), index -> closestDistanceToPedestrianExit(index)))
        .entrySet()
        .stream()
        .sorted(Comparator.comparing(Entry::getValue))
        .findFirst()
        .map(Entry::getKey)
        .orElse(-1);

    if (slotIndexToPark == -1)
    {
      return slotIndexToPark;
    }

    slots[slotIndexToPark].parkCar(car);

    return slotIndexToPark;
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
