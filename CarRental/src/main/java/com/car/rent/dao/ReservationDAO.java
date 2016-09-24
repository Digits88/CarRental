package com.car.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rent.domain.Reservation;

public interface ReservationDAO extends  JpaRepository<Reservation, Long> {

}
