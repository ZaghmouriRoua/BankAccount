package com.bank.project.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.project.models.Account;
import com.bank.project.models.Operation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService{
	
	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<Operation> displayOperationsByClient(Long clientId) {
		
		List<Account> accounts =accountRepository.findByClientId(clientId);
		
		List<Long>accountsId=accounts.stream().map(acc->acc.getId()).collect(Collectors.toList());
		
		return operationRepository.findByAccountsId(accountsId).stream()
				.sorted(Comparator.comparing(Operation:: getOperationDate).reversed()).collect(Collectors.toList());
	} 

	
	
}
