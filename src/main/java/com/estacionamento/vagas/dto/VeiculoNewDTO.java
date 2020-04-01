package com.estacionamento.vagas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.estacionamento.vagas.services.validation.VeiculoInsert;

@VeiculoInsert
public class VeiculoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String marca;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String modelo;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String cor;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String placa;
	
	@NotNull(message="Preenchimento obrigatorio") //nao pode ser vazio
	private Integer tipoVeiculo;
	
	public VeiculoNewDTO() {
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

	public Integer getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
}
