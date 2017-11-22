package com.lucy.validator;

import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
	public static void main(String[] args) {
		System.out.println(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		BCryptPasswordEncoder pd = new BCryptPasswordEncoder(4);
		System.out.println(pd.encode("admin"));
		
	}
}
