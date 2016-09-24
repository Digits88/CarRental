package com.car.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rent.domain.Vehicle;
public interface VehicleDAO extends JpaRepository<Vehicle, Long> {

}
