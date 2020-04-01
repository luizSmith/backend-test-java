package com.estacionamento.vagas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class VagaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message="Preenchimento obrigatorio") //nao pode ser vazio
	private Integer empresaId;
	
	private Integer statusVaga;
	
	public VagaNewDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public Integer getStatusVaga() {
		return statusVaga;
	}

	public void setStatusVaga(Integer statusVaga) {
		this.statusVaga = statusVaga;
	}
	
}
