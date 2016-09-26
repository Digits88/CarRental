package com.car.rent.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.rent.domain.Vehicle;
import com.car.rent.vehicle.domain.VehicleSpec;
import com.car.rent.vehicle.service.VehicleService;

//vhicle controller
@RequestMapping("/vehicle/")
@Controller
public class VehicleController {
	final private String URL = "/vehicle/";

//	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String ssearchVehicles(VehicleSpec vehicleSpec, @ModelAttribute("available") String available, Model data) {
		List<Vehicle> found;
		if (vehicleSpec == null)
			found = vehicleService.getAll();
		else {
			if (available.equals("NO")) {
				vehicleSpec.setIsAvailable(false);
			} else if (available.equals("YES")) {
				vehicleSpec.setIsAvailable(true);
			} else {
				vehicleSpec.setIsAvailable(null);
			}
			found = vehicleService.search(vehicleSpec);
		}
		System.out.println(found);
		data.addAttribute("available", available);		
		data.addAttribute("vehicle",vehicleSpec);
		data.addAttribute("vehicles", found);
		return URL + "search";
	}

	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String searchVehicles(
			@ModelAttribute("vehicle") VehicleSpec vehicleSpec, 
			@ModelAttribute("available") String available,
			Model data) {
		
		List<Vehicle> found;
		if (vehicleSpec == null)
			found = vehicleService.getAll();
		else {
			if (available.equals("NO")) {
				vehicleSpec.setIsAvailable(false);
			} else if (available.equals("YES")) {
				vehicleSpec.setIsAvailable(true);
			} else {
				vehicleSpec.setIsAvailable(null);
			}
			found = vehicleService.search(vehicleSpec);
		}
		System.out.println(found);
		data.addAttribute("available", available);		
		data.addAttribute("vehicle",vehicleSpec);
		data.addAttribute("vehicles", found);
		return URL + "search";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String searchVehicles(Model data) {
		VehicleSpec vs = new VehicleSpec();
		data.addAttribute("vehicle", vs);
		data.addAttribute("available", "ALL");
		data.addAttribute("vehicles", new ArrayList<Vehicle>());
		return URL + "search";
	}

	@RequestMapping(value = "vehicles", method = RequestMethod.GET)
	public String vehicles(Model data) {
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

	@RequestMapping(value = "delete/{vehicleId}")
	public @ResponseBody String delete(@PathVariable int vehicleId, Model data) {
		vehicleService.deleteVehicle(vehicleId);
		return "deleted";
	}

	@RequestMapping(value = "update/{vehicleId}")
	public String update(@PathVariable int vehicleId, Model model) {
		Vehicle vehicle = this.vehicleService.find(vehicleId);
		model.addAttribute("updated", false);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", vehicle.getIsAvailable() ? "YES" : "NO");
		return URL + "update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update
//    (@ModelAttribute("vehicle") @Valid Vehicle vehicle,
//		@ModelAttribute("available") String available,
	       (@Valid Vehicle vehicle,
			String available,
			BindingResult result,
			Model model) {
		System.out.println("***********************************************************8");
		System.out.println(result);
		System.out.println(vehicle);
		System.out.println(available);
		if (!result.hasErrors()) {
			if (available.equalsIgnoreCase("YES")) {
				vehicle.setIsAvailable(true);
			} else {
				vehicle.setIsAvailable(false);
			}
			vehicle.setIsAvailable(true);
			vehicleService.update(vehicle);
			model.addAttribute("updated", true);
		}

		model.addAttribute("updated", true);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", vehicle.getIsAvailable() ? "YES" : "NO");
		return URL + "update";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		System.out.println("Add GET");
		Vehicle vehicle = new Vehicle();
		vehicle.setIsAvailable(false);
		model.addAttribute("added", false);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", "NO");
		return URL + "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid Vehicle vehicle, @ModelAttribute("available") String available, BindingResult result,
			Model model) {

		if (available.equalsIgnoreCase("YES")) {
			vehicle.setIsAvailable(true);
		} else {
			vehicle.setIsAvailable(false);
		}
		if (!result.hasErrors()) {
			System.out.println("Added to db");
			vehicleService.addVehicle(vehicle);
		}

		model.addAttribute("added", true);
		vehicle = new Vehicle();
		vehicle.setIsAvailable(false);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", "NO");
		return URL + "add";
	}

	// RESTfull service

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@Autowired
	private VehicleService vehicleService;
}
