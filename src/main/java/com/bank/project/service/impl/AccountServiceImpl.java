package com.bank.project.service.impl;


import static com.bank.project.utils.RestPreconditions.checkNotNull;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.project.exceptions.BadRequestException;
import com.bank.project.exceptions.RessourceNotFoundException;
import com.bank.project.models.Account;
import com.bank.project.models.EOperation;
import com.bank.project.models.Operation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.repository.OperationRepository;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ApiError;
import com.bank.project.rest.responses.ClientBalance;
import com.bank.project.rest.responses.ErrorResponse;
import com.bank.project.service.AccountService;
import com.bank.project.utils.LoggerUtils;


@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	OperationRepository operationRepository;
	
	@Transactional
	@Override
	public ClientBalance depositAccount(Long clientId,ClientTransaction clientTransaction) {
		
		Optional<Account> account=communProcess(clientId,clientTransaction);
		double newBalance=account.get().getAccountBalance()+clientTransaction.getAmount();
		account.get().setAccountBalance(newBalance);
		accountRepository.save(account.get()); 
		
		Operation operation=saveOperation( account.get(), clientTransaction);
		operation.setOperationName(EOperation.DEPOSIT);
		operationRepository.save(operation);
		
		ClientBalance clientBalance=new ClientBalance();
		clientBalance.setAmount(account.get().getAccountBalance());
		LoggerUtils.logInfo(String.format("The deposit %s has been sucessfully transacted.The new account balance is %s",clientTransaction.getAmount(),account.get().getAccountBalance()));
		
		return clientBalance; 
	} 

	@Transactional
	@Override
	public ClientBalance withdrawalFromAccount(Long clientId, ClientTransaction clientTransaction) {
		
		Optional<Account> account=communProcess(clientId,clientTransaction);	
		
		if(clientTransaction.getAmount()>account.get().getAccountBalance()) {
			throw new BadRequestException(new ErrorResponse(ApiError.INSUFFICIENT_BALANCE.getMessage(), ApiError.INSUFFICIENT_BALANCE.getDescription()));
		}
		
		double newBalance=account.get().getAccountBalance()-clientTransaction.getAmount();
		account.get().setAccountBalance(newBalance);
		accountRepository.save(account.get());
		
		Operation operation=saveOperation( account.get(), clientTransaction);
		operation.setOperationName(EOperation.WITHDRAWEL);
		operationRepository.save(operation);
		
		ClientBalance clientBalance=new ClientBalance();
		clientBalance.setAmount(account.get().getAccountBalance());
		LoggerUtils.logInfo(String.format("The retreive %s has been sucessfully made.The new account balance is %s",clientTransaction.getAmount(),account.get().getAccountBalance()));
		
		return clientBalance;
	} 

	private Optional<Account> communProcess(Long clientId, ClientTransaction clientTransaction) {

		checkNotNull(clientTransaction.getAmount(), ApiError.INVALID_BODY_FIELD_AMOUNT);
		checkNotNull(clientTransaction.getAccountNumber(), ApiError.INVALID_BODY_FIELD_ACCOUNT_NUMBER);
		
		if (clientTransaction.getAmount() < 0) {
			LoggerUtils.logError("The versed amount %s is negative.", clientTransaction.getAmount());
			throw new BadRequestException(new ErrorResponse(ApiError.NEGATIVE_AMOUNT.getMessage(),
					ApiError.NEGATIVE_AMOUNT.getDescription()));
		}
		
		Optional<Account> account = accountRepository.findByClientIdAndAccountNumber(clientId,
				clientTransaction.getAccountNumber());

		if (!account.isPresent()) {
			LoggerUtils.logError("The client does not have an account with number %s.",account.get().getAccountNumber());
			throw new RessourceNotFoundException(new ErrorResponse(ApiError.RESSOURCE_NOT_FOUND.getMessage(),
					ApiError.RESSOURCE_NOT_FOUND.getDescription()));
		}
		return account;
	}
	
	private Operation saveOperation(Account account, ClientTransaction clientTransaction) {

		Operation operation = new Operation();
		operation.setOperationDate(new Date());
		operation.setTransactionAmount(clientTransaction.getAmount());
		operation.setAccount(account);
		operation.setBalanceAfterTransaction(account.getAccountBalance());
		operation.setAccountType(account.getAccountType());

		return operation;
	}
}
