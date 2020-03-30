package com.estacionamento.vagas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.VagaCarro;
import com.estacionamento.vagas.repositories.VagaCarroRepository;
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
}
