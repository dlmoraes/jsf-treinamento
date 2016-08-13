package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;
import com.frostworks.apptreinamento.service.CadastroEmpresa;
import com.frostworks.apptreinamento.service.NegocioException;

@Named("PesEmpresaMB")
@ViewScoped
public class PesquisaEmpresaBean implements Serializable {

	private static final long serialVersionUID = 6823003307204908336L;
	@Inject
	private Empresas empresas;
	@Inject
	private CadastroEmpresa cadastro;
	private List<Empresa> lista;
	private Empresa selecionada;

	@PostConstruct
	public void inicializar() {
		System.out.println("Carregando empresas...");
		carregarEmpresas();
		selecionada = new Empresa();
	}
	
	public void carregarEmpresas() {
		lista = new ArrayList<Empresa>();
		lista = empresas.todas();
	}
	
	public void excluirEmpresa() {
		try {
			selecionada = cadastro.excluir(selecionada);
			lista.remove(selecionada);
			selecionada = new Empresa();
		} catch (NegocioException ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
	}
	
	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}

	public Empresa getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Empresa selecionada) {
		this.selecionada = selecionada;
	}
	

}
