package com.lucy.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucy.util.Util;



@Controller
public class LoginController {

	@RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	/*@RequestMapping(value= {"/"})
	public String admin(Model model) {

		String loggedInUserName = Util.getPrincipal();
		model.addAttribute("user", loggedInUserName);
	
		return "welcome";
	}*/

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model) {
		model.addAttribute("user", Util.getPrincipal());
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
