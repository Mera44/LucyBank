package com.lucy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.domain.Teller;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.service.AccountService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CreditAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.SavingAccountService;
import com.lucy.service.TellerService;

@Controller
@RequestMapping("teller")
public class TellerController {
	@Autowired
	TellerService tellerService;
	@Autowired
	AccountService accountService;
	@Autowired
	CustomerService customerService;
	@Autowired
	CheckingAccountService checkingService;
	@Autowired
	SavingAccountService savingService;
	@Autowired
	CreditAccountService creditService;

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getAllAccounts(@ModelAttribute("teller") Teller teller, @RequestParam("id") Long id, Model model) {

		model.addAttribute("teller", tellerService.getTeller(id));

		return "editTeller";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllCustomers(Model model) {

		model.addAttribute("customers", customerService.getCustomers());

		return "listCustomers";

	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public String success(Model model, @PathVariable("id") Long id) {
		System.out.println("=======>customer id "+id);
		model.addAttribute("customer", customerService.getCustomer(id));
		model.addAttribute("account", customerService.getCustomer(id).getAccounts());

		return "custAccount";

	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public String addAccountForm(@ModelAttribute("teller") Teller teller, @RequestParam("id") Long id, Model model) {

		tellerService.updateTeller(id, teller);
		model.addAttribute("teller", teller);

		return "editTellerSuccess";

	}

	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.POST)
	public String withdraw(@ModelAttribute("transaction") Transaction transaction, Model model, @PathVariable("id") Long id, @PathVariable("accountNumber")Integer accNum,
			RedirectAttributes redirectAttributes) {
		System.out.println("=======>acc simple name class "+id);

		for(Account acc : customerService.getCustomer(id).getAccounts() ) {
			if(acc.getAccountNumber() == accNum) {
				 if(acc.getClass().getSimpleName() == "CheckingAccount") {
					 checkingService.withdraw(accNum, transaction.setTransactionTypeFor(TransactionType.WITHDRAW));
					 break;
				 }
				 if(acc.getClass().getSimpleName() == "SavingAccount") { {
					 savingService.withdraw(accNum, transaction.setTransactionTypeFor(TransactionType.WITHDRAW));
					 break;
				 }
			}
		}
		}
		
        
		return "redirect:/teller/account?id="+id;

	}

	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.GET)
	public String getWithdrawForm(Model model, @PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();
	
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getClass().getSimpleName().equalsIgnoreCase("CheckingAccount") || acc.getClass().getSimpleName().equalsIgnoreCase("SavingAccount")) {
				withdrawingAccount.add(acc);
			}
		}
		model.addAttribute("account", withdrawingAccount);

		//model.addAttribute("account", customerService.getCustomer(id).getAccounts());
		model.addAttribute("customer", customerService.getCustomer(id));

		return "withdraw";

	}

	
	

	@RequestMapping(value = "/deposit/{id}", method = RequestMethod.POST)
	public String deposit(@ModelAttribute("transaction") Transaction transaction, Model model, @RequestParam("id") Integer id, @PathVariable("accountNumber")Integer accNum,
			RedirectAttributes redirectAttributes) {
		
		for(Account acc : customerService.getCustomer(id).getAccounts() ) {
			if(acc.getAccountNumber() == accNum) {
				 if(acc.getClass().getSimpleName() == "CheckingAccount") {
					 checkingService.withdraw(accNum, transaction.setTransactionTypeFor(TransactionType.DEPOSIT));
					 break;
				 }
				 if(acc.getClass().getSimpleName() == "SavingAccount") { {
					 savingService.withdraw(accNum, transaction.setTransactionTypeFor(TransactionType.DEPOSIT));
					 break;
				 }
			}
		}
		}
		
        
		return "redirect:/teller/account?id="+id;

	}

	@RequestMapping(value = "/deposit/{id}", method = RequestMethod.GET)
	public String getDepositForm(Model model, @RequestParam("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getClass().getSimpleName() == "CheckingAccount" || acc.getClass().getSimpleName() == "SavingAccount") {
				withdrawingAccount.add(acc);
			}
		}
		model.addAttribute("account", withdrawingAccount);
		model.addAttribute("customer", customerService.getCustomer(id));

		return "deposit";

	}
	
	@RequestMapping(value = "/transfer/{id}", method = RequestMethod.POST)
	public String transfer(@ModelAttribute("transaction") Transaction transaction, Model model, @RequestParam("id") Integer id, @PathVariable("from")Integer accNumFrom,
			@PathVariable("to")Integer accNumTo,RedirectAttributes redirectAttributes) {
		
		for(Account acc : customerService.getCustomer(id).getAccounts() ) {
			if(acc.getAccountNumber() == accNumFrom) {
				 if(acc.getClass().getSimpleName() == "CheckingAccount") {
					 checkingService.transfer(accNumFrom, accNumTo, transaction);
					 break;
				 }
				 if(acc.getClass().getSimpleName() == "SavingAccount") { 
					 savingService.transfer(accNumFrom, accNumTo, transaction);
					 break;
				 }
//				 if(acc.getClass().getSimpleName() == "CreditAccount") { {
//					 creditService.withdraw(accNumFrom, transaction.setTransactionTypeFor(TransactionType.DEPOSIT));
//					 break;
//				 }
//			}
		}
		}
		
        
		return "redirect:/teller/account?id="+id;

	}

	@RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
	public String getTransferForm(Model model, @RequestParam("id") Long id) {
		model.addAttribute("account", customerService.getCustomer(id).getAccounts());
		model.addAttribute("customer", customerService.getCustomer(id));

		return "transfer";

	}

}
