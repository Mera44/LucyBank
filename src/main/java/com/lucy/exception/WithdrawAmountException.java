package com.lucy.exception;

public class WithdrawAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028561413081664031L;
	private String transferType;
	private String message = "You Don't Have The Necessary Funds For This Transaction ";

	public WithdrawAmountException() {

	}

	public WithdrawAmountException(String transferType, String message) {
		this.transferType = transferType;
		if (message != null)
			this.message = message;
	}

	public String getFullMessage() {
		return (message + transferType);
	}

	public String getProductId() {
		return transferType;
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
}
