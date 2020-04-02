package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estacionamento.vagas.domain.ControleVeiculoVagaCarro;

@Repository
public interface ControleVeiculoVagaCarroRepository extends JpaRepository<ControleVeiculoVagaCarro, Integer>{
	
	 @Query(value = "SELECT * FROM CONTROLE_VEICULO_VAGA_CARRO  controle WHERE controle.VAGA_CARRO_ID = :vagaCarro", nativeQuery = true)
	 ControleVeiculoVagaCarro buscaControle(@Param("vagaCarro")Integer vagaCarro);
	 
}
