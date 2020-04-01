package com.estacionamento.vagas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ControleVeiculoVagaCarro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId // por ser uma chave  composta
	private ControleVeiculoVagaCarroPK id = new ControleVeiculoVagaCarroPK();
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date entrada;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date saida;
	
	ControleVeiculoVagaCarro(){
	}

	public ControleVeiculoVagaCarro(VagaCarro vagaCarro, Veiculo veiculo, Date entrada) {
		super();
		id.setVagaCarro(vagaCarro);
		id.setVeiculo(veiculo);
		
		this.entrada = entrada;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
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
		ControleVeiculoVagaCarro other = (ControleVeiculoVagaCarro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
