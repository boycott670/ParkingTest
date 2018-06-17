package com.sqli.echallenge.parking.visitors;

import java.util.function.Predicate;

import com.sqli.echallenge.parking.entities.ParkingBay;
import com.sqli.echallenge.parking.entities.ParkingSlot;
import com.sqli.echallenge.parking.entities.PedestrianExit;

public abstract class ParkingKeeper extends ParkingSlotsVisitor
{
	private int lookupIndex = 1;
	
	abstract Predicate<ParkingBay> validAsNextAvailableParkingBayValidator();
	
	private boolean isValidAsNextAvailableParkingSlot(final ParkingSlot parkingSlot)
	{
		return parkingSlot instanceof ParkingBay && ((ParkingBay)parkingSlot).isEmpty() && validAsNextAvailableParkingBayValidator().test((ParkingBay)parkingSlot);
	}
	
	public final ParkingBay nextAvailableParkingBay()
	{
		while(lookupIndex < parkingSlots.size())
		{
			for (int index = 0 ; index < parkingSlots.size() ; index++)
			{
				if (parkingSlots.get(index) instanceof PedestrianExit)
				{
					final int leftLookupIndexToCheck = index - lookupIndex;
					
					final int rightLookupIndexToCheck = index + lookupIndex;
					
					if (leftLookupIndexToCheck >= 0 && isValidAsNextAvailableParkingSlot(parkingSlots.get(leftLookupIndexToCheck)))
					{
						return (ParkingBay)parkingSlots.get(leftLookupIndexToCheck);
					}
					else if (rightLookupIndexToCheck < parkingSlots.size() && isValidAsNextAvailableParkingSlot(parkingSlots.get(rightLookupIndexToCheck)))
					{
						return (ParkingBay)parkingSlots.get(rightLookupIndexToCheck);
					}
				}
			}
			
			lookupIndex++;
		}
		
		return null;
	}
}
