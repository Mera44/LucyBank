package com.lucy.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.lucy.domain.CardNumber;

public class CardNumberFormatter implements Formatter<CardNumber>{

	@Override
	public String print(CardNumber cardNumber, Locale arg1) {
		return cardNumber.getStart() + "-" + cardNumber.getMiddle() + "-" + cardNumber.getSuffix();
	}

	@Override
	public CardNumber parse(String source, Locale arg1) throws ParseException {
		CardNumber cardNumber = null;
		try{
			int start = Integer.parseInt(source.substring(0, 3));
			int middle = Integer.parseInt(source.substring(4, 7));
			int suffix = Integer.parseInt(source.substring(8, 11));
			cardNumber = new CardNumber(start, middle, suffix);
		} catch (Exception e) {
			System.out.println("Error - Bad ISBN Format");
		}
		
		return cardNumber;
	}
}
