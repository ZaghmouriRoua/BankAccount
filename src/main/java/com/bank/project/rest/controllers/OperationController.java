package com.bank.project.rest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.project.dto.OperationDto;
import com.bank.project.models.Operation;
import com.bank.project.rest.controllers.interfaces.OperationApi;
import com.bank.project.service.OperationService;



@RestController
@RequestMapping("/api/v1/account/operation")
public class OperationController implements OperationApi{

	@Autowired
	OperationService operationService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<OperationDto> displayAllOperationByClient(Long clientId) {
		return operationService.displayOperationsByClient(clientId).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private OperationDto convertToDTO(Operation operation) {
		return modelMapper.map(operation, OperationDto.class);
	}

}
