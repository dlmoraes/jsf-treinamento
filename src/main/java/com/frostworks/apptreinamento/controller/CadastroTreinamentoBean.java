package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.service.GestaoTreinamento;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadTreinamentoMB")
@ViewScoped
public class CadastroTreinamentoBean implements Serializable {

	private static final long serialVersionUID = -5799072777730774824L;

	@Inject
	private GestaoTreinamento gestao;

	@Inject
	private Empresas empresas;

	private Treinamento treinamento;
	private List<Empresa> listaEmpresas;

	@PostConstruct
	public void inicializar() {
		if (treinamento == null) {
			treinamento = new Treinamento();
		}
		if (FacesUtil.isNotPostBack()) {
			carregarEmpresas();
		}

	}

	public void carregarEmpresas() {
		listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = empresas.todas();
	}

	public void salvar() {
		treinamento = gestao.salvar(treinamento);
		FacesUtil.addInfoMessage("Treinamento salvo com sucesso!");
	}

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}

	public TipoTreinamento[] getTipos() {
		return TipoTreinamento.values();
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

}
