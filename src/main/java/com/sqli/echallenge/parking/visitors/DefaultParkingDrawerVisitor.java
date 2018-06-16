package com.sqli.echallenge.parking.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sqli.echallenge.parking.entities.ParkingSlot;

public final class DefaultParkingDrawerVisitor implements ParkingDrawerVisitor
{

	private final List<ParkingSlot> parkingSlots = new ArrayList<>();

	@Override
	public void visit(ParkingSlot parkingSlot)
	{
		parkingSlots.add(parkingSlot);
	}

	@Override
	public void clear()
	{
		parkingSlots.clear();
	}

	private List<String> getParkingSlotRowDrawings(final int parkingSlotsRowSize)
	{
		final List<String> parkingSlotRowDrawings = new ArrayList<>();
		
		final Supplier<StringBuffer> emptyStringBufferSupplier = StringBuffer::new;
		
		StringBuffer parkingSlotRowDrawing = emptyStringBufferSupplier.get();
		
		for (int index = 0 ; index < parkingSlots.size() ; index++)
		{
			parkingSlotRowDrawing.append(parkingSlots.get(index).draw());
			
			if ((index + 1) % parkingSlotsRowSize == 0)
			{
				parkingSlotRowDrawings.add(parkingSlotRowDrawing.toString());
				
				parkingSlotRowDrawing = emptyStringBufferSupplier.get();
			}
		}
		
		return parkingSlotRowDrawings;
	}
	
	private String getConcatenatedParkingSlotRowDrawings(final List<String> parkingSlotRowDrawings)
	{
		final IntFunction<String> parkingSlotRowDrawer = index ->
		{
			if (index % 2 == 0)
			{
				return parkingSlotRowDrawings.get(index);
			}
			else
			{
				return new StringBuffer(parkingSlotRowDrawings.get(index)).reverse().toString();
			}
		};
		
		return IntStream.range(0, parkingSlotRowDrawings.size())
				.mapToObj(parkingSlotRowDrawer)
				.collect(Collectors.joining("\n"));
	}

	@Override
	public String draw()
	{
		final int parkingSlotsRowSize = new Double(Math.sqrt(parkingSlots.size())).intValue();
		
		return getConcatenatedParkingSlotRowDrawings(getParkingSlotRowDrawings(parkingSlotsRowSize));
	}

}
