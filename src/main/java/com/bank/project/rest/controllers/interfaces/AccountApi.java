package com.bank.project.rest.controllers.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.project.rest.requests.ClientTransaction;
import com.bank.project.rest.responses.ClientBalance;

public interface AccountApi {
	
	@PostMapping(value="/deposit/{clientId}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	ClientBalance depositAmount(@PathVariable("clientId") Long clientId,@RequestBody ClientTransaction clientTransaction );
	
	@PostMapping(value="/retreive/{clientId}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	ClientBalance retreiveAmount(@PathVariable("clientId") Long clientId,@RequestBody ClientTransaction clientTransaction );

}
