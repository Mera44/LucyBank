package com.lucy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.domain.Address;
import com.lucy.domain.CheckingAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.Profile;
import com.lucy.domain.SavingAccount;
import com.lucy.domain.Teller;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.exception.TransferAccountException;
import com.lucy.exception.WithdrawAmountException;
import com.lucy.repository.AddressRepository;
import com.lucy.service.AccountService;
import com.lucy.service.AddressService;
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
	@Autowired
	AddressService addressService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllCustomers(Model model) {
		model.addAttribute("customers", customerService.getCustomers());
		return "listCustomers";

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewTellerProfile(Model model) {
		System.out.println(" usernam teller===> " + tellerService.findTellerByUsername(Util.getPrincipal()).getId());
		model.addAttribute("teller", tellerService.findTellerByUsername(Util.getPrincipal()));

		return "viewTellerProfile";

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEditTellerProfileForm(@PathVariable("id") String userName, Model model) {

		model.addAttribute("name", userName);

		return "editTeller";

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public @ResponseBody Address editTeller(@RequestBody Address address, @PathVariable("id") String userName) {

		System.out.println("======>teller edit customer street  " + address.getStreet());
		Address addr = (Address) addressService
				.findAddressById(tellerService.findTellerByUsername(userName).getProfile().getAddress().getId());
		addr.setState(address.getState());
		addr.setStreet(address.getStreet());
		addr.setZipcode(address.getZipcode());
		addressService.update(addr);

		return address;

	}

	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.GET)
	public String getAllAccounts(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomer(id));
		return "editCustomerByTeller";
	}

	// Teller Edit Customer
	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.POST)
	public @ResponseBody Address addAccountForm(@RequestBody Address address, @PathVariable("id") Long id) {
		System.out.println("======>teller edit customer street  " + address.getStreet());
		Address addr = (Address) addressService
				.findAddressById(customerService.getCustomer(id).getProfile().getAddress().getId());
		addr.setState(address.getState());
		addr.setStreet(address.getStreet());
		addr.setZipcode(address.getZipcode());
		//addressService.update(addr);
		addressService.save(addr);

		return address;

	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public String success(Model model, @PathVariable("id") Long id) {
		model.addAttribute("customer", customerService.getCustomer(id));
		model.addAttribute("account",
				customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));

		return "custAccount";

	}

	// Withdraw Post
	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.POST)
	public String withdraw(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result,
			Model model, @PathVariable("id") Long id,
			@RequestParam(value = "accountNumber", required = false) Integer accNum,
			RedirectAttributes redirectAttributes) {
		if (accNum == null) {
			throw new NullPointerException();

		}
		if (result.hasErrors()) {
			List<Account> withdrawingAccount = new ArrayList<Account>();
			for (Account acc : customerAccountHelper
					.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")
						|| acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					withdrawingAccount.add(acc);
				}
			}

			model.addAttribute("account", withdrawingAccount);
			model.addAttribute("customer", customerService.getCustomer(id));
			return "withdraw";
		}
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
			if (acc.getAccountNumber().intValue() == accNum.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					if (!checkingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW))) {
						throw new IllegalArgumentException(
								new WithdrawAmountException(TransactionType.WITHDRAW.toString(), null));
					}
					// checkingService.withdraw(accNum,
					// trans.setTransactionTypeFor(TransactionType.WITHDRAW));
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					if (!savingService.withdraw(accNum, trans.setTransactionTypeFor(TransactionType.WITHDRAW))) {
						throw new IllegalArgumentException(
								new WithdrawAmountException(TransactionType.WITHDRAW.toString(), null));
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

		for (Account acc : customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
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
	public String deposit(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result,
			Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "accountNumber", required = false) Integer accNum,
			RedirectAttributes redirectAttributes) {
		if (accNum == null) {
			throw new NullPointerException();

		}
		if (result.hasErrors()) {
			List<Account> withdrawingAccount = new ArrayList<Account>();
			for (Account acc : customerAccountHelper
					.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")
						|| acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					withdrawingAccount.add(acc);
				}
			}

			model.addAttribute("account", withdrawingAccount);
			model.addAttribute("customer", customerService.getCustomer(id));
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

	// transfer Post
	@RequestMapping(value = "/account/transfer/{id}", method = RequestMethod.POST)
	public String transfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result,
			Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "accountFrom", required = false) Integer accNumFrom,
			@RequestParam(value = "accountTo", required = false) Integer accNumTo,
			RedirectAttributes redirectAttributes) {
		System.out.println("=====>accNumFrom" + accNumFrom);
		if (accNumFrom == null || accNumTo == null) {
			throw new TransferAccountException(null);

		}
		if (result.hasErrors()) {
			List<Account> withdrawingAccount = new ArrayList<Account>();
			for (Account acc : customerAccountHelper
					.getRemovedDuplicates(customerService.getCustomer(id).getAccounts())) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")
						|| acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					withdrawingAccount.add(acc);
				}
			}

			model.addAttribute("accounts", withdrawingAccount);
			model.addAttribute("accountOther", customerAccountHelper.getRemovedOtherAccountDuplicates(
					accountService.findAll(), customerService.getCustomer(id).getAccounts()));

			model.addAttribute("customer", customerService.getCustomer(id));
			return "transfer";
		}

		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		trans.setTransactionType(TransactionType.TRANSFEREDFROM);
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber().intValue() == accNumFrom.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					if (!checkingService.transfer(accNumFrom, accNumTo, trans)) {
						throw new IllegalArgumentException(
								new WithdrawAmountException(TransactionType.TRANSFEREDFROM.toString(), null));
					}
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					if (!savingService.transfer(accNumFrom, accNumTo, trans)) {
						throw new IllegalArgumentException(
								new WithdrawAmountException(TransactionType.TRANSFEREDFROM.toString(), null));
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
		model.addAttribute("accountOther", customerAccountHelper.getRemovedOtherAccountDuplicates(
				accountService.findAll(), customerService.getCustomer(id).getAccounts()));

		model.addAttribute("customer", customerService.getCustomer(id));

		return "transfer";

	}

	// PayBill Post
	@RequestMapping(value = "/account/paybill/{id}", method = RequestMethod.POST)
	public String payBill(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result,
			Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "accountFrom", required = false) Integer accNumFrom,
			@RequestParam(value = "accountTo", required = false) Integer accNumTo,
			RedirectAttributes redirectAttributes) {

		if (accNumFrom == null || accNumTo == null) {
			throw new TransferAccountException(null);

		}

		if (result.hasErrors()) {
			return "paybill";
		}
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber().intValue() == accNumFrom.intValue()) {
				if (acc.getTypeAccount().equalsIgnoreCase("Checking")) {
					if (!checkingService.payCreditBill(accNumFrom, accNumTo, trans)) {
						throw new IllegalArgumentException(
								new WithdrawAmountException(TransactionType.PAYCREDIT.toString(), null));
					}
					break;
				}
				if (acc.getTypeAccount().equalsIgnoreCase("Saving")) {
					if (!savingService.payCreditBill(accNumFrom, accNumTo, trans)) {
						throw new IllegalArgumentException(
								new WithdrawAmountException(TransactionType.PAYCREDIT.toString(), null));
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

	//// Individual Exception

	@ExceptionHandler(NullPointerException.class)
	// @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Account Should be
	// Selected for Transaction")
	public ModelAndView handleClientErrors(NullPointerException ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("withdrawId", "Account Should be Selected for Transaction");
		mav.setViewName("errorTransaction");
		return mav;
	}

}
