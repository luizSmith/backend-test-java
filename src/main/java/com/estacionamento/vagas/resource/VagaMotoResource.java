package com.estacionamento.vagas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.vagas.domain.VagaMoto;
import com.estacionamento.vagas.services.VagaMotoService;

@RestController //diz que ele é um controlador REST
@RequestMapping(value="/vagasmotos") //responde por essa rota
public class VagaMotoResource {
	
	@Autowired
	private VagaMotoService service;
	
	//para ser uma requisicao esta funcao deve ser associada a um dos verbos do HTTP
	//status:200
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<VagaMoto> find(@PathVariable Integer id) {
		
		VagaMoto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj); //ele vai responder de acordo com o obj de resposta entity
	
	}
}
