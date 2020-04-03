package com.estacionamento.vagas.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Classe auxiliar para fazer a representacao
@Embeddable
public class ControleVeiculoVagaMotoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="vagaMoto_id")
	private VagaMoto vagaMoto;
	
	@ManyToOne
	@JoinColumn(name="veiculo_id")
	private Veiculo veiculo;

	public VagaMoto getVagaMoto() {
		return vagaMoto;
	}

	public void setVagaMoto(VagaMoto vagaMoto) {
		this.vagaMoto = vagaMoto;
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
		result = prime * result + ((vagaMoto == null) ? 0 : vagaMoto.hashCode());
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
		ControleVeiculoVagaMotoPK other = (ControleVeiculoVagaMotoPK) obj;
		if (vagaMoto == null) {
			if (other.vagaMoto != null)
				return false;
		} else if (!vagaMoto.equals(other.vagaMoto))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
}
