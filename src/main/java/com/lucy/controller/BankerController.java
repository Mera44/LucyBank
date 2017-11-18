package com.lucy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/banker")
@Controller
public class BankerController {
	
	@RequestMapping(value="/customer/add", method=RequestMethod.GET)
	public String addCustomer() {
		return "addCustomerForm";
	}
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public String customers() {
		return "customers";
	}
	
}
