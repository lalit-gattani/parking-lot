package com.interview.parking.entity;

import com.interview.parking.enums.ParkingType;

public class Bike extends Vehicle{
    public Bike(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public ParkingType getParkingType() {
        return ParkingType.BIKE_PARKING;
    }
}
