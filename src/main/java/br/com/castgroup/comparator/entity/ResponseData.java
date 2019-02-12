package br.com.castgroup.comparator.entity;

/**
 * A class to encapsulates the response output (JSON).
 */
public class ResponseData {

	private String message;
	
	public ResponseData(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}