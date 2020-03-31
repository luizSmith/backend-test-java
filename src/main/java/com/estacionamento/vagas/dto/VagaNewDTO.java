package com.estacionamento.vagas.dto;

import javax.validation.constraints.NotNull;

public class VagaNewDTO {
	
	private Integer id;
	
	@NotNull(message="Preenchimento obrigatorio") //nao pode ser vazio
	private Integer empresaId;
	
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
}
