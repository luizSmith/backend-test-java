package com.estacionamento.vagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ControleVeiculoVagaCarroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer vagaCarroId; 

	private Date saida;
	
	public ControleVeiculoVagaCarroDTO () {
	}

	public Integer getVagaCarroId() {
		return vagaCarroId;
	}

	public void setVagaCarroId(Integer vagaCarroId) {
		this.vagaCarroId = vagaCarroId;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

}
