package com.interview.parking.dto;

import com.interview.parking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkVehicleRequestDto {
    @NonNull
    private String vehicleNumber;
    @NonNull
    private VehicleType vehicleType;
    @NonNull
    private Boolean isHandicap;
}
