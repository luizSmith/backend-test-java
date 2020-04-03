package com.estacionamento.vagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ControleVeiculoVagaMotoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer vagaMotoId; 
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer veiculoId;
	
	private Date entrada;
	
	public ControleVeiculoVagaMotoNewDTO () {
	}

	public Integer getVagaMotoId() {
		return vagaMotoId;
	}

	public void setVagaMotoId(Integer vagaMotoId) {
		this.vagaMotoId = vagaMotoId;
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
