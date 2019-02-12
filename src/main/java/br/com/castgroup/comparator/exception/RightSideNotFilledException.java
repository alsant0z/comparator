package br.com.castgroup.comparator.exception;

/**
 * An exception that is thrown when the right side is null.
 * @author alsantos
 */
public class RightSideNotFilledException extends Exception {
	private static final String MSG = "Right side is null. Please, fill it!";
	public RightSideNotFilledException() {
		super(MSG);
	}
}
