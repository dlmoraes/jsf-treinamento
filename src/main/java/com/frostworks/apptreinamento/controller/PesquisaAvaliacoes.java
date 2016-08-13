package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Avaliacao;
import com.frostworks.apptreinamento.repository.Avaliacoes;

@Named("PesAvaliacaoMB")
@ViewScoped
public class PesquisaAvaliacoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Avaliacoes avaliacoes;
	private List<Avaliacao> filtrado;
	private Avaliacao avaliacao;

	@PostConstruct
	public void pesquisar() {
		this.filtrado = new ArrayList<>();
		this.filtrado = this.avaliacoes.todas();
	}

	public List<Avaliacao> getFiltrado() {
		return filtrado;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
