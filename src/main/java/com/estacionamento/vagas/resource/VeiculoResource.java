package com.estacionamento.vagas.resource;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.dto.VeiculoNewDTO;
import com.estacionamento.vagas.services.VeiculoService;

@RestController //diz que ele é um controlador REST
@RequestMapping(value="/veiculos") //responde por essa rota
public class VeiculoResource {

	@Autowired
	private VeiculoService service;
	
	//para ser uma requisicao esta funcao deve ser associada a um dos verbos do HTTP
	//status:200
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Veiculo> find(@PathVariable Integer id) {
		
		Veiculo obj = service.buscar(id);
		return ResponseEntity.ok().body(obj); //ele vai responder de acordo com o obj de resposta entity
	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody VeiculoNewDTO objDTO) {
		Veiculo obj = service.fromDTO(objDTO);
		
		obj = service.insert(obj);
		//Definir resposta da URI
		// fromCurrentRequest:pega o valor da url
		// buildAndExpand: atribui o valor
		// toUri: converte o valor para uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Veiculo obj, @PathVariable Integer id) {
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
	public ResponseEntity<List<Veiculo>> findAll() {
		List<Veiculo> list = service.findAll();
		return ResponseEntity.ok().body(list);		
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	//Parametros opcionais
	public ResponseEntity<Page<Veiculo>> findPage(
			// Por padrao pagina = 0
			@RequestParam(value="page", defaultValue="0")
			Integer page,
			//Linhas por pagina
			@RequestParam(value="linesPerPage", defaultValue="48")
			Integer linesPerPage, 
			//Ordenar por
			@RequestParam(value="orderBy", defaultValue="modelo")
			String orderBy, 
			//Direcao
			@RequestParam(value="direction", defaultValue="ASC")
			String direction
	) {
		Page<Veiculo> list = service.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list); //ele vai responder de acordo com o obj de resposta entity
	}
	
}
