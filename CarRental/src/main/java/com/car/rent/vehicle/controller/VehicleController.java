package com.car.rent.vehicle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.rent.domain.Vehicle;
import com.car.rent.vehicle.domain.VehicleSpec;
import com.car.rent.vehicle.service.VehicleService;

@RequestMapping("/vehicle/")
@Controller
public class VehicleController {
	final private String URL = "/vehicle/";

	@RequestMapping(value = "vehicles", method = RequestMethod.POST)
	public String searchVehicles(VehicleSpec vehicleSpec, Model data) {

		List<Vehicle> found;
		if (vehicleSpec == null)
			found = vehicleService.getAll();
		else
			found = vehicleService.search(vehicleSpec);
		data.addAttribute("vehicles", found);
		return URL + "vehicles";
	}

	@RequestMapping(value = "vehicles", method = RequestMethod.GET)
	public String searchVehicles(Model data) {
		List<Vehicle> found;
		found = vehicleService.getAll();
		data.addAttribute("vehicles", found);
		return URL + "vehicles";
	}

	@RequestMapping("detail/{vehicleId}")
	public String vehicleDetail(@PathVariable Integer vehicleId, Model model) {
		Vehicle vehicle = vehicleService.vehicleByVehicleId(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return URL + "detail";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		Vehicle vehicle = new Vehicle();
		vehicle.setIsAvailable(false);
		model.addAttribute("vehicle", vehicle);
		return URL + "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid Vehicle vehicle, BindingResult result) {
		if (!result.hasErrors()) {
			vehicle.setIsAvailable(true);
			vehicleService.addVehicle(vehicle);
		}
		return URL + "add";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(@RequestParam VehicleSpec vehicleSpec, Integer vehicleId, Model data) {
		vehicleService.deleteVehicle(vehicleId);
		// this.searchVehicles(vehicleSpec, data);
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@Autowired
	private VehicleService vehicleService;
}
