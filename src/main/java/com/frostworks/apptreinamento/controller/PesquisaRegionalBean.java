package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.repository.Regionais;
import com.frostworks.apptreinamento.service.CadastroRegional;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("PesRegionalMB")
@ViewScoped
public class PesquisaRegionalBean implements Serializable {

	private static final long serialVersionUID = 3111172035957624642L;

	@Inject
	private CadastroRegional cadastro;
	@Inject
	private Regionais repositorio;
	private Regional selecionada;
	private List<Regional> listaRegional;

	@PostConstruct
	public void inicializar() {
		carregarRegionais();
	}

	public void carregarRegionais() {
		this.listaRegional = new ArrayList<Regional>();
		this.listaRegional = repositorio.todas();
	}

	public void excluir() {
		selecionada = cadastro.excluir(selecionada);
		listaRegional.remove(selecionada);
		selecionada = new Regional();
		FacesUtil.addInfoMessage("Regional removida com sucesso!");
	}

	public Regional getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Regional selecionada) {
		this.selecionada = selecionada;
	}

	public List<Regional> getListaRegional() {
		return listaRegional;
	}

	public void setListaRegional(List<Regional> listaRegional) {
		this.listaRegional = listaRegional;
	}

}
