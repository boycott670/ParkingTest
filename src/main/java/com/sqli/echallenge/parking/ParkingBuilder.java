package com.sqli.echallenge.parking;

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

  Parking build()
  {
    return new Parking(parkingSlots);
  }
}
