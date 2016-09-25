package com.car.rent.vehicle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rent.domain.Vehicle;

public interface VehicleDAO extends JpaRepository<Vehicle, Integer> {

	void deleteByVehicleId(Integer vehicleId);

	Vehicle findByVehicleId(Integer vehicleId);

	List<Vehicle> findByBrandIgnoringCaseLikeAndTypeIgnoringCaseLikeAndVehiclePlateNumberIgnoringCaseLikeAndNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualOrderByVehicleIdDesc(
			String brand, String type, String VehiclePlateNumber, Integer NumberOfSeatsGreater, Double DailyPrice);

	List<Vehicle> findByBrandIgnoringCaseLikeAndTypeIgnoringCaseLikeAndVehiclePlateNumberIgnoringCaseLikeAndNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualAndIsAvailableOrderByVehicleIdDesc(
			String brand, String type, String VehiclePlateNumber, Integer NumberOfSeatsGreater, Double DailyPrice,Boolean isAvailable);	
}
