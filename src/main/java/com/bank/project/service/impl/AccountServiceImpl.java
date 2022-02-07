package com.bank.project.service.impl;

import static com.bank.project.utils.RestPreconditions.checkNotNull;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.project.exceptions.BadRequestException;
import com.bank.project.exceptions.RessourceNotFoundException;
import com.bank.project.models.Account;
import com.bank.project.models.EOperation;
import com.bank.project.repository.AccountRepository;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ApiError;
import com.bank.project.rest.responses.ClientBalance;
import com.bank.project.rest.responses.ErrorResponse;
import com.bank.project.service.AccountService;
import com.bank.project.service.OperationService;
import com.bank.project.utils.LoggerUtils;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
    private OperationService operationService;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, OperationService operationService) {
		this.accountRepository = accountRepository;
		this.operationService = operationService; 
	}

	@Transactional
	@Override
	public ClientBalance depositAccount(Long clientId, ClientTransaction clientTransaction) {

		Account account = communProcess(clientTransaction);
		double newBalance = account.getAccountBalance() + clientTransaction.getAmount();
		account.setAccountBalance(newBalance);
		accountRepository.save(account);
		operationService.saveOperation(account, EOperation.DEPOSIT, clientTransaction);
		ClientBalance clientBalance = new ClientBalance();
		clientBalance.setAmount(account.getAccountBalance());
		LoggerUtils.logInfo(String.format(
				"The deposit %s of the client with Id %s has been sucessfully transacted.The new account balance is %s.",
				clientTransaction.getAmount(), clientId, account.getAccountBalance()));
		return clientBalance;
	}

	@Transactional
	@Override
	public ClientBalance withdrawalFromAccount(Long clientId, ClientTransaction clientTransaction) {

		Account account = communProcess(clientTransaction);

		if (clientTransaction.getAmount() > account.getAccountBalance()) {
			throw new BadRequestException(new ErrorResponse(ApiError.INSUFFICIENT_BALANCE.getMessage(),
					ApiError.INSUFFICIENT_BALANCE.getDescription()));
		}

		double newBalance = account.getAccountBalance() - clientTransaction.getAmount();
		account.setAccountBalance(newBalance);
		accountRepository.save(account);
		operationService.saveOperation(account, EOperation.WITHDRAWEL, clientTransaction);
		ClientBalance clientBalance = new ClientBalance();
		clientBalance.setAmount(account.getAccountBalance());
		LoggerUtils.logInfo(String.format(
				"The retreive %s of the client with Id %s has been sucessfully made.The new account balance is %s.",
				clientTransaction.getAmount(), clientId, account.getAccountBalance()));

		return clientBalance;
	}

	private Account communProcess(ClientTransaction clientTransaction) {

		checkNotNull(clientTransaction.getAmount(), ApiError.INVALID_BODY_FIELD_AMOUNT);
		checkNotNull(clientTransaction.getAccountNumber(), ApiError.INVALID_BODY_FIELD_ACCOUNT_NUMBER);

		if (clientTransaction.getAmount() < 0) {
			LoggerUtils.logError("The versed amount %s is negative.", clientTransaction.getAmount());
			throw new BadRequestException(new ErrorResponse(ApiError.NEGATIVE_AMOUNT.getMessage(),
					ApiError.NEGATIVE_AMOUNT.getDescription()));
		}
		return accountRepository.findByAccountNumber(clientTransaction.getAccountNumber()).orElseThrow(
				() -> new RessourceNotFoundException(new ErrorResponse(ApiError.RESSOURCE_NOT_FOUND.getMessage(),
						ApiError.RESSOURCE_NOT_FOUND.getDescription())));

	}

}
