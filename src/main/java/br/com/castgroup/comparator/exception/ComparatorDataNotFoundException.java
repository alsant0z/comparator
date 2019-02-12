package br.com.castgroup.comparator.exception;

/**
 * A class that represents an exception when a data object was not found in
 * repository,
 * 
 * @author alsantos
 *
 */
public class ComparatorDataNotFoundException extends Exception {
	private static final String MSG = "A data object was not found in repository. Please, try again.";
	public ComparatorDataNotFoundException() {
		super(MSG);
	}
}

