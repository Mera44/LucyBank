package com.lucy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.Account;
import com.lucy.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/allAccounts", method = RequestMethod.GET)
	public String getAllAccounts(@ModelAttribute("account") Account account, Model model) {

		model.addAttribute("accounts", accountService.findAll());

		return "allAccounts";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String addAccountForm(@ModelAttribute("account") Account account, Model model) {

		return "addAccounts";

	}

	@RequestMapping(value = "/listAccount/{id}", method = RequestMethod.GET)
	public String getAccountById(@ModelAttribute("account") Account account, @RequestParam() Long id, Model model) {

		model.addAttribute("account", accountService.findById(id));

		return "singleAccount";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") Account account, Model model,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute(accountService.save(account));
		redirectAttributes.addFlashAttribute("job", "Created");

		return "redirect:/success";

	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {

		return "addAccountSuccess";

	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
	public String updateAccount(@ModelAttribute("account") Account account, Model model) {
		accountService.save(account);
		return "redirect:/listAccount?id=" + account.getId();

	}

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.DELETE)
	public String deleteAccount(@ModelAttribute("account") Account account, @RequestParam() Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		accountService.delete(id);
		return "redirect:/success";

	}

}
