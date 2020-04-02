package com.estacionamento.vagas.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estacionamento.vagas.domain.ControleVeiculoVagaCarro;
import com.estacionamento.vagas.dto.ControleVeiculoVagaCarroDTO;
import com.estacionamento.vagas.dto.ControleVeiculoVagaCarroNewDTO;
import com.estacionamento.vagas.services.ControleVeiculoVagaCarroService;

@RestController //diz que ele é um controlador REST
@RequestMapping(value="/controleVagaCarro") //responde por essa rota
public class ControleVeiculoVagaCarroResource {
	
	@Autowired
	private ControleVeiculoVagaCarroService service;	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ControleVeiculoVagaCarroNewDTO objDTO) {
		
		ControleVeiculoVagaCarro obj = service.fromDTO(objDTO);
		
		
		obj = service.insert(obj);
		
		//Definir resposta da URI
		// fromCurrentRequest:pega o valor da url
		// buildAndExpand: atribui o valor
		// toUri: converte o valor para uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ControleVeiculoVagaCarroDTO objDTO) {
		
		ControleVeiculoVagaCarro obj = service.fromDTO(objDTO);	
		
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
