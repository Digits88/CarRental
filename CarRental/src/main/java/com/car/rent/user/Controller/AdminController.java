package com.car.rent.user.Controller;

import java.security.Principal;

 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@RequestMapping(value ="homePage", method = RequestMethod.GET)
	public String signUpPage(Model model,Principal principal){
	  
		return "/admin/HomePage";
	}
	/*@RequestMapping(value = "users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		return "/AdminView/listUser";
		
	}*/
	/*@RequestMapping(value = "users", method = RequestMethod.GET)
    public String redirectRoot() {
        return "redirect:/UserView/listUser";
    }*/
}
