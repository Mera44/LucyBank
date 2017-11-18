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
	public String addCustomerForm(@ModelAttribute("newCustomer") Customer customer) {
		return "addCustomoerForm";
	}
	@RequestMapping(value="/customer/add", method=RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("newCustomer") Customer customer, BindingResult 
			bindingResult, RedirectAttributes redirectAttribute) {
		customerService.save(customer);
		if(bindingResult.hasErrors())
			return "addCustomerForm";
		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/customer/customerDetail";
	}
	
	
}
