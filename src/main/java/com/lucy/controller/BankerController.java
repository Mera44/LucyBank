package com.lucy.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.domain.Address;
import com.lucy.domain.Banker;
import com.lucy.domain.CardNumber;
import com.lucy.domain.CheckingAccount;
import com.lucy.domain.CreditAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.SavingAccount;
import com.lucy.domain.Teller;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.exception.NoCheckPhotoUploadedException;
import com.lucy.service.AccountService;
import com.lucy.service.AddressService;
import com.lucy.service.BankerService;
import com.lucy.service.CheckingAccountService;
import com.lucy.service.CreditAccountService;
import com.lucy.service.CustomerService;
import com.lucy.service.SavingAccountService;
import com.lucy.service.TellerService;
import com.lucy.serviceImpl.CustomerAccountHelper;
import com.lucy.serviceImpl.GenerateCardNumber;

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
	@Autowired
	GenerateCardNumber generateCardNumber;
	@Autowired
	CustomerAccountHelper customerAccountHelper;
	@Autowired
	SavingAccountService savingAccountService;
	@Autowired
	CreditAccountService creditAccountService;
	@Autowired
	AccountService accountService;
	@Autowired
	AddressService addressService;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@RequestMapping("/welcome")
	public String bankerWelcome(Model model) {
		model.addAttribute("customers", customerService.getCustomers());
		return "bankerWelcome";
	}

	/*
	 * private String encodePassword(String password) { return
	 * bCryptPasswordEncoder.encode(password); }
	 */

	@RequestMapping(value = "/customer/add", method = RequestMethod.GET)
	public String addCustomerForm(@ModelAttribute("customer") Customer customer, Model model) {
		System.out.println(generateCardNumber.generateCardNumberHelper(12));
		model.addAttribute("cardNumberChecking", generateCardNumber.generateCardNumberHelper(12));
		model.addAttribute("cardNumberSaving", generateCardNumber.generateCardNumberHelper(12));
		return "addCustomoerForm";
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult,
			@RequestParam("accTypes") String[] accountsType, RedirectAttributes redirectAttribute)
			throws FileNotFoundException {

		if (bindingResult.hasErrors())
			return "addCustomoerForm";

		MultipartFile image = customer.getProfile().getImage();
		System.out.println("image" + customer.getProfile().getImage().getSize());
		String rootDirectory = servletContext.getRealPath("/");
		System.out.println(rootDirectory);
		if (image != null && !image.isEmpty()) {
			try {
				image.transferTo(
						new File(rootDirectory + "/profilePic/" + customer.getProfile().getUserName() + ".png"));

			} catch (Exception e) {
				throw new FileNotFoundException("unable to save image: " + image.getOriginalFilename());
			}
		}

		for (String accTyp : accountsType) {
			if ("saving".equals(accTyp)) {
				SavingAccount savingAcc = new SavingAccount();
				savingAcc.setTypeAccount("Saving");
				savingAcc.setCardNumber(new CardNumber(generateCardNumber.generateCardNumberHelper(12)));
				savingAcc.setAccountNumber(accountNumber());
				customer.getAccounts().add(savingAcc);
			}

			if ("checking".equals(accTyp)) {
				CheckingAccount checkAcc = new CheckingAccount();
				checkAcc.setTypeAccount("Checking");
				checkAcc.setCardNumber(new CardNumber(generateCardNumber.generateCardNumberHelper(12)));
				checkAcc.setAccountNumber(accountNumber());
				customer.getAccounts().add(checkAcc);
			}
			if ("credit".equals(accTyp)) {
				CreditAccount creditAcc = new CreditAccount();
				creditAcc.setTypeAccount("Credit");
				creditAcc.setCardNumber(new CardNumber(generateCardNumber.generateCardNumberHelper(12)));
				creditAcc.setAccountNumber(accountNumber());
				customer.getAccounts().add(creditAcc);
			}
		}

		customerService.save(customer);

		redirectAttribute.addFlashAttribute("newCustomer", customer);
		return "redirect:/banker/welcome";
	}

	@RequestMapping(value = "/customer/detail/{id}", method = RequestMethod.GET)
	public String customerDetail(@PathVariable("id") long id, Model model) {
		model.addAttribute("customerDetail", customerService.getCustomer(id));
		model.addAttribute("accounts",
				customerAccountHelper.getRemovedDuplicates(customerService.getCustomer(id).getAccounts()));
		return "customerDetail";
	}

	// delete customer
	@RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") long id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/banker/welcome";
	}

	//update customer
		@RequestMapping(value="/customer/update/{id}", method=RequestMethod.GET)
		public String updateCustomer(@ModelAttribute("customer") Customer customer, 
				@PathVariable("id") long id, Model model) {
			model.addAttribute("editCustomer", customerService.getCustomer(id));
			return "updateCustomerForm";
		}
		@RequestMapping(value="/customer/update/{id}", method=RequestMethod.POST)
		public String updateCustomerProcess(@RequestParam("state") String state ,@RequestParam("street") String street,
				@RequestParam("zipcode") String zipcode, @RequestParam("id") long id, Model model) {
			
			Customer cust = customerService.getCustomer(id);
			Address addr = (Address) addressService
					.findAddressById(customerService.getCustomer(id).getProfile().getAddress().getId());
			addr.setState(state);
			addr.setStreet(street);
			addr.setZipcode(zipcode);
			cust.getProfile().setAddress(addr);
			customerService.save(cust);

			return "redirect:/banker/welcome";
		}
		//teller update
			@RequestMapping(value="/teller/update/{id}", method=RequestMethod.GET)
			public String updateTeller(@ModelAttribute("teller") Teller teller, @PathVariable("id") long id, Model model) {
				model.addAttribute("editTeller", tellerService.getTeller(id));
				return "updateTellerForm";
			}
			@RequestMapping(value="/teller/update/{id}", method=RequestMethod.POST)
			public String updateTellerProcess(@RequestParam("state") String state ,@RequestParam("street") String street,
					@RequestParam("zipcode") String zipcode, @RequestParam("id") long id, Model model) {
				
				Teller teller = tellerService.getTeller(id);
				Address addr = (Address) addressService
						.findAddressById(tellerService.getTeller(id).getProfile().getAddress().getId());
				addr.setState(state);
				addr.setStreet(street);
				addr.setZipcode(zipcode);
				teller.getProfile().setAddress(addr);
				tellerService.save(teller);

				return "redirect:/banker/teller/list";
			}
		//banker update	
			@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
			public String updateBanker(@ModelAttribute("banker") Banker banker, @PathVariable("id") long id, Model model) {
				model.addAttribute("editBanker", bankerService.getBanker(id));
				return "updateBankerForm";
			}
			@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
			public String updateBankerProcess(@RequestParam("state") String state ,@RequestParam("street") String street,
					@RequestParam("zipcode") String zipcode, @RequestParam("id") long id, Model model) {
				
				Banker banker = bankerService.getBanker(id);
				Address addr = (Address) addressService
						.findAddressById(bankerService.getBanker(id).getProfile().getAddress().getId());
				addr.setState(state);
				addr.setStreet(street);
				addr.setZipcode(zipcode);
				banker.getProfile().setAddress(addr);
				bankerService.save(banker);

				return "redirect:/banker/list";
			}

	// helper method
	private Integer accountNumber() {
		Random rand = new Random();
		return rand.nextInt(99998) + 99997;
	}

	// teller
	@RequestMapping(value = "/teller/add", method = RequestMethod.GET)
	public String addTellerForm(@ModelAttribute("teller") Teller teller) {
		return "addTellerForm";
	}

	@RequestMapping(value = "/teller/add", method = RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("teller") Teller teller, BindingResult bindingResult,
			RedirectAttributes redirectAttribute) {

		if (bindingResult.hasErrors())
			return "addTellerForm";

		tellerService.save(teller);
		return "redirect:/banker/teller/list";
	}

	@RequestMapping("/teller/list")
	public String tellerList(Model model) {
		model.addAttribute("tellers", tellerService.getAllTellers());
		return "tellerList";
	}

	// delete teller
	@RequestMapping(value = "/teller/delete/{id}", method = RequestMethod.GET)
	public String deleteTeller(@PathVariable("id") long id, Model model) {
		tellerService.deleteTeller(id);
		return "redirect:/banker/teller/list";
	}

	// add banker
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBankerForm(@ModelAttribute("banker") Banker banker) {
		return "addBankerForm";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBanker(@Valid @ModelAttribute("banker") Banker banker, BindingResult bindingResult,
			RedirectAttributes redirectAttribute) {

		if (bindingResult.hasErrors())
			return "addBankerForm";
		bankerService.save(banker);
		return "redirect:/banker/list";
	}

	@RequestMapping("/list")
	public String bankerList(Model model) {
		model.addAttribute("bankers", bankerService.getAllBankers());
		return "bankerList";
	}

	// delete banker
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBanker(@PathVariable("id") long id, Model model) {
		bankerService.deleteBanker(id);
		return "redirect:/banker/list";
	}

	@RequestMapping(value = "/customer/deposit", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Account customerDeposit(@RequestParam("transactionAmount") Double transactionAmount,
			@RequestParam("accountNumber") Integer accountNumber) {
		System.out.println(transactionAmount);
		System.out.println(accountNumber);
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount((Double) transactionAmount);
		return checkingAccountService.deposit(accountNumber, new Transaction());
	}

	@RequestMapping(value = "/customer/deposit/{transactionAmount}/{accountNumber}/{typeAccount}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Account customerDeposit2(@PathVariable("transactionAmount") Double transactionAmount,
			@PathVariable("accountNumber") Integer accountNumber, @PathVariable("typeAccount") String typeAccount) {
		Account account = null;
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount((Double) transactionAmount);
		transaction.setTransactionType(TransactionType.DEPOSIT);

		if (typeAccount.equals("Checking"))
			account = checkingAccountService.deposit(accountNumber, transaction);
		else if (typeAccount.equals("Saving"))
			account = savingAccountService.deposit(accountNumber, transaction);

		return account;
	}

	@RequestMapping(value = "/customer/withdraw/{transactionAmount}/{accountNumber}/{typeAccount}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Account customerWithdraw(@PathVariable("transactionAmount") Double transactionAmount,
			@PathVariable("accountNumber") Integer accountNumber, @PathVariable("typeAccount") String typeAccount) {
		Account account = null;
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount((Double) transactionAmount);
		transaction.setTransactionType(TransactionType.WITHDRAW);

		if (typeAccount.equals("Checking")) {
			checkingAccountService.withdraw(accountNumber, transaction);
			account = checkingAccountService.getByAccountNumber(accountNumber);
		}

		else if (typeAccount.equals("Saving")) {
			savingAccountService.withdraw(accountNumber, transaction);
			account = savingAccountService.getByAccountNumber(accountNumber);
		}

		return account;
	}

	@RequestMapping(value = "/customer/addCredit/{id}", method = RequestMethod.GET)
	public String addCreditAccount(@ModelAttribute("credit") CreditAccount credit, Model model) {
		model.addAttribute("accountNumber", accountNumber());
		model.addAttribute("cardNumber", generateCardNumber.generateCardNumberHelper(12));
		return "addCredit";
	}

	@RequestMapping(value = "/customer/addCredit/{id}", method = RequestMethod.POST)
	public String saveCreditAccount(@Valid @ModelAttribute("credit") CreditAccount credit, BindingResult result,
			RedirectAttributes redirectA, @PathVariable("id") long id) {
		if (result.hasErrors()) {
			return "addCredit";
		}

		Customer customer = customerService.getCustomer(id);
		customer.addAccount(accountService.save(credit));
		customerService.save(customer);
		return "redirect:/banker/customer/detail/" + id;
	}

	@ExceptionHandler(NoCheckPhotoUploadedException.class)
	public ModelAndView handleError(HttpServletRequest req, NoCheckPhotoUploadedException exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", exception.getMessage());
		modelAndView.addObject("exception", exception);
		modelAndView.addObject("url", req.getRequestURL());
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
