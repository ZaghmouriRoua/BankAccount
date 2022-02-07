package com.bank.project.bankAccountManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bank.project.models.Account;
import com.bank.project.models.Client;
import com.bank.project.models.EAccountType;
import com.bank.project.models.EOperation;
import com.bank.project.models.Operation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.service.OperationService;
import com.bank.project.service.impl.OperationServiceImpl;


@SpringJUnitConfig(classes = OperationServiceImpl.class)
class OperationServiceImplTest {

	@Autowired
	OperationService operationService;
	@MockBean
	OperationRepository operationRepository;
	@MockBean
	AccountRepository accountRepository;
	Operation operation;
	Client client;
	Account account;

	@BeforeEach
	void init() {
		client = new Client();
		client.setId(1l);

		account = new Account();
		account.setAccountBalance(100.0);
		account.setAccountType(EAccountType.COURANT);
		account.setClient(client);

		operation = new Operation();
		operation.setAccount(account);
		operation.setAccountType(EAccountType.COURANT);
		operation.setOperationDate(new Date());
	}

	@Test
	void displayOperationsByClientTest() {

		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		List<Long> accountsId = new ArrayList<>();
		accountsId.add(account.getId());

		List<Operation> operations = new ArrayList<>();
		operations.add(operation);

		Mockito.when(accountRepository.findByClientId(client.getId())).thenReturn(accounts);
		Mockito.when(operationRepository.findByAccountsId(accountsId)).thenReturn(operations);
		List<Operation> returnedList = operationService.displayOperationsByClient(client.getId());
		assertEquals(1, returnedList.size());

	}

	@Test
	void saveOperationTest() {
		Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operation);
		Operation savedOperation = operationService.saveOperation(account, EOperation.DEPOSIT, new ClientTransaction());
		assertEquals(EAccountType.COURANT, savedOperation.getAccountType());
	}

}
