package com.lucy.exception;



//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No File found under this directory.")
public class NoCheckPhotoUploadedException extends RuntimeException{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public NoCheckPhotoUploadedException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
