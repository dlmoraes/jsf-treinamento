package com.frostworks.apptreinamento.repository.filter;

import java.io.Serializable;

import com.frostworks.apptreinamento.model.Situacao;

public class AgenciaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String nomeRegional;
	private Long[] empresas;
	private Situacao[] situacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeRegional() {
		return nomeRegional;
	}

	public void setNomeRegional(String nomeRegional) {
		this.nomeRegional = nomeRegional;
	}

	public Long[] getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Long[] empresas) {
		this.empresas = empresas;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

}
