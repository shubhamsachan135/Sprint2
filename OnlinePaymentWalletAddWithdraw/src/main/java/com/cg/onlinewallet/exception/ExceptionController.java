package com.cg.onlinewallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler(value = InvalidException.class)
	public ResponseEntity<Object> handleInvalidException(InvalidException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}
