package com.interview.parking.controller;

import com.interview.parking.dto.ParkVehicleRequestDto;
import com.interview.parking.dto.ParkVehicleResponseDto;
import com.interview.parking.enums.PaymentStatus;
import com.interview.parking.service.ParkingService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lot")
public class ParkingLotController {

    @Autowired
    ParkingService parkingService;
  @PostMapping("/park")
  ParkVehicleResponseDto parkVehicle(@Valid ParkVehicleRequestDto parkVehicleRequestDto) {
      return parkingService.parkVehicle(parkVehicleRequestDto);
  }

    @PostMapping("/payment")
    String pay(@RequestParam String parkingId, @RequestParam PaymentStatus paymentStatus) throws InterruptedException {
        return parkingService.pay(parkingId, paymentStatus);
    }

    @GetMapping("/amount")
    Long getAmount(@RequestParam String parkingId) {
      return parkingService.getAmount(parkingId);
    }

    @PostMapping("/leave")
    String leave(@RequestParam String parkingId) {
      parkingService.leave(parkingId);
      return "SUCCESS";
    }
}
