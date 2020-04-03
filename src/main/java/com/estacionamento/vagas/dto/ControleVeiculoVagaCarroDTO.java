package com.estacionamento.vagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.estacionamento.vagas.services.validation.ControleVeiculoVagaCarroUpdate;

@ControleVeiculoVagaCarroUpdate
public class ControleVeiculoVagaCarroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer carroId; 

	private Date saida;
	
	public ControleVeiculoVagaCarroDTO () {
	}

	public Integer getCarroId() {
		return carroId;
	}

	public void setCarroId(Integer carroId) {
		this.carroId = carroId;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

}
