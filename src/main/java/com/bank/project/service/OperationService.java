package com.bank.project.service;

import java.util.List;

import com.bank.project.models.Account;
import com.bank.project.models.EOperation;
import com.bank.project.models.Operation;
import com.bank.project.rest.requests.ClientTransaction;

public interface OperationService {
	
	List<Operation> displayOperationsByClient(Long clientId);
	Operation saveOperation(Account account,EOperation operationType ,ClientTransaction clientTransaction);

}
