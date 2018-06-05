package com.sqli.echallenge.parking;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.sqli.echallenge.parking.slots.DisabledParkingBay;
import com.sqli.echallenge.parking.slots.NonDisabledParkingBay;
import com.sqli.echallenge.parking.slots.ParkingBay;
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
  
  private OptionalInt indexToClosestParkingSlot(final int fromIndex, final IntUnaryOperator nextIndex, final IntPredicate isValidAsNextIndex, final Predicate<? super ParkingSlot> isValidAsClosestParkingSlot)
  {
	  return IntStream.iterate(nextIndex.applyAsInt(fromIndex), nextIndex).limit(slots.length).filter(isValidAsNextIndex).filter(index -> isValidAsClosestParkingSlot.test(slots[index])).findFirst();
  }

  int parkCar(final char car)
  {
	  final Predicate<ParkingSlot> isValidParkingBay = parkingSlot -> (car == 'D' ? DisabledParkingBay.class : NonDisabledParkingBay.class).isInstance(parkingSlot) && ((ParkingBay)parkingSlot).isAvailable();
	  
	  final Supplier<IntStream> pedestrianExitIndexes = () -> IntStream.range(0, slots.length).filter(index -> slots[index] instanceof PedestrianExit);
	  
	  final Map<Integer, OptionalInt> pedestrianExitIndexToLeftClosestAvailableParkingBayMapping = pedestrianExitIndexes.get().boxed().collect(Collectors.toMap(Function.identity(), pedestrianExitIndex -> indexToClosestParkingSlot(pedestrianExitIndex, index -> index - 1, index -> index >= 0, isValidParkingBay)));
	  
	  final Map<Integer, OptionalInt> pedestrianExitIndexToRightClosestAvailableParkingBayMapping = pedestrianExitIndexes.get().boxed().collect(Collectors.toMap(Function.identity(), pedestrianExitIndex -> indexToClosestParkingSlot(pedestrianExitIndex, index -> index + 1, index -> index < slots.length, isValidParkingBay)));
	  
	  final int indexOfClosestParkingBay = Stream.of(pedestrianExitIndexToLeftClosestAvailableParkingBayMapping, pedestrianExitIndexToRightClosestAvailableParkingBayMapping).map(Map::entrySet).flatMap(Collection::stream).filter(entry -> entry.getValue().isPresent()).min(Comparator.comparingInt((Entry<Integer, OptionalInt> entry) -> Math.abs(entry.getKey() - entry.getValue().getAsInt())).thenComparing(Entry.comparingByKey())).flatMap(entry -> Optional.of(entry.getValue().getAsInt())).orElse(-1);
	  
	  if (indexOfClosestParkingBay != -1)
	  {
		  slots[indexOfClosestParkingBay].parkCar(car);
	  }
	  
	  return indexOfClosestParkingBay;
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
