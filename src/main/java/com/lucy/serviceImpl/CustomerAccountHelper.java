package com.lucy.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lucy.domain.Account;

@Component
public class CustomerAccountHelper {
	
	public List<Account> getRemovedDuplicates(List<Account> acc) {
		Set<Account> set = new HashSet<Account>(acc);
		List<Account> customers = new ArrayList<>(set);
		return customers;

	}

	public List<Account> getRemovedOtherAccountDuplicates(List<Account> Otheracc, List<Account> currentCustomer) {
		List<Account> filterd = Otheracc.stream().distinct().filter(acc -> {
			for (Account account : currentCustomer) {
				if (account.getAccountNumber().intValue() == acc.getAccountNumber().intValue())
					return false;
			}
			return true;
		}).collect(Collectors.toList());
		System.out.println("remove duplicate ===>"+filterd.size());
		return filterd;

	}

}
