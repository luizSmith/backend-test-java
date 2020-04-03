package com.estacionamento.vagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ControleVeiculoVagaMotoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatorio")
	private Integer motoId; 

	private Date saida;
	
	public ControleVeiculoVagaMotoDTO () {
	}

	public Integer getMotoId() {
		return motoId;
	}

	public void setMotoId(Integer motoId) {
		this.motoId = motoId;
	}



	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

}
