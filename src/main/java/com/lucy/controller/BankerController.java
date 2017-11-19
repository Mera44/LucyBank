package com.lucy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Customer;
import com.lucy.domain.Role;
import com.lucy.service.CustomerService;

@RequestMapping("/banker")
@Controller
public class BankerController {
	
	@Autowired 
	CustomerService customerService;
	
	@RequestMapping("/welcome")
	public String bankerWelcome(Model model) {
		model.addAttribute("customers",customerService.getCustomers());
		return "bankerWelcome";
	}
	
	@RequestMapping(value="/customer/add", method=RequestMethod.GET)
	public String addCustomerForm(@ModelAttribute("customer") Customer customer) {
		
		
	
		return "addCustomoerForm";
	}
	@RequestMapping(value="/customer/add", method=RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult 
			bindingResult, RedirectAttributes redirectAttribute) {
		System.out.println(customer.getProfile().getUserName());
		Role role = new Role();
		role.setRole("customer");
		
		customer.getProfile().setRole(role);
		customerService.save(customer);
		if(bindingResult.hasErrors())
			return "addCustomerForm";
		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/customer/customerDetail";
	}
	
	
}
