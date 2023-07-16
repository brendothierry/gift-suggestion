package com.gift.suggestion.gs.controller;

import javax.validation.Valid;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Object> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		var clienteModel = new ClienteModel();
		BeanUtils.copyProperties(clienteDTO, clienteModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(clienteModel));
	}
	
	@DeleteMapping("/gs/delete-cliente/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value= "id")UUID id){
		Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
		if(!clienteModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
		}
		clienteService.delete(clienteModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
	
	}
	
	
	
	
	
	
	
	
	
	
}
