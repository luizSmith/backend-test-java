package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamento.vagas.domain.VagaMoto;

@Repository
public interface VagaMotoRepository extends JpaRepository<VagaMoto, Integer>{

}
