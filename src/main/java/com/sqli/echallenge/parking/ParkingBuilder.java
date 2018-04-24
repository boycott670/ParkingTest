package com.sqli.echallenge.parking;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.NonDisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingSlot;
import com.sqli.echallenge.parking.slots.PedestrianExit;

final class ParkingBuilder
{
  private ParkingSlot[] parkingSlots;

  ParkingBuilder withSquareSize(final int size)
  {
    parkingSlots = IntStream.range(0, size * size)
        .mapToObj(__ -> new NonDisabledParkingBay())
        .toArray(ParkingSlot[]::new);

    return this;
  }

  ParkingBuilder withPedestrianExit(final int pedestrianExitSlotIndex)
  {
    parkingSlots[pedestrianExitSlotIndex] = new PedestrianExit();
    return this;
  }

  ParkingBuilder withDisabledBay(final int disabledBaySlotIndex)
  {
    parkingSlots[disabledBaySlotIndex] = new DisabledParkingBay();
    return this;
  }

  private boolean containsPedestrianExit()
  {
    return Arrays.stream(parkingSlots)
        .anyMatch(PedestrianExit.class::isInstance);
  }

  Parking build()
  {
    if (!containsPedestrianExit())
    {
      throw new IllegalStateException("The parking must contain at least one pedestrian exit");
    }

    return new Parking(parkingSlots);
  }
}
