package com.gift.suggestion.gs.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	

	@PostMapping("/gs/create-cliente")
	public ResponseEntity<Object> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		var clienteModel = new ClienteModel();
		BeanUtils.copyProperties(clienteDTO, clienteModel);
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.criarCliente(clienteModel));
	}
	
	@GetMapping("/gs/getById-cliente/{id}")
	public ResponseEntity<Object> buscarClientePorId(@PathVariable(value = "id") UUID id) {
		Optional<ClienteModel> clienteModelOptional = clienteService.buscarClientePorId(id);
		if(!clienteModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
	}
	 
	@PutMapping("/gs/update-cliente/{id}")
	public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id") UUID id,@RequestBody @Valid ClienteDTO clienteDTO) {
		Optional<ClienteModel> clienteModelOptional = clienteService.buscarClientePorId(id);
		if(!clienteModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found");
		}
		var clienteModel = clienteModelOptional.get();
		clienteModel.setCelular(clienteDTO.getCelular());
		clienteModel.setCpf(clienteDTO.getCpf());
		clienteModel.setDataNascimento(clienteDTO.getDataNascimento());
		clienteModel.setEmail(clienteDTO.getEmail());
		clienteModel.setNome(clienteDTO.getNome());
		clienteModel.setLogin(clienteDTO.getLogin());
		clienteModel.setSenha(clienteDTO.getSenha());
		clienteModel.setConfirmaSenha(clienteDTO.getConfirmaSenha());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteService.criarCliente(clienteModel));
	}
	
	@GetMapping("/gs/findAll")
	public ResponseEntity<List<ClienteModel>> findAllClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClientes());
	}
	
	
	
}
