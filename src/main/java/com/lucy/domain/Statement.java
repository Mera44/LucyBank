package com.lucy.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Statement implements Serializable{

	private static final long serialVersionUID = -4045729241960416615L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String monthName;
	private Date startDate;
	private Date endDate;
	private Double startingBalance;
	private Double endingBalance;
	private Double totalWithdraw;
	private Double totalDeposit;
	@ManyToOne
	private Account account;
	@OneToMany
	private List<Transaction> transactions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public Double getStartingBalance() {
		return startingBalance;
	}
	public void setStartingBalance(Double startingBalance) {
		this.startingBalance = startingBalance;
	}
	public Double getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(Double endingBalance) {
		this.endingBalance = endingBalance;
	}
	public Double getTotalWithdraw() {
		return totalWithdraw;
	}
	public void setTotalWithdraw(Double totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}
	public Double getTotalDeposit() {
		return totalDeposit;
	}
	public void setTotalDeposit(Double tatalDeposit) {
		this.totalDeposit = tatalDeposit;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	

}
