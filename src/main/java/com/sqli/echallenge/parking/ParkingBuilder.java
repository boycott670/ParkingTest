package com.sqli.echallenge.parking;

import java.util.stream.IntStream;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.NonDisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;

final class ParkingBuilder
{
  private ParkingBay[] parkingBays;

  ParkingBuilder withSquareSize(final int size)
  {
    parkingBays = IntStream.range(0, size * size)
        .mapToObj(__ -> new NonDisabledParkingBay())
        .toArray(ParkingBay[]::new);

    return this;
  }

  ParkingBuilder withPedestrianExit(final int pedestrianExitSlotIndex)
  {
    parkingBays[pedestrianExitSlotIndex] = null;
    return this;
  }

  ParkingBuilder withDisabledBay(final int disabledBaySlotIndex)
  {
    parkingBays[disabledBaySlotIndex] = new DisabledParkingBay();
    return this;
  }

  Parking build()
  {
    return new Parking(parkingBays);
  }
}
