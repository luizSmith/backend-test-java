package com.estacionamento.vagas.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) { //mensagem de exceção
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) { //mensagem de exceção com causa
		super(msg, cause);
	}
}
