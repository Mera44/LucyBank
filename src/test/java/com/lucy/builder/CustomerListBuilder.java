package com.lucy.builder;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.lucy.domain.CheckingAccount;
import com.lucy.domain.Customer;
import com.lucy.domain.Role;
import com.lucy.domain.SavingAccount;
import com.lucy.domain.TransactionType;

public class CustomerListBuilder {

	Role bankerRole = new RoleBuilder().withId(1L).withRole("ROLE_BANKER").build();
	Role tellerRole = new RoleBuilder().withId(1L).withRole("ROLE_TELLER").build();
	Role customerRole = new RoleBuilder().withId(1L).withRole("ROLE_CUSTOMER").build();

	TransactionBuilder depositTC = new TransactionBuilder().withId(1L).withTransactionAmount(200.0)
			.withStartingBalance(0.0).withEndingBalance(200.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.DEPOSIT);

	TransactionBuilder withdrawTC = new TransactionBuilder().withId(2L).withTransactionAmount(100.0)
			.withStartingBalance(200.0).withEndingBalance(100.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.WITHDRAW);

	TransactionBuilder depositTS = new TransactionBuilder().withId(3L).withTransactionAmount(400.0)
			.withStartingBalance(0.0).withEndingBalance(400.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.DEPOSIT);
	TransactionBuilder withdrawTS = new TransactionBuilder().withId(4L).withTransactionAmount(100.0)
			.withStartingBalance(400.0).withEndingBalance(300.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.WITHDRAW);

	TransactionBuilder depositTC1 = new TransactionBuilder().withId(5L).withTransactionAmount(200.0)
			.withStartingBalance(0.0).withEndingBalance(200.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.DEPOSIT);

	TransactionBuilder withdrawTC1 = new TransactionBuilder().withId(6L).withTransactionAmount(100.0)
			.withStartingBalance(200.0).withEndingBalance(100.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.WITHDRAW);

	TransactionBuilder depositTS1 = new TransactionBuilder().withId(7L).withTransactionAmount(400.0)
			.withStartingBalance(0.0).withEndingBalance(400.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.DEPOSIT);
	TransactionBuilder withdrawTS1 = new TransactionBuilder().withId(8L).withTransactionAmount(100.0)
			.withStartingBalance(400.0).withEndingBalance(300.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.WITHDRAW);

	TransactionBuilder depositTC3 = new TransactionBuilder().withId(9L).withTransactionAmount(200.0)
			.withStartingBalance(0.0).withEndingBalance(200.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.DEPOSIT);

	TransactionBuilder withdrawTC3 = new TransactionBuilder().withId(10L).withTransactionAmount(100.0)
			.withStartingBalance(200.0).withEndingBalance(100.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.WITHDRAW);

	TransactionBuilder depositTS3 = new TransactionBuilder().withId(11L).withTransactionAmount(400.0)
			.withStartingBalance(0.0).withEndingBalance(400.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.DEPOSIT);
	TransactionBuilder withdrawTS3 = new TransactionBuilder().withId(12L).withTransactionAmount(100.0)
			.withStartingBalance(400.0).withEndingBalance(300.0)
			.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
			.withTransactionType(TransactionType.WITHDRAW);

	CheckingAccount checkingAccount1 = new CheckingAccountBuilder().withId(1L).withBalance(300.0)
			.withTypeAccount("Checking").withTransaction(Arrays.asList(depositTC.build(), withdrawTC.build())).build();
	CheckingAccount checkingAccount2 = new CheckingAccountBuilder().withId(2L).withBalance(400.0)
			.withTypeAccount("Checking").withTransaction(Arrays.asList(depositTC1.build(), withdrawTC1.build())).build();
	CheckingAccount checkingAccount3 = new CheckingAccountBuilder().withId(3L).withBalance(200.0)
			.withTransaction(Arrays.asList(depositTC3.build(),withdrawTC3.build())).withTypeAccount("Checking").build();
	
	SavingAccount savingAccount1 = new SavingAccountBuilder().withId(1L).withBalance(200.0).withTypeAccount("Saving")
			.withTransaction(Arrays.asList(depositTS.build(), withdrawTS.build())).build();
	SavingAccount savingAccount2 = new SavingAccountBuilder().withId(2L).withBalance(500.0).withTypeAccount("Saving")
			.withTransaction(Arrays.asList(depositTS1.build(),withdrawTS1.build())).build();
	SavingAccount savingAccount3 = new SavingAccountBuilder().withId(3L).withBalance(100.0).withTypeAccount("Saving")
			.withTransaction(Arrays.asList(depositTS3.build(),withdrawTS3.build())).build();

	public CustomerBuilder custmer1 = new CustomerBuilder().withId(1L)
			.withProfile(
					new ProfileBuilder().withId(1L).withFistName("Ameha").withLastName("WAA-EA").withUserName("ameha")
							.withPassword("ameha").withRole(bankerRole)
							.withAddress(new AddressBuilder().withId(1L).withStreet("4 Main St").withState("IA")
									.withZipCode("52556")

									.build())
							.build())
			.withAccountlist(Arrays.asList(checkingAccount1, savingAccount1));

	public CustomerBuilder custmer2 = new CustomerBuilder().withId(2L)
			.withProfile(new ProfileBuilder().withId(1L).withFistName("Mera").withLastName("MWA").withUserName("mera12")
					.withPassword("mera12").withRole(customerRole)
					.withAddress(new AddressBuilder().withId(1L).withStreet("1000 4th St").withState("IA")
							.withZipCode("52556")

							.build())
					.build())
			.withAccountlist(Arrays.asList(checkingAccount2, savingAccount2));

	public CustomerBuilder custmer3 = new CustomerBuilder().withId(3L)
			.withProfile(new ProfileBuilder().withId(1L).withFistName("Fili12").withLastName("Aman12")
					.withUserName("fili12").withPassword("fili12").withRole(tellerRole)
					.withAddress(new AddressBuilder().withId(1L).withStreet("2000 court St").withState("IA")
							.withZipCode("52556").build())
					.build())
			.withAccountlist(Arrays.asList(checkingAccount3, savingAccount3));

	public List<Customer> build() {

		Customer customerOne = custmer1.build();
		Customer customerTwo = custmer2.build();
		Customer customerThree = custmer3.build();

		return (List<Customer>) Arrays.asList(customerOne, customerTwo, customerThree);
	}

	public CustomerBuilder getCustomerBuilderOne() {
		return custmer1;
	}

}
