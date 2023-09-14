package com.interview.parking.repository;

import com.interview.parking.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentRepository {
  private static final Map<String, Payment> PAYMENT_REPOSITORY = new HashMap<>();
  private static final Map<String, List<Payment>> PAYMENT_REPOSITORY_PARKING_ID = new HashMap<>();

  private PaymentRepository() {}

    public Map<String, Payment> getAllPayments() {
      return PAYMENT_REPOSITORY;
    }

    public Payment getPayment(String paymentId) {
      return PAYMENT_REPOSITORY.getOrDefault(paymentId, null);
    }

  public List<Payment> getPaymentsByParkingId(String parkingId) {
    return PAYMENT_REPOSITORY_PARKING_ID.getOrDefault(parkingId, new ArrayList<>());
  }

    public void save(Payment payment) {
        PAYMENT_REPOSITORY.put(payment.getPaymentId(), payment);
    if (PAYMENT_REPOSITORY_PARKING_ID.containsKey(payment.getParkingId()))
      PAYMENT_REPOSITORY_PARKING_ID.get(payment.getParkingId()).add(payment);
    else
      PAYMENT_REPOSITORY_PARKING_ID.put(payment.getParkingId(), new ArrayList<>(List.of(payment)));
    }
}
