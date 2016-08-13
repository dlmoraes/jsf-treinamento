package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.event.LotacaoAlteradoEvent;
import com.frostworks.apptreinamento.event.LotacaoEdicao;
import com.frostworks.apptreinamento.model.Atendente;
//import com.frostworks.apptreinamento.model.Avaliacao;
import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Atendentes;
import com.frostworks.apptreinamento.repository.Lotacoes;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.repository.filter.LotacaoFilter;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("PesLotacaoMB")
@ViewScoped
public class PesquisaLotacaoBean implements Serializable {

	private static final long serialVersionUID = -5266049865187218256L;

	@Inject
	private Lotacoes repositorio;

	@Inject
	private Atendentes atendenteRepository;

	@Inject
	private Treinamentos treinamentoRepository;

	@Produces
	@LotacaoEdicao
	private Lotacao selecionada;
	private List<Lotacao> lotacoes;
	private List<Treinamento> treinamentos;
	private LotacaoFilter filtro;

	public PesquisaLotacaoBean() {
		filtro = new LotacaoFilter();
		lotacoes = new ArrayList<>();
		selecionada = new Lotacao();

	}

	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			carregarTreinamentos();
		}
	}

	public void pesquisar() {
		lotacoes = new ArrayList<Lotacao>();
		lotacoes = repositorio.filtrados(filtro);

	}

	public void carregarTreinamentos() {
		treinamentos = new ArrayList<Treinamento>();
		treinamentos = treinamentoRepository.todos();
	}

	public List<Atendente> completarAtendente(String nome) {
		return atendenteRepository.porNomeAtivos(nome, null, null, null);
	}

	public List<Treinamento> completarTreinamento(String titulo) {
		return treinamentoRepository.porTituloAtivos(titulo);
	}

	public void lotacaoAlterada(@Observes LotacaoAlteradoEvent event) {
		lotacoes.remove(selecionada);
		lotacoes.add(event.getLotacao());
	}

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public LotacaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(LotacaoFilter filtro) {
		this.filtro = filtro;
	}

	public TipoTreinamento[] getTiposTreinamento() {
		return TipoTreinamento.values();
	}

	public Lotacao getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Lotacao selecionada) {
		this.selecionada = selecionada;
	}

	public List<Treinamento> getTreinamentos() {
		return treinamentos;
	}

}
