package com.lucy.domain;

import java.io.Serializable;



import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class Check implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore 
	private MultipartFile checkPhoto;
	private long customerId;
	private double depositAmount;
	private Integer accountNumber;
	private String accountType;
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public MultipartFile getCheckPhoto() {
		return checkPhoto;
	}
	public void setCheckPhoto(MultipartFile checkPhoto) {
		this.checkPhoto = checkPhoto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
