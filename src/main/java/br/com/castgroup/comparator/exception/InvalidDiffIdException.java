package br.com.castgroup.comparator.exception;

/**
 * This exception class is used whenver a invalid id is passed throught the endpoints.
 * @author alsantos
 *
 */
public class InvalidDiffIdException extends Exception {
	private static final String MSG = "Invalid Diff Id. Please, use a valid value for the id.";
	public InvalidDiffIdException() {
		super(MSG);
	}
}
