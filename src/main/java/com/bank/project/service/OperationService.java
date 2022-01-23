package com.bank.project.service;

import java.util.List;

import com.bank.project.models.Operation;

public interface OperationService {
	
	List<Operation> displayOperationsByClient(Long clientId);

}
