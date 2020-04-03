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

import com.estacionamento.vagas.domain.ControleVeiculoVagaMoto;
import com.estacionamento.vagas.dto.ControleVeiculoVagaMotoDTO;
import com.estacionamento.vagas.dto.ControleVeiculoVagaMotoNewDTO;
import com.estacionamento.vagas.services.ControleVeiculoVagaMotoService;

@RestController //diz que ele é um controlador REST
@RequestMapping(value="/controleVagaMoto") //responde por essa rota
public class ControleVeiculoVagaMotoResource {
	
	@Autowired
	private ControleVeiculoVagaMotoService service;	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ControleVeiculoVagaMotoNewDTO objDTO) {
		
		ControleVeiculoVagaMoto obj = service.fromDTO(objDTO);
		
		
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
	public ResponseEntity<Void> update(@Valid @RequestBody ControleVeiculoVagaMotoDTO objDTO) {
		
		ControleVeiculoVagaMoto obj = service.fromDTO(objDTO);	
		
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
