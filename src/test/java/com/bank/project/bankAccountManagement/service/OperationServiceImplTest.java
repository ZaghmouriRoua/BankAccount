package com.bank.project.bankAccountManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bank.project.models.Account;
import com.bank.project.models.Client;
import com.bank.project.models.EAccountType;
import com.bank.project.models.Operation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.service.impl.OperationServiceImpl;


@SpringJUnitConfig(classes = OperationServiceImpl.class)
class OperationServiceImplTest {
	
	@Autowired
	OperationServiceImpl operationServiceImpl;
	@MockBean
	OperationRepository operationRepository;
	@MockBean
	AccountRepository accountRepository;
	
	@Test
	void displayOperationsByClient() {
		Client client = new Client();
		client.setId(1l);
		
		Account account = new Account();
		account.setAccountBalance(100.0);
		account.setAccountType(EAccountType.COURANT);
		account.setClient(client);
		
		List<Account>accounts=new ArrayList<>();
		accounts.add(account);
		List<Long> accountsId = new ArrayList<>();
		accountsId.add(account.getId());
		
		Operation operation=new Operation();
		operation.setAccount(account);
		operation.setAccountType(EAccountType.COURANT);
		operation.setOperationDate(new Date());
		
		List<Operation> operations=new ArrayList<>();
		operations.add(operation);
		
		
		Mockito.when(accountRepository.findByClientId(client.getId())).thenReturn(accounts);
		Mockito.when(operationRepository.findByAccountsId(accountsId)).thenReturn(operations);
		List<Operation> returnedList=operationServiceImpl.displayOperationsByClient(client.getId());
		assertEquals(1, returnedList.size());
		
		
		
	}

}
