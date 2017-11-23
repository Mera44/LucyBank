package com.lucy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CardNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private int start;
	private int middle;
	private int suffix;
	
	public CardNumber() {
		
	}
	public CardNumber(String number) {
		super();
		this.start = Integer.parseInt(number.substring(0, 3));
		this.middle = Integer.parseInt(number.substring(3, 6));
		this.suffix = Integer.parseInt(number.substring(6, 9));
	}
	public CardNumber(int start, int middle, int suffix) {
		super();
		this.start = start;
		this.middle = middle;
		this.suffix = suffix;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getMiddle() {
		return middle;
	}

	public void setMiddle(int middle) {
		this.middle = middle;
	}

	public int getSuffix() {
		return suffix;
	}

	public void setSuffix(int suffix) {
		this.suffix = suffix;
	}

}
