package com.car.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rent.domain.Payment;

public interface PaymentDAO extends JpaRepository<Payment, Long> {

}
