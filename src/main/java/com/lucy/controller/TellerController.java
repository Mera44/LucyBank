package com.lucy.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.lucy.service.AccountService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CreditAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.ProfileService;
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
	@Autowired
	ProfileService profileService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllCustomers(Model model) {

		model.addAttribute("customers", customerService.getCustomers());

		return "listCustomers";

	}

	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.GET)
	public String getAllAccounts(@PathVariable("id") Long id, Model model) {

		model.addAttribute("customer", customerService.getCustomer(id));

		return "editCustomerByTeller";

	}

	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.POST)
	public @ResponseBody Profile addAccountForm(@RequestBody Profile profile, @PathVariable("id") Long id) {
		System.out.println("======>teller edit customer email  " + profile.getEmail());
		System.out.println("======>teller edit customer street  " + profile.getAddress().getStreet());
		Customer customer = customerService.getCustomer(id);
		customer.getProfile().setEmail(profile.getEmail());
		customer.getProfile().getAddress().setState((profile.getAddress().getState()));
		customer.getProfile().getAddress().setStreet((profile.getAddress().getStreet()));
		customer.getProfile().getAddress().setZipcode((profile.getAddress().getZipcode()));
		System.out.println("======>teller edit customer state  " + customer.getProfile().getAddress().getState());

		profileService.save(customer.getProfile());
		
		return profile;

	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public String success(Model model, @PathVariable("id") Long id) {
		System.out.println("=======>customer id " + id);
		model.addAttribute("customer", customerService.getCustomer(id));
		model.addAttribute("account", getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));

		return "custAccount";

	}

	// Withdraw Post
	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.POST)
	public String withdraw(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id, @RequestParam("accountNumber") Integer accNum,
			RedirectAttributes redirectAttributes) {
		System.out.println("=======>acc accountNumber  " + accNum);
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getAccountNumber().intValue() == accNum.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					checkingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW));
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					{
						savingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW));
						break;
					}
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

		for (Account acc : getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
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
	public String deposit(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountNumber") Integer accNum,
			RedirectAttributes redirectAttributes) {
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			System.out.println("=======> amount444   " + (acc.getAccountNumber()));

			if (acc.getAccountNumber().intValue() == (accNum.intValue())) {
				System.out.println("=======> amount444  " + (acc.getAccountNumber().intValue() == accNum.intValue()));
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					CheckingAccount checAcc = checkingService.deposit(accNum,
							trans.setTransactionTypeFor(TransactionType.DEPOSIT));
					System.out.println("=======> checking deposit  " + checAcc.getBalance());
					checkingService.save(checAcc);
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					{
						SavingAccount saveAcc = savingService.deposit(accNum,
								trans.setTransactionTypeFor(TransactionType.DEPOSIT));
						System.out.println("=======> saving  deposit   " + saveAcc.getBalance());
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
		for (Account acc : getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getTypeAccount().equalsIgnoreCase("Checking") || acc.getTypeAccount().equalsIgnoreCase("Saving")) {
				withdrawingAccount.add(acc);
			}
		}

		model.addAttribute("account", withdrawingAccount);
		model.addAttribute("customer", customerService.getCustomer(id));

		return "deposit";

	}

	@RequestMapping(value = "/account/transfer/{id}", method = RequestMethod.POST)
	public String transfer(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountFrom") Integer accNumFrom,
			@RequestParam("accountTo") Integer accNumTo,
			@RequestParam(value = "accountOther", required = false) Integer accNumOther,
			RedirectAttributes redirectAttributes) {

		System.out.println("======>transfer From   " + accNumFrom);
		System.out.println("======>transfer To   " + accNumTo);
		System.out.println("======>transfer Other   " + accNumOther);

		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber().intValue() == accNumFrom.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					checkingService.transfer(accNumFrom, accNumTo, trans);
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					savingService.transfer(accNumFrom, accNumTo, trans);
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
		for (Account acc : getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getTypeAccount().equalsIgnoreCase("Checking") || acc.getTypeAccount().equalsIgnoreCase("Saving")) {
				withdrawingAccount.add(acc);
			}
		}

		model.addAttribute("accounts", withdrawingAccount);
		model.addAttribute("accountOther", getRemovedOtherAccountDuplicates(accountService.findAll(),
				customerService.getCustomer(id).getAccounts()));

		model.addAttribute("customer", customerService.getCustomer(id));

		return "transfer";

	}

	// PayBill Post
	@RequestMapping(value = "/account/paybill/{id}", method = RequestMethod.POST)
	public String payBill(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountFrom") Integer accNumFrom,
			@RequestParam("accountTo") Integer accNumTo, RedirectAttributes redirectAttributes) {

		System.out.println("======>transfer From   " + accNumFrom);
		System.out.println("======>transfer To   " + accNumTo);

		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber().intValue() == accNumFrom.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					checkingService.payCreditBill(accNumFrom, accNumTo, trans);
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					savingService.payCreditBill(accNumFrom, accNumTo, trans);
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
		for (Account acc : getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
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

	private List<Account> getRemovedDuplicates(List<Account> acc) {
		Set<Account> set = new HashSet<Account>(acc);
		List<Account> customers = new ArrayList<>(set);
		return customers;

	}

	private List<Account> getRemovedOtherAccountDuplicates(List<Account> Otheracc, List<Account> currentCustomer) {
		List<Account> filterd = Otheracc.stream().distinct().filter(acc -> {
			for (Account account : currentCustomer) {
				if (account.getAccountNumber().intValue() == acc.getAccountNumber().intValue())
					return false;
			}
			return true;
		}).collect(Collectors.toList());
		return filterd;

	}

}
