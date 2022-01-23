package com.bank.project.rest.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.project.dto.OperationDto;

public interface OperationApi {

	@GetMapping(value = "{id}", produces = "application/json")
	public List<OperationDto> displayAllOperationByClient(@PathVariable("id") Long clientId);
}
