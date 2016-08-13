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

import com.frostworks.apptreinamento.event.AtendenteAlteradoEvent;
import com.frostworks.apptreinamento.event.AtendenteEdicao;
//import com.frostworks.apptreinamento.event.AtendenteAlteradoEvent;
//import com.frostworks.apptreinamento.event.AtendenteEdicao;
import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.model.Cargo;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.repository.Atendentes;
import com.frostworks.apptreinamento.repository.Cargos;
import com.frostworks.apptreinamento.repository.filter.AtendenteFilter;
import com.frostworks.apptreinamento.service.CadastroAtendente;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("PesAtendenteMB")
@ViewScoped
public class PesquisaAtendenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CadastroAtendente cadastro;
	@Inject
	private Atendentes atendentes;
	@Inject
	private Cargos cargos;
	
	private List<Atendente> filtrado;
	private List<Cargo> listaCargos;

	@Produces
	@AtendenteEdicao
	private Atendente selecionado;
	private AtendenteFilter filtro;

	@PostConstruct
	public void inicializar() {
		filtro = new AtendenteFilter();
		selecionado = new Atendente();
		if (FacesUtil.isNotPostBack()) {
			carregarCargos();
		}
	}

	public void carregarAtendentes() {
		filtrado = new ArrayList<Atendente>();
		filtrado = atendentes.filtrados(filtro);
	}
	
	public void carregarCargos() {
		this.listaCargos = new ArrayList<>();
		this.listaCargos = this.cargos.todos();
	}

	public void atendenteAlterado(@Observes AtendenteAlteradoEvent event) {
		filtrado.remove(selecionado);
		filtrado.add(event.getAtendente());
	}

	public void excluir() {
		selecionado = cadastro.excluir(selecionado);
		filtrado.remove(selecionado);
		selecionado = new Atendente();
		FacesUtil.addInfoMessage("Atendente removido com sucesso!");
	}

	public Atendente getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Atendente selecionado) {
		this.selecionado = selecionado;
	}

	public List<Atendente> getFiltrado() {
		return filtrado;
	}

	public AtendenteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AtendenteFilter filtro) {
		this.filtro = filtro;
	}

	public Situacao[] getSituacoes() {
		return Situacao.values();
	}
	
	public List<Cargo> getListaCargos() {
		return listaCargos;
	}

}
