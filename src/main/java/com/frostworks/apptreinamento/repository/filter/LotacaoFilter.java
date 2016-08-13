package com.frostworks.apptreinamento.repository.filter;

import java.io.Serializable;

import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.model.TipoTreinamento;

public class LotacaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String regional;
	private String nomeAtendente;
	private String treinamento;
	private TipoTreinamento[] tipoTreinamentos;
	private Situacao[] situacao;

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getNomeAtendente() {
		return nomeAtendente;
	}

	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}

	public String getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(String treinamento) {
		this.treinamento = treinamento;
	}

	public Situacao[] getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao[] situacao) {
		this.situacao = situacao;
	}

	public TipoTreinamento[] getTipoTreinamentos() {
		return tipoTreinamentos;
	}

	public void setTipoTreinamentos(TipoTreinamento[] tipoTreinamentos) {
		this.tipoTreinamentos = tipoTreinamentos;
	}

}
