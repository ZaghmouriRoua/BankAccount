package com.bank.project.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.project.rest.controllers.interfaces.AccountApi;
import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ClientBalance;
import com.bank.project.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController implements AccountApi {

	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public ClientBalance depositAmount(Long clientId, ClientTransaction clientTransaction) {
		return accountService.depositAccount(clientId, clientTransaction);
	}

	@Override
	public ClientBalance retreiveAmount(Long clientId, ClientTransaction clientTransaction) {
		return accountService.withdrawalFromAccount(clientId, clientTransaction);
	}

}
