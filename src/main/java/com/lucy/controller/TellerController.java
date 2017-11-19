package com.lucy.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.lucy.domain.CheckingAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.SavingAccount;
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
		System.out.println("=======>customer id " + id);
		model.addAttribute("customer", customerService.getCustomer(id));
		model.addAttribute("account", getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));

		return "custAccount";

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public String addAccountForm(@ModelAttribute("teller") Teller teller, @RequestParam("id") Long id, Model model) {

		tellerService.updateTeller(id, teller);
		model.addAttribute("teller", teller);

		return "editTellerSuccess";

	}

	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.POST)
	public String withdraw(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id, @RequestParam("accountNumber") Integer accNum,
			RedirectAttributes redirectAttributes) {
		System.out.println("=======>acc accountNumber  " + accNum);

		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber() == accNum) {
				if (acc.getClass().getSimpleName().equalsIgnoreCase("CheckingAccount")) {
					checkingService.withdraw(accNum, transaction.setTransactionTypeFor(TransactionType.WITHDRAW));
					break;
				}
				if (acc.getClass().getSimpleName().equalsIgnoreCase("SavingAccount")) {
					{
						savingService.withdraw(accNum, transaction.setTransactionTypeFor(TransactionType.WITHDRAW));
						break;
					}
				}
			}
		}

		return "redirect:/teller/account/ " + id;

	}

	@RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.GET)
	public String getWithdrawForm(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();

		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getClass().getSimpleName().equalsIgnoreCase("CheckingAccount")
					|| acc.getClass().getSimpleName().equalsIgnoreCase("SavingAccount")) {
				withdrawingAccount.add(acc);
			}
		}
		model.addAttribute("account", withdrawingAccount);

		// model.addAttribute("account", customerService.getCustomer(id).getAccounts());
		model.addAttribute("customer", customerService.getCustomer(id));

		return "withdraw";

	}

	@RequestMapping(value = "/account/deposit/{id}", method = RequestMethod.POST)
	public String deposit(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Integer id, @RequestParam("accountNumber") Integer accNum,
			RedirectAttributes redirectAttributes) {
		Transaction trans = new Transaction();
		trans.setTransactionAmount(transaction.getTransactionAmount());
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			System.out.println("=======> amount444   " + (acc.getAccountNumber()));

			if (acc.getAccountNumber().intValue()==(accNum.intValue())) {
				System.out.println("=======> amount444  " + (acc.getAccountNumber().intValue()== accNum.intValue()));
				if (acc.getClass().getSimpleName().equalsIgnoreCase("CheckingAccount")) {
					CheckingAccount checAcc = checkingService.deposit(accNum,
							trans.setTransactionTypeFor(TransactionType.DEPOSIT));
					System.out.println("=======> amount2   " + checAcc.getBalance());
					checkingService.save(checAcc);
					break;
				}
				if (acc.getClass().getSimpleName().equalsIgnoreCase("SavingAccount")) {
					{
						SavingAccount saveAcc = savingService.deposit(accNum,
								trans.setTransactionTypeFor(TransactionType.DEPOSIT));
						System.out.println("=======> amount2   " + saveAcc.getBalance());
						savingService.save(saveAcc);
						break;
					}
				}
			}
		}

		return "redirect:/teller/account/" + id;

	}

	@RequestMapping(value = "/account/deposit/{id}", method = RequestMethod.GET)
	public String getDepositForm(@ModelAttribute("transaction") Transaction transaction, Model model,
			@PathVariable("id") Long id) {
		List<Account> withdrawingAccount = new ArrayList<Account>();
		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getClass().getSimpleName().equalsIgnoreCase("CheckingAccount")
					|| acc.getClass().getSimpleName().equalsIgnoreCase("SavingAccount")) {
				withdrawingAccount.add(acc);
			}
		}
	
		model.addAttribute("account", getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));
		model.addAttribute("customer", customerService.getCustomer(id));

		return "deposit";

	}

	@RequestMapping(value = "/transfer/{id}", method = RequestMethod.POST)
	public String transfer(@ModelAttribute("transaction") Transaction transaction, Model model,
			@RequestParam("id") Integer id, @PathVariable("from") Integer accNumFrom,
			@PathVariable("to") Integer accNumTo, RedirectAttributes redirectAttributes) {

		for (Account acc : customerService.getCustomer(id).getAccounts()) {
			if (acc.getAccountNumber() == accNumFrom) {
				if (acc.getClass().getSimpleName() == "CheckingAccount") {
					checkingService.transfer(accNumFrom, accNumTo, transaction);
					break;
				}
				if (acc.getClass().getSimpleName() == "SavingAccount") {
					savingService.transfer(accNumFrom, accNumTo, transaction);
					break;
				}
				// if(acc.getClass().getSimpleName() == "CreditAccount") { {
				// creditService.withdraw(accNumFrom,
				// transaction.setTransactionTypeFor(TransactionType.DEPOSIT));
				// break;
				// }
				// }
			}
		}

		return "redirect:/teller/account?id=" + id;

	}

	@RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
	public String getTransferForm(Model model, @RequestParam("id") Long id) {
		model.addAttribute("account", customerService.getCustomer(id).getAccounts());
		model.addAttribute("customer", customerService.getCustomer(id));

		return "transfer";

	}
	
	private List<Account> getRemovedDuplicates(List<Account> acc){
		 Set<Account> set = new HashSet<Account>(acc);
		List<Account> customers = new ArrayList<>(set);
		return customers;
		
	}

}
