package com.estacionamento.vagas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.domain.enums.TipoVeiculo;
import com.estacionamento.vagas.dto.VeiculoNewDTO;
import com.estacionamento.vagas.repositories.VeiculoRepository;
import com.estacionamento.vagas.services.exception.DataIntegrityException;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class VeiculoService {

	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private VeiculoRepository repo;	
	
	public Veiculo buscar(Integer id) {
		Optional<Veiculo> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Veiculo.class.getName()
			));
	}
	
	public Veiculo insert(Veiculo obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Veiculo update(Veiculo obj) {
		Veiculo newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	//methodo auxiliar
	private void updateData(Veiculo newObj, Veiculo obj) {
		newObj.setModelo(obj.getModelo());
		newObj.setMarca(obj.getMarca());
		newObj.setCor(obj.getCor());
		newObj.setPlaca(obj.getPlaca());
	}
	
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityException e) {
			throw new DataIntegrityException(e.getMessage());
		}
	}
	
	public List<Veiculo> findAll() {
		return repo.findAll();
	}
	
	//Paginacao
	/*
	 * page: Nº pagina;
	 * linesPerPage: Nº linhas p/ pagina
	 * orderBy: Qual atributo vai ser utilizado para ordenar
	 * direction: DESC || ASC
	*/
	public Page<Veiculo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Veiculo fromDTO(VeiculoNewDTO objDTO) {
		return new Veiculo(objDTO.getId(), objDTO.getMarca(), objDTO.getModelo(), objDTO.getCor(), objDTO.getPlaca(), TipoVeiculo.toEnum(objDTO.getTipoVeiculo()));
	}
}
