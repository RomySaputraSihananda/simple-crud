package com.simplecrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserIsExistsException extends RuntimeException {
	public UserIsExistsException() {}

	public UserIsExistsException(String message) {
		super(message);
	}

}
