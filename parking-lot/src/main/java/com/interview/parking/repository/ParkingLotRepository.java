package com.interview.parking.repository;

import com.interview.parking.entity.ParkingLot;
import com.interview.parking.enums.ParkingType;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingLotRepository {

    private static ParkingLot parkingLot = null;

    public static ParkingLot getParkingLot() {
        if(parkingLot != null)
            return parkingLot;
        else {
            Map<ParkingType, Integer> parkingTypeIntegerMap = new HashMap<>();
            parkingTypeIntegerMap.put(ParkingType.BIKE_PARKING, 10);
            parkingTypeIntegerMap.put(ParkingType.CAR_PARKING, 8);
            parkingTypeIntegerMap.put(ParkingType.HANDICAP_PARKING, 2);
            Map<ParkingType, Integer> parkingCost = new HashMap<>();
            parkingCost.put(ParkingType.BIKE_PARKING, 80);
            parkingCost.put(ParkingType.CAR_PARKING, 10);
            parkingCost.put(ParkingType.HANDICAP_PARKING, 20);
            return new ParkingLot(parkingCost, parkingTypeIntegerMap);
        }
    }

    public static void updateParkingLot(ParkingLot parkingLot) {
        ParkingLotRepository.parkingLot = parkingLot;
    }

    private ParkingLotRepository(){};


}
