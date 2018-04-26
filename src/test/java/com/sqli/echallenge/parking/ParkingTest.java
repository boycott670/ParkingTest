package com.sqli.echallenge.parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

  @Test
  public void testParkCarVehiculeTypeC()
  {
    assertEquals(7, parking.parkVehicle('C'));
    assertEquals(22, parking.getAvailableBays());
  }

  @Test
  public void testParkCarVehiculeTypeM()
  {
    assertEquals(7, parking.parkVehicle('M'));
    assertEquals(22, parking.getAvailableBays());
  }

  @Test
  public void testParkCarThreeVehicules()
  {
    assertEquals(7, parking.parkVehicle('C'));
    assertEquals(22, parking.getAvailableBays());

    assertEquals(9, parking.parkVehicle('M'));
    assertEquals(21, parking.getAvailableBays());

    assertEquals(11, parking.parkVehicle('V'));
    assertEquals(20, parking.getAvailableBays());

    assertEquals("UUUUU\nM=CU@\n@V=UU\nUUUUU\nUUUUU", parking.toString());
  }

  @Test
  public void testParkCarDisabled()
  {
    assertEquals(10, parking.parkVehicle('D'));
    assertEquals(22, parking.getAvailableBays());

    assertEquals(5, parking.parkVehicle('D'));
    assertEquals(21, parking.getAvailableBays());

    assertEquals(-1, parking.parkVehicle('D'));
    assertEquals(21, parking.getAvailableBays());

    assertEquals("UUUUU\nU=UUD\nDU=UU\nUUUUU\nUUUUU", parking.toString());
  }

  @Test
  public void testUnparkCar()
  {
    final int firstCarBayIndex = parking.parkVehicle('C');
    assertTrue(parking.unparkCar(firstCarBayIndex));
    assertEquals(23, parking.getAvailableBays());
    assertFalse(parking.unparkCar(firstCarBayIndex));

    final int secondCarBayIndex = parking.parkVehicle('D');
    assertTrue(parking.unparkCar(secondCarBayIndex));
    assertEquals(23, parking.getAvailableBays());
    assertFalse(parking.unparkCar(secondCarBayIndex));

    assertFalse(parking.unparkCar(FIRSTUPEDESTRIANUEXITUINDEX));
  }

  @Test
  public void testUnparkCarToString()
  {
    assertEquals(7, parking.parkVehicle('C'));
    assertEquals(9, parking.parkVehicle('C'));
    assertEquals(11, parking.parkVehicle('M'));
    assertEquals(13, parking.parkVehicle('M'));
    assertEquals(10, parking.parkVehicle('D'));
    assertEquals(5, parking.parkVehicle('D'));
    assertEquals(-1, parking.parkVehicle('D'));

    assertFalse(parking.unparkCar(3));
    assertEquals("UUUUU\nC=CUD\nDM=MU\nUUUUU\nUUUUU", parking.toString());
    assertEquals(17, parking.getAvailableBays());

    assertTrue(parking.unparkCar(13));
    assertEquals("UUUUU\nC=CUD\nDM=UU\nUUUUU\nUUUUU", parking.toString());
    assertEquals(18, parking.getAvailableBays());

    assertEquals(13, parking.parkVehicle('V'));
  }

  @Test
  public void testBuildBasicParking()
  {
    final Parking p = new ParkingBuilder().withSquareSize(4)
        .build();
    assertEquals(16, p.getAvailableBays());
  }

  @Test
  public void testBuildParkingWithPedestrianExit()
  {
    final Parking p = new ParkingBuilder().withSquareSize(3)
        .withPedestrianExit(5)
        .build();
    assertEquals(8, p.getAvailableBays());
  }

  @Test
  public void testBuildParkingWithDisabledSlot()
  {
    final Parking p = new ParkingBuilder().withSquareSize(2)
        .withDisabledBay(2)
        .build();
    assertEquals(4, p.getAvailableBays());
  }
}
