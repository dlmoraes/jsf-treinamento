package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.service.CadastroRegional;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadRegionalMB")
@ViewScoped
public class CadastroRegionalBean implements Serializable {

	private static final long serialVersionUID = -1527344493692855245L;

	@Inject
	private CadastroRegional cadastro;
	@Inject
	private Empresas empresas;
	
	private Regional regional;
	private List<Empresa> lista;
	
	public CadastroRegionalBean() {
		this.lista = new ArrayList<>();
	}
	
	public void inicializar() {
		if (regional == null) {
			regional = new Regional();
		}
		carregarEmpresas();
	}
	
	public void carregarEmpresas() {
		this.lista = this.empresas.todas();
	}
	
	public void salvar() {
		try {
			cadastro.salvar(regional);
			FacesUtil.addInfoMessage("Regional salva, com sucesso!");
			regional = new Regional();
		} catch(NegocioException ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}
	
	public List<Empresa> getLista() {
		return lista;
	}


}
