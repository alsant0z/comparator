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
import br.com.castgroup.comparator.service.ComparatorService;
import br.com.castgroup.comparator.service.impl.ComparatorServiceImpl;

@RestController
@RequestMapping("/v1/diff/{id}")
public class ComparatorController {

	static final Logger log = LogManager.getLogger(ComparatorServiceImpl.class);

	@Autowired
	private ComparatorService service;

	@RequestMapping(value = "/left", method = RequestMethod.POST, produces = "application/json")
	public HttpEntity<ComparatorData> addLeft(@PathVariable("id") String diffId, @RequestBody RequestData requestData)
			throws InvalidDiffIdException, InvalidContentException {

		return ResponseEntity.ok(service.addLeft(diffId, requestData.getData()));
	}

	@RequestMapping(value = "/right", method = RequestMethod.POST, produces = "application/json")
	public HttpEntity<ComparatorData> addRight(@PathVariable("id") String diffId, @RequestBody RequestData requestData)
			throws InvalidDiffIdException, InvalidContentException {

		return ResponseEntity.ok(service.addRight(diffId, requestData.getData()));
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<ResponseData> compare(@PathVariable("id") String diffId)
			throws InvalidDiffIdException, ComparatorDataNotFoundException {

		return ResponseEntity.ok(service.compare(diffId));
	}
}
