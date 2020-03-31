package com.estacionamento.vagas.dto;

import javax.validation.constraints.NotNull;

public class VagaCarroNewDTO {
	
	private Integer id;
	
	@NotNull(message="Preenchimento obrigatorio") //nao pode ser vazio
	private Integer empresaId;
	
	public VagaCarroNewDTO() {
		
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
