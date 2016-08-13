package com.frostworks.apptreinamento.repository.filter;

import java.io.Serializable;

import com.frostworks.apptreinamento.model.Situacao;

public class AtendenteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String matricula;
	private String agencia;
	private Situacao[] situacoes;
	private String cargo;
	private Long[] empresas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Long[] getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Long[] empresas) {
		this.empresas = empresas;
	}

}
