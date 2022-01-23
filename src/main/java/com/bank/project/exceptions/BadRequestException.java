package com.bank.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.project.rest.responses.ErrorResponse;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	private  final transient ErrorResponse error;

	public BadRequestException(ErrorResponse error) {
		super(error.getErrorDescription());
		this.error = error;
	}

	public ErrorResponse getError() {
		return error;
	}
}
