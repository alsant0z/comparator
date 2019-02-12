package br.com.castgroup.comparator.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.comparator.entity.ComparatorData;
import br.com.castgroup.comparator.entity.ResponseData;
import br.com.castgroup.comparator.exception.ComparatorDataNotFoundException;
import br.com.castgroup.comparator.exception.InvalidContentException;
import br.com.castgroup.comparator.exception.InvalidDiffIdException;
import br.com.castgroup.comparator.repository.ComparatorDataRepository;
import br.com.castgroup.comparator.service.ComparatorService;

@Service
public class ComparatorServiceImpl implements ComparatorService {
	static final Logger log = LogManager.getLogger(ComparatorServiceImpl.class);

	@Autowired
	private ComparatorDataRepository repository;

	@Override
	public ComparatorData addLeft(String diffId, String content) throws InvalidDiffIdException, InvalidContentException {
		validateDiffId(diffId);
		validateContent(content);
		
		Optional<ComparatorData> optionalData = repository.findById(Long.parseLong(diffId));
		ComparatorData data = null;
		if (optionalData.isPresent()) {
			data = optionalData.get();
			data.setLeft(content);
		} else {
			data = new ComparatorData(Long.parseLong(diffId), content, null);			
		}
		
		return repository.save(data);
	}

	@Override
	public ComparatorData addRight(String diffId, String content) throws InvalidDiffIdException, InvalidContentException {
		validateDiffId(diffId);
		validateContent(content);
		
		Optional<ComparatorData> optionalData = repository.findById(Long.parseLong(diffId));
		ComparatorData data = null;
		if (optionalData.isPresent()) {
			data = optionalData.get();
			data.setRight(content);
		} else {
			data = new ComparatorData(Long.parseLong(diffId), null, content);			
		}
		
		return repository.save(data);
	}

	@Override
	public ResponseData compare(String diffId) throws InvalidDiffIdException, ComparatorDataNotFoundException {
		validateDiffId(diffId);

		Optional<ComparatorData> optionalData = repository.findById(Long.parseLong(diffId));
		if (optionalData.isEmpty()) {
			throw new ComparatorDataNotFoundException();
		}

		byte[] bytesLeft = optionalData.get().getLeft().getBytes();
		byte[] bytesRight = optionalData.get().getRight().getBytes();

		boolean resultComparation = Arrays.equals(bytesLeft, bytesRight);

		String offsets = "";

		if (resultComparation) {
			return new ResponseData("Objects are equal!");
		} else if (bytesLeft.length != bytesRight.length) {
			return new ResponseData("Objects does not have the same size!");

		} else {

			byte different = 0;

			for (int index = 0; index < bytesLeft.length; index++) {
				different = (byte) (bytesLeft[index] ^ bytesRight[index]);

				if (different != 0) {
					offsets = offsets + " " + index;
				}

			}

		}
		return new ResponseData("Objects have the same size, but with different offsets:" + offsets);
	}

	private void validateDiffId(String diffId) throws InvalidDiffIdException {
		if (diffId == null || diffId.trim().isEmpty()) {
			throw new InvalidDiffIdException();
		}
		
		// to test if it is a valid long
		try {
			Long.parseLong(diffId);
		} catch (NumberFormatException e) {
			throw new InvalidDiffIdException();
		}
	}

	private void validateContent(String content) throws InvalidContentException {
		if (content == null || content.trim().isEmpty()) {
			throw new InvalidContentException();
		}
		
		// to test as it calls IllegalArgumentException
        // if it is not in valid Base64 scheme
		try {
			Base64.getDecoder().decode(content);
		} catch (IllegalArgumentException e) {
			throw new InvalidContentException();
		}
	}

}
