package com.sqli.echallenge.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.sqli.echallenge.parking.vehicles.Bike;
import com.sqli.echallenge.parking.vehicles.Car;
import com.sqli.echallenge.parking.vehicles.DisabledVehcile;
import com.sqli.echallenge.parking.vehicles.MotorCycle;
import com.sqli.echallenge.parking.vehicles.Vehicle;

final class VehiclesFactory
{
  private VehiclesFactory()
  {

  }

  static Vehicle get(final char vehcileCode)
  {
    final Map<? super Character, Supplier<? extends Vehicle>> factory = new HashMap<>();

    factory.put(Car.CODE, Car::new);
    factory.put(MotorCycle.CODE, MotorCycle::new);
    factory.put(Bike.CODE, Bike::new);
    factory.put(DisabledVehcile.CODE, DisabledVehcile::new);

    return Optional.ofNullable(factory.get(vehcileCode))
        .orElseThrow(IllegalArgumentException::new)
        .get();
  }
}
