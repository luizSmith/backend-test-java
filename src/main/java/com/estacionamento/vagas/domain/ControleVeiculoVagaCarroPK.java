package com.estacionamento.vagas.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Classe auxiliar para fazer a representacao
@Embeddable
public class ControleVeiculoVagaCarroPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="vagaCarro_id")
	private VagaCarro vagaCarro;
	
	@ManyToOne
	@JoinColumn(name="veiculo_id")
	private Veiculo veiculo;

	public VagaCarro getVagaCarro() {
		return vagaCarro;
	}

	public void setVagaCarro(VagaCarro vagaCarro) {
		this.vagaCarro = vagaCarro;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vagaCarro == null) ? 0 : vagaCarro.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		ControleVeiculoVagaCarroPK other = (ControleVeiculoVagaCarroPK) obj;
		if (vagaCarro == null) {
			if (other.vagaCarro != null)
				return false;
		} else if (!vagaCarro.equals(other.vagaCarro))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
}
