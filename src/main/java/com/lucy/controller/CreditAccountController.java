package com.lucy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.CreditAccount;
import com.lucy.service.CreditAccountService;

public class CreditAccountController {

@Controller
@RequestMapping("credit")
public class SavingAccountController {
	@Autowired
	CreditAccountService  creditAccountService;

	@RequestMapping(value = "/allAccounts", method = RequestMethod.GET)
	public String getAllAccounts(@ModelAttribute("account") CreditAccount account, Model model) {

		model.addAttribute("accounts", creditAccountService.findAll());

		return "allAccounts";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String addAccountForm(@ModelAttribute("account") CreditAccount account, Model model) {

		return "addAccounts";

	}

	@RequestMapping(value = "/listAccount/{id}", method = RequestMethod.GET)
	public String getAccountById(@ModelAttribute("account") CreditAccount account, @RequestParam() Long id,
			Model model) {

		model.addAttribute("account", creditAccountService.findById(id));

		return "singleAccount";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") CreditAccount account, Model model,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute(creditAccountService.save(account));
		redirectAttributes.addFlashAttribute("job", "Created");

		return "redirect:credit/success";

	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {

		return "addAccountSuccess";

	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
	public String updateAccount(@ModelAttribute("account") CreditAccount account, Model model) {
		creditAccountService.save(account);
		return "redirect:/credit/listAccount?id=" + account.getId();

	}

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.DELETE)
	public String deleteAccount(@ModelAttribute("account") CreditAccount account, @RequestParam() Long id, Model model,
			RedirectAttributes redirectAttributes) {
		creditAccountService.delete(id);
		return "redirect:/credit/success";

	}
}
}
