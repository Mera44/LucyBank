package com.lucy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customer")
@Controller
public class CustomerController {
	@RequestMapping("/profile")
	public String customerProfile() {
		return "customerProfile";
	}
	
}
