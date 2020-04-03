package com.estacionamento.vagas.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.estacionamento.vagas.domain.enums.StatusVaga;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VagaMoto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer statusVaga;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(
			name="empresa_id"
	)
	private Empresa empresa;
	
	@OneToMany(mappedBy="id.vagaMoto")
	private Set<ControleVeiculoVagaMoto> controle = new HashSet<>();
	
	public VagaMoto() {
	}
	
	public VagaMoto(Integer id, Empresa empresa, StatusVaga statusVaga) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.statusVaga = (statusVaga == null) ? null : statusVaga.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public StatusVaga getStatusVaga() {
		return StatusVaga.toEnum(statusVaga);
	}

	public void setStatusVaga(StatusVaga statusVaga) {
		this.statusVaga = statusVaga.getCod();
	}

	public Set<ControleVeiculoVagaMoto> getControle() {
		return controle;
	}

	public void setControle(Set<ControleVeiculoVagaMoto> controle) {
		this.controle = controle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VagaMoto other = (VagaMoto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
