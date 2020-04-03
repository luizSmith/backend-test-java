package com.estacionamento.vagas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String nome;
	
	@Column(unique=true)
	private String cnpj;
	
	@ElementCollection
	@CollectionTable(
			name="TELEFONE"
	)
	private Set<String> telefones = new HashSet<>();
	
	@OneToOne(
			cascade=CascadeType.ALL, //Permite que delete os endereços junto com a Empresa
			mappedBy = "empresa"
	)
	private Endereco endereco;
	
	@OneToMany(
			mappedBy="empresa",
			cascade=CascadeType.ALL
	)	
	private List<VagaCarro> vagaCarro = new ArrayList<>();
	
	@OneToMany(
			mappedBy="empresa",
			cascade=CascadeType.ALL
	)
	private List<VagaMoto> vagaMoto = new ArrayList<>();
	
	public Empresa() {
	}

	public Empresa(Integer id, String nome, String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
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
	
	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<VagaCarro> getVagaCarro() {
		return vagaCarro;
	}

	public void setVagaCarro(List<VagaCarro> vagaCarro) {
		this.vagaCarro = vagaCarro;
	}

	public List<VagaMoto> getVagaMoto() {
		return vagaMoto;
	}

	public void setVagaMoto(List<VagaMoto> vagaMoto) {
		this.vagaMoto = vagaMoto;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
