package br.com.castgroup.comparator.exception;

/**
 * An exception that is thrown when the left side is null.
 * @author alsantos
 */
public class LeftSideNotFilledException extends Exception {
	private static final String MSG = "Left side is null. Please, fill it!";
	public LeftSideNotFilledException() {
		super(MSG);
	}
}
