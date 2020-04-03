package com.estacionamento.vagas.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.vagas.domain.Relatorio;
import com.estacionamento.vagas.services.RelatorioService;

@RestController //diz que ele é um controlador REST
@RequestMapping(value="/relatorios") //responde por essa rota
public class RelatorioResource {
	
	@Autowired
	private RelatorioService service;
	
	//para ser uma requisicao esta funcao deve ser associada a um dos verbos do HTTP
	//status:200
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Relatorio> find(@PathVariable Integer id) {
		
		Relatorio obj = service.buscarId(id);
		return ResponseEntity.ok().body(obj); //ele vai responder de acordo com o obj de resposta entity
	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Relatorio obj, @PathVariable Integer id) {
		
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Relatorio>> findAll() {
		List<Relatorio> list = service.findAll();
		return ResponseEntity.ok().body(list);		
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	//Parametros opcionais
	public ResponseEntity<Page<Relatorio>> findPage(
			// Por padrao pagina = 0
			@RequestParam(value="page", defaultValue="0")
			Integer page,
			//Linhas por pagina
			@RequestParam(value="linesPerPage", defaultValue="48")
			Integer linesPerPage, 
			//Ordenar por
			@RequestParam(value="orderBy", defaultValue="id")
			String orderBy, 
			//Direcao
			@RequestParam(value="direction", defaultValue="ASC")
			String direction
	) {
		Page<Relatorio> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list); //ele vai responder de acordo com o obj de resposta entity
	}
}
