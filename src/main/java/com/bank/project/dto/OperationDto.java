package com.bank.project.dto;

import com.bank.project.models.EAccountType;
import com.bank.project.models.EOperation;

public class OperationDto {
	
	private EOperation operationName;
	private Double transactionAmount;
	private String operationDate;
	private Double balanceAfterTransaction;
	private EAccountType accountType;

	public EOperation getOperationName() {
		return operationName;
	}

	public void setOperationName(EOperation operationName) {
		this.operationName = operationName;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public Double getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	public EAccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(EAccountType accountType) {
		this.accountType = accountType;
	}

}
