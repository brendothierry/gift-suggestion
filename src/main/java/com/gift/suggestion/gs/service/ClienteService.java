package com.gift.suggestion.gs.service;

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
	
	
	public Optional<ClienteModel> findById(UUID id){
		return clienteRepository.findById(id);
	}
	
	@Transactional
	public void delete(ClienteModel parkingSpotModel) {
		clienteRepository.delete(parkingSpotModel);
	}
	
	
	
	
	
	
	
	
	
	
	
}