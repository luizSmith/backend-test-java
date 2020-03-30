package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamento.vagas.domain.VagaCarro;

@Repository
public interface VagaCarroRepository extends JpaRepository<VagaCarro, Integer>{

}
