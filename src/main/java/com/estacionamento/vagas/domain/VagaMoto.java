package com.estacionamento.vagas.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VagaMoto extends Vaga {
	private static final long serialVersionUID = 1L;
	
	public VagaMoto() {
	}
	
	public VagaMoto(Integer id) {
		super(id);
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(
			name="empresa_id"
	)
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
