package com.gift.suggestion.gs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gift.suggestion.gs.DTO.ClienteDTO;

@RestController
@RequestMapping("/api")

public class ClienteController {
	
	@PostMapping("/criar-cliente")
	public ResponseEntity<String> criarCliente(@RequestBody ClienteDTO clienteDTO) {
		
		
		return null;
	}
}
