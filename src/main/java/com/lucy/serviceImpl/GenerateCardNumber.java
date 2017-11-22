package com.lucy.serviceImpl;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateCardNumber {
	public String generateCardNumberHelper(int length) {
		
		    Random random = new Random();
		    char[] digits = new char[length];
		    digits[0] = (char) (random.nextInt(9) + '1');
		    for (int i = 1; i < length; i++) {
		        digits[i] = (char) (random.nextInt(10) + '0');
		    }
		    return new String(digits);
	}

}
