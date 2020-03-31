package com.estacionamento.vagas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.domain.VagaCarro;
import com.estacionamento.vagas.domain.enums.StatusVaga;
import com.estacionamento.vagas.dto.VagaDTO;
import com.estacionamento.vagas.dto.VagaNewDTO;
import com.estacionamento.vagas.repositories.VagaCarroRepository;
import com.estacionamento.vagas.services.exception.DataIntegrityException;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class VagaCarroService {

	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private VagaCarroRepository repo;	
	
	public VagaCarro buscar(Integer id) {
		Optional<VagaCarro> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + VagaCarro.class.getName()
			));
	}
	
	public VagaCarro insert(VagaCarro obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public VagaCarro fromDTO(VagaNewDTO objDTO) {
		Empresa emp = new Empresa(objDTO.getEmpresaId(), null, null);
		return new VagaCarro(null, emp, StatusVaga.toEnum(1));
	}
	
	public VagaCarro update(VagaCarro obj) {
		VagaCarro newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	//methodo auxiliar
	private void updateData(VagaCarro newObj, VagaCarro obj) {
		newObj.setStatusVaga(obj.getStatusVaga());
	}
	
	public VagaCarro fromDTO(VagaDTO objDTO) {
		Empresa emp = new Empresa(objDTO.getEmpresaId(), null, null);
		return new VagaCarro(objDTO.getId(), emp, StatusVaga.toEnum(objDTO.getStatusVaga()));
	}
	
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityException e) {
			throw new DataIntegrityException(e.getMessage());
		}
	}
	
	public List<VagaCarro> findAll() {
		return repo.findAll();
	}
	
	//Paginacao
	/*
	 * page: Nº pagina;
	 * linesPerPage: Nº linhas p/ pagina
	 * orderBy: Qual atributo vai ser utilizado para ordenar
	 * direction: DESC || ASC
	*/
	public Page<VagaCarro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
}
