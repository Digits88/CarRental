package com.car.rent.vehicle.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rent.domain.Vehicle;
import com.car.rent.vehicle.dao.VehicleDAO;
import com.car.rent.vehicle.domain.VehicleSpec;

@Service
@Transactional
public class VehicleService {

	public void addVehicle(Vehicle car) {
		vehicleDAO.save(car);
	}

	public void setVehicleDAO(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	public Vehicle vehicleByVehicleId(Integer vehicleId) {
		return this.vehicleDAO.findByVehicleId(vehicleId);
	}

	public List<Vehicle> search(VehicleSpec vSpec) {
		List<Vehicle> vehicles = null;
		if (vSpec.getIsAvailable())
			vehicles = this.vehicleDAO
					.findByBrandIgnoringCaseLikeAndTypeIgnoringCaseLikeAndVehiclePlateNumberIgnoringCaseLikeAndNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualOrderByVehicleIdDesc(
							vSpec.getBrand(), vSpec.getType(), vSpec.getVehiclePlateNumber(), vSpec.getNumberOfSeats(),
							vSpec.getDailyPrice());
		else {
			this.vehicleDAO
					.findByBrandIgnoringCaseLikeAndTypeIgnoringCaseLikeAndVehiclePlateNumberIgnoringCaseLikeAndNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualAndIsAvailableOrderByVehicleIdDesc(
							vSpec.getBrand(), vSpec.getType(), vSpec.getVehiclePlateNumber(), vSpec.getNumberOfSeats(),
							vSpec.getDailyPrice(), vSpec.getIsAvailable());
		}
		return vehicles;
	}

	public List<Vehicle> getAll() {
		return vehicleDAO.findAll();
	}

	public void deleteVehicle(Integer vehicleId) {
		this.vehicleDAO.deleteByVehicleId(vehicleId);
	}

	@Autowired
	private VehicleDAO vehicleDAO;

}
