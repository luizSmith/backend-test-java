package com.estacionamento.vagas.domain.enums;

public enum StatusVaga {
	DISPONIVEL (1, "Disponivel"),
	OCUPADA (2, "Ocupada");
	
	private int cod;
	private String descricao;
	
	private StatusVaga(int cod, String descricao) {
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

	public static StatusVaga toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (StatusVaga x : StatusVaga.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id invalido: " + cod);

	}
}
