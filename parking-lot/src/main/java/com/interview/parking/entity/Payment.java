package com.interview.parking.entity;

import com.interview.parking.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Payment {
    private String vehicleNumber;
    private String parkingId;
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.INITIATED;
    private long amount;
    @Builder.Default
    private LocalDateTime paymentStartTime = LocalDateTime.now();
    private LocalDateTime paymentEndTime;
    @Builder.Default
    private String paymentId = UUID.randomUUID().toString();
}
