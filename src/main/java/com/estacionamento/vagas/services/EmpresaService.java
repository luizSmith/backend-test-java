package com.estacionamento.vagas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.repositories.EmpresaRepository;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	// Ele  tem que chamar o obj de acesso a dados uma dependencia
	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private EmpresaRepository repo;	
	
	public Empresa buscar(Integer id) {
		Optional<Empresa> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()
			));
	}
}
