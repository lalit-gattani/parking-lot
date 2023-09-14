package com.interview.parking.entity;

import com.interview.parking.enums.ParkingType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParkingSpot {
    private ParkingType parkingType;

    private Integer parkingSpotNumber;
    private Boolean isOccupied;
    private Vehicle currVehicle;

    public ParkingSpot(ParkingType parkingType, Integer parkingSpotNumber) {
        this.parkingType = parkingType;
        this.parkingSpotNumber = parkingSpotNumber;
        this.currVehicle = null;
        this.isOccupied = Boolean.FALSE;
    }

}
