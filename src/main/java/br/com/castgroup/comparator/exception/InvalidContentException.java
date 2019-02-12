package br.com.castgroup.comparator.exception;

/**
 * A class that represents an exception for an invalid content.
 * @author alsantos
 *
 */
public class InvalidContentException extends Exception {
	private static final String MSG = "Invalid content. Please use a valide base64 string.";
	public InvalidContentException() {
		super(MSG);
	}
}
