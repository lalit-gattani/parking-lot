package com.interview.parking.service;

import com.interview.parking.entity.Bike;
import com.interview.parking.entity.Car;
import com.interview.parking.entity.Vehicle;
import com.interview.parking.enums.VehicleType;
import org.springframework.stereotype.Service;

@Service
public class ParkingFactory {

    public Vehicle getVehicle(VehicleType vehicleType, String vehicleNumber) {
        switch (vehicleType) {
            case CAR:
                return new Car(vehicleNumber);
            case BIKE:
                return new Bike(vehicleNumber);
        }
        throw new RuntimeException("No Vehicle Found of this Type");
    }
}
