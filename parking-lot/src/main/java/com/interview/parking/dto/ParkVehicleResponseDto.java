package com.interview.parking.dto;

import com.interview.parking.enums.ParkingType;
import com.interview.parking.enums.VehicleType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkVehicleResponseDto {
   private String parkingId;
   private int parkingSpotNumber;
   private ParkingType parkingType;
   private LocalDateTime parkingStartTime;
}
