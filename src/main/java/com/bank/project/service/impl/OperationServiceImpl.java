package com.bank.project.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.project.models.Account;
import com.bank.project.models.EOperation;
import com.bank.project.models.Operation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	private OperationRepository operationRepository;
	private AccountRepository accountRepository;

	@Autowired
	public OperationServiceImpl(OperationRepository operationRepository, AccountRepository accountRepository) {
		this.operationRepository = operationRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Operation> displayOperationsByClient(Long clientId) {

		List<Account> accounts = accountRepository.findByClientId(clientId);

		List<Long> accountsId = accounts.stream().map(Account::getId).collect(Collectors.toList());

		return operationRepository.findByAccountsId(accountsId).stream()
				.sorted(Comparator.comparing(Operation::getOperationDate).reversed()).collect(Collectors.toList());
	}

	public Operation saveOperation(Account account, EOperation operationName, ClientTransaction clientTransaction) {

		Operation operation = new Operation();
		operation.setOperationName(operationName);
		operation.setOperationDate(new Date());
		operation.setTransactionAmount(clientTransaction.getAmount());
		operation.setAccount(account);
		operation.setBalanceAfterTransaction(account.getAccountBalance());
		operation.setAccountType(account.getAccountType());
		return operationRepository.save(operation);
	}

}
