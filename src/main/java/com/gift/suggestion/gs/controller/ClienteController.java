package com.gift.suggestion.gs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gift.suggestion.gs.DTO.ClienteDTO;
import com.gift.suggestion.gs.model.ClienteModel;
import com.gift.suggestion.gs.service.ClienteService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/gift-suggestion")

public class ClienteController {
	
	final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping("/gs/criar-cliente")
	public ResponseEntity<Object> criarCliente (@RequestBody @Valid ClienteDTO clienteDTO) {
		var clienteModel = new ClienteModel();
		BeanUtils.copyProperties(clienteDTO, clienteModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(clienteModel));
	}
	
	@GetMapping("/gs/findAll")
	public ResponseEntity<List<ClienteModel>> findAllClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClientes());
	}
	
	
	
}
