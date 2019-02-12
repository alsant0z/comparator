package br.com.castgroup.comparator.exception;

public class InvalidContentException extends Exception {
	private static final String MSG = "Invalid content. Please use a valide base64 string.";
	public InvalidContentException() {
		super(MSG);
	}
}
