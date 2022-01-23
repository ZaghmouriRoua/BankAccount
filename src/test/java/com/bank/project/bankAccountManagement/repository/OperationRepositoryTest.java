package com.bank.project.bankAccountManagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bank.project.models.Account;
import com.bank.project.models.Operation;
import com.bank.project.repository.OperationRepository;

@DataJpaTest
class OperationRepositoryTest {
	
	@Autowired
	OperationRepository operationRepository;
	
	 @Autowired
	 private TestEntityManager entityManager;
	
	@Test
	void findByAccountsIdTest() {
		Account account = new Account();
		account.setAccountBalance(20.0);
		account.setAccountNumber(1234l);
		Operation operation=new Operation();
		operation.setAccount(account);
		entityManager.persist(account);
		entityManager.persist(operation);
		List<Long> ls=new ArrayList<>();
		ls.add(account.getId());
		List<Operation> operations=operationRepository.findByAccountsId(ls);
		assertEquals(2, operations.size());
	}

}
