package com.bank.project.bankAccountManagement.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.project.rest.controllers.OperationController;
import com.bank.project.service.OperationService;

@WebMvcTest(value =OperationController.class)
@ExtendWith(SpringExtension.class)
class OperationControllerTest {
	
	@MockBean
	OperationService operationService;
	
	@MockBean
	private ModelMapper modelMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void displayAllOperationByClientApiTest() {
		Long clientId = 1l;
		try {
			mockMvc.perform(get("/api/v1/account/operations/{clientId}",clientId)).andExpect(status().isOk()).andDo(print());
			verify(operationService, times(1)).displayOperationsByClient(clientId);
			verifyNoMoreInteractions(operationService);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

}
