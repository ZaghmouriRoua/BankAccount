package com.bank.project.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EAccountType {
	
	@JsonProperty("courant")
	COURANT,
	@JsonProperty("epargne")
	EPARGNE;
	


}
