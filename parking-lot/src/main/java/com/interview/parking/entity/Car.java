package com.interview.parking.entity;

import com.interview.parking.enums.ParkingType;

public class Car extends Vehicle{
    public Car(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public ParkingType getParkingType() {
        return ParkingType.CAR_PARKING;
    }
}
