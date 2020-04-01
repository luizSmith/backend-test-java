package com.estacionamento.vagas.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.estacionamento.vagas.domain.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Veiculo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String marca;
	private String modelo;
	private String cor;
	private String placa;
	
	private Integer tipoVeiculo;
	
	@JsonIgnore
	@OneToMany(mappedBy="id.veiculo")
	private Set<ControleVeiculoVagaCarro> controle = new HashSet<>();	
	
	public Veiculo() {
	}

	public Veiculo(Integer id, String marca, String modelo, String cor, String placa, TipoVeiculo tipoVeiculo) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.tipoVeiculo = (tipoVeiculo == null) ? null : tipoVeiculo.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public TipoVeiculo getTipoVeiculo() {
		return TipoVeiculo.toEnum(tipoVeiculo);
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo.getCod();
	}

	public Set<ControleVeiculoVagaCarro> getControle() {
		return controle;
	}

	public void setControle(Set<ControleVeiculoVagaCarro> controle) {
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
