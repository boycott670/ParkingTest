package com.sqli.echallenge.parking;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ParkingTest
{
  private static final int FIRSTUPEDESTRIANUEXITUINDEX = 8;
  private Parking parking;

  @Before
  public void setUp()
  {
    parking = new ParkingBuilder().withSquareSize(5)
        .withPedestrianExit(FIRSTUPEDESTRIANUEXITUINDEX)
        .withPedestrianExit(12)
        .withDisabledBay(5)
        .withDisabledBay(10)
        .build();
  }

  @Test
  public void testToString()
  {
    assertEquals("UUUUU\nU=UU@\n@U=UU\nUUUUU\nUUUUU", parking.toString());
    assertEquals(23, parking.getAvailableBays());
  }
}
