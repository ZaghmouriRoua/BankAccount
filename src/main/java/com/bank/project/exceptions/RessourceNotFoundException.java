package com.bank.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.project.rest.responses.ErrorResponse;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	private  final transient ErrorResponse error;

	public RessourceNotFoundException(ErrorResponse error) {
		super(error.getErrorDescription());
		this.error = error;
	}

	public ErrorResponse getError() {
		return error;
	}
}
