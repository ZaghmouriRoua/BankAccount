package com.bank.project.bankAccountManagement.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.project.rest.controllers.AccountController;
import com.bank.project.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;

@WebMvcTest(value =AccountController.class)
@ExtendWith(SpringExtension.class)
class AccountControllerTest {
	
	@MockBean
	private AccountService accountService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	 void depositAmountApiTest() throws JsonProcessingException {
		String requestBody = "{\"amount\":5,\"accountType\":\"epargne\"}";
		Long clientId = 1l; 
		try {

			mockMvc.perform(
					post("/api/v1/account/deposit/{clientId}",clientId).contentType(MediaType.APPLICATION_JSON).content(requestBody))
					.andExpect(status().isOk())
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	 void retrieveAmountApiTest() throws JsonProcessingException {
		String requestBody = "{\"amount\":5,\"accountType\":\"epargne\"}";
		Long clientId = 1l;
		
		try {

			mockMvc.perform(
					post("/api/v1/account/retreive/{clientId}",clientId).contentType(MediaType.APPLICATION_JSON).content(requestBody))
					.andExpect(status().isOk())
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
