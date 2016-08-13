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

import com.frostworks.apptreinamento.event.TreinamentoAlteradoEvent;
import com.frostworks.apptreinamento.event.TreinamentoEdicao;
import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.SituacaoTreinamento;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.repository.filter.TreinamentoFilter;
import com.frostworks.apptreinamento.service.GestaoTreinamento;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("PesTreinamentoMB")
@ViewScoped
public class PesquisaTreinamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoTreinamento gestao;
	
	@Inject
	private Treinamentos treinamentos;
	
	@Inject
	private Empresas empresas;
	
	private List<Treinamento> filtrado;
	private TreinamentoFilter filtro;
	private List<Empresa> listaEmpresas;

	@Produces
	@TreinamentoEdicao
	private Treinamento selecionado;

	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			carregarEmpresas();
		}
		
		filtro = new TreinamentoFilter();
		selecionado = new Treinamento();
	}

	public void carregarTreinamentos() {
		filtrado = new ArrayList<Treinamento>();
		filtrado = treinamentos.filtrados(filtro);
	}
	
	public void carregarEmpresas() {
		listaEmpresas = new ArrayList<>();
		listaEmpresas = empresas.todas();
	}

	public void treinamentoAlterado(@Observes TreinamentoAlteradoEvent event) {
		filtrado.remove(selecionado);
		filtrado.add(event.getTreinamento());
	}

	public void excluir() {
		selecionado = gestao.excluir(selecionado);
		filtrado.remove(selecionado);
		selecionado = new Treinamento();
		FacesUtil.addInfoMessage("Treinamento removido com sucesso!");
	}

	public List<Treinamento> getFiltrado() {
		return filtrado;
	}

	public Treinamento getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Treinamento selecionado) {
		this.selecionado = selecionado;
	}

	public TreinamentoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(TreinamentoFilter filtro) {
		this.filtro = filtro;
	}

	public SituacaoTreinamento[] getSituacoes() {
		return SituacaoTreinamento.values();
	}

	public TipoTreinamento[] getTipos() {
		return TipoTreinamento.values();
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

}
