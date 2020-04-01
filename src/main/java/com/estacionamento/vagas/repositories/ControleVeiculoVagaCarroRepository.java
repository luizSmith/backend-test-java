package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamento.vagas.domain.ControleVeiculoVagaCarro;

@Repository
public interface ControleVeiculoVagaCarroRepository extends JpaRepository<ControleVeiculoVagaCarro, Integer>{

}
