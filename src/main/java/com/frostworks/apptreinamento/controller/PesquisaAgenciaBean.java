package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.repository.Agencias;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.repository.filter.AgenciaFilter;
import com.frostworks.apptreinamento.service.CadastroAgencia;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("PesAgenciaMB")
@ViewScoped
public class PesquisaAgenciaBean implements Serializable {

	private static final long serialVersionUID = -3382564134318218361L;

	@Inject
	private CadastroAgencia cadastro;
	
	@Inject
	private Agencias repositorio;
	
	@Inject
	private Empresas empresas;

	private List<Agencia> listaAgencias;
	private Agencia selecionada;
	private AgenciaFilter filtro;
	private List<Empresa> listaEmpresas;

	@PostConstruct
	public void inicializar() {
		
		if (FacesUtil.isNotPostBack()) {
			carregarEmpresas();
		}
		
		selecionada = new Agencia();
		filtro = new AgenciaFilter();
	}

	public void carregarAgencias() {
		listaAgencias = new ArrayList<Agencia>();
		listaAgencias = repositorio.filtrados(filtro);
	}
	
	public void carregarEmpresas() {
		listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = empresas.todas();
	}

	public void excluir() {
		selecionada = cadastro.excluir(selecionada);
		listaAgencias.remove(selecionada);
		selecionada = new Agencia();
		FacesUtil.addInfoMessage("Agência removida com sucesso!");
		
	}

	public List<Agencia> getListaAgencias() {
		return listaAgencias;
	}

	public void setListaAgencias(List<Agencia> listaAgencias) {
		this.listaAgencias = listaAgencias;
	}

	public Agencia getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Agencia selecionada) {
		this.selecionada = selecionada;
	}

	public AgenciaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AgenciaFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	
	public Situacao[] getSituacoes() {
		return Situacao.values();
	}

}
