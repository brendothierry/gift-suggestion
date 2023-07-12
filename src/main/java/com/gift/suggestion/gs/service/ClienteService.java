package com.gift.suggestion.gs.service;

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
}