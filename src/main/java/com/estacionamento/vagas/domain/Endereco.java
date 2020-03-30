package com.estacionamento.vagas.domain;

import java.io.Serializable;

import javax.persistence.Id;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String longradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	public Endereco() {
	}

	public Endereco(Integer id, String longradouro, String numero, String complemento, String bairro, String cep) {
		super();
		this.id = id;
		this.longradouro = longradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLongradouro() {
		return longradouro;
	}

	public void setLongradouro(String longradouro) {
		this.longradouro = longradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
