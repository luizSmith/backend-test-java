package com.estacionamento.vagas.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.domain.Endereco;
import com.estacionamento.vagas.domain.VagaCarro;
import com.estacionamento.vagas.domain.VagaMoto;
import com.estacionamento.vagas.domain.enums.StatusVaga;
import com.estacionamento.vagas.dto.EmpresaNewDTO;
import com.estacionamento.vagas.repositories.EmpresaRepository;
import com.estacionamento.vagas.repositories.EnderecoRepository;
import com.estacionamento.vagas.repositories.VagaCarroRepository;
import com.estacionamento.vagas.repositories.VagaMotoRepository;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	// Ele  tem que chamar o obj de acesso a dados uma dependencia
	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private EmpresaRepository repo;	
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private VagaCarroRepository vagaCarroRepository;
	
	@Autowired
	private VagaMotoRepository vagaMotoRepository;
	
	public Empresa buscar(Integer id) {
		Optional<Empresa> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()
			));
	}
	
	//Inserindo obj novo
	@Transactional
	public Empresa insert(Empresa obj) {
		obj.setId(null);
		
		repo.save(obj);
		
		enderecoRepository.save(obj.getEndereco());
		
		vagaCarroRepository.saveAll(obj.getVagaCarro());
		vagaMotoRepository.saveAll(obj.getVagaMoto());
		
		return obj;
	}
	
	public Empresa fromDTO(EmpresaNewDTO objDTO) {
		Empresa emp = new Empresa(null, objDTO.getNome(), objDTO.getCnpj());
		
		emp.getTelefones().add(objDTO.getTelefone1());
		
		if (objDTO.getTelefone2() != null) {
			emp.getTelefones().add(objDTO.getTelefone2());
		}
		
		Endereco end = new Endereco(null, objDTO.getLongradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), emp);
		
		emp.setEndereco(end);
		
		for (int x = 1; x <= objDTO.getnVagaCarro() ; x++) {
			VagaCarro vagc = new VagaCarro(null, emp, StatusVaga.toEnum(1));
			emp.getVagaCarro().add(vagc);
		}
		
		
		for (int j = 1; j <= objDTO.getnVagaMoto() ; j++) {
			VagaMoto vagm = new VagaMoto(null, emp, StatusVaga.toEnum(1));
			emp.getVagaMoto().add(vagm);
		}
		
		return emp;
	}
	
}
