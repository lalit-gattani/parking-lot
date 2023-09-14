package com.interview.parking.controller;



import com.interview.parking.entity.Parking;
import com.interview.parking.entity.ParkingLot;
import com.interview.parking.entity.Payment;
import com.interview.parking.repository.ParkingRepository;
import com.interview.parking.repository.PaymentRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.interview.parking.repository.ParkingLotRepository.getParkingLot;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/parking-lot")
    public ParkingLot getParkingLotAPI() {
        return getParkingLot();
    }

    @GetMapping("/parking/all")
    public Collection<Parking> getAllParkings() {
        return parkingRepository.getAllParkings().values();
    }

    @GetMapping("/payment/all")
    public Collection<Payment> getAllPayments() {
        return paymentRepository.getAllPayments().values();
    }
}
