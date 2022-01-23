package com.bank.project.rest.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientBalance {

	@JsonProperty("new_amount")
	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
