package com.interview.parking.entity;

import com.interview.parking.enums.ParkingType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Parking {
    private String vehicleNumber;
    @Builder.Default
    private LocalDateTime startTime = LocalDateTime.now();
    @Builder.Default
    private Boolean isOngoing  = Boolean.TRUE;
    private LocalDateTime endTime;
    private String parkingLotId;
    private int parkingSpotNumber;
    private ParkingType parkingType;
  @Builder.Default private String parkingId = UUID.randomUUID().toString();
}
