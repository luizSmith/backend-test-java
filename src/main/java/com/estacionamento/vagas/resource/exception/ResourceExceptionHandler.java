package com.estacionamento.vagas.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

//um obj especial que intercepta a requisicao que trata o retorno da resposta se necessario
@ControllerAdvice
public class ResourceExceptionHandler {
	
	//status:404
	@ExceptionHandler(ObjectNotFoundException.class) //indica que essa funcao vai tratar esse tipo de retorno
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
