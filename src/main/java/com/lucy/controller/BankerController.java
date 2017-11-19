package com.lucy.controller;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.CheckingAccount;
import com.lucy.domain.CreditAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.Role;
import com.lucy.domain.SavingAccount;
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
			bindingResult, @RequestParam("accTypes") String[] accountsType, RedirectAttributes redirectAttribute) {
		Role role = new Role();
		role.setRole("customer");
		
		for(String accTyp:accountsType) {		
			if("saving".equals(accTyp)) {
				SavingAccount savingAcc = new SavingAccount();
				savingAcc.setTypeAccount("Saving");
				savingAcc.setAccountNumber(accountNumber());
				customer.getAccounts().add(savingAcc);
			}
	
			if("checking".equals(accTyp)) {
				CheckingAccount checkAcc = new CheckingAccount();
				checkAcc.setTypeAccount("Checking");
				checkAcc.setAccountNumber(accountNumber());
				customer.getAccounts().add(checkAcc);
			}
			if("credit".equals(accTyp)) {
				CreditAccount creditAcc = new CreditAccount();
				creditAcc.setTypeAccount("Credit");
				creditAcc.setAccountNumber(accountNumber());
				customer.getAccounts().add(creditAcc);
			}
		}
		
		customer.getProfile().setRole(role);
		customerService.save(customer);
		if(bindingResult.hasErrors())
			return "addCustomerForm";
		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/customer/customerDetail";
	}
	private Integer accountNumber() {
		Random rand = new Random();
		return rand.nextInt(99998) + 10001;
	}
	@RequestMapping(value="/customer/detail/{id}", method=RequestMethod.GET)
	public String customerDetail(@PathVariable("id") long id, Model model) {
		System.out.println("id in detail" + id);
		System.out.println(customerService.getCustomer(id).getProfile().getFirstName());
		model.addAttribute("customerDetail", customerService.getCustomer(id));
		return "customerDetail";
	}
	
}
