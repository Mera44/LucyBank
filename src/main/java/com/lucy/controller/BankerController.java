package com.lucy.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.domain.Banker;
import com.lucy.domain.CheckingAccount;
import com.lucy.domain.CreditAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.Role;
import com.lucy.domain.SavingAccount;
import com.lucy.domain.Teller;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.service.BankerService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.RoleService;
import com.lucy.service.SavingAccountService;
import com.lucy.service.TellerService;
import com.lucy.serviceImpl.CustomerAccountHelper;
import com.lucy.util.Util;

@RequestMapping("/banker")
@Controller
public class BankerController {
	
	@Autowired 
	CustomerService customerService;
	@Autowired
	CheckingAccountService checkingAccountService;
	@Autowired
	SavingAccountService savingAccountService;
	@Autowired
	TellerService tellerService;
	@Autowired
	BankerService bankerService;
	@Autowired
	CustomerAccountHelper customerAccountHelper;
	@Autowired
	RoleService roleService;
	
	
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
		
		if(bindingResult.hasErrors())
			return "addCustomoerForm";
		
		Role role =roleService.getByRole("ROLE_CUSTOMER");
		customer.getProfile().setRole(role);
		
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
		
		
		customerService.save(customer);
		
		
		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/banker/welcome";
	}
	
	@RequestMapping(value="/customer/detail/{id}", method=RequestMethod.GET)
	public String customerDetail(@PathVariable("id") long id, Model model) {
		
		customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts());
		  
		model.addAttribute("customerDetail", customerService.getCustomer(id));
		model.addAttribute("accounts", customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));
		return "customerDetail";
	}
	
	//helper method
		private Integer accountNumber() {
			Random rand = new Random();
			return rand.nextInt(99998) + 10001;
		}
	
		//teller	
		@RequestMapping(value="/teller/add", method=RequestMethod.GET)
		public String addTellerForm(@ModelAttribute("teller") Teller teller) {	
			return "addTellerForm";
		}
		
		@RequestMapping(value="/teller/add", method=RequestMethod.POST)
		public String addCustomer(@Valid @ModelAttribute("teller") Teller teller, BindingResult 
				bindingResult, RedirectAttributes redirectAttribute) {
			Role role =roleService.getByRole("ROLE_TELLER");
			teller.getProfile().setRole(role);
			if(bindingResult.hasErrors())
				return "addTellerForm";
			tellerService.save(teller);
			return "redirect:/banker/teller/list";
		}
		
		@RequestMapping("/teller/list")
		public String tellerList(Model model) {
			model.addAttribute("tellers",tellerService.getAllTellers());
			return "tellerList";
		}
		
		
		//add banker
				@RequestMapping(value="/add", method=RequestMethod.GET)
				public String addBankerForm(@ModelAttribute("banker") Banker banker) {	
					return "addBankerForm";
				}
				
				@RequestMapping(value="/add", method=RequestMethod.POST)
				public String addBanker(@Valid @ModelAttribute("banker") Banker banker, BindingResult 
						bindingResult, RedirectAttributes redirectAttribute) {
					Role role =roleService.getByRole("ROLE_BANKER");
					banker.getProfile().setRole(role);
					if(bindingResult.hasErrors())
						return "addBankerForm";
					bankerService.save(banker);
					return "redirect:/banker/list";
				}
				
				@RequestMapping("/list")
				public String bankerList(Model model) {
					model.addAttribute("bankers",bankerService.getAllBankers());
					return "bankerList";
				}
		
	
	
	@RequestMapping(value="/customer/deposit", method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody Account customerDeposit(@RequestParam("transactionAmount") Double transactionAmount,
			@RequestParam("accountNumber") Integer accountNumber) {
		System.out.println(transactionAmount);
		System.out.println(accountNumber);
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount((Double)transactionAmount);
		return checkingAccountService.deposit(accountNumber, new Transaction());
	}

	
	@RequestMapping(value="/customer/deposit/{transactionAmount}/{accountNumber}/{typeAccount}", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody Account customerDeposit2(@PathVariable("transactionAmount") Double transactionAmount,
			@PathVariable("accountNumber") Integer accountNumber, @PathVariable("typeAccount") String typeAccount) {
		Account account=null;
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount((Double)transactionAmount);
		transaction.setTransactionType(TransactionType.DEPOSIT);
		
		if(typeAccount.equals("Checking"))
			account = checkingAccountService.deposit(accountNumber, transaction);
		else if(typeAccount.equals("Saving"))
			account = savingAccountService.deposit(accountNumber, transaction);	
			
		return account;
	}
	
	@RequestMapping(value="/customer/withdraw/{transactionAmount}/{accountNumber}/{typeAccount}", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody Account customerWithdraw(@PathVariable("transactionAmount") Double transactionAmount,
			@PathVariable("accountNumber") Integer accountNumber, @PathVariable("typeAccount") String typeAccount) {
		Account account=null;
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount((Double)transactionAmount);
		transaction.setTransactionType(TransactionType.WITHDRAW);
	
			
			if(typeAccount.equals("Checking")) {
				checkingAccountService.withdraw(accountNumber, transaction);
				account = checkingAccountService.getByAccountNumber(accountNumber);
			}
				
			else if(typeAccount.equals("Saving")) {
				savingAccountService.withdraw(accountNumber, transaction);
				account = savingAccountService.getByAccountNumber(accountNumber);
			}
			
		return account;
	}
	
	
	

}
