package com.estacionamento.vagas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.Relatorio;
import com.estacionamento.vagas.repositories.RelatorioRepository;
import com.estacionamento.vagas.services.exception.DataIntegrityException;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class RelatorioService {
	
	// Ele  tem que chamar o obj de acesso a dados uma dependencia
	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private RelatorioRepository repo;	

	
	public Relatorio buscarId(Integer id) {
		Optional<Relatorio> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Relatorio.class.getName()
			));
	}
	
	//Inserindo obj novo
	@Transactional
	public Relatorio insert(Relatorio obj) {
		obj.setId(null);	
		
		return repo.save(obj);
	}
	
	@Transactional
	public Relatorio update(Relatorio obj) {
		Relatorio newObj = buscarId(obj.getId());
		updateData(newObj, obj);
		
		return repo.save(newObj);
	}
	
	//methodo auxiliar
	private void updateData(Relatorio newObj, Relatorio obj) {
		newObj.setEntrada(obj.getEntrada());
		newObj.setNumeroVaga(obj.getNumeroVaga());
		newObj.setSaida(obj.getSaida());
		newObj.setVeiculo(obj.getVeiculo());
	}
	
	//delete
	public void delete(Integer id) {
		buscarId(id);
		
		try {
			repo.deleteById(id);				
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel Excluir uma empresa que possui Vagas");
		}
	}
	
	//retorna todos
	public List<Relatorio> findAll(){
		return repo.findAll();
	}
	
	//Paginacao
	/*
	 * page: Nº pagina;
	 * linesPerPage: Nº linhas p/ pagina
	 * orderBy: Qual atributo vai ser utilizado para ordenar
	 * direction: DESC || ASC
	*/
	public Page<Relatorio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
}
