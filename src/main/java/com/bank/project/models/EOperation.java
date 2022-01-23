package com.bank.project.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EOperation {
	
	@JsonProperty("deposit")
	DEPOSIT,
	@JsonProperty("withdrawel")
	WITHDRAWEL

}
