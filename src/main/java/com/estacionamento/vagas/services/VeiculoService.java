package com.estacionamento.vagas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.repositories.VeiculoRepository;
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
}
