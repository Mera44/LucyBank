package com.lucy.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Valid
	@OneToOne(cascade=CascadeType.PERSIST)
	private Profile profile;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private List<Account> accounts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
