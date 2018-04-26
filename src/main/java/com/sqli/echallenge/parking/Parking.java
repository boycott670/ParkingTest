package com.sqli.echallenge.parking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.NonDisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;

final class Parking
{
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

  private int closestDistanceToPedestrianExit(final int slotIndex)
  {
    return IntStream.range(0, bays.length)
        .filter(index -> index != slotIndex)
        .filter(index -> bays[index] == null)
        .map(index -> index - slotIndex)
        .map(Math::abs)
        .min()
        .getAsInt();
  }

  int parkCar(final char car)
  {
    final Comparator<Entry<Integer, Integer>> distanceToClosestPedestrianExitComparator = Entry.comparingByValue();

    final int slotIndexToPark = IntStream.range(0, bays.length)
        .filter(index -> (car != 'D' ? NonDisabledParkingBay.class : DisabledParkingBay.class).isInstance(bays[index]))
        .filter(index -> bays[index].isAvailable())
        .boxed()
        .collect(Collectors.toMap(Function.identity(), this::closestDistanceToPedestrianExit))
        .entrySet()
        .stream()
        .sorted(distanceToClosestPedestrianExitComparator.thenComparing(Entry.comparingByKey()))
        .findFirst()
        .map(Entry::getKey)
        .orElse(-1);

    if (slotIndexToPark == -1)
    {
      return slotIndexToPark;
    }

    bays[slotIndexToPark].parkCar(car);

    return slotIndexToPark;
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

      report.append(reverseLane ? laneReport.reverse() : laneReport).append("\n");

      reverseLane = !reverseLane;
    }

    return report.toString().trim();
  }
}
