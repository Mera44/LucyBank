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
