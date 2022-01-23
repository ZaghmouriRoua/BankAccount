package com.bank.project.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operation")
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "operation_id")
	private Long id;
	
	@Column(name="operation_name")
	@Enumerated(EnumType.STRING)
	private EOperation operationName;
	
	@Column(name="transaction_amount")
	private Double transactionAmount;
	
	@Column(name="operation_date")
	private Date operationDate;
	
	@Column(name="balance_after_transaction")
	private Double balanceAfterTransaction;
	
	@Column(name="account_type")
	private EAccountType accountType;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
