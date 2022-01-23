package com.bank.project.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.project.exceptions.BadRequestException;
import com.bank.project.exceptions.RessourceNotFoundException;
import com.bank.project.rest.responses.ApiError;
import com.bank.project.rest.responses.ErrorResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestException(BadRequestException ex) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RessourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> ressourceNotFoundException(RessourceNotFoundException ex) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableException() {
		
		BadRequestException ex=	new BadRequestException(new ErrorResponse(ApiError.INVALID_ACCOUNT_TYPE.getMessage(),
				ApiError.INVALID_ACCOUNT_TYPE.getDescription()));
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}
	
	
}
