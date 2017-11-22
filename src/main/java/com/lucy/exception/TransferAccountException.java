package com.lucy.exception;

public class TransferAccountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5128726717153477440L;
	private String message = "You Need To Select Both Account You WithDrawing And You are Transfering To ";

	public TransferAccountException() {

	}

	public TransferAccountException(String message) {
		
		if (message != null)
			this.message = message;
	}

	public String getFullMessage() {
		return (message);
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
}
