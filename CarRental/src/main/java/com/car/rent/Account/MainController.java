package com.car.rent.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//loading the index page
	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
}
