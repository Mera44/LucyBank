package com.lucy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lucy.exception.WithdrawAmountException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(WithdrawAmountException.class)
	public ModelAndView handleError(HttpServletRequest req, WithdrawAmountException exception) {
		ModelAndView mav = new ModelAndView();
		 mav.addObject("withdrawId", exception.getFullMessage());
		 mav.setViewName("noFund");
		 return mav;
	}
}