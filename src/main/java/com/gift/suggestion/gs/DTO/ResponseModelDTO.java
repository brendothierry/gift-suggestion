package com.gift.suggestion.gs.DTO;

public class ResponseModelDTO {

	private String mensagem;

	public ResponseModelDTO(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
