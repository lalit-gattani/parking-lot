package com.interview.parking.repository;

import com.interview.parking.entity.Parking;
import com.interview.parking.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingRepository {
  private static final Map<String, Parking> PARKING_REPOSITORY = new HashMap<>();

  private ParkingRepository() {}

    public Map<String, Parking> getAllParkings() {
      return PARKING_REPOSITORY;
    }

    public Parking getParking(String parkingId) {
      return PARKING_REPOSITORY.getOrDefault(parkingId, null);
    }
    
    public void save(Parking parking) {
      PARKING_REPOSITORY.put(parking.getParkingId(), parking);
    }
}
