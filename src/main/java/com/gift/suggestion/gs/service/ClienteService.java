package com.gift.suggestion.gs.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gift.suggestion.gs.model.ClienteModel;
import com.gift.suggestion.gs.repositories.ClienteRepository;

@Service
@Component
public class ClienteService {
	
	final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public ClienteModel criarCliente(ClienteModel clienteModel) {
		return clienteRepository.save(clienteModel);
	}
	
	@Transactional
	public List<ClienteModel> findAllClientes() {		
		return clienteRepository.findAll();
  }
  
  @Transactional
	public Optional<ClienteModel> buscarClientePorId(UUID id) {
		return clienteRepository.findById(id);	
	}
	
	@Transactional
	public ClienteModel atualizarCliente(ClienteModel clienteModel) {
		return clienteRepository.save(clienteModel);
	}
}