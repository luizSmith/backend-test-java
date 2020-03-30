package com.estacionamento.vagas.domain.enums;

public enum TipoVeiculo {
	CARRO (1, "Carro"),
	MOTO (2, "Moto");
	
	private int cod;
	private String descricao;
	
	private TipoVeiculo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoVeiculo toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoVeiculo x : TipoVeiculo.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id invalido: " + cod);

	}
}
