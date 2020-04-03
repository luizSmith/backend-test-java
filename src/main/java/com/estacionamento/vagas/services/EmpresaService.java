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

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.domain.Endereco;
import com.estacionamento.vagas.domain.VagaCarro;
import com.estacionamento.vagas.domain.VagaMoto;
import com.estacionamento.vagas.domain.enums.StatusVaga;
import com.estacionamento.vagas.dto.EmpresaDTO;
import com.estacionamento.vagas.dto.EmpresaNewDTO;
import com.estacionamento.vagas.repositories.EmpresaRepository;
import com.estacionamento.vagas.repositories.EnderecoRepository;
import com.estacionamento.vagas.repositories.VagaCarroRepository;
import com.estacionamento.vagas.repositories.VagaMotoRepository;
import com.estacionamento.vagas.services.exception.DataIntegrityException;
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
	
	public Empresa buscarId(Integer id) {
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
	
	@Transactional
	public Empresa update(Empresa obj) {
		Empresa newObj = buscarId(obj.getId());
		updateData(newObj, obj);
		
		repo.save(newObj);
		enderecoRepository.save(newObj.getEndereco());
		
		return newObj;
	}
	
	//methodo auxiliar
	private void updateData(Empresa newObj, Empresa obj) {
		newObj.setNome(obj.getNome());
		
		Endereco end = newObj.getEndereco();
		end.setBairro(obj.getEndereco().getBairro());
		end.setCep(obj.getEndereco().getCep());
		end.setComplemento(obj.getEndereco().getComplemento());
		end.setLongradouro(obj.getEndereco().getLongradouro());
		end.setNumero(obj.getEndereco().getNumero());
		
		newObj.setEndereco(end);
	}
	
	public Empresa fromDTO(EmpresaDTO objDTO) {
		Empresa emp = new Empresa(objDTO.getId(), objDTO.getNome(), null);
		Endereco end = new Endereco(null, objDTO.getLongradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), emp);
		emp.setEndereco(end);
		
		return emp;
	}
	
	//delete
	public void delete(Integer id) {
		buscarId(id);
		
		try {
			repo.deleteById(id);				
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel Excluir uma empresa que possui Vagas sendo utilizadas");
		}
	}
	
	//retorna todos
	public List<Empresa> findAll(){
		return repo.findAll();
	}
	
	//Paginacao
	/*
	 * page: Nº pagina;
	 * linesPerPage: Nº linhas p/ pagina
	 * orderBy: Qual atributo vai ser utilizado para ordenar
	 * direction: DESC || ASC
	*/
	public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
}
