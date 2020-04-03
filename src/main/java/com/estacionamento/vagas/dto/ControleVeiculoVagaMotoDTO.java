package com.estacionamento.vagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ControleVeiculoVagaMotoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer vagaMotoId; 

	private Date saida;
	
	public ControleVeiculoVagaMotoDTO () {
	}

	public Integer getVagaMotoId() {
		return vagaMotoId;
	}

	public void setVagaMotoId(Integer vagaMotoId) {
		this.vagaMotoId = vagaMotoId;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

}
