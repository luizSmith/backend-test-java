package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamento.vagas.domain.Relatorio;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Integer>{

}
