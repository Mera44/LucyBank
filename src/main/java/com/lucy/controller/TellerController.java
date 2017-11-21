package com.lucy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.domain.CheckingAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.Profile;
import com.lucy.domain.SavingAccount;
import com.lucy.domain.Teller;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.exception.WithdrawAmountException;
import com.lucy.service.AccountService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CreditAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.ProfileService;
import com.lucy.service.SavingAccountService;
import com.lucy.service.TellerService;
import com.lucy.serviceImpl.CustomerAccountHelper;
import com.lucy.util.Util;

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
	@Autowired
	ProfileService profileService;
	@Autowired
	CustomerAccountHelper customerAccountHelper;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllCustomers(Model model) {
		model.addAttribute("customers", customerService.getCustomers());
		return "listCustomers";

	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewTellerProfile( Model model) {
   System.out.println(" usernam teller===> "+ tellerService.findTellerByUsername( Util.getPrincipal()).getId());
		model.addAttribute("teller", tellerService.findTellerByUsername( Util.getPrincipal()));

		return "viewTellerProfile";

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEditTellerProfileForm(@PathVariable("id") String userName, Model model) {

		model.addAttribute("name", userName);

		return "editTeller";

	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public @ResponseBody Profile editTeller(@RequestBody Profile profile, @PathVariable("id") String userName) {
		System.out.println("======>teller edit customer email  " + profile.getEmail());
		System.out.println("======>teller edit customer street  " + profile.getAddress().getStreet());
		Teller teller = tellerService.findTellerByUsername(userName);
		teller.getProfile().setEmail(profile.getEmail());
		teller.getProfile().getAddress().setState((profile.getAddress().getState()));
		teller.getProfile().getAddress().setStreet((profile.getAddress().getStreet()));
		teller.getProfile().getAddress().setZipcode((profile.getAddress().getZipcode()));
		System.out.println("======>teller edit customer state  " + teller.getProfile().getAddress().getState());

		profileService.save(teller.getProfile());
		
		return profile;

	}
	

	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.GET)
	public String getAllAccounts(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomer(id));
		return "editCustomerByTeller";
	}
//Teller Edit Customer
	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.POST)
	public @ResponseBody Profile addAccountForm(@RequestBody Profile profile, @PathVariable("id") Long id) {		
		Customer customer = customerService.getCustomer(id);
		customer.getProfile().setEmail(profile.getEmail());
		customer.getProfile().getAddress().setState((profile.getAddress().getState()));
		customer.getProfile().getAddress().setStreet((profile.getAddress().getStreet()));
		customer.getProfile().getAddress().setZipcode((profile.getAddress().getZipcode()));

		profileService.save(customer.getProfile());		
		return profile;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public String success(Model model, @PathVariable("id") Long id) {
		model.addAttribute("customer", customerService.getCustomer(id));
		model.addAttribute("account", customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));

		return "custAccount";

	}

	// Withdraw Post
	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.POST)
	public String withdraw(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model,
			@PathVariable("id") Long id, @RequestParam("accountNumber") Integer accNum,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return "withdraw";
		}
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getAccountNumber().intValue() == accNum.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					if(!checkingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW))){
						throw new IllegalArgumentException(new WithdrawAmountException(TransactionType.WITHDRAW.toString(),null));
					}
					//checkingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW));
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {					
						if(!savingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW))){
							throw new IllegalArgumentException(new WithdrawAmountException(TransactionType.WITHDRAW.toString(),null));
						}
						break;
					
				}
			}
		}

		return "redirect:/teller/account/ " + id;

	}

	// Withdraw Get
	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.GET)
	public String getWithdrawForm(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();

		for (Account acc :customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getTypeAccount().equalsIgnoreCase("Checking") || acc.getTypeAccount().equalsIgnoreCase("Saving")) {
				withdrawingAccount.add(acc);
			}
		}
		model.addAttribute("account", withdrawingAccount);

		// model.addAttribute("account", customerService.getCustomer(id).getAccounts());
		model.addAttribute("customer", customerService.getCustomer(id));

		return "withdraw";

	}

	// Deposit Post
	@RequestMapping(value = "/account/deposit/{id}", method = RequestMethod.POST)
	public String deposit(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountNumber") Integer accNum,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return "deposit";
		}
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {

			if (acc.getAccountNumber().intValue() == (accNum.intValue())) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					CheckingAccount checAcc = checkingService.deposit(accNum,
							trans.setTransactionTypeFor(TransactionType.DEPOSIT));
					checkingService.save(checAcc);
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					{
						SavingAccount saveAcc = savingService.deposit(accNum,
								trans.setTransactionTypeFor(TransactionType.DEPOSIT));
						savingService.save(saveAcc);
						break;
					}
				}
			}
		}

		return "redirect:/teller/account/" + id;

	}

	// Deposit Get
	@RequestMapping(value = "/account/deposit/{id}", method = RequestMethod.GET)
	public String getDepositForm(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();
		for (Account acc : customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getTypeAccount().equalsIgnoreCase("Checking") || acc.getTypeAccount().equalsIgnoreCase("Saving")) {
				withdrawingAccount.add(acc);
			}
		}

		model.addAttribute("account", withdrawingAccount);
		model.addAttribute("customer", customerService.getCustomer(id));

		return "deposit";

	}
//transfer Post
	@RequestMapping(value = "/account/transfer/{id}", method = RequestMethod.POST)
	public String transfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountFrom") Integer accNumFrom,
			@RequestParam("accountTo") Integer accNumTo,
			RedirectAttributes redirectAttributes) {


		System.out.println("======>transfer From   " + accNumFrom);
		System.out.println("======>transfer To   " + accNumTo);
		System.out.println("======>transfer Amount   " + transaction.getTransactionAmount());


		if(result.hasErrors()){
			return "transfer";
		}

		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber().intValue() == accNumFrom.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					if(!checkingService.transfer(accNumFrom, accNumTo, trans)){
						throw new IllegalArgumentException(new WithdrawAmountException(TransactionType.TRANSFEREDFROM.toString(),null));
					}
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					if(!savingService.transfer(accNumFrom, accNumTo, trans)){
						throw new IllegalArgumentException(new WithdrawAmountException(TransactionType.TRANSFEREDFROM.toString(),null));
					}
					break;
				}
				/*
				 * if (acc.getTypeAccount().equalsIgnoreCase("Credit")) {
				 * creditService.payMonthlyBill(accNumFrom, accNumTo, transaction)(accNumFrom,
				 * transaction.setTransactionTypeFor(TransactionType.DEPOSIT)); break; }
				 */
			}
		}

		return "redirect:/teller/account/" + id;

	}

	@RequestMapping(value = "/account/transfer/{id}", method = RequestMethod.GET)
	public String getTransferForm(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();
		for (Account acc : customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getTypeAccount().equalsIgnoreCase("Checking") || acc.getTypeAccount().equalsIgnoreCase("Saving")) {
				withdrawingAccount.add(acc);
			}
		}

		model.addAttribute("accounts", withdrawingAccount);
		model.addAttribute("accountOther", customerAccountHelper.getRemovedOtherAccountDuplicates(accountService.findAll(),
				customerService.getCustomer(id).getAccounts()));

		model.addAttribute("customer", customerService.getCustomer(id));

		return "transfer";

	}

	// PayBill Post
	@RequestMapping(value = "/account/paybill/{id}", method = RequestMethod.POST)
	public String payBill(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountFrom") Integer accNumFrom,
			@RequestParam("accountTo") Integer accNumTo, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return "paybill";
		}
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber().intValue() == accNumFrom.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					if(!checkingService.payCreditBill(accNumFrom, accNumTo, trans)){
						throw new IllegalArgumentException(new WithdrawAmountException(TransactionType.PAYCREDIT.toString(),null));
					}
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					if(!savingService.payCreditBill(accNumFrom, accNumTo, trans)){
						throw new IllegalArgumentException(new WithdrawAmountException(TransactionType.PAYCREDIT.toString(),null));
					}
					break;
				}
			}
		}
		return "redirect:/teller/account/" + id;

	}

	// PayBill Get
	@RequestMapping(value = "/account/paybill/{id}", method = RequestMethod.GET)
	public String getPayBillForm(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();
		for (Account acc : customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getClass().getSimpleName().equalsIgnoreCase("CheckingAccount")
					|| acc.getClass().getSimpleName().equalsIgnoreCase("SavingAccount")) {
				withdrawingAccount.add(acc);
			}
			if (acc.getTypeAccount().equalsIgnoreCase("Credit")) {
				model.addAttribute("accountOther", acc);
			}
		}
		model.addAttribute("accounts", withdrawingAccount);
		model.addAttribute("customer", customerService.getCustomer(id));
		return "paybill";
	}


}
