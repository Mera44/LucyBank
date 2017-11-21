package com.lucy.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucy.domain.Customer;
import com.lucy.service.CustomerService;
import com.lucy.util.Util;

@RequestMapping("/customer")
@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String customerWelcomePage(Model model) {
		String userName  = Util.getPrincipal();
		System.out.println("this is the current user name : " +userName);
		Customer loggedCustomer =customerService.findByProfileUserName(userName);
		System.out.println("email" + loggedCustomer.getProfile().getEmail());
		model.addAttribute("loggedCustomer", loggedCustomer);
		return "customerWelcome";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String customerProfile(HttpSession httpSession, Model model) {
		Customer loggedCustomer = (Customer)httpSession.getAttribute("loggedCustomer");
		if(loggedCustomer==null)
			return "redirect:/logout";//remember to set the logout page
		model.addAttribute("profile", loggedCustomer.getProfile());
		return "customerProfile";
	}
	
	
	@RequestMapping(value="/customerDetail", method=RequestMethod.GET)
	public String customerDetail() {
		return "customerDetail";
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public String customers() {
		return "customers";
	}
	
}


