package com.estacionamento.vagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ControleVeiculoVagaCarroNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer vagaCarroId; 
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer veiculoId;
	
	private Date entrada;
	
	public ControleVeiculoVagaCarroNewDTO () {
	}

	public Integer getVagaCarroId() {
		return vagaCarroId;
	}

	public void setVagaCarroId(Integer vagaCarroId) {
		this.vagaCarroId = vagaCarroId;
	}

	public Integer getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Integer veiculoId) {
		this.veiculoId = veiculoId;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}
}
