package com.interview.parking.entity;

import com.interview.parking.enums.ParkingType;
import lombok.Getter;

public abstract class Vehicle {


    @Getter
    private String vehicleNumber;

    public abstract ParkingType getParkingType();
    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
