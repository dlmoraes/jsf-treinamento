package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.repository.Regionais;
import com.frostworks.apptreinamento.service.CadastroAgencia;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadAgenciaMB")
@ViewScoped
public class CadastroAgenciaBean implements Serializable {

	private static final long serialVersionUID = 5093139700792445498L;

	@Inject
	private CadastroAgencia cadastroAgencia;
	@Inject
	private Regionais regionais;
	@Inject
	private Empresas empresas;

	private Agencia agencia;
	private Empresa empresaSelecionada;
	private List<Empresa> listaEmpresa;
	private List<Regional> lista;
	
	public CadastroAgenciaBean() {
		this.lista = new ArrayList<>();
		this.listaEmpresa = new ArrayList<>();
		this.empresaSelecionada = new Empresa();
	}

	public void inicializar() {
		if (agencia == null) {
			agencia = new Agencia();
		}
		if (FacesUtil.isNotPostBack()) {
			carregarEmpresas();
		}
		
	}

	public void carregarEmpresas() {
		this.listaEmpresa = this.empresas.todas();
	}
	
	public void carregarRegionais(Long idEmpresa) {
		lista = regionais.porEmpresa(idEmpresa);
	}

	public void salvar() {
		try {
			this.agencia.setEmpresa(this.empresaSelecionada);
			cadastroAgencia.salvar(agencia);
			limpar();
			FacesUtil.addInfoMessage("Agência salva com sucesso!");
		} catch (NegocioException ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
	}

	public void limpar() {
		agencia = new Agencia();
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Situacao[] getSituacoes() {
		return Situacao.values();
	}

	public List<Regional> getLista() {
		return lista;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

}
