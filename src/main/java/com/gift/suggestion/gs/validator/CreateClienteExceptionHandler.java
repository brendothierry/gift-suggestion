package com.gift.suggestion.gs.validator;

public class CreateClienteExceptionHandler extends Exception {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateClienteExceptionHandler(String mensagem) {
	        super("Informação de usuário já existente.");
	    }
}
