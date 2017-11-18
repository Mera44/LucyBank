package com.lucy.controller;

import javax.servlet.http.HttpSession;
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

@RequestMapping("/customer")
@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String customerWelcomePage(Model model, HttpSession httpSession) {
		Customer customer = new Customer();
		httpSession.setAttribute("logedCustomer",customer);
		Customer loggedCustomer = (Customer)httpSession.getAttribute("loggedCustomer");
		model.addAttribute("accounts", loggedCustomer.getAccounts());
		return "welcome";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String customerProfile(HttpSession httpSession, Model model) {
		Customer loggedCustomer = (Customer)httpSession.getAttribute("loggedCustomer");
		if(loggedCustomer==null)
			return "redirect:/logout";//remember to set the logout page
		model.addAttribute("profile", loggedCustomer.getProfile());
		return "customerProfile";
	}
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCustomerForm(@ModelAttribute("newCustomer") Customer customer) {
		return "addCustomoerForm";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("newCustomer") Customer customer, BindingResult 
			bindingResult, RedirectAttributes redirectAttribute) {
		customerService.save(customer);
		if(bindingResult.hasErrors())
			return "addCustomerForm";
		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/customer/customerDetail";
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


