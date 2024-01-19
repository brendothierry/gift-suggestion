package com.gift.suggestion.gs.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.gift.suggestion.gs.DTO.ChatCompletionResponse;
import com.gift.suggestion.gs.DTO.ClienteDTO;
import com.gift.suggestion.gs.model.ClienteModel;
import com.gift.suggestion.gs.service.ClienteService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClienteController {

	@Value("${openai.api.url")
	private String OPENAI_API_URL;
	@Value("${openai.api.key")
	private String OPENAI_API_KEY;

	final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping("/gs/obterRespostaDoChatGPT")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<ChatCompletionResponse> obterRespostaDoChatGPT(@RequestBody String mensagem) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(OPENAI_API_KEY);

		JsonObject requestBodyObject = new JsonObject();
		requestBodyObject.addProperty("model", "gpt-3.5-turbo");

		JsonObject systemMessage = new JsonObject();
		systemMessage.addProperty("role", "system");
		systemMessage.addProperty("content", "Você é um assistente virtual.");

		JsonObject userMessage = new JsonObject();
		userMessage.addProperty("role", "user");
		userMessage.addProperty("content", mensagem);
		requestBodyObject.add("messages", new Gson().toJsonTree(new JsonObject[] { systemMessage, userMessage }));

		String requestBody = new Gson().toJson(requestBodyObject);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ChatCompletionResponse> responseEntity = restTemplate.postForEntity(OPENAI_API_URL,
				requestEntity, ChatCompletionResponse.class);

		try {
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				ChatCompletionResponse response = responseEntity.getBody();
				List<ChatCompletionResponse.Choice> choices = response.getChoices();

				if (choices != null && !choices.isEmpty()) {
					ChatCompletionResponse.Choice firstChoice = choices.get(0);
					ChatCompletionResponse.Message message = firstChoice.getMessage();
					System.out.println("Retorno =" + message.getContent().toString());
					return new ResponseEntity<ChatCompletionResponse>(response, HttpStatus.OK);
				}
			}
		} catch (Exception exception) {
			exception.getMessage();
		}
		return null;
	}

	@PostMapping("/gs/create-cliente")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		var clienteModel = new ClienteModel();
		BeanUtils.copyProperties(clienteDTO, clienteModel);
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.criarCliente(clienteModel));
	}

	@GetMapping("/gs/getById-cliente/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> buscarClientePorId(@PathVariable(value = "id") UUID id) {
		Optional<ClienteModel> clienteModelOptional = clienteService.buscarClientePorId(id);
		if (!clienteModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
	}
	
	@GetMapping("/gs/getByEmail-cliente/{email}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> buscarPorEmail(@PathVariable(value = "email") String email) {
		Optional<ClienteModel> clienteModelOptional = clienteService.buscarClientePorEmail(email);
		if (clienteModelOptional == null || !clienteModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get().getEmail());
	}

	@PutMapping("/gs/update-cliente/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ClienteDTO clienteDTO) {
		Optional<ClienteModel> clienteModelOptional = clienteService.buscarClientePorId(id);
		if (!clienteModelOptional.isPresent()) {
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

	@DeleteMapping("/gs/delete-cliente/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id) {
		Optional<ClienteModel> clienteModelOptional = clienteService.buscarClientePorId(id);
		if (!clienteModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
		}
		clienteService.delete(clienteModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
	}

	@GetMapping("/gs/findAll")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<ClienteModel>> findAllClientes() {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClientes());
	}

}
