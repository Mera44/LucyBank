package com.lucy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.CheckingAccount;
import com.lucy.service.CheckingAccountService;

@Controller
@RequestMapping("checking")
public class CheckingAccountController {
	@Autowired
	CheckingAccountService checkingAccountService;
	

	@RequestMapping(value = "/allAccounts", method = RequestMethod.GET)
	public String getAllAccounts(@ModelAttribute("account") CheckingAccount account, Model model) {

		model.addAttribute("accounts", checkingAccountService.findAll());

		return "allAccounts";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String addAccountForm(@ModelAttribute("account") CheckingAccount account, Model model) {

		return "addAccounts";

	}

	@RequestMapping(value = "/listAccount/{id}", method = RequestMethod.GET)
	public String getAccountById(@ModelAttribute("account") CheckingAccount account, @RequestParam() Long id, Model model) {

		model.addAttribute("account", checkingAccountService.findById(id));

		return "singleAccount";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") CheckingAccount account, Model model,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute(checkingAccountService.save(account));
		redirectAttributes.addFlashAttribute("job", "Created");

		return "redirect:/success";

	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {

		return "addAccountSuccess";

	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
	public String updateAccount(@ModelAttribute("account") CheckingAccount account, Model model) {
		checkingAccountService.save(account);
		return "redirect:/listAccount?id=" + account.getId();

	}

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.DELETE)
	public String deleteAccount(@ModelAttribute("account") CheckingAccount account, @RequestParam() Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		checkingAccountService.delete(id);
		return "redirect:/success";

	}

}
