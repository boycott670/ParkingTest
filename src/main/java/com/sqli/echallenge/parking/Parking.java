package com.sqli.echallenge.parking;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import com.sqli.echallenge.parking.entities.ParkingBay;
import com.sqli.echallenge.parking.entities.ParkingSlot;
import com.sqli.echallenge.parking.visitors.DefaultParkingDrawer;
import com.sqli.echallenge.parking.visitors.DisabledParkingKeeper;
import com.sqli.echallenge.parking.visitors.NonDisabledParkingKeeper;
import com.sqli.echallenge.parking.visitors.ParkingDrawer;
import com.sqli.echallenge.parking.visitors.ParkingKeeper;

public final class Parking
{
	private final ParkingDrawer parkingDrawer;
	
	private final ParkingKeeper nonDisabledParkingKeeper;
	
	private final ParkingKeeper disabledParkingKeeper;
	
	private final ParkingSlot[] slots;

	public Parking(ParkingSlot[] slots)
	{
		parkingDrawer = new DefaultParkingDrawer();
		
		nonDisabledParkingKeeper = new NonDisabledParkingKeeper();
		
		disabledParkingKeeper = new DisabledParkingKeeper();
		
		this.slots = slots;
	}
	
	@Override
	public String toString()
	{
		parkingDrawer.clear();
		
		Arrays.stream(slots).forEachOrdered(parkingDrawer::visit);
		
		return parkingDrawer.draw();
	}
	
	public long getAvailableBays()
	{
		return Arrays.stream(slots)
				.filter(ParkingBay.class::isInstance)
				.map(ParkingBay.class::cast)
				.filter(ParkingBay::isEmpty)
				.count();
	}
	
	public int parkCar(final char car)
	{
		final ParkingKeeper appropriateParkingKeeper;
		
		if (car == 'D')
		{
			disabledParkingKeeper.clear();
			
			Arrays.stream(slots).forEachOrdered(disabledParkingKeeper::visit);
			
			appropriateParkingKeeper = disabledParkingKeeper;
		}
		else
		{
			nonDisabledParkingKeeper.clear();
			
			Arrays.stream(slots).forEachOrdered(nonDisabledParkingKeeper::visit);
			
			appropriateParkingKeeper = nonDisabledParkingKeeper;
		}
		
		final int nextAvailableParkingBayIndex = Arrays.asList(slots).indexOf(appropriateParkingKeeper.nextAvailableParkingBay());
		
		if (nextAvailableParkingBayIndex != -1)
		{
			((ParkingBay)slots[nextAvailableParkingBayIndex]).parkCar(car);
			
		}

		return nextAvailableParkingBayIndex;
	}
	
	public boolean unparkCar(final int slotIndex)
	{
		final Optional<ParkingBay> bayToUnslot = Optional.of(slots[slotIndex])
				.filter(ParkingBay.class::isInstance)
				.map(ParkingBay.class::cast)
				.filter(((Predicate<ParkingBay>) ParkingBay::isEmpty).negate());
		
		if (bayToUnslot.isPresent())
		{
			bayToUnslot.get().unparkCar();
			
			return true;
		}
		else
		{
			return false;
		}
	}
}
