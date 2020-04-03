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
	
	
	@EmbeddedId // por ser uma chave  composta
	@JsonIgnore
	private ControleVeiculoVagaCarroPK id = new ControleVeiculoVagaCarroPK();
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private Date entrada;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private Date saida;
	
	public ControleVeiculoVagaCarro(){
	}	

	public ControleVeiculoVagaCarro(VagaCarro vagaCarro, Veiculo veiculo, Date entrada) {
		super();
		id.setVagaCarro(vagaCarro);
		id.setVeiculo(veiculo);

		this.entrada = entrada;
	}
	
	@JsonIgnore
	public VagaCarro getVagaCarro() {
		return id.getVagaCarro();
	}

	public void setVagaCarro(VagaCarro vagaCarro) {
		id.setVagaCarro(vagaCarro);
	}

	public Veiculo getVeiculo() {
		return id.getVeiculo();
	}

	public void setVeiculo(Veiculo veiculo) {
		id.setVeiculo(veiculo);
	}

	public ControleVeiculoVagaCarroPK getId() {
		return id;
	}

	public void setId(ControleVeiculoVagaCarroPK id) {
		this.id = id;
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
	
	
}
