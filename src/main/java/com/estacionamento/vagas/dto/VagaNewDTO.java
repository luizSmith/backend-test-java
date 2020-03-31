package com.estacionamento.vagas.dto;

import javax.validation.constraints.NotNull;

public class VagaNewDTO {
	
	private Integer id;
	
	@NotNull(message="Preenchimento obrigatorio") //nao pode ser vazio
	private Integer empresaId;
	
	@NotNull(message="Preenchimento obrigatorio") //nao pode ser vazio
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
