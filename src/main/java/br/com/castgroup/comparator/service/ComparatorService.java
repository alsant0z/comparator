package br.com.castgroup.comparator.service;

import br.com.castgroup.comparator.entity.ComparatorData;
import br.com.castgroup.comparator.entity.ResponseData;
import br.com.castgroup.comparator.exception.ComparatorDataNotFoundException;
import br.com.castgroup.comparator.exception.InvalidContentException;
import br.com.castgroup.comparator.exception.InvalidDiffIdException;

/**
 * This interface provides the required methods to compare the contents of two base64 strings.
 * @author alsantos
 *
 */
public interface ComparatorService {

	/**
	 * Add the content of the left side to the repository.
	 * @param diffId the identifier
	 * @param content the content to be added 
	 * @return the data object stored in repository. 
	 * @throws InvalidDiffIdException An invalid identifier was passed as parameter.
	 * @throws InvalidContentException if an invalid content was passed as parameter.
	 */
	ComparatorData addLeft(String diffId, String content) throws InvalidDiffIdException, InvalidContentException;
	
	/**
	 * Add the content of the right side to the repository.
	 * @param diffId the identifier
	 * @param content the content to be added 
	 * @return the data object stored in repository. 
	 * @throws InvalidDiffIdException An invalid identifier was passed as parameter.
	 * @throws InvalidContentException if an invalid content was passed as parameter.
	 */
	ComparatorData addRight(String diffId, String content) throws InvalidDiffIdException, InvalidContentException;
	
	/**
	 * Compares the left and right side, and give a result of it.
	 * @param diffId the identifier
	 * @return the difference between the data passed.
	 * @throws InvalidDiffIdException An invalid identifier was passed as parameter.
	 * @throws ComparatorDataNotFoundException A data object was not found in repository with the passed id.
	 */
	ResponseData compare(String diffId) throws InvalidDiffIdException, ComparatorDataNotFoundException;
	
}
