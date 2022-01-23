package com.bank.project.bankAccountManagement.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bank.project.exceptions.BadRequestException;
import com.bank.project.rest.responses.ApiError;
import com.bank.project.utils.RestPreconditions;



class RestPreconditionsTest {
	
	@Test
	void testCheckNotNull(){
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> RestPreconditions.checkNotNull(null, ApiError.INVALID_BODY_FIELD_AMOUNT));
		assertTrue(exception.getMessage().contains("The amount is a mandatory field."));
	}

}
