package com.bank.project.bankAccountManagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bank.project.models.Account;
import com.bank.project.models.Client;
import com.bank.project.repository.AccountRepository;

@DataJpaTest
class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	private TestEntityManager entityManager;
	private Client client;
	private Account account;

	@BeforeEach
	void init() {
		client = new Client();
		client.setFirstName("client1");
		account = new Account();
		account.setAccountBalance(20.0);
		account.setClient(client);
		account.setAccountNumber(1234l);

		entityManager.persist(client);
		entityManager.persist(account);
	}

	@Test
	void findByClientIdAndAccountNumberTest() {

		Optional<Account> returnedAccount = accountRepository.findByClientIdAndAccountNumber(client.getId(),
				account.getAccountNumber());
		assertEquals(20.0, returnedAccount.get().getAccountBalance());

	}

	@Test
	void findByClientIdTest() {
		List<Account> accounts = accountRepository.findByClientId(client.getId());
		assertEquals(1, accounts.size());
	}

}
