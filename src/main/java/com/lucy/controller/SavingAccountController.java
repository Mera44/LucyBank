package com.lucy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucy.domain.SavingAccount;
import com.lucy.service.SavingAccountService;

@Controller
@RequestMapping("saving")
public class SavingAccountController {
	@Autowired
	SavingAccountService savingAccountService;

	@RequestMapping(value = "/allAccounts", method = RequestMethod.GET)
	public String getAllAccounts(@ModelAttribute("account") SavingAccount account, Model model) {

		model.addAttribute("accounts", savingAccountService.findAll());

		return "allAccounts";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String addAccountForm(@ModelAttribute("account") SavingAccount account, Model model) {

		return "addAccounts";

	}

	@RequestMapping(value = "/listAccount/{id}", method = RequestMethod.GET)
	public String getAccountById(@ModelAttribute("account") SavingAccount account, @RequestParam() Long id,
			Model model) {

		model.addAttribute("account", savingAccountService.findById(id));

		return "singleAccount";

	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") SavingAccount account, Model model,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute(savingAccountService.save(account));
		redirectAttributes.addFlashAttribute("job", "Created");

		return "redirect:/saving/success";

	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {

		return "addAccountSuccess";

	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
	public String updateAccount(@ModelAttribute("account") SavingAccount account, Model model) {
		savingAccountService.save(account);
		return "redirect:/saving/listAccount?id=" + account.getId();

	}

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.DELETE)
	public String deleteAccount(@ModelAttribute("account") SavingAccount account, @RequestParam() Long id, Model model,
			RedirectAttributes redirectAttributes) {
		savingAccountService.delete(id);
		return "redirect:/saving/success";

	}
}
