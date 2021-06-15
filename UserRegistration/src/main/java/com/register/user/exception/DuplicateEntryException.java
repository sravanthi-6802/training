package com.register.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final Logger logger = LoggerFactory.getLogger(DuplicateEntryException.class);

	public DuplicateEntryException(String message) {
		super(message);
		logger.warn("Duplicate Entry Exception");
	}

}

