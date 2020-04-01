package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.vagas.domain.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	
	@Transactional(readOnly=true)  //Não precisa ser envolvida com uma transação do banco
	Empresa findByNome(String nome);
	
	@Transactional(readOnly=true)
	Empresa findByCnpj(String cnjp);
}
