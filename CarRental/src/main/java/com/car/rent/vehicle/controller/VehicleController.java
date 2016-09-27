package com.car.rent.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.rent.domain.Vehicle;
import com.car.rent.vehicle.domain.VehicleSpec;
import com.car.rent.vehicle.service.VehicleService;

@RequestMapping("/vehicle/")
@Controller
public class VehicleController {
	final private String URL = "/vehicle/";

	@GetMapping("search")
	public String searchVehiclePage(Model model) {
		model.addAttribute("vs", new VehicleSpec());
		model.addAttribute("vehicles", new ArrayList<>());
		return URL + "search";
	}

	@PostMapping("search")
	public String searchVehicles(@ModelAttribute VehicleSpec vs, Model data) {
		System.out.println("Searching...");
		Boolean a;
		if (vs.getAvailable() == null) {
			a = null;
		} else if (vs.getAvailable().equalsIgnoreCase("NO")) {
			a = false;
		} else {
			a = true;
		}
		List<Vehicle> found = this.vehicleService.search(vs.getMinSeats(), vs.getMinPrice(), vs.getMaxPrice(), a);
		System.out.println(found);
		data.addAttribute("available", a);
		data.addAttribute("vehicles", found);
		return URL + "search";
	}

	@GetMapping("vehicles")
	public String vehicles(Model data) {
		List<Vehicle> found;
		found = vehicleService.getAll();
		data.addAttribute("vehicles", found);
		return URL + "vehicles";
	}

	@GetMapping("detail/{vehicleId}")
	public String vehicleDetail(@PathVariable Integer vehicleId, Model model) {
		Vehicle vehicle = vehicleService.vehicleByVehicleId(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return URL + "detail";
	}

	@PostMapping("delete/{vehicleId}")
	public @ResponseBody String delete(@PathVariable int vehicleId,
			@RequestHeader(value = "referer", required = false) final String referer) {
		vehicleService.deleteVehicle(vehicleId);
		return "deleted";
	}

	@GetMapping("update/{vehicleId}")
	public String update(@PathVariable int vehicleId, Model model) {
		Vehicle vehicle = this.vehicleService.find(vehicleId);
		model.addAttribute("updated", false);
		model.addAttribute("vehicle", vehicle);
		return URL + "update";
	}

	@PostMapping("update")
	public String update(@Valid Vehicle vehicle, BindingResult result, Model model) {
		System.out.println("***********************************************************8");
		System.out.println(result);
		System.out.println(vehicle);
		if (!result.hasErrors()) {
			vehicleService.update(vehicle);
			model.addAttribute("updated", true);
		}
		System.out.println(vehicle);
		model.addAttribute("updated", true);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", vehicle.getIsAvailable() ? "YES" : "NO");
		return URL + "update";
	}

	@GetMapping("add")
	public String add(Model model) {
		System.out.println("Add GET");
		Vehicle vehicle = new Vehicle();
		vehicle.setIsAvailable(false);
		model.addAttribute("added", false);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", "NO");
		return URL + "add";
	}

	@PostMapping("add")
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

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@Autowired
	private VehicleService vehicleService;

}
