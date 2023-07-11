package com.gift.suggestion.gs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gift.suggestion.gs.DTO.ClienteDTO;

@Service
public class ClienteService {
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	public ClienteService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	 public void criarCliente(ClienteDTO cliente) {

	        HttpEntity<ClienteDTO> requestEntity = new HttpEntity<>(cliente);
	        String url = "";
	        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            // A requisição foi bem-sucedida
	            System.out.println("Cliente criado com sucesso!");
	        } else {
	            // Lidar com erros, se necessário
	            System.out.println("Ocorreu um erro ao criar o cliente.");
	        }
	    }
}
