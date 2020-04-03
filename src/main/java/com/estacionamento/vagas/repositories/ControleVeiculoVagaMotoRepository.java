package com.estacionamento.vagas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estacionamento.vagas.domain.ControleVeiculoVagaMoto;

@Repository
public interface ControleVeiculoVagaMotoRepository extends JpaRepository<ControleVeiculoVagaMoto, Integer>{
	
	 @Query(value = "SELECT * FROM CONTROLE_VEICULO_VAGA_MOTO controle WHERE controle.VAGA_MOTO_ID = :vagaMoto", nativeQuery = true)
	 ControleVeiculoVagaMoto buscaControle(@Param("vagaMoto")Integer vagaMoto);
	 
}
