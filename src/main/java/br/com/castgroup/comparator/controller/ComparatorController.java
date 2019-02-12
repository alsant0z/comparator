package br.com.castgroup.comparator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.comparator.entity.ComparatorData;
import br.com.castgroup.comparator.entity.RequestData;
import br.com.castgroup.comparator.entity.ResponseData;
import br.com.castgroup.comparator.exception.ComparatorDataNotFoundException;
import br.com.castgroup.comparator.exception.InvalidContentException;
import br.com.castgroup.comparator.exception.InvalidDiffIdException;
import br.com.castgroup.comparator.exception.LeftSideNotFilledException;
import br.com.castgroup.comparator.exception.RightSideNotFilledException;
import br.com.castgroup.comparator.service.ComparatorService;
import br.com.castgroup.comparator.service.impl.ComparatorServiceImpl;

/**
 * Application Rest Controller. 
 * The endpoints are mapped in this class.
 * @author alsantos
 *
 */
@RestController
@RequestMapping("/v1/diff/{id}")
public class ComparatorController {

	static final Logger log = LogManager.getLogger(ComparatorServiceImpl.class);

	@Autowired
	private ComparatorService service;

	/**
	 * Post the left entry to the comparison.
	 * @param diffId the identification of the operation.
	 * @param requestData the json b64 encoded to be used in comparison.
	 * @return the object stored in repository.
	 * @throws InvalidDiffIdException An invalid identifier was passed as parameter.
	 * @throws InvalidContentException if an invalid content was passed as parameter.
	 */
	@RequestMapping(value = "/left", method = RequestMethod.POST, produces = "application/json")
	public HttpEntity<ComparatorData> addLeft(@PathVariable("id") String diffId, @RequestBody RequestData requestData)
			throws InvalidDiffIdException, InvalidContentException {

		return ResponseEntity.ok(service.addLeft(diffId, requestData.getData()));
	}

	/**
	 * Post the right entry to the comparison.
	 * @param diffId the identification of the operation.
	 * @param requestData the json b64 encoded to be used in comparison.
	 * @return the object stored in repository.
	 * @throws InvalidDiffIdException An invalid identifier was passed as parameter.
	 * @throws InvalidContentException if an invalid content was passed as parameter.
	 */
	@RequestMapping(value = "/right", method = RequestMethod.POST, produces = "application/json")
	public HttpEntity<ComparatorData> addRight(@PathVariable("id") String diffId, @RequestBody RequestData requestData)
			throws InvalidDiffIdException, InvalidContentException {

		return ResponseEntity.ok(service.addRight(diffId, requestData.getData()));
	}

	/**
	 * Compares the content data stored in left and right sides for a specific identifier.
	 * @param diffId the identification of the operation.
	 * @return the message with the result of the comparison.
	 * @throws InvalidDiffIdException An invalid identifier was passed as parameter.
	 * @throws ComparatorDataNotFoundException A data object was not found in repository with the passed id.
	 * @throws LeftSideNotFilledException Occurs when the method was called before add the left side
	 * @throws RightSideNotFilledException Occurs when the method was called before add the right side
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<ResponseData> compare(@PathVariable("id") String diffId)
			throws InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException {

		return ResponseEntity.ok(service.compare(diffId));
	}
}
