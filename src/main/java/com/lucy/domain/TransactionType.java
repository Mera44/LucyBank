package com.lucy.domain;


public enum TransactionType{
	WITHDRAW,
	DEPOSIT, 
	TRANSFEREDTO, 
	TRANSFEREDFROM,
	PAYCREDIT,// payment from checking or saving to credit card
	PAYMENT, //statement/bill payment
	CREDITCHARGE //swipe ur credit card 
}