package com.lucy.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
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
import com.lucy.service.BankerService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.TellerService;

@RequestMapping("/banker")
@Controller
public class BankerController {
	
	@Autowired 
	CustomerService customerService;
	@Autowired
	CheckingAccountService checkingAccountService;
	@Autowired
	TellerService tellerService;
	@Autowired
	BankerService bankerService;
	@Autowired
	ServletContext servletContext;
	
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
			bindingResult, @RequestParam("accTypes") String[] accountsType, RedirectAttributes redirectAttribute) throws FileNotFoundException {
		
		if(bindingResult.hasErrors())
			return "addCustomoerForm";
		MultipartFile image = customer.getProfile().getImage();
		System.out.println("image"+customer.getProfile().getImage().getSize());
		String rootDirectory = servletContext.getRealPath("/");
		System.out.println(rootDirectory);
		if(image!=null && !image.isEmpty()){
			try{
				image.transferTo(new File(rootDirectory+"/profilePic/"+customer.getProfile().getUserName()+".png"));				
				
			} catch (Exception e) {
				throw new FileNotFoundException("unable to save image: "+ image.getOriginalFilename());
			}
		}
		
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
		
		
		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/banker/welcome";
	}
	
	@RequestMapping(value="/customer/detail/{id}", method=RequestMethod.GET)
	public String customerDetail(@PathVariable("id") long id, Model model) {
		model.addAttribute("customerDetail", customerService.getCustomer(id));
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
			Role role = new Role();
			role.setRole("teller");
			teller.getProfile().setRole(role);
			if(bindingResult.hasErrors())
				return "addTellerForm";
			tellerService.save(teller);
			return "redirect:/banker/list";
		}
		
		@RequestMapping("/list")
		public String tellerList(Model model) {
			model.addAttribute("tellers",tellerService.getAllTellers());
			return "tellerList";
		}
		
		
		//add banker
		//teller	
				@RequestMapping(value="/add", method=RequestMethod.GET)
				public String addBankerForm(@ModelAttribute("banker") Banker banker) {	
					return "addBankerForm";
				}
				
				@RequestMapping(value="/add", method=RequestMethod.POST)
				public String addBanker(@Valid @ModelAttribute("banker") Banker banker, BindingResult 
						bindingResult, RedirectAttributes redirectAttribute) {
					Role role = new Role();
					role.setRole("banker");
					banker.getProfile().setRole(role);
					if(bindingResult.hasErrors())
						return "addTellerForm";
					bankerService.save(banker);
					return "redirect:/banker/lists";
				}
				
				@RequestMapping("/lists")
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
}
