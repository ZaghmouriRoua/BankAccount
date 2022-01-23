package com.bank.project.utils;

import com.bank.project.exceptions.BadRequestException;
import com.bank.project.rest.responses.ApiError;
import com.bank.project.rest.responses.ErrorResponse;

public final class RestPreconditions {
	
	public static <T> void checkNotNull(final T reference, final ApiError apiError) {

		if (reference == null) {
			ErrorResponse apiErrorResponse = new ErrorResponse(apiError.getMessage(),apiError.getDescription());	
			throw new BadRequestException(apiErrorResponse);
		}
	}
	
//	public static void checkNotEmpty(final String reference, final ApiError apiError) {
//		if (reference.trim().isEmpty()) {
//			ErrorResponse apiErrorResponse = new ErrorResponse(apiError.getMessage(),apiError.getDescription());
//			throw new BadRequestException(apiErrorResponse);
//		}
//	}
	
	

}
