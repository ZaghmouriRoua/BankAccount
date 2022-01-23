package com.bank.project.service;

import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ClientBalance;

public interface AccountService {

	ClientBalance depositAccount(Long clientId,ClientTransaction clientTransaction);
	
	ClientBalance withdrawalFromAccount(Long clientId,ClientTransaction clientTransaction);
}
