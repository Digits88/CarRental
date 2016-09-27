package com.car.rent.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//loading the index page
	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
	
	  @RequestMapping("/error.html")
	  public String error(HttpServletRequest request, Model model) {
	    model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
	    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
	    String errorMessage = null;
	    if (throwable != null) {
	      errorMessage = throwable.getMessage();
	    }
	    System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
	    throwable.printStackTrace();
	    model.addAttribute("errorMessage", errorMessage);
	    return "error.html";
	  }
}
