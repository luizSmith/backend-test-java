package com.estacionamento.vagas.services.exception;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) { //mensagem de exceção
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) { //mensagem de exceção com causa
		super(msg, cause);
	}
}
