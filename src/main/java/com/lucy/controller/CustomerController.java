package com.lucy.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.domain.Check;
import com.lucy.domain.Customer;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.exception.NoCheckPhotoUploadedException;
import com.lucy.service.AccountService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.SavingAccountService;
import com.lucy.serviceImpl.CustomerAccountHelper;
import com.lucy.util.Util;

@RequestMapping("/customer")
@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	ServletContext servletContext;
	@Autowired
	CustomerAccountHelper customerAccountHelper;
	@Autowired
	AccountService accountService;
	@Autowired
	CheckingAccountService checkingService;
	@Autowired
	SavingAccountService savingService;
	
	
	

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String customerWelcomePage(@ModelAttribute("checks") Check checks, Model model) {
		String userName = Util.getPrincipal();
		System.out.println("this is the current user name : " + userName);
		Customer loggedCustomer = customerService.findByProfileUserName(userName);
		System.out.println("email" + loggedCustomer.getProfile().getEmail());
		model.addAttribute("loggedCustomer", loggedCustomer);
		return "customerWelcome";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String customerProfile(HttpSession httpSession, Model model) {
		Customer loggedCustomer = (Customer) httpSession.getAttribute("loggedCustomer");
		if (loggedCustomer == null)
			return "redirect:/logout";// remember to set the logout page
		model.addAttribute("profile", loggedCustomer.getProfile());
		return "customerProfile";
	}

	@RequestMapping(value = "/depositChecks", method = RequestMethod.POST)
	public String depositChecks(@ModelAttribute("checks") Check checks, @RequestParam("typeAccount") String[] checking,
			@RequestParam("accountNumber") Integer accountNumber, Model model) {
		System.out.println("checks");
		System.out.println(checking[0]);
		System.out.println(accountNumber);
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount(checks.getDepositAmount());
		transaction.setTransactionType(TransactionType.DEPOSIT);
	
		MultipartFile checkPhoto = checks.getCheckPhoto();

		String rootDirectory = servletContext.getRealPath("/");

		if (checkPhoto != null && !checkPhoto.isEmpty()) {
			
			try {
				checkPhoto.transferTo(new File(rootDirectory + "resources/images/" + checks.getCustomerId() + ".png"));
			} catch (Exception e) {
				throw new NoCheckPhotoUploadedException("Check Image saving failed");
			}
		}
		
		/*if(typeAccount.equals("Saving")) {			
			savingService.deposit(accountNumber, transaction);
		}
		else if(typeAccount.equals("Checking")) {			
			checkingService.deposit(accountNumber, transaction);
		}*/
			
			
			
		return "checkUploadedSuccessfully";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String customers() {
		return "customers";
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

	@RequestMapping(value = "/account/transfer/{id}", method = RequestMethod.POST)
	public String transfer(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountFrom") Integer accNumFrom,
			@RequestParam("accountTo") Integer accNumTo,
			@RequestParam(value = "accountOther", required = false) Integer accNumOther,
			RedirectAttributes redirectAttributes) {

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

			}
		}

		return "redirect:/teller/account/" + id;

	}

}
