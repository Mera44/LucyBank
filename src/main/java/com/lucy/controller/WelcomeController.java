package com.lucy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping(value= {"/","/welcome"})
	public String welcomePage() {
		return "welcome";
	}
	
}
