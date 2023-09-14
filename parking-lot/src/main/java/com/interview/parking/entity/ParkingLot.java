package com.interview.parking.entity;

import com.interview.parking.enums.ParkingType;
import lombok.Data;
import lombok.Getter;

import java.util.*;

import static com.interview.parking.repository.ParkingLotRepository.updateParkingLot;

@Data
public class ParkingLot {
  @Getter private Map<ParkingType, List<ParkingSpot>> parkingLot;

  private Set<String> isVehicleParked;
  private Map<ParkingType, Integer> parkingTypeCapacity;

  private Map<ParkingType, Integer> parkingTypeCost;
  @Getter private String lotId;

  private int capacity;

  public ParkingLot(
      Map<ParkingType, Integer> parkingTypeCost, Map<ParkingType, Integer> parkingTypeCapacity) {
    this.capacity = parkingTypeCapacity.values().size();
    this.parkingTypeCost = parkingTypeCost;
    this.parkingTypeCapacity = parkingTypeCapacity;
    this.lotId = UUID.randomUUID().toString();
    this.parkingLot = new HashMap<>();
    this.isVehicleParked = new HashSet<>();
    int currParkingLotNum = 0;
    for (Map.Entry<ParkingType, Integer> parkingTypeCapacityEntry :
        parkingTypeCapacity.entrySet()) {
      List<ParkingSpot> parkingSpotList = new ArrayList<>();
      for (int i = 0; i < parkingTypeCapacityEntry.getValue(); i++) {
        ParkingSpot parkingSpot =
            new ParkingSpot(parkingTypeCapacityEntry.getKey(), currParkingLotNum);
        currParkingLotNum++;
        parkingSpotList.add(parkingSpot);
      }
      this.parkingLot.put(parkingTypeCapacityEntry.getKey(), parkingSpotList);
    }
  }

  public ParkingSpot parkVehicle(Vehicle vehicle, ParkingType parkingType) {
    if (isVehicleParked.contains(vehicle.getVehicleNumber()))
      throw new RuntimeException("Vehicle Already Parked");

    ParkingSpot parkingSpot = getAvailable(parkingType);
    if (parkingSpot == null) {
      throw new RuntimeException("Space Not Available");
    }
    parkingSpot.setCurrVehicle(vehicle);
    parkingSpot.setIsOccupied(Boolean.TRUE);
    isVehicleParked.add(vehicle.getVehicleNumber());
    updateParkingLot(this);
    return parkingSpot;
  }

  private ParkingSpot getAvailable(ParkingType parkingType) {
    List<ParkingSpot> parkingSpotList = parkingLot.getOrDefault(parkingType, null);
    if (parkingSpotList == null) {
      throw new RuntimeException("Invalid Parking Type");
    }

    for (ParkingSpot parkingSpot : parkingSpotList) {
      if (!parkingSpot.getIsOccupied()) return parkingSpot;
    }
    return null;
  }

  public long getCost(ParkingType parkingType) {
    return this.parkingTypeCost.getOrDefault(parkingType, 0);
  }

  public void leave(Parking parking) {
    ParkingSpot parkingSpot = getParkingSpot(parking.getParkingSpotNumber());
    if (parkingSpot == null) {
      throw new RuntimeException("No Parking Spot Found");
    }
    parkingSpot.setIsOccupied(Boolean.FALSE);
    parkingSpot.setCurrVehicle(null);
    isVehicleParked.remove(parking.getVehicleNumber());
  }

  private ParkingSpot getParkingSpot(int parkingSpotNumber) {
    for (List<ParkingSpot> parkingSpotList : parkingLot.values()) {
      for (ParkingSpot parkingSpot : parkingSpotList) {
        if (parkingSpot.getParkingSpotNumber() == parkingSpotNumber) return parkingSpot;
      }
    }
    return null;
  }
}
