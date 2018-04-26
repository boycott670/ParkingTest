package com.sqli.echallenge.parking.parkers;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sqli.echallenge.parking.slots.ParkingBay;
import com.sqli.echallenge.parking.vehicles.Vehicle;

public final class DefaultVehiclesParker implements VehiclesParker
{

  private int closestDistanceToPedestrianExit(final int bayIndex, final ParkingBay[] parkingBays)
  {
    return IntStream.range(0, parkingBays.length)
        .filter(index -> index != bayIndex)
        .filter(index -> parkingBays[index] == null)
        .map(index -> index - bayIndex)
        .map(Math::abs)
        .min()
        .getAsInt();
  }

  public int parkVehicle(Vehicle vehicle, ParkingBay[] parkingBays)
  {
    final Comparator<Entry<Integer, Integer>> distanceToClosestPedestrianExitComparator = Entry.comparingByValue();

    final int slotIndexToPark = IntStream.range(0, parkingBays.length)
        .filter(index -> vehicle.appropriateSlot()
            .isInstance(parkingBays[index]))
        .filter(index -> parkingBays[index].isAvailable())
        .boxed()
        .collect(Collectors.toMap(Function.identity(), index -> closestDistanceToPedestrianExit(index, parkingBays)))
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

    parkingBays[slotIndexToPark].parkCar(vehicle);

    return slotIndexToPark;
  }

}
