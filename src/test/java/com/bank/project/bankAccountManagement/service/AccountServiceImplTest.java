package com.bank.project.bankAccountManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bank.project.exceptions.BadRequestException;
import com.bank.project.exceptions.RessourceNotFoundException;
import com.bank.project.models.Account;
import com.bank.project.models.Client;
import com.bank.project.models.EAccountType;
import com.bank.project.models.EOperation;
import com.bank.project.models.Operation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ClientBalance;
import com.bank.project.service.AccountService;
import com.bank.project.service.OperationService;
import com.bank.project.service.impl.AccountServiceImpl;

@SpringJUnitConfig(classes = AccountServiceImpl.class)
class AccountServiceImplTest {

	@Autowired
	AccountService accountService;

	@MockBean
	AccountRepository accountRepository;
	@MockBean
	OperationRepository operationRepository;
	@MockBean
	OperationService operationService;

	ClientTransaction clientTransaction;
	Client client;
	Account account;
	Operation operation;

	@BeforeEach
	void init() {
		clientTransaction = new ClientTransaction();
		clientTransaction.setAmount(20.0);
		clientTransaction.setAccountNumber(5432l);
		client = new Client();
		client.setId(1l);
		account = new Account();
		account.setAccountNumber(2345l);
		account.setAccountBalance(100.0);
		account.setAccountType(EAccountType.COURANT);
		account.setClient(client);
		operation = new Operation();
		operation.setAccount(account);

	}

	@Test
	void depositAccountTestOK() {
		Mockito.when(accountRepository.findByAccountNumber(clientTransaction.getAccountNumber()))
				.thenReturn(Optional.of(account));
		Mockito.when(operationService.saveOperation(account, EOperation.DEPOSIT, clientTransaction))
				.thenReturn(operation);
		ClientBalance clientBalance = accountService.depositAccount(client.getId(), clientTransaction);
		assertEquals(120.0, clientBalance.getAmount());
	}

	@Test
	void nullAmountFielTest() {
		ClientTransaction clientTransaction = new ClientTransaction();
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> accountService.depositAccount(client.getId(), clientTransaction));
		assertTrue(exception.getMessage().contains("The amount is a mandatory field."));
	}

	@Test
	void negativeClientTransactionTest() {
		clientTransaction.setAmount(-10.0);
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> accountService.depositAccount(client.getId(), clientTransaction));
		assertTrue(exception.getMessage().contains("The versed amount can not be a negative value."));
	}

	@Test
	void accountNotFoundTest() {
		RessourceNotFoundException exception = assertThrows(RessourceNotFoundException.class,
				() -> accountService.depositAccount(2l, clientTransaction));
		assertTrue(exception.getMessage().contains("The requested account does not exist."));
	}

	@Test
	void withdrawalFromAccountTest() {
		clientTransaction.setAmount(300.0);
		account.setAccountBalance(100.0);
		Mockito.when(accountRepository.findByAccountNumber(clientTransaction.getAccountNumber()))
				.thenReturn(Optional.of(account));
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> accountService.withdrawalFromAccount(client.getId(), clientTransaction));
		assertTrue(
				exception.getMessage().contains("Your account balance is insufficient to complete this transaction."));
	}

	@Test
	void withdrawalFromAccountOKTest() {
		Mockito.when(accountRepository.findByAccountNumber(clientTransaction.getAccountNumber()))
				.thenReturn(Optional.of(account));
		Mockito.when(operationService.saveOperation(account, EOperation.WITHDRAWEL, clientTransaction))
				.thenReturn(operation);
		ClientBalance clientBalance = accountService.withdrawalFromAccount(client.getId(), clientTransaction);
		assertEquals(80.0, clientBalance.getAmount());
	}

}
