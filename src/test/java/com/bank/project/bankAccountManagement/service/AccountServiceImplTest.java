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
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ClientBalance;
import com.bank.project.service.impl.AccountServiceImpl;


@SpringJUnitConfig(classes = AccountServiceImpl.class)
class AccountServiceImplTest {
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@MockBean
	AccountRepository accountRepository;
	@MockBean
	OperationRepository operationRepository;
	
	ClientTransaction clientTransaction;
	Client client;
	Account account;
	
	@BeforeEach
	void init() {
	 clientTransaction=new ClientTransaction();
	 clientTransaction.setAmount(20.0);
	 clientTransaction.setAccountNumber(5432l);;
	 client=new Client();
	 client.setId(1l);
	 account=new Account();
	 account.setAccountBalance(100.0);
	 account.setAccountType(EAccountType.COURANT);
	 account.setClient(client);
	}
	
	@Test
	void depositAccountTestOK() {
		Mockito.when(accountRepository.findByClientIdAndAccountNumber(client.getId(), clientTransaction.getAccountNumber())).thenReturn(Optional.of(account));
		ClientBalance clientBalance=accountServiceImpl.depositAccount(client.getId(), clientTransaction);
		assertEquals(120.0,clientBalance.getAmount());
	}
	
	@Test 
	void nullAmountFielTest() {
		ClientTransaction clientTransaction=new ClientTransaction();
		BadRequestException exception=assertThrows(BadRequestException.class,()->accountServiceImpl.depositAccount(client.getId(), clientTransaction));
		assertTrue(exception.getMessage().contains("The amount is mandatory field."));
	}
	
	@Test
	void negativeClientTransactionTest() {
		clientTransaction.setAmount(-10.0);
		BadRequestException exception=assertThrows(BadRequestException.class,()->accountServiceImpl.depositAccount(client.getId(), clientTransaction));
		assertTrue(exception.getMessage().contains("The versed ammount can not be a nagative value."));
	}
	
	@Test
	void accountNotFoundTest(){
		RessourceNotFoundException exception=assertThrows(RessourceNotFoundException.class,()->accountServiceImpl.depositAccount(client.getId(), clientTransaction));
		assertTrue(exception.getMessage().contains("The requested resource does not exist."));
	}

	@Test
	void withdrawalFromAccountTest() {
		clientTransaction.setAmount(300.0);
		account.setAccountBalance(100.0);
		Mockito.when(accountRepository.findByClientIdAndAccountNumber(client.getId(), clientTransaction.getAccountNumber())).thenReturn(Optional.of(account));
		BadRequestException exception=assertThrows(BadRequestException.class,()->accountServiceImpl.withdrawalFromAccount(client.getId(), clientTransaction));
		assertTrue(exception.getMessage().contains("Your account balance is insufficient to complete this transaction."));
	}
	
	@Test
	void withdrawalFromAccountOKTest() {
		Mockito.when(accountRepository.findByClientIdAndAccountNumber(client.getId(), clientTransaction.getAccountNumber())).thenReturn(Optional.of(account));
		ClientBalance clientBalance=accountServiceImpl.withdrawalFromAccount(client.getId(), clientTransaction);
		assertEquals(80.0,clientBalance.getAmount());
	}
	
	
}
