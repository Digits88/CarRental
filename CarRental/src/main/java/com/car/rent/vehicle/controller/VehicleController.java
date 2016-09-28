package com.car.rent.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.rent.domain.Person;
import com.car.rent.domain.Vehicle;
import com.car.rent.vehicle.domain.VehicleSpec;
import com.car.rent.vehicle.service.VehicleService;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.rent.domain.Person;
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

	@GetMapping("vehicles")
	public String vehicles(HttpSession session, Model data) {
		setRole(session, data);
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

	// @Secured("ROLE_ADMIN")
	@PostMapping("delete")
	public @ResponseBody String delete(int vehicleId, HttpSession mySession) {
		authenticate(mySession);
		vehicleService.deleteVehicle(vehicleId);
		return "redirect:" + URL + "vehicles";
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("update/{vehicleId}")
	public String update(@PathVariable int vehicleId, HttpSession mySession, Model model) {
		authenticate(mySession);
		Vehicle vehicle = this.vehicleService.find(vehicleId);
		model.addAttribute("updated", false);
		model.addAttribute("vehicle", vehicle);
		return URL + "update";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("update")
	public String update(@Valid Vehicle vehicle, BindingResult result, HttpSession mySession, Model model) {

		authenticate(mySession);

		if (!result.hasErrors()) {
			System.out.println(vehicle);
			vehicleService.update(vehicle);
			model.addAttribute("updated", true);
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("available", vehicle.getIsAvailable() ? "YES" : "NO");
		} else {
			System.out.println(result);
			System.out.println("not Working");
			model.addAttribute("updated", false);
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("available", vehicle.getIsAvailable() ? "YES" : "NO");
		}
		return "redirect:" + URL + "vehicles";
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("add")
	public String add(HttpSession mySession, Model model) {
		authenticate(mySession);

		Vehicle vehicle = new Vehicle();
		vehicle.setIsAvailable(false);
		model.addAttribute("added", false);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("available", "NO");

		return URL + "add";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("add")
	public String add(@Valid Vehicle vehicle, BindingResult result, @ModelAttribute("available") String available,
			HttpSession mySession, Model model) {

		authenticate(mySession);

		if (available.equalsIgnoreCase("YES")) {
			vehicle.setIsAvailable(true);
		} else {
			vehicle.setIsAvailable(false);
		}
		if (!result.hasErrors()) {
			vehicle.setIsAvailable(false);
			model.addAttribute("added", true);
			System.out.println("Added to db");
			vehicleService.addVehicle(vehicle);
			return URL + "add";
		} else {
			model.addAttribute("added", false);
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("available", "NO");
			return URL + "add";
		}
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@Autowired
	private VehicleService vehicleService;

	// not used yet
	@PostMapping("search")
	public String searchVehicles(@ModelAttribute VehicleSpec vs, HttpSession mySession, Model model) {
		setRole(mySession, model);
		Boolean a;
		if (vs.getAvailable() == null) {
			a = null;
		} else if (vs.getAvailable().equalsIgnoreCase("NO")) {
			a = false;
		} else {
			a = true;
		}
		List<Vehicle> found = this.vehicleService.search(vs.getMinSeats(), vs.getMinPrice(), vs.getMaxPrice(), a);
		model.addAttribute("available", a);
		model.addAttribute("vehicles", found);
		return URL + "search";
	}

	private void setRole(HttpSession session, Model model) {
		if (session.getAttribute("person") != null) {
			model.addAttribute("isAdmin", ((Person) session.getAttribute("person")).isAdmin());
		} else {
			model.addAttribute("isAdmin", false);
		}
	}

	private void authenticate(HttpSession mySession) {
		if (mySession.getAttribute("person") == null || !((Person) mySession.getAttribute("person")).isAdmin()) {
			throw new RuntimeException("Not authenticated to do the operation.");
		}
	}

}