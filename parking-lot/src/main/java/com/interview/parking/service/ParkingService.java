package com.interview.parking.service;

import com.interview.parking.dto.ParkVehicleRequestDto;
import com.interview.parking.dto.ParkVehicleResponseDto;
import com.interview.parking.entity.*;
import com.interview.parking.enums.ParkingType;
import com.interview.parking.enums.PaymentStatus;
import com.interview.parking.enums.VehicleType;
import com.interview.parking.repository.ParkingRepository;
import com.interview.parking.repository.PaymentRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.interview.parking.repository.ParkingLotRepository.getParkingLot;

@Service
public class ParkingService {
    @Autowired
    ParkingFactory parkingFactory;

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    PaymentRepository paymentRepository;

    ParkingLot parkingLot = getParkingLot();


    public ParkVehicleResponseDto parkVehicle(ParkVehicleRequestDto parkVehicleRequestDto) {
        VehicleType vehicleType = parkVehicleRequestDto.getVehicleType();
        String vehicleNumber = parkVehicleRequestDto.getVehicleNumber();
        Boolean isHandicap = parkVehicleRequestDto.getIsHandicap();
        Vehicle vehicle = parkingFactory.getVehicle(vehicleType, vehicleNumber);
        ParkingType parkingType = isHandicap ? ParkingType.HANDICAP_PARKING : vehicle.getParkingType();
        ParkingSpot parkingSpot = parkingLot.parkVehicle(vehicle, parkingType);
        Parking parking = Parking
                .builder()
                .parkingLotId(parkingLot.getLotId())
                .parkingSpotNumber(parkingSpot.getParkingSpotNumber())
                .vehicleNumber(vehicleNumber)
                .parkingType(parkingType)
                .build();
        parkingRepository.save(parking);

    return ParkVehicleResponseDto.builder()
            .parkingId(parking.getParkingId())
            .parkingSpotNumber(parking.getParkingSpotNumber())
            .parkingType(parking.getParkingType())
            .parkingStartTime(parking.getStartTime())
            .build();
    }

    public long getAmount(String parkingId) {
        Parking parking = parkingRepository.getParking(parkingId);
        if(parking == null)
            throw new RuntimeException("No Parking Found");
        long hours = parking.getStartTime().until(LocalDateTime.now(), ChronoUnit.HOURS);
        long cost = parkingLot.getCost(parking.getParkingType());
        return (hours + 1) * cost;
    }

    public String pay(String parkingId, PaymentStatus paymentStatus) throws InterruptedException {
        Parking parking = parkingRepository.getParking(parkingId);
        if(parking == null)
            throw new RuntimeException("No Parking Found");

        List<Payment> paymentList = paymentRepository.getPaymentsByParkingId(parkingId);
        paymentList = paymentList.stream().filter(e -> e.getPaymentStatus().equals(PaymentStatus.COMPLETED)).collect(Collectors.toList());
        if(!paymentList.isEmpty())
            throw new RuntimeException("Payment Already Completed");

        Payment payment = Payment
                .builder()
                .vehicleNumber(parking.getVehicleNumber())
                .parkingId(parkingId)
                .amount(getAmount(parkingId))
                .build();

        Thread.sleep(2000);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentEndTime(LocalDateTime.now());
        paymentRepository.save(payment);

        if(!payment.getPaymentStatus().equals(PaymentStatus.COMPLETED))
            throw new RuntimeException("Payment Not Successful");

        return payment.getPaymentId();
    }

    public void leave(String parkingId) {
        Parking parking = parkingRepository.getParking(parkingId);
        if(parking == null)
            throw new RuntimeException("Parking not found");
        checkIfParkingCanLeave(parkingId);
        parkingLot.leave(parking);
        parking.setEndTime(LocalDateTime.now());
        parking.setIsOngoing(Boolean.FALSE);
        parkingRepository.save(parking);
    }

    private void checkIfParkingCanLeave(String parkingId) {
        List<Payment> paymentList = paymentRepository.getPaymentsByParkingId(parkingId);
        if(paymentList.isEmpty()) {
            throw new RuntimeException("Payment Not Found");
        }
        paymentList = paymentList.stream().filter(e -> e.getPaymentStatus().equals(PaymentStatus.COMPLETED)).collect(Collectors.toList());
        if(paymentList.isEmpty())
            throw new RuntimeException("Payment Not Completed");
    }
}
