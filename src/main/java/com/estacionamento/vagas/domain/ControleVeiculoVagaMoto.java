package com.estacionamento.vagas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ControleVeiculoVagaMoto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId // por ser uma chave  composta
	@JsonIgnore
	private ControleVeiculoVagaMotoPK id = new ControleVeiculoVagaMotoPK();
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private Date entrada;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private Date saida;
	
	public ControleVeiculoVagaMoto(){
	}	

	public ControleVeiculoVagaMoto(VagaMoto vagaMoto, Veiculo veiculo, Date entrada) {
		super();
		id.setVagaMoto(vagaMoto);
		id.setVeiculo(veiculo);

		this.entrada = entrada;
	}
	
	@JsonIgnore
	public VagaMoto getVagaMoto() {
		return id.getVagaMoto();
	}

	public void setVagaMoto(VagaMoto vagaMoto) {
		id.setVagaMoto(vagaMoto);
	}

	public Veiculo getVeiculo() {
		return id.getVeiculo();
	}

	public void setVeiculo(Veiculo veiculo) {
		id.setVeiculo(veiculo);
	}

	public ControleVeiculoVagaMotoPK getId() {
		return id;
	}

	public void setId(ControleVeiculoVagaMotoPK id) {
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
