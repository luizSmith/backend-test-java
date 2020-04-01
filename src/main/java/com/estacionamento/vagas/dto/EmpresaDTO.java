package com.estacionamento.vagas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.estacionamento.vagas.services.validation.EmpresaUpdate;

@EmpresaUpdate
public class EmpresaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String nome;
	
	private String cnpj;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String longradouro;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String numero;
	
	private String complemento;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String bairro;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String cep;
	
	@NotEmpty(message="Preenchimento obrigatorio") //nao pode ser vazio
	private String telefone1;
	
	private String telefone2;
	
	public EmpresaDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	
}
